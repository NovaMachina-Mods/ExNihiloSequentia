package novamachina.exnihilosequentia.common.block.barrels;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.ToolActions;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;
import novamachina.exnihilosequentia.common.tileentity.barrel.StoneBarrelTile;
import novamachina.exnihilosequentia.common.tileentity.barrel.WoodBarrelTile;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class WoodBarrelBlock extends AbstractBarrelBlock {
    public WoodBarrelBlock() {
        super(new BlockBuilder().harvestLevel(ToolActions.AXE_DIG)
                .properties(BlockBehaviour.Properties.of(Material.WOOD).strength(0.75F).sound(SoundType.WOOD)));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@Nonnull BlockPos pos, @Nonnull BlockState state) {
        return new WoodBarrelTile(pos, state);
    }
}
