package com.novamachina.ens.common.registry;

import java.util.ArrayList;
import java.util.List;

public class MasterRegistry {

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
