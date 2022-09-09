package novamachina.exnihilosequentia.common.registries;

import com.google.common.collect.Lists;
import com.mojang.logging.LogUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import javax.annotation.Nonnull;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import novamachina.exnihilosequentia.common.crafting.ItemStackWithChance;
import novamachina.exnihilosequentia.common.crafting.crook.CrookRecipe;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;

public class CrookRegistry {

  @Nonnull private static final ExNihiloLogger logger = new ExNihiloLogger(LogUtils.getLogger());

  @Nonnull private final List<CrookRecipe> recipeList = new ArrayList<>();

  @Nonnull private final Map<ItemLike, List<CrookRecipe>> recipeListByItemCache = new HashMap<>();

  public boolean isCrookable(@Nonnull final ItemLike block) {
    return !getDrops(block).isEmpty();
  }

  @Nonnull
  public List<CrookRecipe> getDrops(@Nonnull final ItemLike block) {
    return recipeListByItemCache.computeIfAbsent(
        block,
        k -> {
          @Nonnull final ItemStack itemStack = new ItemStack(block);
          return recipeList.stream()
              .filter(crookRecipe -> crookRecipe.getInput().test(itemStack))
              .collect(Collectors.toList());
        });
  }

  public void setRecipes(@Nonnull final List<CrookRecipe> recipes) {
    logger.debug("Crook Registry recipes: " + recipes.size());
    recipeList.addAll(recipes);

    recipeListByItemCache.clear();
  }

  @Nonnull
  public List<CrookRecipe> getRecipeList() {
    return recipeList.stream()
        .flatMap(
            crookRecipe -> {
              if (crookRecipe.getOutput().size() <= 21) {
                return Stream.of(crookRecipe);
              }
              @Nonnull
              final List<List<ItemStackWithChance>> partitions =
                  Lists.partition(crookRecipe.getOutput(), 21);
              return IntStream.range(0, partitions.size())
                  .mapToObj(
                      i -> {
                        final ResourceLocation crookRecipeId = crookRecipe.getId();
                        final ResourceLocation newId =
                            new ResourceLocation(
                                crookRecipeId.getNamespace(), crookRecipeId.getPath() + i);
                        return new CrookRecipe(newId, crookRecipe.getInput(), partitions.get(i));
                      });
            })
        .collect(Collectors.toList());
  }

  public void clearRecipes() {
    recipeList.clear();

    recipeListByItemCache.clear();
  }
}
