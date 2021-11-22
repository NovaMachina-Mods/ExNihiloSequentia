package novamachina.exnihilosequentia.common.registries;

import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import novamachina.exnihilosequentia.api.crafting.fluidtransform.FluidTransformRecipe;
import novamachina.exnihilosequentia.api.registry.IFluidTransformRegistry;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
import java.util.List;

public class FluidTransformRegistry implements IFluidTransformRegistry {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    private final List<FluidTransformRecipe> recipeList = new ArrayList<>();

    @Override
    public boolean isValidRecipe(Fluid fluidInTank, IItemProvider catalyst) {
        for(FluidTransformRecipe recipe : recipeList) {
            if(recipe.getFluidInTank().isFluidEqual(new FluidStack(fluidInTank, FluidAttributes.BUCKET_VOLUME)) && recipe.getCatalyst().test(new ItemStack(catalyst))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Fluid getResult(Fluid fluidInTank, IItemProvider catalyst) {
        for(FluidTransformRecipe recipe : recipeList) {
            if(recipe.getFluidInTank().isFluidEqual(new FluidStack(fluidInTank, FluidAttributes.BUCKET_VOLUME)) && recipe.getCatalyst().test(new ItemStack(catalyst))) {
                return recipe.getResult().getFluid();
            }
        }
        return Fluids.EMPTY;
    }

    @Override
    public List<FluidTransformRecipe> getRecipeList() {
        return recipeList;
    }

    @Override
    public void setRecipes(List<FluidTransformRecipe> recipes) {
        logger.debug("Fluid Transform Registry recipes: " + recipes.size());
        recipeList.addAll(recipes);
    }

    @Override
    public void clearRecipes() {
        recipeList.clear();
    }
}
