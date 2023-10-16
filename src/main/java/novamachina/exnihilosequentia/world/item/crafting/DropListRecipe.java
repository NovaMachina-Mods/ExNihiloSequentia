package novamachina.exnihilosequentia.world.item.crafting;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import lombok.Getter;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
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

  public DropListRecipe(ResourceLocation id, Ingredient input, ItemStackWithChance... drops) {
    super(id);
    this.input = input;
    this.drops = Lists.newArrayList(drops);
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

    public Serializer(IFactory<T> factory) {
      this.factory = factory;
    }

    @Override
    @Nonnull
    public T fromNetwork(
        @Nonnull final ResourceLocation recipeId, @Nonnull final FriendlyByteBuf buffer) {
      final int outputCount = buffer.readInt();
      @Nonnull final List<ItemStackWithChance> output = new ArrayList<>(outputCount);
      for (int i = 0; i < outputCount; i++) {
        output.add(ItemStackWithChance.read(buffer));
      }
      @Nonnull final Ingredient input = Ingredient.fromNetwork(buffer);
      return this.factory.create(recipeId, input, output.toArray(ItemStackWithChance[]::new));
    }

    @Override
    public void toNetwork(@Nonnull final FriendlyByteBuf buffer, @Nonnull final T recipe) {
      recipe.write(buffer);
    }

    @Override
    @Nonnull
    public T fromJson(@Nonnull ResourceLocation recipeId, JsonObject json) {
      Ingredient input = Ingredient.fromJson(json.get("input"));
      JsonArray results = json.getAsJsonArray("results");
      List<ItemStackWithChance> output = new ArrayList<>(results.size());
      for (int i = 0; i < results.size(); i++) {
        output.add(ItemStackWithChance.deserialize(results.get(i)));
      }
      return this.factory.create(recipeId, input, output.toArray(ItemStackWithChance[]::new));
    }

    @FunctionalInterface
    public interface IFactory<T> {
      T create(ResourceLocation id, Ingredient input, ItemStackWithChance... drops);
    }
  }
}
