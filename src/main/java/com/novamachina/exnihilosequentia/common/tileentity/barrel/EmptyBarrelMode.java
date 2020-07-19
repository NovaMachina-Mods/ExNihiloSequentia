package com.novamachina.exnihilosequentia.common.tileentity.barrel;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraftforge.fluids.capability.templates.FluidTank;

public class EmptyBarrelMode extends AbstractBarrelMode {
    public EmptyBarrelMode(String name) {
        super(name);
    }

    @Override
    public void tick(BarrelItemHandler inventory, FluidTank tank) {

    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        return compound;
    }

    @Override
    public void read(CompoundNBT compound) {
        compound.get("modeInfo");
    }

    @Override
    public ActionResultType onBlockActivated(BarrelItemHandler inventory, FluidTank tank) {
        return ActionResultType.SUCCESS;
    }
}
