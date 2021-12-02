package novamachina.exnihilosequentia.api.registry;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import novamachina.exnihilosequentia.api.crafting.crucible.CrucibleRecipe;

import java.util.List;

public interface ICrucibleRegistry {
    void clearRecipes();

    CrucibleRecipe findRecipe(ItemLike item);

    CrucibleRecipe findRecipeByItemStack(ItemStack itemStack);

    CrucibleRecipe findRecipeByItem(Item item);

    List<CrucibleRecipe> getRecipeList();

    boolean isMeltable(ItemLike item, int level);

    boolean isMeltableByItem(Item item, int level);

    boolean isMeltableByItemStack(ItemStack item, int level);

    void setRecipes(List<CrucibleRecipe> recipes);
}
