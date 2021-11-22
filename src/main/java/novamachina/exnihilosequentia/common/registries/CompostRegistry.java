package novamachina.exnihilosequentia.common.registries;

import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import novamachina.exnihilosequentia.api.crafting.compost.CompostRecipe;
import novamachina.exnihilosequentia.api.registry.ICompostRegistry;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
import java.util.List;

public class CompostRegistry implements ICompostRegistry {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());
    public final List<CompostRecipe> recipeList = new ArrayList<>();

    @Override
    public boolean containsSolid(IItemProvider item) {
        for(CompostRecipe recipe : recipeList) {
            if(recipe.getInput().test(new ItemStack(item))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getSolidAmount(IItemProvider item) {
        for(CompostRecipe recipe : recipeList) {
            if(recipe.getInput().test(new ItemStack(item))) {
                return recipe.getAmount();
            }
        }
        return 0;
    }

    @Override
    public void setRecipes(List<CompostRecipe> recipes) {
        logger.debug("Compost Registry recipes: " + recipes.size());
        this.recipeList.addAll(recipes);
    }

    @Override
    public List<CompostRecipe> getRecipeList() {
        return recipeList;
    }

    @Override
    public void clearRecipes() {
        recipeList.clear();
    }
}
