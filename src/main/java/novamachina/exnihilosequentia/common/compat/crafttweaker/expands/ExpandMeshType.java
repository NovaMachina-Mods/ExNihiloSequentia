package novamachina.exnihilosequentia.common.compat.crafttweaker.expands;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import novamachina.exnihilosequentia.world.item.MeshType;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@NativeTypeRegistration(value = MeshType.class, zenCodeName = "mods.exnihilosequentia.MeshType")
@Document("mods/ExNihiloSequentia/ExpandMeshType")
public class ExpandMeshType {
  @ZenCodeType.StaticExpansionMethod
  public static MeshType stringMesh() {
    return MeshType.STRING;
  }

  @ZenCodeType.StaticExpansionMethod
  public static MeshType flintMesh() {
    return MeshType.FLINT;
  }

  @ZenCodeType.StaticExpansionMethod
  public static MeshType ironMesh() {
    return MeshType.IRON;
  }

  @ZenCodeType.StaticExpansionMethod
  public static MeshType diamondMesh() {
    return MeshType.DIAMOND;
  }

  @ZenCodeType.StaticExpansionMethod
  public static MeshType emeraldMesh() {
    return MeshType.EMERALD;
  }

  @ZenCodeType.StaticExpansionMethod
  public static MeshType netheriteMesh() {
    return MeshType.NETHERITE;
  }
}
