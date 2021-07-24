package novamachina.exnihilosequentia.common.block.crucibles;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.ToolType;
import novamachina.exnihilosequentia.common.block.CrucibleBaseBlock;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;
import novamachina.exnihilosequentia.common.tileentity.crucible.FiredCrucibleTile;

import javax.annotation.Nullable;

public class FiredCrucibleBlock extends FiredCruciblesBlocks {

    public FiredCrucibleBlock() {
        super(new BlockBuilder().properties(
                Block.Properties.of(Material.STONE).strength(1.5F)
                        .sound(SoundType.STONE).noOcclusion()).harvestLevel(ToolType.PICKAXE, 0));
    }
}
