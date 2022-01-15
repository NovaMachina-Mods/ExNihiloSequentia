package novamachina.exnihilosequentia.common.registries;

import com.google.common.collect.Lists;
import net.minecraft.world.item.Item;
import novamachina.exnihilosequentia.api.crafting.sieve.SieveRecipe;
import novamachina.exnihilosequentia.api.registry.ISieveRegistry;
import novamachina.exnihilosequentia.api.compat.jei.JEISieveRecipe;
import novamachina.exnihilosequentia.common.item.mesh.EnumMesh;
import novamachina.exnihilosequentia.common.item.ore.OreItem;
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import novamachina.exnihilosequentia.common.utility.IngredientUtils;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import org.apache.logging.log4j.LogManager;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class SieveRegistry implements ISieveRegistry {
    @Nonnull private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    private final boolean flattenRecipes = Config.flattenSieveRecipes();

    @Nonnull private final List<SieveRecipe> recipeList = new ArrayList<>();

    @Nonnull private final Map<Boolean, Map<EnumMesh, Map<Block, Boolean>>> blockSiftableCache = new HashMap<>();
    @Nonnull private final Map<Boolean, Map<EnumMesh, Map<Item, List<SieveRecipe>>>> itemDropsListCache = new HashMap<>();

    @Nonnull
    private List<SieveRecipe> getDropsByIngredient(@Nonnull final Ingredient input, @Nonnull final EnumMesh meshType,
                                                   final boolean isWaterlogged) {
        generateCache(input, meshType, isWaterlogged);
        return recipeList.parallelStream()
                .filter(sieveRecipe -> sieveRecipe.isWaterlogged() == isWaterlogged)
                .filter(sieveRecipe -> IngredientUtils.areIngredientsEqual(sieveRecipe.getInput(), input))
                .map(recipe -> recipe.filterByMesh(meshType, flattenRecipes))
                .filter(recipe -> {
                    if(recipe.getDrop().getItem() instanceof OreItem) {
                        @Nonnull final OreItem ore = (OreItem)recipe.getDrop().getItem();
                        return ore.getOre().isEnabled();
                    }
                    return true;
                })
                .filter(recipe -> !recipe.getRolls().isEmpty())
                .collect(Collectors.toList());
    }

    private void generateCache(@Nonnull final Ingredient input, @Nonnull final EnumMesh meshType,
                               final boolean isWaterlogged) {
        Arrays.stream(input.getItems())
                .map(ItemStack::getItem)
                .forEach(item -> getDrops(item, meshType, isWaterlogged));
    }

    @Override
    @Nonnull
    public List<SieveRecipe> getDrops(@Nonnull final ItemLike input, @Nonnull final EnumMesh meshType,
                                      final boolean isWaterlogged) {
        return itemDropsListCache
                .computeIfAbsent(isWaterlogged, k -> new HashMap<>())
                .computeIfAbsent(meshType, k -> new HashMap<>())
                .computeIfAbsent(input.asItem(), k -> {
                    final ItemStack itemStack = new ItemStack(k);
                    return recipeList.stream()
                            .filter(sieveRecipe -> sieveRecipe.isWaterlogged() == isWaterlogged)
                            .filter(sieveRecipe -> sieveRecipe.getInput().test(itemStack))
                            .map(recipe -> recipe.filterByMesh(meshType, flattenRecipes))
                            .filter(recipe -> !recipe.getRolls().isEmpty())
                            .filter(recipe -> {
                                if (recipe.getDrop().getItem() instanceof OreItem) {
                                    final OreItem ore = (OreItem)recipe.getDrop().getItem();
                                    return ore.getOre().isEnabled();
                                }
                                return true;
                            })
                            .collect(Collectors.toList());
                });
    }

    @Override
    public boolean isBlockSiftable(@Nonnull final Block block, @Nonnull final EnumMesh mesh,
                                   final boolean isWaterlogged) {
        return blockSiftableCache
                .computeIfAbsent(isWaterlogged, k -> new HashMap<>())
                .computeIfAbsent(mesh, k -> new HashMap<>())
                .computeIfAbsent(block, k -> {
                    final ItemStack itemStack = new ItemStack(block);
                    final int meshId = mesh.getId();
                    return recipeList
                            .stream()
                            .filter(sieveRecipe -> sieveRecipe.isWaterlogged() == isWaterlogged)
                            .filter(sieveRecipe -> sieveRecipe.getInput().test(itemStack))
                            .anyMatch(sieveRecipe -> sieveRecipe
                                    .getRolls()
                                    .stream()
                                    .anyMatch(meshWithChance -> {
                                        final int meshWithChanceId = meshWithChance.getMesh().getId();
                                        if (flattenRecipes)
                                            return meshWithChanceId <= meshId;
                                        else
                                            return meshWithChanceId == meshId;
                                    }));
                });
    }

    @Nonnull
    private List<JEISieveRecipe> getRecipeList(final boolean isWaterLogged) {
        @Nonnull final Set<Ingredient> ingredients = new HashSet<>();
        recipeList
                .forEach(recipe -> {
                    final Ingredient recipeIngredient = recipe.getInput();
                    if (ingredients
                            .stream()
                            .noneMatch(ingredient ->
                                    IngredientUtils.areIngredientsEqual(ingredient, recipeIngredient))) {
                        ingredients.add(recipeIngredient);
                    }
                });

        return Arrays.stream(EnumMesh.values())
                .filter(enumMesh -> enumMesh != EnumMesh.NONE)
                .flatMap(enumMesh -> {
                    final ItemStack mesh = new ItemStack(enumMesh.getRegistryObject().get());
                    return ingredients
                            .stream()
                            .flatMap(ingredient -> {
                                final List<SieveRecipe> drops = getDropsByIngredient(ingredient, enumMesh, isWaterLogged);
                                if (drops.isEmpty())
                                    return null;
                                final List<List<ItemStack>> input = new ArrayList<>(Arrays.asList(
                                        Collections.singletonList(mesh),
                                        Arrays.asList(ingredient.getItems())
                                ));
                                return Lists
                                        .partition(drops, 21)
                                        .stream()
                                        .map(results -> new JEISieveRecipe(input, results));
                            });
                })
                .collect(Collectors.toList());
    }

    @Override
    @Nonnull
    public List<JEISieveRecipe> getDryRecipeList() {
        return getRecipeList(false);
    }

    @Override
    @Nonnull
    public List<JEISieveRecipe> getWetRecipeList() {
        return getRecipeList(true);
    }

    @Override
    public void setRecipes(@Nonnull final List<SieveRecipe> recipes) {
        logger.debug("Sieve Registry recipes: " + recipes.size());
        recipeList.addAll(recipes);

        blockSiftableCache.clear();
        itemDropsListCache.clear();
    }

    @Override
    public void clearRecipes() {
        recipeList.clear();

        blockSiftableCache.clear();
        itemDropsListCache.clear();
    }
}
