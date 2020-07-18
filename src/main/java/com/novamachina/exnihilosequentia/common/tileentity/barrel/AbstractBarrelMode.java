package com.novamachina.exnihilosequentia.common.tileentity.barrel;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;

public abstract class AbstractBarrelMode {
    private String modeName;

    public AbstractBarrelMode(String name) {
        this.modeName = name;
    }

    public String getModeName() {
        return modeName;
    }

    public abstract void tick();

    public abstract CompoundNBT write();

    public abstract void read(CompoundNBT compound);

    public abstract ActionResultType onBlockActivated();
}
