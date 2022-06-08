package novamachina.exnihilosequentia.common.compat.waila;
//
//import javax.annotation.Nonnull;
//import mcp.mobius.waila.api.BlockAccessor;
//import mcp.mobius.waila.api.IComponentProvider;
//import mcp.mobius.waila.api.ITooltip;
//import mcp.mobius.waila.api.config.IPluginConfig;
//import net.minecraft.network.chat.TranslatableComponent;
//import novamachina.exnihilosequentia.common.blockentity.SieveEntity;
//import novamachina.exnihilosequentia.common.item.mesh.MeshType;
//import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
//import novamachina.exnihilosequentia.common.utility.StringUtils;
//
//public class SieveComponentProvider implements IComponentProvider {
//
//  @Override
//  public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig iPluginConfig) {
//    @Nonnull final SieveEntity sieveEntity = (SieveEntity) accessor.getBlockEntity();
//
//    if (!sieveEntity.getBlockStack().isEmpty()) {
//      tooltip.add(new TranslatableComponent("waila.progress", StringUtils
//          .formatPercent(sieveEntity.getProgress())));
//      tooltip.add(new TranslatableComponent("waila.sieve.block",
//          new TranslatableComponent(sieveEntity.getBlockStack().getDescriptionId())));
//    }
//    if (sieveEntity.getMeshType() != MeshType.NONE) {
//      tooltip.add(new TranslatableComponent("waila.sieve.mesh", new TranslatableComponent(
//          "item." + ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + "." + sieveEntity.getMeshType()
//              .getMeshName())));
//    }
//  }
//}
