package novamachina.exnihilosequentia.common.block;

import java.util.function.Supplier;
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

    private final Supplier<TileEntity> tileEntitySupplier;
    private final ToolType toolType;

    public BaseBlock(BlockBuilder builder) {
        super(builder.getProperties());
        this.tileEntitySupplier = builder.getTileEntitySupplier();
        this.toolType = builder.getToolType();
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
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

    protected static Boolean never(BlockState p_235427_0_, IBlockReader p_235427_1_, BlockPos p_235427_2_, EntityType<?> p_235427_3_) {
        return (boolean)false;
    }

}
