package novamachina.exnihilosequentia.common.tileentity;

import novamachina.exnihilosequentia.common.block.InfestingLeavesBlock;
import novamachina.exnihilosequentia.common.init.ModTiles;
import novamachina.exnihilosequentia.common.utility.Config;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;

public class InfestedLeavesTile extends TileEntity implements ITickableTileEntity {

    private int progressWaitInterval = 0;

    public InfestedLeavesTile() {
        super(ModTiles.INFESTED_LEAVES.get());
    }

    @Override
    public void tick() {
        if (!world.isRemote()) {
            progressWaitInterval++;
            if (progressWaitInterval >= Config.TICKS_BETWEEN_SPREAD_ATTEMPT.get()) {
                progressWaitInterval = 0;
                InfestingLeavesBlock.spread(world, pos);
            }
        }
    }
}
