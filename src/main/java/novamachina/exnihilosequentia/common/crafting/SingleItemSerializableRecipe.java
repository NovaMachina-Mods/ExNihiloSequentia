package novamachina.exnihilosequentia.common.crafting;

import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeType;
import novamachina.novacore.world.item.crafting.SerializableRecipe;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

public abstract class SingleItemSerializableRecipe extends SerializableRecipe {

  protected Ingredient input;
  protected SingleItemSerializableRecipe(@Nullable final ItemStack outputDummy,
                                         @Nonnull final Ingredient input,
                                         @Nonnull final RecipeType<?> type,
                                         @Nonnull final ResourceLocation id) {
    super(outputDummy, type, id);
    this.input = input;
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
  public NonNullList<Ingredient> getIngredients() {
    return NonNullList.of(Ingredient.EMPTY, input);
  }
}
