package novamachina.exnihilothermal.common;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import novamachina.exnihilosequentia.api.datagen.AbstractItemTagGenerator;
import novamachina.exnihilothermal.api.ExNihiloThermalTags;
import novamachina.exnihilothermal.common.init.ExNihiloThermalItems;
import novamachina.exnihilothermal.common.utility.ExNihiloThermalConstants;

public class ExNihiloThermalTagGenerator extends AbstractItemTagGenerator {

  public ExNihiloThermalTagGenerator(DataGenerator generator, BlockTagsProvider blockTagsProvider,
      ExistingFileHelper existingFileHelper) {
    super(generator, blockTagsProvider, ExNihiloThermalConstants.ModIds.EX_NIHILO_THERMAL,
        existingFileHelper);
  }

  @Override
  protected void addTags() {
    tag(ExNihiloThermalTags.DUST_OBSIDIAN).add(ExNihiloThermalItems.DUST_OBSIDIAN.get());
  }
}
