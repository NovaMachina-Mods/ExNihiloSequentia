package novamachina.exnihilosequentia.common.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;
import novamachina.exnihilosequentia.common.tileentity.crucible.FiredCrucibleTile;

public class FiredCrucibleBlock extends CrucibleBaseBlock {

    public FiredCrucibleBlock() {
        super(new BlockBuilder().properties(
                AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(1.5F)
                        .sound(SoundType.STONE).notSolid()).harvestLevel(ToolType.PICKAXE, 0)
                .tileEntitySupplier(FiredCrucibleTile::new));
    }
}
