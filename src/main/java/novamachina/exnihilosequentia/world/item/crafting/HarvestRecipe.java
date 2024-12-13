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

public class HarvestRecipe extends AbstractRecipe {
  public static final HarvestRecipe EMPTY = new HarvestRecipe(null, List.of());
  private final Ingredient input;
  private final List<ItemStackWithChance> drops;

  public HarvestRecipe(Ingredient input, List<ItemStackWithChance> drops) {
    this.input = input;
    this.drops = drops;
  }

  @Override
  @NonNull
  public RecipeSerializer<HarvestRecipe> getSerializer() {
    return EXNRecipeSerializers.HARVEST_RECIPE_SERIALIZER.recipeSerializer();
  }

  @Override
  @NonNull
  public RecipeType<HarvestRecipe> getType() {
    return EXNRecipeTypes.HARVEST;
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

  public static class Serializer implements RecipeSerializer<HarvestRecipe> {

    public static final MapCodec<HarvestRecipe> CODEC =
        RecordCodecBuilder.mapCodec(
            instance ->
                instance
                    .group(
                        Ingredient.CODEC.fieldOf("input").forGetter(HarvestRecipe::getInput),
                        Codec.list(ItemStackWithChance.CODEC)
                            .fieldOf("results")
                            .forGetter(HarvestRecipe::getDrops))
                    .apply(instance, HarvestRecipe::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, HarvestRecipe> STREAM_CODEC =
        StreamCodec.of(HarvestRecipe.Serializer::toNetwork, HarvestRecipe.Serializer::fromNetwork);

    @Override
    public MapCodec<HarvestRecipe> codec() {
      return CODEC;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, HarvestRecipe> streamCodec() {
      return STREAM_CODEC;
    }

    @Nonnull
    public static HarvestRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
      final int outputCount = buffer.readInt();
      @Nonnull final List<ItemStackWithChance> output = new ArrayList<>(outputCount);
      for (int i = 0; i < outputCount; i++) {
        output.add(ItemStackWithChance.read(buffer));
      }
      @Nonnull final Ingredient input = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
      return new HarvestRecipe(input, output);
    }

    public static void toNetwork(RegistryFriendlyByteBuf buffer, HarvestRecipe recipe) {
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
