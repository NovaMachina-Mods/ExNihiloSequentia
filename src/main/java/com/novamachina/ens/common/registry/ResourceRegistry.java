package com.novamachina.ens.common.registry;

import com.novamachina.ens.common.utility.Constants;
import com.novamachina.ens.common.utility.Constants.Items;
import com.novamachina.ens.common.utility.LogUtil;

public class ResourceRegistry extends IRegistry<Void> {

    @Override
    protected void useJsonRegistry() {

    }

    @Override
    protected void useDefaultRegistry() {
        register(Constants.Items.ANCIENT_SPORE, null);
        register(Items.GRASS_SEED, null);
        register(Items.SILKWORM, null);
    }

    @Override
    public void register(String key, Void value) {
        if (registry.containsKey(key)) {
            LogUtil.warn(
                "Resource key '" + key + "' is already registered. Item will not appear in game.");
        } else {
            registry.put(key, value);
        }
    }
}
