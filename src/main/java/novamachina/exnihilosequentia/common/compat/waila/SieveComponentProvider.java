package novamachina.exnihilosequentia.common.compat.waila;

import mcp.mobius.waila.api.BlockAccessor;
import mcp.mobius.waila.api.IComponentProvider;
import mcp.mobius.waila.api.ITooltip;
import mcp.mobius.waila.api.config.IPluginConfig;
import net.minecraft.network.chat.TranslatableComponent;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.item.mesh.EnumMesh;
import novamachina.exnihilosequentia.common.tileentity.SieveTile;
import novamachina.exnihilosequentia.common.utility.StringUtils;

public class SieveComponentProvider implements IComponentProvider {
    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        SieveTile sieveTile = (SieveTile) accessor.getBlockEntity();

        if (!sieveTile.getBlockStack().isEmpty()) {
            tooltip.add(new TranslatableComponent("waila.progress", StringUtils
                    .formatPercent(sieveTile.getProgress())));
            tooltip.add(new TranslatableComponent("waila.sieve.block", new TranslatableComponent(sieveTile.getBlockStack().getDescriptionId())));
        }
        if (sieveTile.getMesh() != EnumMesh.NONE) {
            tooltip.add(new TranslatableComponent("waila.sieve.mesh", new TranslatableComponent("item." + ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + "." + sieveTile.getMesh().getMeshName())));
        }
    }
}
