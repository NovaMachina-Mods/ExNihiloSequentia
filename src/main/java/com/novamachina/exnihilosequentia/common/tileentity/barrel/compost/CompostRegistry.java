package com.novamachina.exnihilosequentia.common.tileentity.barrel.compost;

import com.novamachina.exnihilosequentia.common.utility.LogUtil;
import com.novamachina.exnihilosequentia.common.utility.TagUtils;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CompostRegistry {
    public static Map<String, Integer> solidsMap = new HashMap<>();

    public static boolean containsSolid(IItemProvider item) {
        Collection<ResourceLocation> tags = TagUtils.getTags(item);
        for(ResourceLocation tag : tags) {
            if(solidsMap.containsKey(tag.toString())) {
                return true;
            }
        }
        return solidsMap.containsKey(item.asItem().getRegistryName().toString());
    }

    public static int getSolidAmount(IItemProvider item) {
        Collection<ResourceLocation> tags = TagUtils.getTags(item);
        for(ResourceLocation tag : tags) {
            if(solidsMap.containsKey(tag.toString())) {
                return solidsMap.get(tag.toString());
            }
        }

        return solidsMap.getOrDefault(item.asItem().getRegistryName().toString(), 0);
    }

    // TODO: add remaining compost values
    public static void initialize() {
        addSolid("minecraft:leaves", 250);
    }

    private static void addSolid(IItemProvider item, int solidAmount) {
        Collection<ResourceLocation> tags = TagUtils.getTags(item);

        for(ResourceLocation tag : tags) {
            if(solidsMap.containsKey(tag.toString())) {
                LogUtil.info(String.format("Tag: %s already registered. Skipping...", tag.toString()));
                return;
            }
        }
        solidsMap.put(item.asItem().getRegistryName().toString(), solidAmount);
    }

    private static void addSolid(String tag, int solidAmount) {
        if(solidsMap.containsKey(tag)) {
            LogUtil.info(String.format("Tag: %s already registered. Skipping...", tag));
            return;
        }
        solidsMap.put(tag, solidAmount);
    }
}
