package novamachina.exnihilosequentia.common.datagen;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

import javax.annotation.Nonnull;

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
            generator.addProvider(new ExNihiloFluidTagsGenerator(generator, event.getExistingFileHelper()));
        }
        if (event.includeClient()) {
            // BlockStates
            generator.addProvider(new ExNihiloBlockStateGenerator(generator, event.getExistingFileHelper()));
            // Items
            generator.addProvider(new ExNihiloItemGenerator(generator, event.getExistingFileHelper()));
            // Ores
            generator.addProvider(new ExNihiloOreItemGenerator(generator, event.getExistingFileHelper()));
        }
    }
}
