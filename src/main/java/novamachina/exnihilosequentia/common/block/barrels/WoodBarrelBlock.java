package novamachina.exnihilosequentia.common.block.barrels;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.ToolType;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;

public class WoodBarrelBlock extends AbstractBarrelBlock {
    public WoodBarrelBlock() {
        super(new BlockBuilder().harvestLevel(ToolType.AXE, 0)
                .properties(BlockBehaviour.Properties.of(Material.WOOD).strength(0.75F).sound(SoundType.WOOD)));
    }
}
