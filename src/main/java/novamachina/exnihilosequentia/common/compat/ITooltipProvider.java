package novamachina.exnihilosequentia.common.compat;

import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;

public interface ITooltipProvider {
  List<Component> getTooltipInfo(Level world, BlockPos pos);

  List<Component> getExpandedTooltipInfo(Level world, BlockPos pos);
}
