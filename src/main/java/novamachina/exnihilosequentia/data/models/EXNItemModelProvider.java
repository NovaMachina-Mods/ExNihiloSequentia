package novamachina.exnihilosequentia.data.models;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import novamachina.exnihilosequentia.ExNihiloSequentia;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.world.item.EXNItems;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;
import novamachina.novacore.world.item.ItemDefinition;

public class EXNItemModelProvider extends ItemModelProvider {

  private static final String ITEMS_TAG = "item/";
  private static final String ITEM_HANDHELD_TAG = "item/handheld";
  private static final String ITEM_GENERATED_TAG = "item/generated";
  private static final String LAYER_0_TAG = "layer0";

  public EXNItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
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
                        ResourceLocation.parse(ITEM_GENERATED_TAG),
                        LAYER_0_TAG,
                        ResourceLocation.fromNamespaceAndPath(modid, ITEMS_TAG + "halloween_crook"));
                ItemModelBuilder christmas =
                    singleTexture(
                        "christmas_crook",
                        ResourceLocation.parse(ITEM_GENERATED_TAG),
                        LAYER_0_TAG,
                        ResourceLocation.fromNamespaceAndPath(modid, ITEMS_TAG + "christmas_crook"));

                singleTexture(
                        definition.getId().getPath(),
                    ResourceLocation.parse(ITEM_GENERATED_TAG),
                        LAYER_0_TAG,
                    ResourceLocation.fromNamespaceAndPath(modid, ITEMS_TAG + definition.getId().getPath()));
              } else {
                singleTexture(
                    definition.getId().getPath(),
                    ResourceLocation.parse(ITEM_HANDHELD_TAG),
                    LAYER_0_TAG,
                    ResourceLocation.fromNamespaceAndPath(modid, ITEMS_TAG + definition.getId().getPath()));
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
                    ResourceLocation.parse("exnihilosequentia:item/overlap_gui"),
                    LAYER_0_TAG,
                    ResourceLocation.fromNamespaceAndPath(modid, ITEMS_TAG + definition.getId().getPath()));
              } else {
                singleTexture(
                    definition.getId().getPath(),
                    ResourceLocation.parse(ITEM_GENERATED_TAG),
                    LAYER_0_TAG,
                    ResourceLocation.fromNamespaceAndPath(modid, ITEMS_TAG + definition.getId().getPath()));
              }
            });
    singleTexture(
        EXNBlocks.END_CAKE.getId().getPath(),
        ResourceLocation.parse(ITEM_GENERATED_TAG),
        LAYER_0_TAG,
        ResourceLocation.fromNamespaceAndPath(modid, ITEMS_TAG + EXNBlocks.END_CAKE.getId().getPath()));
  }
}
