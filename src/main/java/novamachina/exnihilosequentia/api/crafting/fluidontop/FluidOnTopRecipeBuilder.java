package novamachina.exnihilosequentia.api.crafting.fluidontop;

import net.minecraft.fluid.Fluid;
import net.minecraft.util.IItemProvider;
import novamachina.exnihilosequentia.api.crafting.ExNihiloFinishedRecipe;

import javax.annotation.Nonnull;

public class FluidOnTopRecipeBuilder extends ExNihiloFinishedRecipe<FluidOnTopRecipeBuilder> {
    private FluidOnTopRecipeBuilder() throws NullPointerException {
        //noinspection ConstantConditions
        super(FluidOnTopRecipe.getStaticSerializer().get());
    }

    @Nonnull
    public static FluidOnTopRecipeBuilder builder() {
        return new FluidOnTopRecipeBuilder();
    }

    @Nonnull
    public FluidOnTopRecipeBuilder fluidInTank(@Nonnull final Fluid fluid) {
        return this.addFluid("fluidInTank", fluid);
    }

    @Nonnull
    public FluidOnTopRecipeBuilder fluidOnTop(@Nonnull final Fluid fluid) {
        return this.addFluid("fluidOnTop", fluid);
    }

    @Nonnull
    public FluidOnTopRecipeBuilder result(@Nonnull final IItemProvider result) {
        return this.addResult(result);
    }
}
