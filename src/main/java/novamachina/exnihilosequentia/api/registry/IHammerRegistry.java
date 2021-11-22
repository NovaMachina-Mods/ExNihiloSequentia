package novamachina.exnihilosequentia.api.registry;

import net.minecraft.block.Block;
import novamachina.exnihilosequentia.api.crafting.ItemStackWithChance;
import novamachina.exnihilosequentia.api.crafting.hammer.HammerRecipe;

import java.util.List;

public interface IHammerRegistry {
    void clearRecipes();

    HammerRecipe findRecipe(Block block);

    List<HammerRecipe> getRecipeList();

    List<ItemStackWithChance> getResult(Block input);

    boolean isHammerable(Block block);

    void setRecipes(List<HammerRecipe> recipes);
}
