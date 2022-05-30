package novamachina.exnihilosequentia.common.registries;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.Nonnull;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import novamachina.exnihilosequentia.common.compat.jei.crucible.JEICrucibleRecipe;
import novamachina.exnihilosequentia.common.crafting.crucible.CrucibleRecipe;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

public class CrucibleRegistry {

  @Nonnull private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

  @Nonnull private final List<CrucibleRecipe> recipeList = new ArrayList<>();

  @Nonnull private final Map<Item, CrucibleRecipe> recipeByItemCache = new HashMap<>();

  @Nonnull
  public List<JEICrucibleRecipe> getRecipeList() {
    return recipeList
        .stream()
        .flatMap(crucibleRecipe -> {
          if (crucibleRecipe.getInputs().size() <= 21) {
            return Stream.of(new JEICrucibleRecipe(crucibleRecipe.getAmount(), crucibleRecipe.getCrucibleType(), crucibleRecipe.getInputs(), crucibleRecipe.getResultFluid()));
          }
          @Nonnull final List<List<ItemStack>> partitions = Lists.partition(
              crucibleRecipe.getInputs(),
              21);
          return partitions.stream().map(partition -> {
            return new JEICrucibleRecipe(crucibleRecipe.getAmount(),
                crucibleRecipe.getCrucibleType(), partition, crucibleRecipe.getResultFluid());
          });
        })
        .collect(Collectors.toList());
  }

  public void setRecipes(@Nonnull final List<CrucibleRecipe> recipes) {
    logger.debug("Crucible Registry recipes: " + recipes.size());
    recipeList.addAll(recipes);
  }

  @Nonnull
  public Optional<CrucibleRecipe> findRecipeByItemStack(@Nonnull final ItemStack itemStack) {
    return Optional.ofNullable(
        recipeByItemCache.computeIfAbsent(
            itemStack.getItem(),
            k ->
                recipeList.stream()
                    .filter(recipe -> recipe.getInput().test(itemStack))
                    .findFirst()
                    .orElse(null)));
  }

  @Nonnull
  public Optional<CrucibleRecipe> findRecipeByItem(@Nonnull final Item item) {
    return Optional.ofNullable(
        recipeByItemCache.computeIfAbsent(
            item,
            k -> {
              final ItemStack itemStack = new ItemStack(item);
              return recipeList.stream()
                  .filter(recipe -> recipe.getInput().test(itemStack))
                  .findFirst()
                  .orElse(null);
            }));
  }

  public boolean isMeltable(@Nonnull final ItemLike item, final int level) {
    return isMeltableByItem(item.asItem(), level);
  }

  public boolean isMeltableByItemStack(@Nonnull final ItemStack itemStack, final int level) {
    final Optional<CrucibleRecipe> recipe = findRecipeByItemStack(itemStack);
    return recipe.isPresent() && recipe.get().getCrucibleType().getLevel() <= level;
  }

  public boolean isMeltableByItem(@Nonnull final Item item, final int level) {
    final Optional<CrucibleRecipe> recipe = findRecipeByItem(item);
    return recipe.isPresent() && recipe.get().getCrucibleType().getLevel() <= level;
  }

  public void clearRecipes() {
    recipeList.clear();

    recipeByItemCache.clear();
  }
}
