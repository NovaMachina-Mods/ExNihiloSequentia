package novamachina.exnihilosequentia.common.crafting.fluiditem;

import java.util.Arrays;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.RegistryObject;
import novamachina.exnihilosequentia.common.crafting.ExNihiloRecipeSerializer;
import novamachina.exnihilosequentia.common.crafting.SerializableRecipe;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

public class FluidItemRecipe extends SerializableRecipe {

  public static RecipeType<FluidItemRecipe> RECIPE_TYPE;
  @Nullable
  private static RegistryObject<ExNihiloRecipeSerializer<FluidItemRecipe>> serializer;
  @Nonnull
  private FluidStack fluid;
  @Nonnull
  private Ingredient input;
  @Nonnull
  private ItemStack output;

  public FluidItemRecipe(@Nonnull final ResourceLocation id, @Nonnull final FluidStack fluid,
      @Nonnull final Ingredient input, @Nonnull final ItemStack output) {
    super(output, RECIPE_TYPE, id);
    this.fluid = fluid;
    this.input = input;
    this.output = output;
  }

  @Nullable
  public static RegistryObject<ExNihiloRecipeSerializer<FluidItemRecipe>> getStaticSerializer() {
    return serializer;
  }

  public static void setSerializer(
      @Nonnull final RegistryObject<ExNihiloRecipeSerializer<FluidItemRecipe>> serializer) {
    FluidItemRecipe.serializer = serializer;
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
    if (serializer == null) {
      return null;
    }
    return serializer.get();
  }
}
