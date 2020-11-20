package novamachina.exnihilosequentia.common.registries;

import novamachina.exnihilosequentia.api.crafting.crook.CrookRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import novamachina.exnihilosequentia.api.registry.ICrookRegistry;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
import java.util.List;

public class CrookRegistry implements ICrookRegistry {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    private List<CrookRecipe> recipeList = new ArrayList<>();

    @Override
    public List<CrookRecipe> getDrops(IItemProvider block) {
        List<CrookRecipe> returnList = new ArrayList<>();
        for(CrookRecipe recipe : recipeList) {
            if(recipe.getInput().test(new ItemStack(block))) {
                returnList.add(recipe);
            }
        }
        return returnList;
    }

    @Override
    public void setRecipes(List<CrookRecipe> recipes) {
        logger.debug("Crook Registry recipes: " + recipes.size());
        recipeList.addAll(recipes);
    }

    @Override
    public List<CrookRecipe> getRecipeList() {
        return recipeList;
    }

    @Override
    public void clearRecipes() {
        recipeList.clear();
    }
}