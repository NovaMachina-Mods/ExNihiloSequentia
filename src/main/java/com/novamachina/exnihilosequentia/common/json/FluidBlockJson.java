package com.novamachina.exnihilosequentia.common.json;

import com.novamachina.exnihilosequentia.common.tileentity.barrel.fluid.FluidBlockTransformRecipe;

public class FluidBlockJson {
    @JsonRequired
    private final String fluid;
    @JsonRequired
    private final String input;
    @JsonRequired
    private final String result;


    public FluidBlockJson(String fluid, String input, String result) {
        this.fluid = fluid;
        this.input = input;
        this.result = result;
    }

    public FluidBlockJson(FluidBlockTransformRecipe recipe) {
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
