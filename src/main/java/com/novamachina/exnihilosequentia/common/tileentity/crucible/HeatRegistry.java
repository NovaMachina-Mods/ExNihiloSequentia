package com.novamachina.exnihilosequentia.common.tileentity.crucible;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.block.Blocks;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class HeatRegistry {

    private static final Map<String, Integer> heatMap     = new HashMap<>();
    private static       boolean              initialized = false;

    public static void addHeatSource(ForgeRegistryEntry<? extends IItemProvider> entry,
        int amount) {
        insertIntoMap(entry.getRegistryName().toString(), amount);
    }

    private static void insertIntoMap(String name, int amount) {
        heatMap.put(name, amount);
    }

    public static boolean isHeatSource(ForgeRegistryEntry<? extends IItemProvider> entry) {
        String blockName = entry.getRegistryName().toString();
        return heatMap.containsKey(blockName);
    }

    public static int getHeatAmount(ForgeRegistryEntry<? extends IItemProvider> entry) {
        if (!initialized) {
            useDefaults();
            initialized = true;
        }

        String blockName = entry.getRegistryName().toString();
        return heatMap.getOrDefault(blockName, 0);
    }

    private static void useDefaults() {
        addHeatSource(Blocks.LAVA, 3);
        addHeatSource(Blocks.FIRE, 4);
        addHeatSource(Blocks.TORCH, 1);
        addHeatSource(Blocks.WALL_TORCH, 1);
        addHeatSource(Blocks.MAGMA_BLOCK, 2);
        addHeatSource(Blocks.GLOWSTONE, 2);
    }
}
