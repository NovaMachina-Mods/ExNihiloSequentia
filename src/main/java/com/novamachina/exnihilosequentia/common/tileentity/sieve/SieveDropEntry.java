package com.novamachina.exnihilosequentia.common.tileentity.sieve;

import net.minecraft.util.ResourceLocation;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SieveDropEntry entry = (SieveDropEntry) o;
        return result.equals(entry.result);
    }
}
