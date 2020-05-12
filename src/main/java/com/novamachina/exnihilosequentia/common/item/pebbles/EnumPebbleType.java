package com.novamachina.exnihilosequentia.common.item.pebbles;

import com.novamachina.exnihilosequentia.common.utility.Constants;

public enum EnumPebbleType {
    STONE(Constants.Items.STONE_PEBBLE),
    GRANITE(Constants.Items.GRANITE_PEBBLE),
    DIORITE(Constants.Items.DIORITE_PEBBLE),
    ANDESITE(Constants.Items.ANDESITE_PEBBLE);

    final String type;

    EnumPebbleType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
