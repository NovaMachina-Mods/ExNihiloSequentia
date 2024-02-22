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
import novamachina.exnihilosequentia.world.level.block.entity.CrucibleBlockEntity.CrucibleType;
import novamachina.novacore.world.item.crafting.Recipe;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.Nullable;

public class MeltingRecipe extends Recipe {

  private final Ingredient input;
  private final FluidStack resultFluid;
  private final CrucibleType crucibleType;

  public MeltingRecipe(Ingredient input, FluidStack fluid, CrucibleType crucibleType) {
    this.resultFluid = fluid;
    this.input = input;
    this.crucibleType = crucibleType;
  }

  @Override
  @NonNull
  public RecipeSerializer<?> getSerializer() {
    return EXNRecipeSerializers.MELTING_RECIPE_SERIALIZER.recipeSerializer();
  }

  @Override
  @NonNull
  public RecipeType<?> getType() {
    return EXNRecipeTypes.MELTING;
  }

  @Override
  @NonNull
  public ItemStack getToastSymbol() {
    return EXNBlocks.FIRED_CRUCIBLE.itemStack();
  }

  @Override
  public void write(FriendlyByteBuf buffer) {
    input.toNetwork(buffer);
    resultFluid.writeToPacket(buffer);
    buffer.writeEnum(crucibleType);
  }

  public Ingredient getInput() {
    return this.input;
  }

  public FluidStack getResultFluid() {
    return this.resultFluid;
  }

  public CrucibleType getCrucibleType() {
    return this.crucibleType;
  }

  public static class Serializer<T extends MeltingRecipe> implements RecipeSerializer<T> {
    private final Codec<T> codec;
    private final IFactory<T> factory;

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
                          FluidStack.CODEC
                              .fieldOf("fluidResult")
                              .forGetter(recipe -> recipe.getResultFluid()),
                          CrucibleType.CODEC
                              .fieldOf("crucibleType")
                              .forGetter(recipe -> recipe.getCrucibleType()))
                      .apply(instance, factory::create));
    }

    @Override
    public Codec<T> codec() {
      return this.codec;
    }

    @Override
    public @Nullable T fromNetwork(FriendlyByteBuf buffer) {
      Ingredient input = Ingredient.fromNetwork(buffer);
      FluidStack fluid = FluidStack.readFromPacket(buffer);
      CrucibleType type = buffer.readEnum(CrucibleType.class);
      return this.factory.create(input, fluid, type);
    }

    @Override
    public void toNetwork(@NonNull FriendlyByteBuf buffer, T recipe) {
      recipe.write(buffer);
    }

    @FunctionalInterface
    public interface IFactory<T> {
      T create(Ingredient input, FluidStack result, CrucibleType type);
    }
  }
}
