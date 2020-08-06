package com.novamachina.exnihilosequentia.common.json;

import com.novamachina.exnihilosequentia.common.tileentity.barrel.transform.FluidTransformRecipe;

public class FluidTransformJson {
    @JsonRequired
    private final String fluidInBarrel;
    @JsonRequired
    private final String blockBelow;
    @JsonRequired
    private final String result;

    public FluidTransformJson(String fluidInBarrel, String blockBelow, String result) {
        this.fluidInBarrel = fluidInBarrel;
        this.blockBelow = blockBelow;
        this.result = result;
    }

    public FluidTransformJson(FluidTransformRecipe recipe) {
        this.fluidInBarrel = recipe.getFluidInBarrel().toString();
        this.blockBelow = recipe.getBlockBelow().toString();
        this.result = recipe.getResult().toString();
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
