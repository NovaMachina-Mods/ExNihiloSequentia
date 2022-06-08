package novamachina.exnihilomekanism;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import novamachina.exnihilomekanism.client.ExNihiloMekanismLangGenerator;
import novamachina.exnihilomekanism.client.ExNihiloMekanismOreItemGenerator;
import novamachina.exnihilomekanism.common.ExNihiloMekanismItemTagGenerator;
import novamachina.exnihilomekanism.common.ExNihiloMekanismRecipeGenerator;
import novamachina.exnihilomekanism.common.utility.ExNihiloMekanismConstants;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ExNihiloMekanismDataGenerators {

  private ExNihiloMekanismDataGenerators() {
  }

  @SubscribeEvent
  public static void gatherData(GatherDataEvent event) {
    DataGenerator generator = event.getGenerator();

    if (event.includeServer()) {
      // Recipes
      generator.addProvider(new ExNihiloMekanismRecipeGenerator(generator));
      // Tags
      generator
          .addProvider(new ExNihiloMekanismItemTagGenerator(generator,
              new BlockTagsProvider(generator, ExNihiloMekanismConstants.ModIds.EX_NIHILO_MEKANISM,
                  event
                      .getExistingFileHelper()), event.getExistingFileHelper()));
    }
    if (event.includeClient()) {
      // Items
      generator.addProvider(
          new ExNihiloMekanismOreItemGenerator(generator, event.getExistingFileHelper()));
    }
    // Lang
    generator.addProvider(new ExNihiloMekanismLangGenerator(generator, "en_us"));
  }
}
