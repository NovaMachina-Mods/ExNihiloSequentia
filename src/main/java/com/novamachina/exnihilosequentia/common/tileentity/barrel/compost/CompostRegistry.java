package com.novamachina.exnihilosequentia.common.tileentity.barrel.compost;

import com.novamachina.exnihilosequentia.common.utility.LogUtil;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CompostRegistry {
    public static Map<String, Integer> solidsMap = new HashMap<>();

    public static boolean containsSolid(IItemProvider item) {
        Collection<ResourceLocation> tags = getTags(item);
        for(ResourceLocation tag : tags) {
            if(solidsMap.containsKey(tag.toString())) {
                return true;
            }
        }
        return solidsMap.containsKey(item.asItem().getRegistryName().toString());
    }

    public static int getSolidAmount(IItemProvider item) {
        Collection<ResourceLocation> tags = getTags(item);
        for(ResourceLocation tag : tags) {
            if(solidsMap.containsKey(tag.toString())) {
                return solidsMap.get(tag.toString());
            }
        }

        return solidsMap.getOrDefault(item.asItem().getRegistryName().toString(), 0);
    }

    public static void initialize() {
        addSolid("minecraft:leaves", 250);
    }

    private static Collection<ResourceLocation> getTags(Item item) {
        Collection<ResourceLocation> c = ItemTags.getCollection().getOwningTags(item);
        return c;
    }

    private static Collection<ResourceLocation> getTags(Block block) {
        Collection<ResourceLocation> c = BlockTags.getCollection().getOwningTags(block);
        return c;
    }

    private static Collection<ResourceLocation> getTags(IItemProvider item) {
        Collection<ResourceLocation> c = null;

        try{
            c = getTags((Item)item);
        } catch (ClassCastException ignored) {
            LogUtil.warn("Not an Item");
        }

        try {
            if(c != null) {
                c.addAll(getTags((Block)item));
            } else {
                c = getTags((Block) item);
            }
        }catch (ClassCastException ignored) {
            LogUtil.warn("Not a Block");
        }

        return c;
    }

    private static void addSolid(IItemProvider item, int solidAmount) {
        Collection<ResourceLocation> c = getTags(item);

        for(ResourceLocation r : c) {
            if(solidsMap.containsKey(r.toString())) {
                LogUtil.info(String.format("Tag: %s already registered. Skipping...", r.toString()));
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
