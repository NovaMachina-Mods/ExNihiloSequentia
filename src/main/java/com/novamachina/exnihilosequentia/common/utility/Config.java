package com.novamachina.exnihilosequentia.common.utility;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import java.nio.file.Path;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.Builder;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod.EventBusSubscriber
public class Config {

    private static final String CATEGORY_MAIN                       = "main";
    private static final String SUBCATEGORY_REGISTRY_OPTIONS        = "registry";
    private static final String SUBCATEGORY_CROOK_OPTIONS           = "crook";
    private static final String SUBCATEGORY_INFESTED_LEAVES_OPTIONS = "infested_leaves";

    private static final ForgeConfigSpec.Builder COMMON_BUILDER = new Builder();

    public static ForgeConfigSpec COMMON_CONFIG;

    public static ForgeConfigSpec.BooleanValue USE_JSON_REGISTRIES;
    public static ForgeConfigSpec.IntValue     NUMBER_OF_TIMES_TO_TEST_VANILLA_DROPS;
    public static ForgeConfigSpec.IntValue     TICKS_TO_TRANSFORM_LEAVES;
    public static ForgeConfigSpec.IntValue     SPREAD_UPDATE_FREQUENCEY;
    public static ForgeConfigSpec.DoubleValue  SPREAD_CHANCE_PERCENT;

    static {
        COMMON_BUILDER.comment("Main Settings").push(CATEGORY_MAIN);
        setUpMainConfig();

        COMMON_BUILDER.pop();

        COMMON_CONFIG = COMMON_BUILDER.build();
    }

    private static void setUpMainConfig() {
        COMMON_BUILDER.comment("Registry Options").push(SUBCATEGORY_REGISTRY_OPTIONS);
        USE_JSON_REGISTRIES = COMMON_BUILDER
            .comment("Decides which registry to use.")
            .define("useJSON", false);

        COMMON_BUILDER.comment("Crook Options").push(SUBCATEGORY_CROOK_OPTIONS);
        NUMBER_OF_TIMES_TO_TEST_VANILLA_DROPS = COMMON_BUILDER
            .comment("Decides which registry to use.")
            .defineInRange("numberOfTimesToTestVanillaDrops", 3, 1, 10);

        COMMON_BUILDER.pop();
        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("Infested Leaves Options").push(SUBCATEGORY_INFESTED_LEAVES_OPTIONS);
        TICKS_TO_TRANSFORM_LEAVES = COMMON_BUILDER.comment(
            "The number of ticks that it takes to transform a leaf block to an infested leaf block")
            .defineInRange("ticksToTransformLeaves", 600, 1, Integer.MAX_VALUE);
        SPREAD_UPDATE_FREQUENCEY  = COMMON_BUILDER.comment("Minimum percentage to spread")
            .defineInRange("spreadUpdateFrequency", 5, 0, 100);
        SPREAD_CHANCE_PERCENT     = COMMON_BUILDER.comment("Chance to spread if it got ticked")
            .defineInRange("spreadChancePercent", 0.5, 0, 1.0);

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
