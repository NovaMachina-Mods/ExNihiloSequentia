package novamachina.exnihilosequentia.common.compat.waila;

import java.util.List;
import mcp.mobius.waila.api.IComponentProvider;
import mcp.mobius.waila.api.IDataAccessor;
import mcp.mobius.waila.api.IPluginConfig;
import net.minecraft.util.text.ITextComponent;
import novamachina.exnihilosequentia.common.tileentity.barrel.AbstractBarrelTile;

import javax.annotation.Nonnull;

public class BarrelComponentProvider implements IComponentProvider {
    @Override
    public void appendBody(@Nonnull final List<ITextComponent> tooltip, IDataAccessor accessor,
                           @Nonnull final IPluginConfig config) {
        @Nonnull final AbstractBarrelTile barrelTile = (AbstractBarrelTile) accessor.getTileEntity();
        tooltip.addAll(barrelTile.getWailaInfo());
    }
}
