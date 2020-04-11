package com.novamachina.ens.common.registry;

import com.novamachina.ens.common.registry.registryitem.IRegistryItem;
import com.novamachina.ens.common.utility.Config;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class IRegistry<T extends IRegistryItem> {

    protected       Set<T>  registrySet = new HashSet<>();
    protected final boolean useJSON     = Config.USE_JSON_REGISTRIES.get();

    public void initRegistry() {
        if (useJSON) {
            //read from json files
        } else {
            useDefaultRegistry();
        }
    }

    public abstract void register(T value);

    protected abstract void useDefaultRegistry();
}
