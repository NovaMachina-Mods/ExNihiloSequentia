package novamachina.exnihilosequentia.common.crafting.compost;

import java.util.Arrays;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import novamachina.exnihilosequentia.common.crafting.ExNihiloRecipeSerializer;
import novamachina.exnihilosequentia.common.crafting.SerializableRecipe;
import novamachina.exnihilosequentia.common.init.ExNihiloRecipeTypes;
import novamachina.exnihilosequentia.common.init.ExNihiloSerializers;

public class CompostRecipe extends SerializableRecipe {
  private int amount;
  @Nonnull private Ingredient input;

  public CompostRecipe(
      @Nonnull final ResourceLocation id, @Nonnull final Ingredient input, final int amount) {
    super(null, ExNihiloRecipeTypes.COMPOST_RECIPE_TYPE.get(), id);
    this.input = input;
    this.amount = amount;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(final int amount) {
    this.amount = amount;
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
    return ItemStack.EMPTY;
  }

  @Override
  @Nullable
  protected ExNihiloRecipeSerializer<CompostRecipe> getENSerializer() {
    return ExNihiloSerializers.COMPOST_RECIPE_SERIALIZER.get();
  }
}
