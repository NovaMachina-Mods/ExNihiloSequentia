package novamachina.exnihilosequentia.common.utility;

public class ExNihiloConstants {

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

        public static final String BARRELS = "barrels";
        public static final String CRUCIBLES = "crucibles";
        public static final String SIEVES = "sieves";
        private static final String BARREL = "_barrel";
        private static final String CRUCIBLE = "_crucible";
        private static final String SIEVE = "_sieve";
        private static final String CRUSHED = "crushed_";

        public static final String ACACIA_BARREL = "acacia" + BARREL;
        public static final String ACACIA_CRUCIBLE = "acacia" + CRUCIBLE;
        public static final String ACACIA_SIEVE = "acacia" + SIEVE;
        public static final String BIRCH_BARREL = "birch" + BARREL;
        public static final String BIRCH_CRUCIBLE = "birch" + CRUCIBLE;
        public static final String BIRCH_SIEVE = "birch" + SIEVE;
        public static final String CRIMSON_BARREL = "crimson" + BARREL;
        public static final String CRIMSON_CRUCIBLE = "crimson" + CRUCIBLE;
        public static final String CRIMSON_SIEVE = "crimson" + SIEVE;
        public static final String CRUSHED_ANDESITE = CRUSHED + "andesite";
        public static final String CRUSHED_DIORITE = CRUSHED + "diorite";
        public static final String CRUSHED_END_STONE = CRUSHED + "end_stone";
        public static final String CRUSHED_GRANITE = CRUSHED + "granite";
        public static final String CRUSHED_NETHERRACK = CRUSHED + "netherrack";
        public static final String DARK_OAK_BARREL = "dark_oak" + BARREL;
        public static final String DARK_OAK_CRUCIBLE = "dark_oak" + CRUCIBLE;
        public static final String DARK_OAK_SIEVE = "dark_oak" + SIEVE;
        public static final String DUST = "dust";
        public static final String END_CAKE = "end_cake";
        public static final String FIRED_CRUCIBLE = "fired" + CRUCIBLE;
        public static final String INFESTED_LEAVES = "infested_leaves";
        public static final String INFESTING_LEAVES = "infesting_leaves";
        public static final String JUNGLE_BARREL = "jungle" + BARREL;
        public static final String JUNGLE_CRUCIBLE = "jungle" + CRUCIBLE;
        public static final String JUNGLE_SIEVE = "jungle" + SIEVE;
        public static final String OAK_BARREL = "oak" + BARREL;
        public static final String OAK_CRUCIBLE = "oak" + CRUCIBLE;
        public static final String OAK_SIEVE = "oak" + SIEVE;
        public static final String SPRUCE_BARREL = "spruce" + BARREL;
        public static final String SPRUCE_CRUCIBLE = "spruce" + CRUCIBLE;
        public static final String SPRUCE_SIEVE = "spruce" + SIEVE;
        public static final String STONE_BARREL = "stone" + BARREL;
        public static final String UNFIRED_CRUCIBLE = "unfired" + CRUCIBLE;
        public static final String WARPED_BARREL = "warped" + BARREL;
        public static final String WARPED_CRUCIBLE = "warped" + CRUCIBLE;
        public static final String WARPED_SIEVE = "warped" + SIEVE;

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
        private static final String CROOK = "_crook";
        private static final String HAMMER = "_hammer";
        private static final String PEBBLE = "_pebble";

        public static final String ANCIENT_SPORE = "ancient_spores";
        public static final String ANDESITE_CROOK = "andesite" + CROOK;
        public static final String ANDESITE_PEBBLE = "andesite" + PEBBLE;
        public static final String BASALT_PEBBLE = "basalt" + PEBBLE;
        public static final String BEEHIVE_FRAME = "beehive_frame";
        public static final String BLACKSTONE_PEBBLE = "blackstone" + PEBBLE;
        public static final String BLAZE_CROOK = "blaze" + CROOK;
        public static final String BLUE_CORAL_LARVAE = "blue_coral_larvae";
        public static final String BONE_CROOK = "bone" + CROOK;
        public static final String CLAY_CROOK = "clay" + CROOK;
        public static final String CLAY_UNCOOKED_CROOK = "clay_uncooked" + CROOK;
        public static final String COOKED_SILKWORM = "cooked_silkworm";
        public static final String CRAFTING_DOLL = "doll_crafting";
        public static final String DIAMOND_CROOK = "diamond" + CROOK;
        public static final String DIAMOND_HAMMER = "diamond" + HAMMER;
        public static final String DIORITE_CROOK = "diorite" + CROOK;
        public static final String DIORITE_PEBBLE = "diorite" + PEBBLE;
        public static final String GOLD_CROOK = "gold" + CROOK;
        public static final String GOLD_HAMMER = "gold" + HAMMER;
        public static final String GRANITE_CROOK = "granite" + CROOK;
        public static final String GRANITE_PEBBLE = "granite" + PEBBLE;
        public static final String GRASS_SEED = "grass_seeds";
        public static final String IRON_CROOK = "iron" + CROOK;
        public static final String IRON_HAMMER = "iron" + HAMMER;
        public static final String NETHERITE_HAMMER = "netherite" + HAMMER;
        public static final String NETHERRACK_CROOK = "netherrack" + CROOK;
        public static final String PINK_CORAL_LARVAE = "pink_coral_larvae";
        public static final String PORCELAIN_CLAY = "porcelain_clay";
        public static final String PRISMARINE_CROOK = "prismarine" + CROOK;
        public static final String PURPLE_CORAL_LARVAE = "purple_coral_larvae";
        public static final String PURPUR_CROOK = "purpur" + CROOK;
        public static final String RED_CORAL_LARVAE = "red_coral_larvae";
        public static final String SEA_WATER_BUCKET = "sea_water_bucket";
        public static final String SILKWORM = "silkworm";
        public static final String STONE_CROOK = "stone" + CROOK;
        public static final String STONE_HAMMER = "stone" + HAMMER;
        public static final String STONE_PEBBLE = "stone" + PEBBLE;
        public static final String STONE_STICK = "stone_stick";
        public static final String WITCH_WATER_BUCKET = "witch_water_bucket";
        public static final String WOOD_CROOK = "wood" + CROOK;
        public static final String WOOD_HAMMER = "wood" + HAMMER;
        public static final String YELLOW_CORAL_LARVAE = "yellow_coral_larvae";

        private Items() {
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
