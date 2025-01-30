package novamachina.exnihilosequentia.data;

import java.util.concurrent.CompletableFuture;
import javax.annotation.Nonnull;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import novamachina.exnihilosequentia.data.loot.EXNLootModifierProvider;
import novamachina.exnihilosequentia.data.loot.EXNLootProvider;
import novamachina.exnihilosequentia.data.models.EXNItemModelProvider;
import novamachina.exnihilosequentia.data.recipes.packs.EXNRecipeProvider;
import novamachina.exnihilosequentia.data.tags.EXNTagProvider;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class ExNihiloDataGenerators {

  private ExNihiloDataGenerators() {}

  @SubscribeEvent
  public static void gatherData(@Nonnull final GatherDataEvent.Client event) {
    DataGenerator generator = event.getGenerator();
    PackOutput output = generator.getPackOutput();
    ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
    CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

    event.addProvider(new EXNLootProvider(lookupProvider, output));
    event.addProvider(new EXNRecipeProvider.Runner(output, lookupProvider));
    event.addProvider(new EXNTagProvider(output, lookupProvider, existingFileHelper));
    event.addProvider(new EXNLootModifierProvider(lookupProvider, output));
    event.addProvider(new ExNihiloFurnaceFuleGenerator(output, lookupProvider));

    event.addProvider(new EXNItemModelProvider(output, existingFileHelper));
    event.addProvider(new EXNLangProvider(output, "en_us"));
    event.addProvider(new ExNihiloBlockStateGenerator(output, existingFileHelper));
  }
}
