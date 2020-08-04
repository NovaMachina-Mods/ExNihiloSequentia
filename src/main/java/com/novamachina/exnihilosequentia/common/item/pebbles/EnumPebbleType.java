package com.novamachina.exnihilosequentia.common.item.pebbles;

import com.novamachina.exnihilosequentia.common.utility.Constants;

public enum EnumPebbleType {
    STONE(Constants.Items.PEBBLE_STONE),
    GRANITE(Constants.Items.PEBBLE_GRANITE),
    DIORITE(Constants.Items.PEBBLE_DIORITE),
    ANDESITE(Constants.Items.PEBBLE_ANDESITE);

    final String type;

    EnumPebbleType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
