package com.novamachina.exnihilosequentia.common.item.tools.hammer;

import com.novamachina.exnihilosequentia.common.utility.Constants;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;
import net.minecraftforge.fml.RegistryObject;

public enum EnumHammer {
    WOOD(Constants.Items.HAMMER_WOOD, 64, ItemTier.WOOD),
    STONE(Constants.Items.HAMMER_STONE, 125, ItemTier.STONE),
    IRON(Constants.Items.HAMMER_IRON, 512, ItemTier.IRON),
    DIAMOND(Constants.Items.HAMMER_DIAMOND, 4096, ItemTier.DIAMOND),
    GOLD(Constants.Items.HAMMER_GOLD, 64, ItemTier.GOLD);

    public final String name;
    public final int defaultDurability;
    public final IItemTier tier;
    private RegistryObject<Item> registryObject;

    EnumHammer(String name, int durability, IItemTier tier) {
        this.name = name;
        this.defaultDurability = durability;
        this.tier = tier;
    }

    public RegistryObject<Item> getRegistryObject() {
        return registryObject;
    }

    public void setRegistryObject(RegistryObject<Item> registryObject) {
        this.registryObject = registryObject;
    }
}
