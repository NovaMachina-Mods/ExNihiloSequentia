package novamachina.exnihilotinkers.client;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import novamachina.exnihilosequentia.api.datagen.AbstractOreItemGenerator;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilotinkers.common.init.EXNTinkersItems;
import novamachina.exnihilotinkers.common.utility.EXNTinkersConstants;

public class EXNTinkersOreItems extends AbstractOreItemGenerator {

  public EXNTinkersOreItems(DataGenerator generator, ExistingFileHelper existingFileHelper) {
    super(generator, EXNTinkersConstants.ModIds.EX_NIHILO_TINKERS, existingFileHelper);
  }

  @Override
  protected void registerModels() {
    registerPiece(EXNTinkersItems.COBALT);
  }
}
