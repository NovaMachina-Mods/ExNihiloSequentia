package novamachina.exnihilosequentia.common.registries;

import novamachina.exnihilosequentia.api.crafting.crook.CrookRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import novamachina.exnihilosequentia.api.registry.ICrookRegistry;
import novamachina.exnihilosequentia.common.utility.LogUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CrookRegistry implements ICrookRegistry {

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
        LogUtil.debug("Crook Registry recipes: " + recipes.size());
        recipeList.addAll(recipes);
    }

    @Override
    public List<CrookRecipe> getRecipeList() {
        return recipeList;
    }
}