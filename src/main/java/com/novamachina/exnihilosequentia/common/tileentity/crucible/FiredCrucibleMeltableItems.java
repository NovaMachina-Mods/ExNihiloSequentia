package com.novamachina.exnihilosequentia.common.tileentity.crucible;

import com.google.gson.Gson;
import com.novamachina.exnihilosequentia.common.json.BarrelRegistriesJson;
import com.novamachina.exnihilosequentia.common.json.CrucibleJson;
import com.novamachina.exnihilosequentia.common.json.CrucibleRegistriesJson;
import com.novamachina.exnihilosequentia.common.json.FluidBlockJson;
import com.novamachina.exnihilosequentia.common.setup.ModBlocks;
import com.novamachina.exnihilosequentia.common.utility.Config;
import com.novamachina.exnihilosequentia.common.utility.Constants;
import com.novamachina.exnihilosequentia.common.utility.LogUtil;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FiredCrucibleMeltableItems {

    private static final Map<ResourceLocation, Meltable> meltableMap = new HashMap<>();

    public static void addMeltable(ForgeRegistryEntry<? extends IItemProvider> entry, int amount, Fluid fluid) {
        addMeltable(entry.getRegistryName(), amount, fluid.getRegistryName());
    }

    public static void addMeltable(ResourceLocation entry, int amount, ResourceLocation fluid) {
        insertIntoMap(entry, new Meltable(amount, fluid));
    }

    private static void insertIntoMap(ResourceLocation name, Meltable meltable) {
        meltableMap.put(name, meltable);
    }

    public static boolean isMeltable(ForgeRegistryEntry<? extends IItemProvider> entry) {
        return meltableMap.containsKey(entry.getRegistryName());
    }

    public static Meltable getMeltable(ForgeRegistryEntry<? extends IItemProvider> entry) {
        return meltableMap.getOrDefault(entry.getRegistryName(), Meltable.DEFAULT);
    }

    public static void initialize() {
        if(Config.USE_JSON_REGISTRIES.get()) {
            addEntries(readJson());
        } else {
            useDefaults();
        }
    }

    private static void addEntries(CrucibleRegistriesJson registriesJson) {
        for(CrucibleJson entry : registriesJson.getWoodCrucibleRegistry()) {
            if(itemExists(entry.getEntry())) {
                ResourceLocation entryID = new ResourceLocation(entry.getEntry());
                if(itemExists(entry.getFluid())) {
                    ResourceLocation fluidID = new ResourceLocation(entry.getFluid());
                    addMeltable(entryID, entry.getAmount(), fluidID);
                }else {
                    LogUtil.warn(String.format("Entry \"%s\" does not exist...Skipping...", entry.getFluid()));
                }
            } else {
                LogUtil.warn(String.format("Entry \"%s\" does not exist...Skipping...", entry.getEntry()));
            }
        }
    }

    private static boolean itemExists(String entry) {
        ResourceLocation itemID = new ResourceLocation(entry);
        return ForgeRegistries.BLOCKS.containsKey(itemID) || ForgeRegistries.ITEMS.containsKey(itemID) || ForgeRegistries.FLUIDS.containsKey(itemID);
    }

    private static CrucibleRegistriesJson readJson() {
        Path path = Constants.Json.baseJsonPath.resolve(Constants.Json.CRUCIBLE_FILE);
        CrucibleRegistriesJson crucibleRegistriesJson = null;
        try {
            StringBuilder builder = new StringBuilder();
            Files.readAllLines(path).forEach(builder::append);
            crucibleRegistriesJson = new Gson().fromJson(builder.toString(), CrucibleRegistriesJson.class);
            LogUtil.info(builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return crucibleRegistriesJson;
    }

    public static void useDefaults() {
        // Lava Meltables
        addMeltable(Blocks.COBBLESTONE, 250, Fluids.LAVA);
        addMeltable(Blocks.DIORITE, 250, Fluids.LAVA);
        addMeltable(Blocks.ANDESITE, 250, Fluids.LAVA);
        addMeltable(Blocks.GRANITE, 250, Fluids.LAVA);
        addMeltable(Blocks.STONE, 250, Fluids.LAVA);
        addMeltable(Blocks.GRAVEL, 200, Fluids.LAVA);
        addMeltable(Blocks.SAND, 100, Fluids.LAVA);
        addMeltable(ModBlocks.DUST.get(), 50, Fluids.LAVA);
        addMeltable(Blocks.NETHERRACK, 1000, Fluids.LAVA);
        addMeltable(Blocks.OBSIDIAN, 1000, Fluids.LAVA);
    }

    public static List<CrucibleJson> toJSONReady() {
        List<CrucibleJson> jsonList = new ArrayList<>();
        for(Map.Entry<ResourceLocation, Meltable> entry : meltableMap.entrySet()) {
            jsonList.add(new CrucibleJson(entry.getKey().toString(), entry.getValue()));
        }
        return jsonList;
    }
}
