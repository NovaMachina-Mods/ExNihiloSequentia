package com.novamachina.exnihilosequentia.common.item.pebbles;

import com.novamachina.exnihilosequentia.common.utility.Constants;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

public enum EnumPebbleType {
    STONE(Constants.Items.PEBBLE_STONE),
    GRANITE(Constants.Items.PEBBLE_GRANITE),
    DIORITE(Constants.Items.PEBBLE_DIORITE),
    ANDESITE(Constants.Items.PEBBLE_ANDESITE);

    private final String type;
    private RegistryObject<Item> registryObject;

    EnumPebbleType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public RegistryObject<Item> getRegistryObject() {
        return registryObject;
    }

    public void setRegistryObject(RegistryObject<Item> registryObject) {
        this.registryObject = registryObject;
    }
}
