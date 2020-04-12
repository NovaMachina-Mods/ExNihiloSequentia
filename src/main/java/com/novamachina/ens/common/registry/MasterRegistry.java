package com.novamachina.ens.common.registry;

import com.novamachina.ens.common.registry.registryitem.CrookRegistryItem;
import java.util.ArrayList;
import java.util.List;

public class MasterRegistry {

    public static CrookRegistry getCrookRegistry() {
        return CROOK_REGISTRY;
    }

    private static final CrookRegistry CROOK_REGISTRY;

    private static final List<IRegistry> registries = new ArrayList<>();

    static {
        CROOK_REGISTRY = new CrookRegistry();
        registries.add(CROOK_REGISTRY);
    }

    public static void addRegistry(IRegistry registry) {
        registries.add(registry);
    }

    public static void initRegistries() {
        for (IRegistry registry : registries) {
            registry.initRegistry();
        }
    }
}
