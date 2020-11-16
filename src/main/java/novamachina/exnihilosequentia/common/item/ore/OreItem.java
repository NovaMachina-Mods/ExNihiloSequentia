package novamachina.exnihilosequentia.common.item.ore;

import novamachina.exnihilosequentia.common.init.ModInitialization;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class OreItem extends Item {

    private final EnumOre ore;

    public OreItem(EnumOre ore) {
        super(new Item.Properties().group(ModInitialization.ITEM_GROUP));
        this.ore = ore;
    }

    public EnumOre getOre() {
        return ore;
    }

    @Override
    protected boolean isInGroup(ItemGroup group) {
        if (group == ModInitialization.ITEM_GROUP) {
            return ore.isEnabled();
        }
        return false;
    }
}
