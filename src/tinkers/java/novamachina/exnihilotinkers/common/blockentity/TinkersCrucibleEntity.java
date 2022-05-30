package novamachina.exnihilotinkers.common.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import novamachina.exnihilosequentia.common.blockentity.crucible.WoodCrucibleEntity;
import novamachina.exnihilotinkers.common.init.EXNTinkersBlockEntites;

public class TinkersCrucibleEntity extends WoodCrucibleEntity {

  public TinkersCrucibleEntity(BlockPos pos, BlockState state) {
    super(EXNTinkersBlockEntites.TINKERS_CRUCIBLES.get(), pos, state);
  }
}
