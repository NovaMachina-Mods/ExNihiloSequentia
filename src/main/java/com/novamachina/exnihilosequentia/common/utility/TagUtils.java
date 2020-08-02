package com.novamachina.exnihilosequentia.common.utility;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TagUtils {

    public static Collection<ResourceLocation> getTags(Item item) {
        Collection<ResourceLocation> tags = ItemTags.getCollection().getOwningTags(item);
        return tags;
    }

    public static Collection<ResourceLocation> getTags(Block block) {
        Collection<ResourceLocation> tags = BlockTags.getCollection().getOwningTags(block);
        return tags;
    }

    public static Collection<ResourceLocation> getTags(IItemProvider item) {
        Collection<ResourceLocation> tags = null;

        try{
            tags = getTags((Item)item);
        } catch (ClassCastException ignored) {
        }

        try {
            if(tags != null) {
                tags.addAll(getTags((Block)item));
            } else {
                tags = getTags((Block) item);
            }
        }catch (ClassCastException ignored) {
        }

        return tags;
    }

    public static List<ResourceLocation> getTagsOwnedBy(ResourceLocation owningTag) {
        Tag<Block> blockTag = BlockTags.getCollection().get(owningTag);
        Tag<Item> itemTag = ItemTags.getCollection().get(owningTag);

        Collection<Block> blockCollection = null;
        Collection<Item> itemCollection = null;

        if(blockTag != null) {
            blockCollection = blockTag.getAllElements();
        }
        if(itemTag != null) {
            itemCollection = itemTag.getAllElements();
        }

        List<ResourceLocation> idList = new ArrayList<>();

        if(blockCollection != null) {
            for(Block block : blockCollection) {
                idList.add(block.getRegistryName());
            }
        }
        if(itemCollection != null) {
            for(Item item : itemCollection) {
                idList.add(item.getRegistryName());
            }
        }
        return idList;
    }
}
