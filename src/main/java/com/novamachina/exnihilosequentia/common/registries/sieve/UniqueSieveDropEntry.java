package com.novamachina.exnihilosequentia.common.registries.sieve;

import com.novamachina.exnihilosequentia.common.item.mesh.EnumMesh;
import net.minecraft.util.ResourceLocation;

public class UniqueSieveDropEntry {
    private ResourceLocation result;
    private float rarity;
    private EnumMesh mesh;
    private boolean isWaterlogged;

    public UniqueSieveDropEntry(ResourceLocation result, float rarity, EnumMesh mesh, boolean isWaterlogged) {
        this.result = result;
        this.rarity = rarity;
        this.mesh = mesh;
        this.isWaterlogged = isWaterlogged;
    }

    public ResourceLocation getResult() {
        return result;
    }

    public float getRarity() {
        return rarity;
    }

    public EnumMesh getMesh() {
        return mesh;
    }

    public boolean isWaterlogged() {
        return isWaterlogged;
    }
}
