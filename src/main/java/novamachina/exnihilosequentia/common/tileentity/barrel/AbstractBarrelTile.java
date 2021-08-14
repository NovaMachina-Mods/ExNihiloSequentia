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

public abstract class AbstractBarrelTile extends BlockEntity {
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

    protected AbstractBarrelTile(BlockEntityType<? extends AbstractBarrelTile> tileEntityType, BlockPos pos, BlockState state) {
        super(tileEntityType, pos, state);
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

    public void tick() {
        assert level != null;
        if (level.isClientSide()) {
            return;
        }

        if (mode.isEmptyMode() || mode.getModeName().equals(ExNihiloConstants.BarrelModes.FLUID)) {
            BlockPos abovePos = worldPosition.offset(0, 1, 0);
            if (level.isRainingAt(abovePos)) {
                FluidStack stack = new FluidStack(Fluids.WATER, Config.getRainFillAmount());
                tank.fill(stack, IFluidHandler.FluidAction.EXECUTE);
            }
        }
        mode.tick(this);
        level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 2);
    }

    @Nonnull
    @Override
    public CompoundTag save(CompoundTag compound) {
        compound.put(INVENTORY_TAG, inventory.serializeNBT());
        compound.put(TANK_TAG, tank.writeToNBT(new CompoundTag()));
        compound.putString(MODE_TAG, mode.getModeName());
        compound.put(MODE_INFO_TAG, mode.write());
        compound.putInt(SOLID_AMOUNT_TAG, solidAmount);
        return super.save(compound);
    }

    @Override
    public void load(CompoundTag compound) {
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
        super.load(compound);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        CompoundTag nbt = pkt.getTag();
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
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        CompoundTag nbt = new CompoundTag();
        nbt.put(INVENTORY_TAG, inventory.serializeNBT());
        nbt.put(TANK_TAG, tank.writeToNBT(new CompoundTag()));
        nbt.putString(MODE_TAG, mode.getModeName());
        nbt.put(MODE_INFO_TAG, mode.write());
        nbt.putInt(SOLID_AMOUNT_TAG, solidAmount);

        return new ClientboundBlockEntityDataPacket(getBlockPos(), -1, nbt);
    }

    public InteractionResult onBlockActivated(Player player, InteractionHand handIn, IFluidHandler fluidHandler, IItemHandler itemHandler) {
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
        assert level != null;
        Containers.dropContents(level, getBlockPos(), list);
    }

    public List<Component> getWailaInfo() {
        return mode.getWailaInfo(this);
    }

    public abstract boolean canAcceptFluidTemperature(FluidStack resource);
}
