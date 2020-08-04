package com.novamachina.exnihilosequentia.common.item.ore;

import com.novamachina.exnihilosequentia.common.setup.ModInitialization;
import net.minecraft.item.Item;

public class OreItem extends Item {

    private final EnumOre ore;

    public OreItem(EnumOre ore) {
        super(new Item.Properties().group(ModInitialization.ITEM_GROUP));
        this.ore = ore;
    }

    public EnumOre getOre() {
        return ore;
    }
}
