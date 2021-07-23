package novamachina.exnihilosequentia.api.registry;

import java.util.List;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistryEntry;
import novamachina.exnihilosequentia.api.crafting.heat.HeatRecipe;

public interface IHeatRegistry {
    void clearRecipes();

    int getHeatAmount(ForgeRegistryEntry<? extends ItemLike> entry);

    List<HeatRecipe> getRecipeList();

    void setRecipes(List<HeatRecipe> recipes);
}
