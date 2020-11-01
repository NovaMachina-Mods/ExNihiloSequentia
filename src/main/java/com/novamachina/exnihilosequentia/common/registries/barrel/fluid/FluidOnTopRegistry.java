package com.novamachina.exnihilosequentia.common.registries.barrel.fluid;

import com.novamachina.exnihilosequentia.common.api.crafting.fluidontop.FluidOnTopRecipe;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FluidOnTopRegistry {
    private List<FluidOnTopRecipe> recipeList = new ArrayList<>();

    public boolean isValidRecipe(Fluid fluidInTank, Fluid fluidOnTop) {
        for(FluidOnTopRecipe recipe : recipeList) {
            if(recipe.validInputs(fluidInTank, fluidOnTop)) {
                return true;
            }
        }
        return false;
    }

    public ItemStack getResult(Fluid fluidInTank, Fluid fluidOnTop) {
        for(FluidOnTopRecipe recipe : recipeList) {
            if(recipe.validInputs(fluidInTank, fluidOnTop)) {
                return recipe.getResult();
            }
        }
        return ItemStack.EMPTY;
    }

    public List<FluidOnTopRecipe> getRecipeList() {
        return recipeList;
    }

    public void setRecipes(Map<ResourceLocation, FluidOnTopRecipe> recipes) {
        recipeList.addAll(recipes.values());
    }
}
