package novamachina.exnihilosequentia.common.block.barrels;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.ToolActions;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;

public class NetherBarrelBlock extends AbstractBarrelBlock {
    public NetherBarrelBlock() {
        super(new BlockBuilder().harvestLevel(ToolActions.AXE_DIG, 0)
                .properties(BlockBehaviour.Properties.of(Material.NETHER_WOOD).strength(1.0F).sound(SoundType.STEM)));
    }
}
