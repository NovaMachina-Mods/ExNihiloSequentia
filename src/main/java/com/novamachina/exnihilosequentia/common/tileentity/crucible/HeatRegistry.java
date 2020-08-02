package com.novamachina.exnihilosequentia.common.tileentity.crucible;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.block.Blocks;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class HeatRegistry {

    private static final Map<ResourceLocation, Integer> heatMap     = new HashMap<>();

    public static void addHeatSource(ForgeRegistryEntry<? extends IItemProvider> entry,
        int amount) {
        addHeatSource(entry.getRegistryName(), amount);
    }

    public static void addHeatSource(ResourceLocation entry, int amount) {
        insertIntoMap(entry, amount);
    }

    private static void insertIntoMap(ResourceLocation name, int amount) {
        heatMap.put(name, amount);
    }

    public static boolean isHeatSource(ForgeRegistryEntry<? extends IItemProvider> entry) {
        return heatMap.containsKey(entry.getRegistryName());
    }

    public static int getHeatAmount(ForgeRegistryEntry<? extends IItemProvider> entry) {
        return heatMap.getOrDefault(entry.getRegistryName(), 0);
    }

    public static void initialized() {
        addHeatSource(Blocks.LAVA, 3);
        addHeatSource(Blocks.FIRE, 4);
        addHeatSource(Blocks.TORCH, 1);
        addHeatSource(Blocks.WALL_TORCH, 1);
        addHeatSource(Blocks.MAGMA_BLOCK, 2);
        addHeatSource(Blocks.GLOWSTONE, 2);
    }
}
