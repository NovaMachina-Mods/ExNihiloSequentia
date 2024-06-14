package novamachina.exnihilosequentia.world.item.crafting;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidStack;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;
import novamachina.novacore.world.item.crafting.AbstractRecipe;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.NotNull;

public class PrecipitateRecipe extends AbstractRecipe {

  private final Ingredient input;
  private final FluidStack fluid;
  private final ItemStack output;

  public PrecipitateRecipe(FluidStack fluid, Ingredient input, ItemStack output) {
    this.input = input;
    this.fluid = fluid;
    this.output = output;
  }

  public boolean validInputs(Fluid fluid, ItemLike input) {
    return this.fluid.getFluid().isSame(fluid) && this.input.test(new ItemStack(input));
  }

  @Override
  public @NotNull ItemStack getToastSymbol() {
    return EXNBlocks.OAK_BARREL.itemStack();
  }

  @Override
  @NonNull
  public RecipeSerializer<?> getSerializer() {
    return EXNRecipeSerializers.PRECIPITATE_RECIPE_SERIALIZER.recipeSerializer();
  }

  @Override
  @NonNull
  public RecipeType<?> getType() {
    return EXNRecipeTypes.PRECIPITATE;
  }

  public Ingredient getInput() {
    return this.input;
  }

  public FluidStack getFluid() {
    return this.fluid;
  }

  public ItemStack getOutput() {
    return this.output;
  }

  public static class Serializer implements RecipeSerializer<PrecipitateRecipe> {

    public static final MapCodec<PrecipitateRecipe> CODEC =
        RecordCodecBuilder.mapCodec(
            instance ->
                instance
                    .group(
                        FluidStack.CODEC.fieldOf("fluid").forGetter(PrecipitateRecipe::getFluid),
                        Ingredient.CODEC_NONEMPTY
                            .fieldOf("input")
                            .forGetter(PrecipitateRecipe::getInput),
                        ItemStack.CODEC.fieldOf("result").forGetter(PrecipitateRecipe::getOutput))
                    .apply(instance, PrecipitateRecipe::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, PrecipitateRecipe> STREAM_CODEC =
        StreamCodec.of(
            PrecipitateRecipe.Serializer::toNetwork, PrecipitateRecipe.Serializer::fromNetwork);

    @Override
    public MapCodec<PrecipitateRecipe> codec() {
      return CODEC;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, PrecipitateRecipe> streamCodec() {
      return STREAM_CODEC;
    }

    public static PrecipitateRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
      Ingredient input = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
      FluidStack fluid = FluidStack.STREAM_CODEC.decode(buffer);
      ItemStack result = ItemStack.STREAM_CODEC.decode(buffer);
      return new PrecipitateRecipe(fluid, input, result);
    }

    public static void toNetwork(
        @NonNull RegistryFriendlyByteBuf buffer, PrecipitateRecipe recipe) {
      Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.getInput());
      FluidStack.STREAM_CODEC.encode(buffer, recipe.getFluid());
      ItemStack.STREAM_CODEC.encode(buffer, recipe.getOutput());
    }
  }
}
