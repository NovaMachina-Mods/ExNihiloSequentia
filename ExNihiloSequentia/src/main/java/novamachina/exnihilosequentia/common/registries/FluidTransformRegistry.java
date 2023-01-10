package novamachina.exnihilosequentia.common.registries;

import com.mojang.logging.LogUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidType;
import novamachina.exnihilosequentia.common.crafting.fluidtransform.FluidTransformRecipe;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;

public class FluidTransformRegistry {

  @Nonnull private static final ExNihiloLogger logger = new ExNihiloLogger(LogUtils.getLogger());

  @Nonnull private final List<FluidTransformRecipe> recipeList = new ArrayList<>();

  @Nonnull private final Map<FluidStack, Map<ItemLike, Fluid>> fluidResultCache = new HashMap<>();

  public boolean isValidRecipe(@Nonnull final Fluid fluidInTank, @Nonnull final ItemLike catalyst) {
    return getResult(fluidInTank, catalyst) != Fluids.EMPTY;
  }

  @Nonnull
  public Fluid getResult(@Nonnull final Fluid fluidInTank, @Nonnull final ItemLike catalyst) {
    final FluidStack fluidStack = new FluidStack(fluidInTank, FluidType.BUCKET_VOLUME);
    return fluidResultCache
        .computeIfAbsent(fluidStack, k -> new HashMap<>())
        .computeIfAbsent(
            catalyst.asItem(),
            k -> {
              @Nonnull final ItemStack itemStack = new ItemStack(catalyst);
              return recipeList.stream()
                  .filter(
                      fluidTransformRecipe ->
                          fluidTransformRecipe.getFluidInTank().isFluidEqual(fluidStack))
                  .filter(
                      fluidTransformRecipe -> fluidTransformRecipe.getCatalyst().test(itemStack))
                  .findFirst()
                  .map(FluidTransformRecipe::getResult)
                  .map(FluidStack::getFluid)
                  .orElse(Fluids.EMPTY);
            });
  }

  @Nonnull
  public List<FluidTransformRecipe> getRecipeList() {
    return recipeList;
  }

  public void setRecipes(@Nonnull final List<FluidTransformRecipe> recipes) {
    logger.debug("Fluid Transform Registry recipes: " + recipes.size());
    recipeList.addAll(recipes);

    fluidResultCache.clear();
  }

  public void clearRecipes() {
    recipeList.clear();

    fluidResultCache.clear();
  }
}
