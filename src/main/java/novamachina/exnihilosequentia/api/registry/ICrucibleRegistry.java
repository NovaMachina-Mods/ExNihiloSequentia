package novamachina.exnihilosequentia.api.registry;

import net.minecraft.world.level.ItemLike;
import novamachina.exnihilosequentia.api.crafting.crucible.CrucibleRecipe;

import java.util.List;

public interface ICrucibleRegistry {
    void clearRecipes();

    CrucibleRecipe findRecipe(ItemLike item);

    List<CrucibleRecipe> getRecipeList();

    boolean isMeltable(ItemLike item, int level);

    void setRecipes(List<CrucibleRecipe> recipes);
}
