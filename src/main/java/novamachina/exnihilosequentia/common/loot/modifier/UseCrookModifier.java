package novamachina.exnihilosequentia.common.loot.modifier;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;
import novamachina.exnihilosequentia.common.Config;
import novamachina.exnihilosequentia.common.registries.ExNihiloRegistries;
import novamachina.exnihilosequentia.tags.ExNihiloTags;
import novamachina.exnihilosequentia.world.item.EXNItems;
import novamachina.exnihilosequentia.world.item.crafting.HarvestRecipe;
import novamachina.exnihilosequentia.world.item.crafting.ItemStackWithChance;
import novamachina.exnihilosequentia.world.level.block.InfestedLeavesBlock;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UseCrookModifier extends LootModifier {

  private static Logger log = LoggerFactory.getLogger(UseCrookModifier.class);

  public static final Supplier<Codec<UseCrookModifier>> CODEC =
      Suppliers.memoize(
          () ->
              RecordCodecBuilder.create(
                  inst -> codecStart(inst).apply(inst, UseCrookModifier::new)));

  @Nonnull private final Random random = new SecureRandom();

  public UseCrookModifier(@Nonnull final LootItemCondition[] conditionsIn) {
    super(conditionsIn);
  }

  @Nonnull
  @Override
  public ObjectArrayList<ItemStack> doApply(
      @Nonnull ObjectArrayList<ItemStack> generatedLoot, @Nonnull final LootContext context) {
    log.debug("Fired Crook Modifier");
    @Nullable final ItemStack tool = context.getParamOrNull(LootContextParams.TOOL);
    @Nullable final BlockState blockState = context.getParamOrNull(LootContextParams.BLOCK_STATE);
    @Nullable final Vec3 origin = context.getParamOrNull(LootContextParams.ORIGIN);
    @Nonnull final ObjectArrayList<ItemStack> newLoot = new ObjectArrayList<>();

    if (tool != null
        && blockState != null
        && tool.getItem().getDefaultInstance().is(ExNihiloTags.CROOK)
        && ExNihiloRegistries.CROOK_REGISTRY.isCrookable(blockState.getBlock())) {
      for (int i = 0; i < Config.getVanillaSimulateDropCount(); i++) {
        getVanillaDrops(context, blockState, origin, newLoot);
      }

      for (@Nonnull
      final HarvestRecipe recipe :
          ExNihiloRegistries.CROOK_REGISTRY.getDrops(blockState.getBlock())) {
        getCrookBlockDrops(newLoot, recipe);
      }

      if (blockState.getBlock() instanceof InfestedLeavesBlock) {
        newLoot.add(
            new ItemStack(
                Items.STRING,
                random.nextInt(Config.getMaxBonusStringCount()) + Config.getMinStringCount()));
        if (random.nextDouble() <= 0.8) {
          newLoot.add(EXNItems.SILKWORM.itemStack());
        }
      }
    }
    if (!newLoot.isEmpty()) {
      log.debug("Adding new loot");
      generatedLoot = newLoot;
    }
    log.debug("Crook Generated Loot: " + generatedLoot);
    return generatedLoot;
  }

  private void getCrookBlockDrops(@NotNull List<ItemStack> newLoot, @NotNull HarvestRecipe recipe) {
    for (@Nonnull final ItemStackWithChance itemStackWithChance : recipe.getDrops()) {
      if (random.nextFloat() <= itemStackWithChance.getChance()
          && itemStackWithChance.getStack() != ItemStack.EMPTY) {
        newLoot.add(itemStackWithChance.getStack());
      }
    }
  }

  private void getVanillaDrops(
      @NotNull LootContext context,
      @NotNull BlockState blockState,
      @org.jetbrains.annotations.Nullable Vec3 origin,
      @NotNull List<ItemStack> newLoot) {
    @Nullable
    final ServerLevel serverWorld =
        context.getLevel().getServer().getLevel(context.getLevel().dimension());
    if (origin != null && serverWorld != null) {
      @Nonnull
      final BlockPos pos = new BlockPos((int) origin.x(), (int) origin.y(), (int) origin.z());
      @Nonnull final List<ItemStack> items = Block.getDrops(blockState, serverWorld, pos, null);
      newLoot.addAll(
          items.stream()
              .filter(
                  drop -> {
                    @Nullable
                    final ResourceLocation resourceLocation =
                        BuiltInRegistries.ITEM.getKey(drop.getItem());
                    if (resourceLocation == null) {
                      return false;
                    }
                    return !resourceLocation.equals(
                        BuiltInRegistries.BLOCK.getKey(blockState.getBlock()));
                  })
              .toList());
    }
  }

  @Override
  public Codec<? extends IGlobalLootModifier> codec() {
    return CODEC.get();
  }
}
