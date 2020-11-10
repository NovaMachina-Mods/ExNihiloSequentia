package com.novamachina.exnihilosequentia.common.registries.crucible;

import com.novamachina.exnihilosequentia.common.api.crafting.crucible.CrucibleRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CrucibleRegistry {
    private final List<CrucibleRecipe> recipeList = new ArrayList<>();

    public List<CrucibleRecipe> getRecipeList() {
        return recipeList;
    }

    public void setRecipes(Map<ResourceLocation, CrucibleRecipe> recipes) {
        recipeList.addAll(recipes.values());
    }

    public CrucibleRecipe findRecipe(IItemProvider item) {
        return recipeList.stream().filter(recipe -> recipe.getInput().test(new ItemStack(item))).findFirst().get();
    }

    public boolean isMeltable(IItemProvider item, int level) {
        return recipeList.stream().anyMatch(recipe -> recipe.getInput().test(new ItemStack(item)) && recipe.getCrucibleType().getLevel() >= level);
    }
}
