package com.novamachina.exnihilosequentia.common.item.tools.hammer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.novamachina.exnihilosequentia.common.jei.hammer.HammerRecipe;
import com.novamachina.exnihilosequentia.common.json.AnnotatedDeserializer;
import com.novamachina.exnihilosequentia.common.json.CrookJson;
import com.novamachina.exnihilosequentia.common.json.HammerJson;
import com.novamachina.exnihilosequentia.common.setup.AbstractModRegistry;
import com.novamachina.exnihilosequentia.common.setup.ModBlocks;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.novamachina.exnihilosequentia.common.setup.ModRegistries;
import com.novamachina.exnihilosequentia.common.utility.Constants;
import com.novamachina.exnihilosequentia.common.utility.LogUtil;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.ResourceLocationException;
import net.minecraftforge.registries.ForgeRegistries;

public class HammerDrops extends AbstractModRegistry {

    private final Map<ResourceLocation, ResourceLocation> hammerDrops = new HashMap<>();

    public HammerDrops(ModRegistries.ModBus bus) {
        bus.register(this);
    }

    public void addRecipe(Block input, Block output) {
        addRecipe(input.getRegistryName(), output.getRegistryName());
    }

    private void addRecipe(ResourceLocation input, ResourceLocation output) {
        if(hammerDrops.containsKey(input)) {
            LogUtil.warn(String.format("%s: Input %s already has a drop assigned. Skipping...", Constants.Json.HAMMER_FILE, input.toString()));
            return;
        }
        hammerDrops.put(input, output);
    }

    public Block getResult(ResourceLocation input) {
        if(hammerDrops.containsKey(input)) {
            ResourceLocation output = hammerDrops.get(input);
            if(ForgeRegistries.BLOCKS.containsKey(output)) {
                return ForgeRegistries.BLOCKS.getValue(output);
            }
        }
        return ForgeRegistries.BLOCKS.getValue(input);
    }

    @Override
    public void clear() {
        hammerDrops.clear();
    }

    @Override
    public List<HammerJson> toJSONReady() {
        List<HammerJson> jsonList = new ArrayList<>();

        for(Map.Entry<ResourceLocation, ResourceLocation> entry : hammerDrops.entrySet()) {
            jsonList.add(new HammerJson(entry.getKey().toString(), entry.getValue().toString()));
        }
        return jsonList;
    }

    @Override
    protected void useJson() {
        if(generateJson(Constants.Json.HAMMER_FILE, this)) {
            return;
        }

        try {
            List<HammerJson> list = readJson();
            for(HammerJson entry : list) {
                try {
                    if(itemExists(entry.getInput())) {
                        ResourceLocation inputID = new ResourceLocation(entry.getInput());
                        if(itemExists(entry.getOutput())) {
                            ResourceLocation outputID = new ResourceLocation(entry.getOutput());
                            addRecipe(inputID, outputID);
                        } else {
                            LogUtil.warn(String.format("%s: Entry \"%s\" does not exist...Skipping...", Constants.Json.HAMMER_FILE, entry.getOutput()));
                        }
                    } else {
                        LogUtil.warn(String.format("%s: Entry \"%s\" does not exist...Skipping...", Constants.Json.HAMMER_FILE, entry.getInput()));
                    }
                } catch (ResourceLocationException e) {
                    LogUtil.warn(String.format("%s: %s. Skipping...", Constants.Json.HAMMER_FILE, e.getMessage()));
                }
            }
        } catch (JsonParseException e) {
            LogUtil.error(String.format("Malformed %s", Constants.Json.HAMMER_FILE));
            LogUtil.error(e.getMessage());
            if(e.getMessage().contains("IllegalStateException")) {
                LogUtil.error("Please consider deleting the file and regenerating it.");
            }
            LogUtil.error("Falling back to defaults");
            clear();
            ModRegistries.BUS.getDefaults().forEach(registry -> registry.registerHammer(this));
        }
    }

    private boolean itemExists(String entry) throws ResourceLocationException {
        ResourceLocation itemID = new ResourceLocation(entry);
        return ForgeRegistries.BLOCKS.containsKey(itemID);
    }

    private List<HammerJson> readJson() {
        Type listType = new TypeToken<ArrayList<HammerJson>>(){}.getType();
        Gson gson = new GsonBuilder().registerTypeAdapter(listType, new AnnotatedDeserializer<ArrayList<HammerJson>>()).create();
        Path path = Constants.Json.baseJsonPath.resolve(Constants.Json.HAMMER_FILE);
        List<HammerJson> list = null;
        try {
            StringBuilder builder = new StringBuilder();
            Files.readAllLines(path).forEach(builder::append);
            list = gson.fromJson(builder.toString(), listType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean isHammerable(ResourceLocation blockID) {
        return hammerDrops.containsKey(blockID);
    }

    public List<HammerRecipe> getRecipeList() {
        List<HammerRecipe> recipes = new ArrayList<>();
        for(ResourceLocation inputID : hammerDrops.keySet()) {
            Block input = ForgeRegistries.BLOCKS.getValue(inputID);
            Block output = getResult(inputID);

            recipes.add(new HammerRecipe(new ItemStack(input), new ItemStack(output)));
        }
        return recipes;
    }
}
