package novamachina.exnihilomekanism.client;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import novamachina.exnihilomekanism.common.init.ExNihiloMekanismItems;
import novamachina.exnihilomekanism.common.utility.ExNihiloMekanismConstants;
import novamachina.exnihilosequentia.api.datagen.AbstractOreItemGenerator;

public class ExNihiloMekanismOreItemGenerator extends AbstractOreItemGenerator {

  public ExNihiloMekanismOreItemGenerator(DataGenerator generator,
      ExistingFileHelper existingFileHelper) {
    super(generator, ExNihiloMekanismConstants.ModIds.EX_NIHILO_MEKANISM, existingFileHelper);
  }

  @Override
  protected void registerModels() {
    registerRaw(ExNihiloMekanismItems.OSMIUM);
//        registerPiece(ExNihiloMekanismItems.OSMIUM);
  }
}
