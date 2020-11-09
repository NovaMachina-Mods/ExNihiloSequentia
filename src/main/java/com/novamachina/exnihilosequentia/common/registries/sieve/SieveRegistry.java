package com.novamachina.exnihilosequentia.common.registries.sieve;

import com.novamachina.exnihilosequentia.common.api.crafting.sieve.SieveRecipe;
import com.novamachina.exnihilosequentia.common.compat.jei.sieve.JEISieveRecipe;
import com.novamachina.exnihilosequentia.common.item.mesh.EnumMesh;
import com.novamachina.exnihilosequentia.common.utility.Config;
import com.novamachina.exnihilosequentia.common.utility.IngredientUtils;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class SieveRegistry {
    private final boolean flattenRecipes = Config.FLATTEN_SIEVE_RECIPES.get();

    private List<SieveRecipe> recipeList = new ArrayList<>();

    private List<SieveRecipe> getDrops(Ingredient input, EnumMesh meshType, boolean isWaterlogged) {
        return recipeList.parallelStream()
            .filter(sieveRecipe -> sieveRecipe.isWaterlogged() == isWaterlogged)
            .filter(sieveRecipe -> IngredientUtils.areIngredientsEqual(sieveRecipe.getInput(), input))
            .map(recipe -> recipe.filterByMesh(meshType, flattenRecipes))
            .filter(recipe -> recipe.getMeshWithChances().size() > 0)
            .collect(Collectors.toList());
    }

    public List<SieveRecipe> getDrops(IItemProvider input, EnumMesh meshType, boolean isWaterlogged) {
        return recipeList.parallelStream()
            .filter(sieveRecipe -> sieveRecipe.isWaterlogged() == isWaterlogged)
            .filter(sieveRecipe -> sieveRecipe.getInput().test(new ItemStack(input)))
            .map(recipe -> recipe.filterByMesh(meshType, flattenRecipes))
            .filter(recipe -> recipe.getMeshWithChances().size() > 0)
            .collect(Collectors.toList());
    }

    public boolean isBlockSiftable(Block block, EnumMesh mesh, boolean isWaterlogged) {
        return recipeList.parallelStream().anyMatch(sieveRecipe -> sieveRecipe.getInput().test(new ItemStack(block)));
    }

    public List<JEISieveRecipe> getDryRecipeList() {
        List<JEISieveRecipe> returnList = new ArrayList<>();

        Set<Ingredient> inputs = new HashSet<>();
        for(SieveRecipe recipe : recipeList) {
            boolean insert = true;
            for(Ingredient ingredient : inputs) {
                if(IngredientUtils.areIngredientsEqual(ingredient, recipe.getInput())) {
                    insert = false;
                    break;
                }
            }
            if(insert) {
                inputs.add(recipe.getInput());
            }
        }

        for(EnumMesh mesh : EnumMesh.values()) {
            if(mesh != EnumMesh.NONE) {
                for(Ingredient ingredient : inputs) {
                    List<SieveRecipe> drops = getDrops(ingredient, mesh, false);
                    if(drops.size() > 0) {
                        List<List<ItemStack>> inputList = new ArrayList<>();
                        inputList.add(Collections.singletonList(new ItemStack(mesh.getRegistryObject().get())));
                        inputList.add(Arrays.asList(ingredient.getMatchingStacks()));
                        returnList.add(new JEISieveRecipe(inputList, drops));
                    }
                }
            }
        }

        return returnList;
    }

    public List<JEISieveRecipe> getWetRecipeList() {
        List<JEISieveRecipe> returnList = new ArrayList<>();

        Set<Ingredient> inputs = new HashSet<>();
        for(SieveRecipe recipe : recipeList) {
            boolean insert = true;
            for(Ingredient ingredient : inputs) {
                if(IngredientUtils.areIngredientsEqual(ingredient, recipe.getInput())) {
                    insert = false;
                    break;
                }
            }
            if(insert) {
                inputs.add(recipe.getInput());
            }
        }

        for(EnumMesh mesh : EnumMesh.values()) {
            if(mesh != EnumMesh.NONE) {
                for(Ingredient ingredient : inputs) {
                    List<SieveRecipe> drops = getDrops(ingredient, mesh, true);
                    if(drops.size() > 0) {
                        List<List<ItemStack>> inputList = new ArrayList<>();
                        inputList.add(Collections.singletonList(new ItemStack(mesh.getRegistryObject().get())));
                        inputList.add(Arrays.asList(ingredient.getMatchingStacks()));
                        returnList.add(new JEISieveRecipe(inputList, drops));
                    }
                }
            }
        }

        return returnList;
    }

    public void setRecipes(Map<ResourceLocation,SieveRecipe> recipes) {
        recipeList.addAll(recipes.values());
    }
}
