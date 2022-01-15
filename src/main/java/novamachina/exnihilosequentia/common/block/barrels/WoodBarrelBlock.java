package novamachina.exnihilosequentia.common.block.barrels;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ToolType;
import novamachina.exnihilosequentia.common.block.BlockBarrel;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;
import novamachina.exnihilosequentia.common.tileentity.barrel.WoodBarrelTile;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public class WoodBarrelBlock extends BlockBarrel {
    public WoodBarrelBlock() {
        this(WoodBarrelTile::new);
    }

    public WoodBarrelBlock(@Nonnull final Supplier<TileEntity> tileEntitySupplier) {
        super(new BlockBuilder().harvestLevel(ToolType.AXE, 0)
                .properties(AbstractBlock.Properties.of(Material.WOOD).strength(0.75F).sound(SoundType.WOOD))
                .tileEntitySupplier(tileEntitySupplier));
    }
}
