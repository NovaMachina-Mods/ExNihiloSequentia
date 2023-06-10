package novamachina.exnihilosequentia.common.crafting.serializer;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import novamachina.novacore.world.item.crafting.NovaRecipeSerializer;
import novamachina.exnihilosequentia.common.crafting.heat.HeatRecipe;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;

public class HeatRecipeSerializer extends NovaRecipeSerializer<HeatRecipe> {

  private static final JsonParser PARSER = new JsonParser();

  @Override
  public HeatRecipe fromNetwork(
      @Nonnull final ResourceLocation recipeId, @Nonnull final FriendlyByteBuf buffer) {
    // Packet structure is:
    // |--------------------------------------|
    // | Block Resource Location (UTF String) |
    // | Heat amount (int)                    |
    // | Flag: has properties (boolean)       |
    // | [OPTIONAL] Properties (UTF JSON)     |
    // |--------------------------------------|
    @Nullable
    final Block input = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(buffer.readUtf()));
    final int amount = buffer.readInt();

    final boolean hasProperties =
        buffer.readBoolean(); // flag showing whether recipe depends on block state
    if (hasProperties) {
      @Nonnull
      final StatePropertiesPredicate properties =
          StatePropertiesPredicate.fromJson(PARSER.parse(buffer.readUtf()));
      return new HeatRecipe(recipeId, input, amount, properties);
    }
    return new HeatRecipe(recipeId, input, amount);
  }

  @Override
  public void toNetwork(@Nonnull final FriendlyByteBuf buffer, @Nonnull final HeatRecipe recipe) {
    @Nullable
    final ResourceLocation resourceLocation = ForgeRegistries.BLOCKS.getKey(recipe.getInput());
    if (resourceLocation == null) {
      return;
    }
    buffer.writeUtf(resourceLocation.toString());
    buffer.writeInt(recipe.getAmount());

    // If recipe respects block state, need to encode properties too
    StatePropertiesPredicate properties = recipe.getProperties();
    boolean hasProperties = properties != null;
    buffer.writeBoolean(
        hasProperties); // Add flag to packet specifying if block state is part of recipe
    if (hasProperties) {
      buffer.writeUtf(properties.serializeToJson().toString());
    }
  }

  @Override
  @Nonnull
  protected HeatRecipe readFromJson(
      @Nonnull final ResourceLocation recipeId, @Nonnull final JsonObject json) {
    @Nullable
    final Block input =
        ForgeRegistries.BLOCKS.getValue(new ResourceLocation(json.get("block").getAsString()));
    final int amount = json.get("amount").getAsInt();
    if (json.has("state")) {
      return new HeatRecipe(
          recipeId, input, amount, StatePropertiesPredicate.fromJson(json.get("state")));
    }
    return new HeatRecipe(recipeId, input, amount);
  }
}
