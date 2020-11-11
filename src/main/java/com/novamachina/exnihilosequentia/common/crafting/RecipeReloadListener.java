package com.novamachina.exnihilosequentia.common.crafting;

import com.novamachina.exnihilosequentia.common.api.ExNihiloRegistries;
import com.novamachina.exnihilosequentia.common.api.crafting.compost.CompostRecipe;
import com.novamachina.exnihilosequentia.common.api.crafting.crook.CrookRecipe;
import com.novamachina.exnihilosequentia.common.api.crafting.crucible.CrucibleRecipe;
import com.novamachina.exnihilosequentia.common.api.crafting.fluidItem.FluidItemRecipe;
import com.novamachina.exnihilosequentia.common.api.crafting.fluidontop.FluidOnTopRecipe;
import com.novamachina.exnihilosequentia.common.api.crafting.fluidtransform.FluidTransformRecipe;
import com.novamachina.exnihilosequentia.common.api.crafting.hammer.HammerRecipe;
import com.novamachina.exnihilosequentia.common.api.crafting.heat.HeatRecipe;
import com.novamachina.exnihilosequentia.common.api.crafting.sieve.SieveRecipe;
import com.novamachina.exnihilosequentia.common.registries.defaults.ExNihilo;
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
        ExNihiloRegistries.COMPOST_REGISTRY.setRecipes(filterRecipes(recipes, CompostRecipe.class, CompostRecipe.TYPE));
        ExNihiloRegistries.FLUID_BLOCK_REGISTRY.setRecipes(filterRecipes(recipes, FluidItemRecipe.class, FluidItemRecipe.TYPE));
        ExNihiloRegistries.FLUID_ON_TOP_REGISTRY.setRecipes(filterRecipes(recipes, FluidOnTopRecipe.class, FluidOnTopRecipe.TYPE));
        ExNihiloRegistries.FLUID_TRANSFORM_REGISTRY.setRecipes(filterRecipes(recipes, FluidTransformRecipe.class, FluidTransformRecipe.TYPE));
        ExNihiloRegistries.CRUCIBLE_REGISTRY.setRecipes(filterRecipes(recipes, CrucibleRecipe.class, CrucibleRecipe.TYPE));
        ExNihiloRegistries.HEAT_REGISTRY.setRecipes(filterRecipes(recipes, HeatRecipe.class, HeatRecipe.TYPE));
        ExNihiloRegistries.SIEVE_REGISTRY.setRecipes(filterRecipes(recipes, SieveRecipe.class, SieveRecipe.TYPE));
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
