package novamachina.exnihilosequentia.datagen.client;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants.Fluids;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants.ModIds;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants.Tooltips;
import novamachina.exnihilosequentia.datagen.api.datagen.AbstractLangGenerator;
import novamachina.exnihilosequentia.world.item.EXNItems;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;
import novamachina.novacore.world.item.ItemDefinition;
import novamachina.novacore.world.level.block.BlockDefinition;

public class ExNihiloLangGenerator extends AbstractLangGenerator {

  public ExNihiloLangGenerator(DataGenerator gen, String locale) {
    super(gen, ModIds.EX_NIHILO_SEQUENTIA, locale);
  }

  @Override
  protected void addTranslations() {
    // CreativeTab
    add("itemGroup." + ModIds.EX_NIHILO_SEQUENTIA, "Ex Nihilo: Sequentia");
    // Blocks
    addBlock();
    // Items
    addItem();
    // Compats
    addCompat();
    // Miscs
    addMisc();
  }

  private void addCompat() {
    final String WAILA_LANG = "waila.";
    // JEI
    add("jei.sieve.dropChance", "Drop Chance");
    // WAILA/HYWLA
    add(WAILA_LANG + "progress", "Progress: %s");
    add(WAILA_LANG + "barrel.fluidAmount", "Fluid(%s): %s mB");
    add(WAILA_LANG + "barrel.solidAmount", "Solid(%s): %s / %s");
    add(WAILA_LANG + "barrel.block", "Block: %s");
    add(WAILA_LANG + "barrel.compost", "Compost: %s / %s");
    add(WAILA_LANG + "sieve.block", "Sifting: %s");
    add(WAILA_LANG + "sieve.mesh", "Mesh: %s");
    add(WAILA_LANG + "crucible.fluid", "Fluid(%s): %s mB");
    add(WAILA_LANG + "crucible.solid", "Solid(%s): %s");
    add(WAILA_LANG + "crucible.heat", "Heat: %s");
    add(WAILA_LANG + "crucible.no_heat", "No Heat Source");
    // TOP
    add("top.barrel.mode", "Mode: %s");
  }

  private void addMisc() {
    // Fluids
    addFluidName(Fluids.WITCH_WATER, "Witch Water");
    addFluidName(Fluids.SEA_WATER, "Sea Water");
    addFluidName(Fluids.WITCH_WATER_FLOW, "Flowing Witch Water");
    addFluidName(Fluids.SEA_WATER_FLOW, "Flowing Sea Water");
    // Tooltips
    add(Tooltips.BEE, "Add to a barrel of witch water to spawn a Bee");
    add(Tooltips.BLAZE, "Add to a barrel of lava to spawn a Blaze");
    add(Tooltips.ENDERMAN, "Add to a barrel of witch water to spawn an Enderman");
    add(Tooltips.GUARDIAN, "Add to a barrel of sea water to spawn a Guardian");
    add(Tooltips.SHULKER, "Add to a barrel of witch water to spawn a Shulker");
    add(ModIds.EX_NIHILO_SEQUENTIA + ".subtitle.pebbleThrow", "Pebble flies");
    add("throwing.pebble", "Flying Pebble");
    add("stat." + ModIds.EX_NIHILO_SEQUENTIA + ".sieved", "Times Sieved");

    addJadeEntry("barrel");
    addJadeEntry("crucible");
    addJadeEntry("sieve");
    addJadeEntry("infesting_leaves");
  }

  private void addJadeEntry(String id) {
    add("config.jade.plugin_" + ModIds.EX_NIHILO_SEQUENTIA + "." + id, properNaming(id));
  }

  private void addItem() {
    for (ItemDefinition<? extends Item> definition : EXNItems.ITEMS.getRegistry()) {
      addItemName(definition);
    }
  }

  private void addBlock() {
    for (BlockDefinition<? extends Block> definition : EXNBlocks.getDefinitions()) {
      addBlockName(definition);
    }
  }
}
