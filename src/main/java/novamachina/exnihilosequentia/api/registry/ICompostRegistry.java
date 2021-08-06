package novamachina.exnihilosequentia.api.registry;

import java.util.List;
import net.minecraft.world.level.ItemLike;
import novamachina.exnihilosequentia.api.crafting.compost.CompostRecipe;

public interface ICompostRegistry {
    void clearRecipes();

    boolean containsSolid(ItemLike item);

    List<CompostRecipe> getRecipeList();

    int getSolidAmount(ItemLike item);

    void setRecipes(List<CompostRecipe> recipes);
}
