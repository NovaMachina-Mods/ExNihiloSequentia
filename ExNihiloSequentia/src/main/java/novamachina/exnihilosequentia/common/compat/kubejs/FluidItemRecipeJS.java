package novamachina.exnihilosequentia.common.compat.kubejs;

import dev.latvian.mods.kubejs.fluid.FluidStackJS;
import dev.latvian.mods.kubejs.recipe.IngredientMatch;
import dev.latvian.mods.kubejs.recipe.ItemInputTransformer;
import dev.latvian.mods.kubejs.recipe.ItemOutputTransformer;
import dev.latvian.mods.kubejs.recipe.RecipeArguments;
import dev.latvian.mods.kubejs.recipe.RecipeJS;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class FluidItemRecipeJS extends RecipeJS {

  private Ingredient input;
  private ItemStack result;
  private FluidStackJS fluid;

  @Override
  public void create(RecipeArguments args) {
    this.input = this.parseItemInput(args.get(0));
    this.result = this.parseItemOutput(args.get(1));
    this.fluid = FluidStackJS.of(args.get(2));
  }

  @Override
  public void deserialize() {
    this.input = this.parseItemInput(this.json.get("input"));
    this.result = this.parseItemOutput(this.json.get("result"));
    this.fluid = FluidStackJS.of(this.json.get("fluid"));
  }

  @Override
  public void serialize() {
    if (this.serializeInputs) {
      this.json.add("input", this.input.toJson());
    }
    if (this.serializeOutputs) {
      this.json.add("result", this.itemToJson(this.result));
    }
    this.json.add("fluid", this.fluid.toJson());
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
    if (ingredientMatch.contains(this.result)) {
      this.result = itemOutputTransformer.transform(this, ingredientMatch, this.result, itemStack);
      return true;
    } else {
      return false;
    }
  }
}
