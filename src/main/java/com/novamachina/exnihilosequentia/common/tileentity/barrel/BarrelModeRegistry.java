package com.novamachina.exnihilosequentia.common.tileentity.barrel;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class BarrelModeRegistry {
    private static Map<String, Supplier<AbstractBarrelMode>> modeMap = new HashMap<>();

    public static AbstractBarrelMode getModeFromName(String barrelMode) {
        return modeMap.getOrDefault(barrelMode, null).get();
    }

    public static void initialize() {
        addMode(() -> new EmptyBarrelMode("empty"));
    }

    public static void addMode(Supplier<AbstractBarrelMode> mode) {
        modeMap.put(mode.get().getModeName(), mode);
    }
}
