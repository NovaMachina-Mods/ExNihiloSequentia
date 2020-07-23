package com.novamachina.exnihilosequentia.common.tileentity.barrel;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;

public class SolidsBarrelMode extends AbstractBarrelMode{
    public SolidsBarrelMode(String name) {
        super(name);
    }

    @Override
    public void tick(BarrelTile barrelTile) {

    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        return null;
    }

    @Override
    public void read(CompoundNBT compound) {

    }

    @Override
    public ActionResultType onBlockActivated(BarrelTile barrelTile) {
        return null;
    }
}
