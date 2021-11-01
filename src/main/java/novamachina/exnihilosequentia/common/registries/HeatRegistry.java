package novamachina.exnihilosequentia.common.registries;

import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistryEntry;
import novamachina.exnihilosequentia.api.crafting.heat.HeatRecipe;
import novamachina.exnihilosequentia.api.registry.IHeatRegistry;
import novamachina.exnihilosequentia.api.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HeatRegistry implements IHeatRegistry {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    private final List<HeatRecipe> recipeList = new ArrayList<>();

    @Override
    public void clearRecipes() {
        recipeList.clear();
    }

    @Override
    public int getHeatAmount(ForgeRegistryEntry<? extends ItemLike> entry) {
        return recipeList.stream().filter(recipe -> Objects.equals(recipe.getInput().getRegistryName(), entry.getRegistryName())).findFirst().map(HeatRecipe::getAmount).orElse(0);
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
