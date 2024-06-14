package novamachina.exnihilosequentia.world.item.crafting;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.Optional;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;
import novamachina.novacore.world.item.crafting.AbstractRecipe;
import org.checkerframework.checker.nullness.qual.NonNull;

public class HeatRecipe extends AbstractRecipe {
  private final int amount;
  private final Block inputBlock;
  private final Optional<StatePropertiesPredicate> properties;

  public HeatRecipe(
      Block inputBlock, final int amount, Optional<StatePropertiesPredicate> properties) {
    this.inputBlock = inputBlock;
    this.amount = amount;
    this.properties = properties;
  }

  public boolean isMatch(@NonNull BlockState state) {
    if (inputBlock == null) {
      return false;
    }
    return state.is(inputBlock) && (properties.isEmpty() || properties.get().matches(state));
  }

  @Override
  public @NonNull ItemStack getToastSymbol() {
    return EXNBlocks.FIRED_CRUCIBLE.itemStack();
  }

  @Override
  @NonNull
  public RecipeSerializer<?> getSerializer() {
    return EXNRecipeSerializers.HEAT_RECIPE_SERIALIZER.recipeSerializer();
  }

  @Override
  @NonNull
  public RecipeType<?> getType() {
    return EXNRecipeTypes.HEAT;
  }

  public int getAmount() {
    return this.amount;
  }

  public Block getInputBlock() {
    return this.inputBlock;
  }

  public Optional<StatePropertiesPredicate> getProperties() {
    return this.properties;
  }

  public static class Serializer implements RecipeSerializer<HeatRecipe> {

    public static final MapCodec<HeatRecipe> CODEC =
        RecordCodecBuilder.mapCodec(
            instance ->
                instance
                    .group(
                        BuiltInRegistries.BLOCK
                            .byNameCodec()
                            .fieldOf("block")
                            .forGetter(HeatRecipe::getInputBlock),
                        Codec.INT.fieldOf("amount").forGetter(HeatRecipe::getAmount),
                        StatePropertiesPredicate.CODEC
                            .optionalFieldOf("state")
                            .forGetter(HeatRecipe::getProperties))
                    .apply(instance, HeatRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, HeatRecipe> STREAM_CODEC =
        StreamCodec.of(HeatRecipe.Serializer::toNetwork, HeatRecipe.Serializer::fromNetwork);

    @Override
    public MapCodec<HeatRecipe> codec() {
      return CODEC;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, HeatRecipe> streamCodec() {
      return STREAM_CODEC;
    }

    public static HeatRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
      Block inputBlock = BuiltInRegistries.BLOCK.get(ResourceLocation.STREAM_CODEC.decode(buffer));
      int amount = buffer.readInt();
      boolean hasProperties =
          buffer.readBoolean(); // flag showing whether recipe depends on block state
      if (hasProperties) {
        StatePropertiesPredicate props = StatePropertiesPredicate.STREAM_CODEC.decode(buffer);
        return new HeatRecipe(inputBlock, amount, Optional.of(props));
      }
      return new HeatRecipe(
          inputBlock, amount, StatePropertiesPredicate.Builder.properties().build());
    }

    public static void toNetwork(RegistryFriendlyByteBuf buffer, HeatRecipe recipe) {
      ResourceLocation blockId = BuiltInRegistries.BLOCK.getKey(recipe.getInputBlock());
      ResourceLocation.STREAM_CODEC.encode(buffer, blockId);
      buffer.writeInt(recipe.getAmount());
      buffer.writeBoolean(recipe.getProperties().isPresent());
      recipe
          .getProperties()
          .ifPresent(props -> StatePropertiesPredicate.STREAM_CODEC.encode(buffer, props));
    }
  }
}
