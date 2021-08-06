package novamachina.exnihilosequentia.common.registries;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.ItemStack;
import novamachina.exnihilosequentia.api.crafting.ItemStackWithChance;
import novamachina.exnihilosequentia.api.crafting.hammer.HammerRecipe;
import novamachina.exnihilosequentia.api.registry.IHammerRegistry;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
import java.util.List;

public class HammerRegistry implements IHammerRegistry {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    private static final List<HammerRecipe> recipeList = new ArrayList<>();

    @Override
    public List<ItemStackWithChance> getResult(Block input) {
        List<ItemStackWithChance> returnList = findRecipe(input).getOutput();
        logger.debug("Hammer Drop Stack: " + returnList);
        return returnList;
    }

    @Override
    public boolean isHammerable(Block block) {

        return findRecipe(block) != HammerRecipe.EMPTY;
    }

    @Override
    public HammerRecipe findRecipe(Block block) {
        for (HammerRecipe recipe : recipeList) {
            if (recipe.getInput().test(new ItemStack(block))) {
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
