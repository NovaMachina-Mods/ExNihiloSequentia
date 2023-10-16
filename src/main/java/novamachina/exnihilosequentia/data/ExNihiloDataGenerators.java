package novamachina.exnihilosequentia.data;

import java.util.concurrent.CompletableFuture;
import javax.annotation.Nonnull;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import novamachina.exnihilosequentia.data.loot.EXNLootModifierProvider;
import novamachina.exnihilosequentia.data.loot.EXNLootProvider;
import novamachina.exnihilosequentia.data.models.ItemModelProvider;
import novamachina.exnihilosequentia.data.recipes.packs.EXNRecipeProvider;
import novamachina.exnihilosequentia.data.tags.EXNTagProvider;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ExNihiloDataGenerators {

  private ExNihiloDataGenerators() {}

  @SubscribeEvent
  public static void gatherData(@Nonnull final GatherDataEvent event) {
    DataGenerator generator = event.getGenerator();
    PackOutput output = generator.getPackOutput();
    ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
    CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

    generator.addProvider(event.includeServer(), new EXNLootProvider(output));
    generator.addProvider(event.includeServer(), new EXNRecipeProvider(output, existingFileHelper));
    generator.addProvider(
        event.includeServer(), new EXNTagProvider(output, lookupProvider, existingFileHelper));
    generator.addProvider(event.includeServer(), new EXNLootModifierProvider(output));

    generator.addProvider(event.includeClient(), new ItemModelProvider(output, existingFileHelper));
    generator.addProvider(event.includeClient(), new EXNLangProvider(output, "en_us"));
    generator.addProvider(
        event.includeClient(), new ExNihiloBlockStateGenerator(output, existingFileHelper));
  }
}
