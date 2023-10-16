package novamachina.exnihilosequentia.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import novamachina.exnihilosequentia.common.Config;
import novamachina.exnihilosequentia.world.level.block.InfestingLeavesBlock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InfestedLeavesBlockEntity extends BlockEntity {

  private static Logger log = LoggerFactory.getLogger(InfestingLeavesBlockEntity.class);

  private int progressWaitInterval = 0;

  public InfestedLeavesBlockEntity(
      BlockEntityType<? extends InfestedLeavesBlockEntity> blockEntityType,
      BlockPos pos,
      BlockState state) {
    super(blockEntityType, pos, state);
  }

  public void tickServer() {
    progressWaitInterval++;
    if (progressWaitInterval >= Config.getTicksBetweenSpreadAttempt()) {
      log.debug("Spreading infested leaves");

      progressWaitInterval = 0;
      InfestingLeavesBlock.spread(level, worldPosition);
    }
  }
}
