package novamachina.exnihilosequentia.common.compat.kubejs;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import dev.latvian.mods.kubejs.recipe.IngredientMatch;
import dev.latvian.mods.kubejs.recipe.ItemInputTransformer;
import dev.latvian.mods.kubejs.recipe.ItemOutputTransformer;
import dev.latvian.mods.kubejs.recipe.RecipeArguments;
import dev.latvian.mods.kubejs.recipe.RecipeJS;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class SimpleChanceRecipeJS extends RecipeJS {

  private Ingredient input;
  private List<DropWithChanceJS> results;

  @Override
  public void create(RecipeArguments args) {
    this.input = this.parseItemInput(args.get(0));
    this.results = new ArrayList<>();
  }

  public SimpleChanceRecipeJS addDrop(Item item, int count, float chance) {
    results.add(new DropWithChanceJS(chance, count, item));
    return this;
  }

  @Override
  public void deserialize() {
    this.input = this.parseItemInput(this.json.get("input"));
    this.results = this.deserializeResults(this.json.getAsJsonArray("results"));
  }

  @Override
  public void serialize() {
    if (this.serializeInputs) {
      this.json.add("input", this.input.toJson());
    }
    if (this.serializeOutputs) {
      this.json.add("results", serializeResults(this.results));
    }
  }

  private JsonElement serializeResults(List<DropWithChanceJS> results) {
    JsonArray array = new JsonArray();
    for (DropWithChanceJS drop : results) {
      array.add(drop.toJson());
    }
    return array;
  }

  private List<DropWithChanceJS> deserializeResults(JsonArray array) {
    List<DropWithChanceJS> jsonResults = new ArrayList<>();
    for (JsonElement element : array) {
      jsonResults.add(DropWithChanceJS.fromJson(element.getAsJsonObject()));
    }
    return jsonResults;
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
    for (DropWithChanceJS drop : this.results) {
      if (ingredientMatch.contains(new ItemStack(drop.getItem()))) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean replaceOutput(
      IngredientMatch ingredientMatch,
      ItemStack itemStack,
      ItemOutputTransformer itemOutputTransformer) {
    for (DropWithChanceJS drop : this.results) {
      if (ingredientMatch.contains(new ItemStack(drop.getItem()))) {
        drop.setItem(
            itemOutputTransformer
                .transform(this, ingredientMatch, new ItemStack(drop.getItem()), itemStack)
                .getItem());
        return true;
      }
    }
    return false;
  }
}
