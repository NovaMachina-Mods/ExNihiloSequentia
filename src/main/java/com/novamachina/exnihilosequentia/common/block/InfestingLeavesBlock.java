package com.novamachina.exnihilosequentia.common.block;

import com.novamachina.exnihilosequentia.common.builder.BlockBuilder;
import com.novamachina.exnihilosequentia.common.setup.ModBlocks;
import com.novamachina.exnihilosequentia.common.setup.ModItems;
import com.novamachina.exnihilosequentia.common.tileentity.InfestingLeavesTile;
import com.novamachina.exnihilosequentia.common.utility.Config;
import com.novamachina.exnihilosequentia.common.utility.LogUtil;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

// TODO: Add progressive render
public class InfestingLeavesBlock extends BaseBlock {

    public InfestingLeavesBlock() {
        super(new BlockBuilder()
            .properties(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).sound(
                SoundType.PLANT).notSolid()).tileEntitySupplier(InfestingLeavesTile::new));
    }

    public static void normalToInfesting(World world, BlockState state, BlockPos pos) {
        world.setBlockState(pos, ModBlocks.INFESTING_LEAVES.get().getDefaultState());
    }

    public static void finishInfestingBlock(World world, BlockPos pos) {
        world.setBlockState(pos, ModBlocks.INFESTED_LEAVES.get().getDefaultState());
    }

    public static void spread(World world, BlockPos pos) {
        if (!world.isRemote()) {
            NonNullList<BlockPos> nearbyLeaves = getNearbyLeaves(world, pos);

            Random random = new Random();
            nearbyLeaves.forEach(leafPos -> {
                if (random.nextDouble() <= Config.SPREAD_CHANCE.get()) {
                    normalToInfesting(world, world.getBlockState(leafPos), leafPos);
                }
            });
        }
    }

    private static NonNullList<BlockPos> getNearbyLeaves(World world, BlockPos pos) {
        NonNullList<BlockPos> nearbyLeaves = NonNullList.create();

        BlockPos.getAllInBox(new BlockPos(pos.getX() - 1, pos.getY() - 1, pos.getZ() - 1),
            new BlockPos(pos.getX() + 1, pos.getY() + 1, pos.getZ() + 1)).forEach(item -> {
            if (world.getBlockState(item).getBlock() instanceof LeavesBlock) {
                nearbyLeaves.add(new BlockPos(item));
            }
        });

        return nearbyLeaves;
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {

    }
}
