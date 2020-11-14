package novamachina.exnihilosequentia.common.registries;

import novamachina.exnihilosequentia.api.crafting.hammer.HammerRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import novamachina.exnihilosequentia.api.registry.IHammerRegistry;
import novamachina.exnihilosequentia.common.utility.LogUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HammerRegistry implements IHammerRegistry {

    public static List<HammerRecipe> recipeList = new ArrayList<>();

    @Override
    public ItemStack getResult(ResourceLocation input) {
        return findRecipe(input).getRecipeOutput();
    }

    @Override
    public boolean isHammerable(ResourceLocation blockID) {

        return findRecipe(blockID) != HammerRecipe.EMPTY;
    }

    @Override
    public HammerRecipe findRecipe(ResourceLocation blockID) {
        for (HammerRecipe recipe : recipeList) {
            if (recipe.getInput().getItem().getRegistryName().equals(blockID)) {
                return recipe;
            }
        }
        return HammerRecipe.EMPTY;
    }

    @Override
    public void setRecipes(List<HammerRecipe> recipes) {
        LogUtil.debug("Hammer Registry recipes: " + recipes.size());
        recipeList.addAll(recipes);
    }

    @Override
    public List<HammerRecipe> getRecipeList() {
        return recipeList;
    }
}
