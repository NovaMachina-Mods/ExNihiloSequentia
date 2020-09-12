package com.novamachina.exnihilosequentia.common.registries.crucible;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.novamachina.exnihilosequentia.common.api.ExNihiloRegistries;
import com.novamachina.exnihilosequentia.common.compat.jei.heat.HeatRecipe;
import com.novamachina.exnihilosequentia.common.json.AnnotatedDeserializer;
import com.novamachina.exnihilosequentia.common.json.HeatJson;
import com.novamachina.exnihilosequentia.common.registries.AbstractModRegistry;
import com.novamachina.exnihilosequentia.common.utility.Constants;
import com.novamachina.exnihilosequentia.common.utility.LogUtil;
import com.novamachina.exnihilosequentia.common.utility.TagUtils;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.ResourceLocationException;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HeatRegistry extends AbstractModRegistry {

    private final Map<ResourceLocation, Integer> heatMap = new HashMap<>();

    public HeatRegistry(ExNihiloRegistries.ModBus bus) {
        bus.register(this);
    }

    public void addHeatSource(ForgeRegistryEntry<? extends IItemProvider> entry, int amount) {
        addHeatSource(entry.getRegistryName(), amount);
    }

    public void addHeatSource(ResourceLocation entry, int amount) {
        // Who do I own?
        List<ResourceLocation> idList = TagUtils.getTagsOwnedBy(entry);
        for (ResourceLocation id : idList) {
            if (heatMap.containsKey(id)) {
                LogUtil.info(String
                    .format("ID: %s falls under Tag: %s. Removing %s ...", id.toString(), entry.toString(), id
                        .toString()));
                heatMap.remove(id);
            }
        }

        // Does a tag who owns me already exist in the map?
        Collection<ResourceLocation> tags = TagUtils.getTags(entry);
        if (tags != null) {
            for (ResourceLocation tag : tags) {
                if (heatMap.containsKey(tag)) {
                    LogUtil
                        .info(String.format("Tag: %s already registered. Skipping item %s ...", tag.toString(), entry));
                    return;
                }
            }
        }

        // Am I in map?
        if (heatMap.containsKey(entry)) {
            LogUtil.info(String.format("Tag: %s already registered. Skipping...", entry));
            return;
        }

        insertIntoMap(entry, amount);
    }

    private void insertIntoMap(ResourceLocation name, int amount) {
        heatMap.put(name, amount);
    }

    public int getHeatAmount(ForgeRegistryEntry<? extends IItemProvider> entry) {
        return heatMap.getOrDefault(entry.getRegistryName(), 0);
    }

    @Override
    public void useJson() {
        if (generateJson(Constants.Json.HEAT_FILE, this)) {
            return;
        }

        try {
            List<HeatJson> registriesJson = readJson();
            for (HeatJson entry : registriesJson) {
                try {
                    if (itemExists(entry.getEntry())) {
                        ResourceLocation entryID = new ResourceLocation(entry.getEntry());
                        addHeatSource(entryID, entry.getRate());
                    } else {
                        LogUtil.warn(String
                            .format("%s: Entry \"%s\" does not exist...Skipping...", Constants.Json.HEAT_FILE, entry
                                .getEntry()));
                    }
                } catch (ResourceLocationException e) {
                    LogUtil.warn(String.format("%s: %s. Skipping...", Constants.Json.HEAT_FILE, e.getMessage()));
                }
            }
        } catch (JsonParseException e) {
            LogUtil.error(String.format("Malformed %s", Constants.Json.HEAT_FILE));
            LogUtil.error(e.getMessage());
            if (e.getMessage().contains("IllegalStateException")) {
                LogUtil.error("Please consider deleting the file and regenerating it.");
            }
            LogUtil.error("Falling back to defaults");
            clear();
            ExNihiloRegistries.BUS.getDefaults().forEach(registry -> registry.registerHeat(this));
        }
    }

    private boolean itemExists(String entry) throws ResourceLocationException {
        ResourceLocation itemID = new ResourceLocation(entry);
        return TagUtils.isTag(itemID) || ForgeRegistries.BLOCKS.containsKey(itemID) || ForgeRegistries.ITEMS
            .containsKey(itemID) || ForgeRegistries.FLUIDS.containsKey(itemID);
    }

    private List<HeatJson> readJson() throws JsonParseException {
        Type listType = new TypeToken<ArrayList<HeatJson>>() {
        }.getType();
        Gson gson = new GsonBuilder().registerTypeAdapter(listType, new AnnotatedDeserializer<ArrayList<HeatJson>>())
            .create();
        Path path = Constants.Json.baseJsonPath.resolve(Constants.Json.HEAT_FILE);
        List<HeatJson> registryJson = null;
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
        heatMap.clear();
    }

    @Override
    public List<HeatJson> toJSONReady() {
        List<HeatJson> jsonList = new ArrayList<>();
        for (Map.Entry<ResourceLocation, Integer> entry : heatMap.entrySet()) {
            jsonList.add(new HeatJson(entry.getKey().toString(), entry.getValue()));
        }
        return jsonList;
    }

    public List<HeatRecipe> getRecipeList() {
        List<HeatRecipe> recipes = new ArrayList<>();

        for (Map.Entry<ResourceLocation, Integer> entry : heatMap.entrySet()) {
            Block block = ForgeRegistries.BLOCKS.getValue(entry.getKey());
            Fluid fluid = ForgeRegistries.FLUIDS.getValue(entry.getKey());
            List<ItemStack> blockList = new ArrayList<>();
            List<FluidStack> fluidList = new ArrayList<>();

            if (block == Blocks.FIRE) {
                blockList.add(new ItemStack(Items.FLINT_AND_STEEL));

            } else {
                if (block != Blocks.AIR && fluid == Fluids.EMPTY) {
                    ITag<Block> blockTag = BlockTags.getCollection().get(entry.getKey());
                    if (blockTag != null) {
                        blockList = blockTag.func_230236_b_().stream().map(ItemStack::new).collect(Collectors.toList());
                    } else {
                        blockList.add(new ItemStack(block));
                    }
                }
                if (fluid != Fluids.EMPTY) {
                    fluidList.add(new FluidStack(fluid, FluidAttributes.BUCKET_VOLUME));
                }
            }

            recipes.add(new HeatRecipe(blockList, fluidList, entry.getValue()));
        }
        return recipes;
    }
}
