package novamachina.exnihilosequentia.common.registries;

import com.mojang.logging.LogUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import novamachina.exnihilosequentia.common.crafting.compost.CompostRecipe;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;

public class CompostRegistry {

  @Nonnull
  private static final ExNihiloLogger logger = new ExNihiloLogger(LogUtils.getLogger());
  @Nonnull
  public final List<CompostRecipe> recipeList = new ArrayList<>();

  @Nonnull
  private final Map<Item, Integer> itemSolidAmountCache = new HashMap<>();

  public boolean containsSolid(@Nonnull final ItemLike item) {
    return getSolidAmount(item) > 0;
  }

  public int getSolidAmount(@Nonnull final ItemLike item) {
    return itemSolidAmountCache
        .computeIfAbsent(item.asItem(), k -> {
          @Nonnull final ItemStack itemStack = new ItemStack(item);
          return recipeList
              .stream()
              .filter(compostRecipe -> compostRecipe.getInput().test(itemStack))
              .findFirst()
              .map(CompostRecipe::getAmount)
              .orElse(0);
        });
  }

  public void setRecipes(@Nonnull final List<CompostRecipe> recipes) {
    logger.debug("Compost Registry recipes: " + recipes.size());
    recipeList.addAll(recipes);

    itemSolidAmountCache.clear();
  }

  @Nonnull
  public List<CompostRecipe> getRecipeList() {
    return recipeList;
  }

  public void clearRecipes() {
    recipeList.clear();

    itemSolidAmountCache.clear();
  }
}
