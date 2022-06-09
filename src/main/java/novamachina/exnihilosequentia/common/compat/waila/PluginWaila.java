package novamachina.exnihilosequentia.common.compat.waila;

 import javax.annotation.Nonnull;
 import net.minecraft.world.level.block.BarrelBlock;
 import novamachina.exnihilosequentia.common.blockentity.barrel.AbstractBarrelEntity;
 import snownee.jade.api.IWailaClientRegistration;
 import snownee.jade.api.IWailaCommonRegistration;
 import snownee.jade.api.IWailaPlugin;
 import snownee.jade.api.TooltipPosition;
 import snownee.jade.api.WailaPlugin;
 import novamachina.exnihilosequentia.common.block.BlockBarrel;
 import novamachina.exnihilosequentia.common.block.BlockSieve;
 import novamachina.exnihilosequentia.common.block.InfestingLeavesBlock;
 import novamachina.exnihilosequentia.common.block.crucibles.FiredCrucibleBlock;
 import novamachina.exnihilosequentia.common.block.crucibles.NetherCrucibleBlock;
 import novamachina.exnihilosequentia.common.block.crucibles.WoodCrucibleBlock;
 import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

 @WailaPlugin(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA)
 public class PluginWaila implements IWailaPlugin {

  @Override
  public void register(@Nonnull final IWailaCommonRegistration registrar) {
    registrar.registerBlockDataProvider(new BarrelComponentProvider(), AbstractBarrelEntity.class);
//    registrar.registerBlockDataProvider(new SieveComponentProvider(), TooltipPosition.BODY,
//        BlockSieve.class);
//    registrar.registerBlockDataProvider(new InfestingLeavesComponentProvider(),
//        TooltipPosition.BODY, InfestingLeavesBlock.class);
//    registrar.registerBlockDataProvider(new CrucibleComponentProvider(), TooltipPosition.BODY,
//        WoodCrucibleBlock.class);
//    registrar.registerBlockDataProvider(new CrucibleComponentProvider(), TooltipPosition.BODY,
//        FiredCrucibleBlock.class);
//    registrar.registerBlockDataProvider(new CrucibleComponentProvider(), TooltipPosition.BODY,
//        NetherCrucibleBlock.class);
  }

  @Override
  public void registerClient(IWailaClientRegistration registration) {
   registration.registerBlockComponent(new BarrelComponentProvider(), BarrelBlock.class);
  }
 }
