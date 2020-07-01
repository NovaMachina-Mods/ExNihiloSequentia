package com.novamachina.exnihilosequentia.common.tileentity.crucible;

import com.novamachina.exnihilosequentia.common.setup.ModTiles;
import com.novamachina.exnihilosequentia.common.utility.LogUtil;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class FiredCrucibleTile extends TileEntity implements ITickableTileEntity {

    private       MeltableItemHandler         inventory;
    private final LazyOptional<IItemHandler>  inventoryHolder = LazyOptional.of(() -> inventory);
    private       FluidTank                   tank;
    private final LazyOptional<IFluidHandler> tankHolder      = LazyOptional.of(() -> tank);
    private       int                         ticksSinceLast;
    private       int                         solidAmount;
    private       ItemStack                   currentItem;

    public FiredCrucibleTile() {
        super(ModTiles.CRUCIBLE_FIRED.get());
        inventory      = new MeltableItemHandler();
        tank           = new FluidTank(4000);
        ticksSinceLast = 0;
        solidAmount    = 0;
        currentItem    = ItemStack.EMPTY;
    }

    @Override
    public void read(CompoundNBT compound) {
        inventory.deserializeNBT(compound.getCompound("inventory"));
        tank.readFromNBT(compound.getCompound("tank"));
        this.ticksSinceLast = compound.getInt("ticksSinceLast");
        this.solidAmount    = compound.getInt("solidAmount");
        this.currentItem    = ItemStack.read(compound.getCompound("currentItem"));
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

    // This is the same for the wooden one.
    @Override
    public void tick() {
        if (world.isRemote()) {
            return;
        }

        inventory.setCrucibleHasRoom(tank.getFluidAmount() < 4000);
        ticksSinceLast++;

        if (ticksSinceLast >= 10) {
            ticksSinceLast = 0;

            int heat = HeatRegistry.getHeatAmount(world.getBlockState(pos.down()).getBlock());
            if (heat <= 0) {
                return;
            }
            if (solidAmount <= 0) {
                if (!inventory.getStackInSlot(0).isEmpty()) {
                    currentItem = inventory.getStackInSlot(0).copy();
                    inventory.getStackInSlot(0).shrink(1);

                    if (inventory.getStackInSlot(0).isEmpty()) {
                        inventory.setStackInSlot(0, ItemStack.EMPTY);
                    }

                    solidAmount = MeltableItems.getMeltable(currentItem.getItem()).getAmount();
                } else {
                    return;
                }
            }

            if (!inventory.getStackInSlot(0).isEmpty() && inventory.getStackInSlot(0)
                .isItemEqual(currentItem)) {
                while (heat > solidAmount && !inventory.getStackInSlot(0).isEmpty()) {
                    solidAmount += MeltableItems.getMeltable(currentItem.getItem()).getAmount();
                    inventory.getStackInSlot(0).shrink(1);

                    if (inventory.getStackInSlot(0).isEmpty()) {
                        inventory.setStackInSlot(0, ItemStack.EMPTY);
                    }
                }
            }

            if (heat > solidAmount) {
                heat = solidAmount;
            }

            if (heat > 0 && MeltableItems.isMeltable(currentItem.getItem())) {
                FluidStack fluidStack = new FluidStack(
                    MeltableItems.getMeltable(currentItem.getItem()).getFluid(), heat);
                int filled = tank.fill(fluidStack, FluidAction.EXECUTE);
                solidAmount -= filled;
            }
            if (solidAmount <= 0 && inventory.getStackInSlot(0).isEmpty()) {
                currentItem = ItemStack.EMPTY;
            }
        }
        world.notifyBlockUpdate(getPos(), getBlockState(), getBlockState(), 2);
    }

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
        LogUtil.info(String.format("Inventory: %s", inventory.getStackInSlot(0).toString()));
        LogUtil.info(String.format("Current Item: %s", currentItem.toString()));

        int itemCount =
            inventory.getStackInSlot(0).isEmpty() ? 0 : inventory.getStackInSlot(0).getCount();
        float solidProportion = ((float) itemCount) / 4;

        if (solidAmount > 0) {
            Meltable meltable = MeltableItems.getMeltable(currentItem.getItem());
            solidProportion += ((float) solidAmount) / (4 * meltable.getAmount());
        }

        LogUtil.info(String.format("Solid Amount: %f", solidProportion));
        return solidProportion;
    }
}
