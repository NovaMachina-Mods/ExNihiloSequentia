package novamachina.exnihilosequentia.common.loot.modifier;

import com.google.gson.JsonObject;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
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
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import novamachina.exnihilosequentia.api.tag.ExNihiloTags;
import novamachina.exnihilosequentia.common.block.InfestedLeavesBlock;
import novamachina.exnihilosequentia.common.crafting.ItemStackWithChance;
import novamachina.exnihilosequentia.common.crafting.crook.CrookRecipe;
import novamachina.exnihilosequentia.common.init.ExNihiloItems;
import novamachina.exnihilosequentia.common.registries.ExNihiloRegistries;
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;
import org.jetbrains.annotations.NotNull;

public class UseCrookModifier extends LootModifier {

  @Nonnull private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

  @Nonnull private final Random random = new SecureRandom();

  public UseCrookModifier(@Nonnull final LootItemCondition[] conditionsIn) {
    super(conditionsIn);
  }

  @Nonnull
  @Override
  public ObjectArrayList<ItemStack> doApply(
      @Nonnull ObjectArrayList<ItemStack> generatedLoot, @Nonnull final LootContext context) {
    logger.debug("Fired Crook Modifier");
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
      final CrookRecipe recipe :
          ExNihiloRegistries.CROOK_REGISTRY.getDrops(blockState.getBlock())) {
        getCrookBlockDrops(newLoot, recipe);
      }

      if (blockState.getBlock() instanceof InfestedLeavesBlock) {
        newLoot.add(
            new ItemStack(
                Items.STRING,
                random.nextInt(Config.getMaxBonusStringCount()) + Config.getMinStringCount()));
        if (random.nextDouble() <= 0.8) {
          newLoot.add(new ItemStack(ExNihiloItems.SILKWORM.get()));
        }
      }
    }
    if (!newLoot.isEmpty()) {
      logger.debug("Adding new loot");
      generatedLoot = newLoot;
    }
    logger.debug("Crook Generated Loot: " + generatedLoot);
    return generatedLoot;
  }

  private void getCrookBlockDrops(@NotNull List<ItemStack> newLoot, @NotNull CrookRecipe recipe) {
    for (@Nonnull final ItemStackWithChance itemStackWithChance : recipe.getOutput()) {
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
      @Nonnull final BlockPos pos = new BlockPos(origin.x(), origin.y(), origin.z());
      @Nonnull final List<ItemStack> items = Block.getDrops(blockState, serverWorld, pos, null);
      newLoot.addAll(
          items.stream()
              .filter(
                  drop -> {
                    @Nullable
                    final ResourceLocation resourceLocation =
                        ForgeRegistries.ITEMS.getKey(drop.getItem());
                    if (resourceLocation == null) {
                      return false;
                    }
                    return !resourceLocation.equals(
                        ForgeRegistries.BLOCKS.getKey(blockState.getBlock()));
                  })
              .toList());
    }
  }

  public static class Serializer extends GlobalLootModifierSerializer<UseCrookModifier> {

    @Override
    @Nonnull
    public UseCrookModifier read(
        @Nonnull final ResourceLocation location,
        @Nonnull final JsonObject object,
        @Nonnull final LootItemCondition[] ailootcondition) {
      return new UseCrookModifier(ailootcondition);
    }

    @Override
    @Nonnull
    public JsonObject write(@Nonnull final UseCrookModifier instance) {
      return makeConditions(instance.conditions);
    }
  }
}
