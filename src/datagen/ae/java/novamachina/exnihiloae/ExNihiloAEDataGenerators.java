package novamachina.exnihiloae;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.data.event.GatherDataEvent;
import novamachina.exnihiloae.client.ExNihiloAEBlockStates;
import novamachina.exnihiloae.common.ExNihiloAELootTables;
import novamachina.exnihiloae.common.ExNihiloAERecipes;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ExNihiloAEDataGenerators {

  private ExNihiloAEDataGenerators() {}

  @SubscribeEvent
  public static void gatherData(GatherDataEvent event) {
    DataGenerator generator = event.getGenerator();

    if (event.includeServer()) {
      // Recipes
      generator.addProvider(true, new ExNihiloAERecipes(generator));
      // LootTable
      generator.addProvider(true, new ExNihiloAELootTables(generator));
    }
    if (event.includeClient()) {
      // BlockStates
      generator.addProvider(
          true, new ExNihiloAEBlockStates(generator, event.getExistingFileHelper()));
    }
  }
}
