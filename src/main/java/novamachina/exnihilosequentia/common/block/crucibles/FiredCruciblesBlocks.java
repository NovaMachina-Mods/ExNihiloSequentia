package novamachina.exnihilosequentia.common.block.crucibles;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import novamachina.exnihilosequentia.common.block.CrucibleBaseBlock;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;
import novamachina.exnihilosequentia.common.tileentity.crucible.FiredCrucibleTile;

import javax.annotation.Nullable;

public class FiredCruciblesBlocks extends CrucibleBaseBlock implements EntityBlock {

    protected FiredCruciblesBlocks(BlockBuilder builder) {super(builder);}

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new FiredCrucibleTile(blockPos, blockState);
    }
}
