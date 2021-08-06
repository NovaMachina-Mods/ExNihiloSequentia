package novamachina.exnihilosequentia.common.block.barrels;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import novamachina.exnihilosequentia.common.block.BlockBarrel;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;
import novamachina.exnihilosequentia.common.tileentity.barrel.StoneBarrelTile;

import javax.annotation.Nullable;

public class AbstractBarrelBlock extends BlockBarrel {
    public AbstractBarrelBlock(BlockBuilder builder) {
        super(builder);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new StoneBarrelTile(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level1, BlockState state, BlockEntityType<T> type) {
        if (level1.isClientSide) {
            return null;
        }
        return (level, blockPos, blockState, t) -> {
            if (t instanceof StoneBarrelTile tile) {
                tile.tick();
            }
        };
    }
}
