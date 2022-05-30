package novamachina.exnihilotinkers.client;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import novamachina.exnihilosequentia.api.datagen.AbstractOreItemGenerator;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilotinkers.common.init.EXNTinkersItems;

public class EXNTinkersOreItems extends AbstractOreItemGenerator {

  public EXNTinkersOreItems(DataGenerator generator, ExistingFileHelper existingFileHelper) {
    super(generator, ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, existingFileHelper);
  }

  @Override
  protected void registerModels() {
//    registerPiece(EXNTinkersItems.COBALT);
  }
}
