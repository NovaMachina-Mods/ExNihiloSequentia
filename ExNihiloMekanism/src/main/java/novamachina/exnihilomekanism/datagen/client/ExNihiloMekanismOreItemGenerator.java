package novamachina.exnihilomekanism.datagen.client;

import javax.annotation.Nullable;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import novamachina.exnihilomekanism.common.init.ExNihiloMekanismItems;
import novamachina.exnihilomekanism.common.utility.ExNihiloMekanismConstants;
import novamachina.exnihilosequentia.datagen.api.AbstractItemGenerator;

public class ExNihiloMekanismOreItemGenerator extends AbstractItemGenerator {

  private static final String ITEM_GENERATED_TAG = "item/generated";
  private static final String LAYER_0_TAG = "layer0";

  public ExNihiloMekanismOreItemGenerator(
      DataGenerator generator, ExistingFileHelper existingFileHelper) {
    super(generator, ExNihiloMekanismConstants.ModIds.EX_NIHILO_MEKANISM, existingFileHelper);
  }

  @Override
  protected void registerModels() {
    @Nullable
    final ResourceLocation resourceLocation =
        ForgeRegistries.ITEMS.getKey(ExNihiloMekanismItems.OSMIUM_PIECES.get());
    if (resourceLocation == null) {
      return;
    }
    singleTexture(
        resourceLocation.getPath(),
        new ResourceLocation(ITEM_GENERATED_TAG),
        LAYER_0_TAG,
        new ResourceLocation(modid, "item/ore/piece/" + resourceLocation.getPath()));
  }
}
