package novamachina.exnihilosequentia.common.registries;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import novamachina.exnihilosequentia.api.crafting.fluiditem.FluidItemRecipe;
import novamachina.exnihilosequentia.api.registry.IFluidItemTransformRegistry;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
import java.util.List;

public class FluidItemTransformRegistry implements IFluidItemTransformRegistry {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

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
    public Item getResult(Fluid fluid, Item input) {
        for (FluidItemRecipe recipe : recipeList) {
            if (recipe.validInputs(fluid, input)) {
                return recipe.getResultItem().getItem();
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
        logger.debug("Fluid Item Transform Registry recipes: " + recipes.size());
        recipeList.addAll(recipes);
    }

    @Override
    public void clearRecipes() {
        recipeList.clear();
    }
}
