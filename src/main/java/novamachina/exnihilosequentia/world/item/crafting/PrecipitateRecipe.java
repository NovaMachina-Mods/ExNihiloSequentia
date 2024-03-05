package novamachina.exnihilosequentia.world.item.crafting;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidStack;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;
import novamachina.novacore.world.item.crafting.AbstractRecipe;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PrecipitateRecipe extends AbstractRecipe {

  private final Ingredient input;
  private final FluidStack fluid;
  private final ItemStack output;

  public PrecipitateRecipe(FluidStack fluid, Ingredient input, ItemStack output) {
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

  public Ingredient getInput() {
    return this.input;
  }

  public FluidStack getFluid() {
    return this.fluid;
  }

  public ItemStack getOutput() {
    return this.output;
  }

  public static class Serializer<T extends PrecipitateRecipe> implements RecipeSerializer<T> {

    private final IFactory<T> factory;
    private final Codec<T> codec;

    public Serializer(IFactory<T> factory) {
      this.factory = factory;
      this.codec =
          RecordCodecBuilder.create(
              instance ->
                  instance
                      .group(
                          FluidStack.CODEC.fieldOf("fluid").forGetter(recipe -> recipe.getFluid()),
                          Ingredient.CODEC_NONEMPTY
                              .fieldOf("input")
                              .forGetter(recipe -> recipe.getInput()),
                          ItemStack.CODEC.fieldOf("result").forGetter(recipe -> recipe.getOutput()))
                      .apply(instance, factory::create));
    }

    //    @Override
    //    @NonNull
    //    public T fromJson(@NonNull ResourceLocation id, JsonObject json) {
    //      Ingredient input = Ingredient.fromJson(json.get("input"));
    //      FluidStack fluid = FluidStackUtils.deserialize(json.getAsJsonObject("fluid"));
    //      ItemStack result = ItemStackHelper.deserialize(json.get("result"));
    //      return this.factory.create(id, fluid, input, result);
    //    }

    @Override
    public Codec<T> codec() {
      return this.codec;
    }

    @Override
    public @Nullable T fromNetwork(FriendlyByteBuf buffer) {
      Ingredient input = Ingredient.fromNetwork(buffer);
      FluidStack fluid = FluidStack.readFromPacket(buffer);
      ItemStack result = buffer.readItem();
      return this.factory.create(fluid, input, result);
    }

    @Override
    public void toNetwork(@NonNull FriendlyByteBuf buffer, T recipe) {
      recipe.write(buffer);
    }

    @FunctionalInterface
    public interface IFactory<T> {
      T create(FluidStack fluid, Ingredient input, ItemStack result);
    }
  }
}
