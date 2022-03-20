package novamachina.exnihilosequentia.api.crafting.fluidtransform;

import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.item.crafting.Ingredient;
import novamachina.exnihilosequentia.api.crafting.ExNihiloFinishedRecipe;

import javax.annotation.Nonnull;

public class FluidTransformRecipeBuilder extends ExNihiloFinishedRecipe<FluidTransformRecipeBuilder> {
    public FluidTransformRecipeBuilder() throws NullPointerException {
        super(FluidTransformRecipe.getStaticSerializer().get());
    }

    @Nonnull
    public static FluidTransformRecipeBuilder builder() {
        return new FluidTransformRecipeBuilder();
    }

    @Nonnull
    public FluidTransformRecipeBuilder catalyst(@Nonnull final Ingredient catalyst) {
        return this.addInput("catalyst", catalyst);
    }

    @Nonnull
    public FluidTransformRecipeBuilder fluidInTank(@Nonnull final Fluid fluid) {
        return this.addFluid("fluidInTank", fluid);
    }

    @Nonnull
    public FluidTransformRecipeBuilder result(@Nonnull final Fluid fluid) {
        return this.addFluid("result", fluid);
    }
}
