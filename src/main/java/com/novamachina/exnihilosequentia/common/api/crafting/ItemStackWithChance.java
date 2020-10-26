package com.novamachina.exnihilosequentia.common.api.crafting;

import com.google.gson.JsonElement;
import com.novamachina.exnihilosequentia.common.crafting.serializer.ItemStackWithChanceSerializer;
import net.minecraft.item.ItemStack;

public class ItemStackWithChance {
    private final ItemStack itemStack;
    private final float chance;

    public ItemStackWithChance(ItemStack itemStack, float chance) {
        this.itemStack = itemStack;
        this.chance = chance;
    }

    public static ItemStackWithChance deserialize(JsonElement input) {
        return ItemStackWithChanceSerializer.INSTANCE.parse(input);
    }

    public ItemStack getStack() {
        return itemStack;
    }

    public float getChance() {
        return chance
    }
}
