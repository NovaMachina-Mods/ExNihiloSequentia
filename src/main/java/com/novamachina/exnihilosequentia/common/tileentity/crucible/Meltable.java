package com.novamachina.exnihilosequentia.common.tileentity.crucible;

import net.minecraft.fluid.Fluid;

public class Meltable {

    public static Meltable DEFAULT = new Meltable(0, null);

    private final int   amount;
    private final Fluid fluid;

    public Meltable(int amount, Fluid fluid) {
        this.amount = amount;
        this.fluid  = fluid;
    }

    public int getAmount() {
        return amount;
    }

    public Fluid getFluid() {
        return fluid;
    }
}
