package com.novamachina.exnihilosequentia.common.tileentity.barrel;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;

public class EmptyBarrelMode extends AbstractBarrelMode {
    public EmptyBarrelMode(String name) {
        super(name);
    }

    @Override
    public void tick() {

    }

    @Override
    public CompoundNBT write() {
        return new CompoundNBT();
    }

    @Override
    public void read(CompoundNBT compound) {
        compound.get("modeInfo");
    }

    @Override
    public ActionResultType onBlockActivated() {
        return ActionResultType.SUCCESS;
    }
}
