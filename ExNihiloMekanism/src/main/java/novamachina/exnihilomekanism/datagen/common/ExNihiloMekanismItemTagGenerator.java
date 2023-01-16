package novamachina.exnihilomekanism.datagen.common;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import novamachina.exnihilomekanism.common.init.ExNihiloMekanismItems;
import novamachina.exnihilomekanism.common.utility.ExNihiloMekanismConstants;
import novamachina.exnihilosequentia.api.tag.ExNihiloTags;
import novamachina.exnihilosequentia.datagen.api.AbstractItemTagGenerator;

public class ExNihiloMekanismItemTagGenerator extends AbstractItemTagGenerator {

  public ExNihiloMekanismItemTagGenerator(
      DataGenerator generator,
      BlockTagsProvider blockTagsProvider,
      ExistingFileHelper existingFileHelper) {
    super(
        generator,
        blockTagsProvider,
        ExNihiloMekanismConstants.ModIds.EX_NIHILO_MEKANISM,
        existingFileHelper);
  }

  @Override
  protected void addTags() {
    tag(ExNihiloTags.PIECE).add(ExNihiloMekanismItems.OSMIUM_PIECES.get());
  }
}
