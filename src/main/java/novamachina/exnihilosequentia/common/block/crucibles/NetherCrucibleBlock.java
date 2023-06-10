package novamachina.exnihilosequentia.common.block.crucibles;

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
import net.minecraft.world.level.material.Material;
import novamachina.exnihilosequentia.common.block.CrucibleBaseBlock;
import novamachina.exnihilosequentia.common.blockentity.crucible.FiredCrucibleEntity;
import novamachina.exnihilosequentia.world.level.block.entity.EXNBlockEntityTypes;
import novamachina.exnihilosequentia.common.utility.Config;

public class NetherCrucibleBlock extends CrucibleBaseBlock implements EntityBlock {

  public NetherCrucibleBlock() {
    super(
        BlockBehaviour.Properties.of(Material.NETHER_WOOD)
            .strength(1.0F)
            .sound(Config.getNetherCrucibleSoundsEnabled() ? SoundType.STEM : SoundType.WOOD)
            .noOcclusion());
  }

  @Nullable
  @Override
  public BlockEntity newBlockEntity(@Nonnull BlockPos pos, @Nonnull BlockState state) {
    return new FiredCrucibleEntity(EXNBlockEntityTypes.FIRED_CRUCIBLE_ENTITY.getType(), pos, state);
  }

  @Nullable
  @Override
  public <T extends BlockEntity> BlockEntityTicker<T> getTicker(
      @Nonnull Level level, @Nonnull BlockState state, @Nonnull BlockEntityType<T> type) {
    if (!level.isClientSide) {
      return (level1, blockPos, blockState, t) -> {
        if (t instanceof FiredCrucibleEntity tile) {
          tile.tickServer();
        }
      };
    }
    return null;
  }
}
