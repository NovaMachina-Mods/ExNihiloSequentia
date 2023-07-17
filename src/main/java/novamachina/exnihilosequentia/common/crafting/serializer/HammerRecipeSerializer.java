package novamachina.exnihilosequentia.common.crafting.serializer;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import novamachina.exnihilosequentia.common.crafting.ItemStackWithChance;
import novamachina.exnihilosequentia.common.crafting.hammer.HammerRecipe;
import novamachina.novacore.world.item.crafting.NovaRecipeSerializer;

public class HammerRecipeSerializer extends NovaRecipeSerializer<HammerRecipe> {

  @Override
  @Nonnull
  public HammerRecipe fromNetwork(
      @Nonnull final ResourceLocation recipeId, @Nonnull final FriendlyByteBuf buffer) {
    @Nonnull final Ingredient input = Ingredient.fromNetwork(buffer);
    final int outputCount = buffer.readInt();
    @Nonnull final List<ItemStackWithChance> output = new ArrayList<>(outputCount);
    for (int i = 0; i < outputCount; i++) {
      output.add(ItemStackWithChance.read(buffer));
    }

    return new HammerRecipe(recipeId, input, output);
  }

  @Override
  public void toNetwork(@Nonnull final FriendlyByteBuf buffer, @Nonnull final HammerRecipe recipe) {
    recipe.getInput().toNetwork(buffer);
    buffer.writeInt(recipe.getOutput().size());
    for (@Nonnull final ItemStackWithChance stack : recipe.getOutput()) {
      stack.write(buffer);
    }
  }

  @Override
  @Nonnull
  protected HammerRecipe readFromJson(
      @Nonnull final ResourceLocation recipeId, @Nonnull final JsonObject json) {
    @Nonnull final Ingredient input = Ingredient.fromJson(json.get("input"));
    @Nonnull final JsonArray results = json.getAsJsonArray("results");
    @Nonnull final List<ItemStackWithChance> output = new ArrayList<>(results.size());
    for (int i = 0; i < results.size(); i++) {
      output.add(ItemStackWithChance.deserialize(results.get(i)));
    }
    return new HammerRecipe(recipeId, input, output);
  }
}
