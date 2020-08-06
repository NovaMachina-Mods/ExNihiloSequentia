package com.novamachina.exnihilosequentia.common.tileentity.crucible;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.novamachina.exnihilosequentia.common.json.AnnotatedDeserializer;
import com.novamachina.exnihilosequentia.common.json.CrucibleJson;
import com.novamachina.exnihilosequentia.common.json.CrucibleRegistriesJson;
import com.novamachina.exnihilosequentia.common.setup.ModBlocks;
import com.novamachina.exnihilosequentia.common.setup.ModRegistries;
import com.novamachina.exnihilosequentia.common.utility.Constants;
import com.novamachina.exnihilosequentia.common.utility.LogUtil;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FiredCrucibleMeltableItems extends BaseCrucibleMeltableItems {
    public FiredCrucibleMeltableItems(ModRegistries.ModBus bus) {
        bus.register(this);
    }

    @Override
    protected void useJson() {
        try {
            CrucibleRegistriesJson registriesJson = readJson();
            for (CrucibleJson entry : registriesJson.getFiredCrucibleRegistry()) {
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
            LogUtil.error("Falling back to defaults");
            clear();
            useDefaults();
        }
    }

    private CrucibleRegistriesJson readJson() throws JsonParseException {
        Gson gson = new GsonBuilder().registerTypeAdapter(CrucibleRegistriesJson.class, new AnnotatedDeserializer<CrucibleRegistriesJson>()).create();
        Path path = Constants.Json.baseJsonPath.resolve(Constants.Json.CRUCIBLE_FILE);
        CrucibleRegistriesJson crucibleRegistriesJson = null;
        try {
            StringBuilder builder = new StringBuilder();
            Files.readAllLines(path).forEach(builder::append);
            crucibleRegistriesJson = gson.fromJson(builder.toString(), CrucibleRegistriesJson.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return crucibleRegistriesJson;
    }

    @Override
    protected void useDefaults() {
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
}
