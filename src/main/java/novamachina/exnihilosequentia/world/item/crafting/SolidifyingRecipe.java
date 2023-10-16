package novamachina.exnihilosequentia.world.item.crafting;

import com.google.gson.JsonObject;
import lombok.Getter;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;
import novamachina.novacore.util.FluidStackUtils;
import novamachina.novacore.util.ItemStackHelper;
import novamachina.novacore.world.item.crafting.Recipe;
import org.checkerframework.checker.nullness.qual.NonNull;

@Getter
public class SolidifyingRecipe extends Recipe {
  private final FluidStack fluidInTank;
  private final FluidStack fluidOnTop;
  private final ItemStack result;

  public SolidifyingRecipe(
      ResourceLocation id, FluidStack fluidInTank, FluidStack fluidOnTop, ItemStack result) {
    super(id);
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

    public Serializer(IFactory<T> factory) {
      this.factory = factory;
    }

    @Override
    @NonNull
    public T fromJson(@NonNull ResourceLocation id, JsonObject json) {
      FluidStack fluidInTank = FluidStackUtils.deserialize(json.getAsJsonObject("fluidInTank"));
      FluidStack fluidOnTop = FluidStackUtils.deserialize(json.getAsJsonObject("fluidOnTop"));
      ItemStack result = ItemStackHelper.deserialize(json.get("result"));
      return this.factory.create(id, fluidInTank, fluidOnTop, result);
    }

    @Override
    @NonNull
    public T fromNetwork(@NonNull ResourceLocation id, @NonNull FriendlyByteBuf buffer) {
      FluidStack fluidInTank = FluidStack.readFromPacket(buffer);
      FluidStack fluidOnTop = FluidStack.readFromPacket(buffer);
      ItemStack result = buffer.readItem();
      return this.factory.create(id, fluidInTank, fluidOnTop, result);
    }

    @Override
    public void toNetwork(@NonNull FriendlyByteBuf buffer, T recipe) {
      recipe.write(buffer);
    }

    @FunctionalInterface
    public interface IFactory<T> {
      T create(
          ResourceLocation id, FluidStack fluidInTank, FluidStack fluidOnTop, ItemStack result);
    }
  }
}
