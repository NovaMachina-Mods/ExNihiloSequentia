package novamachina.exnihilosequentia.common.compat.crafttweaker.expands;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import novamachina.exnihilosequentia.world.level.block.entity.CrucibleBlockEntity;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@NativeTypeRegistration(
    value = CrucibleBlockEntity.CrucibleType.class,
    zenCodeName = "mods.exnihilosequentia.CrucibleType")
@Document("mods/ExNihiloSequentia/ExpandCrucibleType")
public class ExpandCrucibleType {
  @ZenCodeType.StaticExpansionMethod
  public static CrucibleBlockEntity.CrucibleType fired() {
    return CrucibleBlockEntity.CrucibleType.FIRED;
  }

  @ZenCodeType.StaticExpansionMethod
  public static CrucibleBlockEntity.CrucibleType wood() {
    return CrucibleBlockEntity.CrucibleType.WOOD;
  }
}
