package novamachina.exnihilosequentia.data.recipes;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.List;
import javax.annotation.Nonnull;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;
import novamachina.exnihilosequentia.world.item.crafting.EXNRecipeSerializers;
import novamachina.exnihilosequentia.world.item.crafting.MeshWithChance;
import novamachina.novacore.data.recipes.RecipeBuilder;

public class SiftingRecipeBuilder extends RecipeBuilder<SiftingRecipeBuilder> {
  private final Ingredient input;
  private final ItemStack drop;
  private final boolean isWaterlogged;
  private List<MeshWithChance> rolls;

  protected SiftingRecipeBuilder(
      Ingredient input, ItemStack drop, boolean isWaterlogged, MeshWithChance... rolls) {
    //noinspection ConstantConditions
    super(EXNRecipeSerializers.SIFTING_RECIPE_SERIALIZER.recipeSerializer());
    this.input = input;
    this.drop = drop;
    this.isWaterlogged = isWaterlogged;
    this.rolls = Lists.newArrayList(rolls);
  }

  public static SiftingRecipeBuilder sifting(
      ItemLike input, ItemLike drop, MeshWithChance... rolls) {
    return sifting(Ingredient.of(input), new ItemStack(drop), false, rolls);
  }

  public static SiftingRecipeBuilder sifting(
      ItemLike input, ItemLike drop, boolean isWaterlogged, MeshWithChance... rolls) {
    return sifting(Ingredient.of(input), new ItemStack(drop), isWaterlogged, rolls);
  }

  public static SiftingRecipeBuilder sifting(
      ItemLike input, ItemStack drop, MeshWithChance... rolls) {
    return sifting(Ingredient.of(input), drop, false, rolls);
  }

  public static SiftingRecipeBuilder sifting(
      ItemLike input, ItemStack drop, boolean isWaterlogged, MeshWithChance... rolls) {
    return sifting(Ingredient.of(input), drop, isWaterlogged, rolls);
  }

  public static SiftingRecipeBuilder sifting(
      Ingredient input, ItemLike drop, MeshWithChance... rolls) {
    return sifting(input, new ItemStack(drop), false, rolls);
  }

  public static SiftingRecipeBuilder sifting(
      Ingredient input, ItemLike drop, boolean isWaterlogged, MeshWithChance... rolls) {
    return sifting(input, new ItemStack(drop), isWaterlogged, rolls);
  }

  public static SiftingRecipeBuilder sifting(
      Ingredient input, ItemStack drop, MeshWithChance... rolls) {
    return sifting(input, drop, false, rolls);
  }

  public static SiftingRecipeBuilder sifting(
      Ingredient input, ItemStack drop, boolean isWaterlogged, MeshWithChance... rolls) {
    return new SiftingRecipeBuilder(input, drop, isWaterlogged, rolls);
  }

  @Override
  protected void validate(ResourceLocation id) {
    Preconditions.checkNotNull(input, "Input cannot be null.");
    Preconditions.checkNotNull(drop, "Drop cannot be null.");
    Preconditions.checkArgument(!drop.isEmpty(), "Recipe needs at least one drop.");
    Preconditions.checkArgument(!rolls.isEmpty(), "Recipe needs at least one roll.");
  }

  public SiftingRecipeBuilder addRoll(MeshWithChance mesh) {
    rolls.add(mesh);
    return this;
  }

  @Override
  protected SiftingRecipeResult getResult(ResourceLocation id) {
    return new SiftingRecipeResult(id);
  }

  public class SiftingRecipeResult extends RecipeResult {

    public SiftingRecipeResult(ResourceLocation id) {
      super(id);
    }

    @Override
    public void serializeRecipeData(JsonObject json) {
      json.add("input", input.toJson());
      json.add("result", serializeItemStack(drop));
      JsonArray rollArray = new JsonArray();
      rolls.forEach(roll -> rollArray.add(roll.serialize()));
      json.add("rolls", rollArray);
      if (isWaterlogged) {
        json.addProperty("waterlogged", true);
      }
    }

    private JsonObject serializeItemStack(@Nonnull final ItemStack itemStack) {
      JsonObject obj = new JsonObject();
      ResourceLocation resourceLocation = ForgeRegistries.ITEMS.getKey(itemStack.getItem());
      if (resourceLocation != null) {
        obj.addProperty("item", resourceLocation.toString());
      }
      if (itemStack.getCount() > 1) {
        obj.addProperty("count", itemStack.getCount());
      }
      if (itemStack.hasTag() && itemStack.getTag() != null) {
        obj.addProperty("nbt", itemStack.getTag().toString());
      }
      return obj;
    }
  }
}
