package novamachina.exnihilosequentia.common.item.mesh;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum MeshType implements StringRepresentable {
  NONE(0),
  STRING(1),
  FLINT(2),
  IRON(3),
  DIAMOND(4),
  EMERALD(5),
  NETHERITE(6);

  private final int level;

  MeshType(int level) {
    this.level = level;
  }

  public int getLevel() {
    return level;
  }

  public String getMeshName() {
    return this.name().toLowerCase() + "_mesh";
  }

  @Override
  @NotNull
  public String getSerializedName() {
    return this.name().toLowerCase();
  }
}
