package novamachina.exnihilosequentia.common.block;

import novamachina.exnihilosequentia.common.builder.BlockBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class UnfiredCrucibleBlock extends BaseBlock {

    public UnfiredCrucibleBlock() {
        super(new BlockBuilder().properties(
            Block.Properties.create(Material.CLAY).hardnessAndResistance(0.6F)
                .sound(SoundType.GROUND).notSolid()).harvestLevel(ToolType.SHOVEL, 0));
    }
}
