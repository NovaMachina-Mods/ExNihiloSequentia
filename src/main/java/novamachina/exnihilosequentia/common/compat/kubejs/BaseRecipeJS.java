package novamachina.exnihilosequentia.common.compat.kubejs;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.item.ingredient.IngredientStackJS;
import dev.latvian.mods.kubejs.recipe.RecipeJS;

public abstract class BaseRecipeJS extends RecipeJS {
  @Override
  public JsonElement serializeIngredientStack(IngredientStackJS in) {
    JsonObject o = new JsonObject();
    o.addProperty("count", in.getCount());
    o.add("value", in.ingredient.toJson());
    return o;
  }

  @Override
  public void deserialize() {
    this.inputItems.add(this.parseIngredientItem(this.json.get("input")));
    this.outputItems.add(this.parseResultItem(this.json.get("result")));
  }

  @Override
  public void serialize() {
    if (this.serializeInputs) {
      this.json.add("input", this.inputItems.get(0).toJson());
    }
    if (this.serializeOutputs) {
      this.json.add("result", this.outputItems.get(0).toJson());
    }
  }
}
