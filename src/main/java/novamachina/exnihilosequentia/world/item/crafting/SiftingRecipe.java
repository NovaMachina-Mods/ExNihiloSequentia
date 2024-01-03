package novamachina.exnihilosequentia.world.item.crafting;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
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
      Ingredient input, ItemStack drop, boolean isWaterlogged, List<MeshWithChance> rolls) {
    this.input = input;
    this.drop = drop;
    this.rolls = rolls;
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
    return new SiftingRecipe(input, drop, isWaterlogged, possibleMeshes);
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
    private final Codec<T> codec;

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
                          ItemStack.CODEC.fieldOf("result").forGetter(recipe -> recipe.getDrop()),
                          Codec.BOOL
                              .fieldOf("waterlogged")
                              .forGetter(recipe -> recipe.isWaterlogged()),
                          Codec.list(MeshWithChance.CODEC)
                              .fieldOf("rolls")
                              .forGetter(recipe -> recipe.getRolls()))
                      .apply(instance, factory::create));
    }

    @Override
    public Codec<T> codec() {
      return this.codec;
    }

    @Override
    public T fromNetwork(FriendlyByteBuf buffer) {
      Ingredient input = Ingredient.fromNetwork(buffer);
      ItemStack drop = buffer.readItem();
      int rollCount = buffer.readInt();
      List<MeshWithChance> rolls = new ArrayList<>(rollCount);
      for (int i = 0; i < rollCount; i++) {
        rolls.add(MeshWithChance.read(buffer));
      }
      boolean waterlogged = buffer.readBoolean();
      return this.factory.create(input, drop, waterlogged, rolls);
    }

    @Override
    public void toNetwork(@NonNull FriendlyByteBuf buffer, T recipe) {
      recipe.write(buffer);
    }

    @FunctionalInterface
    public interface IFactory<T> {
      T create(Ingredient input, ItemStack drop, boolean waterlogged, List<MeshWithChance> rolls);
    }
  }
}
