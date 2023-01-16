package novamachina.exnihilothermal.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import novamachina.exnihilothermal.common.utility.ExNihiloThermalConstants;
import novamachina.exnihilothermal.datagen.client.ExNihiloThermalItemGenerator;
import novamachina.exnihilothermal.datagen.client.ExNihiloThermalLangGenerator;
import novamachina.exnihilothermal.datagen.common.ExNihiloThermalRecipeGenerator;
import novamachina.exnihilothermal.datagen.common.ExNihiloThermalTagGenerator;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ExNihiloThermalDataGenerators {

  @SubscribeEvent
  public static void gatherData(GatherDataEvent event) {
    DataGenerator generator = event.getGenerator();
    if (event.includeServer()) {
      generator.addProvider(true, new ExNihiloThermalRecipeGenerator(generator));
      generator.addProvider(
          true,
          new ExNihiloThermalTagGenerator(
              generator,
              new BlockTagsProvider(
                  generator,
                  ExNihiloThermalConstants.ModIds.EX_NIHILO_THERMAL,
                  event.getExistingFileHelper()),
              event.getExistingFileHelper()));
    }
    if (event.includeClient()) {
      // Items
      generator.addProvider(
          true,
          new ExNihiloThermalItemGenerator(
              generator,
              ExNihiloThermalConstants.ModIds.EX_NIHILO_THERMAL,
              event.getExistingFileHelper()));
      // Lang
      generator.addProvider(true, new ExNihiloThermalLangGenerator(generator, "en_us"));
    }
  }
}
