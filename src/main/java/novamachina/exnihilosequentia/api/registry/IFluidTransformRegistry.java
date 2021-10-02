package novamachina.exnihilosequentia.api.registry;

import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.material.Fluid;
import novamachina.exnihilosequentia.api.crafting.fluidtransform.FluidTransformRecipe;

import java.util.List;

public interface IFluidTransformRegistry {
    void clearRecipes();

    List<FluidTransformRecipe> getRecipeList();

    Fluid getResult(Fluid fluidInTank, ItemLike catalyst);

    boolean isValidRecipe(Fluid fluidInTank, ItemLike catalyst);

    void setRecipes(List<FluidTransformRecipe> recipes);
}
