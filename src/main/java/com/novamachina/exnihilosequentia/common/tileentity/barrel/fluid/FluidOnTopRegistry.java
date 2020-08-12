package com.novamachina.exnihilosequentia.common.tileentity.barrel.fluid;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.novamachina.exnihilosequentia.common.json.AnnotatedDeserializer;
import com.novamachina.exnihilosequentia.common.json.FluidOnTopJson;
import com.novamachina.exnihilosequentia.common.setup.AbstractModRegistry;
import com.novamachina.exnihilosequentia.common.setup.ModRegistries;
import com.novamachina.exnihilosequentia.common.utility.Constants;
import com.novamachina.exnihilosequentia.common.utility.LogUtil;
import com.novamachina.exnihilosequentia.common.utility.TagUtils;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.ResourceLocationException;
import net.minecraftforge.registries.ForgeRegistries;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FluidOnTopRegistry extends AbstractModRegistry {
    private Map<ResourceLocation, List<FluidOnTopRecipe>> recipeMap = new HashMap<>();

    public FluidOnTopRegistry(ModRegistries.ModBus bus) {
        bus.register(this);
    }

    public boolean isValidRecipe(Fluid fluidInTank, Fluid fluidOnTop) {
        boolean isValid = false;
        ResourceLocation fluidInTankID = fluidInTank.getRegistryName();
        if (recipeMap.containsKey(fluidInTankID)) {
            List<FluidOnTopRecipe> recipeList = recipeMap.get(fluidInTankID);
            for (FluidOnTopRecipe recipe : recipeList) {
                if (recipe.getFluidOnTop().equals(fluidOnTop.getRegistryName())) {
                    isValid = true;
                }
            }
        }
        return isValid;
    }

    public Block getResult(Fluid fluidInTank, Fluid fluidOnTop) {
        ResourceLocation fluidInTankID = fluidInTank.getRegistryName();
        if (recipeMap.containsKey(fluidInTankID)) {
            List<FluidOnTopRecipe> recipeList = recipeMap.get(fluidInTankID);
            for (FluidOnTopRecipe recipe : recipeList) {
                if (recipe.getFluidOnTop().equals(fluidOnTop.getRegistryName())) {
                    return ForgeRegistries.BLOCKS.getValue(recipe.getResult());
                }
            }
        }
        return ForgeRegistries.BLOCKS.getValue(new ResourceLocation("minecraft:air"));
    }

    public void addRecipe(Fluid fluidInTank, Fluid fluidOnTop, Block result) {
        addRecipe(fluidInTank.getRegistryName(), fluidOnTop.getRegistryName(), result.getRegistryName());
    }

    public void addRecipe(ResourceLocation fluidInTank, ResourceLocation fluidOnTop, ResourceLocation result) {
        List<FluidOnTopRecipe> list = recipeMap.get(fluidInTank);

        if (list == null) {
            list = new ArrayList<>();
            recipeMap.put(fluidInTank, list);
        }
        for (FluidOnTopRecipe recipe : list) {
            if (recipe.getFluidOnTop().equals(fluidOnTop)) {
                LogUtil.warn(String
                    .format("Duplicate recipe: %s(In Barrel) + %s(On Top). Keeping first result: %s", fluidInTank, fluidOnTop, recipe.getResult()));
            }
        }
        list.add(new FluidOnTopRecipe(fluidInTank, fluidOnTop, result));
    }

    @Override
    protected void useJson() {
        if(generateJson(Constants.Json.FLUID_ON_TOP_FILE, this)) {
            return;
        }

        try {
            List<FluidOnTopJson> registriesJson = readJson();
            for(FluidOnTopJson entry : registriesJson) {
                try {
                    if(itemExists(entry.getFluidInBarrel())) {
                        ResourceLocation fluidInBarrel = new ResourceLocation(entry.getFluidInBarrel());
                        if(itemExists(entry.getFluidOnTop())) {
                            ResourceLocation fluidOnTop = new ResourceLocation(entry.getFluidOnTop());
                            if(itemExists(entry.getResult())) {
                                ResourceLocation resultID = new ResourceLocation(entry.getResult());
                                addRecipe(fluidInBarrel, fluidOnTop, resultID);
                            }else {
                                LogUtil.warn(String.format("%s: Entry \"%s\" does not exist...Skipping...", Constants.Json.FLUID_ON_TOP_FILE, entry.getResult()));
                            }
                        }else {
                            LogUtil.warn(String.format("%s: Entry \"%s\" does not exist...Skipping...", Constants.Json.FLUID_ON_TOP_FILE, entry.getFluidOnTop()));
                        }
                    } else {
                        LogUtil.warn(String.format("%s: Entry \"%s\" does not exist...Skipping...", Constants.Json.FLUID_ON_TOP_FILE, entry.getFluidInBarrel()));
                    }
                } catch (ResourceLocationException e) {
                    LogUtil.warn(String.format("%s: %s. Skipping...", Constants.Json.FLUID_ON_TOP_FILE, e.getMessage()));
                }
            }
        } catch (JsonParseException e) {
            LogUtil.error(String.format("Malformed %s", Constants.Json.FLUID_ON_TOP_FILE));
            LogUtil.error(e.getMessage());
            if(e.getMessage().contains("IllegalStateException")) {
                LogUtil.error("Please consider deleting the file and regenerating it.");
            }
            LogUtil.error("Falling back to defaults");
            clear();
            ModRegistries.BUS.getDefaults().forEach(registry -> registry.registerFluidOnTop(this));
        }
    }

    private boolean itemExists(String entry) throws ResourceLocationException {
        ResourceLocation itemID = new ResourceLocation(entry);
        return TagUtils.isTag(itemID) ||  ForgeRegistries.BLOCKS.containsKey(itemID) || ForgeRegistries.ITEMS.containsKey(itemID) || ForgeRegistries.FLUIDS.containsKey(itemID);
    }

    private List<FluidOnTopJson> readJson() throws JsonParseException {
        Type listType = new TypeToken<ArrayList<FluidOnTopJson>>(){}.getType();
        Gson gson = new GsonBuilder().registerTypeAdapter(listType, new AnnotatedDeserializer<ArrayList<FluidOnTopJson>>()).create();
        Path path = Constants.Json.baseJsonPath.resolve(Constants.Json.FLUID_ON_TOP_FILE);
        List<FluidOnTopJson> registryJson = null;
        try {
            StringBuilder builder = new StringBuilder();
            Files.readAllLines(path).forEach(builder::append);
            registryJson = gson.fromJson(builder.toString(), listType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return registryJson;
    }

    @Override
    public void clear() {
        recipeMap.clear();
    }

    @Override
    public List<FluidOnTopJson> toJSONReady() {
        List<FluidOnTopJson> gsonList = new ArrayList<>();

        for (List<FluidOnTopRecipe> recipeList : recipeMap.values()) {
            for (FluidOnTopRecipe recipe : recipeList) {
                gsonList.add(new FluidOnTopJson(recipe));
            }
        }

        return gsonList;
    }
}
