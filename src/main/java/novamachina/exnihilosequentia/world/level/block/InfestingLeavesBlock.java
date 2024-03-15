package novamachina.exnihilosequentia.world.level.block;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import novamachina.exnihilosequentia.common.Config;
import novamachina.exnihilosequentia.common.compat.ITooltipProvider;
import novamachina.exnihilosequentia.tags.ExNihiloTags;
import novamachina.exnihilosequentia.world.level.block.entity.EXNBlockEntityTypes;
import novamachina.exnihilosequentia.world.level.block.entity.InfestingLeavesBlockEntity;
import novamachina.novacore.util.StringUtils;

// TODO: Add progressive render
public class InfestingLeavesBlock extends Block implements EntityBlock, ITooltipProvider {

  @Nonnull private static final Random random = new SecureRandom();

  public InfestingLeavesBlock() {
    super(
        BlockBehaviour.Properties.of()
            .strength(0.2F)
            .sound(SoundType.GRASS)
            .noOcclusion()
            .isValidSpawn((blockState, blockGetter, blockPos, entityType) -> false));
  }

  public static void finishInfestingBlock(@Nonnull final Level world, @Nonnull final BlockPos pos) {
    world.setBlockAndUpdate(pos, EXNBlocks.INFESTED_LEAVES.block().defaultBlockState());
  }

  public static void normalToInfesting(@Nonnull final Level world, @Nonnull final BlockPos pos) {
    world.setBlockAndUpdate(pos, EXNBlocks.INFESTING_LEAVES.block().defaultBlockState());
  }

  public static void spread(@Nonnull final Level world, @Nonnull final BlockPos pos) {
    if (!world.isClientSide()) {
      @Nonnull final NonNullList<BlockPos> nearbyLeaves = getNearbyLeaves(world, pos);

      nearbyLeaves.forEach(
          leafPos -> {
            if (random.nextDouble() <= Config.getSpreadChance()) {
              normalToInfesting(world, leafPos);
            }
          });
    }
  }

  @Nonnull
  private static NonNullList<BlockPos> getNearbyLeaves(
      @Nonnull final Level world, @Nonnull final BlockPos pos) {
    @Nonnull final NonNullList<BlockPos> nearbyLeaves = NonNullList.create();

    BlockPos.betweenClosedStream(
            new BlockPos(pos.getX() - 1, pos.getY() - 1, pos.getZ() - 1),
            new BlockPos(pos.getX() + 1, pos.getY() + 1, pos.getZ() + 1))
        .forEach(
            item -> {
              if (world.getBlockState(item).is(ExNihiloTags.INFESTABLE)) {
                nearbyLeaves.add(new BlockPos(item));
              }
            });

    return nearbyLeaves;
  }

  @org.jetbrains.annotations.Nullable
  @Override
  public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
    return new InfestingLeavesBlockEntity(
        EXNBlockEntityTypes.INFESTING_LEAVES_ENTITY.getType(), pPos, pState);
  }

  @Nullable
  @Override
  public <T extends BlockEntity> BlockEntityTicker<T> getTicker(
      @Nonnull Level level, @Nonnull BlockState state, @Nonnull BlockEntityType<T> type) {
    if (!level.isClientSide) {
      return (level1, blockPos, blockState, t) -> {
        if (t instanceof InfestingLeavesBlockEntity tile) {
          tile.tickServer();
        }
      };
    }
    return null;
  }

  @Override
  public List<Component> getTooltipInfo(Level world, BlockPos pos) {
    List<Component> tooltip = new ArrayList<>();
    final InfestingLeavesBlockEntity infestingLeavesBlockEntity =
        (InfestingLeavesBlockEntity) world.getBlockEntity(pos);
    if (infestingLeavesBlockEntity == null) {
      return tooltip;
    }
    return tooltip;
  }

  @Override
  public List<Component> getExpandedTooltipInfo(Level world, BlockPos pos) {
    List<Component> tooltip = new ArrayList<>();
    final InfestingLeavesBlockEntity infestingLeavesBlockEntity =
        (InfestingLeavesBlockEntity) world.getBlockEntity(pos);
    if (infestingLeavesBlockEntity == null) {
      return tooltip;
    }

    tooltip.add(
        Component.translatable(
            "waila.progress",
            StringUtils.formatPercent((float) infestingLeavesBlockEntity.getProgress() / 100)));

    tooltip.addAll(this.getTooltipInfo(world, pos));
    return tooltip;
  }
}
