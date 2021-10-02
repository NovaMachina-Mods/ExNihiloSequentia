package novamachina.exnihilosequentia.api.utility;

public class ExNihiloConstants {

    private static final String STONE_TAG = "stone";

    private ExNihiloConstants() {
    }

    public static class BarrelModes {

        public static final String BLOCK = "block";
        public static final String COMPOST = "compost";
        public static final String EMPTY = "empty";
        public static final String FLUID = "fluid";
        public static final String MOB = "mob";
        public static final String TRANSFORM = "transform";

        private BarrelModes() {
        }
    }

    public static class Blocks {

        public static final String ACACIA_BARREL = "acacia_barrel";
        public static final String ACACIA_CRUCIBLE = "acacia_crucible";
        public static final String ACACIA_SIEVE = "acacia_sieve";
        public static final String BARRELS = "barrels";
        public static final String BIRCH_BARREL = "birch_barrel";
        public static final String BIRCH_CRUCIBLE = "birch_crucible";
        public static final String BIRCH_SIEVE = "birch_sieve";
        public static final String CRIMSON_BARREL = "crimson_barrel";
        public static final String CRIMSON_CRUCIBLE = "crimson_crucible";
        public static final String CRIMSON_SIEVE = "crimson_sieve";
        public static final String CRUCIBLES = "crucibles";
        public static final String CRUSHED_ANDESITE = "crushed_andesite";
        public static final String CRUSHED_DIORITE = "crushed_diorite";
        public static final String CRUSHED_END_STONE = "crushed_end_stone";
        public static final String CRUSHED_GRANITE = "crushed_granite";
        public static final String CRUSHED_NETHERRACK = "crushed_netherrack";
        public static final String DARK_OAK_BARREL = "dark_oak_barrel";
        public static final String DARK_OAK_CRUCIBLE = "dark_oak_crucible";
        public static final String DARK_OAK_SIEVE = "dark_oak_sieve";
        public static final String DUST = "dust";
        public static final String END_CAKE = "end_cake";
        public static final String FIRED_CRUCIBLE = "fired_crucible";
        public static final String INFESTED_LEAVES = "infested_leaves";
        public static final String INFESTING_LEAVES = "infesting_leaves";
        public static final String JUNGLE_BARREL = "jungle_barrel";
        public static final String JUNGLE_CRUCIBLE = "jungle_crucible";
        public static final String JUNGLE_SIEVE = "jungle_sieve";
        public static final String OAK_BARREL = "oak_barrel";
        public static final String OAK_CRUCIBLE = "oak_crucible";
        public static final String OAK_SIEVE = "oak_sieve";
        public static final String SIEVES = "sieves";
        public static final String SPRUCE_BARREL = "spruce_barrel";
        public static final String SPRUCE_CRUCIBLE = "spruce_crucible";
        public static final String SPRUCE_SIEVE = "spruce_sieve";
        public static final String STONE_BARREL = "stone_barrel";
        public static final String UNFIRED_CRUCIBLE = "unfired_crucible";
        public static final String WARPED_BARREL = "warped_barrel";
        public static final String WARPED_CRUCIBLE = "warped_crucible";
        public static final String WARPED_SIEVE = "warped_sieve";

        private Blocks() {
        }
    }

    public static class Fluids {

        public static final String SEA_WATER_STILL = "sea_water_still";
        public static final String SEA_WATER_FLOW = "sea_water_flow";
        public static final String WITCH_WATER_STILL = "witch_water_still";
        public static final String WITCH_WATER_FLOW = "witch_water_flow";

        private Fluids() {
        }
    }

    public static class Items {

