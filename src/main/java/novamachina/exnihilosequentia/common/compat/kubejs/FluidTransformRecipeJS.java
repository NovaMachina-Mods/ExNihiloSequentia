package novamachina.exnihilosequentia.common.compat.kubejs;

import dev.latvian.mods.kubejs.fluid.FluidStackJS;
import dev.latvian.mods.kubejs.item.ingredient.IngredientJS;
import dev.latvian.mods.kubejs.recipe.IngredientMatch;
import dev.latvian.mods.kubejs.recipe.ItemInputTransformer;
import dev.latvian.mods.kubejs.recipe.ItemOutputTransformer;
import dev.latvian.mods.kubejs.recipe.RecipeArguments;
import dev.latvian.mods.kubejs.recipe.RecipeJS;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class FluidTransformRecipeJS extends RecipeJS {

  private Ingredient catalyst;
  private FluidStackJS fluidInTank;
  private FluidStackJS result;

  @Override
  public void create(RecipeArguments args) {
    this.fluidInTank = FluidStackJS.of(args.get(0));
    this.result = FluidStackJS.of(args.get(1));
    this.catalyst = this.parseItemInput(args.get(2));
  }

  @Override
  public void deserialize() {
    this.fluidInTank = FluidStackJS.of(this.json.get("fluidInTank"));
    this.result = FluidStackJS.of(this.json.get("result"));
    this.catalyst = IngredientJS.of(this.json.get("catalyst"));
  }

  @Override
  public void serialize() {
    if (this.serializeInputs) {
      this.json.add("catalyst", this.catalyst.toJson());
      this.json.add("fluidInTank", this.fluidInTank.toJson());
    }
    if (this.serializeOutputs) {
      this.json.add("result", this.result.toJson());
    }
  }

  @Override
  public boolean hasInput(IngredientMatch ingredientMatch) {
    return ingredientMatch.contains(this.catalyst);
  }

  @Override
  public boolean replaceInput(
      IngredientMatch ingredientMatch,
      Ingredient ingredient,
      ItemInputTransformer itemInputTransformer) {
    if (ingredientMatch.contains(this.catalyst)) {
      this.catalyst =
          itemInputTransformer.transform(this, ingredientMatch, this.catalyst, ingredient);
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
