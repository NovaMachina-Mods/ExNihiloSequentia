package novamachina.exnihilosequentia.common.tileentity;

import novamachina.exnihilosequentia.common.block.InfestingLeavesBlock;
import novamachina.exnihilosequentia.common.init.ExNihiloTiles;
import novamachina.exnihilosequentia.common.utility.Config;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

public class InfestedLeavesTile extends TileEntity implements ITickableTileEntity {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    private int progressWaitInterval = 0;

    public InfestedLeavesTile() {
        super(ExNihiloTiles.INFESTED_LEAVES.get());
    }

    @Override
    public void tick() {
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
