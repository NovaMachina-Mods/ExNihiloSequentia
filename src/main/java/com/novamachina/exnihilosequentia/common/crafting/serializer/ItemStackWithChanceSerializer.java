package com.novamachina.exnihilosequentia.common.crafting.serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.novamachina.exnihilosequentia.common.api.crafting.ItemStackWithChance;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;

public class ItemStackWithChanceSerializer {
    public static final ItemStackWithChanceSerializer INSTANCE = new ItemStackWithChanceSerializer();

    private static final String CHANCE_KEY = "chance";
    private static final String BASE_KEY = "base_itemstack";

    public ItemStackWithChance parse(PacketBuffer buffer) {
        final ItemStack stack = buffer.readItemStack();
        final float chance = buffer.readFloat();
        return new ItemStackWithChance(stack, chance);
    }

    public void write(PacketBuffer buffer, ItemStackWithChance itemStack) {
        buffer.writeItemStack(itemStack.getStack());
        buffer.writeFloat(itemStack.getChance());
    }

    public ItemStackWithChance parse(JsonElement json){
        if(json.isJsonObject() && json.getAsJsonObject().has(BASE_KEY)) {
            final float chance = JSONUtils.getFloat(json.getAsJsonObject(), CHANCE_KEY, 1.0F);
            final JsonElement baseJson = json.getAsJsonObject().get(BASE_KEY);
            final ItemStack base = new ItemStack(JSONUtils.getItem(baseJson, "item"));
            return new ItemStackWithChance(base, chance);
        } else {
            final ItemStack base = new ItemStack(JSONUtils.getItem(json, "item"));
            return new ItemStackWithChance(base, 1.0F);
        }
    }

    public JsonElement write(ItemStackWithChance itemStack) {
        JsonObject json
    }
}
