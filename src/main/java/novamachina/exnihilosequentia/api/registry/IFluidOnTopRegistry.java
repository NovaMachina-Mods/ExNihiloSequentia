package novamachina.exnihilosequentia.api.registry;

import java.util.List;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import novamachina.exnihilosequentia.api.crafting.fluidontop.FluidOnTopRecipe;

public interface IFluidOnTopRegistry {
    void clearRecipes();

    List<FluidOnTopRecipe> getRecipeList();

    ItemStack getResult(Fluid fluidInTank, Fluid fluidOnTop);

    boolean isValidRecipe(Fluid fluidInTank, Fluid fluidOnTop);

    void setRecipes(List<FluidOnTopRecipe> recipes);
}
