package com.novamachina.exnihilosequentia.common.tileentity.barrel.fluid;

import net.minecraft.util.ResourceLocation;

public class FluidOnTopJSON {
    private final String fluidInBarrel;
    private final String fluidOnTop;
    private final String result;

    public FluidOnTopJSON(String fluidInBarrel, String fluidOnTop, String result) {
        this.fluidInBarrel = fluidInBarrel;
        this.fluidOnTop = fluidOnTop;
        this.result = result;
    }

    public FluidOnTopJSON(FluidOnTopRecipe recipe) {
        this.fluidInBarrel = recipe.getFluidInBarrel().toString();
        this.fluidOnTop = recipe.getFluidOnTop().toString();
        this.result = recipe.getResult().toString();
    }

    public String getFluidInBarrel() {
        return fluidInBarrel;
    }

    public String getFluidOnTop() {
        return fluidOnTop;
    }

    public String getResult() {
        return result;
    }
}
