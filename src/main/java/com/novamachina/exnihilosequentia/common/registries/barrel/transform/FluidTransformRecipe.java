package com.novamachina.exnihilosequentia.common.registries.barrel.transform;

import net.minecraft.util.ResourceLocation;

public class FluidTransformRecipe {
    private final ResourceLocation fluidInBarrel;
    private final ResourceLocation blockBelow;
    private final ResourceLocation result;

    public FluidTransformRecipe(ResourceLocation fluidInBarrel, ResourceLocation blockBelow, ResourceLocation result) {
        this.fluidInBarrel = fluidInBarrel;
        this.blockBelow = blockBelow;
        this.result = result;
    }

    public ResourceLocation getFluidInBarrel() {
        return fluidInBarrel;
    }

    public ResourceLocation getBlockBelow() {
        return blockBelow;
    }

    public ResourceLocation getResult() {
        return result;
    }
}
