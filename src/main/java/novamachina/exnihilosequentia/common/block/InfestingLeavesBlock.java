package novamachina.exnihilosequentia.common.block;

import java.util.Random;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.NonNullList;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;
import novamachina.exnihilosequentia.common.compat.top.ITOPInfoProvider;
import novamachina.exnihilosequentia.common.init.ExNihiloBlocks;
import novamachina.exnihilosequentia.common.tileentity.InfestingLeavesTile;
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.common.utility.StringUtils;

import javax.annotation.Nullable;

// TODO: Add progressive render
public class InfestingLeavesBlock extends BaseBlock implements ITOPInfoProvider {

    private static final Random random = new Random();

    public InfestingLeavesBlock() {
        super(new BlockBuilder()
                .properties(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).sound(
                        SoundType.GRASS).noOcclusion().isValidSpawn(BaseBlock::never)));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new InfestingLeavesTile(pos, state);
    }

    public static void finishInfestingBlock(Level world, BlockPos pos) {
        world.setBlockAndUpdate(pos, ExNihiloBlocks.INFESTED_LEAVES.get().defaultBlockState());
    }

    public static void normalToInfesting(Level world, BlockPos pos) {
        world.setBlockAndUpdate(pos, ExNihiloBlocks.INFESTING_LEAVES.get().defaultBlockState());
    }

    public static void spread(Level world, BlockPos pos) {
        if (!world.isClientSide()) {
            NonNullList<BlockPos> nearbyLeaves = getNearbyLeaves(world, pos);

            nearbyLeaves.forEach(leafPos -> {
                if (random.nextDouble() <= Config.getSpreadChance()) {
                    normalToInfesting(world, leafPos);
                }
            });
        }
    }

    private static NonNullList<BlockPos> getNearbyLeaves(Level world, BlockPos pos) {
        NonNullList<BlockPos> nearbyLeaves = NonNullList.create();

        BlockPos.betweenClosedStream(new BlockPos(pos.getX() - 1, pos.getY() - 1, pos.getZ() - 1),
                new BlockPos(pos.getX() + 1, pos.getY() + 1, pos.getZ() + 1)).forEach(item -> {
            if (world.getBlockState(item).getBlock() instanceof LeavesBlock) {
                nearbyLeaves.add(new BlockPos(item));
            }
        });

        return nearbyLeaves;
    }

    @Override
    public void addProbeInfo(ProbeMode probeMode, IProbeInfo iProbeInfo, Player playerEntity, Level world, BlockState blockState, IProbeHitData iProbeHitData) {
        InfestingLeavesTile infestingLeavesTile = (InfestingLeavesTile) world.getBlockEntity(iProbeHitData.getPos());

        iProbeInfo.text(new TranslatableComponent("waila.progress", StringUtils
                .formatPercent((float) infestingLeavesTile.getProgress() / 100)));
    }

}
