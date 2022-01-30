package novamachina.exnihilosequentia.common.utility;

import javax.annotation.Nonnull;

public class ExNihiloConstants {

    @Nonnull private static final String STONE_TAG = "stone";

    private ExNihiloConstants() {
    }

    public static class BarrelModes {

        @Nonnull public static final String BLOCK = "block";
        @Nonnull public static final String COMPOST = "compost";
        @Nonnull public static final String EMPTY = "empty";
        @Nonnull public static final String FLUID = "fluid";
        @Nonnull public static final String MOB = "mob";
        @Nonnull public static final String TRANSFORM = "transform";

        private BarrelModes() {
        }
    }

    public static class Blocks {

        @Nonnull public static final String STONE_BARREL = "stone_barrel";
        @Nonnull public static final String BARRELS = "barrels";
        @Nonnull public static final String ACACIA_BARREL = "acacia_barrel";
        @Nonnull public static final String BIRCH_BARREL = "birch_barrel";
        @Nonnull public static final String DARK_OAK_BARREL = "dark_oak_barrel";
        @Nonnull public static final String JUNGLE_BARREL = "jungle_barrel";
        @Nonnull public static final String OAK_BARREL = "oak_barrel";
        @Nonnull public static final String SPRUCE_BARREL = "spruce_barrel";
        @Nonnull public static final String CRIMSON_BARREL = "crimson_barrel";
        @Nonnull public static final String WARPED_BARREL = "warped_barrel";
        @Nonnull public static final String FIRED_CRUCIBLE = "fired_crucible";
        @Nonnull public static final String UNFIRED_CRUCIBLE = "unfired_crucible";
        @Nonnull public static final String CRUCIBLES = "crucibles";
        @Nonnull public static final String ACACIA_CRUCIBLE = "acacia_crucible";
        @Nonnull public static final String BIRCH_CRUCIBLE = "birch_crucible";
        @Nonnull public static final String DARK_OAK_CRUCIBLE = "dark_oak_crucible";
        @Nonnull public static final String JUNGLE_CRUCIBLE = "jungle_crucible";
        @Nonnull public static final String OAK_CRUCIBLE = "oak_crucible";
        @Nonnull public static final String SPRUCE_CRUCIBLE = "spruce_crucible";
        @Nonnull public static final String CRIMSON_CRUCIBLE = "crimson_crucible";
        @Nonnull public static final String WARPED_CRUCIBLE = "warped_crucible";
        @Nonnull public static final String CRUSHED_ANDESITE = "crushed_andesite";
        @Nonnull public static final String CRUSHED_DIORITE = "crushed_diorite";
        @Nonnull public static final String CRUSHED_END_STONE = "crushed_end_stone";
        @Nonnull public static final String CRUSHED_GRANITE = "crushed_granite";
        @Nonnull public static final String CRUSHED_NETHERRACK = "crushed_netherrack";
        @Nonnull public static final String DUST = "dust";
        @Nonnull public static final String END_CAKE = "end_cake";
        @Nonnull public static final String INFESTED_LEAVES = "infested_leaves";
        @Nonnull public static final String INFESTING_LEAVES = "infesting_leaves";
        @Nonnull public static final String SEA_WATER = "sea_water";
        @Nonnull public static final String SIEVES = "sieves";
        @Nonnull public static final String ACACIA_SIEVE = "acacia_sieve";
        @Nonnull public static final String BIRCH_SIEVE = "birch_sieve";
        @Nonnull public static final String DARK_OAK_SIEVE = "dark_oak_sieve";
        @Nonnull public static final String JUNGLE_SIEVE = "jungle_sieve";
        @Nonnull public static final String OAK_SIEVE = "oak_sieve";
        @Nonnull public static final String SPRUCE_SIEVE = "spruce_sieve";
        @Nonnull public static final String CRIMSON_SIEVE = "crimson_sieve";
        @Nonnull public static final String WARPED_SIEVE = "warped_sieve";
        @Nonnull public static final String WITCH_WATER = "witch_water";

        private Blocks() {
        }
    }

    public static class Fluids {

        @Nonnull public static final String SEA_WATER_STILL = "sea_water_still";
        @Nonnull public static final String SEA_WATER_FLOW = "sea_water_flow";
        @Nonnull public static final String WITCH_WATER_STILL = "witch_water_still";
        @Nonnull public static final String WITCH_WATER_FLOW = "witch_water_flow";

        private Fluids() {
        }
    }

    public static class Items {

