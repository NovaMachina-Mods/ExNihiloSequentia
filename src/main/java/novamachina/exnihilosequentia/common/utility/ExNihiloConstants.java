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

        @Nonnull public static final String BARREL_STONE = "barrel_stone";
        @Nonnull public static final String BARRELS = "barrels";
        @Nonnull public static final String BARREL_ACACIA = "acacia_barrel";
        @Nonnull public static final String BARREL_BIRCH = "birch_barrel";
        @Nonnull public static final String BARREL_DARK_OAK = "dark_oak_barrel";
        @Nonnull public static final String BARREL_JUNGLE = "jungle_barrel";
        @Nonnull public static final String BARREL_OAK = "barrel_wood";
        @Nonnull public static final String BARREL_SPRUCE = "spruce_barrel";
        @Nonnull public static final String BARREL_CRIMSON = "crimson_barrel";
        @Nonnull public static final String BARREL_WARPED = "warped_barrel";
        @Nonnull public static final String CRUCIBLE_FIRED = "crucible_fired";
        @Nonnull public static final String CRUCIBLE_UNFIRED = "crucible_unfired";
        @Nonnull public static final String CRUCIBLES = "crucibles";
        @Nonnull public static final String CRUCIBLE_ACACIA = "acacia_crucible";
        @Nonnull public static final String CRUCIBLE_BIRCH = "birch_crucible";
        @Nonnull public static final String CRUCIBLE_DARK_OAK = "dark_oak_crucible";
        @Nonnull public static final String CRUCIBLE_JUNGLE = "jungle_crucible";
        @Nonnull public static final String CRUCIBLE_OAK = "crucible_wood";
        @Nonnull public static final String CRUCIBLE_SPRUCE = "spruce_crucible";
        @Nonnull public static final String CRUCIBLE_CRIMSON = "crimson_crucible";
        @Nonnull public static final String CRUCIBLE_WARPED = "warped_crucible";
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
        @Nonnull public static final String SIEVE_ACACIA = "acacia_sieve";
        @Nonnull public static final String SIEVE_BIRCH = "birch_sieve";
        @Nonnull public static final String SIEVE_DARK_OAK = "dark_oak_sieve";
        @Nonnull public static final String SIEVE_JUNGLE = "jungle_sieve";
        @Nonnull public static final String SIEVE_OAK = "sieve";
        @Nonnull public static final String SIEVE_SPRUCE = "spruce_sieve";
        @Nonnull public static final String SIEVE_CRIMSON = "crimson_sieve";
        @Nonnull public static final String SIEVE_WARPED = "warped_sieve";
        @Nonnull public static final String WITCH_WATER = "witchwater";

        private Blocks() {
        }
    }

    public static class Fluids {

        @Nonnull public static final String SEA_WATER = "sea_water";
        @Nonnull public static final String SEA_WATER_FLOW = "sea_water_flow";
        @Nonnull public static final String WITCH_WATER = "witchwater";
        @Nonnull public static final String WITCH_WATER_FLOW = "witchwater_flow";

        private Fluids() {
        }
    }

    public static class Items {

        @Nonnull public static final String ANCIENT_SPORE = "ancient_spores";
        @Nonnull public static final String BEEHIVE_FRAME = "beehive_frame";
        @Nonnull public static final String BLUE_CORAL_SEED = "seed_blue_coral";
        @Nonnull public static final String COOKED_SILKWORM = "cooked_silkworm";
        @Nonnull public static final String CRAFTING_DOLL = "doll_crafting";
        @Nonnull public static final String CROOK_ANDESITE = createCrookName("andesite");
        @Nonnull public static final String CROOK_BLAZE = createCrookName("blaze");
        @Nonnull public static final String CROOK_BONE = createCrookName("bone");
        @Nonnull public static final String CROOK_CLAY = createCrookName("clay");
        @Nonnull public static final String CROOK_CLAY_UNCOOKED = createCrookName("clay_uncooked");
        @Nonnull public static final String CROOK_DIAMOND = createCrookName("diamond");
        @Nonnull public static final String CROOK_DIORITE = createCrookName("diorite");
        @Nonnull public static final String CROOK_GOLD = createCrookName("gold");
        @Nonnull public static final String CROOK_GRANITE = createCrookName("granite");
        @Nonnull public static final String CROOK_IRON = createCrookName("iron");
        @Nonnull public static final String CROOK_NETHERRACK = createCrookName("netherrack");
        @Nonnull public static final String CROOK_PRISMARINE = createCrookName("prismarine");
        @Nonnull public static final String CROOK_PURPUR = createCrookName("purpur");
        @Nonnull public static final String CROOK_STONE = createCrookName(STONE_TAG);
        @Nonnull public static final String CROOK_WOOD = createCrookName("wood");
        @Nonnull public static final String GRASS_SEED = "grass_seeds";
        @Nonnull public static final String HAMMER_DIAMOND = createHammerName("diamond");
        @Nonnull public static final String HAMMER_GOLD = createHammerName("gold");
        @Nonnull public static final String HAMMER_IRON = createHammerName("iron");
        @Nonnull public static final String HAMMER_NETHERITE = createHammerName("netherite");
        @Nonnull public static final String HAMMER_STONE = createHammerName(STONE_TAG);
        @Nonnull public static final String HAMMER_WOOD = createHammerName("wood");
        @Nonnull public static final String PEBBLE_ANDESITE = createPebbleName("andesite");
        @Nonnull public static final String PEBBLE_DIORITE = createPebbleName("diorite");
        @Nonnull public static final String PEBBLE_GRANITE = createPebbleName("granite");
        @Nonnull public static final String PEBBLE_STONE = createPebbleName(STONE_TAG);
        @Nonnull public static final String PEBBLE_BASALT = createPebbleName("basalt");
        @Nonnull public static final String PEBBLE_BLACKSTONE = createPebbleName("blackstone");
        @Nonnull public static final String PINK_CORAL_SEED = "seed_pink_coral";
        @Nonnull public static final String PORCELAIN_CLAY = "porcelain_clay";
        @Nonnull public static final String PURPLE_CORAL_SEED = "seed_purple_coral";
        @Nonnull public static final String RED_CORAL_SEED = "seed_red_coral";
        @Nonnull public static final String SEA_WATER_BUCKET = "bucket_sea_water";
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
        @Nonnull public static final String WITCH_WATER_BUCKET = "bucket_witchwater";
        @Nonnull public static final String YELLOW_CORAL_SEED = "seed_yellow_coral";
        @Nonnull public static final String SEED_FERN = "fern";
        @Nonnull public static final String SEED_LARGE_FERN = "large_fern";
        @Nonnull public static final String STONE_STICK = "item_stick_stone";

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
