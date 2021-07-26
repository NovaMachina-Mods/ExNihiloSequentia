package novamachina.exnihilosequentia.common.block.crucibles;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.ToolType;
import novamachina.exnihilosequentia.common.block.CrucibleBaseBlock;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;
import novamachina.exnihilosequentia.common.tileentity.barrel.StoneBarrelTile;
import novamachina.exnihilosequentia.common.tileentity.crucible.FiredCrucibleTile;

import javax.annotation.Nullable;

public class FiredCrucibleBlock extends CrucibleBaseBlock {

    public FiredCrucibleBlock() {
        super(new BlockBuilder().properties(
                BlockBehaviour.Properties.of(Material.STONE).strength(1.5F)
                        .sound(SoundType.STONE).noOcclusion()).harvestLevel(ToolType.PICKAXE, 0));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new FiredCrucibleTile(pos, state);
    }
}
