package novamachina.exnihilosequentia.common.datagen;

import novamachina.exnihilosequentia.common.utility.Constants;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();

        if(event.includeServer()) {
            // Recipes
            generator.addProvider(new Recipes(generator));
            // LootTable
            generator.addProvider(new LootTables(generator));
            // Tags
            generator.addProvider(new ItemTags(generator, new BlockTagsProvider(generator, Constants.ModIds.EX_NIHILO_SEQUENTIA, event
                .getExistingFileHelper()), event.getExistingFileHelper()));
        }
        if(event.includeClient()) {
            // BlockStates
            generator.addProvider(new BlockStates(generator, event.getExistingFileHelper()));
            // Items
            generator.addProvider(new Items(generator, event.getExistingFileHelper()));
        }
    }
}
