package novamachina.exnihilosequentia.common.compat.kubejs;

import dev.latvian.mods.kubejs.recipe.IngredientMatch;
import dev.latvian.mods.kubejs.recipe.ItemInputTransformer;
import dev.latvian.mods.kubejs.recipe.ItemOutputTransformer;
import dev.latvian.mods.kubejs.recipe.RecipeArguments;
import dev.latvian.mods.kubejs.recipe.RecipeJS;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class CompostRecipeJS extends RecipeJS {

  private static final String AMOUNT = "amount";
  private static final String INPUT = "input";

  private Ingredient input;
  private int amount;

  @Override
  public void create(RecipeArguments args) {
    this.input = this.parseItemInput(args.get(0));
    this.amount = args.getInt(1, 0);
  }

  @Override
  public void deserialize() {
    this.input = this.parseItemInput(this.json.get(INPUT));
    this.amount = this.json.get(AMOUNT).getAsInt();
  }

  @Override
  public void serialize() {
    if (this.serializeInputs) {
      this.json.add(INPUT, this.input.toJson());
    }
    this.json.addProperty(AMOUNT, this.amount);
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
    return false;
  }

  @Override
  public boolean replaceOutput(
      IngredientMatch ingredientMatch,
      ItemStack itemStack,
      ItemOutputTransformer itemOutputTransformer) {
    return false;
  }
}
