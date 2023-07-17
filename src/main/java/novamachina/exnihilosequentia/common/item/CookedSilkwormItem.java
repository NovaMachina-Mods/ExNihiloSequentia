package novamachina.exnihilosequentia.common.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import novamachina.exnihilosequentia.init.ExNihiloInitialization;

public class CookedSilkwormItem extends Item {

  public CookedSilkwormItem() {
    super(
        new Item.Properties()
            .tab(ExNihiloInitialization.ITEM_GROUP)
            .food(new FoodProperties.Builder().nutrition(2).saturationMod(0.6F).build()));
  }
}
