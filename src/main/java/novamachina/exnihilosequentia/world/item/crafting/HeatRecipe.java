package novamachina.exnihilosequentia.world.item.crafting;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Getter;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;
import novamachina.novacore.world.item.crafting.Recipe;
import org.checkerframework.checker.nullness.qual.NonNull;

@Getter
public class HeatRecipe extends Recipe {

  private final int amount;
  private final Block inputBlock;
  private final StatePropertiesPredicate properties;

  public HeatRecipe(
      ResourceLocation id,
      Block inputBlock,
      final int amount,
      StatePropertiesPredicate properties) {
    super(id);
    this.inputBlock = inputBlock;
    this.amount = amount;
    this.properties = properties;
  }

  public boolean isMatch(@NonNull BlockState state) {
    if (inputBlock == null) {
      return false;
    }
    ResourceLocation resourceLocation = ForgeRegistries.BLOCKS.getKey(state.getBlock());
    if (resourceLocation == null) {
      return false;
    }

    if (inputBlock != null) {
      return state.is(inputBlock) && (properties == null || properties.matches(state));
    }
    return false;
  }

  @Override
  public @NonNull ItemStack getToastSymbol() {
    return EXNBlocks.FIRED_CRUCIBLE.itemStack();
  }

  @Override
  @NonNull
  public RecipeSerializer<?> getSerializer() {
    return EXNRecipeSerializers.HEAT_RECIPE_SERIALIZER.recipeSerializer();
  }

  @Override
  @NonNull
  public RecipeType<?> getType() {
    return EXNRecipeTypes.HEAT;
  }

  private boolean hasProperties() {
    return this.properties != StatePropertiesPredicate.ANY;
  }

  @Override
  public void write(FriendlyByteBuf buffer) {
    int length = ForgeRegistries.BLOCKS.getKey(inputBlock).toString().length();
    buffer.writeInt(length);
    buffer.writeUtf(ForgeRegistries.BLOCKS.getKey(inputBlock).toString(), length);
    buffer.writeInt(amount);
    buffer.writeBoolean(hasProperties());
    if (hasProperties()) {
      buffer.writeUtf(properties.serializeToJson().toString());
    }
  }

  public static class Serializer<T extends HeatRecipe> implements RecipeSerializer<T> {
    private final IBlockFactory<T> blockFactory;

    public Serializer(IBlockFactory<T> blockFactory) {
      this.blockFactory = blockFactory;
    }

    @Override
    @NonNull
    public T fromJson(@NonNull ResourceLocation id, JsonObject json) {
      Block inputBlock =
          ForgeRegistries.BLOCKS.getValue(new ResourceLocation(json.get("block").getAsString()));
      int amount = json.get("amount").getAsInt();
      if (json.has("state")) {
        return this.blockFactory.create(
            id, inputBlock, amount, StatePropertiesPredicate.fromJson(json.get("state")));
      }
      return this.blockFactory.create(id, inputBlock, amount, StatePropertiesPredicate.ANY);
    }

    @Override
    public T fromNetwork(@NonNull ResourceLocation id, FriendlyByteBuf buffer) {
      int length = buffer.readInt();
      Block inputBlock =
          ForgeRegistries.BLOCKS.getValue(new ResourceLocation(buffer.readUtf(length)));

      int amount = buffer.readInt();
      boolean hasProperties =
          buffer.readBoolean(); // flag showing whether recipe depends on block state
      if (hasProperties) {
        StatePropertiesPredicate properties =
            StatePropertiesPredicate.fromJson(JsonParser.parseString(buffer.readUtf()));
        return this.blockFactory.create(id, inputBlock, amount, properties);
      }
      return this.blockFactory.create(id, inputBlock, amount, StatePropertiesPredicate.ANY);
    }

    @Override
    public void toNetwork(@NonNull FriendlyByteBuf buffer, T recipe) {
      recipe.write(buffer);
    }

    @FunctionalInterface
    public interface IBlockFactory<T> {
      T create(
          ResourceLocation id, Block inputBlock, int amount, StatePropertiesPredicate properties);
    }
  }
}
