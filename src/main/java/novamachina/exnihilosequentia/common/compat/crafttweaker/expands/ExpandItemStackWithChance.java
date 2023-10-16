package novamachina.exnihilosequentia.common.compat.crafttweaker.expands;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import novamachina.exnihilosequentia.world.item.crafting.ItemStackWithChance;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@NativeTypeRegistration(
    value = ItemStackWithChance.class,
    zenCodeName = "mods.exnihilosequentia.ItemStackWithChance")
@Document("mods/ExNihiloSequentia/ExpandItemStackWithChance")
public class ExpandItemStackWithChance {
  @ZenCodeType.StaticExpansionMethod
  public static ItemStackWithChance of(IItemStack itemStack, float chance) {
    return ItemStackWithChance.of(itemStack.getInternal(), chance);
  }

  @ZenCodeType.StaticExpansionMethod
  public static ItemStackWithChance of(IItemStack itemStack) {
    return ItemStackWithChance.of(itemStack.getInternal());
  }

  @ZenCodeType.Method
  @ZenCodeType.Getter("itemStack")
  public static IItemStack getItemStack(ItemStackWithChance internal) {
    return IItemStack.of(internal.getStack());
  }

  @ZenCodeType.Method
  @ZenCodeType.Getter("chance")
  public static float getChance(ItemStackWithChance internal) {
    return internal.getChance();
  }
}
