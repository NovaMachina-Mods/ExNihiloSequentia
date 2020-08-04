package com.novamachina.exnihilosequentia.common.tileentity.barrel.fluid;

public class FluidBlockJSON {
    private final String fluid;
    private final String input;
    private final String result;


    public FluidBlockJSON(String fluid, String input, String result) {
        this.fluid = fluid;
        this.input = input;
        this.result = result;
    }

    public FluidBlockJSON(FluidBlockTransformRecipe recipe) {
        this.fluid = recipe.getFluid().toString();
        this.input = recipe.getInput().toString();
        this.result = recipe.getResult().toString();
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
