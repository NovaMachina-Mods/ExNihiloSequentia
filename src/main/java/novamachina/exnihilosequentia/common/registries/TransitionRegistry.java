package novamachina.exnihilosequentia.common.registries;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.annotation.Nonnull;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import novamachina.exnihilosequentia.world.item.crafting.TransitionRecipe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransitionRegistry {

  private static Logger log = LoggerFactory.getLogger(TransitionRegistry.class);

  @Nonnull private final List<TransitionRecipe> recipeList = new ArrayList<>();
  private final LoadingCache<TransitionCacheKey, Fluid> cache;

  public TransitionRegistry() {
    CacheLoader<TransitionCacheKey, Fluid> loader =
        new CacheLoader<>() {
          @Override
          public Fluid load(TransitionCacheKey key) {
            return recipeList.stream()
                .filter(
                    recipe ->
                        recipe
                            .getFluidInTank()
                            .isFluidEqual(
                                new FluidStack(key.fluidInTank(), FluidType.BUCKET_VOLUME)))
                .filter(recipe -> recipe.getCatalyst().test(new ItemStack(key.catalyst)))
                .findFirst()
                .map(TransitionRecipe::getResult)
                .map(FluidStack::getFluid)
                .orElse(Fluids.EMPTY);
          }
        };
    cache = CacheBuilder.newBuilder().maximumSize(100).build(loader);
  }

  public boolean isValidRecipe(@Nonnull final Fluid fluidInTank, @Nonnull final ItemLike catalyst) {
    return getResult(fluidInTank, catalyst) != Fluids.EMPTY;
  }

  @Nonnull
  public Fluid getResult(@Nonnull final Fluid fluidInTank, @Nonnull final ItemLike catalyst) {
    try {
      return cache.get(new TransitionCacheKey(fluidInTank, catalyst));
    } catch (ExecutionException e) {
      throw new RuntimeException(e);
    }
  }

  @Nonnull
  public List<TransitionRecipe> getRecipeList() {
    return recipeList;
  }

  public void setRecipes(@Nonnull final List<TransitionRecipe> recipes) {
    log.debug("Fluid Transform Registry recipes: " + recipes.size());
    recipeList.addAll(recipes);

    cache.invalidateAll();
  }

  public void clearRecipes() {
    recipeList.clear();

    cache.invalidateAll();
  }

  private record TransitionCacheKey(Fluid fluidInTank, ItemLike catalyst) {}
}
