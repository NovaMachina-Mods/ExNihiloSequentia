package novamachina.exnihilosequentia.common.registries;

import net.minecraft.item.Item;
import net.minecraft.util.IItemProvider;
import novamachina.exnihilosequentia.api.crafting.crucible.CrucibleRecipe;
import net.minecraft.item.ItemStack;
import novamachina.exnihilosequentia.api.registry.ICrucibleRegistry;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CrucibleRegistry implements ICrucibleRegistry {
    @Nonnull private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    @Nonnull private final List<CrucibleRecipe> recipeList = new ArrayList<>();

    @Nonnull private final Map<Item, CrucibleRecipe> recipeByItemCache = new HashMap<>();

    @Override
    @Nonnull
    public List<CrucibleRecipe> getRecipeList() {
        return recipeList;
    }

    @Override
    public void setRecipes(@Nonnull final List<CrucibleRecipe> recipes) {
        logger.debug("Crucible Registry recipes: " + recipes.size());
        recipeList.addAll(recipes);
    }

    @Nonnull
    @Override
    public CrucibleRecipe findRecipe(@Nonnull final IItemProvider item) {
        return findRecipeByItem(item.asItem());
    }

    @Nonnull
    @Override
    public CrucibleRecipe findRecipeByItemStack(@Nonnull final ItemStack itemStack) {
        return recipeByItemCache.computeIfAbsent(itemStack.getItem(), k ->
                recipeList
                        .stream()
                        .filter(recipe -> recipe.getInput().test(itemStack))
                        .findFirst()
                        .orElse(null));
    }

    @Nonnull
    @Override
    public CrucibleRecipe findRecipeByItem(@Nonnull final Item item) {
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
    public boolean isMeltable(@Nonnull final IItemProvider item, final int level) {
        return isMeltableByItem(item.asItem(), level);
    }

    public boolean isMeltableByItemStack(@Nonnull final ItemStack itemStack, final int level) {
        @Nullable final CrucibleRecipe recipe = findRecipeByItemStack(itemStack);
        if (recipe == null)
            return false;
        return recipe.getCrucibleType().getLevel() <= level;
    }

    public boolean isMeltableByItem(@Nonnull final Item item, final int level) {
        @Nullable final CrucibleRecipe recipe = findRecipeByItem(item);
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
