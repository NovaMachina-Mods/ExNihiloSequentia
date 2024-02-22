package novamachina.exnihilosequentia.world.item.crafting;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.fluids.FluidStack;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;
import novamachina.novacore.world.item.crafting.Recipe;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

public class TransitionRecipe extends Recipe {

  private final Ingredient catalyst;
  private final FluidStack fluidInTank;
  private final FluidStack result;

  public TransitionRecipe(Ingredient catalyst, FluidStack fluidInTank, FluidStack result) {
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

  public Ingredient getCatalyst() {
    return this.catalyst;
  }

  public FluidStack getFluidInTank() {
    return this.fluidInTank;
  }

  public FluidStack getResult() {
    return this.result;
  }

  public static class Serializer<T extends TransitionRecipe> implements RecipeSerializer<T> {

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
                              .fieldOf("catalyst")
                              .forGetter(recipe -> recipe.getCatalyst()),
                          FluidStack.CODEC
                              .fieldOf("fluidInTank")
                              .forGetter(recipe -> recipe.getFluidInTank()),
                          FluidStack.CODEC
                              .fieldOf("result")
                              .forGetter(recipe -> recipe.getResult()))
                      .apply(instance, factory::create));
    }

    @Override
    @Nullable
    public T fromNetwork(@NonNull FriendlyByteBuf buffer) {
      Ingredient catalyst = Ingredient.fromNetwork(buffer);
      FluidStack fluidInTank = FluidStack.readFromPacket(buffer);
      FluidStack result = FluidStack.readFromPacket(buffer);
      return this.factory.create(catalyst, fluidInTank, result);
    }

    @Override
    public Codec<T> codec() {
      return this.codec;
    }

    @Override
    public void toNetwork(@NonNull FriendlyByteBuf buffer, @NonNull T recipe) {
      recipe.write(buffer);
    }

    @FunctionalInterface
    public interface IFactory<T> {
      T create(Ingredient catalyst, FluidStack fluidInTank, FluidStack result);
    }
  }
}
