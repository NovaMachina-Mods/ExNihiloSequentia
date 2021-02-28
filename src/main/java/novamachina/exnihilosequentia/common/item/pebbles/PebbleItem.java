package novamachina.exnihilosequentia.common.item.pebbles;

import net.minecraft.entity.projectile.SnowballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.SnowballItem;
import novamachina.exnihilosequentia.common.init.ExNihiloInitialization;

public class PebbleItem extends SnowballItem {

    public PebbleItem() {
        super(new Item.Properties().group(ExNihiloInitialization.ITEM_GROUP));
    }

    // TODO: Throwable pebbles (SnowballItem?)
}
