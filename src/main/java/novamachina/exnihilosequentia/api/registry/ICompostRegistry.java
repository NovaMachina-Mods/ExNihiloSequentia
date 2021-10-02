package novamachina.exnihilosequentia.api.registry;

import net.minecraft.world.level.ItemLike;
import novamachina.exnihilosequentia.api.crafting.compost.CompostRecipe;

import java.util.List;

public interface ICompostRegistry {
    void clearRecipes();

    boolean containsSolid(ItemLike item);

    List<CompostRecipe> getRecipeList();

    int getSolidAmount(ItemLike item);

    void setRecipes(List<CompostRecipe> recipes);
}
