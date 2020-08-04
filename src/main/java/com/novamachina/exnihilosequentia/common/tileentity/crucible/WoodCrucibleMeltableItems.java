package com.novamachina.exnihilosequentia.common.tileentity.crucible;

import com.google.gson.Gson;
import com.novamachina.exnihilosequentia.common.json.CrucibleJson;
import com.novamachina.exnihilosequentia.common.json.CrucibleRegistriesJson;
import com.novamachina.exnihilosequentia.common.utility.Config;
import com.novamachina.exnihilosequentia.common.utility.Constants;
import com.novamachina.exnihilosequentia.common.utility.LogUtil;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Items;
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

// TODO: Add Tag support
public class WoodCrucibleMeltableItems{

    private static final Map<ResourceLocation, Meltable> meltableList = new HashMap<>();

    public static void addMeltable(ForgeRegistryEntry<? extends IItemProvider> entry, int amount, Fluid fluid) {
        addMeltable(entry.getRegistryName(), amount, fluid.getRegistryName());
    }

    public static void addMeltable(ResourceLocation entry, int amount, ResourceLocation fluid) {
        insertIntoMap(entry, new Meltable(amount, fluid));
    }

    private static void insertIntoMap(ResourceLocation name, Meltable meltable) {
        meltableList.put(name, meltable);
    }

    public static boolean isMeltable(ForgeRegistryEntry<? extends IItemProvider> entry) {
        return meltableList.containsKey(entry.getRegistryName());
    }

    public static Meltable getMeltable(ForgeRegistryEntry<? extends IItemProvider> entry) {
        return meltableList.getOrDefault(entry.getRegistryName(), Meltable.DEFAULT);
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
        addMeltable(Items.ACACIA_SAPLING, 250, Fluids.WATER);
        addMeltable(Items.BIRCH_SAPLING, 250, Fluids.WATER);
        addMeltable(Items.DARK_OAK_SAPLING, 250, Fluids.WATER);
        addMeltable(Items.JUNGLE_SAPLING, 250, Fluids.WATER);
        addMeltable(Items.SPRUCE_SAPLING, 250, Fluids.WATER);
        addMeltable(Items.OAK_SAPLING, 250, Fluids.WATER);

        addMeltable(Items.ACACIA_LEAVES, 250, Fluids.WATER);
        addMeltable(Items.BIRCH_LEAVES, 250, Fluids.WATER);
        addMeltable(Items.DARK_OAK_LEAVES, 250, Fluids.WATER);
        addMeltable(Items.JUNGLE_LEAVES, 250, Fluids.WATER);
        addMeltable(Items.SPRUCE_LEAVES, 250, Fluids.WATER);
        addMeltable(Items.OAK_LEAVES, 250, Fluids.WATER);
    }

    public static List<CrucibleJson> toJSONReady() {
        List<CrucibleJson> jsonList = new ArrayList<>();
        for(Map.Entry<ResourceLocation, Meltable> entry : meltableList.entrySet()) {
            jsonList.add(new CrucibleJson(entry.getKey().toString(), entry.getValue()));
        }
        return jsonList;
    }
}
