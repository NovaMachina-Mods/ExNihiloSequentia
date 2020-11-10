package com.novamachina.exnihilosequentia.common.api.crafting.fluidtransform;

import com.novamachina.exnihilosequentia.common.api.crafting.ExNihiloFinishedRecipe;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;

public class FluidTransformRecipeBuilder extends ExNihiloFinishedRecipe<FluidTransformRecipeBuilder> {
    public FluidTransformRecipeBuilder() {
        super(FluidTransformRecipe.SERIALIZER.get());
    }

    public static FluidTransformRecipeBuilder builder() {
        return new FluidTransformRecipeBuilder();
    }

    public FluidTransformRecipeBuilder fluidInTank(Fluid fluid) {
        return this.addFluid("fluidInTank", fluid);
    }

    public FluidTransformRecipeBuilder blockBelow(Block block) {
        return this.addInput("blockBelow", block);
    }

    public FluidTransformRecipeBuilder result(Fluid fluid) {
        return this.addFluid("result", fluid);
    }
}
