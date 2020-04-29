package com.novamachina.ens.common.registry.registryitem;

import com.novamachina.ens.common.item.mesh.EnumMesh;
import net.minecraft.item.Item;

public class SieveRegistryItem {

    private final Item     drop;
    private final float    rarity;
    private final EnumMesh meshType;

    public SieveRegistryItem(Item drop, float rarity,
        EnumMesh meshType) {
        this.drop     = drop;
        this.rarity   = rarity;
        this.meshType = meshType;
    }

    public Item getDrop() {
        return drop;
    }

    public float getRarity() {
        return rarity;
    }

    public EnumMesh getMeshType() {
        return meshType;
    }
}
