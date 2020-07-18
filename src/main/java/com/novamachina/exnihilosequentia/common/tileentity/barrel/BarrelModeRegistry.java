package com.novamachina.exnihilosequentia.common.tileentity.barrel;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class BarrelModeRegistry {
    private static Map<String, Supplier<AbstractBarrelMode>> modeMap = new HashMap<>();
    private static boolean initialized = false;

    public static AbstractBarrelMode getModeFromName(String barrelMode) {
        if(!initialized) {
            useDefaults();
            initialized = true;
        }
        return modeMap.getOrDefault(barrelMode, null).get();
    }

    private static void useDefaults() {
        addMode(() -> new EmptyBarrelMode("empty"));
    }

    public static void addMode(Supplier<AbstractBarrelMode> mode) {
        modeMap.put(mode.get().getModeName(), mode);
    }
}
