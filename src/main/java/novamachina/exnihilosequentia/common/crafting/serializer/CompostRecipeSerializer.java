package novamachina.exnihilosequentia.common.crafting.serializer;

import com.google.gson.JsonObject;
import javax.annotation.Nonnull;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import novamachina.exnihilosequentia.common.crafting.compost.CompostRecipe;
import novamachina.novacore.world.item.crafting.NovaRecipeSerializer;

public class CompostRecipeSerializer extends NovaRecipeSerializer<CompostRecipe> {

  @Override
  @Nonnull
  public CompostRecipe fromNetwork(
      @Nonnull final ResourceLocation recipeId, @Nonnull final FriendlyByteBuf buffer) {
    @Nonnull final Ingredient input = Ingredient.fromNetwork(buffer);
    final int amount = buffer.readInt();
    return new CompostRecipe(recipeId, input, amount);
  }

  @Override
  public void toNetwork(
      @Nonnull final FriendlyByteBuf buffer, @Nonnull final CompostRecipe recipe) {
    recipe.getInput().toNetwork(buffer);
    buffer.writeInt(recipe.getAmount());
  }

  @Override
  @Nonnull
  protected CompostRecipe readFromJson(
      @Nonnull final ResourceLocation recipeId, @Nonnull final JsonObject json) {
    @Nonnull final Ingredient input = Ingredient.fromJson(json.get("input"));
    final int amount = json.get("amount").getAsInt();
    return new CompostRecipe(recipeId, input, amount);
  }
}
