package novamachina.exnihilosequentia.api.registry;

import java.util.List;
import net.minecraft.util.IItemProvider;
import novamachina.exnihilosequentia.api.crafting.crook.CrookRecipe;

public interface ICrookRegistry {
    void clearRecipes();

    List<CrookRecipe> getDrops(IItemProvider block);

    List<CrookRecipe> getRecipeList();

    boolean isCrookable(IItemProvider block);

    void setRecipes(List<CrookRecipe> recipes);
}
