package novamachina.exnihilosequentia.common.block.barrels;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import novamachina.exnihilosequentia.common.block.BlockBarrel;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;
import novamachina.exnihilosequentia.common.tileentity.barrel.WoodBarrelTile;

public class WoodBarrelBlock extends BlockBarrel {
    public WoodBarrelBlock() {
        super(new BlockBuilder().harvestLevel(ToolType.AXE, 0)
                .properties(AbstractBlock.Properties.of(Material.WOOD).strength(0.75F).sound(SoundType.WOOD))
                .tileEntitySupplier(WoodBarrelTile::new));
    }
}
