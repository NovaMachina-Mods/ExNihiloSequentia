package novamachina.exnihilosequentia.common.block;

import net.minecraft.world.level.block.FallingBlock;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;

public class BaseFallingBlock extends FallingBlock {

    public BaseFallingBlock(BlockBuilder builder) {
        super(builder.getProperties());
    }
}
