package com.novamachina.exnihilosequentia.common.tileentity.barrel;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;

import java.util.List;
import java.util.function.Supplier;

public class EmptyBarrelMode extends AbstractBarrelMode {
    public EmptyBarrelMode(String name) {
        super(name);
    }

    @Override
    public void tick(BarrelTile barrelTile) {
    }

    @Override
    public ActionResultType onBlockActivated(BarrelTile barrelTile, PlayerEntity player, Hand handIn, IFluidHandler fluidHandler, IItemHandler itemHandler) {
        if(!player.getHeldItem(handIn).isEmpty()) {
            boolean result = FluidUtil.interactWithFluidHandler(player, handIn, fluidHandler);

            if (result) {
                if (!player.isCreative()) {
                    player.getHeldItem(handIn).shrink(1);
                }
                barrelTile.getWorld().notifyBlockUpdate(barrelTile.getPos(), barrelTile.getBlockState(), barrelTile.getBlockState(), 2);
                barrelTile.markDirty();
                return ActionResultType.SUCCESS;
            }

            ItemStack stack = player.getHeldItem(handIn);
            List<Supplier<AbstractBarrelMode>> modes = BarrelModeRegistry.getModes(BarrelModeRegistry.TriggerType.ITEM);
            for(Supplier<AbstractBarrelMode> mode : modes) {
                if(mode.get().isTriggerItem(stack)) {
                    barrelTile.setMode(mode.get());
                    barrelTile.getMode().onBlockActivated(barrelTile, player, handIn, fluidHandler, itemHandler);
                    return ActionResultType.SUCCESS;
                }
            }
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public boolean canFillWithFluid(BarrelTile barrel) {
        return true;
    }

    @Override
    public boolean isEmptyMode() {
        return true;
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
