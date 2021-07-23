package novamachina.exnihilosequentia.common.block.barrels;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import novamachina.exnihilosequentia.common.block.BlockBarrel;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;
import novamachina.exnihilosequentia.common.tileentity.barrel.StoneBarrelTile;

public class NetherBarrelBlock extends BlockBarrel {
    public NetherBarrelBlock() {
        super(new BlockBuilder().harvestLevel(ToolType.AXE, 0)
                .properties(AbstractBlock.Properties.of(Material.NETHER_WOOD).strength(1.0F).sound(SoundType.STEM))
                .tileEntitySupplier(StoneBarrelTile::new));
    }
}
