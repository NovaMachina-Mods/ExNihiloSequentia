package com.novamachina.ens.common.item.tools.crook;

import net.minecraft.item.Item;

public class CrookDropEntry {

    private final Item   item;
    private final double rarity;

    public CrookDropEntry(Item item, double rarity) {
        this.item   = item;
        this.rarity = rarity;
    }

    public Item getItem() {
        return item;
    }

    public double getRarity() {
        return rarity;
    }
}
