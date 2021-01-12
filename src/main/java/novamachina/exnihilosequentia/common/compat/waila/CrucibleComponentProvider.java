package novamachina.exnihilosequentia.common.compat.waila;

import novamachina.exnihilosequentia.common.tileentity.crucible.BaseCrucibleTile;
import mcp.mobius.waila.api.IComponentProvider;
import mcp.mobius.waila.api.IDataAccessor;
import mcp.mobius.waila.api.IPluginConfig;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.List;

public class CrucibleComponentProvider implements IComponentProvider {
    @Override
    public void appendBody(List<ITextComponent> tooltip, IDataAccessor accessor, IPluginConfig config) {
        BaseCrucibleTile crucibleTile = (BaseCrucibleTile) accessor.getTileEntity();
        if (crucibleTile.getSolidAmount() > 0) {
            tooltip.add(new TranslationTextComponent("waila.crucible.solid", new TranslationTextComponent(crucibleTile.getCurrentItem().getItem().getTranslationKey()), crucibleTile.getSolidAmount()));
        }
        if (crucibleTile.getFluidAmount() > 0) {
            tooltip.add(new TranslationTextComponent("waila.crucible.fluid", new TranslationTextComponent(crucibleTile.getFluid().getDefaultState().getBlockState().getBlock().getTranslationKey()), crucibleTile.getFluidAmount()));
        }
        tooltip.add(new TranslationTextComponent("waila.crucible.heat", crucibleTile.getHeat()));
    }
}
