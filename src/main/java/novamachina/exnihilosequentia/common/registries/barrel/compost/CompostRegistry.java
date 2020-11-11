package novamachina.exnihilosequentia.common.registries.barrel.compost;

import novamachina.exnihilosequentia.common.api.crafting.compost.CompostRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CompostRegistry {
    public final List<CompostRecipe> recipeList = new ArrayList<>();

    public boolean containsSolid(IItemProvider item) {
        for(CompostRecipe recipe : recipeList) {
            if(recipe.getInput().test(new ItemStack(item))) {
                return true;
            }
        }
        return false;
    }

    public int getSolidAmount(IItemProvider item) {
        for(CompostRecipe recipe : recipeList) {
            if(recipe.getInput().test(new ItemStack(item))) {
                return recipe.getAmount();
            }
        }
        return 0;
    }

    public void setRecipes(Map<ResourceLocation, CompostRecipe> recipes) {
        this.recipeList.addAll(recipes.values());
    }

    public List<CompostRecipe> getRecipeList() {
        return recipeList;
    }
}
