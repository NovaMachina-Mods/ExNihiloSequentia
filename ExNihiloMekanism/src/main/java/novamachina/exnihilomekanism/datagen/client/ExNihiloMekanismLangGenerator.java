package novamachina.exnihilomekanism.datagen.client;

import net.minecraft.data.DataGenerator;
import novamachina.exnihilomekanism.common.utility.ExNihiloMekanismConstants.ModIds;
import novamachina.exnihilosequentia.datagen.api.AbstractLangGenerator;

public class ExNihiloMekanismLangGenerator extends AbstractLangGenerator {

  public ExNihiloMekanismLangGenerator(DataGenerator gen, String locale) {
    super(gen, ModIds.EX_NIHILO_MEKANISM, locale);
  }

  @Override
  protected void addTranslations() {
    // Items
    addItem();
  }

  private void addItem() {
    addPieceAutoName("osmium");
  }
}