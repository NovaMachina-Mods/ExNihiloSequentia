package com.novamachina.exnihilosequentia.common.tileentity.crucible;

public enum CrucilbeTypeEnum {
    WOOD("wood"), FIRED("fired");

    public String getName() {
        return name;
    }

    private final String name;

    CrucilbeTypeEnum(String name) {
        this.name = name;
    }

    public static CrucilbeTypeEnum getTypeByName(String name) {
        for (CrucilbeTypeEnum type : CrucilbeTypeEnum.values()) {
            if (type.name.equals(name)) {
                return type;
            }
        }
        return null;
    }
}
