package novamachina.exnihilosequentia.api.registry;

import java.util.List;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.material.Fluid;
import novamachina.exnihilosequentia.api.crafting.fluiditem.FluidItemRecipe;

public interface IFluidItemTransformRegistry {
    void clearRecipes();

    List<FluidItemRecipe> getRecipeList();

    ItemLike getResult(Fluid fluid, Item input);

    boolean isValidRecipe(Fluid fluid, Item input);

    void setRecipes(List<FluidItemRecipe> recipes);
}
