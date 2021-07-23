package novamachina.exnihilosequentia.common.block.crucibles;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.ToolType;
import novamachina.exnihilosequentia.common.block.BaseBlock;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;

public class UnfiredCrucibleBlock extends BaseBlock {

    public UnfiredCrucibleBlock() {
        super(new BlockBuilder().properties(
                Block.Properties.of(Material.CLAY).strength(0.6F)
                        .sound(SoundType.GRAVEL).noOcclusion()).harvestLevel(ToolType.SHOVEL, 0));
    }
}
