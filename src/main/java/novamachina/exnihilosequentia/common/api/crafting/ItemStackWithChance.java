package novamachina.exnihilosequentia.common.api.crafting;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemStackWithChance {
    private static final String CHANCE_KEY = "chance";
    private static final String BASE_KEY = "item";
    private final ItemStack itemStack;
    private final float chance;

    public ItemStackWithChance(ItemStack itemStack, float chance) {
        this.itemStack = itemStack;
        this.chance = chance;
    }

    public static ItemStackWithChance read(PacketBuffer buffer) {
        final ItemStack stack = buffer.readItemStack();
        final float chance = buffer.readFloat();
        return new ItemStackWithChance(stack, chance);
    }

    public static ItemStackWithChance deserialize(JsonElement json) {
        if (json.isJsonObject() && json.getAsJsonObject().has(BASE_KEY)) {
            final float chance = JSONUtils.getFloat(json.getAsJsonObject(), CHANCE_KEY, 1.0F);
            final String itemString = JSONUtils.getString(json.getAsJsonObject(), BASE_KEY);
            return new ItemStackWithChance(new ItemStack(ForgeRegistries.ITEMS
                .getValue(new ResourceLocation(itemString))), chance);
        } else {
            final String itemString = JSONUtils.getString(json, BASE_KEY);
            return new ItemStackWithChance(new ItemStack(ForgeRegistries.ITEMS
                .getValue(new ResourceLocation(itemString))), 1.0F);
        }
    }

    public ItemStack getStack() {
        return itemStack;
    }

    public float getChance() {
        return chance;
    }

    public void write(PacketBuffer buffer) {
        buffer.writeItemStack(getStack());
        buffer.writeFloat(getChance());
    }

    public JsonElement serialize() {
        JsonObject json = new JsonObject();
        json.addProperty(CHANCE_KEY, getChance());
        json.addProperty(BASE_KEY, getStack().getItem().getRegistryName().toString());
        return json;
    }
}
