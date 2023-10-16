package novamachina.exnihilosequentia.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import novamachina.exnihilosequentia.common.Config;
import novamachina.exnihilosequentia.world.level.block.entity.EXNBlockEntityTypes;
import novamachina.exnihilosequentia.world.level.block.entity.StoneBarrelBlockEntity;
import org.checkerframework.checker.nullness.qual.NonNull;

public class NetherBarrelBlock extends BarrelBlock implements EntityBlock {

  public NetherBarrelBlock() {
    super(
        BlockBehaviour.Properties.of()
            .strength(1.0F)
            .sound(Config.getNetherBarrelSoundsEnabled() ? SoundType.STEM : SoundType.WOOD)
            .noOcclusion());
  }

  @Override
  public BlockEntity newBlockEntity(@NonNull BlockPos pos, @NonNull BlockState state) {
    return new StoneBarrelBlockEntity(
        EXNBlockEntityTypes.STONE_BARREL_ENTITY.getType(), pos, state);
  }

  @Override
  public <T extends BlockEntity> BlockEntityTicker<T> getTicker(
      Level level, @NonNull BlockState state, @NonNull BlockEntityType<T> type) {
    if (!level.isClientSide) {
      return (level1, blockPos, blockState, t) -> {
        if (t instanceof StoneBarrelBlockEntity tile) {
          tile.tickServer();
        }
      };
    }
    return null;
  }
}
