package com.novamachina.exnihilosequentia.common.tileentity.barrel.transform;

public class FluidTransformRecipe {
    private String fluidInBarrel;
    private String blockBelow;
    private String result;

    public FluidTransformRecipe(String fluidInBarrel, String blockBelow, String result) {
        this.fluidInBarrel = fluidInBarrel;
        this.blockBelow = blockBelow;
        this.result = result;
    }

    public String getFluidInBarrel() {
        return fluidInBarrel;
    }

    public String getBlockBelow() {
        return blockBelow;
    }

    public String getResult() {
        return result;
    }
}
