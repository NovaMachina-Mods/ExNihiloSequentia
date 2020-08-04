package com.novamachina.exnihilosequentia.common.tileentity.sieve;

import com.novamachina.exnihilosequentia.common.item.mesh.EnumMesh;

public class SieveJson {
    private final String result;
    private final float rarity;
    private final EnumMesh mesh;

    public SieveJson(String result, float rarity, EnumMesh mesh) {
        this.result = result;
        this.rarity = rarity;
        this.mesh = mesh;
    }

    public String getResult() {
        return result;
    }

    public float getRarity() {
        return rarity;
    }

    public EnumMesh getMesh() {
        return mesh;
    }
}
