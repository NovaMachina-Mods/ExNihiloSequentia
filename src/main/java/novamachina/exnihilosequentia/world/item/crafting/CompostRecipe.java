package novamachina.exnihilosequentia.world.item.crafting;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;
import novamachina.novacore.world.item.crafting.AbstractRecipe;
import org.checkerframework.checker.nullness.qual.NonNull;

public class CompostRecipe extends AbstractRecipe {
  private final Ingredient input;
  private final int amount;

  public CompostRecipe(Ingredient input, int amount) {
    this.input = input;
    this.amount = amount;
  }

  @Override
  public void write(FriendlyByteBuf buffer) {
    input.toNetwork(buffer);
    buffer.writeInt(amount);
  }

  @Override
  @NonNull
  public RecipeSerializer<?> getSerializer() {
    return EXNRecipeSerializers.COMPOST_RECIPE_SERIALIZER.recipeSerializer();
  }

  @Override
  @NonNull
  public RecipeType<?> getType() {
    return EXNRecipeTypes.COMPOST;
  }

  @Override
  @NonNull
  public ItemStack getToastSymbol() {
    return EXNBlocks.OAK_BARREL.itemStack();
  }

  public Ingredient getInput() {
    return this.input;
  }

  public int getAmount() {
    return this.amount;
  }

  public static class Serializer<T extends CompostRecipe> implements RecipeSerializer<T> {

    private final IFactory<T> factory;
    private final Codec<T> codec;

    public Serializer(IFactory<T> factory) {
      this.factory = factory;
      this.codec =
          RecordCodecBuilder.create(
              instance ->
                  instance
                      .group(
                          Ingredient.CODEC_NONEMPTY
                              .fieldOf("input")
                              .forGetter(CompostRecipe::getInput),
                          Codec.INT.fieldOf("amount").forGetter(CompostRecipe::getAmount))
                      .apply(instance, factory::create));
    }

    @Override
    public Codec<T> codec() {
      return this.codec;
    }

    @Override
    public T fromNetwork(FriendlyByteBuf buffer) {
      Ingredient input = Ingredient.fromNetwork(buffer);
      int amount = buffer.readInt();
      return this.factory.create(input, amount);
    }

    @Override
    public void toNetwork(@NonNull FriendlyByteBuf buffer, T recipe) {
      recipe.write(buffer);
    }

    @FunctionalInterface
    public interface IFactory<T> {
      T create(Ingredient input, int amount);
    }
  }
}
