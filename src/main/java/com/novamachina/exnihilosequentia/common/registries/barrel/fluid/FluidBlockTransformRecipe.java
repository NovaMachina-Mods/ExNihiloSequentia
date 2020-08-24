package com.novamachina.exnihilosequentia.common.registries.barrel.fluid;

import net.minecraft.util.ResourceLocation;

public class FluidBlockTransformRecipe {
    private final ResourceLocation fluid;
    private final ResourceLocation input;
    private final ResourceLocation result;

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
