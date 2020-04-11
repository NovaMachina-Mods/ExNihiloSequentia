package com.novamachina.ens.common.utility;

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

    private static final String CATEGORY_MAIN                = "main";
    private static final String SUBCATEGORY_REGISTRY_OPTIONS = "registry";

    private static final ForgeConfigSpec.Builder COMMON_BUILDER = new Builder();

    public static ForgeConfigSpec COMMON_CONFIG;

    public static ForgeConfigSpec.BooleanValue USE_JSON_REGISTRIES;

    static {
        COMMON_BUILDER.comment("Main Settings").push(CATEGORY_MAIN);
        setUpMainConfig();

        COMMON_BUILDER.pop();

        COMMON_CONFIG = COMMON_BUILDER.build();
    }

    private static void setUpMainConfig() {
        COMMON_BUILDER.comment("Registry Options").push(SUBCATEGORY_REGISTRY_OPTIONS);
        USE_JSON_REGISTRIES = COMMON_BUILDER.comment("Decides which registry to use.")
            .define("useJSON", false);
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
