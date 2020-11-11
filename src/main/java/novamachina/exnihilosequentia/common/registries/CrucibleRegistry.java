package novamachina.exnihilosequentia.common.registries;

import novamachina.exnihilosequentia.api.crafting.crucible.CrucibleRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import novamachina.exnihilosequentia.api.registry.ICrucibleRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CrucibleRegistry implements ICrucibleRegistry {
    private final List<CrucibleRecipe> recipeList = new ArrayList<>();

    @Override
    public List<CrucibleRecipe> getRecipeList() {
        return recipeList;
    }

    @Override
    public void setRecipes(List<CrucibleRecipe> recipes) {
        recipeList.addAll(recipes);
    }

    @Override
    public CrucibleRecipe findRecipe(IItemProvider item) {
        return recipeList.stream().filter(recipe -> recipe.getInput().test(new ItemStack(item))).findFirst().get();
    }

    @Override
    public boolean isMeltable(IItemProvider item, int level) {
        return recipeList.stream().anyMatch(recipe -> recipe.getInput().test(new ItemStack(item)) && recipe.getCrucibleType().getLevel() >= level);
    }
}
