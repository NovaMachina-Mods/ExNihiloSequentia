package com.novamachina.exnihilosequentia.common.tileentity;

import com.novamachina.exnihilosequentia.common.block.InfestingLeavesBlock;
import com.novamachina.exnihilosequentia.common.setup.ModTiles;
import com.novamachina.exnihilosequentia.common.utility.LogUtil;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;

public class InfestedLeavesTile extends TileEntity implements ITickableTileEntity {

    public InfestedLeavesTile() {
        super(ModTiles.INFESTED_LEAVES.get());
    }

    //TODO: Switch to config
    private int progressWaitInterval = 30;

    @Override
    public void tick() {
        if (!world.isRemote()) {
            if (progressWaitInterval <= 0) {
                LogUtil.info("Spreading");
                InfestingLeavesBlock.spread(world, pos);
                progressWaitInterval = 30;
            } else {
                progressWaitInterval--;
            }
        }
    }
}
