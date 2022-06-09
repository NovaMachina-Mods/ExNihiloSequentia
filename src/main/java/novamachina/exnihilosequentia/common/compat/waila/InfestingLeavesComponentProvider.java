package novamachina.exnihilosequentia.common.compat.waila;

import javax.annotation.Nonnull;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import novamachina.exnihilosequentia.common.blockentity.InfestingLeavesEntity;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants.ModIds;
import novamachina.exnihilosequentia.common.utility.StringUtils;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

 public class InfestingLeavesComponentProvider implements IBlockComponentProvider {

  @Override
  public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig iPluginConfig)
 {
    @Nonnull final InfestingLeavesEntity infestingLeavesEntity = (InfestingLeavesEntity)
 accessor.getBlockEntity();

    tooltip.add(Component.translatable("waila.progress", StringUtils
        .formatPercent((float) infestingLeavesEntity.getProgress() / 100)));
  }

  @Override
  public ResourceLocation getUid() {
   return new ResourceLocation(ModIds.EX_NIHILO_SEQUENTIA, "infesting_leaves");
  }
 }
