package novamachina.exnihilosequentia.common.tileentity.crucible;

public enum CrucibleTypeEnum {
    WOOD("wood", 0), FIRED("fired", 1);

    private final String name;
    private final int level;

    CrucibleTypeEnum(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public static CrucibleTypeEnum getTypeByName(String name) {
        for (CrucibleTypeEnum type : CrucibleTypeEnum.values()) {
            if (type.name.equals(name)) {
                return type;
            }
        }
        //TODO this one needs to be changed due to crash with mekanism pipes and fired crucibles
        return null;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }
}
