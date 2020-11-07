package com.novamachina.exnihilosequentia.common.registries.sieve;

import com.novamachina.exnihilosequentia.common.api.crafting.sieve.SieveRecipe;
import com.novamachina.exnihilosequentia.common.item.mesh.EnumMesh;
import com.novamachina.exnihilosequentia.common.utility.Config;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SieveRegistry {
    private final boolean flattenRecipes = Config.FLATTEN_SIEVE_RECIPES.get();

    private List<SieveRecipe> recipeList = new ArrayList<>();

    public List<SieveRecipe> getDrops(Block input, EnumMesh meshType, boolean isWaterlogged) {
        List<SieveRecipe> potentialDrops = recipeList.parallelStream()
            .filter(sieveRecipe -> sieveRecipe.isWaterlogged() == isWaterlogged)
            .filter(sieveRecipe -> sieveRecipe.getInput().test(new ItemStack(input)))
            .collect(Collectors.toList());

        List<SieveRecipe> potentialDropsTest = recipeList.parallelStream()
            .filter(sieveRecipe -> sieveRecipe.isWaterlogged() == isWaterlogged)
            .filter(sieveRecipe -> sieveRecipe.getInput().test(new ItemStack(input)))
            .map(recipe -> recipe.filterByMesh(meshType, isWaterlogged))
            .collect(Collectors.toList());

        List<SieveRecipe> returnList = new ArrayList<>();
        for(SieveRecipe recipe : potentialDrops) {
            returnList.add(recipe.filterByMesh(meshType, flattenRecipes));
        }

        return returnList;
    }

    public boolean isBlockSiftable(Block block, EnumMesh mesh, boolean isWaterlogged) {
        return recipeList.parallelStream().anyMatch(sieveRecipe -> sieveRecipe.getInput().test(new ItemStack(block)));
    }

    public List<SieveRecipe> getRecipeList() {
        return recipeList;
    }

    public void setRecipes(Map<ResourceLocation,SieveRecipe> recipes) {
        recipeList.addAll(recipes.values());
    }
}
