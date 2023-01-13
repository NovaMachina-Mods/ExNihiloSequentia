package novamachina.exnihilosequentia.datagen;

import javax.annotation.Nonnull;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import novamachina.exnihilosequentia.datagen.client.ExNihiloBlockStateGenerator;
import novamachina.exnihilosequentia.datagen.client.ExNihiloItemGenerator;
import novamachina.exnihilosequentia.datagen.client.ExNihiloLangGenerator;
import novamachina.exnihilosequentia.datagen.client.ExNihiloOreItemGenerator;
import novamachina.exnihilosequentia.datagen.common.ExNihiloBlockTagsGenerator;
import novamachina.exnihilosequentia.datagen.common.ExNihiloFluidTagsGenerator;
import novamachina.exnihilosequentia.datagen.common.ExNihiloItemTagsGenerator;
import novamachina.exnihilosequentia.datagen.common.ExNihiloLootTableGenerator;
import novamachina.exnihilosequentia.datagen.common.ExNihiloRecipeGenerator;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ExNihiloDataGenerators {

  private ExNihiloDataGenerators() {}

  @SubscribeEvent
  public static void gatherData(@Nonnull final GatherDataEvent event) {
    @Nonnull final DataGenerator generator = event.getGenerator();

    if (event.includeServer()) {
      // Recipes
      generator.addProvider(true, new ExNihiloRecipeGenerator(generator));
      // LootTable
      generator.addProvider(true, new ExNihiloLootTableGenerator(generator));
      // Tags
      generator.addProvider(
          true,
          new ExNihiloItemTagsGenerator(
              generator,
              new BlockTagsProvider(
                  generator,
                  ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA,
                  event.getExistingFileHelper()),
              event.getExistingFileHelper()));
      generator.addProvider(
          true, new ExNihiloFluidTagsGenerator(generator, event.getExistingFileHelper()));
      generator.addProvider(
          true, new ExNihiloBlockTagsGenerator(generator, event.getExistingFileHelper()));
    }
    if (event.includeClient()) {
      // BlockStates
      generator.addProvider(
          true, new ExNihiloBlockStateGenerator(generator, event.getExistingFileHelper()));
      // Items
      generator.addProvider(
          true, new ExNihiloItemGenerator(generator, event.getExistingFileHelper()));
      // Ores
      generator.addProvider(
          true, new ExNihiloOreItemGenerator(generator, event.getExistingFileHelper()));
      // Lang
      generator.addProvider(true, new ExNihiloLangGenerator(generator, "en_us"));
    }
  }
}
