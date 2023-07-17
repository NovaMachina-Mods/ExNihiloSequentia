package novamachina.exnihilosequentia.common.block.sieves;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import novamachina.exnihilosequentia.common.block.BlockSieve;
import novamachina.exnihilosequentia.common.blockentity.SieveEntity;
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.world.level.block.entity.EXNBlockEntityTypes;

public class NetherSieveBlock extends BlockSieve implements EntityBlock {

  public NetherSieveBlock() {
    super(
        BlockBehaviour.Properties.of(Material.NETHER_WOOD)
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
    return new SieveEntity(EXNBlockEntityTypes.SIEVE_ENTITY.getType(), pos, state);
  }
}
