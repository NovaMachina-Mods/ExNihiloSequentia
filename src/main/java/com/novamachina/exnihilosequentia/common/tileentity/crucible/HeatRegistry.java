package com.novamachina.exnihilosequentia.common.tileentity.crucible;

import com.google.gson.Gson;
import com.novamachina.exnihilosequentia.common.utility.LogUtil;
import com.novamachina.exnihilosequentia.common.utility.TagUtils;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HeatRegistry {

    private static final Map<ResourceLocation, Integer> heatMap     = new HashMap<>();

    public static void addHeatSource(ForgeRegistryEntry<? extends IItemProvider> entry, int amount) {
        addHeatSource(entry.getRegistryName(), amount);
    }

    public static void addHeatSource(ResourceLocation entry, int amount) {
        // Who do I own?
        List<ResourceLocation> idList = TagUtils.getTagsOwnedBy(entry);
        for(ResourceLocation id : idList) {
            if(heatMap.containsKey(id)) {
                LogUtil.info(String.format("ID: %s falls under Tag: %s. Removing %s ...", id.toString(), entry.toString(), id.toString()));
                heatMap.remove(id);
            }
        }

        // Does a tag who owns me already exist in the map?
        Collection<ResourceLocation> tags = TagUtils.getTags(entry);
        if(tags != null) {
            for (ResourceLocation tag : tags) {
                if (heatMap.containsKey(tag)) {
                    LogUtil
                        .info(String.format("Tag: %s already registered. Skipping item %s ...", tag.toString(), entry));
                    return;
                }
            }
        }

        // Am I in map?
        if(heatMap.containsKey(entry)) {
            LogUtil.info(String.format("Tag: %s already registered. Skipping...", entry));
            return;
        }

        insertIntoMap(entry, amount);
    }

    private static void insertIntoMap(ResourceLocation name, int amount) {
        heatMap.put(name, amount);
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

    public static List<HeatJson> toJSONReady() {
        List<HeatJson> jsonList = new ArrayList<>();
        for(Map.Entry<ResourceLocation, Integer> entry: heatMap.entrySet())
        {
            jsonList.add(new HeatJson(entry.getKey().toString(), entry.getValue()));
        }
        return jsonList;
    }
}
