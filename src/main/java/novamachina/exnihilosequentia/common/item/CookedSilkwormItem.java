package novamachina.exnihilosequentia.common.item;

import novamachina.exnihilosequentia.common.init.ExNihiloInitialization;
import net.minecraft.item.Food;
import net.minecraft.item.Item;

public class CookedSilkwormItem extends Item {

    public CookedSilkwormItem() {
        super(new Item.Properties().group(ExNihiloInitialization.ITEM_GROUP)
            .food(new Food.Builder().hunger(2).saturation(0.6F).build()));
    }
}
