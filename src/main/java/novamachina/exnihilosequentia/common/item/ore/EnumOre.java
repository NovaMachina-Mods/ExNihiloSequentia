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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

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

    @Nonnull private final Color color;
    @Nonnull private final String oreName;
    @Nullable private RegistryObject<OreItem> chunkItem;
    private final boolean generateIngot;
    @Nullable private Item ingotItem;
    @Nullable private RegistryObject<OreItem> ingotRegistryItem;
    private boolean isEnabled;
    @Nullable private RegistryObject<OreItem> pieceItem;
    private final Type type;

    EnumOre(@Nonnull final Type type, @Nonnull final String oreName, @Nonnull final Color color,
            final boolean isEnabled, final boolean generateIngot) {
        this.type = type;
        this.oreName = oreName;
        this.color = color;
        this.isEnabled = isEnabled;
        this.generateIngot = generateIngot;
    }

    public static EnumOre create(@Nonnull final String enumName, @Nonnull final Type type,
                                 @Nonnull final String oreName, @Nonnull final Color color, final boolean isEnabled,
                                 final boolean generateIngot) {
        throw new IllegalStateException("Enum not extended");
    }

    @OnlyIn(Dist.CLIENT)
    public static boolean updateEnabledOres(@Nonnull final HandshakeMessages.S2COreList message) {
        @Nullable final List<EnumOre> oreList = message.getOreList();
        Arrays.stream(EnumOre.values()).forEach(EnumOre::disable);
        if (oreList != null) {
            for (EnumOre ore : oreList) {
                EnumOre.valueOf(ore.name()).setEnabled(true);
            }
            return true;
        }
        return false;
    }

    @Nullable
    public RegistryObject<OreItem> getChunkItem() {
        return chunkItem;
    }

    public void setChunkItem(@Nonnull final RegistryObject<OreItem> chunkItem) {
        this.chunkItem = chunkItem;
    }

    @Nonnull
    public String getChunkName() {
        return "chunk_" + oreName;
    }

    @Nonnull
    public Color getColor() {
        return color;
    }

    @Nullable
    public Item getIngotItem() {
        return ingotItem;
    }

    public void setIngotItem(@Nonnull final Item ingotItem) {
        this.ingotItem = ingotItem;
    }

    @Nonnull
    public String getIngotName() {
        return "ingot_" + oreName;
    }

    @Nullable
    public RegistryObject<OreItem> getIngotRegistryItem() {
        return ingotRegistryItem;
    }

    public void setIngotRegistryItem(@Nonnull final RegistryObject<OreItem> ingotRegistryItem) {
        this.ingotRegistryItem = ingotRegistryItem;
    }

    @Nonnull
    public String getOreName() {
        return oreName;
    }

    @Nullable
    public RegistryObject<OreItem> getPieceItem() {
        return pieceItem;
    }

    public void setPieceItem(@Nonnull final RegistryObject<OreItem> pieceItem) {
        this.pieceItem = pieceItem;
    }

    @Nonnull
    public String getPieceName() {
        return "piece_" + oreName;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(final boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public boolean isVanilla() {
        return this.type == Type.VANILLA;
    }

    public boolean shouldGenerateIngot() {
        return generateIngot;
    }

    @Override
    @Nonnull
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
