package com.novamachina.ens.common.registry;

import com.novamachina.ens.common.utility.Config;
import java.util.HashMap;
import java.util.Map;

public abstract class IRegistry<T> {

    protected final boolean        useJSON  = Config.USE_JSON_REGISTRIES.get();
    protected       Map<String, T> registry = new HashMap<>();

    public void initRegistry() {
        if (useJSON) {
            useJsonRegistry();
        } else {
            useDefaultRegistry();
        }
    }

    protected abstract void useJsonRegistry();

    public abstract void register(String key, T value);

    protected abstract void useDefaultRegistry();
}
