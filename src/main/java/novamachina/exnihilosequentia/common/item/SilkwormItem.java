package novamachina.exnihilosequentia.common.item;

import javax.annotation.Nonnull;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import novamachina.exnihilosequentia.common.block.InfestingLeavesBlock;
import novamachina.exnihilosequentia.init.ExNihiloInitialization;

public class SilkwormItem extends Item {

  public SilkwormItem() {
    super(new Item.Properties().tab(ExNihiloInitialization.ITEM_GROUP));
  }

  @Override
  @Nonnull
  public InteractionResult useOn(@Nonnull final UseOnContext context) {
    BlockState state = context.getLevel().getBlockState(context.getClickedPos());
    if (state.getBlock() instanceof LeavesBlock) {
      if (!context.getPlayer().isCreative()) {
        context.getItemInHand().shrink(1);
      }
      InfestingLeavesBlock.normalToInfesting(context.getLevel(),
          context.getClickedPos());
      return InteractionResult.SUCCESS;
    }
    return InteractionResult.FAIL;
  }
}
