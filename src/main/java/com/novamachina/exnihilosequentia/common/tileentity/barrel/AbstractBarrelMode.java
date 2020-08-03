package com.novamachina.exnihilosequentia.common.tileentity.barrel;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;

public abstract class AbstractBarrelMode {
    private String modeName;

    public AbstractBarrelMode(String name) {
        this.modeName = name;
    }

    public String getModeName() {
        return modeName;
    }

    public abstract void tick(BarrelTile barrelTile);

    public abstract ActionResultType onBlockActivated(BarrelTile barrelTile, PlayerEntity player, Hand handIn, IFluidHandler fluidHandler, IItemHandler itemHandler);

    public abstract boolean canFillWithFluid(BarrelTile barrel);

    public abstract boolean isEmptyMode();

    protected abstract boolean isTriggerItem(ItemStack stack);

    public abstract void read(CompoundNBT nbt);

    public abstract CompoundNBT write();

    protected abstract void spawnParticle(BarrelTile barrelTile);
}
