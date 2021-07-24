package novamachina.exnihilosequentia.common.block.sieves;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.ToolType;
import novamachina.exnihilosequentia.common.block.BlockSieve;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;
import novamachina.exnihilosequentia.common.tileentity.SieveTile;

public class NetherSieveBlock extends BlockSieve {
    public NetherSieveBlock() {
        super(new BlockBuilder().properties(
                Block.Properties.of(Material.NETHER_WOOD).strength(1.0F)
                        .sound(SoundType.STEM).noOcclusion().isRedstoneConductor((state, reader, pos) -> false)
                        .isSuffocating((state, reader, pos) -> false).isViewBlocking((state, reader, pos) -> false)));
    }
}
