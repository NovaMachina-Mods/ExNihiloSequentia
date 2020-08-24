package com.novamachina.exnihilosequentia.common.item.tools.hammer;

import com.google.common.collect.Sets;
import com.novamachina.exnihilosequentia.common.api.ExNihiloRegistries;
import com.novamachina.exnihilosequentia.common.init.ModBlocks;
import com.novamachina.exnihilosequentia.common.init.ModInitialization;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ToolItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ToolType;

import java.util.Set;

public class HammerBaseItem extends ToolItem {

    private static final Set<Block> effectiveBlocksOn = Sets
        .newHashSet(Blocks.STONE, Blocks.DIORITE, Blocks.ANDESITE, Blocks.GRANITE,
            Blocks.NETHERRACK, Blocks.END_STONE, Blocks.COBBLESTONE,
            ModBlocks.CRUSHED_DIORITE.get(), ModBlocks.CRUSHED_ANDESITE.get(),
            ModBlocks.CRUSHED_GRANITE.get(), ModBlocks.CRUSHED_NETHERRACK.get(),
            ModBlocks.CRUSHED_END_STONE.get(), Blocks.GRAVEL, Blocks.SAND,
            Blocks.TUBE_CORAL_BLOCK, Blocks.BRAIN_CORAL_BLOCK, Blocks.BUBBLE_CORAL_BLOCK,
            Blocks.FIRE_CORAL_BLOCK, Blocks.HORN_CORAL_BLOCK,
            Blocks.TUBE_CORAL, Blocks.BRAIN_CORAL, Blocks.BUBBLE_CORAL,
            Blocks.FIRE_CORAL, Blocks.HORN_CORAL);

    public HammerBaseItem(IItemTier tier, int maxDamage) {
        super(0.5F, 0.5F, tier, effectiveBlocksOn,
            new Item.Properties().defaultMaxDamage(maxDamage).group(ModInitialization.ITEM_GROUP)
                .addToolType(
                    ToolType.PICKAXE, tier.getHarvestLevel()));
    }

    @Override
    public boolean canHarvestBlock(BlockState blockIn) {
        ResourceLocation blockID = blockIn.getBlock().getRegistryName();
        if (ExNihiloRegistries.HAMMER_REGISTRY.isHammerable(blockID)) {
            return true;
        }
        return super.canHarvestBlock(blockIn);
    }
}
