package com.novamachina.exnihilosequentia.common.tileentity.barrel;

public class FluidOnTopRecipe {
    private String fluidInBarrel;
    private String fluidOnTop;
    private String result;

    public FluidOnTopRecipe(String fluidInBarrel, String fluidOnTop, String result) {
        this.fluidInBarrel = fluidInBarrel;
        this.fluidOnTop = fluidOnTop;
        this.result = result;
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
