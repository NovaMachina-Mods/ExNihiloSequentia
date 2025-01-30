package novamachina.exnihilosequentia.world.item;

import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import novamachina.exnihilosequentia.tags.ExNihiloTags;

public class CrookItem extends DiggerItem {

  public CrookItem(ToolMaterial tier, final float baseDamage, final float attackSpeed, Item.Properties properties) {
    super(tier, ExNihiloTags.MINEABLE_WITH_CROOK, baseDamage, attackSpeed, properties);
  }

  @FunctionalInterface
  public interface CrookFunction {
    CrookItem apply(
        ToolMaterial tier, float baseDamage, float attackSpeed, Item.Properties properties);
  }
}
