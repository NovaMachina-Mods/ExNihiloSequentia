package novamachina.exnihilosequentia.common.compat.waila;
//
//import javax.annotation.Nonnull;
//import mcp.mobius.waila.api.BlockAccessor;
//import mcp.mobius.waila.api.IComponentProvider;
//import mcp.mobius.waila.api.ITooltip;
//import mcp.mobius.waila.api.config.IPluginConfig;
//import net.minecraft.network.chat.TranslatableComponent;
//import novamachina.exnihilosequentia.common.blockentity.InfestingLeavesEntity;
//import novamachina.exnihilosequentia.common.utility.StringUtils;
//
//public class InfestingLeavesComponentProvider implements IComponentProvider {
//
//  @Override
//  public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig iPluginConfig) {
//    @Nonnull final InfestingLeavesEntity infestingLeavesEntity = (InfestingLeavesEntity) accessor.getBlockEntity();
//
//    tooltip.add(new TranslatableComponent("waila.progress", StringUtils
//        .formatPercent((float) infestingLeavesEntity.getProgress() / 100)));
//  }
//}
