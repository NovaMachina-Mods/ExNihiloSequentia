package novamachina.exnihilosequentia.common.compat.kubejs;

import com.google.gson.JsonArray;
import dev.latvian.mods.kubejs.item.ItemStackJS;
import dev.latvian.mods.kubejs.util.ListJS;

public class CrookRecipeJS extends BaseRecipeJS {

  @Override
  public void create(ListJS args) {
    this.inputItems.add(this.parseIngredientItem(args.get(0)));
  }

  public CrookRecipeJS addDrop(ItemStackJS item, int count, float chance) {
    DropWithChanceJS drop = new DropWithChanceJS(chance, count, item);

    if (this.json.has("results")) {
      this.json.get("results").getAsJsonArray().add(drop.toJson());
    } else {
      JsonArray array = new JsonArray();
      array.add(drop.toJson());
      this.json.add("results", array);
    }
    return this;
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
