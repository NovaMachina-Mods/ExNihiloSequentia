package novamachina.exnihilosequentia.api.registry;

import java.util.List;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.item.ItemStack;
import novamachina.exnihilosequentia.api.crafting.fluidontop.FluidOnTopRecipe;

import javax.annotation.Nonnull;

public interface IFluidOnTopRegistry {
    void clearRecipes();

    @Nonnull
    List<FluidOnTopRecipe> getRecipeList();

    @Nonnull
    ItemStack getResult(@Nonnull final Fluid fluidInTank, @Nonnull final Fluid fluidOnTop);

    boolean isValidRecipe(@Nonnull final Fluid fluidInTank, @Nonnull final Fluid fluidOnTop);

    void setRecipes(@Nonnull final List<FluidOnTopRecipe> recipes);
}
