package novamachina.exnihilosequentia.common.compat.waila;

import javax.annotation.Nonnull;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import novamachina.exnihilosequentia.common.blockentity.crucible.BaseCrucibleEntity;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants.ModIds;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

 public class CrucibleComponentProvider implements IBlockComponentProvider {

  @Override
  public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig iPluginConfig)
 {
    @Nonnull final BaseCrucibleEntity crucibleTile = (BaseCrucibleEntity)
 accessor.getBlockEntity();
    if (crucibleTile.getSolidAmount() > 0) {
      tooltip.add(Component.translatable("waila.crucible.solid",
          Component.translatable(crucibleTile.getCurrentItem().getItem().getDescriptionId()),
          crucibleTile.getSolidAmount()));
    }
    if (crucibleTile.getFluidAmount() > 0 && crucibleTile.getFluid() != null) {
      tooltip.add(Component.translatable("waila.crucible.fluid", Component.translatable(
          crucibleTile.getFluid().defaultFluidState().createLegacyBlock().getBlock()
              .getDescriptionId()), crucibleTile.getFluidAmount()));
    }
    if (crucibleTile.getHeat() == 0) {
      tooltip.add(Component.translatable("waila.crucible.no_heat"));
    } else {
      tooltip.add(Component.translatable("waila.crucible.heat", crucibleTile.getHeat()));
    }
  }

   @Override
   public ResourceLocation getUid() {
     return new ResourceLocation(ModIds.EX_NIHILO_SEQUENTIA, "crucible");
   }
 }
