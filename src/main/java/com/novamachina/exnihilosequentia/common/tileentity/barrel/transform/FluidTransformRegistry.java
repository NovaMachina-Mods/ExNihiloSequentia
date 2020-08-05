package com.novamachina.exnihilosequentia.common.tileentity.barrel.transform;

import com.google.gson.Gson;
import com.novamachina.exnihilosequentia.common.json.BarrelRegistriesJson;
import com.novamachina.exnihilosequentia.common.json.FluidBlockJson;
import com.novamachina.exnihilosequentia.common.json.FluidTransformJson;
import com.novamachina.exnihilosequentia.common.setup.ModFluids;
import com.novamachina.exnihilosequentia.common.utility.Config;
import com.novamachina.exnihilosequentia.common.utility.Constants;
import com.novamachina.exnihilosequentia.common.utility.LogUtil;
import com.novamachina.exnihilosequentia.common.utility.TagUtils;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FluidTransformRegistry {
    private static Map<ResourceLocation, FluidTransformRecipe> recipeMap = new HashMap<>();

    public static boolean isValidRecipe(Fluid fluidInTank, Block blockBelow) {
        boolean isValid = false;
        ResourceLocation fluidInTankID = fluidInTank.getRegistryName();
        if(recipeMap.containsKey(fluidInTankID)) {
            if(recipeMap.get(fluidInTankID).getBlockBelow().equals(blockBelow.getRegistryName())) {
                isValid = true;
            }
        }
        return isValid;
    }

    public static Block getResult(Fluid fluidInTank) {
        return ForgeRegistries.BLOCKS.getValue(recipeMap.get(fluidInTank.getRegistryName()).getResult());
    }

    public static void addRecipe(Fluid fluidInTank, Block blockBelow, Fluid result) {
        addRecipe(fluidInTank.getRegistryName(), blockBelow.getRegistryName(), result.getRegistryName());
    }

    public static void addRecipe(ResourceLocation fluidInTank, ResourceLocation blockBelow, ResourceLocation result) {
        insertIntoMap(fluidInTank, new FluidTransformRecipe(fluidInTank, blockBelow, result));
    }

    private static void insertIntoMap(ResourceLocation fluidInTank, FluidTransformRecipe recipe) {
        recipeMap.put(fluidInTank, recipe);
    }

    public static void initialize() {
        if(Config.USE_JSON_REGISTRIES.get()) {
            addEntries(readJson());
        } else {
            useDefaults();
        }
    }

    private static void addEntries(BarrelRegistriesJson registriesJson) {
        for(FluidTransformJson entry : registriesJson.getFluidTransformRegistry()) {
            if(itemExists(entry.getFluidInBarrel())) {
                ResourceLocation fluidID = new ResourceLocation(entry.getFluidInBarrel());
                if(itemExists(entry.getBlockBelow())) {
                    ResourceLocation inputID = new ResourceLocation(entry.getBlockBelow());
                    if(itemExists(entry.getResult())) {
                        ResourceLocation resultID = new ResourceLocation(entry.getResult());
                        addRecipe(fluidID, inputID, resultID);
                    }else {
                        LogUtil.warn(String.format("Entry \"%s\" does not exist...Skipping...", entry.getResult()));
                    }
                }else {
                    LogUtil.warn(String.format("Entry \"%s\" does not exist...Skipping...", entry.getBlockBelow()));
                }
            } else {
                LogUtil.warn(String.format("Entry \"%s\" does not exist...Skipping...", entry.getFluidInBarrel()));
            }
        }
    }

    private static boolean itemExists(String entry) {
        ResourceLocation itemID = new ResourceLocation(entry);
        return TagUtils.isTag(itemID) || ForgeRegistries.BLOCKS.containsKey(itemID) || ForgeRegistries.ITEMS.containsKey(itemID) || ForgeRegistries.FLUIDS.containsKey(itemID);
    }

    private static BarrelRegistriesJson readJson() {
        Path path = Constants.Json.baseJsonPath.resolve(Constants.Json.BARREL_FILE);
        BarrelRegistriesJson barrelRegistriesJson = null;
        try {
            StringBuilder builder = new StringBuilder();
            Files.readAllLines(path).forEach(builder::append);
            barrelRegistriesJson = new Gson().fromJson(builder.toString(), BarrelRegistriesJson.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return barrelRegistriesJson;
    }

    public static void useDefaults() {
        addRecipe(Fluids.WATER, Blocks.MYCELIUM, ModFluids.WITCH_WATER_STILL.get());
    }

    public static List<FluidTransformJson> toJSONReady() {
        List<FluidTransformJson> gsonList = new ArrayList<>();

        for (FluidTransformRecipe recipe : recipeMap.values()) {
            gsonList.add(new FluidTransformJson(recipe));
        }

        return gsonList;
    }
}
