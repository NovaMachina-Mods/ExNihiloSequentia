package com.novamachina.exnihilosequentia.common.block;

import com.novamachina.exnihilosequentia.common.builder.BlockBuilder;
import com.novamachina.exnihilosequentia.common.tileentity.crucible.WoodCrucibleTile;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class WoodCrucibleBlock extends CrucibleBaseBlock {

    public WoodCrucibleBlock() {
        super(new BlockBuilder().properties(
            Block.Properties.create(Material.WOOD).hardnessAndResistance(.75F)
                .sound(SoundType.STONE).notSolid()).harvestLevel(ToolType.AXE, 0)
            .tileEntitySupplier(WoodCrucibleTile::new));
    }
}
