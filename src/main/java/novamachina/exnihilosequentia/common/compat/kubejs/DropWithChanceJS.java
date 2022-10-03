package novamachina.exnihilosequentia.common.compat.kubejs;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

public class DropWithChanceJS {

  private final float chance;
  private final int count;
  private Item item;

  public DropWithChanceJS(float chance, int count, Item item) {
    this.chance = chance;
    this.count = count;
    this.item = item;
  }

  public static DropWithChanceJS fromJson(JsonObject object) {
    return new DropWithChanceJS(
        object.get("chance").getAsFloat(),
        object.get("count").getAsInt(),
        ForgeRegistries.ITEMS.getValue(new ResourceLocation(object.get("item").getAsString())));
  }

  public Item getItem() {
    return item;
  }

  public void setItem(Item item) {
    this.item = item;
  }

  public JsonElement toJson() {
    JsonObject object = new JsonObject();
    object.addProperty("chance", chance);
    object.addProperty("count", count);
    object.addProperty("item", ForgeRegistries.ITEMS.getKey(this.item).toString());
    return object;
  }
}
