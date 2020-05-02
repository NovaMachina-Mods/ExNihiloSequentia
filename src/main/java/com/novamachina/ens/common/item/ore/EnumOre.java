package com.novamachina.ens.common.item.ore;

import com.novamachina.ens.common.utility.Color;
import com.novamachina.ens.common.utility.Constants;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

public enum EnumOre {
    IRON(Constants.Ore.IRON, new Color("BF8040"), Items.IRON_INGOT),
    GOLD(Constants.Ore.GOLD, new Color("FFFF00"), Items.GOLD_INGOT);

    private final String name;
    private final Color  color;
    private final Item   result;

    EnumOre(String name, Color color, Item result) {
        this.name   = name;
        this.color  = color;
        this.result = result;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public Item getResult() {
        return result;
    }

    public String getChunkName() {
        return "item_" + name + "_chunk";
    }

    public String getPieceName() {
        return "item_" + name + "_piece";
    }
}
