package novamachina.exnihilosequentia.client;

import net.minecraft.data.DataGenerator;
import novamachina.exnihilosequentia.api.datagen.AbstractLangGenerator;
import novamachina.exnihilosequentia.common.item.mesh.MeshType;
import novamachina.exnihilosequentia.common.item.ore.Ore;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants.Blocks;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants.Fluids;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants.Items;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants.ModIds;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants.Tooltips;

public class ExNihiloLangGenerator extends AbstractLangGenerator {

  public ExNihiloLangGenerator(DataGenerator gen, String locale) {
    super(gen, locale);
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
    addFluid(Fluids.WITCH_WATER, "Witch Water");
    addFluid(Fluids.SEA_WATER, "Sea Water");
    addFluid(Fluids.WITCH_WATER_FLOW, "Flowing Witch Water");
    addFluid(Fluids.SEA_WATER_FLOW, "Flowing Sea Water");
    // Tooltips
    add(Tooltips.BEE, "Add to a barrel of witch water to spawn a Bee");
    add(Tooltips.BLAZE, "Add to a barrel of lava to spawn a Blaze");
    add(Tooltips.ENDERMAN, "Add to a barrel of witch water to spawn an Enderman");
    add(Tooltips.GUARDIAN, "Add to a barrel of sea water to spawn a Guardian");
    add(Tooltips.SHULKER, "Add to a barrel of witch water to spawn a Shulker");
    add(ModIds.EX_NIHILO_SEQUENTIA + ".subtitle.pebbleThrow", "Pebble flies");
    add("throwing.pebble", "Flying Pebble");
  }

  private void addItem() {
    // Ores
    for (String key : Ore.getOreMap().keySet()) {
      if (Boolean.TRUE.equals(Ore.getOreMap().get(key))) {
        addOreAutoName(key);
      } else if (key.equals("iron") || key.equals("gold") || key.equals("copper")) {
        addPieceAutoName(key);
      } else {
        addOreNoIngotAutoName(key);
      }
    }
    // Meshes
    for (MeshType mesh : MeshType.values()) {
      addMeshAutoName(mesh.getMeshName());
    }
    // Dolls
    addItem(Items.CRAFTING_DOLL, "Procelain Doll");
    addItem(Items.BEE_DOLL, "Buzzing Doll");
    addItem(Items.BLAZE_DOLL, "Blazing Doll");
    addItem(Items.ENDERMAN_DOLL, "Creeping Doll");
    addItem(Items.GUARDIAN_DOLL, "Protecting Doll");
    addItem(Items.SHULKER_DOLL, "Floating Doll");
    // Resources
    for (String name : Items.resourceList) {
      addItemAutoName(name);
    }
  }

  private void addBlock() {
    for (int i = 0; i < Blocks.blocksList.size(); i++) {
      addBlockAutoName(Blocks.blocksList.get(i));
    }
  }
}