package novamachina.exnihilosequentia.world.level.block;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import novamachina.exnihilosequentia.world.level.block.entity.EXNBlockEntityTypes;
import novamachina.exnihilosequentia.world.level.block.entity.FiredCrucibleBlockBlockEntity;

public class FiredCrucibleBlock extends CrucibleBlock implements EntityBlock {

  public FiredCrucibleBlock() {
    super(BlockBehaviour.Properties.of().strength(1.5F).sound(SoundType.STONE).noOcclusion());
  }

  @Nullable
  @Override
  public BlockEntity newBlockEntity(@Nonnull BlockPos pos, @Nonnull BlockState state) {
    return new FiredCrucibleBlockBlockEntity(
        EXNBlockEntityTypes.FIRED_CRUCIBLE_ENTITY.getType(), pos, state);
  }

  @Nullable
  @Override
  public <T extends BlockEntity> BlockEntityTicker<T> getTicker(
      @Nonnull Level level, @Nonnull BlockState state, @Nonnull BlockEntityType<T> type) {
    if (!level.isClientSide) {
      return (level1, blockPos, blockState, t) -> {
        if (t instanceof FiredCrucibleBlockBlockEntity tile) {
          tile.tickServer();
        }
      };
    }
    return null;
  }
}
