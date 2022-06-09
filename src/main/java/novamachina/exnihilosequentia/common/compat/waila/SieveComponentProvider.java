package novamachina.exnihilosequentia.common.compat.waila;

import javax.annotation.Nonnull;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import novamachina.exnihilosequentia.common.blockentity.SieveEntity;
import novamachina.exnihilosequentia.common.item.mesh.MeshType;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants.ModIds;
import novamachina.exnihilosequentia.common.utility.StringUtils;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

 public class SieveComponentProvider implements IBlockComponentProvider {

  @Override
  public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig iPluginConfig)
 {
    @Nonnull final SieveEntity sieveEntity = (SieveEntity) accessor.getBlockEntity();

    if (!sieveEntity.getBlockStack().isEmpty()) {
      tooltip.add(Component.translatable("waila.progress", StringUtils
          .formatPercent(sieveEntity.getProgress())));
      tooltip.add(Component.translatable("waila.sieve.block",
          Component.translatable(sieveEntity.getBlockStack().getDescriptionId())));
    }
    if (sieveEntity.getMeshType() != MeshType.NONE) {
      tooltip.add(Component.translatable("waila.sieve.mesh", Component.translatable(
          "item." + ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + "." + sieveEntity.getMeshType()
              .getMeshName())));
    }
  }

   @Override
   public ResourceLocation getUid() {
     return new ResourceLocation(ModIds.EX_NIHILO_SEQUENTIA, "sieve");
   }
 }
