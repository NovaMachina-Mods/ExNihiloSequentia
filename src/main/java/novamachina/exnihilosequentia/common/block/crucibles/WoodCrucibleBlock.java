package novamachina.exnihilosequentia.common.block.crucibles;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.ToolType;
import novamachina.exnihilosequentia.common.block.CrucibleBaseBlock;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;
import novamachina.exnihilosequentia.common.tileentity.crucible.WoodCrucibleTile;

public class WoodCrucibleBlock extends CrucibleBaseBlock {

    public WoodCrucibleBlock() {
        super(new BlockBuilder().properties(
                Block.Properties.of(Material.WOOD).strength(.75F)
                        .sound(SoundType.STONE).noOcclusion()).harvestLevel(ToolType.AXE, 0)
                .tileEntitySupplier(WoodCrucibleTile::new));
    }
}
