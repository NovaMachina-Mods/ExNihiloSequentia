package novamachina.exnihilosequentia.common.registries;

import novamachina.exnihilosequentia.api.crafting.heat.HeatRecipe;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;
import novamachina.exnihilosequentia.api.registry.IHeatRegistry;
import novamachina.exnihilosequentia.common.utility.LogUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HeatRegistry implements IHeatRegistry {

    private List<HeatRecipe> recipeList = new ArrayList<>();

    @Override
    public int getHeatAmount(ForgeRegistryEntry<? extends IItemProvider> entry) {
        return recipeList.stream().filter(recipe -> recipe.getInput().getRegistryName().equals(entry.getRegistryName())).findFirst().map(HeatRecipe::getAmount).orElse(0);
    }

    @Override
    public List<HeatRecipe> getRecipeList() {
        return recipeList;
    }

    @Override
    public void setRecipes(List<HeatRecipe> recipes) {
        LogUtil.debug("Heat Registry recipes: " + recipes.size());
        this.recipeList.addAll(recipes);
    }
}
