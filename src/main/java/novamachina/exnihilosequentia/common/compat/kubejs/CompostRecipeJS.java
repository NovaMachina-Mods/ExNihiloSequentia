package novamachina.exnihilosequentia.common.compat.kubejs;

import dev.latvian.mods.kubejs.recipe.RecipeArguments;

public class CompostRecipeJS extends BaseRecipeJS {

  @Override
  public void create(RecipeArguments args) {
    this.inputItems.add(this.parseIngredientItem(args.get(0)));
    this.amount(args.getInt(1, 0));
  }

  private void amount(int amount) {
    this.json.addProperty("amount", amount);
  }

  @Override
  public void deserialize() {
    this.inputItems.add(this.parseIngredientItem(this.json.get("input")));
  }

  @Override
  public void serialize() {
    if (this.serializeInputs) {
      this.json.add("input", this.inputItems.get(0).toJson());
    }
  }
}
