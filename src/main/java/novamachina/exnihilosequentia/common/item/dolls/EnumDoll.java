package novamachina.exnihilosequentia.common.item.dolls;

import net.minecraft.item.Item;
import net.minecraftforge.common.IExtensibleEnum;
import net.minecraftforge.fml.RegistryObject;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

public enum EnumDoll implements IExtensibleEnum {
    BLAZE(ExNihiloConstants.ModIds.MINECRAFT, "blaze", "minecraft", "lava", 1, ExNihiloConstants.Tooltips.BLAZE),
    ENDERMAN(ExNihiloConstants.ModIds.MINECRAFT, "enderman", ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, ExNihiloConstants.Fluids.WITCH_WATER, 2, ExNihiloConstants.Tooltips.ENDERMAN),
    SHULKER(ExNihiloConstants.ModIds.MINECRAFT, "shulker", ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, ExNihiloConstants.Fluids.WITCH_WATER, 1.5, ExNihiloConstants.Tooltips.SHULKER),
    GUARDIAN(ExNihiloConstants.ModIds.MINECRAFT, "guardian", ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, ExNihiloConstants.Fluids.SEA_WATER, 1, ExNihiloConstants.Tooltips.GUARDIAN),
    BEE(ExNihiloConstants.ModIds.MINECRAFT, "bee", ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, ExNihiloConstants.Fluids.WITCH_WATER, 1, ExNihiloConstants.Tooltips.BEE);


    private final String entityModId;
    private final String entityName;
    private final String fluidModId;
    private final String fluidName;
    private final String tooltip;
    private final double yOffset;
    private RegistryObject<Item> registryObject;

    EnumDoll(String entityModId, String entityName, String fluidModId, String fluidName, double yOffset, String tooltip) {
        this.entityModId = entityModId;
        this.entityName = entityName;
        this.fluidModId = fluidModId;
        this.fluidName = fluidName;
        this.yOffset = yOffset;
        this.tooltip = tooltip;
    }

    public static EnumDoll create(String enumName, String entityModId, String entityName, String fluidModId, String fluidName, double yOffset, String tooltip) {
        throw new IllegalStateException("Enum not extended");
    }

    public static EnumDoll getDollFromString(String dollType) {
        for (EnumDoll doll : values()) {
            if (doll.name().equalsIgnoreCase(dollType)) {
                return doll;
            }
        }
        return null;
    }

    public String getDollName() {
        return "doll_" + entityName;
    }

    public String getEntityModId() {
        return entityModId;
    }

    public String getEntityName() {
        return entityName;
    }

    public String getFluidModId() {
        return fluidModId;
    }

    public String getFluidName() {
        return fluidName;
    }

    public RegistryObject<Item> getRegistryObject() {
        return registryObject;
    }

    public void setRegistryObject(RegistryObject<Item> registryObject) {
        this.registryObject = registryObject;
    }

    public String getToolTip() {
        return tooltip;
    }

    public double getYOffset() {
        return yOffset;
    }
}
