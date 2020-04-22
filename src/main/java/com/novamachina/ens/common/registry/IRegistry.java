package com.novamachina.ens.common.registry;

import com.novamachina.ens.common.utility.Config;
import java.util.Collection;
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

    public void register(String key, T value) {
        registry.put(key, value);
    }

    public T getValue(String key) {
        return registry.get(key);
    }

    protected abstract void useDefaultRegistry();

    public Collection<T> getValues() {
        return registry.values();
    }

    public Collection<String> getKeys() {
        return registry.keySet();
    }
}
