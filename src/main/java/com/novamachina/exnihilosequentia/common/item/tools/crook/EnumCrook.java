package com.novamachina.exnihilosequentia.common.item.tools.crook;

import com.novamachina.exnihilosequentia.common.utility.Constants;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;
import net.minecraftforge.fml.RegistryObject;

public enum EnumCrook {
    WOOD(Constants.Items.CROOK_WOOD, 64, ItemTier.WOOD),

    STONE(Constants.Items.CROOK_STONE, 128, ItemTier.STONE),
    ANDESITE(Constants.Items.CROOK_ANDESITE, 128, ItemTier.STONE),
    GRANITE(Constants.Items.CROOK_GRANITE, 128, ItemTier.STONE),
    DIORITE(Constants.Items.CROOK_DIORITE, 128, ItemTier.STONE),

    GOLD(Constants.Items.CROOK_GOLD, 32, ItemTier.IRON),
    IRON(Constants.Items.CROOK_IRON, 256, ItemTier.IRON),
    DIAMOND(Constants.Items.CROOK_DIAMOND, 2048, ItemTier.DIAMOND),

    BONE(Constants.Items.CROOK_BONE, 256, ItemTier.STONE);
//    CLAY_UNCOOKED(Constants.Items.CROOK_CLAY_UNCOOKED, 0, 0, ItemTier.WOOD),
//    CLAY(Constants.Items.CROOK_CLAY, 256, 0, ItemTier.WOOD),

//    PRISMARINE(Constants.Items.CROOK_PRISMARINE, 512, 2, ItemTier.IRON),
//    NETHERRACK(Constants.Items.CROOK_NETHERRACK, 512, 2, ItemTier.IRON),
//    PURPUR(Constants.Items.CROOK_PURPUR, 512, 2, ItemTier.IRON),

//    BLAZE(Constants.Items.CROOK_BLAZE, 1024, 3, ItemTier.DIAMOND);

    public final String name;
    public final int defaultDurability;
    public final IItemTier teir;
    private RegistryObject<Item> registryObject;

    EnumCrook(String name, int durability, IItemTier teir) {
        this.name = name;
        this.defaultDurability = durability;
        this.teir = teir;
    }

    public RegistryObject<Item> getRegistryObject() {
        return registryObject;
    }

    public void setRegistryObject(RegistryObject<Item> registryObject) {
        this.registryObject = registryObject;
    }
}
