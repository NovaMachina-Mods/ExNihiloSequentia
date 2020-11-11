package novamachina.exnihilosequentia.api.registry;

import net.minecraft.util.IItemProvider;
import net.minecraftforge.registries.ForgeRegistryEntry;
import novamachina.exnihilosequentia.api.crafting.heat.HeatRecipe;

import java.util.List;

public interface IHeatRegistry {
    int getHeatAmount(ForgeRegistryEntry<? extends IItemProvider> entry);

    List<HeatRecipe> getRecipeList();

    void setRecipes(List<HeatRecipe> recipes);
}
