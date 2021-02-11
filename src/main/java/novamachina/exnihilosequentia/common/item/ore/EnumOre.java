package novamachina.exnihilosequentia.common.item.ore;

import java.util.Arrays;
import java.util.List;
import net.minecraft.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.IExtensibleEnum;
import net.minecraftforge.fml.RegistryObject;
import novamachina.exnihilosequentia.common.network.HandshakeMessages;
import novamachina.exnihilosequentia.common.utility.Color;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

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

    private final Color color;
    private final String oreName;
    private RegistryObject<OreItem> chunkItem;
    private final boolean generateIngot;
    private Item ingotItem;
    private RegistryObject<OreItem> ingotRegistryItem;
    private boolean isEnabled;
    private RegistryObject<OreItem> pieceItem;
    private final Type type;

    EnumOre(Type type, String oreName, Color color, boolean isEnabled, boolean generateIngot) {
        this.type = type;
        this.oreName = oreName;
        this.color = color;
        this.isEnabled = isEnabled;
        this.generateIngot = generateIngot;
    }

    public static EnumOre create(String enumName, Type type, String oreName, Color color, boolean isEnabled, boolean generateIngot) {
        throw new IllegalStateException("Enum not extended");
    }

    @OnlyIn(Dist.CLIENT)
    public static boolean updateEnabledOres(HandshakeMessages.S2COreList message) {
        List<EnumOre> oreList = message.getOreList();
        Arrays.stream(EnumOre.values()).forEach(EnumOre::disable);
        if (oreList != null) {
            for (EnumOre ore : oreList) {
                EnumOre.valueOf(ore.name()).setEnabled(true);
            }
            return true;
        }
        return false;
    }

    public RegistryObject<OreItem> getChunkItem() {
        return chunkItem;
    }

    public void setChunkItem(RegistryObject<OreItem> chunkItem) {
        this.chunkItem = chunkItem;
    }

    public String getChunkName() {
        return "chunk_" + oreName;
    }

    public Color getColor() {
        return color;
    }

    public Item getIngotItem() {
        return ingotItem;
    }

    public void setIngotItem(Item ingotItem) {
        this.ingotItem = ingotItem;
    }

    public String getIngotName() {
        return "ingot_" + oreName;
    }

    public RegistryObject<OreItem> getIngotRegistryItem() {
        return ingotRegistryItem;
    }

    public void setIngotRegistryItem(RegistryObject<OreItem> ingotRegistryItem) {
        this.ingotRegistryItem = ingotRegistryItem;
    }

    public String getOreName() {
        return oreName;
    }

    public RegistryObject<OreItem> getPieceItem() {
        return pieceItem;
    }

    public void setPieceItem(RegistryObject<OreItem> pieceItem) {
        this.pieceItem = pieceItem;
    }

    public String getPieceName() {
        return "piece_" + oreName;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public boolean isVanilla() {
        return this.type == Type.VANILLA;
    }

    public boolean shouldGenerateIngot() {
        return generateIngot;
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

    public enum Type {
        VANILLA,
        MODDED
    }
}
