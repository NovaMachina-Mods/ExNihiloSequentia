package com.novamachina.exnihilosequentia.common.tileentity.barrel;

import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;

public class BlockBarrelMode extends AbstractBarrelMode {
    public BlockBarrelMode(String name) {
        super(name);
    }

    @Override
    public void tick(BarrelTile barrelTile) {

    }

    @Override
    public ActionResultType onBlockActivated(BarrelTile barrelTile, PlayerEntity player, Hand handIn, IFluidHandler fluidHandler, IItemHandler itemHandler) {
        barrelTile.getWorld().addEntity(new ItemEntity(barrelTile.getWorld(), barrelTile.getPos().getX() + 0.5F, barrelTile.getPos().getY() + 0.5F,
            barrelTile.getPos().getZ() + 0.5F, new ItemStack(barrelTile.getInventory().getStackInSlot(0).getItem())));
        barrelTile.getInventory().setStackInSlot(0, ItemStack.EMPTY);
        barrelTile.setMode("empty");
        return ActionResultType.SUCCESS;
    }

    @Override
    public boolean checkConditionsToSwitchToState(BarrelTile barrelTile) {
        return false;
    }

    @Override
    public boolean canFillWithFluid(BarrelTile barrel) {
        return false;
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
