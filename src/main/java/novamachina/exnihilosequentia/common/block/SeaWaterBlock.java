package novamachina.exnihilosequentia.common.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Material;
import novamachina.exnihilosequentia.common.init.ExNihiloFluids;

public class SeaWaterBlock extends FlowingFluid {
    public SeaWaterBlock() {
        super(ExNihiloFluids.SEA_WATER, Block.Properties.of(Material.WATER).noCollission()
                .strength(100.0F).noDrops());
    }
}
