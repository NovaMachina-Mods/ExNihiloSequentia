package novamachina.exnihilosequentia.common.registries;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.annotation.Nonnull;
import net.minecraft.world.level.block.state.BlockState;
import novamachina.exnihilosequentia.world.item.crafting.HeatRecipe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HeatRegistry {

  private static Logger log = LoggerFactory.getLogger(HeatRegistry.class);

  @Nonnull private final List<HeatRecipe> recipeList = new ArrayList<>();

  private final LoadingCache<BlockState, Integer> cache;

  public HeatRegistry() {
    CacheLoader<BlockState, Integer> loader =
        new CacheLoader<>() {
          @Override
          public Integer load(BlockState key) {
            return recipeList.stream()
                .filter(recipe -> recipe.isMatch(key))
                .findFirst()
                .map(HeatRecipe::getAmount)
                .orElse(0);
          }
        };

    cache = CacheBuilder.newBuilder().maximumSize(100).build(loader);
  }

  public void clearRecipes() {
    recipeList.clear();
    cache.invalidateAll();
  }

  public int getHeatAmount(@Nonnull final BlockState entry) {
    try {
      return cache.get(entry);
    } catch (ExecutionException e) {
      throw new RuntimeException(e);
    }
  }

  @Nonnull
  public List<HeatRecipe> getRecipeList() {
    return recipeList;
  }

  public void setRecipes(@Nonnull final List<HeatRecipe> recipes) {
    log.debug("Heat Registry recipes: " + recipes.size());
    recipeList.addAll(recipes);
    cache.invalidateAll();
  }
}
