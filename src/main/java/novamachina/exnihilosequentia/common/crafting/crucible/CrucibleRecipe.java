package novamachina.exnihilosequentia.common.crafting.crucible;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.fluids.FluidStack;
import novamachina.exnihilosequentia.common.blockentity.crucible.CrucibleTypeEnum;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;
import novamachina.novacore.world.item.crafting.NovaRecipeSerializer;
import novamachina.exnihilosequentia.common.crafting.SingleItemSerializableRecipe;
import novamachina.exnihilosequentia.world.item.crafting.EXNRecipeTypes;
import novamachina.exnihilosequentia.world.item.crafting.EXNRecipeSerializers;
import org.jetbrains.annotations.NotNull;

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
    super(null, input, EXNRecipeTypes.CRUCIBLE_RECIPE_TYPE, id);
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

  @Override
  public RecipeSerializer<?> getSerializer() {
    return EXNRecipeSerializers.CRUCIBLE_RECIPE_SERIALIZER;
  }

  @Nonnull
  public FluidStack getResultFluid() {
    return resultFluid;
  }

  public void setResultFluid(@Nonnull final FluidStack resultFluid) {
    this.resultFluid = resultFluid;
  }

  @Override
  @NotNull
  public ItemStack getToastSymbol() {
    return EXNBlocks.FIRED_CRUCIBLE.itemStack();
  }
}
