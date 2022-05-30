package novamachina.exnihilosequentia.client;

import javax.annotation.Nonnull;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import novamachina.exnihilosequentia.api.datagen.AbstractOreItemGenerator;
import novamachina.exnihilosequentia.common.init.ExNihiloItems;
import novamachina.exnihilosequentia.common.item.ore.Ore;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

public class ExNihiloOreItemGenerator extends AbstractOreItemGenerator {

  public ExNihiloOreItemGenerator(
      @Nonnull final DataGenerator generator,
      @Nonnull final ExistingFileHelper existingFileHelper) {
    super(generator, ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, existingFileHelper);
  }

  @Override
  protected void registerModels() {
    registerOre(ExNihiloItems.IRON);
    registerOre(ExNihiloItems.GOLD);
    registerOre(ExNihiloItems.COPPER);
    registerOre(ExNihiloItems.LEAD);
    registerOre(ExNihiloItems.NICKEL);
    registerOre(ExNihiloItems.SILVER);
    registerOre(ExNihiloItems.TIN);
    registerOre(ExNihiloItems.ALUMINUM);
    registerOre(ExNihiloItems.PLATINUM);
    registerOre(ExNihiloItems.URANIUM);
    registerOre(ExNihiloItems.ZINC);
  }

  private void registerOre(Ore ore) {
    String oreName = ore.getOreName();
    if (!(oreName.equals("iron") || oreName.equals("gold") || oreName.equals("copper"))) {
      registerRaw(ore);
    }
    registerPiece(ore);
    if (ore.getIngotItem() != null) {
      registerIngot(ore);
    }
  }
}
