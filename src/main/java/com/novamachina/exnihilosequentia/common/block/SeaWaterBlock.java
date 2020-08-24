package com.novamachina.exnihilosequentia.common.block;

import com.novamachina.exnihilosequentia.common.init.ModFluids;
import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;

public class SeaWaterBlock extends FlowingFluidBlock {
    public SeaWaterBlock() {
        super(ModFluids.SEA_WATER, Block.Properties.create(Material.WATER).doesNotBlockMovement()
            .hardnessAndResistance(100.0F).noDrops());
    }
}
