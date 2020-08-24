package com.novamachina.exnihilosequentia.common.item;

import com.novamachina.exnihilosequentia.common.init.ModInitialization;
import net.minecraft.item.Food;
import net.minecraft.item.Item;

public class CookedSilkwormItem extends Item {

    public CookedSilkwormItem() {
        super(new Item.Properties().group(ModInitialization.ITEM_GROUP)
            .food(new Food.Builder().hunger(2).saturation(0.6F).build()));
    }
}
