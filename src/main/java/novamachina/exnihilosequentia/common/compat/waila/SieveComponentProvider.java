package novamachina.exnihilosequentia.common.compat.waila;

import novamachina.exnihilosequentia.common.item.mesh.EnumMesh;
import novamachina.exnihilosequentia.common.tileentity.sieve.SieveTile;
import novamachina.exnihilosequentia.common.utility.Constants;
import novamachina.exnihilosequentia.common.utility.StringUtils;
import mcp.mobius.waila.api.IComponentProvider;
import mcp.mobius.waila.api.IDataAccessor;
import mcp.mobius.waila.api.IPluginConfig;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.List;

public class SieveComponentProvider implements IComponentProvider {
    @Override
    public void appendBody(List<ITextComponent> tooltip, IDataAccessor accessor, IPluginConfig config) {
        SieveTile sieveTile = (SieveTile) accessor.getTileEntity();
        String block = I18n.format(sieveTile.getBlockStack().getTranslationKey());

        if (!sieveTile.getBlockStack().isEmpty()) {
            tooltip.add(new TranslationTextComponent("waila.progress", StringUtils
                .formatPercent(sieveTile.getProgress() / 1.0F)));
            tooltip.add(new TranslationTextComponent("waila.sieve.block", block));
        }
        if (sieveTile.getMesh() != EnumMesh.NONE) {
            String meshName = I18n.format("item." + Constants.ModIds.EX_NIHILO_SEQUENTIA + "." + sieveTile.getMesh().getMeshName());
            if(meshName.equals(sieveTile.getMesh().getMeshName()))
                meshName = sieveTile.getMesh().getName();
            tooltip.add(new TranslationTextComponent("waila.sieve.mesh", meshName));
        }
    }
}
