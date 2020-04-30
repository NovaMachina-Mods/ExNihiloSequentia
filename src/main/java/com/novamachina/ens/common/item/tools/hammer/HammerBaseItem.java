package com.novamachina.ens.common.item.tools.hammer;

import com.google.common.collect.Sets;
import com.novamachina.ens.common.setup.ModBlocks;
import com.novamachina.ens.common.setup.ModInitialization;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ToolItem;

public class HammerBaseItem extends ToolItem {

    private static final Set<Block> effectiveBlocksOn = Sets
        .newHashSet(Blocks.STONE, Blocks.DIORITE, Blocks.ANDESITE, Blocks.GRANITE,
            Blocks.NETHERRACK, Blocks.END_STONE, Blocks.COBBLESTONE,
            ModBlocks.BLOCK_CRUSHED_DIORITE.get(), ModBlocks.BLOCK_CRUSHED_ANDESITE.get(),
            ModBlocks.BLOCK_CRUSHED_GRANITE.get(), ModBlocks.BLOCK_CRUSHED_NETHERRACK.get(),
            ModBlocks.BLOCK_CRUSHED_END_STONE.get(), Blocks.GRAVEL, Blocks.SAND);

    public HammerBaseItem(IItemTier tier, int maxDamage) {
        super(0.5F, 0.5F, tier, effectiveBlocksOn,
            new Item.Properties().defaultMaxDamage(maxDamage).group(ModInitialization.ITEM_GROUP));
    }
}
