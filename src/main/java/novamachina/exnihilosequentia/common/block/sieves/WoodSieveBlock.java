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
import novamachina.exnihilosequentia.common.builder.BlockBuilder;

public class WoodSieveBlock extends BlockSieve implements EntityBlock {

  public WoodSieveBlock() {
    super(new BlockBuilder().properties(
        BlockBehaviour.Properties.of(Material.WOOD).strength(0.7F)
            .sound(SoundType.SCAFFOLDING).noOcclusion()
            .isRedstoneConductor((state, reader, pos) -> false)
            .isSuffocating((state, reader, pos) -> false)
            .isViewBlocking((state, reader, pos) -> false)));
  }

  @Nullable
  @Override
  public BlockEntity newBlockEntity(@Nonnull BlockPos pos, @Nonnull BlockState state) {
    return new SieveEntity(pos, state);
  }
}
