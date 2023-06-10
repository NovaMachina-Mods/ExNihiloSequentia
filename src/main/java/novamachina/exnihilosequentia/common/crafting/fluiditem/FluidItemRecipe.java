package novamachina.exnihilosequentia.common.crafting.fluiditem;

import javax.annotation.Nonnull;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
import novamachina.exnihilosequentia.common.crafting.SingleItemSerializableRecipe;
import novamachina.exnihilosequentia.world.item.crafting.EXNRecipeSerializers;
import novamachina.exnihilosequentia.world.item.crafting.EXNRecipeTypes;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;
import org.jetbrains.annotations.NotNull;

public class FluidItemRecipe extends SingleItemSerializableRecipe {

  @Nonnull private FluidStack fluid;
  @Nonnull private ItemStack output;

  public FluidItemRecipe(
      @Nonnull final ResourceLocation id,
      @Nonnull final FluidStack fluid,
      @Nonnull final Ingredient input,
      @Nonnull final ItemStack output) {
    super(output, input, EXNRecipeTypes.FLUID_ITEM_RECIPE_TYPE, id);
    this.fluid = fluid;
    this.output = output;
  }

  @Nonnull
  public FluidStack getFluidInBarrel() {
    return fluid;
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
  public @NotNull ItemStack getToastSymbol() {
    return EXNBlocks.OAK_BARREL.itemStack();
  }

  @Override
  public RecipeSerializer<?> getSerializer() {
    return EXNRecipeSerializers.FLUID_ITEM_RECIPE_SERIALIZER;
  }
}