        public static final String ANCIENT_SPORE = "ancient_spores";
        public static final String ANDESITE_CROOK = createCrookName("andesite");
        public static final String ANDESITE_PEBBLE = createPebbleName("andesite");
        public static final String BASALT_PEBBLE = createPebbleName("basalt");
        public static final String BEEHIVE_FRAME = "beehive_frame";
        public static final String BLACKSTONE_PEBBLE = createPebbleName("blackstone");
        public static final String BLAZE_CROOK = createCrookName("blaze");
        public static final String BLUE_CORAL_LARVAE = "blue_coral_larvae";
        public static final String BONE_CROOK = createCrookName("bone");
        public static final String CLAY_CROOK = createCrookName("clay");
        public static final String CLAY_UNCOOKED_CROOK = createCrookName("clay_uncooked");
        public static final String COOKED_SILKWORM = "cooked_silkworm";
        public static final String CRAFTING_DOLL = "crafting_doll";
        public static final String DIAMOND_CROOK = createCrookName("diamond");
        public static final String DIAMOND_HAMMER = createHammerName("diamond");
        public static final String DIORITE_CROOK = createCrookName("diorite");
        public static final String DIORITE_PEBBLE = createPebbleName("diorite");
        public static final String GOLD_CROOK = createCrookName("gold");
        public static final String GOLD_HAMMER = createHammerName("gold");
        public static final String GRANITE_CROOK = createCrookName("granite");
        public static final String GRANITE_PEBBLE = createPebbleName("granite");
        public static final String GRASS_SEED = "grass_seeds";
        public static final String IRON_CROOK = createCrookName("iron");
        public static final String IRON_HAMMER = createHammerName("iron");
        public static final String NETHERITE_HAMMER = createHammerName("netherite");
        public static final String NETHERRACK_CROOK = createCrookName("netherrack");
        public static final String PINK_CORAL_LARVAE = "pink_coral_larvae";
        public static final String PORCELAIN_CLAY = "porcelain_clay";
        public static final String PRISMARINE_CROOK = createCrookName("prismarine");
        public static final String PURPLE_CORAL_LARVAE = "purple_coral_larvae";
        public static final String PURPUR_CROOK = createCrookName("purpur");
        public static final String RED_CORAL_LARVAE = "red_coral_larvae";
        public static final String SEA_WATER_BUCKET = "sea_water_bucket";
        public static final String SILKWORM = "silkworm";
        public static final String STONE_CROOK = createCrookName(STONE_TAG);
        public static final String STONE_HAMMER = createHammerName(STONE_TAG);
        public static final String STONE_PEBBLE = createPebbleName(STONE_TAG);
        public static final String STONE_STICK = "stone_stick";
        public static final String WITCH_WATER_BUCKET = "witch_water_bucket";
        public static final String WOOD_CROOK = createCrookName("wood");
        public static final String WOOD_HAMMER = createHammerName("wood");
        public static final String YELLOW_CORAL_LARVAE = "yellow_coral_larvae";

        private Items() {
        }

        private static String createCrookName(String name) {
            return name + "_crook";
        }

        private static String createHammerName(String name) {
            return name + "_hammer";
        }

        private static String createPebbleName(String name) {
            return name + "_pebble";
        }
    }

    public static class ModIds {
        //Ex Nihilo
        public static final String EX_NIHILO_SEQUENTIA = "exnihilosequentia";

        //Others
        public static final String CRAFT_TWEAKER = "crafttweaker";
        public static final String CREATE = "create";
        public static final String IMMERSIVE_ENGINEERING = "immersiveengineering";
        public static final String JEI = "jei";
        public static final String MINECRAFT = "minecraft";
        public static final String SILENT_MECHANISM = "silent_mechanism";
        public static final String THERMAL_EXPANSION = "thermalexpansion";
        public static final String TOP = "theoneprobe";

        private ModIds() {
        }
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

        private Ore() {
        }
    }

    public static class Tooltips {

        public static final String BEE = "tooltip.doll.bee";
        public static final String BLAZE = "tooltip.doll.blaze";
        public static final String ENDERMAN = "tooltip.doll.enderman";
        public static final String GUARDIAN = "tooltip.doll.guardian";
        public static final String SHULKER = "tooltip.doll.shulker";

        private Tooltips() {
        }
    }
}
