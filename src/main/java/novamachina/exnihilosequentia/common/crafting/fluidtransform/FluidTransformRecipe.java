package novamachina.exnihilosequentia.common.crafting.fluidtransform;

import javax.annotation.Nonnull;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.fluids.FluidStack;
import novamachina.exnihilosequentia.common.crafting.SingleItemSerializableRecipe;
import novamachina.exnihilosequentia.world.item.crafting.EXNRecipeSerializers;
import novamachina.exnihilosequentia.world.item.crafting.EXNRecipeTypes;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;
import org.jetbrains.annotations.NotNull;

public class FluidTransformRecipe extends SingleItemSerializableRecipe {

  @Nonnull private Ingredient catalyst;
  @Nonnull private FluidStack fluidInTank;
  @Nonnull private FluidStack result;

  public FluidTransformRecipe(
      @Nonnull final ResourceLocation id,
      @Nonnull final FluidStack fluidInTank,
      @Nonnull final Ingredient catalyst,
      @Nonnull final FluidStack result) {
    super(null, catalyst, EXNRecipeTypes.FLUID_TRANSFORM_RECIPE_TYPE, id);
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
  public @NotNull ItemStack getToastSymbol() {
    return EXNBlocks.OAK_BARREL.itemStack();
  }

  @Override
  public RecipeSerializer<?> getSerializer() {
    return EXNRecipeSerializers.FLUID_TRANSFORM_RECIPE_SERIALIZER;
  }
}
