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
import novamachina.exnihilosequentia.world.level.block.entity.EXNBlockEntityTypes;
import novamachina.exnihilosequentia.world.level.block.entity.WoodBarrelBlockEntity;
import org.checkerframework.checker.nullness.qual.NonNull;

public class WoodBarrelBlock extends BarrelBlock implements EntityBlock {

  public WoodBarrelBlock() {
    super(BlockBehaviour.Properties.of().strength(0.75F).sound(SoundType.WOOD).noOcclusion());
  }

  @Override
  public BlockEntity newBlockEntity(@NonNull BlockPos pos, @NonNull BlockState state) {
    return new WoodBarrelBlockEntity(
        EXNBlockEntityTypes.WOODEN_BARREL_ENTITY.getType(), pos, state);
  }

  @Override
  public <T extends BlockEntity> BlockEntityTicker<T> getTicker(
      Level level, @NonNull BlockState state, @NonNull BlockEntityType<T> type) {
    if (!level.isClientSide) {
      return (level1, blockPos, blockState, t) -> {
        if (t instanceof WoodBarrelBlockEntity tile) {
          tile.tickServer();
        }
      };
    }
    return null;
  }
}
