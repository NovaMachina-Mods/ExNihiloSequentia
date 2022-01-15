package novamachina.exnihilosequentia.api.registry;

import java.util.List;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.ItemLike;
import novamachina.exnihilosequentia.api.crafting.fluidtransform.FluidTransformRecipe;

import javax.annotation.Nonnull;

public interface IFluidTransformRegistry {
    void clearRecipes();

    @Nonnull
    List<FluidTransformRecipe> getRecipeList();

    @Nonnull
    Fluid getResult(@Nonnull final Fluid fluidInTank, @Nonnull final ItemLike catalyst);

    boolean isValidRecipe(@Nonnull final Fluid fluidInTank, @Nonnull final ItemLike catalyst);

    void setRecipes(@Nonnull final List<FluidTransformRecipe> recipes);
}
