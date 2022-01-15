package novamachina.exnihilosequentia.common.compat.waila;

import java.util.List;
import mcp.mobius.waila.api.IComponentProvider;
import mcp.mobius.waila.api.IDataAccessor;
import mcp.mobius.waila.api.IPluginConfig;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import novamachina.exnihilosequentia.common.tileentity.crucible.BaseCrucibleTile;

import javax.annotation.Nonnull;

public class CrucibleComponentProvider implements IComponentProvider {
    @Override
    public void appendBody(@Nonnull final List<ITextComponent> tooltip, @Nonnull final IDataAccessor accessor,
                           @Nonnull final IPluginConfig config) {
        @Nonnull final BaseCrucibleTile crucibleTile = (BaseCrucibleTile) accessor.getTileEntity();
        if (crucibleTile.getSolidAmount() > 0 && crucibleTile.getCurrentItem() != null) {
            tooltip.add(new TranslationTextComponent("waila.crucible.solid", new TranslationTextComponent(crucibleTile.getCurrentItem().getItem().getDescriptionId()), crucibleTile.getSolidAmount()));
        }
        if (crucibleTile.getFluidAmount() > 0 && crucibleTile.getFluid() != null) {
            tooltip.add(new TranslationTextComponent("waila.crucible.fluid", new TranslationTextComponent(crucibleTile.getFluid().defaultFluidState().createLegacyBlock().getBlock().getDescriptionId()), crucibleTile.getFluidAmount()));
        }
        if (crucibleTile.getHeat() == 0) {
            tooltip.add(new TranslationTextComponent("waila.crucible.no_heat"));
        } else {
            tooltip.add(new TranslationTextComponent("waila.crucible.heat", crucibleTile.getHeat()));
        }
    }
}
