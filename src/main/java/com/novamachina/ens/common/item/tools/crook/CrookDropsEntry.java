package com.novamachina.ens.common.item.tools.crook;

import net.minecraft.item.Item;

public class CrookDropsEntry {

    private final Item   item;
    private final double rarity;

    public CrookDropsEntry(Item item, double rarity) {
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
