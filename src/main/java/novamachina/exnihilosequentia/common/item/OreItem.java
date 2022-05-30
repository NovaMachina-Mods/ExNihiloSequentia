package novamachina.exnihilosequentia.common.item;

import javax.annotation.Nonnull;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import novamachina.exnihilosequentia.common.init.ExNihiloInitialization;
import novamachina.exnihilosequentia.common.item.ore.Ore;

public class OreItem extends Item {

  private final Ore ore;

  public OreItem(Ore ore) {
    super(new Item.Properties().tab(ExNihiloInitialization.ITEM_GROUP));
    this.ore = ore;
  }

  @Override
  protected boolean allowdedIn(@Nonnull final CreativeModeTab group) {
    if (group == ExNihiloInitialization.ITEM_GROUP) {
      return ore.isEnabled();
    }
    return false;
  }

  public Ore getOre() {
    return ore;
  }
}
