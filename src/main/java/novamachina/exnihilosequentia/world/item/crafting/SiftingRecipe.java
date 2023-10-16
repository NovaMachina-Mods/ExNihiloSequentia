package novamachina.exnihilosequentia.world.item.crafting;

import com.google.common.collect.Lists;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;
import novamachina.exnihilosequentia.world.item.MeshType;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;
import novamachina.novacore.world.item.crafting.Recipe;
import org.checkerframework.checker.nullness.qual.NonNull;

@Getter
public class SiftingRecipe extends Recipe {

  private final Ingredient input;
  private final ItemStack drop;
  private final boolean isWaterlogged;
  private final List<MeshWithChance> rolls;

  public SiftingRecipe(
      ResourceLocation id,
      Ingredient input,
      ItemStack drop,
      boolean isWaterlogged,
      MeshWithChance... rolls) {
    super(id);
    this.input = input;
    this.drop = drop;
    this.rolls = Lists.newArrayList(rolls);
    this.isWaterlogged = isWaterlogged;
  }

  public SiftingRecipe filterByMesh(MeshType meshType, boolean flattenRecipes) {
    List<MeshWithChance> possibleMeshes = new ArrayList<>();
    for (MeshWithChance mesh : rolls) {
      if (flattenRecipes) {
        if (mesh.getMesh().getLevel() <= meshType.getLevel()) {
          possibleMeshes.add(mesh);
        }
      } else {
        if (mesh.getMesh().getLevel() == meshType.getLevel()) {
          possibleMeshes.add(mesh);
        }
      }
    }
    return new SiftingRecipe(
        this.getId(), input, drop, isWaterlogged, possibleMeshes.toArray(MeshWithChance[]::new));
  }

  public ItemStack getDrop() {
    return drop.copy();
  }

  @Override
  @NonNull
  public ItemStack getToastSymbol() {
    return EXNBlocks.OAK_SIEVE.itemStack();
  }

  @Override
  @NonNull
  public RecipeSerializer<?> getSerializer() {
    return EXNRecipeSerializers.SIFTING_RECIPE_SERIALIZER.recipeSerializer();
  }

  @Override
  public RecipeType<?> getType() {
    return EXNRecipeTypes.SIFTING;
  }

  @Override
  public void write(FriendlyByteBuf buffer) {
    input.toNetwork(buffer);
    buffer.writeItem(drop);
    buffer.writeInt(rolls.size());
    rolls.forEach(roll -> roll.write(buffer));
    buffer.writeBoolean(isWaterlogged);
  }

  public static class Serializer<T extends SiftingRecipe> implements RecipeSerializer<T> {
    private final IFactory<T> factory;

    public Serializer(IFactory<T> factory) {
      this.factory = factory;
    }

    @Override
    @NonNull
    public T fromJson(@NonNull ResourceLocation id, JsonObject json) {
      Ingredient input = Ingredient.fromJson(json.get("input"));
      ItemStack drop =
          ShapedRecipe.itemFromJson(json.getAsJsonObject("result")).getDefaultInstance();
      List<MeshWithChance> rolls = new ArrayList<>();
      for (JsonElement element : json.get("rolls").getAsJsonArray()) {
        rolls.add(MeshWithChance.deserialize(element));
      }
      if (json.has("waterlogged")) {
        return this.factory.create(
            id,
            input,
            drop,
            json.get("waterlogged").getAsBoolean(),
            rolls.toArray(MeshWithChance[]::new));
      }
      return this.factory.create(id, input, drop, false, rolls.toArray(MeshWithChance[]::new));
    }

    @Override
    public T fromNetwork(@NonNull ResourceLocation id, @NonNull FriendlyByteBuf buffer) {
      Ingredient input = Ingredient.fromNetwork(buffer);
      ItemStack drop = buffer.readItem();
      int rollCount = buffer.readInt();
      List<MeshWithChance> rolls = new ArrayList<>(rollCount);
      for (int i = 0; i < rollCount; i++) {
        rolls.add(MeshWithChance.read(buffer));
      }
      boolean waterlogged = buffer.readBoolean();
      return this.factory.create(
          id, input, drop, waterlogged, rolls.toArray(MeshWithChance[]::new));
    }

    @Override
    public void toNetwork(@NonNull FriendlyByteBuf buffer, T recipe) {
      recipe.write(buffer);
    }

    @FunctionalInterface
    public interface IFactory<T> {
      T create(
          ResourceLocation id,
          Ingredient input,
          ItemStack drop,
          boolean waterlogged,
          MeshWithChance... rolls);
    }
  }
}
