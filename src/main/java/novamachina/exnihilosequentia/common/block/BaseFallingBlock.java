package novamachina.exnihilosequentia.common.block;

import java.util.function.Supplier;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;

public class BaseFallingBlock extends FallingBlock {

    @Nullable private final Supplier<TileEntity> tileEntitySupplier;
    @Nonnull private final ToolType toolType;

    public BaseFallingBlock(@Nonnull final BlockBuilder builder) {
        super(builder.getProperties());
        this.tileEntitySupplier = builder.getTileEntitySupplier();
        this.toolType = builder.getToolType();
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(@Nonnull final BlockState state, @Nonnull final IBlockReader world) {
        if (tileEntitySupplier == null) {
            return null;
        } else {
            return tileEntitySupplier.get();
        }
    }

    @Nullable
    @Override
    public ToolType getHarvestTool(@Nonnull final BlockState state) {
        return toolType;
    }

    @Override
    public boolean hasTileEntity(@Nonnull final BlockState state) {
        return tileEntitySupplier != null;
    }
}
