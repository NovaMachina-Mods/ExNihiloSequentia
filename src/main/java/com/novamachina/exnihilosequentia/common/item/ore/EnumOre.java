package com.novamachina.exnihilosequentia.common.item.ore;

import com.novamachina.exnihilosequentia.common.utility.Color;
import com.novamachina.exnihilosequentia.common.utility.Constants;

public enum EnumOre implements IOre {
    IRON(Constants.Ore.IRON, new Color("BF8040"), true),
    GOLD(Constants.Ore.GOLD, new Color("FFFF00"), true);

    private final String name;
    private final Color color;
    private final boolean isEnabled;


    EnumOre(String name, Color color, boolean isEnabled) {
        this.name = name;
        this.color = color;
        this.isEnabled = isEnabled;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public String getChunkName() {
        return "chunk_" + name;
    }

    @Override
    public String getPieceName() {
        return "piece_" + name;
    }

    @Override
    public String getIngotName() {
        return "ingot_" + name;
    }
}
