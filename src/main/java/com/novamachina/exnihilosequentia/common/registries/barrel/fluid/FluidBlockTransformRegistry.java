package com.novamachina.exnihilosequentia.common.registries.barrel.fluid;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.novamachina.exnihilosequentia.common.api.ExNihiloRegistries;
import com.novamachina.exnihilosequentia.common.compat.jei.fluiditem.FluidBlockJEIRecipe;
import com.novamachina.exnihilosequentia.common.json.AnnotatedDeserializer;
import com.novamachina.exnihilosequentia.common.json.FluidBlockJson;
import com.novamachina.exnihilosequentia.common.registries.AbstractModRegistry;
import com.novamachina.exnihilosequentia.common.utility.Constants;
import com.novamachina.exnihilosequentia.common.utility.LogUtil;
import com.novamachina.exnihilosequentia.common.utility.TagUtils;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.ResourceLocationException;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FluidBlockTransformRegistry extends AbstractModRegistry {
    private final Map<ResourceLocation, List<FluidBlockTransformRecipe>> recipeMap = new HashMap<>();

    public FluidBlockTransformRegistry(ExNihiloRegistries.ModBus bus) {
        bus.register(this);
    }

    public boolean isValidRecipe(Fluid fluid, Item input) {
        List<FluidBlockTransformRecipe> possibleRecipes = recipeMap.get(fluid.getRegistryName());

        if (possibleRecipes == null) {
            return false;
        }

        for (FluidBlockTransformRecipe recipe : possibleRecipes) {
            if (recipe.getInput().equals(input.getRegistryName())) {
                return true;
            }
        }
        return false;
    }

    public IItemProvider getResult(Fluid fluid, Item input) {
        List<FluidBlockTransformRecipe> possibleRecipes = recipeMap.get(fluid.getRegistryName());

        for (FluidBlockTransformRecipe recipe : possibleRecipes) {
            if (recipe.getInput().equals(input.getRegistryName())) {
                return ForgeRegistries.ITEMS.getValue(recipe.getResult());
            }
        }
        return null;
    }

    @Override
    public void useJson() {
        if (generateJson(Constants.Json.FLUID_BLOCK_FILE, this)) {
            return;
        }

        try {
            List<FluidBlockJson> registriesJson = readJson();
            for (FluidBlockJson entry : registriesJson) {
                try {
                    if (itemExists(entry.getFluid())) {
                        ResourceLocation fluidID = new ResourceLocation(entry.getFluid());
                        if (itemExists(entry.getInput())) {
                            ResourceLocation inputID = new ResourceLocation(entry.getInput());
                            if (itemExists(entry.getResult())) {
                                ResourceLocation resultID = new ResourceLocation(entry.getResult());
                                addRecipe(fluidID, inputID, resultID);
                            } else {
                                LogUtil.warn(String
                                    .format("%s: Entry \"%s\" does not exist...Skipping...", Constants.Json.FLUID_BLOCK_FILE, entry
                                        .getResult()));
                            }
                        } else {
                            LogUtil.warn(String
                                .format("%s: Entry \"%s\" does not exist...Skipping...", Constants.Json.FLUID_BLOCK_FILE, entry
                                    .getInput()));
                        }
                    } else {
                        LogUtil.warn(String
                            .format("%s: Entry \"%s\" does not exist...Skipping...", Constants.Json.FLUID_BLOCK_FILE, entry
                                .getFluid()));
                    }
                } catch (ResourceLocationException e) {
                    LogUtil.warn(String.format("%s: %s. Skipping...", Constants.Json.FLUID_BLOCK_FILE, e.getMessage()));
                }
            }
        } catch (JsonParseException e) {
            LogUtil.error(String.format("Malformed %s", Constants.Json.FLUID_BLOCK_FILE));
            LogUtil.error(e.getMessage());
            if (e.getMessage().contains("IllegalStateException")) {
                LogUtil.error("Please consider deleting the file and regenerating it.");
            }
            LogUtil.error("Falling back to defaults");
            clear();
            ExNihiloRegistries.BUS.getDefaults().forEach(registry -> registry.registerFluidBlock(this));
        }
    }

    private boolean itemExists(String entry) throws ResourceLocationException {
        ResourceLocation itemID = new ResourceLocation(entry);
        return TagUtils.isTag(itemID) || ForgeRegistries.BLOCKS.containsKey(itemID) || ForgeRegistries.ITEMS
            .containsKey(itemID) || ForgeRegistries.FLUIDS.containsKey(itemID);
    }

    private List<FluidBlockJson> readJson() throws JsonParseException {
        Type listType = new TypeToken<ArrayList<FluidBlockJson>>() {
        }.getType();
        Gson gson = new GsonBuilder()
            .registerTypeAdapter(listType, new AnnotatedDeserializer<ArrayList<FluidBlockJson>>()).create();
        Path path = Constants.Json.baseJsonPath.resolve(Constants.Json.FLUID_BLOCK_FILE);
        List<FluidBlockJson> registryJson = null;
        try {
            StringBuilder builder = new StringBuilder();
            Files.readAllLines(path).forEach(builder::append);
            registryJson = gson.fromJson(builder.toString(), listType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return registryJson;
    }

    public void addRecipe(ResourceLocation fluid, ResourceLocation input, ResourceLocation result) {
        List<FluidBlockTransformRecipe> list = recipeMap.get(fluid);

        if (list == null) {
            list = new ArrayList<>();
            recipeMap.put(fluid, list);
        }
        for (FluidBlockTransformRecipe recipe : list) {
            if (recipe.getInput().equals(input)) {
                LogUtil.info(String
                    .format("Duplicate recipe: %s(Fluid) + %s(Input). Keeping first result: %s", fluid, input, recipe
                        .getResult().toString()));
            }
        }
        list.add(new FluidBlockTransformRecipe(fluid, input, result));
    }

    public void addRecipe(Fluid fluid, IItemProvider input, Block result) {
        addRecipe(fluid.getRegistryName(), input.asItem().getRegistryName(), result.getRegistryName());
    }

    @Override
    public void clear() {
        recipeMap.clear();
    }

    @Override
    public List<FluidBlockJson> toJSONReady() {
        List<FluidBlockJson> gsonList = new ArrayList<>();

        for (List<FluidBlockTransformRecipe> recipeList : recipeMap.values()) {
            for (FluidBlockTransformRecipe recipe : recipeList) {
                gsonList.add(new FluidBlockJson(recipe));
            }
        }

        return gsonList;
    }

    public List<FluidBlockJEIRecipe> getRecipeList() {
        List<FluidBlockJEIRecipe> recipes = new ArrayList<>();

        for (List<FluidBlockTransformRecipe> recipeList : recipeMap.values()) {
            for (FluidBlockTransformRecipe recipe : recipeList) {
                FluidStack fluidInBarrel = new FluidStack(ForgeRegistries.FLUIDS
                    .getValue(recipe.getFluid()), FluidAttributes.BUCKET_VOLUME);
                ItemStack input = new ItemStack(ForgeRegistries.ITEMS.getValue(recipe.getInput()));
                ItemStack result = new ItemStack(ForgeRegistries.ITEMS.getValue(recipe.getResult()));
                recipes.add(new FluidBlockJEIRecipe(fluidInBarrel, input, result));
            }
        }

        return recipes;
    }
}
