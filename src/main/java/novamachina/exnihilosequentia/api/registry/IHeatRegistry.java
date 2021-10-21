package novamachina.exnihilosequentia.api.registry;

import java.util.List;
import net.minecraft.block.BlockState;
import novamachina.exnihilosequentia.api.crafting.heat.HeatRecipe;

public interface IHeatRegistry {
    void clearRecipes();

    int getHeatAmount(BlockState entry);

    List<HeatRecipe> getRecipeList();

    void setRecipes(List<HeatRecipe> recipes);
}
