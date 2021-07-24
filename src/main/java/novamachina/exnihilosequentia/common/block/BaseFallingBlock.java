package novamachina.exnihilosequentia.common.block;

import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolType;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;

import javax.annotation.Nullable;

public class BaseFallingBlock extends FallingBlock {

    private final ToolType toolType;

    public BaseFallingBlock(BlockBuilder builder) {
        super(builder.getProperties());
        this.toolType = builder.getToolType();
    }

    @Nullable
    @Override
    public ToolType getHarvestTool(BlockState state) {
        return toolType;
    }
}
