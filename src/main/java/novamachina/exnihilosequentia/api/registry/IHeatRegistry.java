package novamachina.exnihilosequentia.api.registry;

import java.util.List;
import net.minecraft.block.BlockState;
import novamachina.exnihilosequentia.api.crafting.heat.HeatRecipe;

import javax.annotation.Nonnull;

public interface IHeatRegistry {
    void clearRecipes();

    int getHeatAmount(@Nonnull final BlockState entry);

    @Nonnull
    List<HeatRecipe> getRecipeList();

    void setRecipes(@Nonnull final List<HeatRecipe> recipes);
}
