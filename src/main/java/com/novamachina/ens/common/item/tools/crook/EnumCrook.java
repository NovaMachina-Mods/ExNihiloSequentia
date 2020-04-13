package com.novamachina.ens.common.item.tools.crook;

import com.novamachina.ens.common.utility.Constants;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemTier;

public enum EnumCrook {
    WOOD(Constants.Items.CROOK_WOOD, 64, 0, ItemTier.WOOD),

    STONE(Constants.Items.CROOK_STONE, 128, 1, ItemTier.STONE),
    ANDESITE(Constants.Items.CROOK_ANDESITE, 128, 1, ItemTier.STONE),
    GRANITE(Constants.Items.CROOK_GRANITE, 128, 1, ItemTier.STONE),
    DIORITE(Constants.Items.CROOK_DIORITE, 128, 1, ItemTier.STONE),

    GOLD(Constants.Items.CROOK_GOLD, 32, 2, ItemTier.IRON),
    IRON(Constants.Items.CROOK_IRON, 256, 2, ItemTier.IRON),
    DIAMOND(Constants.Items.CROOK_DIAMOND, 2048, 3, ItemTier.DIAMOND),

    BONE(Constants.Items.CROOK_BONE, 256, 1, ItemTier.STONE);
//    CLAY_UNCOOKED(Constants.Items.CROOK_CLAY_UNCOOKED, 0, 0, ItemTier.WOOD),
//    CLAY(Constants.Items.CROOK_CLAY, 256, 0, ItemTier.WOOD),

//    PRISMARINE(Constants.Items.CROOK_PRISMARINE, 512, 2, ItemTier.IRON),
//    NETHERRACK(Constants.Items.CROOK_NETHERRACK, 512, 2, ItemTier.IRON),
//    PURPUR(Constants.Items.CROOK_PURPUR, 512, 2, ItemTier.IRON),

//    BLAZE(Constants.Items.CROOK_BLAZE, 1024, 3, ItemTier.DIAMOND);

    public final String    name;
    public final int       defaultDurability;
    public final IItemTier teir;
    public final int       defaultLevel;

    EnumCrook(String name, int durability, int miningLevel, IItemTier teir) {
        this.name              = name;
        this.defaultDurability = durability;
        this.defaultLevel      = miningLevel;
        this.teir              = teir;
    }
}
