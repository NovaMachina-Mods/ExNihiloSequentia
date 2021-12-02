package novamachina.exnihilosequentia.common.registries;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.material.Fluid;
import novamachina.exnihilosequentia.api.crafting.fluiditem.FluidItemRecipe;
import novamachina.exnihilosequentia.api.registry.IFluidItemTransformRegistry;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FluidItemTransformRegistry implements IFluidItemTransformRegistry {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    private final List<FluidItemRecipe> recipeList = new ArrayList<>();

    private final Item empty = ItemStack.EMPTY.getItem();
    private final Map<FluidStack, Map<Item, Item>> itemResultCache = new HashMap<>();

    @Override
    public boolean isValidRecipe(Fluid fluid, Item input) {
        return getResult(fluid, input) != empty;
    }

    @Override
    public ItemLike getResult(Fluid fluid, Item input) {
        return itemResultCache
                .computeIfAbsent(new FluidStack(fluid, FluidAttributes.BUCKET_VOLUME), k -> new HashMap<>())
                .computeIfAbsent(input, k -> recipeList
                        .stream()
                        .filter(fluidItemRecipe -> fluidItemRecipe.validInputs(fluid, input))
                        .findFirst()
                        .map(FluidItemRecipe::getResultItem)
                        .map(ItemStack::getItem)
                        .orElse(empty));
    }

    @Override
    public List<FluidItemRecipe> getRecipeList() {
        return recipeList;
    }

    @Override
    public void setRecipes(List<FluidItemRecipe> recipes) {
        logger.debug("Fluid Item Transform Registry recipes: " + recipes.size());
        recipeList.addAll(recipes);

        itemResultCache.clear();
    }

    @Override
    public void clearRecipes() {
        recipeList.clear();

        itemResultCache.clear();
    }
}
