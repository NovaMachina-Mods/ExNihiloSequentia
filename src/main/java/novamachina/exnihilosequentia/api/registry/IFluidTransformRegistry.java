package novamachina.exnihilosequentia.api.registry;

import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import novamachina.exnihilosequentia.api.crafting.fluidtransform.FluidTransformRecipe;

import java.util.List;

public interface IFluidTransformRegistry {
    boolean isValidRecipe(Fluid fluidInTank, Block blockBelow);

    Fluid getResult(Fluid fluidInTank, Block blockBelow);

    List<FluidTransformRecipe> getRecipeList();

    void setRecipes(List<FluidTransformRecipe> recipes);

    void clearRecipes();
}
