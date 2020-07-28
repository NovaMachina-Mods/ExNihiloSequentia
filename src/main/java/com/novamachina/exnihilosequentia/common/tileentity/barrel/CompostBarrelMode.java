package com.novamachina.exnihilosequentia.common.tileentity.barrel;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.registries.ForgeRegistries;

public class CompostBarrelMode extends AbstractBarrelMode {
    private int currentProgress;

    public CompostBarrelMode(String name) {
        super(name);
        currentProgress = 0;
    }

    @Override
    public void tick(BarrelTile barrelTile) {
        if (barrelTile.getSolidAmount() >= BarrelTile.MAX_SOLID_AMOUNT && barrelTile.getInventory().getStackInSlot(0).isEmpty()) {
            currentProgress++;
            if (currentProgress >= 200) {
                currentProgress = 0;
                barrelTile.getInventory().setStackInSlot(0, new ItemStack(ForgeRegistries.BLOCKS.getValue(Blocks.DIRT.getRegistryName())));
                barrelTile.removeSolid(barrelTile.getSolidAmount());
                barrelTile.setMode("block");
            }
        }
    }

    @Override
    public ActionResultType onBlockActivated(BarrelTile barrelTile, PlayerEntity player, Hand handIn, IFluidHandler fluidHandler, IItemHandler itemHandler) {
        if (CompostRegistry.containsSolid(player.getHeldItem(handIn).getItem().getRegistryName().toString())) {
            if(barrelTile.addSolid(CompostRegistry
                .getSolidAmount(player.getHeldItem(handIn).getItem().getRegistryName().toString()))) {
                player.getHeldItem(handIn).shrink(1);
            }
        }

        return ActionResultType.SUCCESS;
    }

    @Override
    public boolean checkConditionsToSwitchToState(BarrelTile barrelTile) {
        return !barrelTile.getInventory().getStackInSlot(0).isEmpty();
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
        return CompostRegistry.containsSolid(stack.getItem().getRegistryName().toString());
    }

    @Override
    public void read(CompoundNBT nbt) {
        this.currentProgress = nbt.getInt("currentProgress");
    }

    @Override
    public CompoundNBT write() {
        CompoundNBT modeInfo = new CompoundNBT();
        modeInfo.putInt("currentProgress", currentProgress);
        return modeInfo;
    }
}
