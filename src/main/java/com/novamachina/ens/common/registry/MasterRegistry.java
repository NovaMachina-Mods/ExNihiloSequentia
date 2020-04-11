package com.novamachina.ens.common.registry;

import java.util.List;

public class MasterRegistry {

    private static List<IRegistry> registries;

    public static void addRegistry(IRegistry registry) {
        registries.add(registry);
    }

    public static void initRegistries() {
        for (IRegistry registry : registries) {
            registry.initRegistry();
        }
    }
}
