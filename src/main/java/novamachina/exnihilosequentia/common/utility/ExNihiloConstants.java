package novamachina.exnihilosequentia.common.utility;

import java.util.ArrayList;
import java.util.List;

public class ExNihiloConstants {

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
    public static final String OAK_BARREL = addBlockToList("oak_barrel");
    public static final String STONE_BARREL = addBlockToList("stone_barrel");
    public static final String FIRED_CRUCIBLE = addBlockToList("fired_crucible");
    public static final String INFESTED_LEAVES = addBlockToList("infested_leaves");
    public static final String INFESTING_LEAVES = addBlockToList("infesting_leaves");

    public static final String OAK_SIEVE = addBlockToList("oak_sieve");

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

    public static final String CRAFTING_DOLL = "porcelain_doll";
    public static final String DIAMOND_MESH = "diamond_mesh";
    public static final String EMERALD_MESH = "emerald_mesh";
    public static final String FLINT_MESH = "flint_mesh";
    public static final String IRON_MESH = "iron_mesh";
    public static final String NETHERITE_MESH = "netherite_mesh";
    public static final String STRING_MESH = "string_mesh";
    public static final List<String> resourceList = new ArrayList<>();
    public static final String CRIMSON_NYLIUM_SPORE = createResourceName("crimson_nylium_spores");
    public static final String WARPED_NYLIUM_SPORE = createResourceName("warped_nylium_spores");
    public static final String NETHERITE_CROOK = createCrookName("netherite");
    public static final String NETHERITE_HAMMER = createHammerName("netherite");
    public static final String PORCELAIN_CLAY = createResourceName("porcelain_clay");
    public static final String MYCELIUM_SPORE = createResourceName("mycelium_spores");
    public static final String BEEHIVE_FRAME = createResourceName("beehive_frame");
    public static final String TUBE_CORAL_LARVA = createResourceName("tube_coral_larva");
    public static final String BRAIN_CORAL_LARVA = createResourceName("brain_coral_larva");
    public static final String BUBBLE_CORAL_LARVA = createResourceName("bubble_coral_larva");
    public static final String FIRE_CORAL_LARVA = createResourceName("fire_coral_larva");
    public static final String HORN_CORAL_LARVA = createResourceName("horn_coral_larva");
    public static final String GRASS_SEED = createSeedName("grass");

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
