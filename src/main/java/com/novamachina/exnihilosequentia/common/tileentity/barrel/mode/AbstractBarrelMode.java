package com.novamachina.exnihilosequentia.common.tileentity.barrel.mode;

import com.novamachina.exnihilosequentia.common.tileentity.barrel.AbstractBarrelTile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;

import java.util.List;

public abstract class AbstractBarrelMode {
    private final String modeName;

    public AbstractBarrelMode(String name) {
        this.modeName = name;
    }

    public String getModeName() {
        return modeName;
    }

    public abstract void tick(AbstractBarrelTile barrelTile);

    public abstract ActionResultType onBlockActivated(AbstractBarrelTile barrelTile, PlayerEntity player, Hand handIn, IFluidHandler fluidHandler, IItemHandler itemHandler);

    public abstract boolean canFillWithFluid(AbstractBarrelTile barrel);

    public abstract boolean isEmptyMode();

    protected abstract boolean isTriggerItem(ItemStack stack);

    public abstract void read(CompoundNBT nbt);

    public abstract CompoundNBT write();

    protected abstract void spawnParticle(AbstractBarrelTile barrelTile);

    public abstract List<ITextComponent> getWailaInfo(AbstractBarrelTile barrelTile);
}
