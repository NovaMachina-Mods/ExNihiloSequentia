package novamachina.exnihilosequentia.api.registry;

import java.util.List;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluid;
import novamachina.exnihilosequentia.api.crafting.fluidtransform.FluidTransformRecipe;

public interface IFluidTransformRegistry {
    void clearRecipes();

    List<FluidTransformRecipe> getRecipeList();

    Fluid getResult(Fluid fluidInTank, Item catalyst);

    boolean isValidRecipe(Fluid fluidInTank, Item catalyst);

    void setRecipes(List<FluidTransformRecipe> recipes);
}
