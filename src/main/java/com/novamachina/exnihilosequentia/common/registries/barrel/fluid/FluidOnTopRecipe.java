package com.novamachina.exnihilosequentia.common.registries.barrel.fluid;

import net.minecraft.util.ResourceLocation;

public class FluidOnTopRecipe {
    private final ResourceLocation fluidInBarrel;
    private final ResourceLocation fluidOnTop;
    private final ResourceLocation result;

    public FluidOnTopRecipe(ResourceLocation fluidInBarrel, ResourceLocation fluidOnTop, ResourceLocation result) {
        this.fluidInBarrel = fluidInBarrel;
        this.fluidOnTop = fluidOnTop;
        this.result = result;
    }

    public ResourceLocation getFluidInBarrel() {
        return fluidInBarrel;
    }

    public ResourceLocation getFluidOnTop() {
        return fluidOnTop;
    }

    public ResourceLocation getResult() {
        return result;
    }
}
