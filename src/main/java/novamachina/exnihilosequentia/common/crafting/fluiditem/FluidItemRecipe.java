package novamachina.exnihilosequentia.common.crafting.fluiditem;

import java.util.Arrays;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
import novamachina.exnihilosequentia.common.crafting.ExNihiloRecipeSerializer;
import novamachina.exnihilosequentia.common.crafting.SerializableRecipe;
import novamachina.exnihilosequentia.common.init.ExNihiloRecipeTypes;
import novamachina.exnihilosequentia.common.init.ExNihiloSerializers;

public class FluidItemRecipe extends SerializableRecipe {

  @Nonnull private FluidStack fluid;
  @Nonnull private Ingredient input;
  @Nonnull private ItemStack output;

  public FluidItemRecipe(
      @Nonnull final ResourceLocation id,
      @Nonnull final FluidStack fluid,
      @Nonnull final Ingredient input,
      @Nonnull final ItemStack output) {
    super(output, ExNihiloRecipeTypes.FLUID_ITEM_RECIPE_TYPE.get(), id);
    this.fluid = fluid;
    this.input = input;
    this.output = output;
  }

  @Nonnull
  public FluidStack getFluidInBarrel() {
    return fluid;
  }

  @Nonnull
  public Ingredient getInput() {
    return input;
  }

  public void setInput(@Nonnull final Ingredient input) {
    this.input = input;
  }

  @Nonnull
  public List<ItemStack> getInputs() {
    return Arrays.asList(input.getItems());
  }

  @Override
  @Nonnull
  public ItemStack getResultItem() {
    return output;
  }

  public void setFluid(@Nonnull final FluidStack fluidInBarrel) {
    this.fluid = fluidInBarrel;
  }

  public void setOutput(@Nonnull final ItemStack output) {
    this.output = output;
  }

  public boolean validInputs(@Nonnull final Fluid fluid, @Nonnull final Item input) {
    return this.fluid.getFluid().isSame(fluid) && this.input.test(new ItemStack(input));
  }

  @Override
  @Nullable
  protected ExNihiloRecipeSerializer<FluidItemRecipe> getENSerializer() {
    return ExNihiloSerializers.FLUID_ITEM_RECIPE_SERIALIZER.get();
  }
}
