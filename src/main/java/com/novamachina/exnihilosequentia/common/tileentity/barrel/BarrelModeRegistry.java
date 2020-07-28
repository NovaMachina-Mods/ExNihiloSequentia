package com.novamachina.exnihilosequentia.common.tileentity.barrel;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class BarrelModeRegistry {
    private static Map<String, Supplier<AbstractBarrelMode>> modeNameMap = new HashMap<>();
    private static Map<TriggerType, ArrayList<Supplier<AbstractBarrelMode>>> modeMap = new EnumMap<>(TriggerType.class);

    public static AbstractBarrelMode getModeFromName(String barrelMode) {
        return modeNameMap.getOrDefault(barrelMode, null).get();
    }

    public static ArrayList<Supplier<AbstractBarrelMode>> getModes(TriggerType type) {
        return modeMap.get(type);
    }

    public static void initialize() {
        addMode(() -> new EmptyBarrelMode("empty"), TriggerType.NONE);
        addMode(() -> new CompostBarrelMode("solids"), TriggerType.ITEM);
//        addMode(() -> new FluidsBarrelMode("fluids"), TriggerType.FLUID);
        addMode(() -> new BlockBarrelMode("block"), TriggerType.NONE);
//        addMode(() -> new MobSpawnBarrelMode("mob"), TriggerType.NONE);
//        addMode(() -> new FluidTransformBarrelMode("transform"), TriggerType.NONE);
    }

    public static void addMode(Supplier<AbstractBarrelMode> mode, TriggerType type) {
        ArrayList<Supplier<AbstractBarrelMode>> list = modeMap.get(type);
        if(list == null) {
            list = new ArrayList<>();
        }
        list.add(mode);
        modeMap.put(type, list);
        modeNameMap.put(mode.get().getModeName(), mode);
    }

    public enum TriggerType {
        ITEM, FLUID, NONE
    }
}
