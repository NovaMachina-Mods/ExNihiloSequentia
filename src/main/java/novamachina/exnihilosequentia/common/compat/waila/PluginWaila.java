package novamachina.exnihilosequentia.common.compat.waila;

import novamachina.exnihilosequentia.common.block.BlockBarrel;
import novamachina.exnihilosequentia.common.block.BlockSieve;
import novamachina.exnihilosequentia.common.block.FiredCrucibleBlock;
import novamachina.exnihilosequentia.common.block.InfestingLeavesBlock;
import novamachina.exnihilosequentia.common.block.WoodCrucibleBlock;
import novamachina.exnihilosequentia.common.utility.Constants;
import mcp.mobius.waila.api.IRegistrar;
import mcp.mobius.waila.api.IWailaPlugin;
import mcp.mobius.waila.api.TooltipPosition;
import mcp.mobius.waila.api.WailaPlugin;

@WailaPlugin(Constants.ModIds.EX_NIHILO_SEQUENTIA)
public class PluginWaila implements IWailaPlugin {
    @Override
    public void register(IRegistrar registrar) {
        registrar.registerComponentProvider(new BarrelComponentProvider(), TooltipPosition.BODY, BlockBarrel.class);
        registrar.registerComponentProvider(new SieveComponentProvider(), TooltipPosition.BODY, BlockSieve.class);
        registrar
            .registerComponentProvider(new InfestingLeavesComponentProvider(), TooltipPosition.BODY, InfestingLeavesBlock.class);
        registrar
            .registerComponentProvider(new CrucibleComponentProvider(), TooltipPosition.BODY, WoodCrucibleBlock.class);
        registrar
            .registerComponentProvider(new CrucibleComponentProvider(), TooltipPosition.BODY, FiredCrucibleBlock.class);
    }
}
