package com.novamachina.ens.common.item.tools.hammer;

import com.google.common.collect.Sets;
import com.novamachina.ens.common.registry.MasterRegistry;
import com.novamachina.ens.common.setup.ModSetup;
import com.novamachina.ens.common.setup.Registration;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HammerBaseItem extends ToolItem {

    private static final Set<Block> effectiveBlocksOn = Sets
        .newHashSet(Blocks.STONE, Blocks.DIORITE, Blocks.ANDESITE, Blocks.GRANITE,
            Blocks.NETHERRACK, Blocks.END_STONE, Blocks.COBBLESTONE,
            Registration.BLOCK_CRUSHED_DIORITE.get(), Registration.BLOCK_CRUSHED_ANDESITE.get(),
            Registration.BLOCK_CRUSHED_GRANITE.get(), Registration.BLOCK_CRUSHED_NETHERRACK.get(),
            Registration.BLOCK_CRUSHED_END_STONE.get(), Blocks.GRAVEL, Blocks.SAND,
            Registration.BLOCK_DUST.get());

    public HammerBaseItem(IItemTier tier, int maxDamage) {
        super(0.5F, 0.5F, tier, effectiveBlocksOn,
            new Item.Properties().defaultMaxDamage(maxDamage).group(ModSetup.ITEM_GROUP));
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos,
        LivingEntity entityLiving) {
        super.onBlockDestroyed(stack, worldIn, state, pos, entityLiving);
        Block result = MasterRegistry.HAMMER_REGISTRY.getResult(state.getBlock());
        Block.spawnAsEntity(worldIn, pos, new ItemStack(result));

        return false;
    }
}
