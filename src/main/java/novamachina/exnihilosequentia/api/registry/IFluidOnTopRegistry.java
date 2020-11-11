package novamachina.exnihilosequentia.api.registry;

import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import novamachina.exnihilosequentia.api.crafting.fluidontop.FluidOnTopRecipe;

import java.util.List;

public interface IFluidOnTopRegistry {
    boolean isValidRecipe(Fluid fluidInTank, Fluid fluidOnTop);

    ItemStack getResult(Fluid fluidInTank, Fluid fluidOnTop);

    List<FluidOnTopRecipe> getRecipeList();

    void setRecipes(List<FluidOnTopRecipe> recipes);
}
