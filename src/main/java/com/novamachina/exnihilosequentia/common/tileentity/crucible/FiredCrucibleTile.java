package com.novamachina.exnihilosequentia.common.tileentity.crucible;

import com.novamachina.exnihilosequentia.common.setup.ModTiles;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;

public class FiredCrucibleTile extends BaseCrucibleTile {

    public FiredCrucibleTile() {
        super(ModTiles.CRUCIBLE_FIRED.get());
    }

    @Override
    protected int getHeat() {
        return HeatRegistry.getHeatAmount(world.getBlockState(pos.down()).getBlock());
    }

    @Override
    public CrucilbeTypeEnum getCrucibleType() {
        return CrucilbeTypeEnum.FIRED;
    }

    @Override
    public void tick() {
        if (world.isRemote()) {
            return;
        }

        inventory.setCrucibleHasRoom(tank.getFluidAmount() < 4000);
        ticksSinceLast++;

        if (ticksSinceLast >= 10) {
            ticksSinceLast = 0;

            int heat = getHeat();
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

                    solidAmount = FiredCrucibleMeltableItems.getMeltable(currentItem.getItem())
                        .getAmount();
                } else {
                    return;
                }
            }

            if (!inventory.getStackInSlot(0).isEmpty() && inventory.getStackInSlot(0)
                .isItemEqual(currentItem)) {
                while (heat > solidAmount && !inventory.getStackInSlot(0).isEmpty()) {
                    solidAmount += FiredCrucibleMeltableItems.getMeltable(currentItem.getItem())
                        .getAmount();
                    inventory.getStackInSlot(0).shrink(1);

                    if (inventory.getStackInSlot(0).isEmpty()) {
                        inventory.setStackInSlot(0, ItemStack.EMPTY);
                    }
                }
            }

            if (heat > solidAmount) {
                heat = solidAmount;
            }

            if (heat > 0 && FiredCrucibleMeltableItems.isMeltable(currentItem.getItem())) {
                FluidStack fluidStack = new FluidStack(
                    FiredCrucibleMeltableItems.getMeltable(currentItem.getItem()).getFluid(), heat);
                int filled = tank.fill(fluidStack, FluidAction.EXECUTE);
                solidAmount -= filled;
            }
        }
        world.notifyBlockUpdate(getPos(), getBlockState(), getBlockState(), 2);
    }
}
