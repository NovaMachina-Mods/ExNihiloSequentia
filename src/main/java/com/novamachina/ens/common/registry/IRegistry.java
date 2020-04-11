package com.novamachina.ens.common.registry;

import com.novamachina.ens.common.registry.registryitem.IRegistryItem;
import com.novamachina.ens.common.utility.Config;

public abstract class IRegistry<T extends IRegistryItem> {

    private final boolean useJSON = Config.USE_JSON_REGISTRIES.get();

    public abstract void initRegistry();

    public abstract void register(T value);
}
