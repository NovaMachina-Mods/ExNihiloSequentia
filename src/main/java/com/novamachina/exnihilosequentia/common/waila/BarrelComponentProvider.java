package com.novamachina.exnihilosequentia.common.waila;

import com.novamachina.exnihilosequentia.common.tileentity.barrel.BarrelTile;
import mcp.mobius.waila.api.IComponentProvider;
import mcp.mobius.waila.api.IDataAccessor;
import mcp.mobius.waila.api.IPluginConfig;
import net.minecraft.util.text.ITextComponent;

import java.util.List;

public class BarrelComponentProvider implements IComponentProvider {
    @Override
    public void appendBody(List<ITextComponent> tooltip, IDataAccessor accessor, IPluginConfig config) {
        BarrelTile barrelTile = (BarrelTile) accessor.getTileEntity();
        tooltip.addAll(barrelTile.getWailaInfo());
    }
}
