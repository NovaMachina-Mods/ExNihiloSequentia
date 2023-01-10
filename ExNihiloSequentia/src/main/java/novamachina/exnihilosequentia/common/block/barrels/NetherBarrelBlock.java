package novamachina.exnihilosequentia.common.block.barrels;

import javax.annotation.Nonnull;
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
import novamachina.exnihilosequentia.common.block.BlockBarrel;
import novamachina.exnihilosequentia.common.blockentity.barrel.StoneBarrelEntity;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;
import novamachina.exnihilosequentia.common.utility.Config;
import org.jetbrains.annotations.Nullable;

public class NetherBarrelBlock extends BlockBarrel implements EntityBlock {

  public NetherBarrelBlock() {
    super(new BlockBuilder()
        .properties(Properties.of(Material.NETHER_WOOD).strength(1.0F)
            .sound(Config.getNetherBarrelSoundsEnabled() ? SoundType.STEM : SoundType.WOOD).noOcclusion()));
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
