package novamachina.exnihilosequentia.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.IForgeShearable;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;
import novamachina.exnihilosequentia.common.tileentity.InfestedLeavesTile;

import javax.annotation.Nullable;

public class InfestedLeavesBlock extends BaseBlock implements IForgeShearable, EntityBlock {

    public InfestedLeavesBlock() {
        super(new BlockBuilder().properties(
                Block.Properties.of(Material.LEAVES).strength(0.2F).sound(
                        SoundType.GRASS).noOcclusion().isValidSpawn(BaseBlock::never)));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new InfestedLeavesTile(blockPos, blockState);
    }
}
