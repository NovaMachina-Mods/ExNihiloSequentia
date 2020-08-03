package com.novamachina.exnihilosequentia.common.tileentity.barrel.compost;

import com.novamachina.exnihilosequentia.common.utility.LogUtil;
import com.novamachina.exnihilosequentia.common.utility.TagUtils;
import net.minecraft.block.Blocks;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
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
        addSolid(new ResourceLocation("minecraft:saplings"), 125);
        addSolid(new ResourceLocation("minecraft:leaves"), 125);
        addSolid(new ResourceLocation("minecraft:flowers"), 100);
        addSolid(new ResourceLocation("minecraft:fishes"), 150);
        addSolid(new ResourceLocation("forge:allMeatCooked"), 200);
    }

    public static void addSolid(IItemProvider item, int solidAmount) {
        addSolid(item.asItem().getRegistryName(), solidAmount);
    }

    public static void addSolid(ResourceLocation tag, int solidAmount) {
        List<ResourceLocation> idList = TagUtils.getTagsOwnedBy(tag);

        for(ResourceLocation id : idList) {
            if(solidsMap.containsKey(id)) {
                LogUtil.info(String.format("ID: %s falls under Tag: %s. Removing %s ...", id.toString(), tag.toString(), id.toString()));
                solidsMap.remove(id);
            }
        }

        // Does a tag who owns me already exist in the map?
        Collection<ResourceLocation> tags = TagUtils.getTags(tag);
        if(tags != null) {
            for(ResourceLocation id : tags) {
                if(solidsMap.containsKey(id)) {
                    LogUtil.info(String.format("Tag: %s already registered. Skipping item %s ...", id.toString(), tag));
                    return;
                }
            }
        }

        if(solidsMap.containsKey(tag)) {
            LogUtil.info(String.format("Tag: %s already registered. Skipping...", tag));
            return;
        }
        insertIntoMap(tag, solidAmount);
    }

    private static void insertIntoMap(ResourceLocation id, int amount) {
        solidsMap.put(id, amount);
    }
}
