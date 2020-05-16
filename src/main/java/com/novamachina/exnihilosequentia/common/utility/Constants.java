package com.novamachina.exnihilosequentia.common.utility;

public class Constants {

    public static class ModInfo {

        public static final String MOD_ID = "exnihilosequentia";
    }

    public static class Blocks {

        public static final String CRUSHED_ANDESITE   = "crushed_andesite";
        public static final String CRUSHED_DIORITE    = "crushed_diorite";
        public static final String CRUSHED_END_STONE  = "crushed_end_stone";
        public static final String CRUSHED_GRANITE    = "crushed_granite";
        public static final String CRUSHED_NETHERRACK = "crushed_netherrack";
        public static final String DUST               = "dust";
        public static final String END_CAKE           = "end_cake";
        public static final String SIEVE              = "sieve";
        public static final String WITCH_WATER        = "witchwater";
    }

    public static class Items {

        public static final String CROOK_WOOD          = createCrookName("wood");
        public static final String CROOK_STONE         = createCrookName("stone");
        public static final String CROOK_ANDESITE      = createCrookName("andesite");
        public static final String CROOK_GRANITE       = createCrookName("granite");
        public static final String CROOK_DIORITE       = createCrookName("diorite");
        public static final String CROOK_GOLD          = createCrookName("gold");
        public static final String CROOK_IRON          = createCrookName("iron");
        public static final String CROOK_DIAMOND       = createCrookName("diamond");
        public static final String CROOK_BONE          = createCrookName("bone");
        public static final String CROOK_CLAY_UNCOOKED = createCrookName("clay_uncooked");
        public static final String CROOK_CLAY          = createCrookName("clay");
        public static final String CROOK_PRISMARINE    = createCrookName("prismarine");
        public static final String CROOK_NETHERRACK    = createCrookName("netherrack");
        public static final String CROOK_PURPUR        = createCrookName("purpur");
        public static final String CROOK_BLAZE         = createCrookName("blaze");
        public static final String SILKWORM            = "silkworm";
        public static final String HAMMER_WOOD         = createHammername("wood");
        public static final String HAMMER_STONE        = createHammername("stone");
        public static final String HAMMER_IRON         = createHammername("iron");
        public static final String HAMMER_DIAMOND     = createHammername("diamond");
        public static final String HAMMER_GOLD        = createHammername("gold");
        public static final String SEED_OAK           = "oak";
        public static final String SEED_SPRUCE        = "spruce";
        public static final String SEED_BIRCH         = "birch";
        public static final String SEED_JUNGLE        = "jungle";
        public static final String SEED_ACACIA        = "acacia";
        public static final String SEED_DARK_OAK      = "darkoak";
        public static final String SEED_CACTUS        = "cactus";
        public static final String SEEDSUGARCANE      = "sugarcane";
        public static final String SEED_CARROT        = "carrot";
        public static final String SEED_POTATO        = "potato";
        public static final String SEED_SWEET_BERRY   = "berry";
        public static final String ANCIENT_SPORE      = "ancient_spores";
        public static final String GRASS_SEED         = "grass_seeds";
        public static final String COOKED_SILKWORM    = "cooked_silkworm";
        public static final String PORCELAIN_CLAY     = "porcelain_clay";
        public static final String PEBBLE_STONE       = createPebbleName("stone");
        public static final String PEBBLE_GRANITE     = createPebbleName("granite");
        public static final String PEBBLE_DIORITE     = createPebbleName("diorite");
        public static final String PEBBLE_ANDESITE    = createPebbleName("andesite");
        public static final String WITCH_WATER_BUCKET = "bucket_witchwater";

        private static String createHammername(String name) {
            return "hammer_" + name;
        }

        private static String createCrookName(String name) {
            return "crook_" + name;
        }

        private static String createPebbleName(String name) {
            return "pebble_" + name;
        }
    }

    public class Ore {

        public static final String GOLD = "gold";
        public static final String IRON = "iron";
    }

    public class Fluids {

        public static final String WITCH_WATER_STILL = "witchwater_still";
        public static final String WITCH_WATER_FLOW  = "witchwater_flow";
    }
}
