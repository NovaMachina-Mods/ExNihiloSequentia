package novamachina.exnihilosequentia.common.crafting.fluidtransform;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.fluids.FluidStack;
import novamachina.exnihilosequentia.common.crafting.ExNihiloRecipeSerializer;
import novamachina.exnihilosequentia.common.crafting.SingleItemSerializableRecipe;
import novamachina.exnihilosequentia.common.init.ExNihiloRecipeTypes;
import novamachina.exnihilosequentia.common.init.ExNihiloSerializers;

public class FluidTransformRecipe extends SingleItemSerializableRecipe {

  @Nonnull private Ingredient catalyst;
  @Nonnull private FluidStack fluidInTank;
  @Nonnull private FluidStack result;

  public FluidTransformRecipe(
      @Nonnull final ResourceLocation id,
      @Nonnull final FluidStack fluidInTank,
      @Nonnull final Ingredient catalyst,
      @Nonnull final FluidStack result) {
    super(null, catalyst, ExNihiloRecipeTypes.FLUID_TRANSFORM_RECIPE_TYPE.get(), id);
    this.fluidInTank = fluidInTank;
    this.catalyst = catalyst;
    this.result = result;
  }

  @Nonnull
  public Ingredient getCatalyst() {
    return catalyst;
  }

  public void setCatalyst(@Nonnull final Ingredient catalyst) {
    this.catalyst = catalyst;
  }

  @Nonnull
  public FluidStack getFluidInTank() {
    return fluidInTank;
  }

  public void setFluidInTank(@Nonnull final FluidStack fluidInTank) {
    this.fluidInTank = fluidInTank;
  }

  @Override
  @Nonnull
  public ItemStack getResultItem() {
    return ItemStack.EMPTY;
  }

  @Nonnull
  public FluidStack getResult() {
    return result;
  }

  public void setResult(@Nonnull final FluidStack result) {
    this.result = result;
  }

  @Override
  @Nullable
  protected ExNihiloRecipeSerializer<FluidTransformRecipe> getENSerializer() {
    return ExNihiloSerializers.FLUID_TRANSFORM_RECIPE_SERIALIZER.get();
  }
}
