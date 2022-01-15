package novamachina.exnihilosequentia.common.block;

import java.util.Random;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

// TODO: Add progressive render
public class InfestingLeavesBlock extends BaseBlock implements ITOPInfoProvider, EntityBlock {

    @Nonnull private static final Random random = new Random();

    public InfestingLeavesBlock() {
        super(new BlockBuilder()
                .properties(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).sound(
                        SoundType.GRASS).noOcclusion().isValidSpawn(BaseBlock::never)));
    }

    public static void finishInfestingBlock(@Nonnull final Level world, @Nonnull final BlockPos pos) {
        world.setBlockAndUpdate(pos, ExNihiloBlocks.INFESTED_LEAVES.get().defaultBlockState());
    }

    public static void normalToInfesting(@Nonnull final Level world, @Nonnull final BlockPos pos) {
        world.setBlockAndUpdate(pos, ExNihiloBlocks.INFESTING_LEAVES.get().defaultBlockState());
    }

    public static void spread(@Nonnull final Level world, @Nonnull final BlockPos pos) {
        if (!world.isClientSide()) {
            @Nonnull final NonNullList<BlockPos> nearbyLeaves = getNearbyLeaves(world, pos);

            nearbyLeaves.forEach(leafPos -> {
                if (random.nextDouble() <= Config.getSpreadChance()) {
                    normalToInfesting(world, leafPos);
                }
            });
        }
    }

    @Nonnull
    private static NonNullList<BlockPos> getNearbyLeaves(@Nonnull final Level world, @Nonnull final BlockPos pos) {
        @Nonnull final NonNullList<BlockPos> nearbyLeaves = NonNullList.create();

        BlockPos.betweenClosedStream(new BlockPos(pos.getX() - 1, pos.getY() - 1, pos.getZ() - 1),
                new BlockPos(pos.getX() + 1, pos.getY() + 1, pos.getZ() + 1)).forEach(item -> {
            if (world.getBlockState(item).getBlock() instanceof LeavesBlock) {
                nearbyLeaves.add(new BlockPos(item));
            }
        });

        return nearbyLeaves;
    }

    @Override
    public void addProbeInfo(@Nonnull final ProbeMode probeMode, @Nonnull final IProbeInfo iProbeInfo,
                             @Nonnull final Player playerEntity, @Nonnull final Level world,
                             @Nonnull final BlockState blockState, @Nonnull final IProbeHitData iProbeHitData) {
        if(probeMode == ProbeMode.EXTENDED) {
            @Nullable final InfestingLeavesTile infestingLeavesTile = (InfestingLeavesTile) world
                    .getBlockEntity(iProbeHitData.getPos());

            if (infestingLeavesTile != null)
                iProbeInfo.text(new TranslatableComponent("waila.progress", StringUtils
                        .formatPercent((float) infestingLeavesTile.getProgress() / 100)));
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@Nonnull BlockPos pos, @Nonnull BlockState state) {
        return new InfestingLeavesTile(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@Nonnull Level level, @Nonnull BlockState state, @Nonnull BlockEntityType<T> type) {
        if (!level.isClientSide) {
            return (level1, blockPos, blockState, t) -> {
                if (t instanceof InfestingLeavesTile tile) {
                    tile.tickServer();
                }
            };
        }
        return null;
    }
}
