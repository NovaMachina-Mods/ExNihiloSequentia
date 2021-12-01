package novamachina.exnihilosequentia.common.block.sieves;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.ToolActions;
import novamachina.exnihilosequentia.common.block.BlockSieve;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;

public class NetherSieveBlock extends BlockSieve {
    public NetherSieveBlock() {
        super(new BlockBuilder().properties(
                BlockBehaviour.Properties.of(Material.NETHER_WOOD).strength(1.0F)
                        .sound(SoundType.STEM).noOcclusion().isRedstoneConductor((state, reader, pos) -> false)
                        .isSuffocating((state, reader, pos) -> false).isViewBlocking((state, reader, pos) -> false))
                .harvestLevel(ToolActions.AXE_DIG));
    }
}
