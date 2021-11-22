package novamachina.exnihilosequentia.common.compat.waila;

import mcp.mobius.waila.api.IRegistrar;
import mcp.mobius.waila.api.IWailaPlugin;
import mcp.mobius.waila.api.TooltipPosition;
import mcp.mobius.waila.api.WailaPlugin;
import novamachina.exnihilosequentia.common.block.BlockBarrel;
import novamachina.exnihilosequentia.common.block.BlockSieve;
import novamachina.exnihilosequentia.common.block.InfestingLeavesBlock;
import novamachina.exnihilosequentia.common.block.crucibles.FiredCrucibleBlock;
import novamachina.exnihilosequentia.common.block.crucibles.NetherCrucibleBlock;
import novamachina.exnihilosequentia.common.block.crucibles.WoodCrucibleBlock;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

@WailaPlugin(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA)
public class PluginWaila implements IWailaPlugin {
    @Override
    public void register(IRegistrar registrar) {
        registrar.registerComponentProvider(new BarrelComponentProvider(), TooltipPosition.BODY, BlockBarrel.class);
        registrar.registerComponentProvider(new SieveComponentProvider(), TooltipPosition.BODY, BlockSieve.class);
        registrar.registerComponentProvider(new InfestingLeavesComponentProvider(), TooltipPosition.BODY, InfestingLeavesBlock.class);
        registrar.registerComponentProvider(new CrucibleComponentProvider(), TooltipPosition.BODY, WoodCrucibleBlock.class);
        registrar.registerComponentProvider(new CrucibleComponentProvider(), TooltipPosition.BODY, FiredCrucibleBlock.class);
        registrar.registerComponentProvider(new CrucibleComponentProvider(), TooltipPosition.BODY, NetherCrucibleBlock.class);
    }
}
