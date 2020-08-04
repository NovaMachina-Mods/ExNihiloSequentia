package com.novamachina.exnihilosequentia.common.tileentity;

import com.novamachina.exnihilosequentia.common.block.InfestingLeavesBlock;
import com.novamachina.exnihilosequentia.common.setup.ModTiles;
import com.novamachina.exnihilosequentia.common.utility.Config;
import com.novamachina.exnihilosequentia.common.utility.LogUtil;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;

public class InfestingLeavesTile extends TileEntity implements ITickableTileEntity {

    public InfestingLeavesTile() {
        super(ModTiles.INFESTING_LEAVES.get());
    }

    private int progress             = 0;
    private int progressWaitInterval = Config.TICKS_TO_TRANSFORM_LEAVES.get() / 100;
    private int spreadCounter        = 0;

    @Override
    public void tick() {
        if (!world.isRemote()) {
            if (progressWaitInterval <= 0) {
                progress++;
                spreadCounter++;

                if (progress >= 100) {
                    LogUtil.info("Finished Transform");
                    InfestingLeavesBlock.finishInfestingBlock(world, pos);
                }

                if (spreadCounter >= Config.SPREAD_UPDATE_FREQUENCEY.get()) {
                    LogUtil.info("Spreading");
                    InfestingLeavesBlock.spread(world, pos);
                    spreadCounter = 0;
                }
                progressWaitInterval = Config.TICKS_TO_TRANSFORM_LEAVES.get() / 100;
            } else {
                progressWaitInterval--;
            }
        }
    }
}
