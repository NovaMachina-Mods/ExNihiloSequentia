package novamachina.exnihilosequentia.common.item.ore;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import novamachina.exnihilosequentia.common.init.ExNihiloInitialization;

import javax.annotation.Nonnull;

public class OreItem extends Item {

    private final EnumOre ore;

    public OreItem(EnumOre ore) {
        super(new Item.Properties().tab(ExNihiloInitialization.ITEM_GROUP));
        this.ore = ore;
    }

    public EnumOre getOre() {
        return ore;
    }

    @Override
    protected boolean allowdedIn(@Nonnull CreativeModeTab group) {
        if (group == ExNihiloInitialization.ITEM_GROUP) {
            return ore.isEnabled();
        }
        return false;
    }
}
