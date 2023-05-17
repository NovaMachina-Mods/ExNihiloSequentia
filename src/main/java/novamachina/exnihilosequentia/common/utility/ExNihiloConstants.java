package novamachina.exnihilosequentia.common.utility;

import java.util.ArrayList;
import java.util.List;

public class ExNihiloConstants {

  private static final String STONE_TAG = "stone";

  private ExNihiloConstants() {}

  public static class BarrelModes {

    public static final String BLOCK = "block";
    public static final String COMPOST = "compost";
    public static final String EMPTY = "empty";
    public static final String FLUID = "fluid";
    public static final String MOB = "mob";
    public static final String TRANSFORM = "transform";

    private BarrelModes() {}
  }

  public static class Blocks {

    public static List<String> blocksList = new ArrayList<>();
    public static final String BARRELS = "barrels";
    public static final String CRUCIBLES = "crucibles";
    public static final String SIEVES = "sieves";
    public static final String ACACIA_BARREL = addBlockToList("acacia_barrel");
    public static final String BIRCH_BARREL = addBlockToList("birch_barrel");
    public static final String CRIMSON_BARREL = addBlockToList("crimson_barrel");
    public static final String DARK_OAK_BARREL = addBlockToList("dark_oak_barrel");
    public static final String JUNGLE_BARREL = addBlockToList("jungle_barrel");
    public static final String MANGROVE_BARREL = addBlockToList("mangrove_barrel");
    public static final String OAK_BARREL = addBlockToList("oak_barrel");
    public static final String SPRUCE_BARREL = addBlockToList("spruce_barrel");
    public static final String STONE_BARREL = addBlockToList("stone_barrel");
    public static final String WARPED_BARREL = addBlockToList("warped_barrel");
    public static final String ACACIA_CRUCIBLE = addBlockToList("acacia_crucible");
    public static final String BIRCH_CRUCIBLE = addBlockToList("birch_crucible");
    public static final String CRIMSON_CRUCIBLE = addBlockToList("crimson_crucible");
    public static final String DARK_OAK_CRUCIBLE = addBlockToList("dark_oak_crucible");
    public static final String FIRED_CRUCIBLE = addBlockToList("fired_crucible");
    public static final String JUNGLE_CRUCIBLE = addBlockToList("jungle_crucible");
    public static final String MANGROVE_CRUCIBLE = addBlockToList("mangrove_crucible");
    public static final String OAK_CRUCIBLE = addBlockToList("oak_crucible");
    public static final String SPRUCE_CRUCIBLE = addBlockToList("spruce_crucible");
    public static final String UNFIRED_CRUCIBLE = addBlockToList("unfired_crucible");
    public static final String WARPED_CRUCIBLE = addBlockToList("warped_crucible");
    public static final String CRUSHED_ANDESITE = addBlockToList("crushed_andesite");
    public static final String CRUSHED_BASALT = addBlockToList("crushed_basalt");
    public static final String CRUSHED_BLACKSTONE = addBlockToList("crushed_blackstone");
    public static final String CRUSHED_CALCITE = addBlockToList("crushed_calcite");
    public static final String CRUSHED_DEEPSLATE = addBlockToList("crushed_deepslate");
    public static final String CRUSHED_DIORITE = addBlockToList("crushed_diorite");
    public static final String CRUSHED_DRIPSTONE = addBlockToList("crushed_dripstone");
    public static final String CRUSHED_END_STONE = addBlockToList("crushed_end_stone");
    public static final String CRUSHED_GRANITE = addBlockToList("crushed_granite");
    public static final String CRUSHED_NETHERRACK = addBlockToList("crushed_netherrack");
    public static final String CRUSHED_TUFF = addBlockToList("crushed_tuff");
    public static final String DUST = addBlockToList("dust");
    public static final String END_CAKE = addBlockToList("end_cake");
    public static final String INFESTED_LEAVES = addBlockToList("infested_leaves");
    public static final String INFESTING_LEAVES = addBlockToList("infesting_leaves");
    public static final String SEA_WATER = addBlockToList("sea_water");
    public static final String WITCH_WATER = addBlockToList("witch_water");
    public static final String ACACIA_SIEVE = addBlockToList("acacia_sieve");
    public static final String BIRCH_SIEVE = addBlockToList("birch_sieve");
    public static final String CRIMSON_SIEVE = addBlockToList("crimson_sieve");
    public static final String DARK_OAK_SIEVE = addBlockToList("dark_oak_sieve");
    public static final String JUNGLE_SIEVE = addBlockToList("jungle_sieve");
    public static final String MANGROVE_SIEVE = addBlockToList("mangrove_sieve");
    public static final String OAK_SIEVE = addBlockToList("oak_sieve");
    public static final String SPRUCE_SIEVE = addBlockToList("spruce_sieve");
    public static final String WARPED_SIEVE = addBlockToList("warped_sieve");

    private Blocks() {}

    private static String addBlockToList(final String name) {
      blocksList.add(name);
      return name;
    }
  }

  public static class Fluids {

