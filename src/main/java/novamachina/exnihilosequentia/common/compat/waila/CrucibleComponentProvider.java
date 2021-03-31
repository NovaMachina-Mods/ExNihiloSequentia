package novamachina.exnihilosequentia.common.compat.waila;

import java.util.List;
import mcp.mobius.waila.api.IComponentProvider;
import mcp.mobius.waila.api.IDataAccessor;
import mcp.mobius.waila.api.IPluginConfig;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import novamachina.exnihilosequentia.common.tileentity.crucible.BaseCrucibleTile;

public class CrucibleComponentProvider implements IComponentProvider {
    @Override
    public void appendBody(List<ITextComponent> tooltip, IDataAccessor accessor, IPluginConfig config) {
        BaseCrucibleTile crucibleTile = (BaseCrucibleTile) accessor.getTileEntity();
        if (crucibleTile.getSolidAmount() > 0) {
            tooltip.add(new TranslationTextComponent("waila.crucible.solid", new TranslationTextComponent(crucibleTile.getCurrentItem().getItem().getDescriptionId()), crucibleTile.getSolidAmount()));
        }
        if (crucibleTile.getFluidAmount() > 0) {
            tooltip.add(new TranslationTextComponent("waila.crucible.fluid", new TranslationTextComponent(crucibleTile.getFluid().defaultFluidState().createLegacyBlock().getBlock().getDescriptionId()), crucibleTile.getFluidAmount()));
        }
        tooltip.add(new TranslationTextComponent("waila.crucible.heat", crucibleTile.getHeat()));
    }
}
