package novamachina.exnihilotinkers;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import novamachina.exnihilotinkers.client.EXNTinkersBlockStates;
import novamachina.exnihilotinkers.client.EXNTinkersLang;
import novamachina.exnihilotinkers.client.EXNTinkersOreItems;
import novamachina.exnihilotinkers.common.EXNTinkersBlockTags;
import novamachina.exnihilotinkers.common.EXNTinkersItemTags;
import novamachina.exnihilotinkers.common.EXNTinkersLootTables;
import novamachina.exnihilotinkers.common.EXNTinkersRecipes;
import novamachina.exnihilotinkers.common.utility.EXNTinkersConstants;
import novamachina.exnihilotinkers.tinkers.EXNTinkersPartSprites;
import novamachina.exnihilotinkers.tinkers.EXNTinkersStationSlotLayout;
import novamachina.exnihilotinkers.tinkers.EXNTinkersToolDefinitions;
import novamachina.exnihilotinkers.tinkers.EXNTinkersToolRecipes;
import slimeknights.tconstruct.library.client.data.material.MaterialPartTextureGenerator;
import slimeknights.tconstruct.tools.data.sprite.TinkerMaterialSpriteProvider;

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
      generator.addProvider(new EXNTinkersRecipes(generator));
      // Tags
      BlockTagsProvider blockTags = new EXNTinkersBlockTags(generator, fileHelper);
      generator.addProvider(blockTags);
      generator.addProvider(new EXNTinkersItemTags(generator, blockTags, fileHelper));
      // LootTables
      generator.addProvider(new EXNTinkersLootTables(generator));
      //Tinkers Tool Recipe
      generator.addProvider(new EXNTinkersToolRecipes(generator));
      //Tinkers Tool Definition
      generator.addProvider(new EXNTinkersToolDefinitions(generator));
      //Tinkers Station Slot Layout
      generator.addProvider(new EXNTinkersStationSlotLayout(generator));
    }
    if (event.includeClient()) {
      // BlockStates
      generator.addProvider(new EXNTinkersBlockStates(generator, fileHelper));
      // Lang
      generator.addProvider(new EXNTinkersLang(generator, "en_us"));
      // Items
      generator.addProvider(new EXNTinkersOreItems(generator, fileHelper));
      TinkerMaterialSpriteProvider materialSprites = new TinkerMaterialSpriteProvider();
      generator.addProvider(new MaterialPartTextureGenerator(generator, fileHelper, new EXNTinkersPartSprites(), materialSprites));
    }
  }
}
