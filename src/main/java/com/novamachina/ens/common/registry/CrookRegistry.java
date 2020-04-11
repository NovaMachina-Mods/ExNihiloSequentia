package com.novamachina.ens.common.registry;

import com.novamachina.ens.common.registry.defaults.DefaultCrookRegistry;
import com.novamachina.ens.common.registry.registryitem.CrookRegistryItem;

public class CrookRegistry extends IRegistry<CrookRegistryItem> {

    @Override
    public void register(CrookRegistryItem value) {
        registrySet.add(value);
    }

    @Override
    protected void useDefaultRegistry() {
        new DefaultCrookRegistry(this).init();
    }
}
