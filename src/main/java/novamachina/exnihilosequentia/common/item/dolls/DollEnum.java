package novamachina.exnihilosequentia.common.item.dolls;

import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

public enum DollEnum {
    BLAZE(ExNihiloConstants.ModIds.MINECRAFT, "blaze", "minecraft", "lava", 1, ExNihiloConstants.Tooltips.BLAZE),
    ENDERMAN(ExNihiloConstants.ModIds.MINECRAFT, "enderman", ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, ExNihiloConstants.Fluids.WITCH_WATER, 2, ExNihiloConstants.Tooltips.ENDERMAN),
    SHULKER(ExNihiloConstants.ModIds.MINECRAFT, "shulker", ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, ExNihiloConstants.Fluids.WITCH_WATER, 1.5, ExNihiloConstants.Tooltips.SHULKER),
    GUARDIAN(ExNihiloConstants.ModIds.MINECRAFT, "guardian", ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, ExNihiloConstants.Fluids.SEA_WATER, 1, ExNihiloConstants.Tooltips.GUARDIAN),
    BEE(ExNihiloConstants.ModIds.MINECRAFT, "bee", ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, ExNihiloConstants.Fluids.WITCH_WATER, 1, ExNihiloConstants.Tooltips.BEE);


    private final String entityModId;
    private final String entityName;
    private final String fluidModId;
    private final String fluidName;
    private final double yOffset;
    private final String tooltip;
    private RegistryObject<Item> registryObject;

    DollEnum(String entityModId, String entityName, String fluidModId, String fluidName, double yOffset, String tooltip) {
        this.entityModId = entityModId;
        this.entityName = entityName;
        this.fluidModId = fluidModId;
        this.fluidName = fluidName;
        this.yOffset = yOffset;
        this.tooltip = tooltip;
    }

    public static DollEnum getDollFromString(String dollType) {
        for (DollEnum doll : values()) {
            if (doll.name().equalsIgnoreCase(dollType)) {
                return doll;
            }
        }
        return null;
    }

    public String getFluidModId() {
        return fluidModId;
    }

    public String getEntityModId() {
        return entityModId;
    }

    public String getEntityName() {
        return entityName;
    }

    public String getFluidName() {
        return fluidName;
    }

    public double getYOffset() {
        return yOffset;
    }

    public String getDollName() {
        return "doll_" + entityName;
    }

    public String getToolTip() {
        return tooltip;
    }

    public RegistryObject<Item> getRegistryObject() {
        return registryObject;
    }

    public void setRegistryObject(RegistryObject<Item> registryObject) {
        this.registryObject = registryObject;
    }
}
