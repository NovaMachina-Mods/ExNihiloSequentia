package com.novamachina.exnihilosequentia.common.waila;

import com.novamachina.exnihilosequentia.common.tileentity.crucible.BaseCrucibleTile;
import mcp.mobius.waila.api.IComponentProvider;
import mcp.mobius.waila.api.IDataAccessor;
import mcp.mobius.waila.api.IEntityComponentProvider;
import mcp.mobius.waila.api.IPluginConfig;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.List;

public class CrucibleComponentProvider implements IComponentProvider {
    @Override
    public void appendBody(List<ITextComponent> tooltip, IDataAccessor accessor, IPluginConfig config) {
        BaseCrucibleTile crucibleTile = (BaseCrucibleTile) accessor.getTileEntity();
        if(crucibleTile.getFluidAmount() > 0) {
            String fluidName = I18n.format(crucibleTile.getFluid().getDefaultState().getBlockState().getBlock().getTranslationKey());
            tooltip.add(new TranslationTextComponent("waila.crucible.fluid", fluidName, crucibleTile.getFluidAmount()));
        }
        if(crucibleTile.getSolidAmount() > 0) {
            tooltip.add(new TranslationTextComponent("waila.crucible.solid", crucibleTile.getSolidAmount()));
        }
    }
}
