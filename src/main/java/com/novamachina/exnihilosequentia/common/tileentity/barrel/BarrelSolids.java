package com.novamachina.exnihilosequentia.common.tileentity.barrel;

import java.util.HashMap;
import java.util.Map;

public class BarrelSolids {
    public static Map<String, Integer> solidsMap = new HashMap<>();

    public static boolean containsSolid(String registryName) {
        return solidsMap.containsKey(registryName);
    }

    public static void initialize() {

    }
}
