package novamachina.exnihilosequentia.common.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;
import novamachina.exnihilosequentia.common.tileentity.crucible.WoodCrucibleTile;

public class WoodCrucibleBlock extends CrucibleBaseBlock {

    public WoodCrucibleBlock() {
        super(new BlockBuilder().properties(
                AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(.75F)
                        .sound(SoundType.STONE).notSolid()).harvestLevel(ToolType.AXE, 0)
                .tileEntitySupplier(WoodCrucibleTile::new));
    }
}
