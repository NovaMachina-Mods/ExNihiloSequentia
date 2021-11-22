package novamachina.exnihilosequentia.api.registry;

import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import novamachina.exnihilosequentia.api.crafting.fluidontop.FluidOnTopRecipe;

import java.util.List;

public interface IFluidOnTopRegistry {
    void clearRecipes();

    List<FluidOnTopRecipe> getRecipeList();

    ItemStack getResult(Fluid fluidInTank, Fluid fluidOnTop);

    boolean isValidRecipe(Fluid fluidInTank, Fluid fluidOnTop);

    void setRecipes(List<FluidOnTopRecipe> recipes);
}
