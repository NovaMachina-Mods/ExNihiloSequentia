package novamachina.exnihilosequentia.common.block.barrels;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.ToolType;
import novamachina.exnihilosequentia.common.block.BlockBarrel;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;
import novamachina.exnihilosequentia.common.tileentity.barrel.StoneBarrelTile;

import javax.annotation.Nullable;

public class NetherBarrelBlock extends BlockBarrel {
    public NetherBarrelBlock() {
        super(new BlockBuilder().harvestLevel(ToolType.AXE, 0)
                .properties(BlockBehaviour.Properties.of(Material.NETHER_WOOD).strength(1.0F).sound(SoundType.STEM)));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new StoneBarrelTile(pos, state);
    }
}
