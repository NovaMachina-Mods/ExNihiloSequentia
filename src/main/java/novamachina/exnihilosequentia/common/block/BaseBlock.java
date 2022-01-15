package novamachina.exnihilosequentia.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;

import javax.annotation.Nonnull;

public class BaseBlock extends Block {

    public BaseBlock(@Nonnull final BlockBuilder builder) {
        super(builder.getProperties());
    }

    @SuppressWarnings("unused")
    protected static Boolean never(@Nonnull final BlockState p_235427_0_, @Nonnull final BlockGetter p_235427_1_,
                                   @Nonnull final BlockPos p_235427_2_,@Nonnull final EntityType<?> p_235427_3_) {
        return false;
    }
}
