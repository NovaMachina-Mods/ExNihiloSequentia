package com.novamachina.ens.common.utility;

public class Constants {

    public static class ModInfo {

        public static final String MOD_ID = "ens";
    }

    public static class Blocks {

        public static final String CRUSHED_ANDESITE   = createBlockName("crushed_andesite");
        public static final String CRUSHED_DIORITE    = createBlockName("crushed_diorite");
        public static final String CRUSHED_END_STONE  = createBlockName("crushed_end_stone");
        public static final String CRUSHED_GRANITE    = createBlockName("crushed_granite");
        public static final String CRUSHED_NETHERRACK = createBlockName("crushed_netherrack");
        public static final String DUST               = createBlockName("dust");

        private static String createBlockName(String name) {
            return "block_" + name;
        }
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


        private static String createCrookName(String name) {
            return createItemName(name) + "_crook";
        }

        private static String createItemName(String name) {
            return "item_" + name;
        }


    }
}
