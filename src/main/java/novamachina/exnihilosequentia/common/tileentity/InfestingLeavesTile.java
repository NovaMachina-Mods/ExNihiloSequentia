package novamachina.exnihilosequentia.common.tileentity;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import novamachina.exnihilosequentia.common.block.InfestingLeavesBlock;
import novamachina.exnihilosequentia.common.init.ExNihiloTiles;
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

public class InfestingLeavesTile extends TileEntity implements ITickableTileEntity {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());
    private static final String PROGRESS_TAG = "progress";

    private int progress = 0;
    private int progressWaitInterval = (Config.getSecondsToTransformLeaves() * 20) / 100;
    private int spreadCounter = 0;

    public InfestingLeavesTile() {
        super(ExNihiloTiles.INFESTING_LEAVES.get());
    }

    public int getProgress() {
        return progress;
    }

    @Override
    public void tick() {
        if (!level.isClientSide()) {
            progressWaitInterval--;
            if (progressWaitInterval <= 0) {
                progress++;
                spreadCounter++;

                if (progress >= 100) {
                    logger.debug("Finish insfesting leaves");
                    InfestingLeavesBlock.finishInfestingBlock(level, worldPosition);
                }

                if (spreadCounter >= Config.getTicksBetweenSpreadAttempt()) {
                    logger.debug("Spreading infested leaves");
                    InfestingLeavesBlock.spread(level, worldPosition);
                    spreadCounter = 0;
                }
                progressWaitInterval = (Config.getSecondsToTransformLeaves() * 20) / 100;
                level.sendBlockUpdated(worldPosition, level.getBlockState(worldPosition), level.getBlockState(worldPosition), 2);
            }
        }
    }

    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putInt(PROGRESS_TAG, progress);
        return new SUpdateTileEntityPacket(getBlockPos(), -1, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        CompoundNBT nbt = pkt.getTag();
        if (nbt.contains(PROGRESS_TAG)) {
            progress = nbt.getInt(PROGRESS_TAG);
        }
    }
}
