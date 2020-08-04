package com.novamachina.exnihilosequentia.common.item.tools.crook;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class CrookDropEntry {

    private final ResourceLocation item;
    private final double rarity;

    public CrookDropEntry(ResourceLocation item, double rarity) {
        this.item   = item;
        this.rarity = rarity;
    }

    public ResourceLocation getItem() {
        return item;
    }

    public double getRarity() {
        return rarity;
    }
}
