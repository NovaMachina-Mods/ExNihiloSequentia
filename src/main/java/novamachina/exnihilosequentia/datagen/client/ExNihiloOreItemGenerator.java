package novamachina.exnihilosequentia.datagen.client;

import java.util.Set;
import javax.annotation.Nonnull;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import novamachina.exnihilosequentia.common.item.ore.Ore;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.datagen.api.datagen.AbstractOreItemGenerator;
import novamachina.exnihilosequentia.world.item.EXNItems;

public class ExNihiloOreItemGenerator extends AbstractOreItemGenerator {

  public ExNihiloOreItemGenerator(
      @Nonnull final DataGenerator generator,
      @Nonnull final ExistingFileHelper existingFileHelper) {
    super(generator, ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, existingFileHelper);
  }

  @Override
  protected void registerModels() {
    Set<Ore> ores =
        Set.of(
            EXNItems.IRON,
            EXNItems.GOLD,
            EXNItems.COPPER,
            EXNItems.LEAD,
            EXNItems.NICKEL,
            EXNItems.SILVER,
            EXNItems.TIN,
            EXNItems.ALUMINUM,
            EXNItems.PLATINUM,
            EXNItems.URANIUM,
            EXNItems.ZINC);
    for (Ore ore : ores) {
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