    public static final String SEA_WATER = "sea_water";
    public static final String SEA_WATER_FLOW = "sea_water_flow";
    public static final String WITCH_WATER = "witch_water";
    public static final String WITCH_WATER_FLOW = "witch_water_flow";

    private Fluids() {}
  }

  public static class Items {

    public static final String BEE_DOLL = "bee_doll";
    public static final String BLAZE_DOLL = "blaze_doll";
    public static final String CRAFTING_DOLL = "porcelain_doll";
    public static final String DIAMOND_MESH = "diamond_mesh";
    public static final String EMERALD_MESH = "emerald_mesh";
    public static final String ENDERMAN_DOLL = "enderman_doll";
    public static final String FLINT_MESH = "flint_mesh";
    public static final String GUARDIAN_DOLL = "guardian_doll";
    public static final String IRON_MESH = "iron_mesh";
    public static final String NETHERITE_MESH = "netherite_mesh";
    public static final String SHULKER_DOLL = "shulker_doll";
    public static final String STRING_MESH = "string_mesh";
    public static final List<String> resourceList = new ArrayList<>();
    public static final String CALCITE_PEBBLE = createPebbleName("calcite");
    public static final String DRIPSTONE_PEBBLE = createPebbleName("dripstone");
    public static final String CRIMSON_NYLIUM_SPORE = createResourceName("crimson_nylium_spores");
    public static final String WARPED_NYLIUM_SPORE = createResourceName("warped_nylium_spores");
    public static final String BASALT_CROOK = createCrookName("basalt");
    public static final String BLACKSTONE_CROOK = createCrookName("blackstone");
    public static final String CALCITE_CROOK = createCrookName("calcite");
    public static final String COPPER_CROOK = createCrookName("copper");
    public static final String CRIMSON_FUNGUS_CROOK = createCrookName("crimson_fungus");
    public static final String DEEPSLATE_CROOK = createCrookName("deepslate");
    public static final String DRIPSTONE_CROOK = createCrookName("dripstone");
    public static final String NETHERITE_CROOK = createCrookName("netherite");
    public static final String NETHER_BRICK_CROOK = createCrookName("nether_brick");
    public static final String RED_NETHER_BRICK_CROOK = createCrookName("red_nether_brick");
    public static final String TERRACOTTA_CROOK = createCrookName("terracotta");
    public static final String TUFF_CROOK = createCrookName("tuff");
    public static final String WARPED_FUNGUS_CROOK = createCrookName("warped_fungus");
    public static final String ANDISITE_CROOK = createCrookName("andesite");
    public static final String BLAZE_CROOK = createCrookName("blaze");
    public static final String BONE_CROOK = createCrookName("bone");
    public static final String CLAY_CROOK = createCrookName("clay");
    public static final String UNCOOKED_CLAY_CROOK = createCrookName("uncooked_clay");
    public static final String DIAMOND_CROOK = createCrookName("diamond");
    public static final String DIORITE_CROOK = createCrookName("diorite");
    public static final String GOLD_CROOK = createCrookName("golden");
    public static final String GRANITE_CROOK = createCrookName("granite");
    public static final String IRON_CROOK = createCrookName("iron");
    public static final String NETHERRACK_CROOK = createCrookName("netherrack");
    public static final String PRISMARINE_CROOK = createCrookName("prismarine");
    public static final String PURPUR_CROOK = createCrookName("purpur");
    public static final String STONE_CROOK = createCrookName(STONE_TAG);
    public static final String WOODEN_CROOK = createCrookName("wooden");
    public static final String BASALT_HAMMER = createHammerName("basalt");
    public static final String BLACKSTONE_HAMMER = createHammerName("blackstone");
    public static final String CALCITE_HAMMER = createHammerName("calcite");
    public static final String COPPER_HAMMER = createHammerName("copper");
    public static final String CRIMSON_FUNGUS_HAMMER = createHammerName("crimson_fungus");
    public static final String DEEPSLATE_HAMMER = createHammerName("deepslate");
    public static final String DIORITE_HAMMER = createHammerName("diorite");
    public static final String DRIPSTONE_HAMMER = createHammerName("dripstone");
    public static final String GRANITE_HAMMER = createHammerName("granite");
    public static final String NETHER_BRICK_HAMMER = createHammerName("nether_brick");
    public static final String PRISMARINE_HAMMER = createHammerName("prismarine");
    public static final String RED_NETHER_BRICK_HAMMER = createHammerName("red_nether_brick");
    public static final String TERRACOTTA_HAMMER = createHammerName("terracotta");
    public static final String TUFF_HAMMER = createHammerName("tuff");
    public static final String WARPED_FUNGUS_HAMMER = createHammerName("warped_fungus");
    public static final String ANDESITE_HAMMER = createHammerName("andesite");
    public static final String DIAMOND_HAMMER = createHammerName("diamond");
    public static final String GOLD_HAMMER = createHammerName("golden");
    public static final String IRON_HAMMER = createHammerName("iron");
    public static final String NETHERITE_HAMMER = createHammerName("netherite");
    public static final String STONE_HAMMER = createHammerName(STONE_TAG);
    public static final String WOODEN_HAMMER = createHammerName("wooden");
    public static final String DEEPSLATE_PEBBLE = createPebbleName("deepslate");
    public static final String END_STONE_PEBBLE = createPebbleName("end_stone");
    public static final String NETHERRACK_PEBBLE = createPebbleName("netherrack");
    public static final String TUFF_PEBBLE = createPebbleName("tuff");
    public static final String ANDESITE_PEBBLE = createPebbleName("andesite");
    public static final String BASALT_PEBBLE = createPebbleName("basalt");
    public static final String BLACKSTONE_PEBBLE = createPebbleName("blackstone");
    public static final String DIORITE_PEBBLE = createPebbleName("diorite");
    public static final String GRANITE_PEBBLE = createPebbleName("granite");
    public static final String STONE_PEBBLE = createPebbleName(STONE_TAG);
    public static final String PORCELAIN_CLAY = createResourceName("porcelain_clay");
    public static final String SEA_WATER_BUCKET = createResourceName("sea_water_bucket");
    public static final String SILKWORM = createResourceName("silkworm");
    public static final String WITCH_WATER_BUCKET = createResourceName("witch_water_bucket");
    public static final String MYCELIUM_SPORE = createResourceName("mycelium_spores");
    public static final String BEEHIVE_FRAME = createResourceName("beehive_frame");
    public static final String COOKED_SILKWORM = createResourceName("cooked_silkworm");
    public static final String TUBE_CORAL_LARVA = createResourceName("tube_coral_larva");
    public static final String BRAIN_CORAL_LARVA = createResourceName("brain_coral_larva");
    public static final String BUBBLE_CORAL_LARVA = createResourceName("bubble_coral_larva");
    public static final String FIRE_CORAL_LARVA = createResourceName("fire_coral_larva");
    public static final String HORN_CORAL_LARVA = createResourceName("horn_coral_larva");
    public static final String GRASS_SEED = createSeedName("grass");
    public static final String ACACIA_SEED = createSeedName("acacia");
    public static final String SEED_BAMBOO = createSeedName("bamboo");
    public static final String SEED_BIRCH = createSeedName("birch");
    public static final String SEED_CACTUS = createSeedName("cactus");
    public static final String SEED_CARROT = createSeedName("carrot");
    public static final String SEED_DARK_OAK = createSeedName("dark_oak");
    public static final String SEED_FERN = createSeedName("fern");
    public static final String SEED_JUNGLE = createSeedName("jungle");
    public static final String SEED_KELP = createSeedName("kelp");
    public static final String SEED_LARGE_FERN = createSeedName("large_fern");
    public static final String SEED_OAK = createSeedName("oak");
    public static final String SEED_PICKLE = createSeedName("pickle");
    public static final String SEED_POTATO = createSeedName("potato");
    public static final String SEED_SPRUCE = createSeedName("spruce");
    public static final String SEED_SUGARCANE = createSeedName("sugarcane");
    public static final String SEED_SWEET_BERRY = createSeedName("sweet_berry");
    public static List<String> seedList = new ArrayList<>();

