package novamachina.exnihilosequentia.api.registry;

import net.minecraft.util.IItemProvider;
import novamachina.exnihilosequentia.api.crafting.compost.CompostRecipe;

import java.util.List;

public interface ICompostRegistry {
    boolean containsSolid(IItemProvider item);

    int getSolidAmount(IItemProvider item);

    void setRecipes(List<CompostRecipe> recipes);

    List<CompostRecipe> getRecipeList();

    void clearRecipes();
}
