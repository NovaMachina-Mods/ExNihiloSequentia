package novamachina.exnihilosequentia.api.registry;

import java.util.List;
import net.minecraft.world.item.Item;
import novamachina.exnihilosequentia.api.crafting.compost.CompostRecipe;

public interface ICompostRegistry {
    void clearRecipes();

    boolean containsSolid(Item item);

    List<CompostRecipe> getRecipeList();

    int getSolidAmount(Item item);

    void setRecipes(List<CompostRecipe> recipes);
}
