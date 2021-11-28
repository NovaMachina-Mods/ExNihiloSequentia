package novamachina.exnihilosequentia.common.registries;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import novamachina.exnihilosequentia.api.crafting.ItemStackWithChance;
import novamachina.exnihilosequentia.api.crafting.hammer.HammerRecipe;
import novamachina.exnihilosequentia.api.registry.IHammerRegistry;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HammerRegistry implements IHammerRegistry {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    private static final List<HammerRecipe> recipeList = new ArrayList<>();

    private final Map<Block, HammerRecipe> recipeByBlockCache = new HashMap<>();

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
        return recipeByBlockCache.computeIfAbsent(block, k -> {
            final ItemStack itemStack = new ItemStack(block);
            return recipeList
                    .stream()
                    .filter(recipe -> recipe.getInput().test(itemStack))
                    .findFirst()
                    .orElse(HammerRecipe.EMPTY);
        });
    }

    @Override
    public void setRecipes(List<HammerRecipe> recipes) {
        logger.debug("Hammer Registry recipes: " + recipes.size());
        recipeList.addAll(recipes);

        recipeByBlockCache.clear();
    }

    @Override
    public List<HammerRecipe> getRecipeList() {
        return recipeList;
    }

    @Override
    public void clearRecipes() {
        recipeList.clear();

        recipeByBlockCache.clear();
    }
}
