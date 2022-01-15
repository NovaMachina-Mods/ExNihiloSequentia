package novamachina.exnihilosequentia.api.registry;

import java.util.List;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.util.IItemProvider;
import novamachina.exnihilosequentia.api.crafting.fluiditem.FluidItemRecipe;

import javax.annotation.Nonnull;

public interface IFluidItemTransformRegistry {
    void clearRecipes();

    @Nonnull
    List<FluidItemRecipe> getRecipeList();

    @Nonnull
    IItemProvider getResult(@Nonnull final Fluid fluid, @Nonnull final Item input);

    boolean isValidRecipe(@Nonnull final Fluid fluid, @Nonnull final Item input);

    void setRecipes(@Nonnull final List<FluidItemRecipe> recipes);
}
