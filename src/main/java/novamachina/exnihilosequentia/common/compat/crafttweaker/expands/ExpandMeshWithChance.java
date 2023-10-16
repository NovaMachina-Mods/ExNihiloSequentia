package novamachina.exnihilosequentia.common.compat.crafttweaker.expands;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import novamachina.exnihilosequentia.world.item.MeshType;
import novamachina.exnihilosequentia.world.item.crafting.MeshWithChance;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@NativeTypeRegistration(
    value = MeshWithChance.class,
    zenCodeName = "mods.exnihilosequentia.MeshWithChance")
@Document("mods/ExNihiloSequentia/ExpandMeshWithChance")
public class ExpandMeshWithChance {
  @ZenCodeType.StaticExpansionMethod
  public static MeshWithChance of(MeshType meshType, float chance) {
    return new MeshWithChance(meshType, chance);
  }

  @ZenCodeType.Method
  @ZenCodeType.Getter("meshType")
  public static MeshType getMesh(MeshWithChance internal) {
    return internal.getMesh();
  }

  @ZenCodeType.Method
  @ZenCodeType.Getter("chance")
  public static float getChance(MeshWithChance internal) {
    return internal.getChance();
  }
}
