package com.novamachina.exnihilosequentia.common.item.ore;

import com.novamachina.exnihilosequentia.common.utility.Color;
import com.novamachina.exnihilosequentia.common.utility.Constants;
import net.minecraftforge.fml.RegistryObject;

public enum EnumModdedOre implements IOre {
    COPPER(Constants.Ore.COPPER, new Color("FF9933"), false),
    LEAD(Constants.Ore.LEAD, new Color("330066"), false),
    NICKEL(Constants.Ore.NICKEL, new Color("FFFFCC"), false),
    SILVER(Constants.Ore.SILVER, new Color("C2FAFF"), false),
    TIN(Constants.Ore.TIN, new Color("F5FEFF"), false),
    ALUMINUM(Constants.Ore.ALUMINUM, new Color("BFBFBF"), false),
    PLATINUM(Constants.Ore.PLATINUM, new Color("00FFF7"), false),
    OSMIUM(Constants.Ore.OSMIUM, new Color("BBDDFF"), false),
    URANIUM(Constants.Ore.URANIUM, new Color("4E5B43"), false),
    ZINC(Constants.Ore.ZINC, new Color("A59C74"), false);

    private final String name;
    private final Color color;
    private boolean isEnabled;
    private RegistryObject<OreItem> pieceItem;
    private RegistryObject<OreItem> chunkItem;
    private RegistryObject<OreItem> ingotItem;

    EnumModdedOre(String name, Color color, boolean isEnabled) {
        this.name = name;
        this.color = color;
        this.isEnabled = isEnabled;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled() {
        isEnabled = true;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Color getColor() {
        return color;
    }

    public String getChunkName() {
        return "chunk_" + name;
    }

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

    public RegistryObject<OreItem> getIngotItem() {
        return ingotItem;
    }

    public void setIngotItem(RegistryObject<OreItem> ingotItem) {
        this.ingotItem = ingotItem;
    }
}
