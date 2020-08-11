package com.novamachina.exnihilosequentia.common.item.ore;

import com.novamachina.exnihilosequentia.common.setup.ModInitialization;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class OreItem extends Item {

    private final IOre ore;

    public OreItem(IOre ore) {
        super(new Item.Properties().group(ModInitialization.ITEM_GROUP));
        this.ore = ore;
    }

    public IOre getOre() {
        return ore;
    }

    @Override
    protected boolean isInGroup(ItemGroup group) {
        return ore.isEnabled();
    }
}
