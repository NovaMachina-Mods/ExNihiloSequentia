package novamachina.exnihilosequentia.common.compat.waila;

import mcp.mobius.waila.api.BlockAccessor;
import mcp.mobius.waila.api.IComponentProvider;
import mcp.mobius.waila.api.ITooltip;
import mcp.mobius.waila.api.config.IPluginConfig;
import net.minecraft.network.chat.TranslatableComponent;
import novamachina.exnihilosequentia.common.tileentity.crucible.BaseCrucibleTile;

public class CrucibleComponentProvider implements IComponentProvider {
    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        BaseCrucibleTile crucibleTile = (BaseCrucibleTile) accessor.getBlockEntity();
        if (crucibleTile.getSolidAmount() > 0) {
            tooltip.add(new TranslatableComponent("waila.crucible.solid", new TranslatableComponent(crucibleTile.getCurrentItem().getItem().getDescriptionId()), crucibleTile.getSolidAmount()));
        }
        if (crucibleTile.getFluidAmount() > 0) {
            tooltip.add(new TranslatableComponent("waila.crucible.fluid", new TranslatableComponent(crucibleTile.getFluid().defaultFluidState().createLegacyBlock().getBlock().getDescriptionId()), crucibleTile.getFluidAmount()));
        }
        tooltip.add(new TranslatableComponent("waila.crucible.heat", crucibleTile.getHeat()));
    }
}
