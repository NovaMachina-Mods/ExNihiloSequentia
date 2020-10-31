package com.novamachina.exnihilosequentia.common.registries.crook;

import com.novamachina.exnihilosequentia.common.api.crafting.crook.CrookRecipe;
import com.novamachina.exnihilosequentia.common.api.crafting.hammer.HammerRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CrookRegistry {

    public List<CrookRecipe> recipeList = new ArrayList<>();

    public List<CrookRecipe> getDrops(IItemProvider block) {
        List<CrookRecipe> returnList = new ArrayList<>();
        for(CrookRecipe recipe : recipeList) {
            if(recipe.getInput().test(new ItemStack(block))) {
                returnList.add(recipe);
            }
        }
        return returnList;
    }

    public void setRecipes(Map<ResourceLocation, CrookRecipe> recipes) {
        for(CrookRecipe recipe : recipes.values()) {
            recipeList.add(recipe);
        }
    }

    public List<CrookRecipe> getRecipeList() {
        return recipeList;
    }
}