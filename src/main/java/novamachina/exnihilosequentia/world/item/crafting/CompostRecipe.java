package novamachina.exnihilosequentia.world.item.crafting;

import com.google.gson.JsonObject;
import lombok.Getter;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;
import novamachina.novacore.world.item.crafting.Recipe;
import org.checkerframework.checker.nullness.qual.NonNull;

@Getter
public class CompostRecipe extends Recipe {
  private final Ingredient input;
  private final int amount;

  public CompostRecipe(ResourceLocation id, Ingredient input, int amount) {
    super(id);
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

  public static class Serializer<T extends CompostRecipe> implements RecipeSerializer<T> {

    private final IFactory<T> factory;

    public Serializer(IFactory<T> factory) {
      this.factory = factory;
    }

    @Override
    @NonNull
    public T fromJson(@NonNull ResourceLocation id, JsonObject json) {
      Ingredient input = Ingredient.fromJson(json.get("input"));
      int amount = json.get("amount").getAsInt();
      return this.factory.create(id, input, amount);
    }

    @Override
    public T fromNetwork(@NonNull ResourceLocation id, @NonNull FriendlyByteBuf buffer) {
      Ingredient input = Ingredient.fromNetwork(buffer);
      int amount = buffer.readInt();
      return this.factory.create(id, input, amount);
    }

    @Override
    public void toNetwork(@NonNull FriendlyByteBuf buffer, T recipe) {
      recipe.write(buffer);
    }

    @FunctionalInterface
    public interface IFactory<T> {
      T create(ResourceLocation id, Ingredient input, int amount);
    }
  }
}
