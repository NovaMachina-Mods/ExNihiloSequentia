package com.novamachina.exnihilosequentia.common.item.mesh;

import net.minecraft.item.Item;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.fml.RegistryObject;

public enum EnumMesh implements IStringSerializable {
    NONE(0, "none"), STRING(1, "string"), FLINT(2, "flint"), IRON(3, "iron"), DIAMOND(4, "diamond");

    private final int id;
    private final String name;
    private RegistryObject<Item> registryObject;

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

    @Override
    public String getString() {
        return this.name;
    }

    public RegistryObject<Item> getRegistryObject() {
        return registryObject;
    }

    public void setRegistryObject(RegistryObject<Item> registryObject) {
        this.registryObject = registryObject;
    }
}
