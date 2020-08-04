package com.novamachina.exnihilosequentia.common.tileentity.barrel.fluid;

import net.minecraft.util.ResourceLocation;

public class FluidBlockTransformRecipe {
    private ResourceLocation fluid;
    private ResourceLocation input;
    private ResourceLocation result;

    public FluidBlockTransformRecipe(ResourceLocation fluid, ResourceLocation input, ResourceLocation result) {
        this.fluid = fluid;
        this.input = input;
        this.result = result;
    }

    public ResourceLocation getFluid() {
        return fluid;
    }

    public ResourceLocation getInput() {
        return input;
    }

    public ResourceLocation getResult() {
        return result;
    }
}
