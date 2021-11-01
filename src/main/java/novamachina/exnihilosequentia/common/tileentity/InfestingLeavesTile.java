package novamachina.exnihilosequentia.common.tileentity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import novamachina.exnihilosequentia.api.utility.Config;
import novamachina.exnihilosequentia.common.block.InfestingLeavesBlock;
import novamachina.exnihilosequentia.common.init.ExNihiloTiles;
import novamachina.exnihilosequentia.api.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

public class InfestingLeavesTile extends BlockEntity {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());
    private static final String PROGRESS_TAG = "progress";

    private int progress = 0;
    private int progressWaitInterval = (Config.getSecondsToTransformLeaves() * 20) / 100;
    private int spreadCounter = 0;

    public InfestingLeavesTile(BlockPos pos, BlockState state) {
        super(ExNihiloTiles.INFESTING_LEAVES.get(), pos, state);
    }

    public int getProgress() {
        return progress;
    }

    public void tick() {
        assert level != null;
        if (!level.isClientSide()) {
            progressWaitInterval--;
            if (progressWaitInterval <= 0) {
                progress++;
                spreadCounter++;

                if (progress >= 100) {
                    logger.debug("Finish infesting leaves");
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
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        CompoundTag nbt = new CompoundTag();
        nbt.putInt(PROGRESS_TAG, progress);
        return new ClientboundBlockEntityDataPacket(getBlockPos(), -1, nbt);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        CompoundTag nbt = pkt.getTag();
        if (nbt.contains(PROGRESS_TAG)) {
            progress = nbt.getInt(PROGRESS_TAG);
        }
    }
}
