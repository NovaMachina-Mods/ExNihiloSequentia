package novamachina.exnihilosequentia.common.compat.kubejs;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.recipe.IngredientMatch;
import dev.latvian.mods.kubejs.recipe.ItemInputTransformer;
import dev.latvian.mods.kubejs.recipe.ItemOutputTransformer;
import dev.latvian.mods.kubejs.recipe.RecipeArguments;
import dev.latvian.mods.kubejs.recipe.RecipeExceptionJS;
import dev.latvian.mods.kubejs.recipe.RecipeJS;
import java.util.Locale;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import novamachina.exnihilosequentia.common.item.mesh.MeshType;

public class SieveRecipeJS extends RecipeJS {

  private Ingredient input;
  private ItemStack result;
  private JsonArray rolls;
  private boolean isWaterlogged;

  @Override
  public void create(RecipeArguments args) {
    this.input = this.parseItemInput(args.get(0));
    this.result = this.parseItemOutput(args.get(1));
    this.rolls = new JsonArray();
    this.isWaterlogged = false;
  }

  @Override
  public void deserialize() {
    this.input = this.parseItemInput(this.json.get("input"));
    this.result = this.parseItemOutput(this.json.get("result"));
    if (this.json.has("waterlogged")) {
      this.isWaterlogged = this.json.get("waterlogged").getAsBoolean();
    }
    this.rolls = this.json.getAsJsonArray("rolls");
  }

  @Override
  public void serialize() {
    if (this.serializeInputs) {
      this.json.add("input", this.input.toJson());
    }
    if (this.serializeOutputs) {
      this.json.add("result", this.itemToJson(this.result));
    }
    this.json.addProperty("waterlogged", this.isWaterlogged);
    this.json.add("rolls", this.rolls);
  }

  @Override
  public boolean hasInput(IngredientMatch ingredientMatch) {
    return ingredientMatch.contains(this.input);
  }

  @Override
  public boolean replaceInput(
      IngredientMatch ingredientMatch,
      Ingredient ingredient,
      ItemInputTransformer itemInputTransformer) {
    if (ingredientMatch.contains(this.input)) {
      this.input = itemInputTransformer.transform(this, ingredientMatch, this.input, ingredient);
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean hasOutput(IngredientMatch ingredientMatch) {
    return ingredientMatch.contains(this.result);
  }

  @Override
  public boolean replaceOutput(
      IngredientMatch ingredientMatch,
      ItemStack itemStack,
      ItemOutputTransformer itemOutputTransformer) {
    if (ingredientMatch.contains(this.input)) {
      this.result = itemOutputTransformer.transform(this, ingredientMatch, this.result, itemStack);
      return true;
    } else {
      return false;
    }
  }

  public SieveRecipeJS addRoll(float chance, String mesh) {
    if (!isValidMesh(mesh)) {
      throw new RecipeExceptionJS("Mesh type must be " + MeshType.printList());
    }
    if (chance < 0.0F || chance > 1.0F) {
      throw new RecipeExceptionJS("Chance must be in range of 0.0 - 1.0");
    }
    JsonObject object = new JsonObject();
    object.addProperty("chance", chance);
    object.addProperty("mesh", mesh.toLowerCase(Locale.ROOT));
    this.rolls.add(object);
    return this;
  }

  public SieveRecipeJS setWaterlogged() {
    this.isWaterlogged = true;
    return this;
  }

  private boolean isValidMesh(String mesh) {
    for (MeshType type : MeshType.values()) {
      if (type.name().toLowerCase(Locale.ROOT).equals(mesh.toLowerCase(Locale.ROOT))) {
        return true;
      }
    }
    return false;
  }
}
