package novamachina.exnihilosequentia.world.item.crafting;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.fluids.FluidStack;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;
import novamachina.novacore.world.item.crafting.AbstractRecipe;
import org.checkerframework.checker.nullness.qual.NonNull;

public class TransitionRecipe extends AbstractRecipe {

  private final Ingredient catalyst;
  private final FluidStack fluidInTank;
  private final FluidStack result;

  public TransitionRecipe(Ingredient catalyst, FluidStack fluidInTank, FluidStack result) {
    this.fluidInTank = fluidInTank;
    this.catalyst = catalyst;
    this.result = result;
  }

  @Override
  public @NonNull ItemStack getToastSymbol() {
    return EXNBlocks.OAK_BARREL.itemStack();
  }

  @Override
  @NonNull
  public RecipeSerializer<?> getSerializer() {
    return EXNRecipeSerializers.TRANSITION_RECIPE_SERIALIZER.recipeSerializer();
  }

  @Override
  @NonNull
  public RecipeType<?> getType() {
    return EXNRecipeTypes.TRANSITION;
  }

  public Ingredient getCatalyst() {
    return this.catalyst;
  }

  public FluidStack getFluidInTank() {
    return this.fluidInTank;
  }

  public FluidStack getResult() {
    return this.result;
  }

  public static class Serializer implements RecipeSerializer<TransitionRecipe> {

    private static final MapCodec<TransitionRecipe> CODEC =
        RecordCodecBuilder.mapCodec(
            instance ->
                instance
                    .group(
                        Ingredient.CODEC_NONEMPTY
                            .fieldOf("catalyst")
                            .forGetter(TransitionRecipe::getCatalyst),
                        FluidStack.CODEC
                            .fieldOf("fluidInTank")
                            .forGetter(TransitionRecipe::getFluidInTank),
                        FluidStack.CODEC.fieldOf("result").forGetter(TransitionRecipe::getResult))
                    .apply(instance, TransitionRecipe::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, TransitionRecipe> STREAM_CODEC =
        StreamCodec.of(
            TransitionRecipe.Serializer::toNetwork, TransitionRecipe.Serializer::fromNetwork);

    public static TransitionRecipe fromNetwork(@NonNull RegistryFriendlyByteBuf buffer) {
      Ingredient catalyst = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
      FluidStack fluidInTank = FluidStack.STREAM_CODEC.decode(buffer);
      FluidStack result = FluidStack.STREAM_CODEC.decode(buffer);
      return new TransitionRecipe(catalyst, fluidInTank, result);
    }

    @Override
    public MapCodec<TransitionRecipe> codec() {
      return CODEC;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, TransitionRecipe> streamCodec() {
      return STREAM_CODEC;
    }

    public static void toNetwork(RegistryFriendlyByteBuf buffer, TransitionRecipe recipe) {
      Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.getCatalyst());
      FluidStack.STREAM_CODEC.encode(buffer, recipe.getFluidInTank());
      FluidStack.STREAM_CODEC.encode(buffer, recipe.getResult());
    }
  }
}
