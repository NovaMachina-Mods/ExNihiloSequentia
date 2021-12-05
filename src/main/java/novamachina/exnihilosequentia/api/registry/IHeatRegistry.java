package novamachina.exnihilosequentia.api.registry;

import net.minecraft.world.level.block.state.BlockState;
import novamachina.exnihilosequentia.api.crafting.heat.HeatRecipe;

import java.util.List;

public interface IHeatRegistry {
    void clearRecipes();

    int getHeatAmount(BlockState entry);

    List<HeatRecipe> getRecipeList();

    void setRecipes(List<HeatRecipe> recipes);
}
