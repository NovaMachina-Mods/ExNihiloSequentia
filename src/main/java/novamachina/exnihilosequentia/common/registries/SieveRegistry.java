package novamachina.exnihilosequentia.common.registries;

import com.google.common.collect.Lists;
import com.mojang.logging.LogUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import novamachina.exnihilosequentia.common.compat.jei.sieve.JEISieveRecipe;
import novamachina.exnihilosequentia.common.crafting.sieve.SieveRecipe;
import novamachina.exnihilosequentia.common.item.MeshItem;
import novamachina.exnihilosequentia.common.item.OreItem;
import novamachina.exnihilosequentia.common.item.mesh.MeshType;
import novamachina.exnihilosequentia.common.item.ore.Ore;
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import novamachina.exnihilosequentia.common.utility.IngredientUtils;

public class SieveRegistry {

  @Nonnull private static final ExNihiloLogger logger = new ExNihiloLogger(LogUtils.getLogger());

  private final boolean flattenRecipes = Config.flattenSieveRecipes();

  @Nonnull private final List<SieveRecipe> recipeList = new ArrayList<>();

  @Nonnull
  private final Map<Boolean, Map<MeshType, Map<Block, Boolean>>> blockSiftableCache =
      new HashMap<>();

  @Nonnull
  private final Map<Boolean, Map<MeshType, Map<Item, List<SieveRecipe>>>> itemDropsListCache =
      new HashMap<>();

  @Nonnull
  private List<SieveRecipe> getDropsByIngredient(
      @Nonnull final Ingredient input,
      @Nonnull final MeshType meshType,
      final boolean isWaterlogged) {
    generateCache(input, meshType, isWaterlogged);
    return recipeList.parallelStream()
        .filter(sieveRecipe -> sieveRecipe.isWaterlogged() == isWaterlogged)
        .filter(sieveRecipe -> IngredientUtils.areIngredientsEqual(sieveRecipe.getInput(), input))
        .map(recipe -> recipe.filterByMesh(meshType, flattenRecipes))
        .filter(
            recipe -> {
              if (recipe.getDrop().getItem() instanceof OreItem) {
                @Nonnull final Ore ore = ((OreItem) recipe.getDrop().getItem()).getOre();
                return ore.isEnabled();
              }
              return true;
            })
        .filter(recipe -> !recipe.getRolls().isEmpty())
        .collect(Collectors.toList());
  }

  private void generateCache(
      @Nonnull final Ingredient input,
      @Nonnull final MeshType meshType,
      final boolean isWaterlogged) {
    Arrays.stream(input.getItems())
        .map(ItemStack::getItem)
        .forEach(item -> getDrops(item, meshType, isWaterlogged));
  }

  @Nonnull
  public List<SieveRecipe> getDrops(
      @Nonnull final ItemLike input,
      @Nonnull final MeshType meshType,
      final boolean isWaterlogged) {
    return itemDropsListCache
        .computeIfAbsent(isWaterlogged, k -> new HashMap<>())
        .computeIfAbsent(meshType, k -> new HashMap<>())
        .computeIfAbsent(
            input.asItem(),
            k -> {
              final ItemStack itemStack = new ItemStack(k);
              return recipeList.stream()
                  .filter(sieveRecipe -> sieveRecipe.isWaterlogged() == isWaterlogged)
                  .filter(sieveRecipe -> sieveRecipe.getInput().test(itemStack))
                  .map(recipe -> recipe.filterByMesh(meshType, flattenRecipes))
                  .filter(recipe -> !recipe.getRolls().isEmpty())
                  .filter(
                      recipe -> {
                        if (recipe.getDrop().getItem() instanceof OreItem) {
                          final Ore ore = ((OreItem) recipe.getDrop().getItem()).getOre();
                          return ore.isEnabled();
                        }
                        return true;
                      })
                  .collect(Collectors.toList());
            });
  }

  public boolean isBlockSiftable(
      @Nonnull final Block block, @Nonnull final MeshType mesh, final boolean isWaterlogged) {
    return blockSiftableCache
        .computeIfAbsent(isWaterlogged, k -> new HashMap<>())
        .computeIfAbsent(mesh, k -> new HashMap<>())
        .computeIfAbsent(
            block,
            k -> {
              final ItemStack itemStack = new ItemStack(block);
              final int meshlevel = mesh.getLevel();
              return recipeList.stream()
                  .filter(sieveRecipe -> sieveRecipe.isWaterlogged() == isWaterlogged)
                  .filter(sieveRecipe -> sieveRecipe.getInput().test(itemStack))
                  .anyMatch(
                      sieveRecipe ->
                          sieveRecipe.getRolls().stream()
                              .anyMatch(
                                  meshWithChance -> {
                                    final int meshWithChanceId =
                                        meshWithChance.getMesh().getLevel();
                                    if (flattenRecipes) {
                                      return meshWithChanceId <= meshlevel;
                                    } else {
                                      return meshWithChanceId == meshlevel;
                                    }
                                  }));
            });
  }

  @Nonnull
  private List<JEISieveRecipe> getRecipeList(final boolean isWaterLogged) {
    @Nonnull final Set<Ingredient> ingredients = new HashSet<>();
    recipeList.forEach(
        recipe -> {
          final Ingredient recipeIngredient = recipe.getInput();
          if (ingredients.stream()
              .noneMatch(
                  ingredient ->
                      IngredientUtils.areIngredientsEqual(ingredient, recipeIngredient))) {
            ingredients.add(recipeIngredient);
          }
        });

    return Arrays.stream(MeshType.values())
        .filter(enumMesh -> enumMesh != MeshType.NONE)
        .flatMap(
            enumMesh -> {
              final ItemStack mesh = new ItemStack(MeshItem.getMesh(enumMesh));
              return ingredients.stream()
                  .flatMap(
                      ingredient -> {
                        final List<SieveRecipe> drops =
                            getDropsByIngredient(ingredient, enumMesh, isWaterLogged);
                        if (drops.isEmpty()) {
                          return null;
                        }
                        final List<List<ItemStack>> input =
                            new ArrayList<>(
                                Arrays.asList(
                                    Collections.singletonList(mesh),
                                    Arrays.asList(ingredient.getItems())));
                        return Lists.partition(drops, 21).stream()
                            .map(results -> new JEISieveRecipe(input, results));
                      });
            })
        .collect(Collectors.toList());
  }

  @Nonnull
  public List<JEISieveRecipe> getDryRecipeList() {
    return getRecipeList(false);
  }

  @Nonnull
  public List<JEISieveRecipe> getWetRecipeList() {
    return getRecipeList(true);
  }

  public void setRecipes(@Nonnull final List<SieveRecipe> recipes) {
    logger.debug("Sieve Registry recipes: " + recipes.size());
    recipeList.addAll(recipes);

    blockSiftableCache.clear();
    itemDropsListCache.clear();
  }

  public void clearRecipes() {
    recipeList.clear();

    blockSiftableCache.clear();
    itemDropsListCache.clear();
  }
}
