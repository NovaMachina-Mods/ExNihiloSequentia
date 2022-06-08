package novamachina.exnihilosequentia.common.crafting.fluidtransform;

import javax.annotation.Nonnull;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.material.Fluid;
import novamachina.exnihilosequentia.common.crafting.ExNihiloFinishedRecipe;
import novamachina.exnihilosequentia.common.init.ExNihiloSerializers;

public class FluidTransformRecipeBuilder extends
    ExNihiloFinishedRecipe<FluidTransformRecipeBuilder> {

  public FluidTransformRecipeBuilder() throws NullPointerException {
    //noinspection ConstantConditions
    super(ExNihiloSerializers.FLUID_TRANSFORM_RECIPE_SERIALIZER.get());
  }

  @Nonnull
  public static FluidTransformRecipeBuilder builder() {
    return new FluidTransformRecipeBuilder();
  }

  @Nonnull
  public FluidTransformRecipeBuilder catalyst(@Nonnull final Ingredient catalyst) {
    return this.addInput("catalyst", catalyst);
  }

  @Nonnull
  public FluidTransformRecipeBuilder fluidInTank(@Nonnull final Fluid fluid) {
    return this.addFluid("fluidInTank", fluid);
  }

  @Nonnull
  public FluidTransformRecipeBuilder result(@Nonnull final Fluid fluid) {
    return this.addFluid("result", fluid);
  }
}
