package novamachina.exnihilosequentia.common.registries;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.annotation.Nonnull;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import novamachina.exnihilosequentia.world.item.crafting.SolidifyingRecipe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SolidifyingRegistry {

  private static Logger log = LoggerFactory.getLogger(SolidifyingRegistry.class);

  @Nonnull private final List<SolidifyingRecipe> recipeList = new ArrayList<>();
  private final LoadingCache<SolidifyingCacheKey, ItemStack> cache;

  public SolidifyingRegistry() {
    CacheLoader<SolidifyingCacheKey, ItemStack> loader =
        new CacheLoader<>() {
          @Override
          public ItemStack load(SolidifyingCacheKey key) {
            return recipeList.stream()
                .filter(
                    fluidOnTopRecipe ->
                        fluidOnTopRecipe.validInputs(key.fluidInTank(), key.fluidOnTop()))
                .findFirst()
                .map(SolidifyingRecipe::getResult)
                .orElse(ItemStack.EMPTY);
          }
        };
    cache = CacheBuilder.newBuilder().maximumSize(100).build(loader);
  }

  public boolean isValidRecipe(@Nonnull final Fluid fluidInTank, @Nonnull final Fluid fluidOnTop) {
    return getResultItem(fluidInTank, fluidOnTop) != ItemStack.EMPTY;
  }

  @Nonnull
  private ItemStack getResultItem(
      @Nonnull final Fluid fluidInTank, @Nonnull final Fluid fluidOnTop) {
    try {
      return cache.get(new SolidifyingCacheKey(fluidInTank, fluidOnTop));
    } catch (ExecutionException e) {
      throw new RuntimeException(e);
    }
  }

  @Nonnull
  public ItemStack getResult(@Nonnull final Fluid fluidInTank, @Nonnull final Fluid fluidOnTop) {
    return getResultItem(fluidInTank, fluidOnTop);
  }

  @Nonnull
  public List<SolidifyingRecipe> getRecipeList() {
    return recipeList;
  }

  public void setRecipes(@Nonnull final List<SolidifyingRecipe> recipes) {
    log.debug("Fluid On Top Registry recipes: " + recipes.size());
    recipeList.addAll(recipes);

    cache.invalidateAll();
  }

  public void clearRecipes() {
    recipeList.clear();

    cache.invalidateAll();
  }

  private record SolidifyingCacheKey(Fluid fluidInTank, Fluid fluidOnTop) {}
}
