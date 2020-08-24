package com.novamachina.exnihilosequentia.common.tileentity.crucible;

import com.novamachina.exnihilosequentia.common.api.ExNihiloRegistries;
import com.novamachina.exnihilosequentia.common.registries.crucible.Meltable;
import com.novamachina.exnihilosequentia.common.utility.Config;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class BaseCrucibleTile extends TileEntity implements ITickableTileEntity {

    protected static int MAX_FLUID_AMOUNT = Config.CRUCIBLE_NUMBER_OF_BUCKETS.get() * FluidAttributes.BUCKET_VOLUME;
    protected MeltableItemHandler inventory;
    private final LazyOptional<IItemHandler> inventoryHolder = LazyOptional.of(() -> inventory);
    protected FluidTank tank;
    private final LazyOptional<IFluidHandler> tankHolder = LazyOptional.of(() -> tank);
    protected int ticksSinceLast;
    protected int solidAmount;
    protected ItemStack currentItem;

    public BaseCrucibleTile(
        TileEntityType<? extends BaseCrucibleTile> tileEntityType) {
        super(tileEntityType);
        inventory = new MeltableItemHandler(getCrucibleType());
        tank = new FluidTank(MAX_FLUID_AMOUNT);
        ticksSinceLast = 0;
        solidAmount = 0;
        currentItem = ItemStack.EMPTY;
    }

    @Override
    public void read(CompoundNBT compound) {
        inventory.deserializeNBT(compound.getCompound("inventory"));
        tank.readFromNBT(compound.getCompound("tank"));
        this.ticksSinceLast = compound.getInt("ticksSinceLast");
        this.solidAmount = compound.getInt("solidAmount");
        this.currentItem = ItemStack.read(compound.getCompound("currentItem"));
        super.read(compound);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("inventory", inventory.serializeNBT());
        compound.put("tank", tank.writeToNBT(new CompoundNBT()));
        compound.putInt("ticksSinceLast", ticksSinceLast);
        compound.putInt("solidAmount", solidAmount);
        compound.put("currentItem", currentItem.write(new CompoundNBT()));
        return super.write(compound);
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

    @Override
    public abstract void tick();

    protected abstract int getHeat();

    public ActionResultType onBlockActivated(PlayerEntity player, Hand handIn,
                                             IFluidHandler handler) {
        ItemStack stack = player.getHeldItem(handIn);
        if (stack.isEmpty()) {
            return ActionResultType.SUCCESS;
        }

        boolean result = FluidUtil.interactWithFluidHandler(player, handIn, handler);

        if (result) {
            if (!player.isCreative()) {
                stack.shrink(1);
            }
            world.notifyBlockUpdate(getPos(), getBlockState(), getBlockState(), 2);
            markDirty();
            return ActionResultType.SUCCESS;
        }

        if (!tank.isEmpty()) {
            if (!tank.getFluid().getFluid()
                .isEquivalentTo(getMeltable().getFluid())) {
                return ActionResultType.SUCCESS;
            }
        }

        ItemStack addStack = stack.copy();
        addStack.setCount(1);
        ItemStack insertStack = inventory.insertItem(0, addStack, true);
        if (!ItemStack.areItemStacksEqual(addStack, insertStack)) {
            inventory.insertItem(0, addStack, false);

            if (!player.isCreative()) {
                stack.shrink(1);
            }
            markDirty();
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.SUCCESS;
    }

    public ResourceLocation getSolidTexture() {
        if (!inventory.getStackInSlot(0).isEmpty()) {
            return inventory.getStackInSlot(0).getItem().getRegistryName();
        }
        if (!currentItem.isEmpty()) {
            return currentItem.getItem().getRegistryName();
        }
        return null;
    }

    public Fluid getFluid() {
        if (!tank.isEmpty()) {
            return tank.getFluid().getFluid();
        }
        return null;
    }

    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT nbt = new CompoundNBT();
        if (!inventory.getStackInSlot(0).isEmpty()) {
            CompoundNBT blockNbt = inventory.getStackInSlot(0).write(new CompoundNBT());
            nbt.put("block", blockNbt);
        }
        if (!currentItem.isEmpty()) {
            CompoundNBT currentItemTag = currentItem.write(new CompoundNBT());
            nbt.put("currentItem", currentItemTag);
        }
        if (!tank.isEmpty()) {
            CompoundNBT fluidNbt = tank.writeToNBT(new CompoundNBT());
            nbt.put("fluid", fluidNbt);
        }
        nbt.putInt("solidAmount", solidAmount);

        return new SUpdateTileEntityPacket(getPos(), -1, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket packet) {
        CompoundNBT nbt = packet.getNbtCompound();
        if (nbt.contains("currentItem")) {
            currentItem = ItemStack.read((CompoundNBT) nbt.get("currentItem"));
        } else {
            currentItem = ItemStack.EMPTY;
        }

        if (nbt.contains("block")) {
            inventory.setStackInSlot(0, ItemStack.read((CompoundNBT) nbt.get("block")));
        } else {
            inventory.setStackInSlot(0, ItemStack.EMPTY);
        }

        if (nbt.contains("fluid")) {
            tank.readFromNBT(nbt.getCompound("fluid"));
        } else {
            tank.setFluid(FluidStack.EMPTY);
        }
        solidAmount = nbt.getInt("solidAmount");
    }

    public float getFluidProportion() {
        return ((float) tank.getFluidAmount()) / tank.getCapacity();
    }

    public float getSolidProportion() {

        int itemCount =
            inventory.getStackInSlot(0).isEmpty() ? 0 : inventory.getStackInSlot(0).getCount();
        float solidProportion = ((float) itemCount) / 4;

        if (solidAmount > 0) {
            Meltable meltable = getMeltable();
            solidProportion += ((float) solidAmount) / (4 * meltable.getAmount());
        }
        return solidProportion;
    }

    public abstract CrucilbeTypeEnum getCrucibleType();

    private Meltable getMeltable() {
        return ExNihiloRegistries.CRUCIBLE_REGISTRY.getMeltable(currentItem.getItem());
    }

    public int getFluidAmount() {
        return tank.getFluidAmount();
    }

    public abstract int getSolidAmount();
}
