package novamachina.exnihilosequentia.api.registry;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import novamachina.exnihilosequentia.api.crafting.ItemStackWithChance;
import novamachina.exnihilosequentia.api.crafting.hammer.HammerRecipe;

import java.util.List;

public interface IHammerRegistry {
    List<ItemStackWithChance> getResult(Block input);

    boolean isHammerable(Block block);

    HammerRecipe findRecipe(Block block);

    void setRecipes(List<HammerRecipe> recipes);

    List<HammerRecipe> getRecipeList();

    void clearRecipes();
}
