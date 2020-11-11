package novamachina.exnihilosequentia.common.registries;

import novamachina.exnihilosequentia.api.crafting.sieve.MeshWithChance;
import novamachina.exnihilosequentia.api.crafting.sieve.SieveRecipe;
import novamachina.exnihilosequentia.api.registry.ISieveRegistry;
import novamachina.exnihilosequentia.api.compat.jei.JEISieveRecipe;
import novamachina.exnihilosequentia.common.item.mesh.EnumMesh;
import novamachina.exnihilosequentia.common.item.ore.OreItem;
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.common.utility.IngredientUtils;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SieveRegistry implements ISieveRegistry {
    private final boolean flattenRecipes = Config.FLATTEN_SIEVE_RECIPES.get();

    private List<SieveRecipe> recipeList = new ArrayList<>();

    private List<SieveRecipe> getDrops(Ingredient input, EnumMesh meshType, boolean isWaterlogged) {
        return recipeList.parallelStream()
            .filter(sieveRecipe -> sieveRecipe.isWaterlogged() == isWaterlogged)
            .filter(sieveRecipe -> IngredientUtils.areIngredientsEqual(sieveRecipe.getInput(), input))
            .map(recipe -> recipe.filterByMesh(meshType, flattenRecipes))
            .filter(recipe -> {
                if(recipe.getDrop().getItem() instanceof OreItem) {
                    OreItem ore = (OreItem)recipe.getDrop().getItem();
                    if(!ore.getOre().isEnabled()) {
                        return false;
                    }
                }
                return true;
            })
            .filter(recipe -> recipe.getRolls().size() > 0)
            .collect(Collectors.toList());
    }

    @Override
    public List<SieveRecipe> getDrops(IItemProvider input, EnumMesh meshType, boolean isWaterlogged) {
        return recipeList.parallelStream()
            .filter(sieveRecipe -> sieveRecipe.isWaterlogged() == isWaterlogged)
            .filter(sieveRecipe -> sieveRecipe.getInput().test(new ItemStack(input)))
            .map(recipe -> recipe.filterByMesh(meshType, flattenRecipes))
            .filter(recipe -> {
                if(recipe.getDrop().getItem() instanceof OreItem) {
                    OreItem ore = (OreItem)recipe.getDrop().getItem();
                    if(!ore.getOre().isEnabled()) {
                        return false;
                    }
                }
                return true;
            })
            .filter(recipe -> recipe.getRolls().size() > 0)
            .collect(Collectors.toList());
    }

    @Override
    public boolean isBlockSiftable(Block block, EnumMesh mesh, boolean isWaterlogged) {
        return recipeList.parallelStream().anyMatch(sieveRecipe ->{
            if(sieveRecipe.getInput().test(new ItemStack(block))) {
                if(sieveRecipe.isWaterlogged() == isWaterlogged) {
                    for(MeshWithChance meshWithChance : sieveRecipe.getRolls()) {
                        if(flattenRecipes) {
                            if(meshWithChance.getMesh().getId() <= mesh.getId()) {
                                return true;
                            }
                        } else {
                            if(meshWithChance.getMesh().getId() == mesh.getId()) {
                                return true;
                            }
                        }
                    }
                }
            }
            return false;
        });
    }

    @Override
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

    @Override
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

    @Override
    public void setRecipes(List<SieveRecipe> recipes) {
        recipeList.addAll(recipes);
    }
}
