package novamachina.exnihilosequentia.common.crafting.compost;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import novamachina.exnihilosequentia.common.crafting.ExNihiloRecipeSerializer;
import novamachina.exnihilosequentia.common.crafting.SingleItemSerializableRecipe;
import novamachina.exnihilosequentia.common.init.ExNihiloRecipeTypes;
import novamachina.exnihilosequentia.common.init.ExNihiloSerializers;

public class CompostRecipe extends SingleItemSerializableRecipe {
  private int amount;

  public CompostRecipe(
      @Nonnull final ResourceLocation id, @Nonnull final Ingredient input, final int amount) {
    super(null, input, ExNihiloRecipeTypes.COMPOST_RECIPE_TYPE.get(), id);
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
  @Nullable
  protected ExNihiloRecipeSerializer<CompostRecipe> getENSerializer() {
    return ExNihiloSerializers.COMPOST_RECIPE_SERIALIZER.get();
  }
}
