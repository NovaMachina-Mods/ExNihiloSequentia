package novamachina.exnihilosequentia.common.registries;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.ItemStack;
import novamachina.exnihilosequentia.api.crafting.ItemStackWithChance;
import novamachina.exnihilosequentia.api.crafting.hammer.HammerRecipe;
import novamachina.exnihilosequentia.api.registry.IHammerRegistry;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HammerRegistry implements IHammerRegistry {
    @Nonnull private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    @Nonnull private static final List<HammerRecipe> recipeList = new ArrayList<>();

    @Nonnull private final Map<Block, HammerRecipe> recipeByBlockCache = new HashMap<>();

    @Override
    @Nonnull
    public List<ItemStackWithChance> getResult(@Nonnull final Block input) {
        @Nonnull final List<ItemStackWithChance> returnList = findRecipe(input).getOutput();
        logger.debug("Hammer Drop Stack: " + returnList);
        return returnList;
    }

    @Override
    public boolean isHammerable(@Nonnull final Block block) {
        return findRecipe(block) != HammerRecipe.EMPTY;
    }

    @Override
    @Nonnull
    public HammerRecipe findRecipe(@Nonnull final Block block) {
        return recipeByBlockCache.computeIfAbsent(block, k -> {
            @Nonnull final ItemStack itemStack = new ItemStack(block);
            return recipeList
                    .stream()
                    .filter(recipe -> recipe.getInput().test(itemStack))
                    .findFirst()
                    .orElse(HammerRecipe.EMPTY);
        });
    }

    @Override
    public void setRecipes(@Nonnull final List<HammerRecipe> recipes) {
        logger.debug("Hammer Registry recipes: " + recipes.size());
        recipeList.addAll(recipes);

        recipeByBlockCache.clear();
    }

    @Override
    @Nonnull
    public List<HammerRecipe> getRecipeList() {
        return recipeList;
    }

    @Override
    public void clearRecipes() {
        recipeList.clear();

        recipeByBlockCache.clear();
    }
}
