package novamachina.exnihilosequentia.common.registries;

import com.mojang.logging.LogUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidType;
import novamachina.exnihilosequentia.common.crafting.fluidontop.FluidOnTopRecipe;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;

public class FluidOnTopRegistry {

  @Nonnull
  private static final ExNihiloLogger logger = new ExNihiloLogger(LogUtils.getLogger());

  @Nonnull
  private final List<FluidOnTopRecipe> recipeList = new ArrayList<>();

  @Nonnull
  private final Item empty = ItemStack.EMPTY.getItem();
  @Nonnull
  private final Map<FluidStack, Map<FluidStack, Item>> itemResultCache = new HashMap<>();

  public boolean isValidRecipe(@Nonnull final Fluid fluidInTank, @Nonnull final Fluid fluidOnTop) {
    return getResultItem(fluidInTank, fluidOnTop) != empty;
  }

  @Nonnull
  private Item getResultItem(@Nonnull final Fluid fluidInTank, @Nonnull final Fluid fluidOnTop) {
    return itemResultCache
        .computeIfAbsent(new FluidStack(fluidInTank, FluidType.BUCKET_VOLUME),
            k -> new HashMap<>())
        .computeIfAbsent(new FluidStack(fluidOnTop, FluidType.BUCKET_VOLUME), k -> recipeList
            .stream()
            .filter(fluidOnTopRecipe -> fluidOnTopRecipe.validInputs(fluidInTank, fluidOnTop))
            .findFirst()
            .map(FluidOnTopRecipe::getResultItem)
            .map(ItemStack::getItem)
            .orElse(empty));
  }

  @Nonnull
  public ItemStack getResult(@Nonnull final Fluid fluidInTank, @Nonnull final Fluid fluidOnTop) {
    return new ItemStack(getResultItem(fluidInTank, fluidOnTop));
  }

  @Nonnull
  public List<FluidOnTopRecipe> getRecipeList() {
    return recipeList;
  }

  public void setRecipes(@Nonnull final List<FluidOnTopRecipe> recipes) {
    logger.debug("Fluid On Top Registry recipes: " + recipes.size());
    recipeList.addAll(recipes);

    itemResultCache.clear();
  }

  public void clearRecipes() {
    recipeList.clear();

    itemResultCache.clear();
  }
}
