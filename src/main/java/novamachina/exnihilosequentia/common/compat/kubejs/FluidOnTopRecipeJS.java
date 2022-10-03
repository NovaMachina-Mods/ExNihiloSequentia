package novamachina.exnihilosequentia.common.compat.kubejs;

import dev.latvian.mods.kubejs.fluid.FluidStackJS;
import dev.latvian.mods.kubejs.recipe.IngredientMatch;
import dev.latvian.mods.kubejs.recipe.ItemInputTransformer;
import dev.latvian.mods.kubejs.recipe.ItemOutputTransformer;
import dev.latvian.mods.kubejs.recipe.RecipeArguments;
import dev.latvian.mods.kubejs.recipe.RecipeJS;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class FluidOnTopRecipeJS extends RecipeJS {

  private FluidStackJS fluidInTank;
  private FluidStackJS fluidOnTop;
  private ItemStack result;

  @Override
  public void create(RecipeArguments args) {
    this.fluidInTank = FluidStackJS.of(args.get(0));
    this.fluidOnTop = FluidStackJS.of(args.get(1));
    this.result = this.parseItemOutput(args.get(2));
  }

  @Override
  public void deserialize() {
    this.fluidInTank = FluidStackJS.of(this.json.get("fluidInTank"));
    this.fluidOnTop = FluidStackJS.of(this.json.get("fluidOnTop"));
    this.result = this.parseItemOutput(this.json.get("result"));
  }

  @Override
  public void serialize() {
    if (this.serializeOutputs) {
      this.json.add("result", this.itemToJson(this.result));
    }
    this.json.add("fluidInTank", this.fluidInTank.toJson());
    this.json.add("fluidOnTop", this.fluidOnTop.toJson());
  }

  @Override
  public boolean hasInput(IngredientMatch ingredientMatch) {
    return false;
  }

  @Override
  public boolean replaceInput(
      IngredientMatch ingredientMatch,
      Ingredient ingredient,
      ItemInputTransformer itemInputTransformer) {
    return false;
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
