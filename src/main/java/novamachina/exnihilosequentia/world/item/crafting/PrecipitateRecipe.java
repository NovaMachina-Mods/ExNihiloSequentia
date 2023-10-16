package novamachina.exnihilosequentia.world.item.crafting;

import com.google.gson.JsonObject;
import javax.annotation.Nonnull;
import lombok.Getter;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;
import novamachina.novacore.util.FluidStackUtils;
import novamachina.novacore.util.ItemStackHelper;
import novamachina.novacore.world.item.crafting.Recipe;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Getter
public class PrecipitateRecipe extends Recipe {

  private final Ingredient input;
  private final FluidStack fluid;
  private final ItemStack output;

  public PrecipitateRecipe(
      @Nonnull final ResourceLocation id,
      @Nonnull final FluidStack fluid,
      @Nonnull final Ingredient input,
      @Nonnull final ItemStack output) {
    super(id);
    this.input = input;
    this.fluid = fluid;
    this.output = output;
  }

  public boolean validInputs(Fluid fluid, ItemLike input) {
    return this.fluid.getFluid().isSame(fluid) && this.input.test(new ItemStack(input));
  }

  @Override
  public void write(FriendlyByteBuf buffer) {
    input.toNetwork(buffer);
    fluid.writeToPacket(buffer);
    buffer.writeItem(output);
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

  public static class Serializer<T extends PrecipitateRecipe> implements RecipeSerializer<T> {

    private final IFactory<T> factory;

    public Serializer(IFactory<T> factory) {
      this.factory = factory;
    }

    @Override
    @NonNull
    public T fromJson(@NonNull ResourceLocation id, JsonObject json) {
      Ingredient input = Ingredient.fromJson(json.get("input"));
      FluidStack fluid = FluidStackUtils.deserialize(json.getAsJsonObject("fluid"));
      ItemStack result = ItemStackHelper.deserialize(json.get("result"));
      return this.factory.create(id, fluid, input, result);
    }

    @Override
    public @Nullable T fromNetwork(@NonNull ResourceLocation id, @NonNull FriendlyByteBuf buffer) {
      Ingredient input = Ingredient.fromNetwork(buffer);
      FluidStack fluid = FluidStack.readFromPacket(buffer);
      ItemStack result = buffer.readItem();
      return this.factory.create(id, fluid, input, result);
    }

    @Override
    public void toNetwork(@NonNull FriendlyByteBuf buffer, T recipe) {
      recipe.write(buffer);
    }

    @FunctionalInterface
    public interface IFactory<T> {
      T create(ResourceLocation id, FluidStack fluid, Ingredient input, ItemStack result);
    }
  }
}
