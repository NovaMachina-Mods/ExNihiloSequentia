package novamachina.exnihilosequentia.common.tileentity.barrel;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
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

public abstract class AbstractBarrelTile extends TileEntity implements ITickableTileEntity {
    public static final int MAX_SOLID_AMOUNT = Config.getBarrelMaxSolidAmount();
    public static final int MAX_FLUID_AMOUNT = Config.getBarrelNumberOfBuckets() * FluidAttributes.BUCKET_VOLUME;
    private static final String INVENTORY_TAG = "inventory";
    private static final String MODE_TAG = "barrelMode";
    private static final String MODE_INFO_TAG = "modeInfo";
    private static final String SOLID_AMOUNT_TAG = "solidAmount";
    private static final String TANK_TAG = "tank";
    private BarrelInventoryHandler inventory;
    private final LazyOptional<IItemHandler> inventoryHolder = LazyOptional.of(() -> inventory);
    private BarrelFluidHandler tank;
    private final LazyOptional<IFluidHandler> tankHolder = LazyOptional.of(() -> tank);
    private AbstractBarrelMode mode;
    private int solidAmount;

    protected AbstractBarrelTile(TileEntityType<? extends AbstractBarrelTile> tileEntityType) {
        super(tileEntityType);
        this.mode = BarrelModeRegistry.getModeFromName(ExNihiloConstants.BarrelModes.EMPTY);
        inventory = new BarrelInventoryHandler(this);
        tank = new BarrelFluidHandler(this);
        solidAmount = 0;
    }

    public ItemStackHandler getInventory() {
        return inventory;
    }

    public FluidTank getTank() {
        return tank;
    }

    @Override
    public void tick() {
        if (level.isClientSide()) {
            return;
        }

        if (mode.isEmptyMode() || mode.getModeName().equals(ExNihiloConstants.BarrelModes.FLUID)) {
            BlockPos abovePos = worldPosition.offset(0, 1, 0);
            if (getLevel().isRainingAt(abovePos)) {
                FluidStack stack = new FluidStack(Fluids.WATER, Config.getRainFillAmount());
                tank.fill(stack, IFluidHandler.FluidAction.EXECUTE);
            }
        }
        mode.tick(this);
        getLevel().sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 2);
    }

    @Override
    public CompoundNBT save(CompoundNBT compound) {
        compound.put(INVENTORY_TAG, inventory.serializeNBT());
        compound.put(TANK_TAG, tank.writeToNBT(new CompoundNBT()));
        compound.putString(MODE_TAG, mode.getModeName());
        compound.put(MODE_INFO_TAG, mode.write());
        compound.putInt(SOLID_AMOUNT_TAG, solidAmount);
        return super.save(compound);
    }

    @Override
    public void load(BlockState state, CompoundNBT compound) {
        if (compound.contains(INVENTORY_TAG)) {
            inventory.deserializeNBT(compound.getCompound(INVENTORY_TAG));
        }
        if (compound.contains(TANK_TAG)) {
            tank.readFromNBT(compound.getCompound(TANK_TAG));
        }
        if (compound.contains(MODE_TAG)) {
            mode = BarrelModeRegistry.getModeFromName(compound.getString(MODE_TAG));
        }
        if (compound.contains(MODE_INFO_TAG)) {
            mode.read(compound.getCompound(MODE_INFO_TAG));
        }
        if (compound.contains(SOLID_AMOUNT_TAG)) {
            this.solidAmount = compound.getInt(SOLID_AMOUNT_TAG);
        }
        super.load(state, compound);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        CompoundNBT nbt = pkt.getTag();
        if (nbt.contains(INVENTORY_TAG)) {
            inventory.deserializeNBT(nbt.getCompound(INVENTORY_TAG));
        }
        if (nbt.contains(TANK_TAG)) {
            tank.readFromNBT(nbt.getCompound(TANK_TAG));
        }
        mode = BarrelModeRegistry.getModeFromName(nbt.getString(MODE_TAG));
        if (nbt.contains(MODE_INFO_TAG)) {
            mode.read(nbt.getCompound(MODE_INFO_TAG));
        }
        solidAmount = nbt.getInt(SOLID_AMOUNT_TAG);
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.put(INVENTORY_TAG, inventory.serializeNBT());
        nbt.put(TANK_TAG, tank.writeToNBT(new CompoundNBT()));
        nbt.putString(MODE_TAG, mode.getModeName());
        nbt.put(MODE_INFO_TAG, mode.write());
        nbt.putInt(SOLID_AMOUNT_TAG, solidAmount);

        return new SUpdateTileEntityPacket(getBlockPos(), -1, nbt);
    }

    public ActionResultType onBlockActivated(PlayerEntity player, Hand handIn, IFluidHandler fluidHandler, IItemHandler itemHandler) {
        return mode.onBlockActivated(this, player, handIn, fluidHandler, itemHandler);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return inventoryHolder.cast();
        }
        if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return tankHolder.cast();
        }
        return super.getCapability(cap, side);
    }

    public int getSolidAmount() {
        return this.solidAmount;
    }

    public int getFluidAmount() {
        return tank.getFluidAmount();
    }

    public boolean addSolid(int amount, boolean simulate) {
        if (amount <= 0) {
            return false;
        }
        if (solidAmount == MAX_SOLID_AMOUNT) {
            return false;
        }
        if(!simulate) {
            solidAmount += amount;
            if (solidAmount > MAX_SOLID_AMOUNT) {
                solidAmount = MAX_SOLID_AMOUNT;
            }
        }
        return true;
    }

    public void removeSolid(int amount) {
        solidAmount -= amount;
        if (solidAmount < 0) {
            solidAmount = 0;
        }
    }

    public AbstractBarrelMode getMode() {
        return mode;
    }

    public void setMode(String nextState) {
        mode = BarrelModeRegistry.getModeFromName(nextState);
        if (mode == null) {
            mode = BarrelModeRegistry.getModeFromName(ExNihiloConstants.BarrelModes.EMPTY);
        }
    }

    public void setMode(AbstractBarrelMode mode) {
        this.mode = mode;
    }

    public ResourceLocation getSolidTexture() {
        if (!inventory.getStackInSlot(0).isEmpty()) {
            return inventory.getStackInSlot(0).getItem().getRegistryName();
        }
        return null;
    }

    public Fluid getFluid() {
        if (!tank.isEmpty()) {
            return tank.getFluid().getFluid();
        }
        return null;
    }

    public float getSolidProportion() {
        return (float) solidAmount / MAX_SOLID_AMOUNT;
    }

    public float getFluidProportion() {
        return (float) tank.getFluidAmount() / MAX_FLUID_AMOUNT;
    }


    @Override
    public void setRemoved() {
        super.setRemoved();
        NonNullList<ItemStack> list = NonNullList.create();
        list.add(inventory.getStackInSlot(0));
        InventoryHelper.dropContents(getLevel(), getBlockPos(), list);
    }

    public List<ITextComponent> getWailaInfo() {
        return mode.getWailaInfo(this);
    }

    public abstract boolean canAcceptFluidTemperature(FluidStack resource);
}
