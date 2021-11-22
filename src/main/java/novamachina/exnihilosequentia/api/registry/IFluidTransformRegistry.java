package novamachina.exnihilosequentia.api.registry;

import net.minecraft.fluid.Fluid;
import net.minecraft.util.IItemProvider;
import novamachina.exnihilosequentia.api.crafting.fluidtransform.FluidTransformRecipe;

import java.util.List;

public interface IFluidTransformRegistry {
    void clearRecipes();

    List<FluidTransformRecipe> getRecipeList();

    Fluid getResult(Fluid fluidInTank, IItemProvider catalyst);

    boolean isValidRecipe(Fluid fluidInTank, IItemProvider catalyst);

    void setRecipes(List<FluidTransformRecipe> recipes);
}
