package com.novamachina.exnihilosequentia.common.block;

import com.novamachina.exnihilosequentia.common.builder.BlockBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.CakeBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

public class EndCakeBlock extends CakeBlock {

    public EndCakeBlock() {
        super(new BlockBuilder().getProperties().hardnessAndResistance(0.5F));
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos,
                                             PlayerEntity player, Hand handIn, BlockRayTraceResult p_225533_6_) {
        ItemStack itemStack = player.getHeldItem(handIn);

        if (itemStack.isEmpty()) {
            return eatCake(worldIn, pos, state, player);
        } else {
            int bites = state.get(BITES);

            if (itemStack.getItem() == Items.ENDER_EYE && bites > 0) {
                if (!worldIn.isRemote()) {
                    worldIn.setBlockState(pos, state.with(BITES, bites - 1));
                    itemStack.shrink(1);
                }
                return ActionResultType.SUCCESS;
            }
        }
        return ActionResultType.CONSUME;
    }

    private ActionResultType eatCake(World worldIn, BlockPos pos, BlockState state,
                                     PlayerEntity player) {
        if (!worldIn.isRemote() && player.getRidingEntity() == null && player.isCreative()) {
            player.changeDimension(DimensionType.THE_END);
        }

        if (!player.canEat(false) || player.dimension == DimensionType.THE_END) {
            return ActionResultType.FAIL;
        } else {
            player.addStat(Stats.EAT_CAKE_SLICE);
            player.getFoodStats().addStats(2, 0.1F);
            int i = state.get(BITES);

            if (i < 6) {
                worldIn.setBlockState(pos, state.with(BITES, i + 1));
            } else {
                worldIn.removeBlock(pos, false);
            }

            if (!worldIn.isRemote() && player.getRidingEntity() == null) {
                player.changeDimension(DimensionType.THE_END);
            }
        }
        return ActionResultType.SUCCESS;
    }
}
