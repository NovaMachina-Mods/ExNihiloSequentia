package novamachina.exnihilosequentia.common.registries;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import net.minecraft.world.level.block.state.BlockState;
import novamachina.exnihilosequentia.common.crafting.heat.HeatRecipe;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

public class HeatRegistry {

  @Nonnull
  private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

  @Nonnull
  private final List<HeatRecipe> recipeList = new ArrayList<>();

  public void clearRecipes() {
    recipeList.clear();
  }

  public int getHeatAmount(@Nonnull final BlockState entry) {
    return recipeList
        .stream()
        .filter(recipe -> recipe.isMatch(entry))
        .findFirst()
        .map(HeatRecipe::getAmount)
        .orElse(0);
  }

  @Nonnull
  public List<HeatRecipe> getRecipeList() {
    return recipeList;
  }

  public void setRecipes(@Nonnull final List<HeatRecipe> recipes) {
    logger.debug("Heat Registry recipes: " + recipes.size());
    recipeList.addAll(recipes);
  }
}
