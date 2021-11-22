package novamachina.exnihilosequentia.api.registry;

import java.util.List;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import novamachina.exnihilosequentia.api.crafting.crucible.CrucibleRecipe;

public interface ICrucibleRegistry {
    void clearRecipes();

    CrucibleRecipe findRecipe(IItemProvider item);

    CrucibleRecipe findRecipeByItemStack(ItemStack itemStack);

    CrucibleRecipe findRecipeByItem(Item item);

    List<CrucibleRecipe> getRecipeList();

    boolean isMeltable(IItemProvider item, int level);

    boolean isMeltableByItem(Item item, int level);

    boolean isMeltableByItemStack(ItemStack item, int level);

    void setRecipes(List<CrucibleRecipe> recipes);
}
