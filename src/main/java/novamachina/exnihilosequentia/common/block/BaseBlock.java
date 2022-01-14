package novamachina.exnihilosequentia.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Supplier;

public class BaseBlock extends Block implements EntityBlock {

    private final Supplier<BlockEntity> tileEntitySupplier;

    public BaseBlock(BlockBuilder builder) {
        super(builder.getProperties());
        this.tileEntitySupplier = builder.getTileEntitySupplier();
    }


    protected static Boolean never(BlockState p_235427_0_, BlockGetter p_235427_1_, BlockPos p_235427_2_, EntityType<?> p_235427_3_) {
        return false;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@Nonnull BlockPos pos, @Nonnull BlockState state) {
        if (tileEntitySupplier == null) {
            return null;
        } else {
            return tileEntitySupplier.get();
        }
    }
}
