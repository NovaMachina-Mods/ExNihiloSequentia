package com.novamachina.exnihilosequentia.common.item.resources;

import com.novamachina.exnihilosequentia.common.utility.Constants.Items;

public enum EnumResource {
    ANCIENT_SPORE(Items.ANCIENT_SPORE),
    GRASS_SEED(Items.GRASS_SEED),
    SILKWORM(Items.SILKWORM),
    PORCELAIN_CLAY(Items.PORCELAIN_CLAY);

    public String getResourceName() {
        return resourceName;
    }

    private final String resourceName;

    EnumResource(String resourceName) {
        this.resourceName = resourceName;
    }
}
