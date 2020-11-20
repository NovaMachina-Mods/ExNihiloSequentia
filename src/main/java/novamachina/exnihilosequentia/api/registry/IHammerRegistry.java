package novamachina.exnihilosequentia.api.registry;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import novamachina.exnihilosequentia.api.crafting.hammer.HammerRecipe;

import java.util.List;

public interface IHammerRegistry {
    ItemStack getResult(ResourceLocation input);

    boolean isHammerable(ResourceLocation blockID);

    HammerRecipe findRecipe(ResourceLocation blockID);

    void setRecipes(List<HammerRecipe> recipes);

    List<HammerRecipe> getRecipeList();

    void clearRecipes();
}
