package novamachina.exnihilosequentia.api.registry;

import net.minecraft.util.IItemProvider;
import novamachina.exnihilosequentia.api.crafting.crook.CrookRecipe;

import java.util.List;

public interface ICrookRegistry {
    boolean isCrookable(IItemProvider block);
    List<CrookRecipe> getDrops(IItemProvider block);
    List<CrookRecipe> getRecipeList();
    void setRecipes(List<CrookRecipe> recipes);
    void clearRecipes();
}
