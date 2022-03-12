package novamachina.exnihilosequentia.common.item.ore;

import java.util.Arrays;
import java.util.List;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.IExtensibleEnum;
import net.minecraftforge.registries.RegistryObject;
import novamachina.exnihilosequentia.common.network.HandshakeMessages;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public enum EnumOre implements IExtensibleEnum {
    LEAD(Type.MODDED, ExNihiloConstants.Ore.LEAD, false, true),
    NICKEL(Type.MODDED, ExNihiloConstants.Ore.NICKEL, false, true),
    SILVER(Type.MODDED, ExNihiloConstants.Ore.SILVER, false, true),
    TIN(Type.MODDED, ExNihiloConstants.Ore.TIN, false, true),
    ALUMINUM(Type.MODDED, ExNihiloConstants.Ore.ALUMINUM, false, true),
    PLATINUM(Type.MODDED, ExNihiloConstants.Ore.PLATINUM, false, true),
    URANIUM(Type.MODDED, ExNihiloConstants.Ore.URANIUM, false, true),
    ZINC(Type.MODDED, ExNihiloConstants.Ore.ZINC, false, true),
    COPPER(Type.VANILLA, ExNihiloConstants.Ore.COPPER, true, false),
    IRON(Type.VANILLA, ExNihiloConstants.Ore.IRON, true, false),
    GOLD(Type.VANILLA, ExNihiloConstants.Ore.GOLD, true, false);

    @Nonnull private final String oreName;
    @Nullable private RegistryObject<OreItem> chunkItem;
    private final boolean generateIngot;
    @Nullable private Item ingotItem;
    @Nullable private RegistryObject<OreItem> ingotRegistryItem;
    private boolean isEnabled;
    @Nullable private RegistryObject<OreItem> pieceItem;
    private final Type type;

    EnumOre(@Nonnull final Type type, @Nonnull final String oreName,
            final boolean isEnabled, final boolean generateIngot) {
        this.type = type;
        this.oreName = oreName;
        this.isEnabled = isEnabled;
        this.generateIngot = generateIngot;
    }

    @SuppressWarnings("unused")
    public static EnumOre create(@Nonnull final String enumName, @Nonnull final Type type,
                                 @Nonnull final String oreName, final boolean isEnabled,
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
        return "raw_" + oreName;
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
        return oreName + "_ingot";
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
        return oreName + "_piece";
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
