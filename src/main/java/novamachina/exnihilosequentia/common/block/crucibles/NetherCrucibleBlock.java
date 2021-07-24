package novamachina.exnihilosequentia.common.block.crucibles;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.ToolType;
import novamachina.exnihilosequentia.common.block.CrucibleBaseBlock;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;
import novamachina.exnihilosequentia.common.tileentity.crucible.FiredCrucibleTile;

public class NetherCrucibleBlock extends FiredCruciblesBlocks {
    public NetherCrucibleBlock() {
        super(new BlockBuilder().properties(
                Block.Properties.of(Material.NETHER_WOOD).strength(1.0F)
                        .sound(SoundType.STEM).noOcclusion()).harvestLevel(ToolType.AXE, 0));
    }
}
