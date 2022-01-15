package novamachina.exnihilosequentia.common.registries;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.world.level.block.state.BlockState;
import novamachina.exnihilosequentia.api.crafting.heat.HeatRecipe;
import novamachina.exnihilosequentia.api.registry.IHeatRegistry;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

import javax.annotation.Nonnull;

public class HeatRegistry implements IHeatRegistry {
    @Nonnull private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    @Nonnull private final List<HeatRecipe> recipeList = new ArrayList<>();

    @Override
    public void clearRecipes() {
        recipeList.clear();
    }

    @Override
    public int getHeatAmount(@Nonnull final BlockState entry) {
        return recipeList
                .stream()
                .filter(recipe -> recipe.isMatch(entry))
                .findFirst()
                .map(HeatRecipe::getAmount)
                .orElse(0);
    }

    @Override
    @Nonnull
    public List<HeatRecipe> getRecipeList() {
        return recipeList;
    }

    @Override
    public void setRecipes(@Nonnull final List<HeatRecipe> recipes) {
        logger.debug("Heat Registry recipes: " + recipes.size());
        recipeList.addAll(recipes);
    }
}
