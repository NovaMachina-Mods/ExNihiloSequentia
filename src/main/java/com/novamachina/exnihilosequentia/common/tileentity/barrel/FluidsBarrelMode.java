package com.novamachina.exnihilosequentia.common.tileentity.barrel;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;

public class FluidsBarrelMode extends AbstractBarrelMode {
    public FluidsBarrelMode(String name) {
        super(name);
    }

    @Override
    public void tick(BarrelTile barrelTile) {
        if(barrelTile.getFluidAmount() >= FluidAttributes.BUCKET_VOLUME){
            if(fluidOnTop(barrelTile)) {
                return;
            }
        }
    }

    private boolean fluidOnTop(BarrelTile barrelTile) {
        Fluid fluidOnTop = barrelTile.getWorld().getFluidState(barrelTile.getPos().add(0, 1, 0)).getFluid();
        Fluid fluidInTank = barrelTile.getTank().getFluid().getFluid();

        if(FluidOnTopRegistry.isValidRecipe(fluidInTank, fluidOnTop)) {
            barrelTile.getTank().drain(FluidAttributes.BUCKET_VOLUME, IFluidHandler.FluidAction.EXECUTE);
            barrelTile.getInventory().setStackInSlot(0, new ItemStack(FluidOnTopRegistry.getResult(fluidInTank, fluidOnTop)));
            barrelTile.setMode("block");
            return true;
        }
        return false;
    }

    @Override
    public ActionResultType onBlockActivated(BarrelTile barrelTile, PlayerEntity player, Hand handIn, IFluidHandler fluidHandler, IItemHandler itemHandler) {
        ItemStack stack = player.getHeldItem(handIn);
        if (stack.isEmpty()) {
            return ActionResultType.SUCCESS;
        }

        boolean result = FluidUtil.interactWithFluidHandler(player, handIn, fluidHandler);

        if (result) {
            if (!player.isCreative()) {
                stack.shrink(1);
            }
            barrelTile.getWorld().notifyBlockUpdate(barrelTile.getPos(), barrelTile.getBlockState(), barrelTile.getBlockState(), 2);
            barrelTile.markDirty();
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public boolean canFillWithFluid(BarrelTile barrel) {
        return true;
    }

    @Override
    public boolean isEmptyMode() {
        return false;
    }

    @Override
    protected boolean isTriggerItem(ItemStack stack) {
        return false;
    }

    @Override
    public void read(CompoundNBT nbt) {

    }

    @Override
    public CompoundNBT write() {
        return new CompoundNBT();
    }
}
