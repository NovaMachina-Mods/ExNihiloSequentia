package novamachina.exnihilosequentia.common.compat.waila;

import javax.annotation.Nonnull;
import novamachina.exnihilosequentia.common.block.BlockBarrel;
import novamachina.exnihilosequentia.common.block.BlockSieve;
import novamachina.exnihilosequentia.common.block.CrucibleBaseBlock;
import novamachina.exnihilosequentia.common.block.InfestingLeavesBlock;
import novamachina.exnihilosequentia.common.blockentity.InfestingLeavesEntity;
import novamachina.exnihilosequentia.common.blockentity.SieveEntity;
import novamachina.exnihilosequentia.common.blockentity.barrel.AbstractBarrelEntity;
import novamachina.exnihilosequentia.common.blockentity.crucible.BaseCrucibleEntity;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaCommonRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;

@WailaPlugin(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA)
public class PluginWaila implements IWailaPlugin {

  @Override
  public void register(@Nonnull final IWailaCommonRegistration registrar) {
    registrar.registerBlockDataProvider(new BarrelComponentProvider(), AbstractBarrelEntity.class);
    registrar.registerBlockDataProvider(new CrucibleComponentProvider(), BaseCrucibleEntity.class);
    registrar.registerBlockDataProvider(new SieveComponentProvider(), SieveEntity.class);
    registrar.registerBlockDataProvider(
        new InfestingLeavesComponentProvider(), InfestingLeavesEntity.class);
  }

  @Override
  public void registerClient(IWailaClientRegistration registration) {
    registration.registerBlockComponent(new BarrelComponentProvider(), BlockBarrel.class);
    registration.registerBlockComponent(new CrucibleComponentProvider(), CrucibleBaseBlock.class);
    registration.registerBlockComponent(new SieveComponentProvider(), BlockSieve.class);
    registration.registerBlockComponent(
        new InfestingLeavesComponentProvider(), InfestingLeavesBlock.class);
  }
}
