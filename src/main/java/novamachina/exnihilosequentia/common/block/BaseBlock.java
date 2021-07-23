package novamachina.exnihilosequentia.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolType;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class BaseBlock extends Block {

    private final Supplier<BlockEntity> tileEntitySupplier;
    private final ToolType toolType;

    public BaseBlock(BlockBuilder builder) {
        super(builder.getProperties());
        this.tileEntitySupplier = builder.getTileEntitySupplier();
        this.toolType = builder.getToolType();
    }


    @Nullable
    @Override
    public BlockEntity createTileEntity(BlockState state, BlockGetter world) {
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

    protected static Boolean never(BlockState p_235427_0_, BlockGetter p_235427_1_, BlockPos p_235427_2_, EntityType<?> p_235427_3_) {
        return (boolean)false;
    }

}
