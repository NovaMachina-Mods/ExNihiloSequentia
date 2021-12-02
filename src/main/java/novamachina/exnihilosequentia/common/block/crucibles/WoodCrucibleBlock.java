package novamachina.exnihilosequentia.common.block.crucibles;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ToolType;
import novamachina.exnihilosequentia.common.block.CrucibleBaseBlock;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;
import novamachina.exnihilosequentia.common.tileentity.crucible.WoodCrucibleTile;

import java.util.function.Supplier;

public class WoodCrucibleBlock extends CrucibleBaseBlock {

    public WoodCrucibleBlock() {
        this(WoodCrucibleTile::new);
    }

    public WoodCrucibleBlock(Supplier<TileEntity> tileEntitySupplier) {
        super(new BlockBuilder().properties(
                        AbstractBlock.Properties.of(Material.WOOD).strength(.75F)
                                .sound(SoundType.STONE).noOcclusion()).harvestLevel(ToolType.AXE, 0)
                .tileEntitySupplier(tileEntitySupplier));
    }

}
