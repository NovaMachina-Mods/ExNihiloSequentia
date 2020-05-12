package com.novamachina.ens.common.tileentity.sieve;

import net.minecraft.item.Item;

public class SieveDropEntry {

    private final Item  result;
    private final float rarity;

    public SieveDropEntry(Item result, float rarity) {
        this.result = result;
        this.rarity = rarity;
    }

    public Item getResult() {
        return result;
    }

    public float getRarity() {
        return rarity;
    }
}
