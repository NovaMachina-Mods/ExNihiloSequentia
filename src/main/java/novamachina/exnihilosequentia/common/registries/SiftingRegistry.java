package novamachina.exnihilosequentia.common.registries;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import novamachina.exnihilosequentia.common.Config;
import novamachina.exnihilosequentia.common.compat.jei.sifting.JEISieveRecipe;
import novamachina.exnihilosequentia.world.item.MeshItem;
import novamachina.exnihilosequentia.world.item.MeshType;
import novamachina.exnihilosequentia.world.item.Ore;
import novamachina.exnihilosequentia.world.item.OreItem;
import novamachina.exnihilosequentia.world.item.crafting.SiftingRecipe;
import novamachina.novacore.util.IngredientUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SiftingRegistry {

  private static Logger log = LoggerFactory.getLogger(SiftingRegistry.class);

  private final boolean flattenRecipes = Config.flattenSieveRecipes();

  @Nonnull private final List<SiftingRecipe> recipeList = new ArrayList<>();

  private final LoadingCache<SiftingCacheKey, List<SiftingRecipe>> recipeCache;
  private final LoadingCache<SiftingCacheKey, Boolean> siftableCache;

  public SiftingRegistry() {
    CacheLoader<SiftingCacheKey, List<SiftingRecipe>> recipeCacheLoader =
        new CacheLoader<>() {
          @Override
          public List<SiftingRecipe> load(SiftingCacheKey key) {
            return recipeList.parallelStream()
                .filter(recipe -> recipe.isWaterlogged() == key.isWaterlogged())
                .filter(
                    recipe -> IngredientUtils.areIngredientsEqual(recipe.getInput(), key.input()))
                .map(recipe -> recipe.filterByMesh(key.meshType(), flattenRecipes))
                .filter(
                    recipe -> {
                      if (recipe.getDrop().getItem() instanceof OreItem) {
                        Ore ore = ((OreItem) recipe.getDrop().getItem()).getOre();
                        return ore.isEnabled();
                      }
                      return true;
                    })
                .filter(recipe -> !recipe.getRolls().isEmpty())
                .collect(Collectors.toList());
          }
        };
    recipeCache = CacheBuilder.newBuilder().maximumSize(100).build(recipeCacheLoader);

    CacheLoader<SiftingCacheKey, Boolean> siftableCacheLoader =
        new CacheLoader<>() {
          @Override
          public Boolean load(SiftingCacheKey key) {
            return recipeList.stream()
                .filter(recipe -> recipe.isWaterlogged() == key.isWaterlogged())
                .filter(recipe -> IngredientUtils.isIngredientIn(key.input(), recipe.getInput()))
                .anyMatch(
                    recipe ->
                        recipe.getRolls().stream()
                            .anyMatch(
                                roll -> {
                                  int level = roll.getMesh().getLevel();
                                  if (flattenRecipes) {
                                    return level <= key.meshType().getLevel();
                                  } else {
                                    return level == key.meshType().getLevel();
                                  }
                                }));
          }
        };
    siftableCache = CacheBuilder.newBuilder().maximumSize(100).build(siftableCacheLoader);
  }

  @Nonnull
  private List<SiftingRecipe> getDrops(
      @Nonnull final Ingredient input,
      @Nonnull final MeshType meshType,
      final boolean isWaterlogged) {
    try {
      return recipeCache.get(new SiftingCacheKey(input, meshType, isWaterlogged));
    } catch (ExecutionException e) {
      throw new RuntimeException(e);
    }
  }

  @Nonnull
  public List<SiftingRecipe> getDrops(
      @Nonnull final ItemLike input,
      @Nonnull final MeshType meshType,
      final boolean isWaterlogged) {
    try {
      return recipeCache.get(new SiftingCacheKey(Ingredient.of(input), meshType, isWaterlogged));
    } catch (ExecutionException e) {
      throw new RuntimeException(e);
    }
  }

  public boolean isBlockSiftable(
      @Nonnull final Block block, @Nonnull final MeshType mesh, final boolean isWaterlogged) {
    try {
      return siftableCache.get(new SiftingCacheKey(Ingredient.of(block), mesh, isWaterlogged));
    } catch (ExecutionException e) {
      throw new RuntimeException(e);
    }
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
                        final List<SiftingRecipe> drops =
                            getDrops(ingredient, enumMesh, isWaterLogged);
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

  public void setRecipes(@Nonnull final List<SiftingRecipe> recipes) {
    log.debug("Sieve Registry recipes: " + recipes.size());
    recipeList.addAll(recipes);

    recipeCache.invalidateAll();
    siftableCache.invalidateAll();
  }

  public void clearRecipes() {
    recipeList.clear();

    recipeCache.invalidateAll();
    siftableCache.invalidateAll();
  }

  private record SiftingCacheKey(Ingredient input, MeshType meshType, boolean isWaterlogged) {}
}
