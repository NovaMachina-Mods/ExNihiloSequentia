package novamachina.exnihilosequentia.api.registry;

import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.IItemProvider;
import novamachina.exnihilosequentia.api.crafting.fluidtransform.FluidTransformRecipe;

import java.util.List;

public interface IFluidTransformRegistry {
    boolean isValidRecipe(Fluid fluidInTank, IItemProvider catalyst);

    Fluid getResult(Fluid fluidInTank, IItemProvider catalyst);

    List<FluidTransformRecipe> getRecipeList();

    void setRecipes(List<FluidTransformRecipe> recipes);

    void clearRecipes();
}
