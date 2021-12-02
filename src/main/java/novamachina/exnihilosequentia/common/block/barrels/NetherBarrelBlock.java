package novamachina.exnihilosequentia.common.block.barrels;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ToolType;
import novamachina.exnihilosequentia.common.block.BlockBarrel;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;
import novamachina.exnihilosequentia.common.tileentity.barrel.StoneBarrelTile;
import novamachina.exnihilosequentia.common.utility.Config;

import java.util.function.Supplier;

public class NetherBarrelBlock extends BlockBarrel {
    public NetherBarrelBlock() {
        this(StoneBarrelTile::new);
    }

    public NetherBarrelBlock(Supplier<TileEntity> tileEntitySupplier) {
        super(new BlockBuilder().harvestLevel(ToolType.AXE, 0)
                .properties(AbstractBlock.Properties.of(Material.NETHER_WOOD).strength(1.0F).sound(Config.getNetherBarrelSoundsEnabled() ? SoundType.STEM : SoundType.WOOD))
                .tileEntitySupplier(tileEntitySupplier));
    }
}
