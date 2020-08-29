package com.novamachina.exnihilosequentia.common.utility;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.Builder;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

import java.nio.file.Path;

@Mod.EventBusSubscriber
public class Config {

    private static final String CATEGORY_BARREL = "barrel";
    private static final String CATEGORY_CROOK = "crook";
    private static final String CATEGORY_CRUCIBLE = "crucible";
    private static final String CATEGORY_INFESTED_LEAVES = "infested_leaves";
    private static final String CATEGORY_REGISTRY = "registry";
    private static final String CATEGORY_SIEVE = "sieve";
    private static final String CATEGORY_COMPAT = "compatibility";
    private static final String SUBCATEGORY_BARREL_MOB = "mob_spawn";
    private static final String SUBCATEGORY_BARREL_COMPOST = "compost";
    private static final String SUBCATEGORY_BARREL_FLUID = "fluid_transform";
    private static final String SUBCATEGORY_CRUCIBLE_WOOD = "wood";

    private static final ForgeConfigSpec.Builder COMMON_BUILDER = new Builder();

    public static ForgeConfigSpec COMMON_CONFIG;

    // Barrel
    public static ForgeConfigSpec.IntValue SECONDS_TO_SPAWN;
    public static ForgeConfigSpec.IntValue BARREL_MAX_SOLID_AMOUNT;
    public static ForgeConfigSpec.IntValue SECONDS_TO_COMPOST;
    public static ForgeConfigSpec.IntValue SECONDS_TO_FLUID_TRANSFORM;
    public static ForgeConfigSpec.IntValue BARREL_NUMBER_OF_BUCKETS;
    public static ForgeConfigSpec.IntValue RAIN_FILL_AMOUNT;

    // Crook
    public static ForgeConfigSpec.IntValue MAX_BONUS_STRING_COUNT;
    public static ForgeConfigSpec.IntValue MIN_STRING_COUNT;
    public static ForgeConfigSpec.IntValue VANILLA_SIMULATE_DROP_COUNT;

    // Crucible
    public static ForgeConfigSpec.IntValue TICKS_BETWEEN_MELTS;
    public static ForgeConfigSpec.IntValue CRUCIBLE_NUMBER_OF_BUCKETS;
    public static ForgeConfigSpec.IntValue WOOD_HEAT_RATE;

    // Infested Leaves
    public static ForgeConfigSpec.IntValue SECONDS_TO_TRANSFORM_LEAVES;
    public static ForgeConfigSpec.DoubleValue SPREAD_CHANCE;
    public static ForgeConfigSpec.IntValue TICKS_BETWEEN_SPREAD_ATTEMPT;

    // Registry
    public static ForgeConfigSpec.BooleanValue USE_JSON_REGISTRIES;

    // Sieve
    public static ForgeConfigSpec.BooleanValue FLATTEN_SIEVE_RECIPES;
    public static ForgeConfigSpec.IntValue SIEVE_RANGE;

    // Compat
    public static ForgeConfigSpec.BooleanValue ENABLE_THERMAL;
    public static ForgeConfigSpec.BooleanValue ENABLE_IMMERSIVE;
    public static ForgeConfigSpec.BooleanValue ENABLE_MEKANISM;

    static {
        COMMON_BUILDER.comment("Barrel Configs").push(CATEGORY_BARREL);
        barrelConfigs();
        COMMON_BUILDER.pop();
        COMMON_BUILDER.comment("Crook Configs").push(CATEGORY_CROOK);
        crookConfigs();
        COMMON_BUILDER.pop();
        COMMON_BUILDER.comment("Crucible Configs").push(CATEGORY_CRUCIBLE);
        crucibleConfigs();
        COMMON_BUILDER.pop();
        COMMON_BUILDER.comment("Infested Leaves Configs").push(CATEGORY_INFESTED_LEAVES);
        infestedLeavesConfigs();
        COMMON_BUILDER.pop();
        COMMON_BUILDER.comment("Registry Configs").push(CATEGORY_REGISTRY);
        registryConfigs();
        COMMON_BUILDER.pop();
        COMMON_BUILDER.comment("Sieve Configs").push(CATEGORY_SIEVE);
        sieveConfigs();
        COMMON_BUILDER.pop();
        COMMON_BUILDER.comment("Compatibility Configs").push(CATEGORY_COMPAT);
        compatConfigs();
        COMMON_BUILDER.pop();

        COMMON_CONFIG = COMMON_BUILDER.build();
    }

    private static void compatConfigs() {
        ENABLE_THERMAL = COMMON_BUILDER.comment("Should Thermal Expansion ores be enabled? (Default: false)")
            .define("enableThermalExpansion", false);
        ENABLE_IMMERSIVE = COMMON_BUILDER.comment("Should Immersive Engineering ores be enabled? (Default: false)")
            .define("enableImmersiveEngineering", false);
        ENABLE_MEKANISM = COMMON_BUILDER.comment("Should Mekanism ores be enabled? (Default: false)")
            .define("enableImmersiveEngineering", false);
    }

    private static void sieveConfigs() {
        FLATTEN_SIEVE_RECIPES = COMMON_BUILDER
            .comment("Sieve will get results for all mesh tiers below the one in the sieve (Default: true)")
            .define("flattenSieveRecipes", true);
        SIEVE_RANGE = COMMON_BUILDER
            .comment("Defines the radius that a sieve will attempt to activate other sieves (Default: 2)")
            .defineInRange("sieveRange", 2, 0, 5);
    }

