package novamachina.exnihilosequentia.common.registries.barrel.fluid;

import novamachina.exnihilosequentia.common.api.crafting.fluidItem.FluidItemRecipe;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FluidBlockTransformRegistry {
    private final List<FluidItemRecipe> recipeList = new ArrayList<>();

    public boolean isValidRecipe(Fluid fluid, Item input) {
        for (FluidItemRecipe recipe : recipeList) {
            if(recipe.validInputs(fluid, input)) {
                return true;
            }
        }
        return false;
    }

    public IItemProvider getResult(Fluid fluid, Item input) {
        for (FluidItemRecipe recipe : recipeList) {
            if (recipe.validInputs(fluid, input)) {
                return recipe.getResult().getItem();
            }
        }
        return ItemStack.EMPTY.getItem();
    }

    public List<FluidItemRecipe> getRecipeList() {
        return recipeList;
    }

    public void setRecipes(Map<ResourceLocation, FluidItemRecipe> recipes) {
        recipeList.addAll(recipes.values());
    }
}
