package com.novamachina.exnihilosequentia.common.tileentity.barrel.transform;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.novamachina.exnihilosequentia.common.json.AnnotatedDeserializer;
import com.novamachina.exnihilosequentia.common.json.FluidTransformJson;
import com.novamachina.exnihilosequentia.common.setup.AbstractModRegistry;
import com.novamachina.exnihilosequentia.common.setup.ModFluids;
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

public class FluidTransformRegistry extends AbstractModRegistry {
    private Map<ResourceLocation, FluidTransformRecipe> recipeMap = new HashMap<>();

    public FluidTransformRegistry(ModRegistries.ModBus bus) {
        bus.register(this);
    }

    public boolean isValidRecipe(Fluid fluidInTank, Block blockBelow) {
        boolean isValid = false;
        ResourceLocation fluidInTankID = fluidInTank.getRegistryName();
        if (recipeMap.containsKey(fluidInTankID)) {
            if (recipeMap.get(fluidInTankID).getBlockBelow().equals(blockBelow.getRegistryName())) {
                isValid = true;
            }
        }
        return isValid;
    }

    public Fluid getResult(Fluid fluidInTank) {
        return ForgeRegistries.FLUIDS.getValue(recipeMap.get(fluidInTank.getRegistryName()).getResult());
    }

    public void addRecipe(Fluid fluidInTank, Block blockBelow, Fluid result) {
        addRecipe(fluidInTank.getRegistryName(), blockBelow.getRegistryName(), result.getRegistryName());
    }

    public void addRecipe(ResourceLocation fluidInTank, ResourceLocation blockBelow, ResourceLocation result) {
        if (recipeMap.containsKey(fluidInTank)) {
            if (recipeMap.get(fluidInTank).getBlockBelow().equals(blockBelow)) {
                LogUtil.warn(String
                    .format("Duplicate recipe: %s(Fluid) + %s(Block Below). Keeping first result: %s", fluidInTank
                        .toString(), blockBelow.toString(), recipeMap.get(fluidInTank).getResult().toString()));
                return;
            }
        }
        insertIntoMap(fluidInTank, new FluidTransformRecipe(fluidInTank, blockBelow, result));
    }

    private void insertIntoMap(ResourceLocation fluidInTank, FluidTransformRecipe recipe) {
        recipeMap.put(fluidInTank, recipe);
    }

    @Override
    protected void useJson() {
        if(generateJson(Constants.Json.FLUID_TRANSFORM_FILE, this)) {
            return;
        }

        try {
            List<FluidTransformJson> registriesJson = readJson();
            for (FluidTransformJson entry : registriesJson) {
                try {
                    if (itemExists(entry.getFluidInBarrel())) {
                        ResourceLocation fluidID = new ResourceLocation(entry.getFluidInBarrel());
                        if (itemExists(entry.getBlockBelow())) {
                            ResourceLocation inputID = new ResourceLocation(entry.getBlockBelow());
                            if (itemExists(entry.getResult())) {
                                ResourceLocation resultID = new ResourceLocation(entry.getResult());
                                addRecipe(fluidID, inputID, resultID);
                            } else {
                                LogUtil.warn(String.format("%s: Entry \"%s\" does not exist...Skipping...", Constants.Json.FLUID_TRANSFORM_FILE, entry.getResult()));
                            }
                        } else {
                            LogUtil.warn(String.format("%s: Entry \"%s\" does not exist...Skipping...", Constants.Json.FLUID_TRANSFORM_FILE, entry.getBlockBelow()));
                        }
                    } else {
                        LogUtil.warn(String.format("%s: Entry \"%s\" does not exist...Skipping...", Constants.Json.FLUID_TRANSFORM_FILE, entry.getFluidInBarrel()));
                    }
                } catch (ResourceLocationException e) {
                    LogUtil.warn(String.format("%s: %s. Skipping...", Constants.Json.FLUID_TRANSFORM_FILE, e.getMessage()));
                }
            }
        } catch (JsonParseException e) {
            LogUtil.error(String.format("Malformed %s", Constants.Json.FLUID_TRANSFORM_FILE));
            LogUtil.error(e.getMessage());
            if (e.getMessage().contains("IllegalStateException")) {
                LogUtil.error("Please consider deleting the file and regenerating it.");
            }
            LogUtil.error("Falling back to defaults");
            clear();
            ModRegistries.BUS.getDefaults().forEach(registry -> registry.registerFluidTransform(this));
        }
    }

    private boolean itemExists(String entry) throws ResourceLocationException {
        ResourceLocation itemID = new ResourceLocation(entry);
        return TagUtils.isTag(itemID) || ForgeRegistries.BLOCKS.containsKey(itemID) || ForgeRegistries.ITEMS
            .containsKey(itemID) || ForgeRegistries.FLUIDS.containsKey(itemID);
    }

    private List<FluidTransformJson> readJson() throws JsonParseException {
        Type listType = new TypeToken<ArrayList<FluidTransformJson>>() {
        }.getType();
        Gson gson = new GsonBuilder()
            .registerTypeAdapter(listType, new AnnotatedDeserializer<ArrayList<FluidTransformJson>>()).create();
        Path path = Constants.Json.baseJsonPath.resolve(Constants.Json.FLUID_TRANSFORM_FILE);
        List<FluidTransformJson> registryJson = null;
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
    public List<FluidTransformJson> toJSONReady() {
        List<FluidTransformJson> gsonList = new ArrayList<>();

        for (FluidTransformRecipe recipe : recipeMap.values()) {
            gsonList.add(new FluidTransformJson(recipe));
        }

        return gsonList;
    }
}
