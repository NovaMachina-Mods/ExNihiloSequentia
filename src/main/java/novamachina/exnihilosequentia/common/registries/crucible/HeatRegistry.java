package novamachina.exnihilosequentia.common.registries.crucible;

import novamachina.exnihilosequentia.common.api.crafting.heat.HeatRecipe;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HeatRegistry {

    private List<HeatRecipe> recipeList = new ArrayList<>();

    public int getHeatAmount(ForgeRegistryEntry<? extends IItemProvider> entry) {
        return recipeList.stream().filter(recipe -> recipe.getInput().getRegistryName().equals(entry.getRegistryName())).findFirst().map(HeatRecipe::getAmount).orElse(0);
    }

    public List<HeatRecipe> getRecipeList() {
        return recipeList;
    }

    public void setRecipes(Map<ResourceLocation, HeatRecipe> recipes) {
        this.recipeList.addAll(recipes.values());
    }
}
