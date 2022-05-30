package novamachina.exnihilosequentia.common.blockentity;

import javax.annotation.Nonnull;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import novamachina.exnihilosequentia.common.block.InfestingLeavesBlock;
import novamachina.exnihilosequentia.common.init.ExNihiloBlockEntities;
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

public class InfestingLeavesEntity extends BlockEntity {

  @Nonnull
  private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());
  @Nonnull
  private static final String PROGRESS_TAG = "progress";

  private int progress = 0;
  private int progressWaitInterval = (Config.getSecondsToTransformLeaves() * 20) / 100;
  private int spreadCounter = 0;

  public InfestingLeavesEntity(BlockPos pos, BlockState state) {
    super(ExNihiloBlockEntities.INFESTING_LEAVES_ENTITY.get(), pos, state);
  }

  public int getProgress() {
    return progress;
  }

  public void tickServer() {
    if (level == null || level.isClientSide) {
      return;
    }
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
      level.sendBlockUpdated(worldPosition, level.getBlockState(worldPosition),
          level.getBlockState(worldPosition), 2);
    }
  }

  @Override
  @Nonnull
  public ClientboundBlockEntityDataPacket getUpdatePacket() {
    return ClientboundBlockEntityDataPacket.create(this);
  }

  @Override
  public CompoundTag getUpdateTag() {
    @Nonnull final CompoundTag nbt = new CompoundTag();
    nbt.putInt(PROGRESS_TAG, progress);
    return nbt;
  }

  @Override
  public void onDataPacket(@Nonnull final Connection net,
      @Nonnull final ClientboundBlockEntityDataPacket pkt) {
    @Nonnull final CompoundTag nbt = pkt.getTag();
    if (nbt.contains(PROGRESS_TAG)) {
      progress = nbt.getInt(PROGRESS_TAG);
    }
  }
}
