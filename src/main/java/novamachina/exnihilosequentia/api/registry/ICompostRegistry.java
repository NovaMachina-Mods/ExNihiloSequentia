package novamachina.exnihilosequentia.api.registry;

import net.minecraft.util.IItemProvider;
import novamachina.exnihilosequentia.api.crafting.compost.CompostRecipe;

import java.util.List;

public interface ICompostRegistry {
    void clearRecipes();

    boolean containsSolid(IItemProvider item);

    List<CompostRecipe> getRecipeList();

    int getSolidAmount(IItemProvider item);

    void setRecipes(List<CompostRecipe> recipes);
}
