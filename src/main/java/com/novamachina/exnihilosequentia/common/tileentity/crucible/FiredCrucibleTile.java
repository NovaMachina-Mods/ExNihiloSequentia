package com.novamachina.exnihilosequentia.common.tileentity.crucible;

import com.novamachina.exnihilosequentia.common.setup.ModTiles;
import com.novamachina.exnihilosequentia.common.utility.LogUtil;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
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

    @Override
    public void tick() {
        if (world.isRemote()) {
            return;
        }

        inventory.setCrucibleHasRoom(tank.getFluidAmount() < 3000);
        ticksSinceLast++;

        if (ticksSinceLast >= 10) {
            ticksSinceLast = 0;

            LogUtil.info(String
                .format("Fluid: %s Amount: %d", tank.getFluid().getFluid().toString(),
                    tank.getFluid().getAmount()));
            LogUtil.info(String.format("Current Item: %s", currentItem.toString()));
            LogUtil
                .info(String.format("Inventory Item: %s", inventory.getStackInSlot(0).toString()));
            LogUtil.info(String.format("Solid Amount: %d", solidAmount));

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
                int        filled     = tank.fill(fluidStack, FluidAction.EXECUTE);
                solidAmount -= filled;
            }
        }
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
}
