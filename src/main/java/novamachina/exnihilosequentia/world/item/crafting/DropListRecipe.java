package novamachina.exnihilosequentia.world.item.crafting;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import lombok.Getter;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import novamachina.novacore.world.item.crafting.Recipe;
import org.checkerframework.checker.nullness.qual.NonNull;

@Getter
public abstract class DropListRecipe extends Recipe {
  private final Ingredient input;
  private final List<ItemStackWithChance> drops;

  public DropListRecipe(Ingredient input, List<ItemStackWithChance> drops) {
    this.input = input;
    this.drops = drops;
  }

  @Override
  @NonNull
  public abstract ItemStack getToastSymbol();

  @Override
  @NonNull
  public abstract RecipeSerializer<?> getSerializer();

  @Override
  @NonNull
  public abstract RecipeType<?> getType();

  @NonNull
  public List<ItemStack> getOutputsWithoutChance() {
    List<ItemStack> returnList = new ArrayList<>();
    drops.forEach(stack -> returnList.add(stack.getStack()));
    return returnList;
  }

  @Override
  public void write(FriendlyByteBuf buffer) {
    buffer.writeInt(drops.size());
    drops.forEach(drop -> drop.write(buffer));
    input.toNetwork(buffer);
  }

  public static class Serializer<T extends DropListRecipe> implements RecipeSerializer<T> {

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
                              .forGetter(recipe -> recipe.getInput()),
                          Codec.list(ItemStackWithChance.CODEC)
                              .fieldOf("results")
                              .forGetter(recipe -> recipe.getDrops()))
                      .apply(instance, factory::create));
    }

    @Override
    public Codec<T> codec() {
      return this.codec;
    }

    @Override
    @Nonnull
    public T fromNetwork(@Nonnull final FriendlyByteBuf buffer) {
      final int outputCount = buffer.readInt();
      @Nonnull final List<ItemStackWithChance> output = new ArrayList<>(outputCount);
      for (int i = 0; i < outputCount; i++) {
        output.add(ItemStackWithChance.read(buffer));
      }
      @Nonnull final Ingredient input = Ingredient.fromNetwork(buffer);
      return this.factory.create(input, output);
    }

    @Override
    public void toNetwork(@Nonnull final FriendlyByteBuf buffer, @Nonnull final T recipe) {
      recipe.write(buffer);
    }

    @FunctionalInterface
    public interface IFactory<T> {
      T create(Ingredient ingredient, List<ItemStackWithChance> itemStackWithChances);
    }
  }
}
