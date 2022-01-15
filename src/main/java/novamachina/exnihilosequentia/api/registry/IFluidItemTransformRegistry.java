package novamachina.exnihilosequentia.api.registry;

import java.util.List;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import novamachina.exnihilosequentia.api.crafting.fluiditem.FluidItemRecipe;

import javax.annotation.Nonnull;

public interface IFluidItemTransformRegistry {
    void clearRecipes();

    @Nonnull
    List<FluidItemRecipe> getRecipeList();

    @Nonnull
    ItemLike getResult(@Nonnull final Fluid fluid, @Nonnull final Item input);

    boolean isValidRecipe(@Nonnull final Fluid fluid, @Nonnull final Item input);

    void setRecipes(@Nonnull final List<FluidItemRecipe> recipes);
}
