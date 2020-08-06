package com.novamachina.exnihilosequentia.common.json;

import com.novamachina.exnihilosequentia.common.tileentity.barrel.fluid.FluidOnTopRecipe;
import net.minecraft.util.ResourceLocation;

public class FluidOnTopJson {
    @JsonRequired
    private final String fluidInBarrel;
    @JsonRequired
    private final String fluidOnTop;
    @JsonRequired
    private final String result;

    public FluidOnTopJson(String fluidInBarrel, String fluidOnTop, String result) {
        this.fluidInBarrel = fluidInBarrel;
        this.fluidOnTop = fluidOnTop;
        this.result = result;
    }

    public FluidOnTopJson(FluidOnTopRecipe recipe) {
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
