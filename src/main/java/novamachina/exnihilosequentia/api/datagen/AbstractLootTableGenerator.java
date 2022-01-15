package novamachina.exnihilosequentia.api.datagen;

import com.google.common.collect.Multimap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.loot.ConstantRange;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTableManager;
import net.minecraft.loot.ValidationTracker;
import net.minecraft.loot.conditions.SurvivesExplosion;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class AbstractLootTableGenerator implements IDataProvider {
    @Nonnull private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();
    @Nonnull private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());
    @Nonnull protected final Map<ResourceLocation, LootTable> lootTables = new HashMap<>();
    @Nonnull private final DataGenerator generator;
    @Nonnull private final String modId;

    protected AbstractLootTableGenerator(@Nonnull final DataGenerator generator, @Nonnull final String modId) {
        this.generator = generator;
        this.modId = modId;
    }

    @Override
    public void run(@Nonnull final DirectoryCache cache) {
        lootTables.clear();
        @Nonnull final Path outFolder = generator.getOutputFolder();

        createLootTables();

        @Nonnull final ValidationTracker validator = new ValidationTracker(LootParameterSets.ALL_PARAMS,
                function -> null, lootTables::get);
        lootTables.forEach((name, table) -> LootTableManager.validate(validator, name, table));
        @Nonnull final Multimap<String, String> problems = validator.getProblems();
        if (!problems.isEmpty()) {
            problems.forEach((name, table) -> logger.warn("Found validation problem in " + name + ": " + table));
            throw new IllegalStateException("Failed to validate loot tables, see logs");
        } else {
            lootTables.forEach((name, table) -> {
                @Nonnull final Path out = getPath(outFolder, name);

                try {
                    IDataProvider.save(GSON, cache, LootTableManager.serialize(table), out);
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
        return LootPool.lootPool().when(SurvivesExplosion.survivesExplosion());
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
        return outFolder.resolve("data/" + name.getNamespace() + "/loot_tables/" + name.getPath() + ".json");
    }

    private void register(@Nonnull final Block block, @Nonnull final LootTable.Builder table) {
        @Nullable final ResourceLocation resourceLocation = block.getRegistryName();
        if (resourceLocation == null)
            return;
        register(resourceLocation, table);
    }

    private void register(@Nonnull final ResourceLocation registryName, @Nonnull final LootTable.Builder table) {
        if (lootTables.put(toTableLoc(registryName), table.setParamSet(LootParameterSets.BLOCK).build()) != null) {
            throw new IllegalStateException("Duplicate loot table: " + table);
        }
    }

    @Nonnull
    private LootPool.Builder singleItem(@Nonnull final IItemProvider in) {
        return createLootPoolBuilder().setRolls(ConstantRange.exactly(1)).add(ItemLootEntry.lootTableItem(in));
    }

    @Nonnull
    private ResourceLocation toTableLoc(@Nonnull final ResourceLocation registryName) {
        return new ResourceLocation(registryName.getNamespace(), "blocks/" + registryName.getPath());
    }
}
