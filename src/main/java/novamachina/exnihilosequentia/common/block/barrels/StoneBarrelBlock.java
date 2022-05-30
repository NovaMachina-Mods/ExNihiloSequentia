package novamachina.exnihilosequentia.common.block.barrels;

import javax.annotation.Nonnull;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import novamachina.exnihilosequentia.common.block.BlockBarrel;
import novamachina.exnihilosequentia.common.blockentity.barrel.StoneBarrelEntity;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;
import org.jetbrains.annotations.Nullable;

public class StoneBarrelBlock extends BlockBarrel implements EntityBlock {

  public StoneBarrelBlock(BlockBuilder builder) {
    super(builder);
  }

  @Nullable
  @Override
  public BlockEntity newBlockEntity(@Nonnull BlockPos pos, @Nonnull BlockState state) {
    return new StoneBarrelEntity(pos, state);
  }

  @Nullable
  @Override
  public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@Nonnull Level level,
      @Nonnull BlockState state, @Nonnull BlockEntityType<T> type) {
    if (!level.isClientSide) {
      return (level1, blockPos, blockState, t) -> {
        if (t instanceof StoneBarrelEntity tile) {
          tile.tickServer();
        }
      };
    }
    return null;
  }
}
