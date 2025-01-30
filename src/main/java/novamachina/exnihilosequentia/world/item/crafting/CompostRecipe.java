package novamachina.exnihilosequentia.world.item.crafting;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.PlacementInfo;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;
import novamachina.novacore.world.item.crafting.AbstractRecipe;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.List;
import java.util.Optional;

public class CompostRecipe extends AbstractRecipe {
  private final Ingredient input;
  private final int amount;

  public CompostRecipe(Ingredient input, int amount) {
    this.input = input;
    this.amount = amount;
  }

  @Override
  @NonNull
  public RecipeSerializer<CompostRecipe> getSerializer() {
    return EXNRecipeSerializers.COMPOST_RECIPE_SERIALIZER.recipeSerializer();
  }

  @Override
  @NonNull
  public RecipeType<CompostRecipe> getType() {
    return EXNRecipeTypes.COMPOST;
  }

  public Ingredient getInput() {
    return this.input;
  }

  public int getAmount() {
    return this.amount;
  }

  public static class Serializer implements RecipeSerializer<CompostRecipe> {

    public static final MapCodec<CompostRecipe> CODEC =
        RecordCodecBuilder.mapCodec(
            instance ->
                instance
                    .group(
                        Ingredient.CODEC
                            .fieldOf("input")
                            .forGetter(CompostRecipe::getInput),
                        Codec.INT.fieldOf("amount").forGetter(CompostRecipe::getAmount))
                    .apply(instance, CompostRecipe::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, CompostRecipe> STREAM_CODEC =
        StreamCodec.of(CompostRecipe.Serializer::toNetwork, CompostRecipe.Serializer::fromNetwork);

    @Override
    public MapCodec<CompostRecipe> codec() {
      return CODEC;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, CompostRecipe> streamCodec() {
      return STREAM_CODEC;
    }

    public static CompostRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
      Ingredient input = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
      int amount = buffer.readInt();
      return new CompostRecipe(input, amount);
    }

    public static void toNetwork(RegistryFriendlyByteBuf buffer, CompostRecipe recipe) {
      Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.getInput());
      buffer.writeInt(recipe.getAmount());
    }
  }
  @Override
  public PlacementInfo placementInfo() {
    return PlacementInfo.createFromOptionals(List.of(Optional.of(input)));
  }
}
