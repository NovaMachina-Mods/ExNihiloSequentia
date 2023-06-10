package novamachina.exnihilosequentia.datagen.client;

import javax.annotation.Nonnull;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.datagen.api.datagen.AbstractItemGenerator;
import novamachina.exnihilosequentia.world.item.EXNItems;
import novamachina.novacore.world.item.ItemDefinition;

public class ExNihiloItemGenerator extends AbstractItemGenerator {

  private static final String ITEMS_TAG = "item/";
  private static final String ITEM_HANDHELD_TAG = "item/handheld";
  private static final String ITEM_GENERATED_TAG = "item/generated";
  private static final String LAYER_0_TAG = "layer0";

  public ExNihiloItemGenerator(
      @Nonnull final DataGenerator generator,
      @Nonnull final ExistingFileHelper existingFileHelper) {
    super(generator, ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, existingFileHelper);
  }

  @Override
  protected void registerModels() {
    registerTools();
    registerOther();
  }

  private void registerTools() {
    EXNItems.getDefinitions().stream()
        .filter(def -> def.getType() == ItemDefinition.ItemType.TOOL)
        .forEach(
            definition ->
                singleTexture(
                    definition.getId().getPath(),
                    new ResourceLocation(ITEM_HANDHELD_TAG),
                    LAYER_0_TAG,
                    new ResourceLocation(modid, ITEMS_TAG + definition.getId().getPath())));
  }

  private void registerOther() {

    EXNItems.getDefinitions().stream()
        .filter(def -> def.getType() == ItemDefinition.ItemType.OTHER)
        .forEach(
            definition -> {
              if (definition.equals(EXNItems.BLAZE_DOLL)) {
                singleTexture(
                    definition.getId().getPath(),
                    new ResourceLocation("exnihilosequentia:item/overlap_gui"),
                    LAYER_0_TAG,
                    new ResourceLocation(modid, ITEMS_TAG + definition.getId().getPath()));
              } else {
                singleTexture(
                    definition.getId().getPath(),
                    new ResourceLocation(ITEM_GENERATED_TAG),
                    LAYER_0_TAG,
                    new ResourceLocation(modid, ITEMS_TAG + definition.getId().getPath()));
              }
            });
  }
}
