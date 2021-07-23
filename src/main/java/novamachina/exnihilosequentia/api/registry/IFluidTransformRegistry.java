package novamachina.exnihilosequentia.api.registry;

import java.util.List;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import novamachina.exnihilosequentia.api.crafting.fluidtransform.FluidTransformRecipe;

public interface IFluidTransformRegistry {
    void clearRecipes();

    List<FluidTransformRecipe> getRecipeList();

    Fluid getResult(Fluid fluidInTank, ItemLike catalyst);

    boolean isValidRecipe(Fluid fluidInTank, ItemLike catalyst);

    void setRecipes(List<FluidTransformRecipe> recipes);
}
