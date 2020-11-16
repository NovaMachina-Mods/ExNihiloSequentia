package novamachina.exnihilosequentia.common.item.dolls;

import novamachina.exnihilosequentia.common.utility.Constants;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

public enum DollEnum {
    BLAZE("minecraft", "blaze", "minecraft", "lava", 1, Constants.Tooltips.BLAZE),
    ENDERMAN("minecraft", "enderman", Constants.ModIds.EX_NIHILO_SEQUENTIA, Constants.Fluids.WITCH_WATER, 2, Constants.Tooltips.ENDERMAN),
    SHULKER("minecraft", "shulker", Constants.ModIds.EX_NIHILO_SEQUENTIA, Constants.Fluids.WITCH_WATER, 1.5, Constants.Tooltips.SHULKER),
    GUARDIAN("minecraft", "guardian", Constants.ModIds.EX_NIHILO_SEQUENTIA, Constants.Fluids.SEA_WATER, 1, Constants.Tooltips.GUARDIAN),
    BEE("minecraft", "bee", Constants.ModIds.EX_NIHILO_SEQUENTIA, Constants.Fluids.WITCH_WATER, 1, Constants.Tooltips.BEE);


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
            if (doll.name().toLowerCase().equals(dollType)) {
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
