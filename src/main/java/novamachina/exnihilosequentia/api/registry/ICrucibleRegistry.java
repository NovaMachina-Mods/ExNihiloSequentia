package novamachina.exnihilosequentia.api.registry;

import net.minecraft.util.IItemProvider;
import novamachina.exnihilosequentia.api.crafting.crucible.CrucibleRecipe;

import java.util.List;

public interface ICrucibleRegistry {
    List<CrucibleRecipe> getRecipeList();

    void setRecipes(List<CrucibleRecipe> recipes);

    CrucibleRecipe findRecipe(IItemProvider item);

    boolean isMeltable(IItemProvider item, int level);

    void clearRecipes();
}
