package novamachina.exnihilothermal.datagen.client;

import com.mojang.logging.LogUtils;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import novamachina.exnihilosequentia.datagen.api.AbstractItemGenerator;
import novamachina.exnihilothermal.common.init.ExNihiloThermalItems;
import novamachina.exnihilothermal.common.utility.ExNihiloThermalConstants;

public class ExNihiloThermalItemGenerator extends AbstractItemGenerator {

  private static final ExNihiloLogger logger = new ExNihiloLogger(LogUtils.getLogger());

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

    singleTexture(
        ForgeRegistries.ITEMS.getKey(ExNihiloThermalItems.DUST_OBSIDIAN.get()).getPath(),
        new ResourceLocation(ITEM_GENERATED),
        "layer0",
        new ResourceLocation(
            ExNihiloThermalConstants.ModIds.EX_NIHILO_THERMAL,
            "items/" + ExNihiloThermalItems.DUST_OBSIDIAN.get().getResourceName()));
  }
}
