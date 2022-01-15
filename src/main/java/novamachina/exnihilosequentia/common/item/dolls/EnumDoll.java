package novamachina.exnihilosequentia.common.item.dolls;

import net.minecraft.world.item.Item;
import net.minecraftforge.common.IExtensibleEnum;
import net.minecraftforge.registries.RegistryObject;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public enum EnumDoll implements IExtensibleEnum {
    BLAZE(ExNihiloConstants.ModIds.MINECRAFT, "blaze", "minecraft", "lava", 1, ExNihiloConstants.Tooltips.BLAZE),
    ENDERMAN(ExNihiloConstants.ModIds.MINECRAFT, "enderman", ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, ExNihiloConstants.Fluids.WITCH_WATER, 2, ExNihiloConstants.Tooltips.ENDERMAN),
    SHULKER(ExNihiloConstants.ModIds.MINECRAFT, "shulker", ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, ExNihiloConstants.Fluids.WITCH_WATER, 1.5, ExNihiloConstants.Tooltips.SHULKER),
    GUARDIAN(ExNihiloConstants.ModIds.MINECRAFT, "guardian", ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, ExNihiloConstants.Fluids.SEA_WATER, 1, ExNihiloConstants.Tooltips.GUARDIAN),
    BEE(ExNihiloConstants.ModIds.MINECRAFT, "bee", ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, ExNihiloConstants.Fluids.WITCH_WATER, 1, ExNihiloConstants.Tooltips.BEE);


    @Nonnull private final String entityModId;
    @Nonnull private final String entityName;
    @Nonnull private final String fluidModId;
    @Nonnull private final String fluidName;
    @Nonnull private final String tooltip;
    private final double yOffset;
    @Nullable private RegistryObject<Item> registryObject;

    EnumDoll(@Nonnull final String entityModId, @Nonnull final String entityName, @Nonnull final String fluidModId,
             @Nonnull final String fluidName, double yOffset, @Nonnull final String tooltip) {
        this.entityModId = entityModId;
        this.entityName = entityName;
        this.fluidModId = fluidModId;
        this.fluidName = fluidName;
        this.yOffset = yOffset;
        this.tooltip = tooltip;
    }

    @Nonnull
    @SuppressWarnings("unused")
    public static EnumDoll create(@Nonnull final String enumName, @Nonnull final String entityModId,
                                  @Nonnull final String entityName, @Nonnull final String fluidModId,
                                  @Nonnull final String fluidName, final double yOffset,
                                  @Nonnull final String tooltip) {
        throw new IllegalStateException("Enum not extended");
    }

    @Nullable
    public static EnumDoll getDollFromString(@Nonnull final String dollType) {
        for (EnumDoll doll : values()) {
            if (doll.name().equalsIgnoreCase(dollType)) {
                return doll;
            }
        }
        return null;
    }

    @Nonnull
    public String getDollName() {
        return "doll_" + entityName;
    }

    @Nonnull
    public String getEntityModId() {
        return entityModId;
    }

    @Nonnull
    public String getEntityName() {
        return entityName;
    }

    @Nonnull
    public String getFluidModId() {
        return fluidModId;
    }

    @Nonnull
    public String getFluidName() {
        return fluidName;
    }

    @Nullable
    public RegistryObject<Item> getRegistryObject() {
        return registryObject;
    }

    public void setRegistryObject(@Nonnull final RegistryObject<Item> registryObject) {
        this.registryObject = registryObject;
    }

    @Nonnull
    public String getToolTip() {
        return tooltip;
    }

    public double getYOffset() {
        return yOffset;
    }
}
