package novamachina.exnihilosequentia.common.registries;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import javax.annotation.Nonnull;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import novamachina.exnihilosequentia.world.item.crafting.HarvestRecipe;
import novamachina.exnihilosequentia.world.item.crafting.ItemStackWithChance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HarvestingRegistry {

  private static Logger log = LoggerFactory.getLogger(HarvestingRegistry.class);

  @Nonnull private final List<HarvestRecipe> recipeList = new ArrayList<>();

  private final LoadingCache<ItemLike, List<HarvestRecipe>> cache;

  public HarvestingRegistry() {
    CacheLoader<ItemLike, List<HarvestRecipe>> loader =
        new CacheLoader<>() {
          @Override
          public List<HarvestRecipe> load(ItemLike key) {
            return recipeList.stream()
                .filter(crookRecipe -> crookRecipe.getInput().test(new ItemStack(key)))
                .collect(Collectors.toList());
          }
        };

    cache = CacheBuilder.newBuilder().maximumSize(100).build(loader);
  }

  public boolean isCrookable(@Nonnull final ItemLike block) {
    return !getDrops(block).isEmpty();
  }

  @Nonnull
  public List<HarvestRecipe> getDrops(@Nonnull final ItemLike block) {
    try {
      return cache.get(block);
    } catch (ExecutionException e) {
      throw new RuntimeException(e);
    }
  }

  public void setRecipes(@Nonnull final List<HarvestRecipe> recipes) {
    log.debug("Crook Registry recipes: " + recipes.size());
    recipeList.addAll(recipes);

    cache.invalidateAll();
  }

  @Nonnull
  public List<HarvestRecipe> getRecipeList() {
    return recipeList.stream()
        .flatMap(
            crookRecipe -> {
              if (crookRecipe.getDrops().size() <= 21) {
                return Stream.of(crookRecipe);
              }
              @Nonnull
              final List<List<ItemStackWithChance>> partitions =
                  Lists.partition(crookRecipe.getDrops(), 21);
              return IntStream.range(0, partitions.size())
                  .mapToObj(
                      i -> {
                        final ResourceLocation crookRecipeId = crookRecipe.getId();
                        final ResourceLocation newId =
                            new ResourceLocation(
                                crookRecipeId.getNamespace(), crookRecipeId.getPath() + i);
                        return new HarvestRecipe(
                            newId,
                            crookRecipe.getInput(),
                            partitions.get(i).toArray(ItemStackWithChance[]::new));
                      });
            })
        .collect(Collectors.toList());
  }

  public void clearRecipes() {
    recipeList.clear();

    cache.invalidateAll();
  }
}
