package novamachina.exnihilosequentia.common.crafting.compost;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.RegistryObject;
import novamachina.exnihilosequentia.common.crafting.ExNihiloRecipeSerializer;
import novamachina.exnihilosequentia.common.crafting.SingleItemInputRecipe;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CompostRecipe extends SingleItemInputRecipe {

  public static RecipeType<CompostRecipe> RECIPE_TYPE;
  @Nullable
  private static RegistryObject<ExNihiloRecipeSerializer<CompostRecipe>> serializer;
  private int amount;

  public CompostRecipe(@Nonnull final ResourceLocation id, @Nonnull final Ingredient input,
      final int amount) {
    super(null, input, RECIPE_TYPE, id);
    this.amount = amount;
  }

  public static RegistryObject<ExNihiloRecipeSerializer<CompostRecipe>> getStaticSerializer() {
    return serializer;
  }

  public static void setSerializer(
      @Nonnull final RegistryObject<ExNihiloRecipeSerializer<CompostRecipe>> serializer) {
    CompostRecipe.serializer = serializer;
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
    if (serializer != null) {
      return serializer.get();
    }
    return null;
  }
}
