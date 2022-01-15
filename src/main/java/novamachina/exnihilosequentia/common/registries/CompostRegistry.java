package novamachina.exnihilosequentia.common.registries;

import net.minecraft.world.item.Item;
import novamachina.exnihilosequentia.api.crafting.compost.CompostRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import novamachina.exnihilosequentia.api.registry.ICompostRegistry;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompostRegistry implements ICompostRegistry {
    @Nonnull private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());
    @Nonnull public final List<CompostRecipe> recipeList = new ArrayList<>();

    @Nonnull private final Map<Item, Integer> itemSolidAmountCache = new HashMap<>();

    @Override
    public boolean containsSolid(@Nonnull final ItemLike item) {
        return getSolidAmount(item) > 0;
    }

    @Override
    public int getSolidAmount(@Nonnull final ItemLike item) {
        return itemSolidAmountCache
                .computeIfAbsent(item.asItem(), k -> {
                    @Nonnull final ItemStack itemStack = new ItemStack(item);
                    return recipeList
                            .stream()
                            .filter(compostRecipe -> compostRecipe.getInput().test(itemStack))
                            .findFirst()
                            .map(CompostRecipe::getAmount)
                            .orElse(0);
                });
    }

    @Override
    public void setRecipes(@Nonnull final List<CompostRecipe> recipes) {
        logger.debug("Compost Registry recipes: " + recipes.size());
        recipeList.addAll(recipes);

        itemSolidAmountCache.clear();
    }

    @Override
    @Nonnull
    public List<CompostRecipe> getRecipeList() {
        return recipeList;
    }

    @Override
    public void clearRecipes() {
        recipeList.clear();

        itemSolidAmountCache.clear();
    }
}
