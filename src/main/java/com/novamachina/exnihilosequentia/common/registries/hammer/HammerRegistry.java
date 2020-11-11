package com.novamachina.exnihilosequentia.common.registries.hammer;

import com.novamachina.exnihilosequentia.common.api.crafting.hammer.HammerRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HammerRegistry {

    public static List<HammerRecipe> recipeList = new ArrayList<>();

    public ItemStack getResult(ResourceLocation input) {
        return findRecipe(input).getRecipeOutput();
    }

    public boolean isHammerable(ResourceLocation blockID) {

        return findRecipe(blockID) != HammerRecipe.EMPTY;
    }

    private HammerRecipe findRecipe(ResourceLocation blockID) {
        for (HammerRecipe recipe : recipeList) {
            if (recipe.getInput().getItem().getRegistryName().equals(blockID)) {
                return recipe;
            }
        }
        return HammerRecipe.EMPTY;
    }

    public void setRecipes(Map<ResourceLocation, HammerRecipe> recipes) {
        recipeList.addAll(recipes.values());
    }

    public List<HammerRecipe> getRecipeList() {
        return recipeList;
    }
}
