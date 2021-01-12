package novamachina.exnihilosequentia.common.compat.waila;

import novamachina.exnihilosequentia.common.item.mesh.EnumMesh;
import novamachina.exnihilosequentia.common.tileentity.SieveTile;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.utility.StringUtils;
import mcp.mobius.waila.api.IComponentProvider;
import mcp.mobius.waila.api.IDataAccessor;
import mcp.mobius.waila.api.IPluginConfig;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.List;

public class SieveComponentProvider implements IComponentProvider {
    @Override
    public void appendBody(List<ITextComponent> tooltip, IDataAccessor accessor, IPluginConfig config) {
        SieveTile sieveTile = (SieveTile) accessor.getTileEntity();

        if (!sieveTile.getBlockStack().isEmpty()) {
            tooltip.add(new TranslationTextComponent("waila.progress", StringUtils
                    .formatPercent(sieveTile.getProgress() / 1.0F)));
            tooltip.add(new TranslationTextComponent("waila.sieve.block", new TranslationTextComponent(sieveTile.getBlockStack().getTranslationKey())));
        }
        if (sieveTile.getMesh() != EnumMesh.NONE) {
            tooltip.add(new TranslationTextComponent("waila.sieve.mesh", new TranslationTextComponent("item." + ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + "." + sieveTile.getMesh().getMeshName())));
        }
    }
}
