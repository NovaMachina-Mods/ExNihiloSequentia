package novamachina.exnihilosequentia.common.compat.waila;

import mcp.mobius.waila.api.BlockAccessor;
import mcp.mobius.waila.api.IComponentProvider;
import mcp.mobius.waila.api.ITooltip;
import mcp.mobius.waila.api.config.IPluginConfig;
import novamachina.exnihilosequentia.common.tileentity.barrel.AbstractBarrelTile;

import javax.annotation.Nonnull;

public class BarrelComponentProvider implements IComponentProvider {

    @Override
    public void appendTooltip(ITooltip iTooltip, BlockAccessor blockAccessor, IPluginConfig iPluginConfig) {
        @Nonnull final AbstractBarrelTile barrelTile = (AbstractBarrelTile) blockAccessor.getBlockEntity();
        iTooltip.addAll(barrelTile.getWailaInfo());
    }
}
