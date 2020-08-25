package com.novamachina.exnihilosequentia.common.utility;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

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

        try {
            tags = getTags((Item) item);
        } catch (ClassCastException ignored) {
        }

        try {
            if (tags != null) {
                tags.addAll(getTags((Block) item));
            } else {
                tags = getTags((Block) item);
            }
        } catch (ClassCastException ignored) {
        }

        return tags;
    }

    public static Collection<ResourceLocation> getTags(ResourceLocation id) {
        Collection<ResourceLocation> tags = null;
        if (ForgeRegistries.BLOCKS.containsKey(id)) {
            Block block = ForgeRegistries.BLOCKS.getValue(id);
            tags = getTags(block);
        }

        if (ForgeRegistries.ITEMS.containsKey(id)) {
            Item item = ForgeRegistries.ITEMS.getValue(id);
            if (tags != null) {
                tags.addAll(getTags(item));
            } else {
                tags = getTags(item);
            }
        }
        return tags;
    }

    public static List<ResourceLocation> getTagsOwnedBy(ResourceLocation owningTag) {
        ITag<Block> blockTag = BlockTags.getCollection().get(owningTag);
        ITag<Item> itemTag = ItemTags.getCollection().get(owningTag);

        Collection<Block> blockCollection = null;
        Collection<Item> itemCollection = null;

        if (blockTag != null) {
            blockCollection = blockTag.func_230236_b_();
        }
        if (itemTag != null) {
            itemCollection = itemTag.func_230236_b_();
        }

        List<ResourceLocation> idList = new ArrayList<>();

        if (blockCollection != null) {
            for (Block block : blockCollection) {
                idList.add(block.getRegistryName());
            }
        }
        if (itemCollection != null) {
            for (Item item : itemCollection) {
                if (!idList.contains(item.getRegistryName())) {
                    idList.add(item.getRegistryName());
                }
            }
        }
        return idList;
    }

    public static boolean isTag(ResourceLocation tag) {
        return ItemTags.getCollection().get(tag) != null || BlockTags.getCollection().get(tag) != null;
    }
}
