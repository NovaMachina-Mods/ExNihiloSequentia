package novamachina.exnihilosequentia.world.level.block.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import novamachina.exnihilosequentia.common.Config;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.world.item.capability.BarrelInventoryHandler;
import novamachina.exnihilosequentia.world.level.block.entity.mode.AbstractBarrelMode;
import novamachina.exnihilosequentia.world.level.block.entity.mode.BarrelModeRegistry;
import novamachina.exnihilosequentia.world.level.material.capability.BarrelFluidHandler;

public abstract class BarrelBlockEntity extends BlockEntity {

  public static final int MAX_FLUID_AMOUNT =
      Config.getBarrelNumberOfBuckets() * FluidType.BUCKET_VOLUME;
  public static final int MAX_SOLID_AMOUNT = Config.getBarrelMaxSolidAmount();
  @Nonnull private static final String INVENTORY_TAG = "inventory";
  @Nonnull private static final String MODE_INFO_TAG = "modeInfo";
  @Nonnull private static final String MODE_TAG = "barrelMode";
  @Nonnull private static final String SOLID_AMOUNT_TAG = "solidAmount";
  @Nonnull private static final String TANK_TAG = "tank";
  @Nullable private AbstractBarrelTileState lastSyncedState = null;
  @Nullable private AbstractBarrelMode mode;
  private int solidAmount = 0;

  protected BarrelBlockEntity(
      @Nonnull final BlockEntityType<? extends BarrelBlockEntity> tileEntityType,
      BlockPos pos,
      BlockState state) {
    super(tileEntityType, pos, state);
    this.mode = BarrelModeRegistry.getModeFromName(ExNihiloConstants.BarrelModes.EMPTY);
  }

  public boolean addSolid(final int amount, final boolean simulate) {
    if (amount <= 0) {
      return false;
    }
    if (solidAmount == MAX_SOLID_AMOUNT) {
      return false;
    }
    if (!simulate) {
      solidAmount += amount;
      if (solidAmount > MAX_SOLID_AMOUNT) {
        solidAmount = MAX_SOLID_AMOUNT;
      }
    }
    return true;
  }

  public abstract boolean canAcceptFluidTemperature(@Nonnull final FluidStack resource);

  @Nullable
  public Fluid getFluid() {
    if (!BarrelFluidHandler.getHandler(this).isEmpty()) {
      return BarrelFluidHandler.getHandler(this).getFluid().getFluid();
    }
    return null;
  }

  public int getFluidAmount() {
    return BarrelFluidHandler.getHandler(this).getFluidAmount();
  }

  public float getFluidProportion() {
    return (float) BarrelFluidHandler.getHandler(this).getFluidAmount() / MAX_FLUID_AMOUNT;
  }

  @Nonnull
  public ItemStackHandler getInventory() {
    return BarrelInventoryHandler.getHandler(this);
  }

  @Nullable
  public AbstractBarrelMode getMode() {
    return mode;
  }

  public void setMode(@Nonnull final String nextState) {
    mode = BarrelModeRegistry.getModeFromName(nextState);
    if (mode == null) {
      mode = BarrelModeRegistry.getModeFromName(ExNihiloConstants.BarrelModes.EMPTY);
    }
  }

  public void setMode(@Nonnull final AbstractBarrelMode mode) {
    this.mode = mode;
  }

  public int getSolidAmount() {
    return this.solidAmount;
  }

  public float getSolidProportion() {
    return (float) solidAmount / MAX_SOLID_AMOUNT;
  }

  public ItemStack getInventoryBlock() {
    return BarrelInventoryHandler.getHandler(this).getStackInSlot(0);
  }

  @Nonnull
  public FluidTank getTank() {
    return BarrelFluidHandler.getHandler(this);
  }

  @Nonnull
  @Override
  public ClientboundBlockEntityDataPacket getUpdatePacket() {
    return ClientboundBlockEntityDataPacket.create(this);
  }

  @Override
  @Nonnull
  public CompoundTag getUpdateTag() {
    @Nonnull final CompoundTag nbt = new CompoundTag();
    this.saveAdditional(nbt);
    return nbt;
  }

  @Nullable
  public List<Component> getWailaInfo() {
    if (mode == null) {
      return new ArrayList<>();
    }
    return mode.getWailaInfo(this);
  }

  @Override
  public void load(@Nonnull final CompoundTag compound) {
    if (compound.contains(INVENTORY_TAG)) {
      BarrelInventoryHandler.getHandler(this).deserializeNBT(compound.getCompound(INVENTORY_TAG));
    }
    if (compound.contains(TANK_TAG)) {
      BarrelFluidHandler.getHandler(this).readFromNBT(compound.getCompound(TANK_TAG));
    }
    if (compound.contains(MODE_TAG)) {
      mode = BarrelModeRegistry.getModeFromName(compound.getString(MODE_TAG));
    }
    if (compound.contains(MODE_INFO_TAG) && mode != null) {
      mode.read(compound.getCompound(MODE_INFO_TAG));
    }
    if (compound.contains(SOLID_AMOUNT_TAG)) {
      this.solidAmount = compound.getInt(SOLID_AMOUNT_TAG);
    }
    super.load(compound);
  }

