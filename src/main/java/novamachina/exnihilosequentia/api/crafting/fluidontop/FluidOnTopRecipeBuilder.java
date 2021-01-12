package novamachina.exnihilosequentia.api.crafting.fluidontop;

import net.minecraft.fluid.Fluid;
import net.minecraft.util.IItemProvider;
import novamachina.exnihilosequentia.api.crafting.ExNihiloFinishedRecipe;

public class FluidOnTopRecipeBuilder extends ExNihiloFinishedRecipe<FluidOnTopRecipeBuilder> {
    private FluidOnTopRecipeBuilder() {
        super(FluidOnTopRecipe.getStaticSerializer().get());
    }

    public static FluidOnTopRecipeBuilder builder() {
        return new FluidOnTopRecipeBuilder();
    }

    public FluidOnTopRecipeBuilder fluidInTank(Fluid fluid) {
        return this.addFluid("fluidInTank", fluid);
    }

    public FluidOnTopRecipeBuilder fluidOnTop(Fluid fluid) {
        return this.addFluid("fluidOnTop", fluid);
    }

    public FluidOnTopRecipeBuilder result(IItemProvider result) {
        return this.addResult(result);
    }
}
