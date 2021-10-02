package novamachina.exnihilosequentia.api.registry;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import novamachina.exnihilosequentia.api.crafting.fluidontop.FluidOnTopRecipe;

import java.util.List;

public interface IFluidOnTopRegistry {
    void clearRecipes();

    List<FluidOnTopRecipe> getRecipeList();

    ItemStack getResult(Fluid fluidInTank, Fluid fluidOnTop);

    boolean isValidRecipe(Fluid fluidInTank, Fluid fluidOnTop);

    void setRecipes(List<FluidOnTopRecipe> recipes);
}
