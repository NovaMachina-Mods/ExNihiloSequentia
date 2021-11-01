package novamachina.exnihilosequentia.common.tileentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import novamachina.exnihilosequentia.api.utility.Config;
import novamachina.exnihilosequentia.common.block.InfestingLeavesBlock;
import novamachina.exnihilosequentia.common.init.ExNihiloTiles;
import novamachina.exnihilosequentia.api.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

public class InfestedLeavesTile extends BlockEntity {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    private int progressWaitInterval = 0;

    public InfestedLeavesTile(BlockPos pos, BlockState state) {
        super(ExNihiloTiles.INFESTED_LEAVES.get(), pos, state);
    }

    public void tick() {
        assert level != null;
        if (!level.isClientSide()) {
            progressWaitInterval++;
            if (progressWaitInterval >= Config.getTicksBetweenSpreadAttempt()) {
                logger.debug("Spreading infested leaves");

                progressWaitInterval = 0;
                InfestingLeavesBlock.spread(level, worldPosition);
            }
        }
    }
}
