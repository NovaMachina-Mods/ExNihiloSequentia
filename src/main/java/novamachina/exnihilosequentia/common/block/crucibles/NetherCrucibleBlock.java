package novamachina.exnihilosequentia.common.block.crucibles;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ToolType;
import novamachina.exnihilosequentia.common.block.CrucibleBaseBlock;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;
import novamachina.exnihilosequentia.common.tileentity.crucible.BaseCrucibleTile;
import novamachina.exnihilosequentia.common.tileentity.crucible.FiredCrucibleTile;
import novamachina.exnihilosequentia.common.utility.Config;

import java.util.function.Supplier;

public class NetherCrucibleBlock extends CrucibleBaseBlock {
    public NetherCrucibleBlock() {
        this(FiredCrucibleTile::new);
    }
    public NetherCrucibleBlock(Supplier<TileEntity> tileEntitySupplier) {
        super(new BlockBuilder().properties(
                        AbstractBlock.Properties.of(Material.NETHER_WOOD).strength(1.0F)
                                .sound(Config.getNetherCrucibleSoundsEnabled() ? SoundType.STEM : SoundType.WOOD).noOcclusion()).harvestLevel(ToolType.AXE, 0)
                .tileEntitySupplier(tileEntitySupplier));
    }

}
