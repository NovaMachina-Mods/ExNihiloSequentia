package novamachina.exnihilosequentia.world.item;

import javax.annotation.Nonnull;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class ResourceItem extends Item {

  private final Block triggerBlock;
  private final BlockState replaceBlock;

  public ResourceItem(Block triggerBlock, Block replaceBlock, Item.Properties properties) {
    super(properties);
    this.triggerBlock = triggerBlock;
    this.replaceBlock = replaceBlock == null ? null : replaceBlock.defaultBlockState();
  }

  @Override
  @NotNull
  public InteractionResult useOn(@Nonnull final UseOnContext context) {
    if (this.getTriggerBlock() == null || this.getReplaceBlock() == null) {
      return InteractionResult.PASS;
    }
    BlockState state = context.getLevel().getBlockState(context.getClickedPos());
    if (state.getBlock().equals(getTriggerBlock())) {
      if (!context.getPlayer().isCreative()) {
        context.getItemInHand().shrink(1);
      }
      Block.updateOrDestroy(
          state, getReplaceBlock(), context.getLevel(), context.getClickedPos(), 1);
      return InteractionResult.SUCCESS;
    }
    return InteractionResult.FAIL;
  }

  private Block getTriggerBlock() {
    return this.triggerBlock;
  }

  private BlockState getReplaceBlock() {
    return this.replaceBlock;
  }

  @FunctionalInterface
  public interface ResourceItemFunction {
    ResourceItem apply(Block tiggerBlock, Block replaceBlock, Item.Properties properties);
  }
}
