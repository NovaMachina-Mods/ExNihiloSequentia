package novamachina.exnihilosequentia.common.crafting.crucible;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.RegistryObject;
import novamachina.exnihilosequentia.common.blockentity.crucible.CrucibleTypeEnum;
import novamachina.exnihilosequentia.common.crafting.ExNihiloRecipeSerializer;
import novamachina.exnihilosequentia.common.crafting.SingleItemInputRecipe;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CrucibleRecipe extends SingleItemInputRecipe {

  public static RecipeType<CrucibleRecipe> RECIPE_TYPE;
  @Nullable
  private static RegistryObject<ExNihiloRecipeSerializer<CrucibleRecipe>> serializer;
  private int amount;
  @Nullable
  private CrucibleTypeEnum crucibleType;
  @Nonnull
  private FluidStack resultFluid;

  public CrucibleRecipe(@Nonnull final ResourceLocation id, @Nonnull final Ingredient input,
      final int amount,
      @Nonnull final FluidStack fluid, @Nonnull final CrucibleTypeEnum crucibleType) {
    super(null, input, RECIPE_TYPE, id);
    this.amount = amount;
    this.resultFluid = fluid;
    this.crucibleType = crucibleType;
  }

  @Nullable
  public static RegistryObject<ExNihiloRecipeSerializer<CrucibleRecipe>> getStaticSerializer() {
    return serializer;
  }

  public static void setSerializer(
      @Nonnull final RegistryObject<ExNihiloRecipeSerializer<CrucibleRecipe>> serializer) {
    CrucibleRecipe.serializer = serializer;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  @Nullable
  public CrucibleTypeEnum getCrucibleType() {
    return crucibleType;
  }

  public void setCrucibleType(@Nonnull final String crucibleType) {
    this.crucibleType = CrucibleTypeEnum.getTypeByName(crucibleType);
  }

  @Override
  @Nonnull
  public ItemStack getResultItem() {
    return ItemStack.EMPTY;
  }

  @Nonnull
  public FluidStack getResultFluid() {
    return resultFluid;
  }

  public void setResultFluid(@Nonnull final FluidStack resultFluid) {
    this.resultFluid = resultFluid;
  }

  @Override
  @Nullable
  protected ExNihiloRecipeSerializer<CrucibleRecipe> getENSerializer() {
    if (serializer == null) {
      return null;
    }
    return serializer.get();
  }
}
