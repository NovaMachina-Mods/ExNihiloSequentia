package novamachina.exnihilosequentia.common.registries;

import novamachina.exnihilosequentia.api.crafting.fluidItem.FluidItemRecipe;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import novamachina.exnihilosequentia.api.registry.IFluidItemTransformRegistry;
import novamachina.exnihilosequentia.common.utility.LogUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FluidItemTransformRegistry implements IFluidItemTransformRegistry {
    private final List<FluidItemRecipe> recipeList = new ArrayList<>();

    @Override
    public boolean isValidRecipe(Fluid fluid, Item input) {
        for (FluidItemRecipe recipe : recipeList) {
            if(recipe.validInputs(fluid, input)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public IItemProvider getResult(Fluid fluid, Item input) {
        for (FluidItemRecipe recipe : recipeList) {
            if (recipe.validInputs(fluid, input)) {
                return recipe.getResult().getItem();
            }
        }
        return ItemStack.EMPTY.getItem();
    }

    @Override
    public List<FluidItemRecipe> getRecipeList() {
        return recipeList;
    }

    @Override
    public void setRecipes(List<FluidItemRecipe> recipes) {
        LogUtil.debug("Fluid Item Transform Registry recipes: " + recipes.size());
        recipeList.addAll(recipes);
    }
}
