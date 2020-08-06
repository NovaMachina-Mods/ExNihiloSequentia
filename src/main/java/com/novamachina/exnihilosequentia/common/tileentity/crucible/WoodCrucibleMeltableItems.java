package com.novamachina.exnihilosequentia.common.tileentity.crucible;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.novamachina.exnihilosequentia.common.json.AnnotatedDeserializer;
import com.novamachina.exnihilosequentia.common.json.CrucibleJson;
import com.novamachina.exnihilosequentia.common.json.CrucibleRegistriesJson;
import com.novamachina.exnihilosequentia.common.setup.ModRegistries;
import com.novamachina.exnihilosequentia.common.utility.Config;
import com.novamachina.exnihilosequentia.common.utility.Constants;
import com.novamachina.exnihilosequentia.common.utility.LogUtil;
import com.novamachina.exnihilosequentia.common.utility.TagUtils;
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

public class WoodCrucibleMeltableItems extends BaseCrucibleMeltableItems{

    public WoodCrucibleMeltableItems(ModRegistries.ModBus bus) {
        bus.register(this);
    }

    @Override
    protected void useJson() {
        try {
            CrucibleRegistriesJson registriesJson = readJson();
            for (CrucibleJson entry : registriesJson.getWoodCrucibleRegistry()) {
                if (itemExists(entry.getEntry())) {
                    ResourceLocation entryID = new ResourceLocation(entry.getEntry());
                    if (itemExists(entry.getFluid())) {
                        ResourceLocation fluidID = new ResourceLocation(entry.getFluid());
                        addMeltable(entryID, entry.getAmount(), fluidID);
                    } else {
                        LogUtil.warn(String.format("Entry \"%s\" does not exist...Skipping...", entry.getFluid()));
                    }
                } else {
                    LogUtil.warn(String.format("Entry \"%s\" does not exist...Skipping...", entry.getEntry()));
                }
            }
        } catch (JsonParseException e) {
            LogUtil.error("Malformed CrucibleRegistries.json");
            LogUtil.error(e.getMessage());
            if(e.getMessage().contains("IllegalStateException")) {
                LogUtil.error("Please consider deleting the file and regenerating it.");
            }
            LogUtil.error("Falling back to defaults");
            clear();
            useDefaults();
        }
    }

    private static CrucibleRegistriesJson readJson() throws JsonParseException {
        Gson gson = new GsonBuilder().registerTypeAdapter(CrucibleRegistriesJson.class, new AnnotatedDeserializer<CrucibleRegistriesJson>()).create();
        Path path = Constants.Json.baseJsonPath.resolve(Constants.Json.CRUCIBLE_FILE);
        CrucibleRegistriesJson crucibleRegistriesJson = null;
        try {
            StringBuilder builder = new StringBuilder();
            Files.readAllLines(path).forEach(builder::append);
            crucibleRegistriesJson = new Gson().fromJson(builder.toString(), CrucibleRegistriesJson.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return crucibleRegistriesJson;
    }

    @Override
    protected void useDefaults() {
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
}
