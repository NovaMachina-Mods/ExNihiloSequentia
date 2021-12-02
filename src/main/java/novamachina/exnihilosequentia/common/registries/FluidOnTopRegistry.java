package novamachina.exnihilosequentia.common.registries;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import novamachina.exnihilosequentia.api.crafting.fluidontop.FluidOnTopRecipe;
import novamachina.exnihilosequentia.api.registry.IFluidOnTopRegistry;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
import java.util.List;

public class FluidOnTopRegistry implements IFluidOnTopRegistry {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    private final List<FluidOnTopRecipe> recipeList = new ArrayList<>();

    @Override
    public boolean isValidRecipe(Fluid fluidInTank, Fluid fluidOnTop) {
        for(FluidOnTopRecipe recipe : recipeList) {
            if(recipe.validInputs(fluidInTank, fluidOnTop)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ItemStack getResult(Fluid fluidInTank, Fluid fluidOnTop) {
        for(FluidOnTopRecipe recipe : recipeList) {
            if(recipe.validInputs(fluidInTank, fluidOnTop)) {
                return recipe.getResultItem();
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    public List<FluidOnTopRecipe> getRecipeList() {
        return recipeList;
    }

    @Override
    public void setRecipes(List<FluidOnTopRecipe> recipes) {
        logger.debug("Fluid On Top Registry recipes: " + recipes.size());
        recipeList.addAll(recipes);
    }

    @Override
    public void clearRecipes() {
        recipeList.clear();
    }
}
