package novamachina.exnihilosequentia.common.item.ore;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import novamachina.exnihilosequentia.common.init.ExNihiloInitialization;

import javax.annotation.Nonnull;

public class OreItem extends Item {

    @Nonnull private final EnumOre ore;

    public OreItem(@Nonnull final EnumOre ore) {
        super(new Item.Properties().tab(ExNihiloInitialization.ITEM_GROUP));
        this.ore = ore;
    }

    @Nonnull
    public EnumOre getOre() {
        return ore;
    }

    @Override
    protected boolean allowdedIn(@Nonnull final CreativeModeTab group) {
        if (group == ExNihiloInitialization.ITEM_GROUP) {
            return ore.isEnabled();
        }
        return false;
    }
}
