package com.novamachina.ens.common.item.tools.crook;

import com.google.common.collect.Sets;
import com.novamachina.ens.common.setup.ModSetup;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ToolItem;
import net.minecraft.tags.BlockTags;

public class CrookBaseItem extends ToolItem {

    private static final Set<Block> effectiveBlocksIn = Sets
        .newHashSet(BlockTags.LEAVES.getAllElements());

    public CrookBaseItem(IItemTier tier, int maxDamage) {
        super(0.5F, 0.5F, tier, effectiveBlocksIn,
            new Item.Properties().defaultMaxDamage(maxDamage).group(ModSetup.ITEM_GROUP));
    }
}
