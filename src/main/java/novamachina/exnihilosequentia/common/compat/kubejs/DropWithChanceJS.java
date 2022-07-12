package novamachina.exnihilosequentia.common.compat.kubejs;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.item.ItemStackJS;

public class DropWithChanceJS {

  private final float chance;
  private final int count;
  private final ItemStackJS item;

  public DropWithChanceJS(float chance, int count, ItemStackJS item) {
    this.chance = chance;
    this.count = count;
    this.item = item;
  }

  public JsonElement toJson() {
    JsonObject object = new JsonObject();
    object.addProperty("chance", chance);
    object.addProperty("count", count);
    object.addProperty("item", item.getId());
    return object;
  }
}
