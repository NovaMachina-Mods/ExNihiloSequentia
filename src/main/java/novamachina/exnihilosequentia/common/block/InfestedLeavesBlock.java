package novamachina.exnihilosequentia.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.IForgeShearable;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;
import novamachina.exnihilosequentia.common.tileentity.InfestedLeavesTile;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class InfestedLeavesBlock extends BaseBlock implements IForgeShearable {

    public InfestedLeavesBlock() {
        super(new BlockBuilder().properties(
                BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).sound(
                        SoundType.GRASS).noOcclusion().isValidSpawn(BaseBlock::never)));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@Nonnull BlockPos pos, @Nonnull BlockState state) {
        return new InfestedLeavesTile(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level1, @Nonnull BlockState state, @Nonnull BlockEntityType<T> type) {
        if (level1.isClientSide) {
            return null;
        }
        return (level, blockPos, blockState, t) -> {
            if (t instanceof InfestedLeavesTile tile) {
                tile.tick();
            }
        };
    }

    @Override
    public void playerDestroy(Level world, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack itemStack) {
        if (itemStack.getItem() instanceof ShearsItem) {
            world.addFreshEntity(new ItemEntity(world, pos.getX() + 0.5F, pos.getY() + 1.1F,
                    pos.getZ() + 0.5F, new ItemStack(this)));
        }
        super.playerDestroy(world, player, pos, state, blockEntity, itemStack);
    }
}
