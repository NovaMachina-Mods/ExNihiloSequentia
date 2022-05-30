package novamachina.exnihilosequentia;

import javax.annotation.Nonnull;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import novamachina.exnihilosequentia.client.ExNihiloBlockStateGenerator;
import novamachina.exnihilosequentia.client.ExNihiloItemGenerator;
import novamachina.exnihilosequentia.client.ExNihiloLangGenerator;
import novamachina.exnihilosequentia.client.ExNihiloOreItemGenerator;
import novamachina.exnihilosequentia.common.ExNihiloBlockTagsGenerator;
import novamachina.exnihilosequentia.common.ExNihiloFluidTagsGenerator;
import novamachina.exnihilosequentia.common.ExNihiloItemTagsGenerator;
import novamachina.exnihilosequentia.common.ExNihiloLootTableGenerator;
import novamachina.exnihilosequentia.common.ExNihiloRecipeGenerator;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ExNihiloDataGenerators {

  private ExNihiloDataGenerators() {
  }

  @SubscribeEvent
  public static void gatherData(@Nonnull final GatherDataEvent event) {
    @Nonnull final DataGenerator generator = event.getGenerator();

    if (event.includeServer()) {
      // Recipes
      generator.addProvider(new ExNihiloRecipeGenerator(generator));
      // LootTable
      generator.addProvider(new ExNihiloLootTableGenerator(generator));
      // Tags
      generator.addProvider(new ExNihiloItemTagsGenerator(generator,
          new BlockTagsProvider(generator, ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA,
              event.getExistingFileHelper()), event.getExistingFileHelper()));
      generator.addProvider(
          new ExNihiloFluidTagsGenerator(generator, event.getExistingFileHelper()));
      generator.addProvider(
          new ExNihiloBlockTagsGenerator(generator, event.getExistingFileHelper()));
    }
    if (event.includeClient()) {
      // BlockStates
      generator.addProvider(
          new ExNihiloBlockStateGenerator(generator, event.getExistingFileHelper()));
      // Items
      generator.addProvider(new ExNihiloItemGenerator(generator, event.getExistingFileHelper()));
      // Ores
      generator.addProvider(new ExNihiloOreItemGenerator(generator, event.getExistingFileHelper()));
      // Lang
      generator.addProvider(new ExNihiloLangGenerator(generator, "en_us"));
    }
  }
}
