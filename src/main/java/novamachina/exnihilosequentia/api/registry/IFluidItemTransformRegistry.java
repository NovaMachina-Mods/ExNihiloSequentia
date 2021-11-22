package novamachina.exnihilosequentia.api.registry;

import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.util.IItemProvider;
import novamachina.exnihilosequentia.api.crafting.fluiditem.FluidItemRecipe;

import java.util.List;

public interface IFluidItemTransformRegistry {
    void clearRecipes();

    List<FluidItemRecipe> getRecipeList();

    IItemProvider getResult(Fluid fluid, Item input);

    boolean isValidRecipe(Fluid fluid, Item input);

    void setRecipes(List<FluidItemRecipe> recipes);
}
