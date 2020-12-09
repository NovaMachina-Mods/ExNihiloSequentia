package novamachina.exnihilosequentia.common.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;

public class UnfiredCrucibleBlock extends BaseBlock {

    public UnfiredCrucibleBlock() {
        super(new BlockBuilder().properties(
            AbstractBlock.Properties.create(Material.CLAY).hardnessAndResistance(0.6F)
                .sound(SoundType.GROUND).notSolid()).harvestLevel(ToolType.SHOVEL, 0));
    }
}
