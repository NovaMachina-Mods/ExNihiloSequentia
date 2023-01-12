package novamachina.exnihiloae.common;

import net.minecraft.data.DataGenerator;
import novamachina.exnihiloae.common.init.ExNihiloAEBlocks;
import novamachina.exnihiloae.common.utility.ExNihiloAEConstants;
import novamachina.exnihilosequentia.api.datagen.AbstractLootTableGenerator;

public class ExNihiloAELootTables extends AbstractLootTableGenerator {

  public ExNihiloAELootTables(DataGenerator generator) {
    super(generator, ExNihiloAEConstants.ModIds.EX_NIHILO_AE);
  }

  @Override
  protected void createLootTables() {
    registerSelfDrop(ExNihiloAEBlocks.CRUSHED_SKYSTONE.get());
  }
}
