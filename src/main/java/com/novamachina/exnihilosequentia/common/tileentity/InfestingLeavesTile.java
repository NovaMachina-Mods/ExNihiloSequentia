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
    private int progressWaitInterval = (Config.SECONDS_TO_TRANSFORM_LEAVES.get() * 20) / 100;
    private int spreadCounter        = 0;

    @Override
    public void tick() {
        if (!world.isRemote()) {
            progressWaitInterval--;
            if (progressWaitInterval <= 0){
                progress++;
                spreadCounter++;

                if (progress >= 100) {
                    InfestingLeavesBlock.finishInfestingBlock(world, pos);
                }

                if (spreadCounter >= Config.TICKS_BETWEEN_SPREAD_ATTEMPT.get()) {
                    InfestingLeavesBlock.spread(world, pos);
                    spreadCounter = 0;
                }
                progressWaitInterval = (Config.SECONDS_TO_TRANSFORM_LEAVES.get() * 20) / 100;
            }
        }
    }
}
