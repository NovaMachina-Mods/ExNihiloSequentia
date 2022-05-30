package novamachina.exnihilothermal.client;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import novamachina.exnihilosequentia.api.datagen.AbstractItemGenerator;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import novamachina.exnihilothermal.common.init.ExNihiloThermalItems;
import novamachina.exnihilothermal.common.utility.ExNihiloThermalConstants;
import org.apache.logging.log4j.LogManager;

public class ExNihiloThermalItemGenerator extends AbstractItemGenerator {

  private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

  private static final String ITEMS_TAG = "items/";
  private static final String ITEM_HANDHELD_TAG = "item/handheld";
  private static final String LAYER_0_TAG = "layer0";

  public ExNihiloThermalItemGenerator(DataGenerator generator, String modId,
      ExistingFileHelper existingFileHelper) {
    super(generator, modId, existingFileHelper);
  }

  @Override
  protected void registerModels() {
//    singleTexture(ExNihiloThermalItems.BASALZ_DOLL.get().getRegistryName().toString(),
//        new ResourceLocation(ITEM_HANDHELD_TAG), LAYER_0_TAG,
//        new ResourceLocation(ExNihiloThermalConstants.ModIds.EX_NIHILO_THERMAL,
//            ITEMS_TAG + ExNihiloThermalItems.BASALZ_DOLL.get().getDollName()));
//
//    singleTexture(ExNihiloThermalItems.BLITZ_DOLL.get().getRegistryName().toString(),
//        new ResourceLocation(ITEM_HANDHELD_TAG), LAYER_0_TAG,
//        new ResourceLocation(ExNihiloThermalConstants.ModIds.EX_NIHILO_THERMAL,
//            ITEMS_TAG + ExNihiloThermalItems.BLITZ_DOLL.get().getDollName()));
//
//    singleTexture(ExNihiloThermalItems.BLIZZ_DOLL.get().getRegistryName().toString(),
//        new ResourceLocation(ITEM_HANDHELD_TAG), LAYER_0_TAG,
//        new ResourceLocation(ExNihiloThermalConstants.ModIds.EX_NIHILO_THERMAL,
//            ITEMS_TAG + ExNihiloThermalItems.BLIZZ_DOLL.get().getDollName()));
//
//    singleTexture((ExNihiloThermalItems.DUST_OBSIDIAN.get()).getRegistryName().getPath(),
//        new ResourceLocation("item/handheld"), "layer0",
//        new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA,
//            "items/" + ExNihiloThermalItems.DUST_OBSIDIAN.get().getResourceName()));
  }
}
