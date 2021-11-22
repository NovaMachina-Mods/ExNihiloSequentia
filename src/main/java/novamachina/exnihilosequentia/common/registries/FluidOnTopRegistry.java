package novamachina.exnihilosequentia.common.registries;

import net.minecraft.item.Item;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import novamachina.exnihilosequentia.api.crafting.fluiditem.FluidItemRecipe;
import novamachina.exnihilosequentia.api.crafting.fluidontop.FluidOnTopRecipe;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import novamachina.exnihilosequentia.api.registry.IFluidOnTopRegistry;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FluidOnTopRegistry implements IFluidOnTopRegistry {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    private List<FluidOnTopRecipe> recipeList = new ArrayList<>();

    private final Item empty = ItemStack.EMPTY.getItem();
    private final Map<FluidStack, Map<FluidStack, Item>> itemResultCache = new HashMap<>();

    @Override
    public boolean isValidRecipe(Fluid fluidInTank, Fluid fluidOnTop) {
        return getResultItem(fluidInTank, fluidOnTop) != empty;
    }

    private Item getResultItem(Fluid fluidInTank, Fluid fluidOnTop) {
        return itemResultCache
                .computeIfAbsent(new FluidStack(fluidInTank, FluidAttributes.BUCKET_VOLUME), k -> new HashMap<>())
                .computeIfAbsent(new FluidStack(fluidOnTop, FluidAttributes.BUCKET_VOLUME), k -> recipeList
                        .stream()
                        .filter(fluidOnTopRecipe -> fluidOnTopRecipe.validInputs(fluidInTank, fluidOnTop))
                        .findFirst()
                        .map(FluidOnTopRecipe::getResultItem)
                        .map(ItemStack::getItem)
                        .orElse(empty));
    }

    @Override
    public ItemStack getResult(Fluid fluidInTank, Fluid fluidOnTop) {
        return new ItemStack(getResultItem(fluidInTank, fluidOnTop));
    }

    @Override
    public List<FluidOnTopRecipe> getRecipeList() {
        return recipeList;
    }

    @Override
    public void setRecipes(List<FluidOnTopRecipe> recipes) {
        logger.debug("Fluid On Top Registry recipes: " + recipes.size());
        recipeList.addAll(recipes);
    }

    @Override
    public void clearRecipes() {
        recipeList.clear();

        itemResultCache.clear();
    }
}
