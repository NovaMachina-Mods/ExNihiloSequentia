package novamachina.exnihilosequentia.common.block;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.IForgeShearable;
import novamachina.exnihilosequentia.common.blockentity.InfestedLeavesEntity;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;

public class InfestedLeavesBlock extends BaseBlock implements IForgeShearable, EntityBlock {

  public InfestedLeavesBlock() {
    super(
        new BlockBuilder()
            .properties(
                Properties.of(Material.LEAVES)
                    .strength(0.2F)
                    .sound(SoundType.GRASS)
                    .noOcclusion()
                    .isValidSpawn((blockState, blockGetter, blockPos, entityType) -> false)));
  }

  @Override
  public void playerDestroy(
      @Nonnull final Level world,
      @Nonnull final Player player,
      @Nonnull final BlockPos pos,
      @Nonnull final BlockState state,
      @Nullable final BlockEntity tileEntity,
      @Nonnull final ItemStack itemStack) {
    if (itemStack.getItem() instanceof ShearsItem) {
      world.addFreshEntity(
          new ItemEntity(
              world, pos.getX() + 0.5F, pos.getY() + 1.1F, pos.getZ() + 0.5F, new ItemStack(this)));
    }
    super.playerDestroy(world, player, pos, state, tileEntity, itemStack);
  }

  @Nullable
  @Override
  public BlockEntity newBlockEntity(@Nonnull BlockPos pos, @Nonnull BlockState state) {
    return new InfestedLeavesEntity(pos, state);
  }

  @Nullable
  @Override
  public <T extends BlockEntity> BlockEntityTicker<T> getTicker(
      @Nonnull Level level, @Nonnull BlockState state, @Nonnull BlockEntityType<T> type) {
    if (!level.isClientSide) {
      return (level1, blockPos, blockState, t) -> {
        if (t instanceof InfestedLeavesEntity tile) {
          tile.tickServer();
        }
      };
    }
    return null;
  }
}
