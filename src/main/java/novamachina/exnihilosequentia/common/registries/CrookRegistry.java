package novamachina.exnihilosequentia.common.registries;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import novamachina.exnihilosequentia.api.crafting.crook.CrookRecipe;
import novamachina.exnihilosequentia.api.registry.ICrookRegistry;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
import java.util.List;

public class CrookRegistry implements ICrookRegistry {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    private final List<CrookRecipe> recipeList = new ArrayList<>();

    @Override
    public boolean isCrookable(ItemLike block) {
        for (CrookRecipe recipe : recipeList) {
            if (recipe.getInput().test(new ItemStack(block))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<CrookRecipe> getDrops(ItemLike block) {
        List<CrookRecipe> returnList = new ArrayList<>();
        for (CrookRecipe recipe : recipeList) {
            if (recipe.getInput().test(new ItemStack(block))) {
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