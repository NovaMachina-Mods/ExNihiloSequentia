package com.novamachina.exnihilosequentia.common.tileentity.barrel;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

import java.util.HashMap;
import java.util.Map;

public class CompostRegistry {
    public static Map<String, Integer> solidsMap = new HashMap<>();

    public static boolean containsSolid(String registryName) {
        return solidsMap.containsKey(registryName);
    }

    public static int getSolidAmount(String registryName) {
        return solidsMap.getOrDefault(registryName, 0);
    }

    public static void initialize() {
        addSolid(Blocks.OAK_LEAVES, 250);
    }

    private static void addSolid(Block block, int solidAmount) {
        solidsMap.put(block.getRegistryName().toString(), solidAmount);
    }
}
