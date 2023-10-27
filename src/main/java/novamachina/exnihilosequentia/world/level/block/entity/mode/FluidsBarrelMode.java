package novamachina.exnihilosequentia.world.level.block.entity.mode;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;
import novamachina.exnihilosequentia.common.registries.ExNihiloRegistries;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.world.item.DollItem;
import novamachina.exnihilosequentia.world.level.block.entity.BarrelBlockEntity;
import novamachina.novacore.util.TankUtil;

public class FluidsBarrelMode extends AbstractBarrelMode {

  public FluidsBarrelMode(@Nonnull final String name) {
    super(name);
  }

  @Override
  public void tick(@Nonnull final BarrelBlockEntity barrelTile) {
    if (barrelTile.getFluidAmount() <= 0) {
      barrelTile.setMode(BarrelModeRegistry.getModeFromName(ExNihiloConstants.BarrelModes.EMPTY));
    }
    if (barrelTile.getFluidAmount() >= BarrelBlockEntity.MAX_FLUID_AMOUNT) {
      if (fluidOnTop(barrelTile)) {
        return;
      }
      @Nullable final Level world = barrelTile.getLevel();
      if (world != null) {
        @Nonnull final BlockPos blockPosBelow = barrelTile.getBlockPos().offset(0, -1, 0);
        @Nonnull final Block blockBelow = world.getBlockState(blockPosBelow).getBlock();
        fluidTransform(barrelTile, blockBelow);
      }
    }
  }

  @SuppressWarnings("UnusedReturnValue")
  private boolean doMobSpawn(
      @Nonnull final BarrelBlockEntity barrelTile,
      @Nonnull final Player player,
      @Nonnull final InteractionHand handIn) {
    if (checkFluidAmount(barrelTile)) {
      return false;
    }

    @Nonnull final Item item = player.getItemInHand(handIn).getItem();

    if (item instanceof @Nonnull final DollItem doll) {
      @Nullable final Fluid barrelFluid = barrelTile.getFluid();
      if (barrelFluid != null && barrelFluid.isSame(doll.getSpawnFluid())) {
        barrelTile.setMode(ExNihiloConstants.BarrelModes.MOB);
        ((MobSpawnBarrelMode) barrelTile.getMode()).setDoll(doll);
        player.getItemInHand(handIn).shrink(1);
        return true;
      }
    }

    return false;
  }

  private boolean fluidTransform(
      @Nonnull final BarrelBlockEntity barrelTile, @Nonnull final ItemLike catalyst) {
    if (checkFluidAmount(barrelTile)) {
      return false;
    }
    @Nullable final Fluid fluidInTank = barrelTile.getTank().getFluid().getFluid();

    if (ExNihiloRegistries.FLUID_TRANSFORM_REGISTRY.isValidRecipe(fluidInTank, catalyst)) {
      barrelTile.setMode(ExNihiloConstants.BarrelModes.TRANSFORM);
      ((FluidTransformBarrelMode) barrelTile.getMode()).setCatalyst(catalyst);
      return true;
    }
    return false;
  }

  private boolean fluidOnTop(BarrelBlockEntity barrelTile) {
    if (checkFluidAmount(barrelTile)) {
      return false;
    }

    @Nullable final Level world = barrelTile.getLevel();
    if (world == null) {
      return false;
    }

    @Nonnull
    final Fluid fluidOnTop =
        world.getFluidState(barrelTile.getBlockPos().offset(0, 1, 0)).getType();
    @Nonnull final Fluid fluidInTank = barrelTile.getTank().getFluid().getFluid();

    if (ExNihiloRegistries.FLUID_ON_TOP_REGISTRY.isValidRecipe(fluidInTank, fluidOnTop)) {
      barrelTile.getTank().setFluid(FluidStack.EMPTY);
      barrelTile
          .getInventory()
          .setStackInSlot(
              0, ExNihiloRegistries.FLUID_ON_TOP_REGISTRY.getResult(fluidInTank, fluidOnTop));
      barrelTile.setMode(ExNihiloConstants.BarrelModes.BLOCK);
      return true;
    }
    return false;
  }

  @Override
  @Nonnull
  public InteractionResult onBlockActivated(
      @Nonnull final BarrelBlockEntity barrelTile,
      @Nonnull final Player player,
      @Nonnull final InteractionHand handIn,
      @Nonnull final IFluidHandler fluidHandler,
      @Nonnull final IItemHandler itemHandler) {
    @Nonnull final ItemStack stack = player.getItemInHand(handIn);
    if (stack.isEmpty()) {
      return InteractionResult.SUCCESS;
    }

    if (TankUtil.drainWaterIntoBottle(barrelTile, player, fluidHandler)) {
      return InteractionResult.SUCCESS;
    }
    if (TankUtil.drainWaterFromBottle(barrelTile, player, fluidHandler)) {
      return InteractionResult.SUCCESS;
    }

    boolean result = FluidUtil.interactWithFluidHandler(player, handIn, fluidHandler);

    if (result) {
      if (!player.isCreative()) {
        stack.shrink(1);
      }
      @Nullable final Level world = barrelTile.getLevel();
      if (world != null) {
        @Nonnull final BlockState blockState = barrelTile.getBlockState();
        world.sendBlockUpdated(barrelTile.getBlockPos(), blockState, blockState, 2);
      }
      barrelTile.setChanged();
      return InteractionResult.SUCCESS;
    }

    if (fluidBlockTransform(barrelTile, player, handIn)) {
      return InteractionResult.SUCCESS;
    }

    @Nonnull final ItemLike catalyst = player.getItemInHand(handIn).getItem();
    if (fluidTransform(barrelTile, catalyst)) {
      player.getItemInHand(handIn).shrink(1);
    }

    doMobSpawn(barrelTile, player, handIn);

    return InteractionResult.SUCCESS;
  }

