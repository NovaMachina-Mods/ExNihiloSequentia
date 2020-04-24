package com.novamachina.ens.common.registry;

import com.novamachina.ens.common.item.ore.Ore;
import com.novamachina.ens.common.utility.Color;
import com.novamachina.ens.common.utility.Constants;
import net.minecraft.item.Items;

public class OreRegistry extends IRegistry<Ore> {

    @Override
    protected void useJsonRegistry() {

    }

    @Override
    protected void useDefaultRegistry() {
        register(Constants.Ore.GOLD,
            new Ore(Constants.Ore.GOLD, new Color("FFFF00"), Items.GOLD_INGOT));
        register(Constants.Ore.IRON,
            new Ore(Constants.Ore.IRON, new Color("BF8040"), Items.IRON_INGOT));
    }
}
