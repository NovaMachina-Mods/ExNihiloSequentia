package novamachina.exnihilosequentia.common.block.crucibles;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import novamachina.exnihilosequentia.common.block.CrucibleBaseBlock;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;
import novamachina.exnihilosequentia.common.tileentity.crucible.FiredCrucibleTile;
import novamachina.exnihilosequentia.common.utility.Config;

public class NetherCrucibleBlock extends CrucibleBaseBlock {
    public NetherCrucibleBlock() {
        super(new BlockBuilder().properties(
                AbstractBlock.Properties.of(Material.NETHER_WOOD).strength(1.0F)
                        .sound(Config.getNetherCrucibleSoundsEnabled() ? SoundType.STEM : SoundType.WOOD).noOcclusion()).harvestLevel(ToolType.AXE, 0)
                .tileEntitySupplier(FiredCrucibleTile::new));
    }
}
