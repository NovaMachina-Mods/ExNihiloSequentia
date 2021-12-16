package novamachina.exnihilosequentia.common.block;

import java.util.function.Supplier;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;

public class BaseBlock extends Block {

    @Nullable private final Supplier<TileEntity> tileEntitySupplier;
    @Nonnull private final ToolType toolType;

    public BaseBlock(@Nonnull final BlockBuilder builder) {
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

    @SuppressWarnings("unused")
    protected static Boolean never(@Nonnull final BlockState p_235427_0_, @Nonnull final IBlockReader p_235427_1_,
                                   @Nonnull final BlockPos p_235427_2_,@Nonnull final EntityType<?> p_235427_3_) {
        return false;
    }

}
