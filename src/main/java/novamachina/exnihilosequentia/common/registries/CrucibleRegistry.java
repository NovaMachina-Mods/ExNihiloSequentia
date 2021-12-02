package novamachina.exnihilosequentia.common.registries;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import novamachina.exnihilosequentia.api.crafting.crucible.CrucibleRecipe;
import novamachina.exnihilosequentia.api.registry.ICrucibleRegistry;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Map;

public class CrucibleRegistry implements ICrucibleRegistry {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    private final List<CrucibleRecipe> recipeList = new ArrayList<>();

    private final Map<Item, CrucibleRecipe> recipeByItemCache = new HashMap<>();

    @Override
    public List<CrucibleRecipe> getRecipeList() {
        return recipeList;
    }

    @Override
    public void setRecipes(List<CrucibleRecipe> recipes) {
        logger.debug("Crucible Registry recipes: " + recipes.size());
        recipeList.addAll(recipes);
    }

    @Override
    public CrucibleRecipe findRecipe(ItemLike item) {
        return findRecipeByItem(item.asItem());
    }

    @Override
    public CrucibleRecipe findRecipeByItemStack(ItemStack itemStack) {
        return recipeByItemCache.computeIfAbsent(itemStack.getItem(), k ->
                recipeList
                        .stream()
                        .filter(recipe -> recipe.getInput().test(itemStack))
                        .findFirst()
                        .orElse(null));
    }

    @Override
    public CrucibleRecipe findRecipeByItem(Item item) {
        return recipeByItemCache.computeIfAbsent(item, k -> {
            final ItemStack itemStack = new ItemStack(item);
            return recipeList
                    .stream()
                    .filter(recipe -> recipe.getInput().test(itemStack))
                    .findFirst()
                    .orElse(null);
        });
    }

    @Override
    public boolean isMeltable(ItemLike item, int level) {
        return isMeltableByItem(item.asItem(), level);
    }

    public boolean isMeltableByItemStack(ItemStack itemStack, int level) {
        final CrucibleRecipe recipe = findRecipeByItemStack(itemStack);
        if (recipe == null)
            return false;
        return recipe.getCrucibleType().getLevel() <= level;
    }

    public boolean isMeltableByItem(Item item, int level) {
        final CrucibleRecipe recipe = findRecipeByItem(item);
        if (recipe == null)
            return false;
        return recipe.getCrucibleType().getLevel() <= level;
    }

    @Override
    public void clearRecipes() {
        recipeList.clear();

        recipeByItemCache.clear();
    }
}
