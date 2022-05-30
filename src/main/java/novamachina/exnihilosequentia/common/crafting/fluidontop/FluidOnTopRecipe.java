package novamachina.exnihilosequentia.common.crafting.fluidontop;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.RegistryObject;
import novamachina.exnihilosequentia.common.crafting.ExNihiloRecipeSerializer;
import novamachina.exnihilosequentia.common.crafting.SerializableRecipe;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

public class FluidOnTopRecipe extends SerializableRecipe {

  public static RecipeType<FluidOnTopRecipe> RECIPE_TYPE;
  @Nonnull
  private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());
  @Nullable
  private static RegistryObject<ExNihiloRecipeSerializer<FluidOnTopRecipe>> serializer;
  @Nonnull
  private FluidStack fluidInTank;
  @Nonnull
  private FluidStack fluidOnTop;
  @Nonnull
  private ItemStack result;

  public FluidOnTopRecipe(@Nonnull final ResourceLocation id, @Nonnull final FluidStack fluidInTank,
      @Nonnull final FluidStack fluidOnTop, @Nonnull final ItemStack result) {
    super(result, RECIPE_TYPE, id);
    this.fluidInTank = fluidInTank;
    this.fluidOnTop = fluidOnTop;
    this.result = result;
  }

  @Nullable
  public static RegistryObject<ExNihiloRecipeSerializer<FluidOnTopRecipe>> getStaticSerializer() {
    return serializer;
  }

  public static void setSerializer(
      @Nonnull final RegistryObject<ExNihiloRecipeSerializer<FluidOnTopRecipe>> serializer) {
    FluidOnTopRecipe.serializer = serializer;
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
    return this.fluidInTank.getFluid().isSame(fluidInTank) && this.fluidOnTop.getFluid()
        .isSame(fluidOnTop);
  }

  @Override
  @Nullable
  protected ExNihiloRecipeSerializer<FluidOnTopRecipe> getENSerializer() {
    if (serializer == null) {
      return null;
    }
    return serializer.get();
  }
}