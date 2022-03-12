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

        @Nonnull public static final String STONE_BARREL = createBarrelName(STONE_TAG);
        @Nonnull public static final String ACACIA_BARREL = createBarrelName("acacia");
        @Nonnull public static final String BIRCH_BARREL = createBarrelName("birch");
        @Nonnull public static final String DARK_OAK_BARREL = createBarrelName("dark_oak");
        @Nonnull public static final String JUNGLE_BARREL = createBarrelName("jungle");
        @Nonnull public static final String OAK_BARREL = createBarrelName("oak");
        @Nonnull public static final String SPRUCE_BARREL = createBarrelName("spruce");
        @Nonnull public static final String CRIMSON_BARREL = createBarrelName("crimson");
        @Nonnull public static final String WARPED_BARREL = createBarrelName("warped");
        @Nonnull public static final String FIRED_CRUCIBLE = createCrucibleName("fired");
        @Nonnull public static final String UNFIRED_CRUCIBLE = createCrucibleName("unfired");
        @Nonnull public static final String ACACIA_CRUCIBLE = createCrucibleName("acacia");
        @Nonnull public static final String BIRCH_CRUCIBLE = createCrucibleName("birch");
        @Nonnull public static final String DARK_OAK_CRUCIBLE = createCrucibleName("dark_oak");
        @Nonnull public static final String JUNGLE_CRUCIBLE = createCrucibleName("jungle");
        @Nonnull public static final String OAK_CRUCIBLE = createCrucibleName("oak");
        @Nonnull public static final String SPRUCE_CRUCIBLE = createCrucibleName("spruce");
        @Nonnull public static final String CRIMSON_CRUCIBLE = createCrucibleName("crimson");
        @Nonnull public static final String WARPED_CRUCIBLE = createCrucibleName("warped");
        @Nonnull public static final String CRUSHED_ANDESITE = createCrushedName("andesite");
        @Nonnull public static final String CRUSHED_DIORITE = createCrushedName("diorite");
        @Nonnull public static final String CRUSHED_END_STONE = createCrushedName("end_stone");
        @Nonnull public static final String CRUSHED_GRANITE = createCrushedName("granite");
        @Nonnull public static final String CRUSHED_NETHERRACK = createCrushedName("netherrack");
        @Nonnull public static final String DUST = "dust";
        @Nonnull public static final String END_CAKE = "end_cake";
        @Nonnull public static final String INFESTED_LEAVES = "infested_leaves";
        @Nonnull public static final String INFESTING_LEAVES = "infesting_leaves";
        @Nonnull public static final String SEA_WATER = "sea_water";
        @Nonnull public static final String ACACIA_SIEVE = createSieveName("acacia");
        @Nonnull public static final String BIRCH_SIEVE = createSieveName("birch");
        @Nonnull public static final String DARK_OAK_SIEVE = createSieveName("dark_oak");
        @Nonnull public static final String JUNGLE_SIEVE = createSieveName("jungle");
        @Nonnull public static final String OAK_SIEVE = createSieveName("oak");
        @Nonnull public static final String SPRUCE_SIEVE = createSieveName("spruce");
        @Nonnull public static final String CRIMSON_SIEVE = createSieveName("crimson");
        @Nonnull public static final String WARPED_SIEVE = createSieveName("warped");
        @Nonnull public static final String WITCH_WATER = "witch_water";

        private Blocks() {
        }

        @Nonnull
        private static String createCrushedName(@Nonnull final String name) { return "crushed_" + name; }

        @Nonnull
        private static String createBarrelName(@Nonnull final String name) { return name + "_barrel"; }

        @Nonnull
        private static String createCrucibleName(@Nonnull final String name) {
            return name + "_crucible";
        }

        @Nonnull
        private static String createSieveName(@Nonnull final String name) {
            return name + "_sieve";
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

        @Nonnull public static final String ANCIENT_SPORES = "ancient_spores";
        @Nonnull public static final String BEEHIVE_FRAME = "beehive_frame";
        @Nonnull public static final String BLUE_CORAL_SEED = "blue_coral_seed";
        @Nonnull public static final String COOKED_SILKWORM = "cooked_silkworm";
        @Nonnull public static final String CRAFTING_DOLL = "crafting_doll";
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
        @Nonnull public static final String GRASS_SEEDS = "grass_seeds";
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
        @Nonnull public static final String SILKWORM = "silkworm";
        @Nonnull public static final String WITCH_WATER_BUCKET = "witch_water_bucket";
        @Nonnull public static final String YELLOW_CORAL_SEED = "yellow_coral_seed";
        @Nonnull public static final String FERN_SEED = "fern";
        @Nonnull public static final String SEED_LARGE_FERN = "large_fern";

        private Items() {
        }

        @Nonnull
        private static String createCrookName(@Nonnull final String name) {
            return name + "_crook";
        }

        @Nonnull
        private static String createHammerName(@Nonnull final String name) {
            return name + "_hammer";
        }

        @Nonnull
        private static String createPebbleName(@Nonnull final String name) {
            return name + "_pebble";
        }
    }

    public static class ModIds {

        @Nonnull public static final String CRAFT_TWEAKER = "crafttweaker";
        @Nonnull public static final String CREATE = "create";
        @Nonnull public static final String EX_NIHILO_SEQUENTIA = "exnihilosequentia";
        @Nonnull public static final String IMMERSIVE_ENGINEERING = "immersiveengineering";
        @Nonnull public static final String JEI = "jei";
        @Nonnull public static final String MINECRAFT = "minecraft";
        @Nonnull public static final String TOP = "theoneprobe";

        private ModIds() {
        }
    }

    public static class Ore {

        @Nonnull public static final String ALUMINUM = "aluminum";
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

    public static class BlockEntities {

        @Nonnull public static final String FIRED_CRUCIBLES = "fired_crucibles";
        @Nonnull public static final String SIEVES = "sieves";
        @Nonnull public static final String FIRED_BARRELS = "fired_barrels";
        @Nonnull public static final String BARRELS = "barrels";
        @Nonnull public static final String CRUCIBLES = "crucibles";

        private BlockEntities(){}
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