        @Nonnull public static final String ANCIENT_SPORE = "ancient_spores";
        @Nonnull public static final String BEEHIVE_FRAME = "beehive_frame";
        @Nonnull public static final String BLUE_CORAL_SEED = "seed_blue_coral";
        @Nonnull public static final String COOKED_SILKWORM = "cooked_silkworm";
        @Nonnull public static final String CRAFTING_DOLL = "doll_crafting";
        @Nonnull public static final String ANDESITE_CROOK = createCrookName("andesite");
        @Nonnull public static final String BLAZE_CROOK = createCrookName("blaze");
        @Nonnull public static final String BONE_CROOK = createCrookName("bone");
        @Nonnull public static final String CLAY_CROOK = createCrookName("clay");
        @Nonnull public static final String UNCOOKED_CLAY_CROOK = createCrookName("uncooked_clay");
        @Nonnull public static final String DIAMOND_CROOK = createCrookName("diamond");
        @Nonnull public static final String DIORITE_CROOK = createCrookName("diorite");
        @Nonnull public static final String GOLD_CROOK = createCrookName("gold");
        @Nonnull public static final String GRANITE_CROOK = createCrookName("granite");
        @Nonnull public static final String IRON_CROOK = createCrookName("iron");
        @Nonnull public static final String NETHERRACK_CROOK = createCrookName("netherrack");
        @Nonnull public static final String PRISMARINE_CROOK = createCrookName("prismarine");
        @Nonnull public static final String PURPUR_CROOK = createCrookName("purpur");
        @Nonnull public static final String STONE_CROOK = createCrookName(STONE_TAG);
        @Nonnull public static final String WOODEN_CROOK = createCrookName("wooden");
        @Nonnull public static final String GRASS_SEED = "grass_seeds";
        @Nonnull public static final String DIAMOND_HAMMER = createHammerName("diamond");
        @Nonnull public static final String GOLD_HAMMER = createHammerName("gold");
        @Nonnull public static final String IRON_HAMMER = createHammerName("iron");
        @Nonnull public static final String NETHERITE_HAMMER = createHammerName("netherite");
        @Nonnull public static final String STONE_HAMMER = createHammerName(STONE_TAG);
        @Nonnull public static final String WOODEN_HAMMER = createHammerName("wooden");
        @Nonnull public static final String ANDESITE_PEBBLE = createPebbleName("andesite");
        @Nonnull public static final String DIORITE_PEBBLE = createPebbleName("diorite");
        @Nonnull public static final String GRANITE_PEBBLE = createPebbleName("granite");
        @Nonnull public static final String STONE_PEBBLE = createPebbleName(STONE_TAG);
        @Nonnull public static final String BASALT_PEBBLE = createPebbleName("basalt");
        @Nonnull public static final String BLACKSTONE_PEBBLE = createPebbleName("blackstone");
        @Nonnull public static final String PINK_CORAL_SEED = "pink_coral_seed";
        @Nonnull public static final String PORCELAIN_CLAY = "porcelain_clay";
        @Nonnull public static final String PURPLE_CORAL_SEED = "purple_coral_seed";
        @Nonnull public static final String RED_CORAL_SEED = "red_coral_seed";
        @Nonnull public static final String SEA_WATER_BUCKET = "sea_water_bucket";
        @Nonnull public static final String SEED_ACACIA = "acacia";
        @Nonnull public static final String SEED_BAMBOO = "bamboo";
        @Nonnull public static final String SEED_BIRCH = "birch";
        @Nonnull public static final String SEED_CACTUS = "cactus";
        @Nonnull public static final String SEED_CARROT = "carrot";
        @Nonnull public static final String SEED_DARK_OAK = "darkoak";
        @Nonnull public static final String SEED_JUNGLE = "jungle";
        @Nonnull public static final String SEED_KELP = "kelp";
        @Nonnull public static final String SEED_OAK = "oak";
        @Nonnull public static final String SEED_PICKLE = "pickle";
        @Nonnull public static final String SEED_POTATO = "potato";
        @Nonnull public static final String SEED_SPRUCE = "spruce";
        @Nonnull public static final String SEED_SUGARCANE = "sugarcane";
        @Nonnull public static final String SEED_SWEET_BERRY = "berry";
        @Nonnull public static final String SILKWORM = "silkworm";
        @Nonnull public static final String WITCH_WATER_BUCKET = "witch_water_bucket";
        @Nonnull public static final String YELLOW_CORAL_SEED = "yellow_coral_seed";
        @Nonnull public static final String SEED_FERN = "fern";
        @Nonnull public static final String SEED_LARGE_FERN = "large_fern";

        private Items() {
        }

        @Nonnull
        private static String createCrookName(@Nonnull final String name) {
            return "crook_" + name;
        }

        @Nonnull
        private static String createHammerName(@Nonnull final String name) {
            return "hammer_" + name;
        }

        @Nonnull
        private static String createPebbleName(@Nonnull final String name) {
            return "pebble_" + name;
        }
    }

    public static class ModIds {

        @Nonnull public static final String CRAFT_TWEAKER = "crafttweaker";
        @Nonnull public static final String CREATE = "create";
        @Nonnull public static final String EX_NIHILO_SEQUENTIA = "exnihilosequentia";
        @Nonnull public static final String IMMERSIVE_ENGINEERING = "immersiveengineering";
        @Nonnull public static final String JEI = "jei";
        @Nonnull public static final String MINECRAFT = "minecraft";
        @Nonnull public static final String SILENT_MECHANISM = "silent_mechanism";
        @Nonnull public static final String THERMAL_EXPANSION = "thermalexpansion";
        @Nonnull public static final String TOP = "theoneprobe";

        private ModIds() {
        }
    }

    public static class Ore {

        @Nonnull public static final String ALUMINUM = "aluminum";
        @Nonnull public static final String BISMUTH = "bismuth";
        @Nonnull public static final String COPPER = "copper";
        @Nonnull public static final String GOLD = "gold";
        @Nonnull public static final String IRON = "iron";
        @Nonnull public static final String LEAD = "lead";
        @Nonnull public static final String NICKEL = "nickel";
        @Nonnull public static final String PLATINUM = "platinum";
        @Nonnull public static final String SILVER = "silver";
        @Nonnull public static final String TIN = "tin";
        @Nonnull public static final String URANIUM = "uranium";
        @Nonnull public static final String ZINC = "zinc";

        private Ore() {
        }
    }

    public static class Tooltips {

        @Nonnull public static final String BEE = "tooltip.doll.bee";
        @Nonnull public static final String BLAZE = "tooltip.doll.blaze";
        @Nonnull public static final String ENDERMAN = "tooltip.doll.enderman";
        @Nonnull public static final String GUARDIAN = "tooltip.doll.guardian";
        @Nonnull public static final String SHULKER = "tooltip.doll.shulker";

        private Tooltips() {
        }
    }
}
