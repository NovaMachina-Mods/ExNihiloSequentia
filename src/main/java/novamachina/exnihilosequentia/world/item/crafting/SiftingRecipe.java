package novamachina.exnihilosequentia.world.item.crafting;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.PlacementInfo;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import novamachina.exnihilosequentia.world.item.MeshType;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;
import novamachina.novacore.world.item.crafting.AbstractRecipe;
import org.checkerframework.checker.nullness.qual.NonNull;

public class SiftingRecipe extends AbstractRecipe {

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
  public RecipeSerializer<SiftingRecipe> getSerializer() {
    return EXNRecipeSerializers.SIFTING_RECIPE_SERIALIZER.recipeSerializer();
  }

  @Override
  public RecipeType<SiftingRecipe> getType() {
    return EXNRecipeTypes.SIFTING;
  }

  public Ingredient getInput() {
    return this.input;
  }

  public boolean isWaterlogged() {
    return this.isWaterlogged;
  }

  public List<MeshWithChance> getRolls() {
    return this.rolls;
  }

  public static class Serializer implements RecipeSerializer<SiftingRecipe> {
    public static final MapCodec<SiftingRecipe> CODEC =
        RecordCodecBuilder.mapCodec(
            instance ->
                instance
                    .group(
                        Ingredient.CODEC
                            .fieldOf("input")
                            .forGetter(recipe -> recipe.getInput()),
                        ItemStack.CODEC.fieldOf("result").forGetter(recipe -> recipe.getDrop()),
                        Codec.BOOL
                            .fieldOf("waterlogged")
                            .forGetter(recipe -> recipe.isWaterlogged()),
                        Codec.list(MeshWithChance.CODEC)
                            .fieldOf("rolls")
                            .forGetter(recipe -> recipe.getRolls()))
                    .apply(instance, SiftingRecipe::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, SiftingRecipe> STREAM_CODEC =
        StreamCodec.of(SiftingRecipe.Serializer::toNetwork, SiftingRecipe.Serializer::fromNetwork);

    @Override
    public MapCodec<SiftingRecipe> codec() {
      return CODEC;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, SiftingRecipe> streamCodec() {
      return STREAM_CODEC;
    }

    public static SiftingRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {

      Ingredient input = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
      ItemStack drop = ItemStack.STREAM_CODEC.decode(buffer);
      int rollCount = buffer.readInt();
      List<MeshWithChance> rolls = new ArrayList<>(rollCount);
      for (int i = 0; i < rollCount; i++) {
        rolls.add(MeshWithChance.read(buffer));
      }
      boolean waterlogged = buffer.readBoolean();
      return new SiftingRecipe(input, drop, waterlogged, rolls);
    }

    public static void toNetwork(@NonNull RegistryFriendlyByteBuf buffer, SiftingRecipe recipe) {
      Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.getInput());
      ItemStack.STREAM_CODEC.encode(buffer, recipe.getDrop());
      buffer.writeInt(recipe.getRolls().size());
      recipe.getRolls().forEach(roll -> roll.write(buffer));
      buffer.writeBoolean(recipe.isWaterlogged());
    }
  }

  @Override
  public PlacementInfo placementInfo() {
    return PlacementInfo.create(input);
  }
}
