package novamachina.exnihilosequentia.api.registry;

import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistryEntry;
import novamachina.exnihilosequentia.api.crafting.heat.HeatRecipe;

import java.util.List;

public interface IHeatRegistry {
    void clearRecipes();

    int getHeatAmount(ForgeRegistryEntry<? extends ItemLike> entry);

    List<HeatRecipe> getRecipeList();

    void setRecipes(List<HeatRecipe> recipes);
}
