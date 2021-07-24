package novamachina.exnihilosequentia.common.block.barrels;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.ToolType;
import novamachina.exnihilosequentia.common.block.BlockBarrel;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;
import novamachina.exnihilosequentia.common.tileentity.barrel.WoodBarrelTile;

import javax.annotation.Nullable;

public class WoodBarrelBlock extends BlockBarrel implements EntityBlock {
    public WoodBarrelBlock() {
        super(new BlockBuilder().harvestLevel(ToolType.AXE, 0).properties(
                Block.Properties.of(Material.WOOD).strength(0.75F).sound(SoundType.WOOD)));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new WoodBarrelTile(blockPos, blockState);
    }
}
