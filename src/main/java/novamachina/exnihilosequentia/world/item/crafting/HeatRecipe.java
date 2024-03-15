package novamachina.exnihilosequentia.world.item.crafting;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.Optional;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ExtraCodecs;
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

  @Override
  public void write(FriendlyByteBuf buffer) {
    int length = BuiltInRegistries.BLOCK.getKey(inputBlock).toString().length();
    buffer.writeInt(length);
    buffer.writeUtf(BuiltInRegistries.BLOCK.getKey(inputBlock).toString(), length);
    buffer.writeInt(amount);
    buffer.writeBoolean(this.properties.isPresent());
    if (this.properties.isPresent()) {
      buffer.writeJsonWithCodec(StatePropertiesPredicate.CODEC, this.properties.get());
    }
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

  public static class Serializer<T extends HeatRecipe> implements RecipeSerializer<T> {

    private final Codec<T> codec;
    private final IFactory<T> factory;

    public Serializer(IFactory<T> factory) {
      this.factory = factory;
      this.codec =
          RecordCodecBuilder.create(
              instance ->
                  instance
                      .group(
                          BuiltInRegistries.BLOCK
                              .byNameCodec()
                              .fieldOf("block")
                              .forGetter(recipe -> recipe.getInputBlock()),
                          Codec.INT.fieldOf("amount").forGetter(recipe -> recipe.getAmount()),
                          ExtraCodecs.strictOptionalField(StatePropertiesPredicate.CODEC, "state")
                              .forGetter(recipe -> recipe.getProperties()))
                      .apply(instance, factory::create));
    }

    @Override
    public Codec<T> codec() {
      return this.codec;
    }

    @Override
    public T fromNetwork(FriendlyByteBuf buffer) {
      int length = buffer.readInt();
      Block inputBlock = BuiltInRegistries.BLOCK.get(new ResourceLocation(buffer.readUtf(length)));

      int amount = buffer.readInt();
      boolean hasProperties =
          buffer.readBoolean(); // flag showing whether recipe depends on block state
      if (hasProperties) {
        StatePropertiesPredicate props = buffer.readJsonWithCodec(StatePropertiesPredicate.CODEC);
        return this.factory.create(inputBlock, amount, Optional.of(props));
      }
      return this.factory.create(
          inputBlock, amount, StatePropertiesPredicate.Builder.properties().build());
    }

    @Override
    public void toNetwork(@NonNull FriendlyByteBuf buffer, T recipe) {
      recipe.write(buffer);
    }

    @FunctionalInterface
    public interface IFactory<T> {
      T create(Block inputBlock, int amount, Optional<StatePropertiesPredicate> properties);
    }
  }
}
