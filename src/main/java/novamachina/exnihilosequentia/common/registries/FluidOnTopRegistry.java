package novamachina.exnihilosequentia.common.registries;

import novamachina.exnihilosequentia.api.crafting.fluidontop.FluidOnTopRecipe;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import novamachina.exnihilosequentia.api.registry.IFluidOnTopRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FluidOnTopRegistry implements IFluidOnTopRegistry {
    private List<FluidOnTopRecipe> recipeList = new ArrayList<>();

    @Override
    public boolean isValidRecipe(Fluid fluidInTank, Fluid fluidOnTop) {
        for(FluidOnTopRecipe recipe : recipeList) {
            if(recipe.validInputs(fluidInTank, fluidOnTop)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ItemStack getResult(Fluid fluidInTank, Fluid fluidOnTop) {
        for(FluidOnTopRecipe recipe : recipeList) {
            if(recipe.validInputs(fluidInTank, fluidOnTop)) {
                return recipe.getResult();
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    public List<FluidOnTopRecipe> getRecipeList() {
        return recipeList;
    }

    @Override
    public void setRecipes(List<FluidOnTopRecipe> recipes) {
        recipeList.addAll(recipes);
    }
}
