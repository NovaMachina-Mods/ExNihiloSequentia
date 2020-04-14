package com.novamachina.ens.common.registry;

import com.novamachina.ens.common.registry.registryitem.CrookRegistryItem;
import com.novamachina.ens.common.registry.registryitem.HammerRegistry;
import java.util.ArrayList;
import java.util.List;

public class MasterRegistry {

    public static CrookRegistry  CROOK_REGISTRY;
    public static HammerRegistry HAMMER_REGISTRY;

    private static final List<IRegistry> registries = new ArrayList<>();

    public static void addRegistry(IRegistry registry) {
        registries.add(registry);
    }

    public static void initRegistries() {
        for (IRegistry registry : registries) {
            registry.initRegistry();
        }
    }
}
