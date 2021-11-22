package novamachina.exnihilosequentia.api.registry;

import net.minecraft.util.IItemProvider;
import novamachina.exnihilosequentia.api.crafting.crucible.CrucibleRecipe;

import java.util.List;

public interface ICrucibleRegistry {
    void clearRecipes();

    CrucibleRecipe findRecipe(IItemProvider item);

    List<CrucibleRecipe> getRecipeList();

    boolean isMeltable(IItemProvider item, int level);

    void setRecipes(List<CrucibleRecipe> recipes);
}
