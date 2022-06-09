package novamachina.exnihilothermal.client;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import novamachina.exnihilosequentia.api.datagen.AbstractItemGenerator;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import novamachina.exnihilothermal.common.init.ExNihiloThermalItems;
import novamachina.exnihilothermal.common.utility.ExNihiloThermalConstants;
import org.apache.logging.log4j.LogManager;

public class ExNihiloThermalItemGenerator extends AbstractItemGenerator {

  private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

  private static final String ITEMS_TAG = "items/";
  private static final String ITEM_GENERATED = "item/generated";
  private static final String LAYER_0_TAG = "layer0";

  public ExNihiloThermalItemGenerator(
      DataGenerator generator, String modId, ExistingFileHelper existingFileHelper) {
    super(generator, modId, existingFileHelper);
  }

  @Override
  protected void registerModels() {
    singleTexture(
        ForgeRegistries.ITEMS.getKey(ExNihiloThermalItems.BASALZ_DOLL.get()).toString(),
        new ResourceLocation(ITEM_GENERATED),
        LAYER_0_TAG,
        new ResourceLocation(
            ExNihiloThermalConstants.ModIds.EX_NIHILO_THERMAL,
            ITEMS_TAG + ExNihiloThermalItems.BASALZ_DOLL.get().getDollName()));

    singleTexture(
        ForgeRegistries.ITEMS.getKey(ExNihiloThermalItems.BLITZ_DOLL.get()).toString(),
        new ResourceLocation(ITEM_GENERATED),
        LAYER_0_TAG,
        new ResourceLocation(
            ExNihiloThermalConstants.ModIds.EX_NIHILO_THERMAL,
            ITEMS_TAG + ExNihiloThermalItems.BLITZ_DOLL.get().getDollName()));

    singleTexture(
        ForgeRegistries.ITEMS.getKey(ExNihiloThermalItems.BLIZZ_DOLL.get()).toString(),
        new ResourceLocation(ITEM_GENERATED),
        LAYER_0_TAG,
        new ResourceLocation(
            ExNihiloThermalConstants.ModIds.EX_NIHILO_THERMAL,
            ITEMS_TAG + ExNihiloThermalItems.BLIZZ_DOLL.get().getDollName()));

    //    singleTexture((ExNihiloThermalItems.DUST_OBSIDIAN.get()).getRegistryName().getPath(),
    //        new ResourceLocation(ITEM_GENERATED), "layer0",
    //        new ResourceLocation(ExNihiloThermalConstants.ModIds.EX_NIHILO_THERMAL,
    //            "items/" + ExNihiloThermalItems.DUST_OBSIDIAN.get().getResourceName()));
  }
}
