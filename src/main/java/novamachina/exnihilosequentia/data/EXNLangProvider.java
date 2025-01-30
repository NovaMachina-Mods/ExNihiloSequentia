package novamachina.exnihilosequentia.data;

import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import novamachina.exnihilosequentia.ExNihiloSequentia;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants.Fluids;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants.ModIds;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants.Tooltips;
import novamachina.exnihilosequentia.world.item.EXNItems;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;
import novamachina.novacore.data.AbstractLangGenerator;
import novamachina.novacore.world.item.ItemDefinition;
import novamachina.novacore.world.level.block.BlockDefinition;

public class EXNLangProvider extends AbstractLangGenerator {

  public EXNLangProvider(PackOutput output, String locale) {
    super(output, ModIds.EX_NIHILO_SEQUENTIA, locale);
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
    addConfigs();
  }

  private void addConfigs() {
    addConfigEntry("pebble", "Pebble Settings");
    addConfigEntry("pebbleDamage", "Damage");
    addConfigEntry("enableThrowable", "Throwable");

    addConfigEntry("barrel", "Barrel Settings");
    addConfigEntry("barrelNumberOfBuckets", "Max Number of Buckets");
    addConfigEntry("rainFillAmount", "Rain mB per Second");
    addConfigEntry("woodBarrelMaxTemp", "Wooden Barrel Max Temp");
    addConfigEntry("showParticles", "Show Particles");
    addConfigEntry("netherBarrelSoundsEnabled", "Nether Barrel Sounds Enabled");

    addConfigEntry("mob_spawn", "Mob Spawn Settings");
    addConfigEntry("secondsToSpawnMobs", "Seconds to Spawn Mobs");

    addConfigEntry("compost", "Compost Settings");
    addConfigEntry("maxSolidAmount", "Max Solid Amount");
    addConfigEntry("secondsToCompost", "Seconds to Compost");

    addConfigEntry("fluid_transform", "Fluid Transform Settings");
    addConfigEntry("secondsToTransformFluid", "Seconds to Transform Fluid");

    addConfigEntry("crook", "Crook Settings");
    addConfigEntry("vanillaDropSimulateCount", "Drop Simulation Count");
    addConfigEntry("maxBonusStringCount", "Max Bonus String");
    addConfigEntry("minStringCount", "Min Bonus String");

    addConfigEntry("crucible", "Crucible Settings");
    addConfigEntry("ticksBetweenMelts", "Ticks Between Melt Actions");
    addConfigEntry("crucibleNumberOfBuckets", "Number of Buckets");
    addConfigEntry("netherCrucibleSoundsEnabled", "Nether Crucible Sounds Enabled");

    addConfigEntry("wood", "Wooden Crucible Settings");
    addConfigEntry("woodHeatRate", "Wooden Crucible Heat Rate");

    addConfigEntry("infested_leaves", "Infested Leaves Settings");
    addConfigEntry("secondsToTransformLeaves", "Seconds to Transform");
    addConfigEntry("spreadChance", "Spread Chance");
    addConfigEntry("ticksBetweenSpreadAttempt", "Ticks Between Spread Attempt");

    addConfigEntry("sieve", "Sieve Settings");
    addConfigEntry("flattenSieveRecipes", "Flatten Recipes");
    addConfigEntry("sieveRange", "Sieve Range");
    addConfigEntry("enableMeshDurability", "Enable Mesh Durability");
    addConfigEntry("meshStackSize", "Max Mesh Stack Size");
    addConfigEntry("maxSieveClicks", "Sieve Clicks");
    addConfigEntry("netherSieveSoundsEnabled", "Nether Sieve Sounds Enabled");

    addConfigEntry("ore", "Ore Settings");
    addConfigEntry("enableOreOverride", "Enable Ore Override");
    addConfigEntry("enableAluminum", "Enable Aluminum");
    addConfigEntry("enableCopper", "Enable Copper");
    addConfigEntry("enableGold", "Enable Gold");
    addConfigEntry("enableIron", "Enable Iron");
    addConfigEntry("enableLead", "Enable Lead");
    addConfigEntry("enableNickel", "Enable Nickel");
    addConfigEntry("enablePlatinum", "Enable Platinum");
    addConfigEntry("enableSilver", "Enable Silver");
    addConfigEntry("enableTin", "Enable Tin");
    addConfigEntry("enableUranium", "Enable Uranium");
    addConfigEntry("enableZinc", "Enable Zinc");

    addConfigEntry("durability", "Durability Settings");

    addConfigEntry("hammer", "Hammer Durability");
    addConfigEntry("hammerIronValue", "Iron Hammer");
    addConfigEntry("hammerDiamondValue", "Diamond Hammer");
    addConfigEntry("hammerGoldValue", "Gold Hammer");
    addConfigEntry("hammerNetheriteValue", "Netherite Hammer");
    addConfigEntry("hammerStoneValue", "Stone Hammer");
    addConfigEntry("hammerWoodValue", "Wooden Hammer");

    addConfigEntry("crookAndesiteValue", "Andesite Crook");
    addConfigEntry("crookBoneValue", "Bone Crook");
    addConfigEntry("crookDiamondValue", "Diamond Crook");
    addConfigEntry("crookDioriteValue", "Diorite Crook");
    addConfigEntry("crookGoldValue", "Gold Crook");
    addConfigEntry("crookGraniteValue", "Granite Crook");
    addConfigEntry("crookIronValue", "Iron Crook");
    addConfigEntry("crookNetheriteValue", "Nether Crook");
    addConfigEntry("crookStoneValue", "Stone Crook");
    addConfigEntry("crookWoodValue", "Wooden Crok");

    addConfigEntry("mesh", "Mesh Durability");
    addConfigEntry("meshStringValue", "String Mesh");
    addConfigEntry("meshFlintValue", "Flint Mesh");
    addConfigEntry("meshIronValue", "Iron Mesh");
    addConfigEntry("meshDiamondValue", "Diamond Mesh");
    addConfigEntry("meshEmeraldValue", "Emerald Mesh");
    addConfigEntry("meshNetheriteValue", "Netherite Mesh");
    addConfigEntry("section.exnihilosequentia.startup.toml", "");
    addConfigEntry("section.exnihilosequentia.startup.toml.title", "");
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

    addJEIEntry("compost", "Composting");
    addJEIEntry("crushing", "Crushing");
    addJEIEntry("harvest", "Harvesting");
    addJEIEntry("heat", "Crucible Heat Sources");
    addJEIEntry("melting", "Crucible Melting");
    addJEIEntry("fired_melting", "Fired Crucible Melting");
    addJEIEntry("precipitate", "Precipitate");
    addJEIEntry("dry_sifting", "Sifting");
    addJEIEntry("wet_sifting", "Waterlogged Sifting");
    addJEIEntry("solidifying", "Solidifying");
    addJEIEntry("transition", "Transition");

    add(
        ExNihiloSequentia.MOD_ID + ".open_beta_text",
        "Ex Nihilo: Sequentia is in open Beta. There may be things that are broken or missing. If you encounter something that is broken or missing, please open a bug ticket: ");
    add(ExNihiloSequentia.MOD_ID + ".issue_collector", "Issue Collector");
  }

  private void addJEIEntry(String id, String fullText) {
    add(String.format("jei.category.%s", id), fullText);
  }

  private void addJadeEntry(String id) {
    add("config.jade.plugin_" + ModIds.EX_NIHILO_SEQUENTIA + "." + id, properNaming(id));
  }

  private void addConfigEntry(String id, String fullText) {
    add(String.format("%s.configuration.%s", ExNihiloSequentia.MOD_ID, id), fullText);
  }

  private void addItem() {
    EXNItems.getDefinitions().forEach(this::addItemName);
    EXNBlocks.getDefinitions().forEach(this::addItemName);
  }

  private void addBlock() {
    EXNBlocks.getDefinitions().forEach(this::addBlockName);
  }
}
