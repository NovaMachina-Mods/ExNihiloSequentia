package novamachina.exnihilosequentia.common.compat.kubejs;

import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.item.ItemStackJS;
import dev.latvian.mods.kubejs.util.ListJS;
import dev.latvian.mods.kubejs.util.MapJS;

public class HeatRecipeJS extends BaseRecipeJS {

  @Override
  public void create(ListJS args) {
    this.source(ItemStackJS.of(args.get(0)));
    this.amount(((Number) args.get(1)).intValue());
    this.properties((MapJS)args.get(2));
  }

  private void properties(MapJS jsonObject) {
    this.json.add("state", jsonObject.toJson());
  }

  private void source(ItemStackJS block) {
    this.json.addProperty("block", block.getId());
  }
  private void amount(int amount) {
    this.json.addProperty("amount", amount);
  }


  @Override
  public void deserialize() {

  }

  @Override
  public void serialize() {

  }
}
