package novamachina.exnihilosequentia.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Supplier;

public class BaseFallingBlock extends FallingBlock implements EntityBlock {

    private final Supplier<BlockEntity> tileEntitySupplier;

    public BaseFallingBlock(BlockBuilder builder) {
        super(builder.getProperties());
        this.tileEntitySupplier = builder.getTileEntitySupplier();
        ToolAction toolType = builder.getToolType();
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@Nonnull BlockPos p_153215_, @Nonnull BlockState p_153216_) {
        if (tileEntitySupplier == null) {
            return null;
        } else {
            return tileEntitySupplier.get();
        }
    }
}
