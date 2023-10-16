package novamachina.exnihilosequentia.common.registries;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.Nonnull;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import novamachina.exnihilosequentia.common.compat.jei.melting.JEICrucibleRecipe;
import novamachina.exnihilosequentia.world.item.crafting.MeltingRecipe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MeltingRegistry {

  private static Logger log = LoggerFactory.getLogger(MeltingRegistry.class);

  @Nonnull private final List<MeltingRecipe> recipeList = new ArrayList<>();

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

  @Nonnull
  public List<JEICrucibleRecipe> getRecipeList() {
    return recipeList.stream()
        .flatMap(
            crucibleRecipe -> {
              if (crucibleRecipe.getInput().getItems().length <= 21) {
                return Stream.of(
                    new JEICrucibleRecipe(
                        crucibleRecipe.getResultFluid().getAmount(),
                        crucibleRecipe.getCrucibleType(),
                        Arrays.asList(crucibleRecipe.getInput().getItems()),
                        crucibleRecipe.getResultFluid()));
              }
              @Nonnull
              final List<List<ItemStack>> partitions =
                  Lists.partition(List.of(crucibleRecipe.getInput().getItems()), 21);
              return partitions.stream()
                  .map(
                      partition ->
                          new JEICrucibleRecipe(
                              crucibleRecipe.getResultFluid().getAmount(),
                              crucibleRecipe.getCrucibleType(),
                              partition,
                              crucibleRecipe.getResultFluid()));
            })
        .collect(Collectors.toList());
  }

  public void setRecipes(@Nonnull final List<MeltingRecipe> recipes) {
    log.debug("Crucible Registry recipes: " + recipes.size());
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
    Optional<MeltingRecipe> recipe = null;
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
}
