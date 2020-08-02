package com.novamachina.exnihilosequentia.common.tileentity.barrel.transform;

import net.minecraft.util.ResourceLocation;

public class FluidTransformRecipe {
    private ResourceLocation fluidInBarrel;
    private ResourceLocation blockBelow;
    private ResourceLocation result;

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
