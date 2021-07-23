package novamachina.exnihilosequentia.common.block;

import java.util.function.Supplier;
import javax.annotation.Nullable;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolType;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;

public class BaseFallingBlock extends FallingBlock {

    private final Supplier<BlockEntity> tileEntitySupplier;
    private final ToolType toolType;

    public BaseFallingBlock(BlockBuilder builder) {
        super(builder.getProperties());
        this.tileEntitySupplier = builder.getTileEntitySupplier();
        this.toolType = builder.getToolType();
    }

    @Nullable
    @Override
    public BlockEntity createTileEntity(BlockState state, IBlockReader world) {
        if (tileEntitySupplier == null) {
            return null;
        } else {
            return tileEntitySupplier.get();
        }
    }

    @Nullable
    @Override
    public ToolType getHarvestTool(BlockState state) {
        return toolType;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return tileEntitySupplier != null;
    }
}
