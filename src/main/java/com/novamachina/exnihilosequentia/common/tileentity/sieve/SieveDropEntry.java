package com.novamachina.exnihilosequentia.common.tileentity.sieve;

import net.minecraft.util.ResourceLocation;

public class SieveDropEntry {

    private final ResourceLocation result;
    private final float rarity;

    public SieveDropEntry(ResourceLocation result, float rarity) {
        this.result = result;
        this.rarity = rarity;
    }

    public ResourceLocation getResult() {
        return result;
    }

    public float getRarity() {
        return rarity;
    }
}
