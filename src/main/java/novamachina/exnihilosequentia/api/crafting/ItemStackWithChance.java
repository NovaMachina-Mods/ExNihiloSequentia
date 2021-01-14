package novamachina.exnihilosequentia.api.crafting;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemStackWithChance {
    private static final String BASE_KEY = "item";
    private static final String CHANCE_KEY = "chance";
    private static final String COUNT_KEY = "count";
    private final float chance;
    private final ItemStack itemStack;

    public ItemStackWithChance(ItemStack itemStack, float chance) {
        this.itemStack = itemStack;
        this.chance = chance;
    }

    public static ItemStackWithChance deserialize(JsonElement json) {
        if (json.isJsonObject() && json.getAsJsonObject().has(BASE_KEY)) {
            final float chance = JSONUtils.getFloat(json.getAsJsonObject(), CHANCE_KEY, 1.0F);
            final String itemString = JSONUtils.getString(json.getAsJsonObject(), BASE_KEY);
            int count = 1;
            if (json.getAsJsonObject().has(COUNT_KEY)) {
                count = json.getAsJsonObject().get(COUNT_KEY).getAsInt();
            }
            return new ItemStackWithChance(new ItemStack(ForgeRegistries.ITEMS
                    .getValue(new ResourceLocation(itemString)), count), chance);
        } else {
            final String itemString = JSONUtils.getString(json, BASE_KEY);
            return new ItemStackWithChance(new ItemStack(ForgeRegistries.ITEMS
                    .getValue(new ResourceLocation(itemString))), 1.0F);
        }
    }

    public static ItemStackWithChance read(PacketBuffer buffer) {
        final ItemStack stack = buffer.readItemStack();
        final float chance = buffer.readFloat();
        return new ItemStackWithChance(stack, chance);
    }

    public float getChance() {
        return chance;
    }

    public ItemStack getStack() {
        return itemStack.copy();
    }

    public JsonElement serialize() {
        JsonObject json = new JsonObject();
        json.addProperty(CHANCE_KEY, getChance());
        json.addProperty(BASE_KEY, getStack().getItem().getRegistryName().toString());
        json.addProperty(COUNT_KEY, getStack().getCount());
        return json;
    }

    public void write(PacketBuffer buffer) {
        buffer.writeItemStack(getStack());
        buffer.writeFloat(getChance());
    }
}
