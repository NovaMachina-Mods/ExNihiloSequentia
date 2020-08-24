package com.novamachina.exnihilosequentia.common.item.mesh;

import net.minecraft.util.IStringSerializable;

public enum EnumMesh implements IStringSerializable {
    NONE(0, "none"), STRING(1, "string"), FLINT(2, "flint"), IRON(3, "iron"), DIAMOND(4, "diamond");

    private final int id;
    private final String name;

    EnumMesh(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMeshName() {
        return "mesh_" + name;
    }
}
