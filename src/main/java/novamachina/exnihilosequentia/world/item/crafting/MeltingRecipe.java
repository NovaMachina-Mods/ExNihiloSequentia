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
import novamachina.exnihilosequentia.world.level.block.entity.CrucibleBlockEntity.CrucibleType;
import novamachina.novacore.util.FluidStackUtils;
import novamachina.novacore.world.item.crafting.Recipe;
import org.checkerframework.checker.nullness.qual.NonNull;

@Getter
public class MeltingRecipe extends Recipe {

  private final Ingredient input;
  private final FluidStack resultFluid;
  private final CrucibleType crucibleType;

  public MeltingRecipe(
      ResourceLocation id, Ingredient input, FluidStack fluid, CrucibleType crucibleType) {
    super(id);
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

  public static class Serializer<T extends MeltingRecipe> implements RecipeSerializer<T> {
    private final IFactory<T> factory;

    public Serializer(IFactory<T> factory) {
      this.factory = factory;
    }

    @Override
    @NonNull
    public T fromJson(@NonNull ResourceLocation id, JsonObject json) {
      Ingredient input = Ingredient.fromJson(json.get("input"));
      FluidStack fluid = FluidStackUtils.deserialize(json.get("fluidResult").getAsJsonObject());
      CrucibleType typeEnum = CrucibleType.getTypeByName(json.get("crucibleType").getAsString());
      return this.factory.create(id, input, fluid, typeEnum);
    }

    @Override
    public T fromNetwork(@NonNull ResourceLocation id, @NonNull FriendlyByteBuf buffer) {
      Ingredient input = Ingredient.fromNetwork(buffer);
      FluidStack fluid = FluidStack.readFromPacket(buffer);
      CrucibleType type = buffer.readEnum(CrucibleType.class);
      return this.factory.create(id, input, fluid, type);
    }

    @Override
    public void toNetwork(@NonNull FriendlyByteBuf buffer, T recipe) {
      recipe.write(buffer);
    }

    @FunctionalInterface
    public interface IFactory<T> {
      T create(ResourceLocation id, Ingredient input, FluidStack result, CrucibleType type);
    }
  }
}
