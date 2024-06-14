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
import novamachina.exnihilosequentia.world.level.block.entity.CrucibleBlockEntity.CrucibleType;
import novamachina.novacore.world.item.crafting.AbstractRecipe;
import org.checkerframework.checker.nullness.qual.NonNull;

public class MeltingRecipe extends AbstractRecipe {

  private final Ingredient input;
  private final FluidStack resultFluid;
  private final CrucibleType crucibleType;

  public MeltingRecipe(Ingredient input, FluidStack fluid, CrucibleType crucibleType) {
    this.resultFluid = fluid;
    this.input = input;
    this.crucibleType = crucibleType;
  }

  @Override
  @NonNull
  public RecipeSerializer<?> getSerializer() {
    return EXNRecipeSerializers.MELTING_RECIPE_SERIALIZER.recipeSerializer();
  }

  @Override
  @NonNull
  public RecipeType<?> getType() {
    return EXNRecipeTypes.MELTING;
  }

  @Override
  @NonNull
  public ItemStack getToastSymbol() {
    return EXNBlocks.FIRED_CRUCIBLE.itemStack();
  }

  public Ingredient getInput() {
    return this.input;
  }

  public FluidStack getResultFluid() {
    return this.resultFluid;
  }

  public CrucibleType getCrucibleType() {
    return this.crucibleType;
  }

  public static class Serializer implements RecipeSerializer<MeltingRecipe> {
    public static final MapCodec<MeltingRecipe> CODEC =
        RecordCodecBuilder.mapCodec(
            instance ->
                instance
                    .group(
                        Ingredient.CODEC_NONEMPTY
                            .fieldOf("input")
                            .forGetter(MeltingRecipe::getInput),
                        FluidStack.CODEC
                            .fieldOf("fluidResult")
                            .forGetter(MeltingRecipe::getResultFluid),
                        CrucibleType.CODEC
                            .fieldOf("crucibleType")
                            .forGetter(MeltingRecipe::getCrucibleType))
                    .apply(instance, MeltingRecipe::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, MeltingRecipe> STREAM_CODEC =
        StreamCodec.of(MeltingRecipe.Serializer::toNetwork, MeltingRecipe.Serializer::fromNetwork);

    @Override
    public MapCodec<MeltingRecipe> codec() {
      return CODEC;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, MeltingRecipe> streamCodec() {
      return STREAM_CODEC;
    }

    public static MeltingRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
      Ingredient input = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
      FluidStack fluid = FluidStack.STREAM_CODEC.decode(buffer);
      CrucibleType type = buffer.readEnum(CrucibleType.class);
      return new MeltingRecipe(input, fluid, type);
    }

    public static void toNetwork(@NonNull RegistryFriendlyByteBuf buffer, MeltingRecipe recipe) {
      Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.getInput());
      FluidStack.STREAM_CODEC.encode(buffer, recipe.getResultFluid());
      buffer.writeEnum(recipe.crucibleType);
    }
  }
}
