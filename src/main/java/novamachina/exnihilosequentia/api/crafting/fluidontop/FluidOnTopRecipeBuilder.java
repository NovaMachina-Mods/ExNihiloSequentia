package novamachina.exnihilosequentia.api.crafting.fluidontop;

import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.ItemLike;
import novamachina.exnihilosequentia.api.crafting.ExNihiloFinishedRecipe;

import javax.annotation.Nonnull;

public class FluidOnTopRecipeBuilder extends ExNihiloFinishedRecipe<FluidOnTopRecipeBuilder> {
    private FluidOnTopRecipeBuilder() throws NullPointerException {
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
    public FluidOnTopRecipeBuilder result(@Nonnull final ItemLike result) {
        return this.addResult(result);
    }
}
