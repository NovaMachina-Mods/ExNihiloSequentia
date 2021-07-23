package novamachina.exnihilosequentia.common.block.barrels;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.ToolType;
import novamachina.exnihilosequentia.common.block.BlockBarrel;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;
import novamachina.exnihilosequentia.common.tileentity.barrel.WoodBarrelTile;

public class WoodBarrelBlock extends BlockBarrel {
    public WoodBarrelBlock() {
        super(new BlockBuilder().harvestLevel(ToolType.AXE, 0).properties(
                Block.Properties.of(Material.WOOD).strength(0.75F).sound(SoundType.WOOD))
                .tileEntitySupplier(WoodBarrelTile::new));
    }
}
