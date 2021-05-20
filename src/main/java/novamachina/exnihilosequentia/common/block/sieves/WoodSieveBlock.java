package novamachina.exnihilosequentia.common.block.sieves;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import novamachina.exnihilosequentia.common.block.BlockSieve;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;
import novamachina.exnihilosequentia.common.tileentity.SieveTile;

public class WoodSieveBlock extends BlockSieve {
    public WoodSieveBlock() {
        super(new BlockBuilder().properties(
                AbstractBlock.Properties.of(Material.WOOD).strength(0.7F)
                        .sound(SoundType.WOOD).noOcclusion().isRedstoneConductor((state, reader, pos) -> false)
                        .isSuffocating((state, reader, pos) -> false).isViewBlocking((state, reader, pos) -> false))
                .harvestLevel(ToolType.AXE, 0).tileEntitySupplier(SieveTile::new));
    }
}
