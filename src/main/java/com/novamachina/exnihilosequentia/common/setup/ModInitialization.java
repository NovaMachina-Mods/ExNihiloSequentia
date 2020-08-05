package com.novamachina.exnihilosequentia.common.setup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.novamachina.exnihilosequentia.common.item.tools.crook.CrookDrops;
import com.novamachina.exnihilosequentia.common.item.tools.hammer.HammerDrops;
import com.novamachina.exnihilosequentia.common.json.BarrelRegistriesJson;
import com.novamachina.exnihilosequentia.common.json.CrucibleRegistriesJson;
import com.novamachina.exnihilosequentia.common.tileentity.barrel.BarrelModeRegistry;
import com.novamachina.exnihilosequentia.common.tileentity.barrel.compost.CompostRegistry;
import com.novamachina.exnihilosequentia.common.tileentity.barrel.fluid.FluidBlockTransformRegistry;
import com.novamachina.exnihilosequentia.common.tileentity.barrel.fluid.FluidOnTopRegistry;
import com.novamachina.exnihilosequentia.common.tileentity.barrel.transform.FluidTransformRegistry;
import com.novamachina.exnihilosequentia.common.tileentity.crucible.FiredCrucibleMeltableItems;
import com.novamachina.exnihilosequentia.common.tileentity.crucible.HeatRegistry;
import com.novamachina.exnihilosequentia.common.tileentity.crucible.WoodCrucibleMeltableItems;
import com.novamachina.exnihilosequentia.common.tileentity.sieve.SieveDrops;
import com.novamachina.exnihilosequentia.common.utility.Config;
import com.novamachina.exnihilosequentia.common.utility.Constants;
import com.novamachina.exnihilosequentia.common.utility.Constants.ModInfo;
import com.novamachina.exnihilosequentia.common.utility.LogUtil;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.ObjectHolder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Mod.EventBusSubscriber(modid = Constants.ModInfo.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModInitialization {

    public static final ItemGroup ITEM_GROUP = new ItemGroup(Constants.ModInfo.MOD_ID) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModBlocks.SIEVE.get());
        }
    };

    @ObjectHolder(ModInfo.MOD_ID + ":use_hammer")
    public static GlobalLootModifierSerializer<?> HAMMER_MODIFIER = null;

    public static void init(IEventBus modEventBus) {
        LogUtil.info("Registration init");
        ModBlocks.init(modEventBus);
        ModItems.init(modEventBus);
        ModTiles.init(modEventBus);
        ModFluids.init(modEventBus);
    }

    @SubscribeEvent
    public static void setupNonTagBasedRegistries(FMLCommonSetupEvent event) {
        LogUtil.info("Initialize Non-Tag Based Mod Registries");

        BarrelModeRegistry.initialize();
    }

    @SubscribeEvent
    public static void setupTagBasedRegistries(FMLServerStartingEvent event) {
        LogUtil.info("Initialize Tag Based Mod Registries");

        if(Config.USE_JSON_REGISTRIES.get()) {
            generateJsonFiles();
        }

        ModRegistries.BUS.initialize(Config.USE_JSON_REGISTRIES.get());
    }
    private static void generateJsonFiles() {
        ModRegistries.BUS.initialize(false);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().create();

        File file = Constants.Json.baseJsonPath.resolve(Constants.Json.BARREL_FILE).toFile();
        if(!Files.exists(file.toPath())) {
            try (Writer writer = new FileWriter(file)){
                BarrelRegistriesJson barrelRegistriesJson = new BarrelRegistriesJson(ModRegistries.FLUID_ON_TOP
                    .toJSONReady(), ModRegistries.FLUID_TRANSFORM.toJSONReady(), ModRegistries.FLUID_BLOCK
                    .toJSONReady(), ModRegistries.COMPOST.toJSONReady());
                writer.write(gson.toJson(barrelRegistriesJson));
                LogUtil.info("Created Barrel Registries JSON");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        file = Constants.Json.baseJsonPath.resolve(Constants.Json.CRUCIBLE_FILE).toFile();
        if(!Files.exists(file.toPath())) {
            try (Writer writer = new FileWriter(file)){
                CrucibleRegistriesJson crucibleRegistriesJson = new CrucibleRegistriesJson(ModRegistries.FIRED_CRUCIBLE
                    .toJSONReady(), ModRegistries.WOODEN_CRUCIBLE.toJSONReady(), ModRegistries.HEAT.toJSONReady());
                writer.write(gson.toJson(crucibleRegistriesJson));
                LogUtil.info("Created Barrel Registries JSON");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        file = Constants.Json.baseJsonPath.resolve(Constants.Json.SIEVE_FILE).toFile();
        if(!Files.exists(file.toPath())) {
            try (Writer writer = new FileWriter(file)){
                writer.write(gson.toJson(ModRegistries.SIEVE.toJSONReady()));
                LogUtil.info("Created Sieve Registry JSON");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        file = Constants.Json.baseJsonPath.resolve(Constants.Json.CROOK_FILE).toFile();
        if(!Files.exists(file.toPath())) {
            try (Writer writer = new FileWriter(file)){
                writer.write(gson.toJson(ModRegistries.CROOK.toJSONReady()));
                LogUtil.info("Created Crook Registry JSON");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ModRegistries.BUS.clearRegistries();
    }
}
