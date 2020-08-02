package com.novamachina.exnihilosequentia.common.tileentity.barrel.compost;

import com.novamachina.exnihilosequentia.common.utility.LogUtil;
import com.novamachina.exnihilosequentia.common.utility.TagUtils;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CompostRegistry {
    public static Map<ResourceLocation, Integer> solidsMap = new HashMap<>();

    public static boolean containsSolid(IItemProvider item) {
        Collection<ResourceLocation> tags = TagUtils.getTags(item);
        for(ResourceLocation tag : tags) {
            if(solidsMap.containsKey(tag)) {
                return true;
            }
        }
        return solidsMap.containsKey(item.asItem().getRegistryName());
    }

    public static int getSolidAmount(IItemProvider item) {
        Collection<ResourceLocation> tags = TagUtils.getTags(item);
        for(ResourceLocation tag : tags) {
            if(solidsMap.containsKey(tag)) {
                return solidsMap.get(tag);
            }
        }

        return solidsMap.getOrDefault(item.asItem().getRegistryName(), 0);
    }

    // TODO: add remaining compost values
    public static void initialize() {
        addSolid(new ResourceLocation("minecraft:leaves"), 250);
    }

    private static void addSolid(IItemProvider item, int solidAmount) {
        Collection<ResourceLocation> tags = TagUtils.getTags(item);

        for(ResourceLocation tag : tags) {
            if(solidsMap.containsKey(tag)) {
                LogUtil.info(String.format("Tag: %s already registered. Skipping...", tag.toString()));
                return;
            }
        }
        solidsMap.put(item.asItem().getRegistryName(), solidAmount);
    }

    private static void addSolid(ResourceLocation tag, int solidAmount) {
        if(solidsMap.containsKey(tag)) {
            LogUtil.info(String.format("Tag: %s already registered. Skipping...", tag));
            return;
        }
        solidsMap.put(tag, solidAmount);
    }
}
