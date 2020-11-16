package novamachina.exnihilosequentia.common.block;

import novamachina.exnihilosequentia.common.builder.BlockBuilder;
import novamachina.exnihilosequentia.common.tileentity.crucible.FiredCrucibleTile;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class FiredCrucibleBlock extends CrucibleBaseBlock {

    public FiredCrucibleBlock() {
        super(new BlockBuilder().properties(
            Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5F)
                .sound(SoundType.STONE).notSolid()).harvestLevel(ToolType.PICKAXE, 0)
            .tileEntitySupplier(FiredCrucibleTile::new));
    }
}
