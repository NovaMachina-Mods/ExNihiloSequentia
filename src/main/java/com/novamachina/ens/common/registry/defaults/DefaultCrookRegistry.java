package com.novamachina.ens.common.registry.defaults;

import com.novamachina.ens.common.registry.CrookRegistry;
import com.novamachina.ens.common.registry.registryitem.CrookRegistryItem;
import com.novamachina.ens.common.setup.Registration;

public class DefaultCrookRegistry implements IDefaultRegistry {

    private final CrookRegistry crookRegistry;

    public DefaultCrookRegistry(CrookRegistry crookRegistry) {
        this.crookRegistry = crookRegistry;
        this.crookRegistry.setNumberOfTimesToTestVanillaDrops(3);
    }

    public void init() {
        crookRegistry.register(new CrookRegistryItem(Registration.ITEM_SILKWORM.get(), 0.3));
    }
}
