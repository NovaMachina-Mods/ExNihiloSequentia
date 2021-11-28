package novamachina.exnihilosequentia.common.registries;

import net.minecraft.item.Item;
import novamachina.exnihilosequentia.api.crafting.compost.CompostRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import novamachina.exnihilosequentia.api.registry.ICompostRegistry;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompostRegistry implements ICompostRegistry {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());
    public final List<CompostRecipe> recipeList = new ArrayList<>();

    private final Map<Item, Integer> itemSolidAmountCache = new HashMap<>();

    @Override
    public boolean containsSolid(IItemProvider item) {
        return getSolidAmount(item) > 0;
    }

    @Override
    public int getSolidAmount(IItemProvider item) {
        return itemSolidAmountCache
                .computeIfAbsent(item.asItem(), k -> {
                    final ItemStack itemStack = new ItemStack(item);
                    return recipeList
                            .stream()
                            .filter(compostRecipe -> compostRecipe.getInput().test(itemStack))
                            .findFirst()
                            .map(CompostRecipe::getAmount)
                            .orElse(0);
                });
    }

    @Override
    public void setRecipes(List<CompostRecipe> recipes) {
        logger.debug("Compost Registry recipes: " + recipes.size());
        this.recipeList.addAll(recipes);

        itemSolidAmountCache.clear();
    }

    @Override
    public List<CompostRecipe> getRecipeList() {
        return recipeList;
    }

    @Override
    public void clearRecipes() {
        recipeList.clear();

        itemSolidAmountCache.clear();
    }
}
