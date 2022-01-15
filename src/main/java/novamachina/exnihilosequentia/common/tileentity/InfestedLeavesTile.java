package novamachina.exnihilosequentia.common.tileentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import novamachina.exnihilosequentia.common.block.InfestingLeavesBlock;
import novamachina.exnihilosequentia.common.init.ExNihiloTiles;
import novamachina.exnihilosequentia.common.utility.Config;
import net.minecraft.world.level.block.entity.BlockEntity;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

import javax.annotation.Nonnull;

public class InfestedLeavesTile extends BlockEntity {
    @Nonnull private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    private int progressWaitInterval = 0;

    public InfestedLeavesTile(BlockPos pos, BlockState state) {
        super(ExNihiloTiles.INFESTED_LEAVES.get(), pos, state);
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
