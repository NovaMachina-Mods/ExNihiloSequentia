package novamachina.exnihilosequentia.common.item.pebbles;

import net.minecraft.item.Item;
import net.minecraft.item.SnowballItem;
import novamachina.exnihilosequentia.common.init.ExNihiloInitialization;

public class PebbleItem extends SnowballItem {
    public PebbleItem() {
        super(new Item.Properties().tab(ExNihiloInitialization.ITEM_GROUP));
    }
}
