package novamachina.exnihilosequentia.common.registries;

import novamachina.exnihilosequentia.api.crafting.hammer.HammerRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import novamachina.exnihilosequentia.api.registry.IHammerRegistry;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
import java.util.List;

public class HammerRegistry implements IHammerRegistry {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    public static final List<HammerRecipe> recipeList = new ArrayList<>();

    @Override
    public ItemStack getResult(ResourceLocation input) {
        ItemStack returnStack = findRecipe(input).getRecipeOutput();
        returnStack.setCount(1);
        logger.debug("Hammer Drop Stack: " + returnStack);
        return returnStack;
    }

    @Override
    public boolean isHammerable(ResourceLocation blockID) {

        return findRecipe(blockID) != HammerRecipe.EMPTY;
    }

    @Override
    public HammerRecipe findRecipe(ResourceLocation blockID) {
        for (HammerRecipe recipe : recipeList) {
            if (recipe.getInput().getItem().getRegistryName().equals(blockID)) {
                return recipe;
            }
        }
        return HammerRecipe.EMPTY;
    }

    @Override
    public void setRecipes(List<HammerRecipe> recipes) {
        logger.debug("Hammer Registry recipes: " + recipes.size());
        recipeList.addAll(recipes);
    }

    @Override
    public List<HammerRecipe> getRecipeList() {
        return recipeList;
    }

    @Override
    public void clearRecipes() {
        recipeList.clear();
    }
}
