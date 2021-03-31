package novamachina.exnihilosequentia.common.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import novamachina.exnihilosequentia.common.init.ExNihiloFluids;

public class SeaWaterBlock extends FlowingFluidBlock {
    public SeaWaterBlock() {
        super(ExNihiloFluids.SEA_WATER, AbstractBlock.Properties.of(Material.WATER).noCollission()
                .strength(100.0F).noDrops());
    }
}
