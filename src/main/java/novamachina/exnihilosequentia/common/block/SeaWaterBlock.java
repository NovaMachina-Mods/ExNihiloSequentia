package novamachina.exnihilosequentia.common.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import novamachina.exnihilosequentia.common.init.ModFluids;

public class SeaWaterBlock extends FlowingFluidBlock {
    public SeaWaterBlock() {
        super(ModFluids.SEA_WATER, AbstractBlock.Properties.create(Material.WATER).doesNotBlockMovement()
            .hardnessAndResistance(100.0F).noDrops());
    }
}
