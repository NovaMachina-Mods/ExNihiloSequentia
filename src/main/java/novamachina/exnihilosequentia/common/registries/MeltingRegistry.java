package novamachina.exnihilosequentia.common.registries;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import javax.annotation.Nonnull;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import novamachina.exnihilosequentia.world.item.crafting.MeltingRecipe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MeltingRegistry {
  private static final Logger log = LoggerFactory.getLogger(MeltingRegistry.class);
  private final List<MeltingRecipe> recipeList = new ArrayList<>();

  private final LoadingCache<ItemLike, Optional<MeltingRecipe>> cache;

  public MeltingRegistry() {
    CacheLoader<ItemLike, Optional<MeltingRecipe>> loader =
        new CacheLoader<>() {
          @Override
          public Optional<MeltingRecipe> load(ItemLike key) {
            return recipeList.stream()
                .filter(recipe -> recipe.getInput().test(new ItemStack(key)))
                .findFirst();
          }
        };
    cache = CacheBuilder.newBuilder().maximumSize(100).build(loader);
  }

  public void setRecipes(@Nonnull final List<MeltingRecipe> recipes) {
    log.debug("Crucible Registry recipes: {}", recipes.size());
    recipeList.addAll(recipes);
    cache.invalidateAll();
  }

  @Nonnull
  public Optional<MeltingRecipe> findRecipe(ItemLike item) {
    try {
      return cache.get(item);
    } catch (ExecutionException e) {
      throw new RuntimeException(e);
    }
  }

  public boolean isMeltable(@Nonnull final ItemLike item, final int level) {
    Optional<MeltingRecipe> recipe = Optional.empty();
    try {
      recipe = cache.get(item);
    } catch (ExecutionException e) {
      throw new RuntimeException(e);
    }

    return recipe.isPresent() && recipe.get().getCrucibleType().getLevel() <= level;
  }

  public void clearRecipes() {
    recipeList.clear();

    cache.invalidateAll();
  }

  public List<MeltingRecipe> getRecipeList() {
    return this.recipeList;
  }
}