    private Items() {}

    private static String createCrookName(final String name) {
      String crookName = name + "_crook";
      resourceList.add(crookName);
      return crookName;
    }

    private static String createHammerName(final String name) {
      String hammerName = name + "_hammer";
      resourceList.add(hammerName);
      return hammerName;
    }

    private static String createPebbleName(final String name) {
      String pebbleName = name + "_pebble";
      resourceList.add(pebbleName);
      return pebbleName;
    }

    private static String createSeedName(final String name) {
      String seedName = name + "_seeds";
      resourceList.add(seedName);
      return seedName;
    }

    private static String createResourceName(final String name) {
      resourceList.add(name);
      return name;
    }
  }

  public static class ModIds {

    public static final String CRAFT_TWEAKER = "crafttweaker";
    public static final String CREATE = "create";
    public static final String EX_NIHILO_SEQUENTIA = "exnihilosequentia";
    public static final String IMMERSIVE_ENGINEERING = "immersiveengineering";
    public static final String JEI = "jei";
    public static final String MINECRAFT = "minecraft";
    public static final String SILENT_MECHANISM = "silent_mechanism";
    public static final String THERMAL_EXPANSION = "thermalexpansion";
    public static final String TOP = "theoneprobe";

    private ModIds() {}
  }

  public static class Ore {

    public static final String ALUMINUM = "aluminum";

    public static final String BISMUTH = "bismuth";

    public static final String COPPER = "copper";

    public static final String GOLD = "gold";

    public static final String IRON = "iron";

    public static final String LEAD = "lead";

    public static final String NICKEL = "nickel";

    public static final String PLATINUM = "platinum";

    public static final String SILVER = "silver";

    public static final String TIN = "tin";

    public static final String URANIUM = "uranium";

    public static final String ZINC = "zinc";

    private Ore() {}
  }

  public static class Tooltips {

    public static final String BEE = "tooltip.doll.bee";

    public static final String BLAZE = "tooltip.doll.blaze";

    public static final String ENDERMAN = "tooltip.doll.enderman";

    public static final String GUARDIAN = "tooltip.doll.guardian";

    public static final String SHULKER = "tooltip.doll.shulker";

    private Tooltips() {}
  }
}
