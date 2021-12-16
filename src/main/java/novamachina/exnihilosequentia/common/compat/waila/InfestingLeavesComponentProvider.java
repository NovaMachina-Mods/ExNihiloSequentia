package novamachina.exnihilosequentia.common.compat.waila;

import java.util.List;
import mcp.mobius.waila.api.IComponentProvider;
import mcp.mobius.waila.api.IDataAccessor;
import mcp.mobius.waila.api.IPluginConfig;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import novamachina.exnihilosequentia.common.tileentity.InfestingLeavesTile;
import novamachina.exnihilosequentia.common.utility.StringUtils;

import javax.annotation.Nonnull;

public class InfestingLeavesComponentProvider implements IComponentProvider {
    @Override
    public void appendBody(@Nonnull final List<ITextComponent> tooltip, @Nonnull final IDataAccessor accessor,
                           @Nonnull final IPluginConfig config) {
        @Nonnull final InfestingLeavesTile infestingLeavesTile = (InfestingLeavesTile) accessor.getTileEntity();

        tooltip.add(new TranslationTextComponent("waila.progress", StringUtils
                .formatPercent((float) infestingLeavesTile.getProgress() / 100)));
    }
}
