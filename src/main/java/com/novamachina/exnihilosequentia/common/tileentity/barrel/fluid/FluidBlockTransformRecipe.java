package com.novamachina.exnihilosequentia.common.tileentity.barrel.fluid;

public class FluidBlockTransformRecipe {
    private String fluid;
    private String input;
    private String result;

    public FluidBlockTransformRecipe(String fluid, String input, String result) {
        this.fluid = fluid;
        this.input = input;
        this.result = result;
    }

    public String getFluid() {
        return fluid;
    }

    public String getInput() {
        return input;
    }

    public String getResult() {
        return result;
    }
}