    private static void registryConfigs() {
        USE_JSON_REGISTRIES = COMMON_BUILDER
            .comment("Should use JSON registries. Will generate JSON files if they do not exist. (Default: false)")
            .define("useJson", false);
    }

    private static void infestedLeavesConfigs() {
        SECONDS_TO_TRANSFORM_LEAVES = COMMON_BUILDER
            .comment("Number of seconds to for leaves to become completely infested (Default: 10)")
            .defineInRange("secondsToTransformLeaves", 10, 1, Integer.MAX_VALUE);
        SPREAD_CHANCE = COMMON_BUILDER.comment("Percentage of the time that infested leaves will spread (Default: 0.3)")
            .defineInRange("spreadChance", 0.3, 0.001, 1.0);
        TICKS_BETWEEN_SPREAD_ATTEMPT = COMMON_BUILDER
            .comment("Number of ticks between infested leave spread attempts (Default: 100)")
            .defineInRange("ticksBetweenSpreadAttempt", 100, 1, Integer.MAX_VALUE);
    }

    private static void crucibleConfigs() {
        TICKS_BETWEEN_MELTS = COMMON_BUILDER.comment("Ticks between melting operations (Default: 20)")
            .defineInRange("ticksBetweenMelts", 20, 1, Integer.MAX_VALUE);
        CRUCIBLE_NUMBER_OF_BUCKETS = COMMON_BUILDER.comment("Number of buckets the crucible will hold (Default: 4)")
            .defineInRange("crucibleNumberOfBuckets", 4, 1, Integer.MAX_VALUE);

        COMMON_BUILDER.comment("Wooden Crucible Configs").push(SUBCATEGORY_CRUCIBLE_WOOD);
        WOOD_HEAT_RATE = COMMON_BUILDER
            .comment("Heat rate the Wood Crucible will use regardless of heat source below (Default: 2)")
            .defineInRange("woodHeatRate", 2, 1, Integer.MAX_VALUE);
        COMMON_BUILDER.pop();
    }

    private static void crookConfigs() {
        MAX_BONUS_STRING_COUNT = COMMON_BUILDER
            .comment("Maximum additional string that a crook will drop from infested leaves in addition to the minimum string count (Default: 3)")
            .defineInRange("maxBonusStringCount", 3, 0, Integer.MAX_VALUE);
        MIN_STRING_COUNT = COMMON_BUILDER
            .comment("Minimum string that a crook will drop from infested leaves (Default: 2)")
            .defineInRange("minStringCount", 2, 1, Integer.MAX_VALUE);
        VANILLA_SIMULATE_DROP_COUNT = COMMON_BUILDER
            .comment("Number of times the crook will \"break\" a leaf block to get drops (Default: 3)")
            .defineInRange("vanillaDropSimulateCount", 3, 1, Integer.MAX_VALUE);
    }

    private static void barrelConfigs() {
        BARREL_NUMBER_OF_BUCKETS = COMMON_BUILDER.comment("Number of buckets the barrel will hold (Default: 1)")
            .defineInRange("barrelNumberOfBuckets", 1, 1, Integer.MAX_VALUE);
        RAIN_FILL_AMOUNT = COMMON_BUILDER.comment("How much fluid rain will fill per iteration (Default: 2)")
            .defineInRange("rainFillAmount", 2, 1, Integer.MAX_VALUE);

        COMMON_BUILDER.comment("Mob Spawn Configs").push(SUBCATEGORY_BARREL_MOB);
        SECONDS_TO_SPAWN = COMMON_BUILDER.comment("Number of seconds to spawn mobs (Default: 10)")
            .defineInRange("secondsToSpawnMobs", 10, 1, Integer.MAX_VALUE);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("Compost Configs").push(SUBCATEGORY_BARREL_COMPOST);
        BARREL_MAX_SOLID_AMOUNT = COMMON_BUILDER
            .comment("How much solids need to be in barrel before composting starts (Default: 1000)")
            .defineInRange("maxSolidAmount", 1000, 1, Integer.MAX_VALUE);
        SECONDS_TO_COMPOST = COMMON_BUILDER.comment("Number of seconds to spawn mobs (Default: 10)")
            .defineInRange("secondsToCompost", 10, 1, Integer.MAX_VALUE);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("Fluid Transform Configs").push(SUBCATEGORY_BARREL_FLUID);
        SECONDS_TO_FLUID_TRANSFORM = COMMON_BUILDER.comment("Number of seconds to transform fluids (Default: 10)")
            .defineInRange("secondsToTransformFluid", 10, 1, Integer.MAX_VALUE);
        COMMON_BUILDER.pop();
    }

    public static void loadConfig(ForgeConfigSpec spec, Path path) {
        final CommentedFileConfig configData = CommentedFileConfig.builder(path).sync().autosave()
            .writingMode(
                WritingMode.REPLACE).build();

        configData.load();
        spec.setConfig(configData);
    }

    @SubscribeEvent
    public static void onLoad(final ModConfig.Loading configEvent) {
    }

    @SubscribeEvent
    public static void onReload(final ModConfig.Reloading configEvent) {
    }
}
