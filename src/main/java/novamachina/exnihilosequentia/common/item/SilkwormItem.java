package novamachina.exnihilosequentia.common.item;

import javax.annotation.Nonnull;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import novamachina.exnihilosequentia.common.block.InfestingLeavesBlock;
import org.jetbrains.annotations.NotNull;

public class SilkwormItem extends ResourceItem {

  public SilkwormItem(@NotNull String name) {
    super(name);
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
