package novamachina.exnihilomekanism.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import novamachina.exnihilomekanism.common.utility.ExNihiloMekanismConstants;
import novamachina.exnihilomekanism.datagen.client.ExNihiloMekanismLangGenerator;
import novamachina.exnihilomekanism.datagen.client.ExNihiloMekanismOreItemGenerator;
import novamachina.exnihilomekanism.datagen.common.ExNihiloMekanismItemTagGenerator;
import novamachina.exnihilomekanism.datagen.common.ExNihiloMekanismRecipeGenerator;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ExNihiloMekanismDataGenerators {

  private ExNihiloMekanismDataGenerators() {}

  @SubscribeEvent
  public static void gatherData(GatherDataEvent event) {
    DataGenerator generator = event.getGenerator();

    if (event.includeServer()) {
      // Recipes
      generator.addProvider(true, new ExNihiloMekanismRecipeGenerator(generator));
      // Tags
      generator.addProvider(
          true,
          new ExNihiloMekanismItemTagGenerator(
              generator,
              new BlockTagsProvider(
                  generator,
                  ExNihiloMekanismConstants.ModIds.EX_NIHILO_MEKANISM,
                  event.getExistingFileHelper()),
              event.getExistingFileHelper()));
    }
    if (event.includeClient()) {
      // Items
      generator.addProvider(
          true, new ExNihiloMekanismOreItemGenerator(generator, event.getExistingFileHelper()));
    }
    // Lang
    generator.addProvider(true, new ExNihiloMekanismLangGenerator(generator, "en_us"));
  }
}
