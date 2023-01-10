package novamachina.exnihilosequentia.common.crafting.crucible;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.fluids.FluidStack;
import novamachina.exnihilosequentia.common.blockentity.crucible.CrucibleTypeEnum;
import novamachina.exnihilosequentia.common.crafting.ExNihiloRecipeSerializer;
import novamachina.exnihilosequentia.common.crafting.SingleItemSerializableRecipe;
import novamachina.exnihilosequentia.common.init.ExNihiloRecipeTypes;
import novamachina.exnihilosequentia.common.init.ExNihiloSerializers;

public class CrucibleRecipe extends SingleItemSerializableRecipe {

  private int amount;
  @Nullable private CrucibleTypeEnum crucibleType;
  @Nonnull private FluidStack resultFluid;

  public CrucibleRecipe(
      @Nonnull final ResourceLocation id,
      @Nonnull final Ingredient input,
      final int amount,
      @Nonnull final FluidStack fluid,
      @Nonnull final CrucibleTypeEnum crucibleType) {
    super(null, input, ExNihiloRecipeTypes.CRUCIBLE_RECIPE_TYPE.get(), id);
    this.amount = amount;
    this.resultFluid = fluid;
    this.crucibleType = crucibleType;
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
    return ExNihiloSerializers.CRUCIBLE_RECIPE_SERIALIZER.get();
  }
}
