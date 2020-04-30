package com.novamachina.ens.common.item.ore;

import com.novamachina.ens.common.setup.ModInitialization;
import net.minecraft.item.Item;

public class OreItem extends Item {

    private final Ore ore;

    public OreItem(Ore ore) {
        super(new Item.Properties().group(ModInitialization.ITEM_GROUP));
        this.ore = ore;
    }

    public Ore getOre() {
        return ore;
    }
}
