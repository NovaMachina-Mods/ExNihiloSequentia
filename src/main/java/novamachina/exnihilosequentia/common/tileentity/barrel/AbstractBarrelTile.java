package novamachina.exnihilosequentia.common.tileentity.barrel;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
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
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import novamachina.exnihilosequentia.common.tileentity.barrel.mode.AbstractBarrelMode;
import novamachina.exnihilosequentia.common.tileentity.barrel.mode.BarrelModeRegistry;
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

public abstract class AbstractBarrelTile extends BlockEntity {
    public static final int MAX_FLUID_AMOUNT = Config.getBarrelNumberOfBuckets() * FluidAttributes.BUCKET_VOLUME;
    public static final int MAX_SOLID_AMOUNT = Config.getBarrelMaxSolidAmount();
    @Nonnull
    private static final String INVENTORY_TAG = "inventory";
    @Nonnull
    private static final String MODE_INFO_TAG = "modeInfo";
    @Nonnull
    private static final String MODE_TAG = "barrelMode";
    @Nonnull
    private static final String SOLID_AMOUNT_TAG = "solidAmount";
    @Nonnull
    private static final String TANK_TAG = "tank";
    @Nonnull
    private final BarrelInventoryHandler inventory = new BarrelInventoryHandler(this);
    @Nonnull
    private final LazyOptional<IItemHandler> inventoryHolder = LazyOptional.of(() -> inventory);
    @Nonnull
    private final BarrelFluidHandler tank = new BarrelFluidHandler(this);
    @Nonnull
    private final LazyOptional<IFluidHandler> tankHolder = LazyOptional.of(() -> tank);
    @Nullable
    private AbstractBarrelTileState lastSyncedState = null;
    @Nullable
    private AbstractBarrelMode mode;
    private int solidAmount = 0;

    protected AbstractBarrelTile(@Nonnull final BlockEntityType<? extends AbstractBarrelTile> tileEntityType, BlockPos pos, BlockState state) {
        super(tileEntityType, pos, state);
        mode = BarrelModeRegistry.getModeFromName(ExNihiloConstants.BarrelModes.EMPTY);
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

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull final Capability<T> cap, @Nullable final Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return inventoryHolder.cast();
        }
        if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return tankHolder.cast();
        }
        return super.getCapability(cap, side);
    }

    @Nullable
    public Fluid getFluid() {
        if (!tank.isEmpty()) {
            return tank.getFluid().getFluid();
        }
        return null;
    }

    public int getFluidAmount() {
        return tank.getFluidAmount();
    }

    public float getFluidProportion() {
        return (float) tank.getFluidAmount() / MAX_FLUID_AMOUNT;
    }

    @Nonnull
    public ItemStackHandler getInventory() {
        return inventory;
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

    @Nullable
    public ResourceLocation getSolidTexture() {
        if (!inventory.getStackInSlot(0).isEmpty()) {
            return inventory.getStackInSlot(0).getItem().getRegistryName();
        }
        return null;
    }

    @Nonnull
    public FluidTank getTank() {
        return tank;
    }

    @Nonnull
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
        //return new ClientboundBlockEntityDataPacket(getBlockPos(), -1, nbt);
    }

    @Nonnull
    @Override
    public CompoundTag getUpdateTag() {
        @Nonnull final CompoundTag nbt = new CompoundTag();
        this.saveAdditional(nbt);
        return nbt;
    }

    @Nullable
    public List<Component> getWailaInfo() {
        if (mode == null)
            return null;
        return mode.getWailaInfo(this);
    }

    @Override
    public void load(@Nonnull final CompoundTag compound) {
        if (compound.contains(INVENTORY_TAG)) {
            inventory.deserializeNBT(compound.getCompound(INVENTORY_TAG));
        }
        if (compound.contains(TANK_TAG)) {
            tank.readFromNBT(compound.getCompound(TANK_TAG));
        }
        if (compound.contains(MODE_TAG)) {
            this.setMode(BarrelModeRegistry.getModeFromName(compound.getString(MODE_TAG)));
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
    public InteractionResult onBlockActivated(@Nonnull final Player player, @Nonnull final InteractionHand handIn,
                                              @Nonnull final IFluidHandler fluidHandler,
                                              @Nonnull final IItemHandler itemHandler) {
        if (mode == null)
            return null;
        return mode.onBlockActivated(this, player, handIn, fluidHandler, itemHandler);
    }

    @Override
    public void onDataPacket(@Nonnull final Connection net, @Nonnull final ClientboundBlockEntityDataPacket pkt) {
        @Nonnull final CompoundTag nbt = pkt.getTag();
        if (nbt.contains(INVENTORY_TAG)) {
            inventory.deserializeNBT(nbt.getCompound(INVENTORY_TAG));
        }
        if (nbt.contains(TANK_TAG)) {
            tank.readFromNBT(nbt.getCompound(TANK_TAG));
        }
        this.setMode(BarrelModeRegistry.getModeFromName(nbt.getString(MODE_TAG)));
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
        compound.put(INVENTORY_TAG, inventory.serializeNBT());
        compound.put(TANK_TAG, tank.writeToNBT(new CompoundTag()));
        if (mode != null) {
            compound.putString(MODE_TAG, mode.getModeName());
            compound.put(MODE_INFO_TAG, mode.write());
        }
        compound.putInt(SOLID_AMOUNT_TAG, solidAmount);
        compound.putString("timestamp", String.valueOf(System.currentTimeMillis()));
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        @Nonnull final NonNullList<ItemStack> list = NonNullList.create();
        list.add(inventory.getStackInSlot(0));
        if (level != null) {
            Containers.dropContents(level, worldPosition, list);
        }
    }

    public void tickServer() {
        if (mode.isEmptyMode() || mode.getModeName().equals(ExNihiloConstants.BarrelModes.FLUID)) {
            @Nonnull final BlockPos abovePos = worldPosition.offset(0, 1, 0);
            if (level.isRainingAt(abovePos) && tank.getSpace() >= Config.getRainFillAmount()) {
                @Nonnull final FluidStack stack = new FluidStack(Fluids.WATER, Config.getRainFillAmount());
                tank.fill(stack, IFluidHandler.FluidAction.EXECUTE);
            }
        }
        mode.tick(this);
        @Nonnull final AbstractBarrelTileState currentState = new AbstractBarrelTileState(this);
        if (!currentState.equals(lastSyncedState)) {
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 2);
            lastSyncedState = currentState;
        }
    }

    static protected class AbstractBarrelTileState {
        @Nullable
        private final Fluid fluid;
        private final int fluidAmount;
        @Nonnull
        private final Item solid;
        private final int solidAmount;
        @Nonnull
        private final List<Component> wailaInfo;

        AbstractBarrelTileState(@Nonnull final AbstractBarrelTile abstractBarrelTile) {
            fluid = abstractBarrelTile.getFluid();
            fluidAmount = abstractBarrelTile.getFluidAmount();
            solid = abstractBarrelTile.inventory.getStackInSlot(0).getItem();
            solidAmount = abstractBarrelTile.getSolidAmount();
            wailaInfo = abstractBarrelTile.getWailaInfo();
        }

        @Override
        public boolean equals(@Nullable final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            AbstractBarrelTileState that = (AbstractBarrelTileState) o;
            return fluidAmount == that.fluidAmount
                    && solidAmount == that.solidAmount
                    && Objects.equals(fluid, that.fluid)
                    && Objects.equals(solid, that.solid)
                    && Objects.equals(wailaInfo, that.wailaInfo);
        }
    }
}
