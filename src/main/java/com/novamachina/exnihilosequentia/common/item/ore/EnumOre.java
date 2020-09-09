package com.novamachina.exnihilosequentia.common.item.ore;

import com.novamachina.exnihilosequentia.common.utility.Color;
import com.novamachina.exnihilosequentia.common.utility.Constants;
import net.minecraftforge.fml.RegistryObject;

public enum EnumOre implements IOre {
    IRON(Constants.Ore.IRON, new Color("BF8040"), true),
    GOLD(Constants.Ore.GOLD, new Color("FFFF00"), true);

    private final String name;
    private final Color color;
    private final boolean isEnabled;
    private RegistryObject<OreItem> pieceItem;
    private RegistryObject<OreItem> chunkItem;

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

    @Override
    public RegistryObject<OreItem> getPieceItem() {
        return pieceItem;
    }

    @Override
    public void setPieceItem(RegistryObject<OreItem> pieceItem) {
        this.pieceItem = pieceItem;
    }

    @Override
    public RegistryObject<OreItem> getChunkItem() {
        return chunkItem;
    }

    @Override
    public void setChunkItem(RegistryObject<OreItem> chunkItem) {
        this.chunkItem = chunkItem;
    }
}
