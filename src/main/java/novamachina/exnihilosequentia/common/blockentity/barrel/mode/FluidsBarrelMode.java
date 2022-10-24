package novamachina.exnihilosequentia.common.blockentity.barrel.mode;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
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
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;
import novamachina.exnihilosequentia.common.blockentity.barrel.AbstractBarrelEntity;
import novamachina.exnihilosequentia.common.item.DollItem;
import novamachina.exnihilosequentia.common.registries.ExNihiloRegistries;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.utility.TankUtil;

public class FluidsBarrelMode extends AbstractBarrelMode {

  public FluidsBarrelMode(@Nonnull final String name) {
    super(name);
  }

  @Override
  public void tick(@Nonnull final AbstractBarrelEntity barrelTile) {
    if (barrelTile.getFluidAmount() <= 0) {
      barrelTile.setMode(BarrelModeRegistry.getModeFromName(ExNihiloConstants.BarrelModes.EMPTY));
    }
    if (barrelTile.getFluidAmount() >= AbstractBarrelEntity.MAX_FLUID_AMOUNT) {
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
  private boolean doMobSpawn(@Nonnull final AbstractBarrelEntity barrelTile,
      @Nonnull final Player player,
      @Nonnull final InteractionHand handIn) {
    @Nonnull final Item item = player.getItemInHand(handIn).getItem();

    if (item instanceof DollItem) {
      @Nonnull final DollItem doll = (DollItem) item;
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

  private boolean fluidTransform(@Nonnull final AbstractBarrelEntity barrelTile,
      @Nonnull final ItemLike catalyst) {
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

  private boolean fluidOnTop(AbstractBarrelEntity barrelTile) {
    @Nullable final Level world = barrelTile.getLevel();
    if (world == null) {
      return false;
    }

    @Nonnull final Fluid fluidOnTop = world.getFluidState(barrelTile.getBlockPos().offset(0, 1, 0))
        .getType();
    @Nonnull final Fluid fluidInTank = barrelTile.getTank().getFluid().getFluid();

    if (ExNihiloRegistries.FLUID_ON_TOP_REGISTRY.isValidRecipe(fluidInTank, fluidOnTop)) {
      barrelTile.getTank().setFluid(FluidStack.EMPTY);
      barrelTile.getInventory().setStackInSlot(0, ExNihiloRegistries.FLUID_ON_TOP_REGISTRY
          .getResult(fluidInTank, fluidOnTop));
      barrelTile.setMode(ExNihiloConstants.BarrelModes.BLOCK);
      return true;
    }
    return false;
  }

  @Override
  @Nonnull
  public InteractionResult onBlockActivated(@Nonnull final AbstractBarrelEntity barrelTile,
      @Nonnull final Player player, @Nonnull final InteractionHand handIn,
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

    if (checkFluidAmount(barrelTile)) {
      if (fluidBlockTransform(barrelTile, player, handIn)) {
        return InteractionResult.SUCCESS;
      }

      @Nonnull final ItemLike catalyst = player.getItemInHand(handIn).getItem();
      if (fluidTransform(barrelTile, catalyst)) {
        player.getItemInHand(handIn).shrink(1);
      }

      doMobSpawn(barrelTile, player, handIn);
    }

    return InteractionResult.SUCCESS;
  }

  private boolean fluidBlockTransform(@Nonnull final AbstractBarrelEntity barrelTile,
      @Nonnull final Player player, @Nonnull final InteractionHand handIn) {
    @Nonnull final Fluid fluid = barrelTile.getTank().getFluid().getFluid();
    @Nonnull final Item input = player.getItemInHand(handIn).getItem();
    if (ExNihiloRegistries.FLUID_BLOCK_REGISTRY.isValidRecipe(fluid, input)) {
      barrelTile.getTank().drain(FluidAttributes.BUCKET_VOLUME, IFluidHandler.FluidAction.EXECUTE);
      barrelTile.getInventory()
          .setStackInSlot(0,
              new ItemStack(ExNihiloRegistries.FLUID_BLOCK_REGISTRY.getResult(fluid, input)));
      if (!player.isCreative()) {
        player.getItemInHand(handIn).shrink(1);
      }
      barrelTile.setMode(ExNihiloConstants.BarrelModes.BLOCK);
      return true;
    }
    return false;
  }

  private boolean checkFluidAmount(AbstractBarrelEntity barrelEntity) {
    return barrelEntity.getFluidAmount() < AbstractBarrelEntity.MAX_FLUID_AMOUNT;
  }

  @Override
  public boolean canFillWithFluid(@Nonnull final AbstractBarrelEntity barrel) {
    return true;
  }

  @Override
  public boolean isEmptyMode() {
    return false;
  }

  @Override
  protected boolean isTriggerItem(@Nonnull final ItemStack stack) {
    return FluidUtil.getFluidContained(stack).map(FluidStack::getAmount).orElse(0) >= FluidAttributes.BUCKET_VOLUME ||
        ItemStack.isSame(stack, PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WATER));
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
  protected void spawnParticle(@Nonnull final AbstractBarrelEntity barrelTile) {
    // NOOP
  }

  @Override
  @Nonnull
  public List<Component> getWailaInfo(@Nonnull final AbstractBarrelEntity barrelTile) {
    @Nonnull final List<Component> info = new ArrayList<>();

    @Nonnull final String key;
    if (barrelTile.getFluid() == null) {
      key = Fluids.EMPTY.defaultFluidState().createLegacyBlock().getBlock().getDescriptionId();
    } else {
      key = barrelTile.getFluid().defaultFluidState().createLegacyBlock().getBlock()
          .getDescriptionId();
    }

    info.add(new TranslatableComponent("waila.barrel.fluidAmount", new TranslatableComponent(key),
        barrelTile.getFluidAmount()));

    return info;
  }

  @Override
  @Nonnull
  public ItemStack handleInsert(@Nonnull final AbstractBarrelEntity barrelTile,
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
        barrelTile.getTank()
            .drain(FluidAttributes.BUCKET_VOLUME, IFluidHandler.FluidAction.EXECUTE);
        barrelTile.getInventory()
            .setStackInSlot(0,
                new ItemStack(ExNihiloRegistries.FLUID_BLOCK_REGISTRY.getResult(fluid, input)));
        barrelTile.setMode(ExNihiloConstants.BarrelModes.BLOCK);
      }
      returnStack.shrink(1);
      return returnStack;
    }

    if (input instanceof DollItem) {
      @Nonnull final DollItem doll = (DollItem) input;
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
}
