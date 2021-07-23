package novamachina.exnihilosequentia.api.crafting.fluidtransform;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.material.Fluid;
import novamachina.exnihilosequentia.api.crafting.ExNihiloFinishedRecipe;

public class FluidTransformRecipeBuilder extends ExNihiloFinishedRecipe<FluidTransformRecipeBuilder> {
    public FluidTransformRecipeBuilder() {
        super(FluidTransformRecipe.getStaticSerializer().get());
    }

    public static FluidTransformRecipeBuilder builder() {
        return new FluidTransformRecipeBuilder();
    }

    public FluidTransformRecipeBuilder catalyst(Ingredient catalyst) {
        return this.addInput("catalyst", catalyst);
    }

    public FluidTransformRecipeBuilder fluidInTank(Fluid fluid) {
        return this.addFluid("fluidInTank", fluid);
    }

    public FluidTransformRecipeBuilder result(Fluid fluid) {
        return this.addFluid("result", fluid);
    }
}
