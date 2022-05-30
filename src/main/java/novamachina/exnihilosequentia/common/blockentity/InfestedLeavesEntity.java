package novamachina.exnihilosequentia.common.blockentity;

import javax.annotation.Nonnull;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import novamachina.exnihilosequentia.common.block.InfestingLeavesBlock;
import novamachina.exnihilosequentia.common.init.ExNihiloBlockEntities;
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

public class InfestedLeavesEntity extends BlockEntity {

  @Nonnull
  private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

  private int progressWaitInterval = 0;

  public InfestedLeavesEntity(BlockPos pos, BlockState state) {
    super(ExNihiloBlockEntities.INFESTED_LEAVES_ENTITY.get(), pos, state);
  }

  public void tickServer() {
    progressWaitInterval++;
    if (progressWaitInterval >= Config.getTicksBetweenSpreadAttempt()) {
      logger.debug("Spreading infested leaves");

      progressWaitInterval = 0;
      InfestingLeavesBlock.spread(level, worldPosition);
    }
  }
}
