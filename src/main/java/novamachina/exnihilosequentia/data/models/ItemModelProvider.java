package novamachina.exnihilosequentia.data.models;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import novamachina.exnihilosequentia.ExNihiloSequentia;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.world.item.EXNItems;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;
import novamachina.novacore.world.item.ItemDefinition;

public class ItemModelProvider
    extends net.minecraftforge.client.model.generators.ItemModelProvider {

  private static final String ITEMS_TAG = "item/";
  private static final String ITEM_HANDHELD_TAG = "item/handheld";
  private static final String ITEM_GENERATED_TAG = "item/generated";
  private static final String LAYER_0_TAG = "layer0";

  public ItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
    super(output, ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, existingFileHelper);
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
            definition -> {
              if (definition.equals(EXNItems.CROOK_WOOD)) {
                ItemModelBuilder halloween =
                    singleTexture(
                        "halloween_crook",
                        new ResourceLocation(ITEM_GENERATED_TAG),
                        LAYER_0_TAG,
                        new ResourceLocation(modid, ITEMS_TAG + "halloween_crook"));
                ItemModelBuilder christmas =
                    singleTexture(
                        "christmas_crook",
                        new ResourceLocation(ITEM_GENERATED_TAG),
                        LAYER_0_TAG,
                        new ResourceLocation(modid, ITEMS_TAG + "christmas_crook"));

                singleTexture(
                        definition.getId().getPath(),
                        new ResourceLocation(ITEM_GENERATED_TAG),
                        LAYER_0_TAG,
                        new ResourceLocation(modid, ITEMS_TAG + definition.getId().getPath()))
                    .override()
                    .model(halloween)
                    .predicate(new ResourceLocation(ExNihiloSequentia.MOD_ID, "holiday"), 0.10F)
                    .end()
                    .override()
                    .model(christmas)
                    .predicate(new ResourceLocation(ExNihiloSequentia.MOD_ID, "holiday"), 0.12F)
                    .end();
              } else {
                singleTexture(
                    definition.getId().getPath(),
                    new ResourceLocation(ITEM_HANDHELD_TAG),
                    LAYER_0_TAG,
                    new ResourceLocation(modid, ITEMS_TAG + definition.getId().getPath()));
              }
            });
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
    singleTexture(
        EXNBlocks.END_CAKE.getId().getPath(),
        new ResourceLocation(ITEM_GENERATED_TAG),
        LAYER_0_TAG,
        new ResourceLocation(modid, ITEMS_TAG + EXNBlocks.END_CAKE.getId().getPath()));
  }
}
