package novamachina.exnihilosequentia.common.registries;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.registries.ForgeRegistryEntry;
import novamachina.exnihilosequentia.api.crafting.heat.HeatRecipe;
import novamachina.exnihilosequentia.api.registry.IHeatRegistry;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

public class HeatRegistry implements IHeatRegistry {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    private final List<HeatRecipe> recipeList = new ArrayList<>();

    @Override
    public void clearRecipes() {
        recipeList.clear();
    }

    @Override
    public int getHeatAmount(ForgeRegistryEntry<? extends IItemProvider> entry) {
        return recipeList.stream().filter(recipe -> recipe.getInput().getRegistryName().equals(entry.getRegistryName())).findFirst().map(HeatRecipe::getAmount).orElse(0);
    }

    @Override
    public List<HeatRecipe> getRecipeList() {
        return recipeList;
    }

    @Override
    public void setRecipes(List<HeatRecipe> recipes) {
        logger.debug("Heat Registry recipes: " + recipes.size());
        this.recipeList.addAll(recipes);
    }
}
