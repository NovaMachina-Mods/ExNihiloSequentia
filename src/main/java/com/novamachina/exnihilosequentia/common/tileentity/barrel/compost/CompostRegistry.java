package com.novamachina.exnihilosequentia.common.tileentity.barrel.compost;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.novamachina.exnihilosequentia.common.compat.IDefaultRegistry;
import com.novamachina.exnihilosequentia.common.item.resources.EnumResource;
import com.novamachina.exnihilosequentia.common.item.tools.crook.CrookDropEntry;
import com.novamachina.exnihilosequentia.common.json.AnnotatedDeserializer;
import com.novamachina.exnihilosequentia.common.json.BarrelRegistriesJson;
import com.novamachina.exnihilosequentia.common.json.CompostJson;
import com.novamachina.exnihilosequentia.common.json.CrookJson;
import com.novamachina.exnihilosequentia.common.json.CrucibleRegistriesJson;
import com.novamachina.exnihilosequentia.common.json.SieveJson;
import com.novamachina.exnihilosequentia.common.setup.AbstractModRegistry;
import com.novamachina.exnihilosequentia.common.setup.ModItems;
import com.novamachina.exnihilosequentia.common.setup.ModRegistries;
import com.novamachina.exnihilosequentia.common.utility.Config;
import com.novamachina.exnihilosequentia.common.utility.Constants;
import com.novamachina.exnihilosequentia.common.utility.LogUtil;
import com.novamachina.exnihilosequentia.common.utility.TagUtils;
import net.minecraft.block.Blocks;
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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompostRegistry extends AbstractModRegistry {
    private final Map<ResourceLocation, Integer> solidsMap = new HashMap<>();

    public CompostRegistry(ModRegistries.ModBus bus) {
        bus.register(this);
    }

    public boolean containsSolid(IItemProvider item) {
        Collection<ResourceLocation> tags = TagUtils.getTags(item);
        for(ResourceLocation tag : tags) {
            if(solidsMap.containsKey(tag)) {
                return true;
            }
        }
        return solidsMap.containsKey(item.asItem().getRegistryName());
    }

    public int getSolidAmount(IItemProvider item) {
        Collection<ResourceLocation> tags = TagUtils.getTags(item);
        for(ResourceLocation tag : tags) {
            if(solidsMap.containsKey(tag)) {
                return solidsMap.get(tag);
            }
        }

        return solidsMap.getOrDefault(item.asItem().getRegistryName(), 0);
    }

    @Override
    protected void useJson() {
        if(generateJson(Constants.Json.COMPOST_FILE, this)) {
            return;
        }

        try {
            List<CompostJson> registryJson = readJson();
            for (CompostJson entry : registryJson) {
                try {
                    if (itemExists(entry.getEntry())) {
                        solidsMap.put(new ResourceLocation(entry.getEntry()), entry.getAmount());
                    } else {
                        LogUtil.warn(String.format("%s: Entry \"%s\" does not exist...Skipping...", Constants.Json.COMPOST_FILE, entry.getEntry()));
                    }
                } catch (ResourceLocationException e) {
                    LogUtil.warn(String.format("%s: %s. Skipping...", Constants.Json.COMPOST_FILE, e.getMessage()));
                }
            }
        } catch (JsonParseException e) {
            LogUtil.error(String.format("Malformed %s", Constants.Json.COMPOST_FILE));
            LogUtil.error(e.getMessage());
            if(e.getMessage().contains("IllegalStateException")) {
                LogUtil.error("Please consider deleting the file and regenerating it.");
            }
            LogUtil.error("Falling back to defaults");
            clear();
            ModRegistries.BUS.getDefaults().forEach(registry -> registry.registerCompost(this));
        }
    }

    private boolean itemExists(String entry) throws ResourceLocationException {
        ResourceLocation itemID = new ResourceLocation(entry);
        return TagUtils.isTag(itemID) || ForgeRegistries.BLOCKS.containsKey(itemID) || ForgeRegistries.ITEMS.containsKey(itemID);
    }

    private List<CompostJson> readJson() throws JsonParseException {
        Type listType = new TypeToken<ArrayList<CompostJson>>() {
        }.getType();
        Gson gson = new GsonBuilder().registerTypeAdapter(listType, new AnnotatedDeserializer<ArrayList<CompostJson>>())
            .create();
        Path path = Constants.Json.baseJsonPath.resolve(Constants.Json.COMPOST_FILE);
        List<CompostJson> registryJson = null;
        try {
            StringBuilder builder = new StringBuilder();
            Files.readAllLines(path).forEach(builder::append);
            registryJson = gson.fromJson(builder.toString(), listType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return registryJson;
    }

    public void addSolid(IItemProvider item, int solidAmount) {
        addSolid(item.asItem().getRegistryName(), solidAmount);
    }

    public void addSolid(ResourceLocation tag, int solidAmount) {
        List<ResourceLocation> idList = TagUtils.getTagsOwnedBy(tag);

        for(ResourceLocation id : idList) {
            if(solidsMap.containsKey(id)) {
                LogUtil.info(String.format("ID: %s falls under Tag: %s. Removing %s ...", id.toString(), tag.toString(), id.toString()));
                solidsMap.remove(id);
            }
        }

        // Does a tag who owns me already exist in the map?
        Collection<ResourceLocation> tags = TagUtils.getTags(tag);
        if(tags != null) {
            for(ResourceLocation id : tags) {
                if(solidsMap.containsKey(id)) {
                    LogUtil.info(String.format("Tag: %s already registered. Skipping item %s ...", id.toString(), tag));
                    return;
                }
            }
        }

        if(solidsMap.containsKey(tag)) {
            LogUtil.info(String.format("Tag: %s already registered. Skipping...", tag));
            return;
        }
        insertIntoMap(tag, solidAmount);
    }

    private void insertIntoMap(ResourceLocation id, int amount) {
        solidsMap.put(id, amount);
    }

    @Override
    public void clear() {
        solidsMap.clear();
    }

    public List<CompostJson> toJSONReady() {
        List<CompostJson> gsonList = new ArrayList<>();

        for(Map.Entry<ResourceLocation, Integer> entry : solidsMap.entrySet()) {
            gsonList.add(new CompostJson(entry.getKey().toString(), entry.getValue()));
        }

        return gsonList;
    }
}
