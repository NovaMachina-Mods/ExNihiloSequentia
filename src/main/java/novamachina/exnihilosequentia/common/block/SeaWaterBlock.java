package novamachina.exnihilosequentia.common.block;

import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import novamachina.exnihilosequentia.common.init.ExNihiloFluids;

public class SeaWaterBlock extends LiquidBlock {
    public SeaWaterBlock() {
        super(ExNihiloFluids.SEA_WATER, BlockBehaviour.Properties.of(Material.WATER).noCollission()
                .strength(100.0F).noDrops());
    }
}
