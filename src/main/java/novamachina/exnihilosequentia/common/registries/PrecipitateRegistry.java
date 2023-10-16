package novamachina.exnihilosequentia.common.registries;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.annotation.Nonnull;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.material.Fluid;
import novamachina.exnihilosequentia.world.item.crafting.PrecipitateRecipe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrecipitateRegistry {

  private static Logger log = LoggerFactory.getLogger(PrecipitateRegistry.class);
  @Nonnull private final List<PrecipitateRecipe> recipeList = new ArrayList<>();

  @Nonnull private final Item empty = ItemStack.EMPTY.getItem();

  private final LoadingCache<PrecipitateCacheKey, Item> cache;

  public PrecipitateRegistry() {
    CacheLoader<PrecipitateCacheKey, Item> loader =
        new CacheLoader<>() {
          @Override
          public Item load(PrecipitateCacheKey key) {
            return recipeList.stream()
                .filter(fluidItemRecipe -> fluidItemRecipe.validInputs(key.fluid(), key.input()))
                .findFirst()
                .map(PrecipitateRecipe::getOutput)
                .map(ItemStack::getItem)
                .orElse(empty);
          }
        };
    cache = CacheBuilder.newBuilder().maximumSize(100).build(loader);
  }

  public boolean isValidRecipe(@Nonnull final Fluid fluid, @Nonnull final Item input) {
    return getResult(fluid, input) != empty;
  }

  @Nonnull
  public ItemLike getResult(@Nonnull final Fluid fluid, @Nonnull final Item input) {
    try {
      return cache.get(new PrecipitateCacheKey(fluid, input));
    } catch (ExecutionException e) {
      throw new RuntimeException(e);
    }
  }

  @Nonnull
  public List<PrecipitateRecipe> getRecipeList() {
    return recipeList;
  }

  public void setRecipes(@Nonnull final List<PrecipitateRecipe> recipes) {
    log.debug("Fluid Item Transform Registry recipes: " + recipes.size());
    recipeList.addAll(recipes);

    cache.invalidateAll();
  }

  public void clearRecipes() {
    recipeList.clear();

    cache.invalidateAll();
  }

  private record PrecipitateCacheKey(Fluid fluid, ItemLike input) {}
}
