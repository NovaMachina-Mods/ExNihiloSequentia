package novamachina.exnihilosequentia.common.crafting.fluidontop;

import javax.annotation.Nonnull;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.material.Fluid;
import novamachina.exnihilosequentia.common.crafting.ExNihiloFinishedRecipe;
import novamachina.exnihilosequentia.world.item.crafting.EXNRecipeSerializers;

public class FluidOnTopRecipeBuilder extends ExNihiloFinishedRecipe<FluidOnTopRecipeBuilder> {

  private FluidOnTopRecipeBuilder() throws NullPointerException {
    //noinspection ConstantConditions
    super(EXNRecipeSerializers.FLUID_ON_TOP_RECIPE_SERIALIZER);
  }

  @Nonnull
  public static FluidOnTopRecipeBuilder builder() {
    return new FluidOnTopRecipeBuilder();
  }

  @Nonnull
  public FluidOnTopRecipeBuilder fluidInTank(@Nonnull final Fluid fluid) {
    return this.addFluid("fluidInTank", fluid);
  }

  @Nonnull
  public FluidOnTopRecipeBuilder fluidOnTop(@Nonnull final Fluid fluid) {
    return this.addFluid("fluidOnTop", fluid);
  }

  @Nonnull
  public FluidOnTopRecipeBuilder result(@Nonnull final ItemLike result) {
    return this.addResult(result);
  }
}
