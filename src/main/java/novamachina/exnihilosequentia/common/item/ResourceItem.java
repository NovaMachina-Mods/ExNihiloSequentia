package novamachina.exnihilosequentia.common.item;

import javax.annotation.Nonnull;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import novamachina.exnihilosequentia.init.ExNihiloInitialization;
import org.jetbrains.annotations.NotNull;

public class ResourceItem extends Item {

  @Nonnull private final String resourceName;
  private Block tiggerBlock;
  private BlockState replaceBlock;

  public ResourceItem(@Nonnull final String name, Block tiggerBlock, Block replaceBlock) {
    super(new Item.Properties().tab(ExNihiloInitialization.ITEM_GROUP));
    this.resourceName = name;
    this.tiggerBlock = tiggerBlock;
    this.replaceBlock = replaceBlock == null ? null : replaceBlock.defaultBlockState();
  }

  public ResourceItem(@Nonnull final String name) {
    super(new Item.Properties().tab(ExNihiloInitialization.ITEM_GROUP));
    this.resourceName = name;
    this.tiggerBlock = null;
    this.replaceBlock = null;
  }

  @Nonnull
  public String getResourceName() {
    return resourceName;
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
    return this.tiggerBlock;
  }

  private BlockState getReplaceBlock() {
    return this.replaceBlock;
  }
}
