package novamachina.exnihilosequentia.datagen.client;

import javax.annotation.Nonnull;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import novamachina.exnihilosequentia.common.init.ExNihiloItems;
import novamachina.exnihilosequentia.common.item.ore.Ore;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.datagen.api.datagen.AbstractOreItemGenerator;

public class ExNihiloOreItemGenerator extends AbstractOreItemGenerator {

  public ExNihiloOreItemGenerator(
      @Nonnull final DataGenerator generator,
      @Nonnull final ExistingFileHelper existingFileHelper) {
    super(generator, ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, existingFileHelper);
  }

  @Override
  protected void registerModels() {
    for (Ore ore : ExNihiloItems.ORES) {
      registerOre(ore);
    }
  }

  private void registerOre(Ore ore) {
    String oreName = ore.getOreName();
    if (!(oreName.equals("iron") || oreName.equals("gold") || oreName.equals("copper"))) {
      registerRaw(ore);
      registerIngot(ore);
    }
    if (!(oreName.equals("iron") || oreName.equals("gold"))) {
      registerNugget(ore);
    }
    registerPiece(ore);
  }
}