  private boolean fluidBlockTransform(
      @Nonnull final BarrelBlockEntity barrelTile,
      @Nonnull final Player player,
      @Nonnull final InteractionHand handIn) {
    if (checkFluidAmount(barrelTile)) {
      return false;
    }
    @Nonnull final Fluid fluid = barrelTile.getTank().getFluid().getFluid();
    @Nonnull final Item input = player.getItemInHand(handIn).getItem();
    if (ExNihiloRegistries.FLUID_BLOCK_REGISTRY.isValidRecipe(fluid, input)) {
      barrelTile.getTank().drain(FluidType.BUCKET_VOLUME, IFluidHandler.FluidAction.EXECUTE);
      barrelTile
          .getInventory()
          .setStackInSlot(
              0, new ItemStack(ExNihiloRegistries.FLUID_BLOCK_REGISTRY.getResult(fluid, input)));
      if (!player.isCreative()) {
        player.getItemInHand(handIn).shrink(1);
      }
      barrelTile.setMode(ExNihiloConstants.BarrelModes.BLOCK);
      return true;
    }
    return false;
  }

  @Override
  public boolean canFillWithFluid(@Nonnull final BarrelBlockEntity barrel) {
    return true;
  }

  @Override
  public boolean isEmptyMode() {
    return false;
  }

  @Override
  protected boolean isTriggerItem(@Nonnull final ItemStack stack) {
    return FluidUtil.getFluidContained(stack).map(FluidStack::getAmount).orElse(0)
            >= FluidType.BUCKET_VOLUME
        || ItemStack.isSameItem(
            stack, PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WATER));
  }

  @Override
  public void read(@Nonnull final CompoundTag nbt) {
    // NOOP
  }

  @Override
  @Nonnull
  public CompoundTag write() {
    return new CompoundTag();
  }

  @Override
  protected void spawnParticle(@Nonnull final BarrelBlockEntity barrelTile) {
    // NOOP
  }

  @Override
  @Nonnull
  public List<Component> getWailaInfo(@Nonnull final BarrelBlockEntity barrelTile) {
    @Nonnull final List<Component> info = new ArrayList<>();

    @Nonnull final String key;
    if (barrelTile.getFluid() == null) {
      key = Fluids.EMPTY.getFluidType().getDescriptionId();
    } else {
      key = barrelTile.getFluid().getFluidType().getDescriptionId();
    }

    info.add(
        Component.translatable(
            "waila.barrel.fluidAmount", Component.translatable(key), barrelTile.getFluidAmount()));

    return info;
  }

  @Override
  @Nonnull
  public ItemStack handleInsert(
      @Nonnull final BarrelBlockEntity barrelTile,
      @Nonnull final ItemStack stack,
      final boolean simulate) {
    if (checkFluidAmount(barrelTile)) {
      return stack.copy();
    }
    @Nonnull final ItemStack returnStack = stack.copy();
    @Nonnull final Fluid fluid = barrelTile.getTank().getFluid().getFluid();
    @Nonnull final Item input = stack.getItem();
    if (ExNihiloRegistries.FLUID_BLOCK_REGISTRY.isValidRecipe(fluid, input)) {
      if (!simulate) {
        barrelTile.getTank().drain(FluidType.BUCKET_VOLUME, IFluidHandler.FluidAction.EXECUTE);
        barrelTile
            .getInventory()
            .setStackInSlot(
                0, new ItemStack(ExNihiloRegistries.FLUID_BLOCK_REGISTRY.getResult(fluid, input)));
        barrelTile.setMode(ExNihiloConstants.BarrelModes.BLOCK);
      }
      returnStack.shrink(1);
      return returnStack;
    }

    if (input instanceof @Nonnull final DollItem doll) {
      @Nullable final Fluid barrelFluid = barrelTile.getFluid();
      if (barrelFluid != null && barrelFluid.isSame(doll.getSpawnFluid())) {
        if (!simulate) {
          barrelTile.setMode(ExNihiloConstants.BarrelModes.MOB);
          ((MobSpawnBarrelMode) barrelTile.getMode()).setDoll(doll);
        }
        returnStack.shrink(1);
      }
    }
    return returnStack;
  }

  private boolean checkFluidAmount(BarrelBlockEntity barrelEntity) {
    return barrelEntity.getFluidAmount() < FluidType.BUCKET_VOLUME;
  }
}