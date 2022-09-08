package novamachina.exnihilosequentia.common.registries;

import com.mojang.logging.LogUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import novamachina.exnihilosequentia.common.crafting.ItemStackWithChance;
import novamachina.exnihilosequentia.common.crafting.hammer.HammerRecipe;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;

public class HammerRegistry {

  @Nonnull
  private static final ExNihiloLogger logger = new ExNihiloLogger(LogUtils.getLogger());

  @Nonnull
  private static final List<HammerRecipe> recipeList = new ArrayList<>();

  @Nonnull
  private final Map<Block, HammerRecipe> recipeByBlockCache = new HashMap<>();

  @Nonnull
  public List<ItemStackWithChance> getResult(@Nonnull final Block input) {
    @Nonnull final List<ItemStackWithChance> returnList = findRecipe(input).getOutput();
    logger.debug("Hammer Drop Stack: " + returnList);
    return returnList;
  }

  public boolean isHammerable(@Nonnull final Block block) {
    return findRecipe(block) != HammerRecipe.EMPTY;
  }

  @Nonnull
  public HammerRecipe findRecipe(@Nonnull final Block block) {
    return recipeByBlockCache.computeIfAbsent(block, k -> {
      @Nonnull final ItemStack itemStack = new ItemStack(block);
      return recipeList
          .stream()
          .filter(recipe -> recipe.getInput().test(itemStack))
          .findFirst()
          .orElse(HammerRecipe.EMPTY);
    });
  }

  public void setRecipes(@Nonnull final List<HammerRecipe> recipes) {
    logger.debug("Hammer Registry recipes: " + recipes.size());
    recipeList.addAll(recipes);

    recipeByBlockCache.clear();
  }

  @Nonnull
  public List<HammerRecipe> getRecipeList() {
    return recipeList;
  }

  public void clearRecipes() {
    recipeList.clear();

    recipeByBlockCache.clear();
  }
}
