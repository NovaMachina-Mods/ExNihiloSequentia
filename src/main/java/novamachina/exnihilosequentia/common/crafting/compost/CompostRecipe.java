package novamachina.exnihilosequentia.common.crafting.compost;

import javax.annotation.Nonnull;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import novamachina.exnihilosequentia.common.crafting.SingleItemSerializableRecipe;
import novamachina.exnihilosequentia.world.item.crafting.EXNRecipeSerializers;
import novamachina.exnihilosequentia.world.item.crafting.EXNRecipeTypes;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;
import org.jetbrains.annotations.NotNull;

public class CompostRecipe extends SingleItemSerializableRecipe {
  private int amount;

  public CompostRecipe(
      @Nonnull final ResourceLocation id, @Nonnull final Ingredient input, final int amount) {
    super(null, input, EXNRecipeTypes.COMPOST_RECIPE_TYPE, id);
    this.amount = amount;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(final int amount) {
    this.amount = amount;
  }

  @Override
  @Nonnull
  public ItemStack getResultItem() {
    return ItemStack.EMPTY;
  }

  @Override
  public @NotNull ItemStack getToastSymbol() {
    return EXNBlocks.OAK_BARREL.itemStack();
  }

  @Override
  public RecipeSerializer<?> getSerializer() {
    return EXNRecipeSerializers.COMPOST_RECIPE_SERIALIZER;
  }
}
