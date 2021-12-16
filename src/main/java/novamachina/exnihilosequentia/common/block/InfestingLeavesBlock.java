package novamachina.exnihilosequentia.common.block;

import java.util.Random;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;
import novamachina.exnihilosequentia.common.compat.top.ITOPInfoProvider;
import novamachina.exnihilosequentia.common.init.ExNihiloBlocks;
import novamachina.exnihilosequentia.common.tileentity.InfestingLeavesTile;
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.common.utility.StringUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

// TODO: Add progressive render
public class InfestingLeavesBlock extends BaseBlock implements ITOPInfoProvider {

    @Nonnull private static final Random random = new Random();

    public InfestingLeavesBlock() {
        super(new BlockBuilder()
                .properties(AbstractBlock.Properties.of(Material.LEAVES).strength(0.2F).sound(
                        SoundType.GRASS).noOcclusion().isValidSpawn(BaseBlock::never)).tileEntitySupplier(InfestingLeavesTile::new));
    }

    public static void finishInfestingBlock(@Nonnull final World world, @Nonnull final BlockPos pos) {
        world.setBlockAndUpdate(pos, ExNihiloBlocks.INFESTED_LEAVES.get().defaultBlockState());
    }

    public static void normalToInfesting(@Nonnull final World world, @Nonnull final BlockPos pos) {
        world.setBlockAndUpdate(pos, ExNihiloBlocks.INFESTING_LEAVES.get().defaultBlockState());
    }

    public static void spread(@Nonnull final World world, @Nonnull final BlockPos pos) {
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
    private static NonNullList<BlockPos> getNearbyLeaves(@Nonnull final World world, @Nonnull final BlockPos pos) {
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
                             @Nonnull final PlayerEntity playerEntity, @Nonnull final World world,
                             @Nonnull final BlockState blockState, @Nonnull final IProbeHitData iProbeHitData) {
        if(probeMode == ProbeMode.EXTENDED) {
            @Nullable final InfestingLeavesTile infestingLeavesTile = (InfestingLeavesTile) world
                    .getBlockEntity(iProbeHitData.getPos());

            if (infestingLeavesTile != null)
                iProbeInfo.text(new TranslationTextComponent("waila.progress", StringUtils
                        .formatPercent((float) infestingLeavesTile.getProgress() / 100)));
        }
    }
}
