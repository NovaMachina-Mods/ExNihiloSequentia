package novamachina.exnihilosequentia.world.item.crafting;

import com.google.common.base.Objects;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.Getter;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import org.checkerframework.checker.nullness.qual.NonNull;

public class ItemStackWithChance {

  private static final String BASE_KEY = "item";
  private static final String CHANCE_KEY = "chance";
  private static final String COUNT_KEY = "count";
  @Getter private final float chance;
  private final ItemStack itemStack;

  private ItemStackWithChance(ItemStack itemStack, float chance) {
    this.itemStack = itemStack;
    this.chance = chance;
  }

  public static ItemStackWithChance of(ItemStack itemStack) {
    return of(itemStack, 1.0F);
  }

  public static ItemStackWithChance of(ItemStack itemStack, int count) {
    itemStack.setCount(count);
    return of(itemStack, 1.0F);
  }

  public static ItemStackWithChance of(ItemStack itemStack, int count, float chance) {
    itemStack.setCount(count);
    return of(itemStack, chance);
  }

  public static ItemStackWithChance of(ItemStack itemStack, float chance) {
    return new ItemStackWithChance(itemStack, chance);
  }

  @NonNull
  public static ItemStackWithChance deserialize(JsonElement json) {
    if (json.isJsonObject() && json.getAsJsonObject().has(BASE_KEY)) {
      final float chance = GsonHelper.getAsFloat(json.getAsJsonObject(), CHANCE_KEY, 1.0F);
      String itemString = GsonHelper.getAsString(json.getAsJsonObject(), BASE_KEY);
      int count = 1;
      if (json.getAsJsonObject().has(COUNT_KEY)) {
        count = json.getAsJsonObject().get(COUNT_KEY).getAsInt();
      }
      return of(
          new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemString)), count),
          chance);
    } else {
      String itemString = GsonHelper.convertToString(json, BASE_KEY);
      return of(
          new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemString))), 1.0F);
    }
  }

  @NonNull
  public static ItemStackWithChance read(FriendlyByteBuf buffer) {
    ItemStack stack = buffer.readItem();
    final float chance = buffer.readFloat();
    return new ItemStackWithChance(stack, chance);
  }

  @NonNull
  public ItemStack getStack() {
    return itemStack.copy();
  }

  @NonNull
  public JsonElement serialize() {
    JsonObject json = new JsonObject();
    json.addProperty(CHANCE_KEY, getChance());
    ResourceLocation resourceLocation = ForgeRegistries.ITEMS.getKey(getStack().getItem());
    if (resourceLocation != null) {
      json.addProperty(BASE_KEY, resourceLocation.toString());
    }
    json.addProperty(COUNT_KEY, getStack().getCount());
    return json;
  }

  public void write(FriendlyByteBuf buffer) {
    buffer.writeItem(getStack());
    buffer.writeFloat(getChance());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ItemStackWithChance that = (ItemStackWithChance) o;
    return Float.compare(getChance(), that.getChance()) == 0
        && ItemStack.matches(itemStack, that.itemStack);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getChance(), itemStack);
  }
}
