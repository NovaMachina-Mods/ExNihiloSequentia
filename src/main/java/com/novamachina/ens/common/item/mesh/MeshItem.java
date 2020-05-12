package com.novamachina.ens.common.item.mesh;

import com.novamachina.ens.common.setup.ModInitialization;
import net.minecraft.item.Item;

public class MeshItem extends Item {

    private final EnumMesh mesh;

    public MeshItem(EnumMesh mesh) {
        super(new Item.Properties().group(ModInitialization.ITEM_GROUP));
        this.mesh = mesh;
    }

    public EnumMesh getMesh() {
        return mesh;
    }
}
