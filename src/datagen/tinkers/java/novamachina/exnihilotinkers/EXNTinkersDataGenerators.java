package novamachina.exnihilotinkers;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import novamachina.exnihilotinkers.client.EXNTinkersBlockStates;
import novamachina.exnihilotinkers.client.EXNTinkersOreItems;
import novamachina.exnihilotinkers.common.EXNTinkersItemTags;
import novamachina.exnihilotinkers.common.EXNTinkersLootTables;
import novamachina.exnihilotinkers.common.EXNTinkersRecipes;
import novamachina.exnihilotinkers.common.utility.EXNTinkersConstants;
//import slimeknights.tconstruct.library.client.data.material.MaterialPartTextureGenerator;
//import slimeknights.tconstruct.tools.data.sprite.TinkerMaterialSpriteProvider;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class EXNTinkersDataGenerators {

  private EXNTinkersDataGenerators() {
  }

  @SubscribeEvent
  public static void gatherData(GatherDataEvent event) {
    DataGenerator generator = event.getGenerator();
    ExistingFileHelper fileHelper = event.getExistingFileHelper();

    if (event.includeServer()) {
      // Recipes
      generator.addProvider(true, new EXNTinkersRecipes(generator));
      // Tags
      generator.addProvider(true, new EXNTinkersItemTags(generator, new BlockTagsProvider(generator,
          EXNTinkersConstants.ModIds.EX_NIHILO_TINKERS, fileHelper), fileHelper));
      // LootTables
      generator.addProvider(true, new EXNTinkersLootTables(generator));
      //TODO reanable when tinkers available for 1.18.2
      //Tinkers Tool Recipe
      //generator.addProvider(new EXNTinkersToolRecipes(generator));
      ////Tinkers Tool Definition
      //generator.addProvider(new EXNTinkersToolDefinitions(generator));
      ////Tinkers Station Slot Layout
      //generator.addProvider(new EXNTinkersStationSlotLayout(generator));
    }
    if (event.includeClient()) {
      // BlockStates
      generator.addProvider(true, new EXNTinkersBlockStates(generator, fileHelper));
      // Items
      generator.addProvider(true, new EXNTinkersOreItems(generator, fileHelper));
      //TODO reanable when tinkers available for 1.18.2
      //TinkerMaterialSpriteProvider materialSprites = new TinkerMaterialSpriteProvider();
      //generator.addProvider(new MaterialPartTextureGenerator(generator, fileHelper, new EXNTinkersPartSprites(), materialSprites));
    }
  }
}
