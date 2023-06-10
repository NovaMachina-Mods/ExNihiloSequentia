package novamachina.exnihilosequentia.common.crafting.fluidontop;

import com.mojang.logging.LogUtils;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;
import novamachina.novacore.world.item.crafting.NovaRecipeSerializer;
import novamachina.novacore.world.item.crafting.SerializableRecipe;
import novamachina.exnihilosequentia.world.item.crafting.EXNRecipeTypes;
import novamachina.exnihilosequentia.world.item.crafting.EXNRecipeSerializers;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.jetbrains.annotations.NotNull;

public class FluidOnTopRecipe extends SerializableRecipe {
  @Nonnull private FluidStack fluidInTank;
  @Nonnull private FluidStack fluidOnTop;
  @Nonnull private ItemStack result;

  public FluidOnTopRecipe(
      @Nonnull final ResourceLocation id,
      @Nonnull final FluidStack fluidInTank,
      @Nonnull final FluidStack fluidOnTop,
      @Nonnull final ItemStack result) {
    super(result, EXNRecipeTypes.FLUID_ON_TOP_RECIPE_TYPE, id);
    this.fluidInTank = fluidInTank;
    this.fluidOnTop = fluidOnTop;
    this.result = result;
  }

  @Nonnull
  public FluidStack getFluidInTank() {
    return fluidInTank;
  }

  public void setFluidInTank(@Nonnull final FluidStack fluidInTank) {
    this.fluidInTank = fluidInTank;
  }

  @Nonnull
  public FluidStack getFluidOnTop() {
    return fluidOnTop;
  }

  public void setFluidOnTop(@Nonnull final FluidStack fluidOnTop) {
    this.fluidOnTop = fluidOnTop;
  }

  @Override
  @Nonnull
  public ItemStack getResultItem() {
    return result.copy();
  }

  public void setResult(@Nonnull final ItemStack result) {
    this.result = result;
  }

  public boolean validInputs(@Nonnull final Fluid fluidInTank, @Nonnull final Fluid fluidOnTop) {
    return this.fluidInTank.getFluid().isSame(fluidInTank)
        && this.fluidOnTop.getFluid().isSame(fluidOnTop);
  }

  @Override
  public @NotNull ItemStack getToastSymbol() {
    return EXNBlocks.OAK_SIEVE.itemStack();
  }

  @Override
  public RecipeSerializer<?> getSerializer() {
    return EXNRecipeSerializers.FLUID_ON_TOP_RECIPE_SERIALIZER;
  }
}
