package com.novamachina.exnihilosequentia.common.tileentity.barrel;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraftforge.fluids.capability.templates.FluidTank;

public class EmptyBarrelMode extends AbstractBarrelMode {
    public EmptyBarrelMode(String name) {
        super(name);
    }

    @Override
    public void tick(BarrelTile barrelTile) {
        String nextState = checkConditions(barrelTile.getInventory(), barrelTile.getTank());
        barrelTile.setMode(nextState);
    }

    private String checkConditions(BarrelItemHandler inventory, FluidTank tank) {
        if(!inventory.getStackInSlot(0).isEmpty()) {
            return "solids";
        }
        if(!tank.isEmpty()) {
            return "fluids";
        }
        return "empty";
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
    public ActionResultType onBlockActivated(BarrelTile barrelTile) {
        return ActionResultType.SUCCESS;
    }
}
