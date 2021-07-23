package novamachina.exnihilosequentia.common.registries;

import com.google.common.collect.Lists;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import novamachina.exnihilosequentia.api.compat.jei.JEISieveRecipe;
import novamachina.exnihilosequentia.api.crafting.sieve.MeshWithChance;
import novamachina.exnihilosequentia.api.crafting.sieve.SieveRecipe;
import novamachina.exnihilosequentia.api.registry.ISieveRegistry;
import novamachina.exnihilosequentia.common.item.mesh.EnumMesh;
import novamachina.exnihilosequentia.common.item.ore.OreItem;
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import novamachina.exnihilosequentia.common.utility.IngredientUtils;
import org.apache.logging.log4j.LogManager;

import java.util.*;
import java.util.stream.Collectors;

public class SieveRegistry implements ISieveRegistry {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    private final boolean flattenRecipes = Config.flattenSieveRecipes();

    private List<SieveRecipe> recipeList = new ArrayList<>();

    @Override
    public List<SieveRecipe> getDrops(ItemLike input, EnumMesh meshType, boolean isWaterlogged) {
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
            .filter(recipe -> !recipe.getRolls().isEmpty())
            .collect(Collectors.toList());
    }

    @Override
    public boolean isBlockSiftable(Block block, EnumMesh mesh, boolean isWaterlogged) {
        return recipeList.parallelStream().anyMatch(sieveRecipe ->{
            if(sieveRecipe.getInput().test(new ItemStack(block)) && sieveRecipe.isWaterlogged() == isWaterlogged) {
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
                    List<List<SieveRecipe>> dropLists = Lists.partition(drops, 21);
                    if(!drops.isEmpty()) {
                        List<List<ItemStack>> inputList = new ArrayList<>();
                        inputList.add(Collections.singletonList(new ItemStack(mesh.getRegistryObject().get())));
                        inputList.add(Arrays.asList(ingredient.getItems()));
                        for(List<SieveRecipe> dropList : dropLists) {
                            returnList.add(new JEISieveRecipe(inputList, dropList));
                        }
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
                    List<List<SieveRecipe>> dropLists = Lists.partition(drops, 21);
                    if(!drops.isEmpty()) {
                        List<List<ItemStack>> inputList = new ArrayList<>();
                        inputList.add(Collections.singletonList(new ItemStack(mesh.getRegistryObject().get())));
                        inputList.add(Arrays.asList(ingredient.getItems()));
                        for(List<SieveRecipe> dropList : dropLists) {
                            returnList.add(new JEISieveRecipe(inputList, dropList));
                        }
                    }
                }
            }
        }

        return returnList;
    }

    @Override
    public void setRecipes(List<SieveRecipe> recipes) {
        logger.debug("Sieve Registry recipes: " + recipes.size());
        recipeList.addAll(recipes);
    }

    @Override
    public void clearRecipes() {
        recipeList.clear();
    }
}
