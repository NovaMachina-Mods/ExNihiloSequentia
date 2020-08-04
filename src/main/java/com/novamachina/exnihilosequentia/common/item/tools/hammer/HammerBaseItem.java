package com.novamachina.exnihilosequentia.common.item.tools.hammer;

import com.google.common.collect.Sets;
import com.novamachina.exnihilosequentia.common.setup.ModBlocks;
import com.novamachina.exnihilosequentia.common.setup.ModInitialization;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ToolItem;
import net.minecraftforge.common.ToolType;

public class HammerBaseItem extends ToolItem {

    private static final Set<Block> effectiveBlocksOn = Sets
        .newHashSet(Blocks.STONE, Blocks.DIORITE, Blocks.ANDESITE, Blocks.GRANITE,
            Blocks.NETHERRACK, Blocks.END_STONE, Blocks.COBBLESTONE,
            ModBlocks.CRUSHED_DIORITE.get(), ModBlocks.CRUSHED_ANDESITE.get(),
            ModBlocks.CRUSHED_GRANITE.get(), ModBlocks.CRUSHED_NETHERRACK.get(),
            ModBlocks.CRUSHED_END_STONE.get(), Blocks.GRAVEL, Blocks.SAND);

    public HammerBaseItem(IItemTier tier, int maxDamage) {
        super(0.5F, 0.5F, tier, effectiveBlocksOn,
            new Item.Properties().defaultMaxDamage(maxDamage).group(ModInitialization.ITEM_GROUP)
                .addToolType(
                    ToolType.PICKAXE, tier.getHarvestLevel()));
    }
}