  @Nullable
  public InteractionResult onBlockActivated(
      @Nonnull final Player player,
      @Nonnull final InteractionHand handIn,
      @Nonnull final IFluidHandler fluidHandler,
      @Nonnull final IItemHandler itemHandler) {
    if (mode == null) {
      return null;
    }
    return mode.onBlockActivated(this, player, handIn, fluidHandler, itemHandler);
  }

  @Override
  public void onDataPacket(
      @Nonnull final Connection net, @Nonnull final ClientboundBlockEntityDataPacket pkt) {
    @Nonnull final CompoundTag nbt = pkt.getTag();
    if (nbt.contains(INVENTORY_TAG)) {
      BarrelInventoryHandler.getHandler(this).deserializeNBT(nbt.getCompound(INVENTORY_TAG));
    }
    if (nbt.contains(TANK_TAG)) {
      BarrelFluidHandler.getHandler(this).readFromNBT(nbt.getCompound(TANK_TAG));
    }
    mode = BarrelModeRegistry.getModeFromName(nbt.getString(MODE_TAG));
    if (nbt.contains(MODE_INFO_TAG) && mode != null) {
      mode.read(nbt.getCompound(MODE_INFO_TAG));
    }
    solidAmount = nbt.getInt(SOLID_AMOUNT_TAG);
  }

  public void removeSolid(final int amount) {
    solidAmount -= amount;
    if (solidAmount < 0) {
      solidAmount = 0;
    }
  }

  @Override
  public void saveAdditional(@Nonnull final CompoundTag compound) {
    super.saveAdditional(compound);
    compound.put(INVENTORY_TAG, BarrelInventoryHandler.getHandler(this).serializeNBT());
    compound.put(TANK_TAG, BarrelFluidHandler.getHandler(this).writeToNBT(new CompoundTag()));
    if (mode != null) {
      compound.putString(MODE_TAG, mode.getModeName());
      compound.put(MODE_INFO_TAG, mode.write());
    }
    compound.putInt(SOLID_AMOUNT_TAG, solidAmount);
  }

  public void dropInventory() {
    @Nonnull final NonNullList<ItemStack> list = NonNullList.create();
    list.add(BarrelInventoryHandler.getHandler(this).getStackInSlot(0));
    if (level != null) {
      Containers.dropContents(level, worldPosition, list);
    }
  }

  public void tickServer() {
    if (level == null || level.isClientSide() || mode == null) {
      return;
    }

    if (mode.isEmptyMode() || mode.getModeName().equals(ExNihiloConstants.BarrelModes.FLUID)) {
      @Nonnull final BlockPos abovePos = worldPosition.offset(0, 1, 0);
      if (level.isRainingAt(abovePos)
          && BarrelFluidHandler.getHandler(this).getSpace() >= Config.getRainFillAmount()) {
        @Nonnull final FluidStack stack = new FluidStack(Fluids.WATER, Config.getRainFillAmount());
        BarrelFluidHandler.getHandler(this).fill(stack, IFluidHandler.FluidAction.EXECUTE);
      }
    }
    mode.tick(this);
    @Nonnull final AbstractBarrelTileState currentState = new AbstractBarrelTileState(this);
    if (!currentState.equals(lastSyncedState)) {
      lastSyncedState = currentState;
      level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 2);
      this.setChanged();
    }
  }

  protected static class AbstractBarrelTileState {

    @Nullable private final Fluid fluid;
    private final int fluidAmount;
    @Nonnull private final Item solid;
    private final int solidAmount;
    @Nonnull private final List<Component> wailaInfo;

    AbstractBarrelTileState(@Nonnull final BarrelBlockEntity barrelBlockEntity) {
      fluid = barrelBlockEntity.getFluid();
      fluidAmount = barrelBlockEntity.getFluidAmount();
      solid = BarrelInventoryHandler.getHandler(barrelBlockEntity).getStackInSlot(0).getItem();
      solidAmount = barrelBlockEntity.getSolidAmount();
      wailaInfo = barrelBlockEntity.getWailaInfo();
    }

    @Override
    public boolean equals(@Nullable final Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      AbstractBarrelTileState that = (AbstractBarrelTileState) o;
      return fluidAmount == that.fluidAmount
          && solidAmount == that.solidAmount
          && Objects.equals(fluid, that.fluid)
          && Objects.equals(solid, that.solid)
          && Objects.equals(wailaInfo, that.wailaInfo);
    }

    @Override
    public int hashCode() {
      return Objects.hash(fluid, fluidAmount, solid, solidAmount, wailaInfo);
    }
  }
}
