package novamachina.exnihilosequentia.common.block.barrels;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import novamachina.exnihilosequentia.common.block.BlockBarrel;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;
import novamachina.exnihilosequentia.common.tileentity.barrel.StoneBarrelTile;

public class StoneBarrelBlock extends BlockBarrel {
    public StoneBarrelBlock() {
        super(new BlockBuilder().harvestLevel(ToolType.PICKAXE, 0)
                .properties(AbstractBlock.Properties.of(Material.STONE).strength(0.75F).sound(SoundType.STONE))
                .tileEntitySupplier(StoneBarrelTile::new));
    }
}
