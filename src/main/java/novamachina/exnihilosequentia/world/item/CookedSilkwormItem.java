package novamachina.exnihilosequentia.world.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class CookedSilkwormItem extends Item {

  public CookedSilkwormItem() {
    super(
        new Item.Properties()
            .food(new FoodProperties.Builder().nutrition(2).saturationMod(0.6F).build()));
  }
}
