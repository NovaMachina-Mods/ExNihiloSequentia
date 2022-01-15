package novamachina.exnihilosequentia.api.registry;

import java.util.List;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.IItemProvider;
import novamachina.exnihilosequentia.api.crafting.fluidtransform.FluidTransformRecipe;

import javax.annotation.Nonnull;

public interface IFluidTransformRegistry {
    void clearRecipes();

    @Nonnull
    List<FluidTransformRecipe> getRecipeList();

    @Nonnull
    Fluid getResult(@Nonnull final Fluid fluidInTank, @Nonnull final IItemProvider catalyst);

    boolean isValidRecipe(@Nonnull final Fluid fluidInTank, @Nonnull final IItemProvider catalyst);

    void setRecipes(@Nonnull final List<FluidTransformRecipe> recipes);
}
