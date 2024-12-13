package novamachina.exnihilosequentia.world.item.crafting;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.annotation.Nonnull;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.PlacementInfo;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import novamachina.novacore.world.item.crafting.AbstractRecipe;
import org.checkerframework.checker.nullness.qual.NonNull;

public class CrushingRecipe extends AbstractRecipe {

  public static final CrushingRecipe EMPTY = new CrushingRecipe(null, List.of());
  private final Ingredient input;
  private final List<ItemStackWithChance> drops;

  public CrushingRecipe(Ingredient input, List<ItemStackWithChance> drops) {
    this.input = input;
    this.drops = drops;
  }

  @Override
  @NonNull
  public RecipeSerializer<CrushingRecipe> getSerializer() {
    return EXNRecipeSerializers.CRUSHING_RECIPE_SERIALIZER.recipeSerializer();
  }

  @Override
  @NonNull
  public RecipeType<CrushingRecipe> getType() {
    return EXNRecipeTypes.CRUSHING;
  }

  @NonNull
  public List<ItemStack> getOutputsWithoutChance() {
    List<ItemStack> returnList = new ArrayList<>();
    drops.forEach(stack -> returnList.add(stack.getStack()));
    return returnList;
  }

  public Ingredient getInput() {
    return this.input;
  }

  public List<ItemStackWithChance> getDrops() {
    return this.drops;
  }

  public static class Serializer implements RecipeSerializer<CrushingRecipe> {

    public static final MapCodec<CrushingRecipe> CODEC =
        RecordCodecBuilder.mapCodec(
            instance ->
                instance
                    .group(
                        Ingredient.CODEC
                            .fieldOf("input")
                            .forGetter(CrushingRecipe::getInput),
                        Codec.list(ItemStackWithChance.CODEC)
                            .fieldOf("results")
                            .forGetter(CrushingRecipe::getDrops))
                    .apply(instance, CrushingRecipe::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, CrushingRecipe> STREAM_CODEC =
        StreamCodec.of(
            CrushingRecipe.Serializer::toNetwork, CrushingRecipe.Serializer::fromNetwork);

    @Override
    public MapCodec<CrushingRecipe> codec() {
      return CODEC;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, CrushingRecipe> streamCodec() {
      return STREAM_CODEC;
    }

    @Nonnull
    public static CrushingRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
      int outputCount = buffer.readInt();
      List<ItemStackWithChance> output = new ArrayList<>(outputCount);
      for (int i = 0; i < outputCount; i++) {
        output.add(ItemStackWithChance.read(buffer));
      }
      @Nonnull final Ingredient input = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
      return new CrushingRecipe(input, output);
    }

    public static void toNetwork(RegistryFriendlyByteBuf buffer, CrushingRecipe recipe) {
      buffer.writeInt(recipe.getDrops().size());
      recipe.getDrops().forEach(drop -> ItemStackWithChance.STREAM_CODEC.encode(buffer, drop));
      Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.getInput());
    }
  }
  @Override
  public PlacementInfo placementInfo() {
    return PlacementInfo.createFromOptionals(List.of(Optional.of(input)));
  }
}
