package novamachina.exnihilosequentia.world.level.block;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import novamachina.exnihilosequentia.common.Config;
import novamachina.exnihilosequentia.world.level.block.entity.EXNBlockEntityTypes;
import novamachina.exnihilosequentia.world.level.block.entity.SieveBlockEntity;

public class NetherSieveBlock extends SieveBlock implements EntityBlock {

  public NetherSieveBlock() {
    super(
        BlockBehaviour.Properties.of()
            .strength(1.0F)
            .sound(Config.getNetherSieveSoundsEnabled() ? SoundType.STEM : SoundType.SCAFFOLDING)
            .noOcclusion()
            .isRedstoneConductor((state, reader, pos) -> false)
            .isSuffocating((state, reader, pos) -> false)
            .isViewBlocking((state, reader, pos) -> false));
  }

  @Nullable
  @Override
  public BlockEntity newBlockEntity(@Nonnull BlockPos pos, @Nonnull BlockState state) {
    return new SieveBlockEntity(EXNBlockEntityTypes.SIEVE_ENTITY.getType(), pos, state);
  }
}
