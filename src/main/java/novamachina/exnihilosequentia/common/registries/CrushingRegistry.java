package novamachina.exnihilosequentia.common.registries;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.annotation.Nonnull;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import novamachina.exnihilosequentia.world.item.crafting.CrushingRecipe;
import novamachina.exnihilosequentia.world.item.crafting.ItemStackWithChance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CrushingRegistry {

  private static Logger log = LoggerFactory.getLogger(CrushingRegistry.class);

  @Nonnull private static final List<CrushingRecipe> recipeList = new ArrayList<>();
  private final LoadingCache<Block, CrushingRecipe> cache;

  public CrushingRegistry() {
    CacheLoader<Block, CrushingRecipe> loader =
        new CacheLoader<>() {
          @Override
          public CrushingRecipe load(Block key) {
            return recipeList.stream()
                .filter(recipe -> recipe.getInput().test(new ItemStack(key)))
                .findFirst()
                .orElse(CrushingRecipe.EMPTY);
          }
        };
    cache = CacheBuilder.newBuilder().maximumSize(100).build(loader);
  }

  @Nonnull
  public List<ItemStackWithChance> getResult(@Nonnull final Block input) {
    @Nonnull final List<ItemStackWithChance> returnList = findRecipe(input).getDrops();
    log.debug("Hammer Drop Stack: " + returnList);
    return returnList;
  }

  public boolean isHammerable(@Nonnull final Block block) {
    return findRecipe(block) != CrushingRecipe.EMPTY;
  }

  @Nonnull
  public CrushingRecipe findRecipe(@Nonnull final Block block) {
    try {
      return cache.get(block);
    } catch (ExecutionException e) {
      throw new RuntimeException(e);
    }
  }

  public void setRecipes(@Nonnull final List<CrushingRecipe> recipes) {
    log.debug("Hammer Registry recipes: " + recipes.size());
    recipeList.addAll(recipes);

    cache.invalidateAll();
  }

  @Nonnull
  public List<CrushingRecipe> getRecipeList() {
    return recipeList;
  }

  public void clearRecipes() {
    recipeList.clear();

    cache.invalidateAll();
  }
}
