package com.novamachina.exnihilosequentia.common.tileentity.barrel.fluid;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.novamachina.exnihilosequentia.common.json.AnnotatedDeserializer;
import com.novamachina.exnihilosequentia.common.json.FluidBlockJson;
import com.novamachina.exnihilosequentia.common.setup.AbstractModRegistry;
import com.novamachina.exnihilosequentia.common.setup.ModBlocks;
import com.novamachina.exnihilosequentia.common.setup.ModFluids;
import com.novamachina.exnihilosequentia.common.setup.ModItems;
import com.novamachina.exnihilosequentia.common.setup.ModRegistries;
import com.novamachina.exnihilosequentia.common.utility.Constants;
import com.novamachina.exnihilosequentia.common.utility.LogUtil;
import com.novamachina.exnihilosequentia.common.utility.TagUtils;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.IItemProvider;
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

public class FluidBlockTransformRegistry extends AbstractModRegistry {
    private final Map<ResourceLocation, List<FluidBlockTransformRecipe>> recipeMap = new HashMap<>();

    public FluidBlockTransformRegistry(ModRegistries.ModBus bus) {
        bus.register(this);
    }

    public boolean isValidRecipe(Fluid fluid, Item input) {
        List<FluidBlockTransformRecipe> possibleRecipes = recipeMap.get(fluid.getRegistryName());

        if(possibleRecipes == null) {
            return false;
        }

        for(FluidBlockTransformRecipe recipe : possibleRecipes) {
            if(recipe.getInput().equals(input.getRegistryName())) {
                return true;
            }
        }
        return false;
    }

    public IItemProvider getResult(Fluid fluid, Item input) {
        List<FluidBlockTransformRecipe> possibleRecipes = recipeMap.get(fluid.getRegistryName());

        for(FluidBlockTransformRecipe recipe : possibleRecipes) {
            if(recipe.getInput().equals(input.getRegistryName())) {
                return ForgeRegistries.ITEMS.getValue(recipe.getResult());
            }
        }
        return null;
    }

    @Override
    protected void useJson() {
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
                                LogUtil.warn(String.format("%s: Entry \"%s\" does not exist...Skipping...", Constants.Json.FLUID_BLOCK_FILE, entry.getResult()));
                            }
                        } else {
                            LogUtil.warn(String.format("%s: Entry \"%s\" does not exist...Skipping...", Constants.Json.FLUID_BLOCK_FILE, entry.getInput()));
                        }
                    } else {
                        LogUtil.warn(String.format("%s: Entry \"%s\" does not exist...Skipping...", Constants.Json.FLUID_BLOCK_FILE, entry.getFluid()));
                    }
                } catch (ResourceLocationException e) {
                    LogUtil.warn(String.format("%s: %s. Skipping...", Constants.Json.FLUID_BLOCK_FILE, e.getMessage()));
                }
            }
        } catch (JsonParseException e) {
            LogUtil.error(String.format("Malformed %s", Constants.Json.FLUID_BLOCK_FILE));
            LogUtil.error(e.getMessage());
            if(e.getMessage().contains("IllegalStateException")) {
                LogUtil.error("Please consider deleting the file and regenerating it.");
            }
            LogUtil.error("Falling back to defaults");
            clear();
            useDefaults();
        }
    }

    private boolean itemExists(String entry) throws ResourceLocationException {
        ResourceLocation itemID = new ResourceLocation(entry);
        return TagUtils.isTag(itemID) ||  ForgeRegistries.BLOCKS.containsKey(itemID) || ForgeRegistries.ITEMS.containsKey(itemID) || ForgeRegistries.FLUIDS.containsKey(itemID);
    }

    private List<FluidBlockJson> readJson() throws JsonParseException {
        Type listType = new TypeToken<ArrayList<FluidBlockJson>>(){}.getType();
        Gson gson = new GsonBuilder().registerTypeAdapter(listType, new AnnotatedDeserializer<ArrayList<FluidBlockJson>>()).create();
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

    protected void useDefaults() {
        addRecipe(Fluids.WATER, ModBlocks.DUST.get(), Blocks.CLAY);
        addRecipe(Fluids.LAVA, Items.REDSTONE, Blocks.NETHERRACK);
        addRecipe(Fluids.LAVA, Items.GLOWSTONE_DUST, Blocks.END_STONE);
        addRecipe(ModFluids.WITCH_WATER.get(), Blocks.SAND, Blocks.SOUL_SAND);
        addRecipe(ModFluids.WITCH_WATER.get(), Blocks.RED_MUSHROOM, Blocks.SLIME_BLOCK);
        addRecipe(ModFluids.WITCH_WATER.get(), Blocks.BROWN_MUSHROOM, Blocks.SLIME_BLOCK);
        addRecipe(ModFluids.SEA_WATER.get(), ModItems.resourceMap.get(Constants.Items.BLUE_CORAL_SEED).get(), Blocks.TUBE_CORAL_BLOCK);
        addRecipe(ModFluids.SEA_WATER.get(), ModItems.resourceMap.get(Constants.Items.RED_CORAL_SEED).get(), Blocks.FIRE_CORAL_BLOCK);
        addRecipe(ModFluids.SEA_WATER.get(), ModItems.resourceMap.get(Constants.Items.PINK_CORAL_SEED).get(), Blocks.BRAIN_CORAL_BLOCK);
        addRecipe(ModFluids.SEA_WATER.get(), ModItems.resourceMap.get(Constants.Items.PURPLE_CORAL_SEED).get(), Blocks.BUBBLE_CORAL_BLOCK);
        addRecipe(ModFluids.SEA_WATER.get(), ModItems.resourceMap.get(Constants.Items.YELLOW_CORAL_SEED).get(), Blocks.HORN_CORAL_BLOCK);
        addRecipe(ModFluids.SEA_WATER.get(), Blocks.TUBE_CORAL_FAN, Blocks.TUBE_CORAL_WALL_FAN);
        addRecipe(ModFluids.SEA_WATER.get(), Blocks.BRAIN_CORAL_FAN, Blocks.BRAIN_CORAL_WALL_FAN);
        addRecipe(ModFluids.SEA_WATER.get(), Blocks.BUBBLE_CORAL_FAN, Blocks.BUBBLE_CORAL_WALL_FAN);
        addRecipe(ModFluids.SEA_WATER.get(), Blocks.HORN_CORAL_FAN, Blocks.HORN_CORAL_WALL_FAN);
        addRecipe(ModFluids.SEA_WATER.get(), Blocks.FIRE_CORAL_FAN, Blocks.FIRE_CORAL_WALL_FAN);
    }

    public void addRecipe(ResourceLocation fluid, ResourceLocation input, ResourceLocation result) {
        List<FluidBlockTransformRecipe> list = recipeMap.get(fluid);

        if(list == null) {
            list = new ArrayList<>();
            recipeMap.put(fluid, list);
        }
        for(FluidBlockTransformRecipe recipe : list) {
            if(recipe.getInput().equals(input)) {
                LogUtil.info(String.format("Duplicate recipe: %s(Fluid) + %s(Input). Keeping first result: %s", fluid, input, recipe.getResult().toString()));
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
}
