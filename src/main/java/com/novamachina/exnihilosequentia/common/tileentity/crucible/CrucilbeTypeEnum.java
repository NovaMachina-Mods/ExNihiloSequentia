package com.novamachina.exnihilosequentia.common.tileentity.crucible;

public enum CrucilbeTypeEnum {
    WOOD("wood", 0), FIRED("fired", 1);

    private final String name;
    private final int level;

    CrucilbeTypeEnum(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public static CrucilbeTypeEnum getTypeByName(String name) {
        for (CrucilbeTypeEnum type : CrucilbeTypeEnum.values()) {
            if (type.name.equals(name)) {
                return type;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }
}
