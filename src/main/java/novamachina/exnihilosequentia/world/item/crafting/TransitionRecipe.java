package novamachina.exnihilosequentia.world.item.crafting;

import com.google.gson.JsonObject;
import lombok.Getter;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.fluids.FluidStack;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;
import novamachina.novacore.util.FluidStackUtils;
import novamachina.novacore.world.item.crafting.Recipe;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

@Getter
public class TransitionRecipe extends Recipe {

  private final Ingredient catalyst;
  private final FluidStack fluidInTank;
  private final FluidStack result;

  public TransitionRecipe(
      ResourceLocation id, Ingredient catalyst, FluidStack fluidInTank, FluidStack result) {
    super(id);
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

  @Override
  public void write(FriendlyByteBuf buffer) {
    catalyst.toNetwork(buffer);
    fluidInTank.writeToPacket(buffer);
    result.writeToPacket(buffer);
  }

  public static class Serializer<T extends TransitionRecipe> implements RecipeSerializer<T> {
    private final IFactory<T> factory;

    public Serializer(IFactory<T> factory) {
      this.factory = factory;
    }

    @Override
    @NonNull
    public T fromJson(@NonNull ResourceLocation id, @NonNull JsonObject json) {
      Ingredient catalyst = Ingredient.fromJson(json.get("catalyst"));
      FluidStack fluidInTank = FluidStackUtils.deserialize(json.getAsJsonObject("fluidInTank"));
      FluidStack result = FluidStackUtils.deserialize(json.getAsJsonObject("result"));
      return this.factory.create(id, catalyst, fluidInTank, result);
    }

    @Override
    @Nullable
    public T fromNetwork(@NonNull ResourceLocation id, @NonNull FriendlyByteBuf buffer) {
      Ingredient catalyst = Ingredient.fromNetwork(buffer);
      FluidStack fluidInTank = FluidStack.readFromPacket(buffer);
      FluidStack result = FluidStack.readFromPacket(buffer);
      return this.factory.create(id, catalyst, fluidInTank, result);
    }

    @Override
    public void toNetwork(@NonNull FriendlyByteBuf buffer, @NonNull T recipe) {
      recipe.write(buffer);
    }

    @FunctionalInterface
    public interface IFactory<T> {
      T create(ResourceLocation id, Ingredient catalyst, FluidStack fluidInTank, FluidStack result);
    }
  }
}
