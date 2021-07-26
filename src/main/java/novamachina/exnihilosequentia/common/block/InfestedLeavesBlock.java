package novamachina.exnihilosequentia.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.IForgeShearable;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;
import novamachina.exnihilosequentia.common.tileentity.InfestedLeavesTile;

import javax.annotation.Nullable;

public class InfestedLeavesBlock extends BaseBlock implements IForgeShearable {

    public InfestedLeavesBlock() {
        super(new BlockBuilder().properties(
                BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).sound(
                        SoundType.GRASS).noOcclusion().isValidSpawn(BaseBlock::never)));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new InfestedLeavesTile(pos, state);
    }
}
