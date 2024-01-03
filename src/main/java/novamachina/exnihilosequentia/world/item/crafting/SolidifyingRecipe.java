package novamachina.exnihilosequentia.world.item.crafting;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import lombok.Getter;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidStack;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;
import novamachina.novacore.world.item.crafting.Recipe;
import org.checkerframework.checker.nullness.qual.NonNull;

@Getter
public class SolidifyingRecipe extends Recipe {
  private final FluidStack fluidInTank;
  private final FluidStack fluidOnTop;
  private final ItemStack result;

  public SolidifyingRecipe(FluidStack fluidInTank, FluidStack fluidOnTop, ItemStack result) {
    this.fluidInTank = fluidInTank;
    this.fluidOnTop = fluidOnTop;
    this.result = result;
  }

  public boolean validInputs(@NonNull final Fluid fluidInTank, @NonNull final Fluid fluidOnTop) {
    return this.fluidInTank.getFluid().isSame(fluidInTank)
        && this.fluidOnTop.getFluid().isSame(fluidOnTop);
  }

  @Override
  @NonNull
  public ItemStack getToastSymbol() {
    return EXNBlocks.OAK_SIEVE.itemStack();
  }

  @Override
  @NonNull
  public RecipeSerializer<?> getSerializer() {
    return EXNRecipeSerializers.SOLIDIFYING_RECIPE_SERIALIZER.recipeSerializer();
  }

  @Override
  @NonNull
  public RecipeType<?> getType() {
    return EXNRecipeTypes.SOLIDIFYING;
  }

  @Override
  public void write(FriendlyByteBuf buffer) {
    fluidInTank.writeToPacket(buffer);
    fluidOnTop.writeToPacket(buffer);
    buffer.writeItem(result);
  }

  public static class Serializer<T extends SolidifyingRecipe> implements RecipeSerializer<T> {

    private final IFactory<T> factory;
    private final Codec<T> codec;

    public Serializer(IFactory<T> factory) {
      this.factory = factory;
      this.codec =
          RecordCodecBuilder.create(
              instance ->
                  instance
                      .group(
                          FluidStack.CODEC
                              .fieldOf("fluidInTank")
                              .forGetter(recipe -> recipe.getFluidInTank()),
                          FluidStack.CODEC
                              .fieldOf("fluidOnTop")
                              .forGetter(recipe -> recipe.getFluidOnTop()),
                          ItemStack.CODEC.fieldOf("result").forGetter(recipe -> recipe.getResult()))
                      .apply(instance, factory::create));
    }

    @Override
    public Codec<T> codec() {
      return this.codec;
    }

    @Override
    @NonNull
    public T fromNetwork(FriendlyByteBuf buffer) {
      FluidStack fluidInTank = FluidStack.readFromPacket(buffer);
      FluidStack fluidOnTop = FluidStack.readFromPacket(buffer);
      ItemStack result = buffer.readItem();
      return this.factory.create(fluidInTank, fluidOnTop, result);
    }

    @Override
    public void toNetwork(@NonNull FriendlyByteBuf buffer, T recipe) {
      recipe.write(buffer);
    }

    @FunctionalInterface
    public interface IFactory<T> {
      T create(FluidStack fluidInTank, FluidStack fluidOnTop, ItemStack result);
    }
  }
}
