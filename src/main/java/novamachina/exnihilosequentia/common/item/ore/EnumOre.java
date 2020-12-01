package novamachina.exnihilosequentia.common.item.ore;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import novamachina.exnihilosequentia.common.network.HandshakeMessages;
import novamachina.exnihilosequentia.common.utility.Color;
import novamachina.exnihilosequentia.common.utility.Constants;
import net.minecraftforge.fml.RegistryObject;

import java.util.Arrays;
import java.util.List;

public enum EnumOre {
    COPPER(Type.MODDED, Constants.Ore.COPPER, new Color("FF9933"), false),
    LEAD(Type.MODDED, Constants.Ore.LEAD, new Color("330066"), false),
    NICKEL(Type.MODDED, Constants.Ore.NICKEL, new Color("FFFFCC"), false),
    SILVER(Type.MODDED, Constants.Ore.SILVER, new Color("C2FAFF"), false),
    TIN(Type.MODDED, Constants.Ore.TIN, new Color("F5FEFF"), false),
    ALUMINUM(Type.MODDED, Constants.Ore.ALUMINUM, new Color("BFBFBF"), false),
    PLATINUM(Type.MODDED, Constants.Ore.PLATINUM, new Color("00FFF7"), false),
    OSMIUM(Type.MODDED, Constants.Ore.OSMIUM, new Color("BBDDFF"), false),
    URANIUM(Type.MODDED, Constants.Ore.URANIUM, new Color("4E5B43"), false),
    ZINC(Type.MODDED, Constants.Ore.ZINC, new Color("A59C74"), false),
    IRON(Type.VANILLA, Constants.Ore.IRON, new Color("BF8040"), true),
    GOLD(Type.VANILLA, Constants.Ore.GOLD, new Color("FFFF00"), true),
    BISMUTH(Type.MODDED, Constants.Ore.BISMUTH, new Color("908194"), false);

    private final String name;
    private final Color color;
    private boolean isEnabled;
    private RegistryObject<OreItem> pieceItem;
    private RegistryObject<OreItem> chunkItem;
    private RegistryObject<OreItem> ingotItem;
    private Type type;

    EnumOre(Type type, String name, Color color, boolean isEnabled) {
        this.type = type;
        this.name = name;
        this.color = color;
        this.isEnabled = isEnabled;
    }

    @OnlyIn(Dist.CLIENT)
    public static boolean updateEnabledOres(HandshakeMessages.S2COreList message) {
        List<EnumOre> oreList = message.getOreList();
        Arrays.stream(EnumOre.values()).forEach(EnumOre::disable);
        if (oreList != null) {
            for (EnumOre ore : oreList) {
                EnumOre.valueOf(ore.name()).setEnabled();
            }
            return true;
        }
        return false;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled() {
        isEnabled = true;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public String getChunkName() {
        return "chunk_" + name;
    }

    public String getPieceName() {
        return "piece_" + name;
    }

    public String getIngotName() {
        return "ingot_" + name;
    }

    public RegistryObject<OreItem> getPieceItem() {
        return pieceItem;
    }

    public void setPieceItem(RegistryObject<OreItem> pieceItem) {
        this.pieceItem = pieceItem;
    }

    public RegistryObject<OreItem> getChunkItem() {
        return chunkItem;
    }

    public void setChunkItem(RegistryObject<OreItem> chunkItem) {
        this.chunkItem = chunkItem;
    }

    public RegistryObject<OreItem> getIngotItem() {
        return ingotItem;
    }

    public void setIngotItem(RegistryObject<OreItem> ingotItem) {
        this.ingotItem = ingotItem;
    }

    public boolean isVanilla() {
        return this.type == Type.VANILLA;
    }

    @Override
    public String toString() {
        return "EnumOre{" +
            "name='" + name + '\'' +
            ", color=" + color +
            ", isEnabled=" + isEnabled +
            ", type=" + type +
            '}';
    }

    private void disable() {
        isEnabled = false;
    }

    private enum Type {
        VANILLA,
        MODDED
    }
}
