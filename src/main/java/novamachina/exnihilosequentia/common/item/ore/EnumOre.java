package novamachina.exnihilosequentia.common.item.ore;

import net.minecraft.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.IExtensibleEnum;
import net.minecraftforge.fml.RegistryObject;
import novamachina.exnihilosequentia.common.network.HandshakeMessages;
import novamachina.exnihilosequentia.common.utility.Color;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

import java.util.Arrays;
import java.util.List;

public enum EnumOre implements IExtensibleEnum {
    COPPER(Type.MODDED, ExNihiloConstants.Ore.COPPER, new Color("FF9933"), false, true),
    LEAD(Type.MODDED, ExNihiloConstants.Ore.LEAD, new Color("330066"), false, true),
    NICKEL(Type.MODDED, ExNihiloConstants.Ore.NICKEL, new Color("FFFFCC"), false, true),
    SILVER(Type.MODDED, ExNihiloConstants.Ore.SILVER, new Color("C2FAFF"), false, true),
    TIN(Type.MODDED, ExNihiloConstants.Ore.TIN, new Color("F5FEFF"), false, true),
    ALUMINUM(Type.MODDED, ExNihiloConstants.Ore.ALUMINUM, new Color("BFBFBF"), false, true),
    PLATINUM(Type.MODDED, ExNihiloConstants.Ore.PLATINUM, new Color("00FFF7"), false, true),
    URANIUM(Type.MODDED, ExNihiloConstants.Ore.URANIUM, new Color("4E5B43"), false, true),
    ZINC(Type.MODDED, ExNihiloConstants.Ore.ZINC, new Color("A59C74"), false, true),
    IRON(Type.VANILLA, ExNihiloConstants.Ore.IRON, new Color("BF8040"), true, false),
    GOLD(Type.VANILLA, ExNihiloConstants.Ore.GOLD, new Color("FFFF00"), true, false);
//    BISMUTH(Type.MODDED, ExNihiloConstants.Ore.BISMUTH, new Color("908194"), false);

    private final String oreName;
    private final Color color;
    private boolean isEnabled;
    private boolean generateIngot;
    private RegistryObject<OreItem> pieceItem;
    private RegistryObject<OreItem> chunkItem;
    private RegistryObject<OreItem> ingotRegistryItem;
    private Item ingotItem;
    private Type type;

    EnumOre(Type type, String oreName, Color color, boolean isEnabled, boolean generateIngot) {
        this.type = type;
        this.oreName = oreName;
        this.color = color;
        this.isEnabled = isEnabled;
        this.generateIngot = generateIngot;
    }

    public static EnumOre create(String name, Type type, String oreName, Color color, boolean isEnabled, boolean generateIngot) {
        throw new IllegalStateException("Enum not extended");
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

    public String getOreName() {
        return oreName;
    }

    public Color getColor() {
        return color;
    }

    public String getChunkName() {
        return "chunk_" + oreName;
    }

    public String getPieceName() {
        return "piece_" + oreName;
    }

    public String getIngotName() {
        return "ingot_" + oreName;
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

    public Item getIngotItem() {
        return ingotItem;
    }

    public RegistryObject<OreItem> getIngotRegistryItem() {
        return ingotRegistryItem;
    }

    public void setIngotRegistryItem(RegistryObject<OreItem> ingotRegistryItem) {
        this.ingotRegistryItem = ingotRegistryItem;
    }

    public void setIngotItem(Item ingotItem) {
        this.ingotItem = ingotItem;
    }

    public boolean isVanilla() {
        return this.type == Type.VANILLA;
    }

    @Override
    public String toString() {
        return "EnumOre{" +
            "name='" + oreName + '\'' +
            ", color=" + color +
            ", isEnabled=" + isEnabled +
            ", type=" + type +
            '}';
    }

    private void disable() {
        isEnabled = false;
    }

    public boolean shouldGenerateIngot() {
        return generateIngot;
    }

    public enum Type {
        VANILLA,
        MODDED
    }
}
