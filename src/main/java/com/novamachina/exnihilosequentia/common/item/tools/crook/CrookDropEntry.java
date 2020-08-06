package com.novamachina.exnihilosequentia.common.item.tools.crook;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class CrookDropEntry {

    private final ResourceLocation item;
    private final float rarity;

    public CrookDropEntry(ResourceLocation item, float rarity) {
        this.item   = item;
        this.rarity = rarity;
    }

    public ResourceLocation getItem() {
        return item;
    }

    public float getRarity() {
        return rarity;
    }
}
