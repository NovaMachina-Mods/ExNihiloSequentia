package novamachina.exnihilosequentia.common.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;

public class UnfiredCrucibleBlock extends BaseBlock {

    public UnfiredCrucibleBlock() {
        super(new BlockBuilder().properties(
                AbstractBlock.Properties.of(Material.CLAY).strength(0.6F)
                        .sound(SoundType.GRAVEL).noOcclusion()).harvestLevel(ToolType.SHOVEL, 0));
    }
}
