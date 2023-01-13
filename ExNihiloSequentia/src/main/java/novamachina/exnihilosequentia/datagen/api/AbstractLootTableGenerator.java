package novamachina.exnihilosequentia.datagen.api;

import com.google.common.collect.Multimap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.logging.LogUtils;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.registries.ForgeRegistries;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;

public abstract class AbstractLootTableGenerator implements DataProvider {

  @Nonnull
  private static final Gson GSON =
      (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();

  @Nonnull private static final ExNihiloLogger logger = new ExNihiloLogger(LogUtils.getLogger());
  @Nonnull protected final Map<ResourceLocation, LootTable> lootTables = new HashMap<>();
  @Nonnull private final DataGenerator generator;
  @Nonnull private final String modId;

  protected AbstractLootTableGenerator(
      @Nonnull final DataGenerator generator, @Nonnull final String modId) {
    this.generator = generator;
    this.modId = modId;
  }

  @Override
  public void run(@Nonnull final CachedOutput cache) {
    lootTables.clear();
    @Nonnull final Path outFolder = generator.getOutputFolder();

    createLootTables();

    @Nonnull
    final ValidationContext validator =
        new ValidationContext(LootContextParamSets.ALL_PARAMS, function -> null, lootTables::get);
    lootTables.forEach((name, table) -> LootTables.validate(validator, name, table));
    @Nonnull final Multimap<String, String> problems = validator.getProblems();
    if (!problems.isEmpty()) {
      problems.forEach(
          (name, table) -> logger.warn("Found validation problem in " + name + ": " + table));
      throw new IllegalStateException("Failed to validate loot tables, see logs");
    } else {
      lootTables.forEach(
          (name, table) -> {
            @Nonnull final Path out = getPath(outFolder, name);

            try {
              DataProvider.saveStable(cache, LootTables.serialize(table), out);
            } catch (IOException e) {
              logger.error("Couldn't save loot table " + out);
              logger.error(Arrays.toString(e.getStackTrace()));
            }
          });
    }
  }

  @Nonnull
  @Override
  public String getName() {
    return "Loot Tables: " + modId;
  }

  @Nonnull
  protected LootPool.Builder createLootPoolBuilder() {
    return LootPool.lootPool().when(ExplosionCondition.survivesExplosion());
  }

  protected abstract void createLootTables();

  protected void register(@Nonnull final Block block, @Nonnull final LootPool.Builder... pools) {
    @Nonnull final LootTable.Builder builder = LootTable.lootTable();
    for (@Nonnull final LootPool.Builder pool : pools) {
      builder.withPool(pool);
    }
    register(block, builder);
  }

  protected void registerSelfDrop(@Nonnull final Block block) {
    register(block, singleItem(block));
  }

  @Nonnull
  private Path getPath(@Nonnull final Path outFolder, @Nonnull final ResourceLocation name) {
    return outFolder.resolve(
        "data/" + name.getNamespace() + "/loot_tables/" + name.getPath() + ".json");
  }

  private void register(@Nonnull final Block block, @Nonnull final LootTable.Builder table) {
    @Nullable final ResourceLocation resourceLocation = ForgeRegistries.BLOCKS.getKey(block);
    if (resourceLocation == null) {
      return;
    }
    register(resourceLocation, table);
  }

  private void register(
      @Nonnull final ResourceLocation registryName, @Nonnull final LootTable.Builder table) {
    if (lootTables.put(
            toTableLoc(registryName), table.setParamSet(LootContextParamSets.BLOCK).build())
        != null) {
      throw new IllegalStateException("Duplicate loot table: " + table);
    }
  }

  @Nonnull
  private LootPool.Builder singleItem(@Nonnull final ItemLike in) {
    return createLootPoolBuilder()
        .setRolls(ConstantValue.exactly(1))
        .add(LootItem.lootTableItem(in));
  }

  @Nonnull
  private ResourceLocation toTableLoc(@Nonnull final ResourceLocation registryName) {
    return new ResourceLocation(registryName.getNamespace(), "blocks/" + registryName.getPath());
  }
}
