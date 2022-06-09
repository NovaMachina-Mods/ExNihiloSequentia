package novamachina.exnihilosequentia.common.crafting.fluidontop;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
import novamachina.exnihilosequentia.common.crafting.ExNihiloRecipeSerializer;
import novamachina.exnihilosequentia.common.crafting.SerializableRecipe;
import novamachina.exnihilosequentia.common.init.ExNihiloRecipeTypes;
import novamachina.exnihilosequentia.common.init.ExNihiloSerializers;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

public class FluidOnTopRecipe extends SerializableRecipe {

  @Nonnull private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());
  @Nonnull private FluidStack fluidInTank;
  @Nonnull private FluidStack fluidOnTop;
  @Nonnull private ItemStack result;

  public FluidOnTopRecipe(
      @Nonnull final ResourceLocation id,
      @Nonnull final FluidStack fluidInTank,
      @Nonnull final FluidStack fluidOnTop,
      @Nonnull final ItemStack result) {
    super(result, ExNihiloRecipeTypes.FLUID_ON_TOP_RECIPE_TYPE.get(), id);
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
  @Nullable
  protected ExNihiloRecipeSerializer<FluidOnTopRecipe> getENSerializer() {
    return ExNihiloSerializers.FLUID_ON_TOP_RECIPE_SERIALIZER.get();
  }
}
