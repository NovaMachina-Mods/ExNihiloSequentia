package com.novamachina.exnihilosequentia.common.utility;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

import java.util.Collection;

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
            LogUtil.warn("Not an Item");
        }

        try {
            if(tags != null) {
                tags.addAll(getTags((Block)item));
            } else {
                tags = getTags((Block) item);
            }
        }catch (ClassCastException ignored) {
            LogUtil.warn("Not a Block");
        }

        return tags;
    }
}
