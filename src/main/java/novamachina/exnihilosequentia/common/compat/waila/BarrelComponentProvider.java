package novamachina.exnihilosequentia.common.compat.waila;

import java.util.List;
import mcp.mobius.waila.api.IComponentProvider;
import mcp.mobius.waila.api.IDataAccessor;
import mcp.mobius.waila.api.IPluginConfig;
import net.minecraft.util.text.ITextComponent;
import novamachina.exnihilosequentia.common.tileentity.barrel.AbstractBarrelTile;

public class BarrelComponentProvider implements IComponentProvider {
    @Override
    public void appendBody(List<ITextComponent> tooltip, IDataAccessor accessor, IPluginConfig config) {
        AbstractBarrelTile barrelTile = (AbstractBarrelTile) accessor.getTileEntity();
        tooltip.addAll(barrelTile.getWailaInfo());
    }
}
