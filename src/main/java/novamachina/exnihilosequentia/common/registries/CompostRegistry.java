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
import novamachina.exnihilosequentia.world.item.crafting.CompostRecipe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CompostRegistry {

  private static Logger log = LoggerFactory.getLogger(CompostRegistry.class);
  @Nonnull public final List<CompostRecipe> recipeList = new ArrayList<>();

  private final LoadingCache<ItemLike, Integer> cache;

  public CompostRegistry() {
    CacheLoader<ItemLike, Integer> loader =
        new CacheLoader<>() {
          @Override
          public Integer load(ItemLike key) {
            return recipeList.stream()
                .filter(compostRecipe -> compostRecipe.getInput().test(new ItemStack(key)))
                .findFirst()
                .map(CompostRecipe::getAmount)
                .orElse(0);
          }
        };
    cache = CacheBuilder.newBuilder().maximumSize(100).build(loader);
  }

  public boolean containsSolid(@Nonnull final ItemLike item) {
    return getSolidAmount(item) > 0;
  }

  public int getSolidAmount(@Nonnull final ItemLike item) {
    try {
      return cache.get(item);
    } catch (ExecutionException e) {
      throw new RuntimeException(e);
    }
  }

  public void setRecipes(@Nonnull final List<CompostRecipe> recipes) {
    log.debug("Compost Registry recipes: " + recipes.size());
    recipeList.addAll(recipes);
    cache.invalidateAll();
  }

  @Nonnull
  public List<CompostRecipe> getRecipeList() {
    return recipeList;
  }

  public void clearRecipes() {
    recipeList.clear();

    cache.invalidateAll();
  }
}
