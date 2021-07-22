package novamachina.exnihilosequentia.api.registry;

import java.util.List;

import net.minecraft.world.item.Item;
import novamachina.exnihilosequentia.api.crafting.crucible.CrucibleRecipe;

public interface ICrucibleRegistry {
    void clearRecipes();

    CrucibleRecipe findRecipe(Item item);

    List<CrucibleRecipe> getRecipeList();

    boolean isMeltable(Item item, int level);

    void setRecipes(List<CrucibleRecipe> recipes);
}
