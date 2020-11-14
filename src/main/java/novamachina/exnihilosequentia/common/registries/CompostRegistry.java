package novamachina.exnihilosequentia.common.registries;

import novamachina.exnihilosequentia.api.crafting.compost.CompostRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import novamachina.exnihilosequentia.api.registry.ICompostRegistry;
import novamachina.exnihilosequentia.common.utility.LogUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CompostRegistry implements ICompostRegistry {
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
        LogUtil.debug("Compost Registry recipes: " + recipes.size());
        this.recipeList.addAll(recipes);
    }

    @Override
    public List<CompostRecipe> getRecipeList() {
        return recipeList;
    }
}
