package novamachina.exnihilosequentia.common.block.crucibles;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ToolType;
import novamachina.exnihilosequentia.common.block.CrucibleBaseBlock;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;
import novamachina.exnihilosequentia.common.tileentity.crucible.FiredCrucibleTile;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public class FiredCrucibleBlock extends CrucibleBaseBlock {

    public FiredCrucibleBlock() {
        this(FiredCrucibleTile::new);
    }

    public FiredCrucibleBlock(@Nonnull final Supplier<TileEntity> tileEntitySupplier) {
        super(new BlockBuilder().properties(
                        AbstractBlock.Properties.of(Material.STONE).strength(1.5F)
                                .sound(SoundType.STONE).noOcclusion()).harvestLevel(ToolType.PICKAXE, 0)
                .tileEntitySupplier(tileEntitySupplier));
    }
}
