package novamachina.exnihilosequentia.common.block;

import novamachina.exnihilosequentia.common.builder.BlockBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class BaseFallingBlock extends FallingBlock {

    private final int harvestLevel;
    private final Supplier<TileEntity> tileEntitySupplier;
    private final ToolType toolType;

    public BaseFallingBlock(BlockBuilder builder) {
        super(builder.getProperties());
        this.tileEntitySupplier = builder.getTileEntitySupplier();
        this.toolType = builder.getToolType();
        this.harvestLevel = builder.getHarvestLevel();
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

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        if (tileEntitySupplier == null) {
            return null;
        } else {
            return tileEntitySupplier.get();
        }
    }
}
