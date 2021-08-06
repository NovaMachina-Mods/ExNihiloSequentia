package novamachina.exnihilosequentia.api.registry;

import java.util.List;
import net.minecraft.world.level.block.Block;
import novamachina.exnihilosequentia.api.crafting.ItemStackWithChance;
import novamachina.exnihilosequentia.api.crafting.hammer.HammerRecipe;

public interface IHammerRegistry {
    void clearRecipes();

    HammerRecipe findRecipe(Block block);

    List<HammerRecipe> getRecipeList();

    List<ItemStackWithChance> getResult(Block input);

    boolean isHammerable(Block block);

    void setRecipes(List<HammerRecipe> recipes);
}
