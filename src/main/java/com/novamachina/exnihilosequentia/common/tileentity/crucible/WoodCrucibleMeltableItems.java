package com.novamachina.exnihilosequentia.common.tileentity.crucible;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.novamachina.exnihilosequentia.common.json.AnnotatedDeserializer;
import com.novamachina.exnihilosequentia.common.json.CrucibleJson;
import com.novamachina.exnihilosequentia.common.json.CrucibleRegistriesJson;
import com.novamachina.exnihilosequentia.common.json.FluidOnTopJson;
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
import net.minecraft.util.ResourceLocationException;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

import java.io.IOException;
import java.lang.reflect.Type;
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
        if(generateJson(Constants.Json.WOOD_CRUCIBLE_FILE, this)) {
            return;
        }

        try {
            List<CrucibleJson> registriesJson = readJson();
            for (CrucibleJson entry : registriesJson) {
                try {
                    if (itemExists(entry.getEntry())) {
                        ResourceLocation entryID = new ResourceLocation(entry.getEntry());
                        if (itemExists(entry.getFluid())) {
                            ResourceLocation fluidID = new ResourceLocation(entry.getFluid());
                            addMeltable(entryID, entry.getAmount(), fluidID);
                        } else {
                            LogUtil.warn(String.format("%s: Entry \"%s\" does not exist...Skipping...", Constants.Json.WOOD_CRUCIBLE_FILE, entry.getFluid()));
                        }
                    } else {
                        LogUtil.warn(String.format("%s: Entry \"%s\" does not exist...Skipping...", Constants.Json.HEAT_FILE, entry.getEntry()));
                    }
                } catch (ResourceLocationException e) {
                    LogUtil.warn(String.format("%s: %s. Skipping...", Constants.Json.WOOD_CRUCIBLE_FILE, e.getMessage()));
                }
            }
        } catch (JsonParseException e) {
            LogUtil.error(String.format("Malformed %s", Constants.Json.WOOD_CRUCIBLE_FILE));
            LogUtil.error(e.getMessage());
            if(e.getMessage().contains("IllegalStateException")) {
                LogUtil.error("Please consider deleting the file and regenerating it.");
            }
            LogUtil.error("Falling back to defaults");
            clear();
            ModRegistries.BUS.getDefaults().forEach(registry -> registry.registerWoodCrucible(this));
        }
    }

    private List<CrucibleJson> readJson() throws JsonParseException {
        Type listType = new TypeToken<ArrayList<CrucibleJson>>(){}.getType();
        Gson gson = new GsonBuilder().registerTypeAdapter(listType, new AnnotatedDeserializer<ArrayList<CrucibleJson>>()).create();
        Path path = Constants.Json.baseJsonPath.resolve(Constants.Json.WOOD_CRUCIBLE_FILE);
        List<CrucibleJson> registryJson = null;
        try {
            StringBuilder builder = new StringBuilder();
            Files.readAllLines(path).forEach(builder::append);
            registryJson = gson.fromJson(builder.toString(), listType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return registryJson;
    }
}
