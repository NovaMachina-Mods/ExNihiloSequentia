package novamachina.exnihilosequentia.world.item.crafting;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidStack;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;
import novamachina.novacore.world.item.crafting.AbstractRecipe;
import org.checkerframework.checker.nullness.qual.NonNull;

public class SolidifyingRecipe extends AbstractRecipe {
  private final FluidStack fluidInTank;
  private final FluidStack fluidOnTop;
  private final ItemStack result;

  public SolidifyingRecipe(FluidStack fluidInTank, FluidStack fluidOnTop, ItemStack result) {
    this.fluidInTank = fluidInTank;
    this.fluidOnTop = fluidOnTop;
    this.result = result;
  }

  public boolean validInputs(@NonNull final Fluid fluidInTank, @NonNull final Fluid fluidOnTop) {
    return this.fluidInTank.getFluid().isSame(fluidInTank)
        && this.fluidOnTop.getFluid().isSame(fluidOnTop);
  }

  @Override
  @NonNull
  public ItemStack getToastSymbol() {
    return EXNBlocks.OAK_SIEVE.itemStack();
  }

  @Override
  @NonNull
  public RecipeSerializer<?> getSerializer() {
    return EXNRecipeSerializers.SOLIDIFYING_RECIPE_SERIALIZER.recipeSerializer();
  }

  @Override
  @NonNull
  public RecipeType<?> getType() {
    return EXNRecipeTypes.SOLIDIFYING;
  }

  public FluidStack getFluidInTank() {
    return this.fluidInTank;
  }

  public FluidStack getFluidOnTop() {
    return this.fluidOnTop;
  }

  public ItemStack getResult() {
    return this.result;
  }

  public static class Serializer implements RecipeSerializer<SolidifyingRecipe> {

    public static final MapCodec<SolidifyingRecipe> CODEC =
        RecordCodecBuilder.mapCodec(
            instance ->
                instance
                    .group(
                        FluidStack.CODEC
                            .fieldOf("fluidInTank")
                            .forGetter(SolidifyingRecipe::getFluidInTank),
                        FluidStack.CODEC
                            .fieldOf("fluidOnTop")
                            .forGetter(SolidifyingRecipe::getFluidOnTop),
                        ItemStack.CODEC.fieldOf("result").forGetter(SolidifyingRecipe::getResult))
                    .apply(instance, SolidifyingRecipe::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, SolidifyingRecipe> STREAM_CODEC =
        StreamCodec.of(
            SolidifyingRecipe.Serializer::toNetwork, SolidifyingRecipe.Serializer::fromNetwork);

    @Override
    public MapCodec<SolidifyingRecipe> codec() {
      return CODEC;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, SolidifyingRecipe> streamCodec() {
      return STREAM_CODEC;
    }

    public static SolidifyingRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
      FluidStack fluidInTank = FluidStack.STREAM_CODEC.decode(buffer);
      FluidStack fluidOnTop = FluidStack.STREAM_CODEC.decode(buffer);
      ItemStack result = ItemStack.STREAM_CODEC.decode(buffer);
      return new SolidifyingRecipe(fluidInTank, fluidOnTop, result);
    }

    public static void toNetwork(RegistryFriendlyByteBuf buffer, SolidifyingRecipe recipe) {
      FluidStack.STREAM_CODEC.encode(buffer, recipe.getFluidInTank());
      FluidStack.STREAM_CODEC.encode(buffer, recipe.getFluidOnTop());
      ItemStack.STREAM_CODEC.encode(buffer, recipe.getResult());
    }
  }
}
