package novamachina.exnihilosequentia.common.block.crucibles;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import novamachina.exnihilosequentia.common.block.CrucibleBaseBlock;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;
import novamachina.exnihilosequentia.common.tileentity.crucible.FiredCrucibleTile;

public class FiredCrucibleBlock extends CrucibleBaseBlock {

    public FiredCrucibleBlock() {
        super(new BlockBuilder().properties(
                AbstractBlock.Properties.of(Material.STONE).strength(1.5F)
                        .sound(SoundType.STONE).noOcclusion()).harvestLevel(ToolType.PICKAXE, 0)
                .tileEntitySupplier(FiredCrucibleTile::new));
    }
}
