package novamachina.exnihilosequentia.common.compat.waila;

import java.util.List;

import mcp.mobius.waila.api.BlockAccessor;
import mcp.mobius.waila.api.IComponentProvider;
import mcp.mobius.waila.api.ITooltip;
import mcp.mobius.waila.api.config.IPluginConfig;
import novamachina.exnihilosequentia.common.tileentity.barrel.AbstractBarrelTile;

public class BarrelComponentProvider implements IComponentProvider {

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig iPluginConfig) {
        AbstractBarrelTile barrelTile = (AbstractBarrelTile) accessor.getBlockEntity();
        tooltip.addAll(barrelTile.getWailaInfo());
    }
}
