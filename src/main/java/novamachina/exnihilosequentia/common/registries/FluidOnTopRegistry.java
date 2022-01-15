package novamachina.exnihilosequentia.common.registries;

import net.minecraft.world.item.Item;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import novamachina.exnihilosequentia.api.crafting.fluidontop.FluidOnTopRecipe;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.item.ItemStack;
import novamachina.exnihilosequentia.api.registry.IFluidOnTopRegistry;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FluidOnTopRegistry implements IFluidOnTopRegistry {
    @Nonnull private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    @Nonnull private final List<FluidOnTopRecipe> recipeList = new ArrayList<>();

    @Nonnull private final Item empty = ItemStack.EMPTY.getItem();
    @Nonnull private final Map<FluidStack, Map<FluidStack, Item>> itemResultCache = new HashMap<>();

    @Override
    public boolean isValidRecipe(@Nonnull final Fluid fluidInTank, @Nonnull final Fluid fluidOnTop) {
        return getResultItem(fluidInTank, fluidOnTop) != empty;
    }

    @Nonnull
    private Item getResultItem(@Nonnull final Fluid fluidInTank, @Nonnull final Fluid fluidOnTop) {
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
    @Nonnull
    public ItemStack getResult(@Nonnull final Fluid fluidInTank, @Nonnull final Fluid fluidOnTop) {
        return new ItemStack(getResultItem(fluidInTank, fluidOnTop));
    }

    @Override
    @Nonnull
    public List<FluidOnTopRecipe> getRecipeList() {
        return recipeList;
    }

    @Override
    public void setRecipes(@Nonnull final List<FluidOnTopRecipe> recipes) {
        logger.debug("Fluid On Top Registry recipes: " + recipes.size());
        recipeList.addAll(recipes);

        itemResultCache.clear();
    }

    @Override
    public void clearRecipes() {
        recipeList.clear();

        itemResultCache.clear();
    }
}
