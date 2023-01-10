package novamachina.exnihilosequentia.common.blockentity.crucible;

import javax.annotation.Nonnull;

public enum CrucibleTypeEnum {
  WOOD("wood", 0),
  FIRED("fired", 1);

  @Nonnull private final String name;
  private final int level;

  CrucibleTypeEnum(@Nonnull final String name, final int level) {
    this.name = name;
    this.level = level;
  }

  public static CrucibleTypeEnum getTypeByName(@Nonnull final String name) {
    for (@Nonnull final CrucibleTypeEnum type : CrucibleTypeEnum.values()) {
      if (type.name.equals(name)) {
        return type;
      }
    }
    // TODO this one needs to be changed due to crash with mekanism pipes and fired crucibles
    return null;
  }

  @Nonnull
  public String getName() {
    return name;
  }

  public int getLevel() {
    return level;
  }
}
