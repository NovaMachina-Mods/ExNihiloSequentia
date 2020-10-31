package com.novamachina.exnihilosequentia.common.crafting;

import com.novamachina.exnihilosequentia.common.api.ExNihiloRegistries;
import com.novamachina.exnihilosequentia.common.api.crafting.crook.CrookRecipe;
import com.novamachina.exnihilosequentia.common.api.crafting.hammer.HammerRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.resources.DataPackRegistries;
import net.minecraft.resources.IResourceManager;
import net.minecraft.resources.IResourceManagerReloadListener;
import net.minecraft.util.ResourceLocation;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RecipeReloadListener implements IResourceManagerReloadListener {
    private final DataPackRegistries dataPackRegistries;

    public RecipeReloadListener(DataPackRegistries dataPackRegistries) {
        this.dataPackRegistries = dataPackRegistries;
    }

    @Override
    public void onResourceManagerReload(IResourceManager resourceManager) {
        if (dataPackRegistries != null) {
            RecipeManager recipeManager = dataPackRegistries.getRecipeManager();
            buildRecipeLists(recipeManager);
        }
    }

    private void buildRecipeLists(RecipeManager recipeManager) {
        Collection<IRecipe<?>> recipes = recipeManager.getRecipes();
        if(recipes.size() == 0) {
            return;
        }

        ExNihiloRegistries.HAMMER_REGISTRY.setRecipes(filterRecipes(recipes, HammerRecipe.class, HammerRecipe.TYPE));
        ExNihiloRegistries.CROOK_REGISTRY.setRecipes(filterRecipes(recipes, CrookRecipe.class, CrookRecipe.TYPE));
    }

    private static <R extends IRecipe<?>> Map<ResourceLocation, R> filterRecipes(Collection<IRecipe<?>> recipes, Class<R> recipeClass, IRecipeType<R> recipeType)
    {
        return recipes.stream()
            .filter(iRecipe -> iRecipe.getType() == recipeType)
            .flatMap(Stream::of)
            .map(recipeClass::cast)
            .collect(Collectors.toMap(recipe -> recipe.getId(), recipe -> recipe));
    }
}
