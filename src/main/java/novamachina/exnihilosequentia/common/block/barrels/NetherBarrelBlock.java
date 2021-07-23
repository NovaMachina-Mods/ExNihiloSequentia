package novamachina.exnihilosequentia.common.block.barrels;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.ToolType;
import novamachina.exnihilosequentia.common.block.BlockBarrel;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;
import novamachina.exnihilosequentia.common.tileentity.barrel.StoneBarrelTile;

public class NetherBarrelBlock extends BlockBarrel {
    public NetherBarrelBlock() {
        super(new BlockBuilder().harvestLevel(ToolType.AXE, 0).properties(
                Block.Properties.of(Material.NETHER_WOOD).strength(1.0F).sound(SoundType.STEM))
                .tileEntitySupplier(StoneBarrelTile::new));
    }
}
