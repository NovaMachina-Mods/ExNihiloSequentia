package com.novamachina.exnihilosequentia.common.tileentity.barrel;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraftforge.fluids.capability.templates.FluidTank;

public abstract class AbstractBarrelMode {
    private String modeName;

    public AbstractBarrelMode(String name) {
        this.modeName = name;
    }

    public String getModeName() {
        return modeName;
    }

    public abstract void tick(BarrelItemHandler inventory, FluidTank tank);

    public abstract CompoundNBT write(CompoundNBT compound);

    public abstract void read(CompoundNBT compound);

    public abstract ActionResultType onBlockActivated(BarrelItemHandler inventory, FluidTank tank);
}
