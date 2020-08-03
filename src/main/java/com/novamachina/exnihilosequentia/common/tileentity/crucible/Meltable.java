package com.novamachina.exnihilosequentia.common.tileentity.crucible;

import net.minecraft.fluid.Fluid;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public class Meltable {

    public static Meltable DEFAULT = new Meltable(0, null);

    private final int   amount;
    private final ResourceLocation fluid;

    public Meltable(int amount, ResourceLocation fluid) {
        this.amount = amount;
        this.fluid  = fluid;
    }

    public int getAmount() {
        return amount;
    }

    public Fluid getFluid() {
        return ForgeRegistries.FLUIDS.getValue(fluid);
    }
}
