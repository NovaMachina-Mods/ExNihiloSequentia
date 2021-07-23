package novamachina.exnihilosequentia.api.registry;

import java.util.List;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import novamachina.exnihilosequentia.api.crafting.crook.CrookRecipe;

public interface ICrookRegistry {
    void clearRecipes();

    List<CrookRecipe> getDrops(ItemLike block);

    List<CrookRecipe> getRecipeList();

    boolean isCrookable(ItemLike block);

    void setRecipes(List<CrookRecipe> recipes);
}
