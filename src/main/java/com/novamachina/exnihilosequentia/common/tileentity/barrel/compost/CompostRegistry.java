package com.novamachina.exnihilosequentia.common.tileentity.barrel.compost;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.novamachina.exnihilosequentia.common.item.resources.EnumResource;
import com.novamachina.exnihilosequentia.common.item.tools.crook.CrookDropEntry;
import com.novamachina.exnihilosequentia.common.json.AnnotatedDeserializer;
import com.novamachina.exnihilosequentia.common.json.BarrelRegistriesJson;
import com.novamachina.exnihilosequentia.common.json.CompostJson;
import com.novamachina.exnihilosequentia.common.json.CrookJson;
import com.novamachina.exnihilosequentia.common.json.CrucibleRegistriesJson;
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
        try {
            BarrelRegistriesJson registriesJson = readJson();
            for (CompostJson entry : registriesJson.getCompostRegistry()) {
                if (itemExists(entry.getEntry())) {
                    solidsMap.put(new ResourceLocation(entry.getEntry()), entry.getAmount());
                } else {
                    LogUtil.warn(String.format("Entry \"%s\" does not exist...Skipping...", entry.getEntry()));
                }
            }
        } catch (JsonParseException e) {
            LogUtil.error("Malformed BarrelRegistries.json");
            LogUtil.error(e.getMessage());
            LogUtil.error("Falling back to defaults");
            clear();
            useDefaults();
        }
    }

    private boolean itemExists(String entry) {
        ResourceLocation itemID = new ResourceLocation(entry);
        return TagUtils.isTag(itemID) || ForgeRegistries.BLOCKS.containsKey(itemID) || ForgeRegistries.ITEMS.containsKey(itemID);
    }

    private BarrelRegistriesJson readJson() throws JsonParseException {
        Gson gson = new GsonBuilder().registerTypeAdapter(BarrelRegistriesJson.class, new AnnotatedDeserializer<BarrelRegistriesJson>()).create();
        Path path = Constants.Json.baseJsonPath.resolve(Constants.Json.BARREL_FILE);
        BarrelRegistriesJson barrelRegistriesJson = null;
        try {
            StringBuilder builder = new StringBuilder();
            Files.readAllLines(path).forEach(builder::append);
           barrelRegistriesJson = gson.fromJson(builder.toString(), BarrelRegistriesJson.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return barrelRegistriesJson;
    }

    protected void useDefaults() {
        addSolid(new ResourceLocation("minecraft:saplings"), 125);
        addSolid(new ResourceLocation("minecraft:leaves"), 125);
        addSolid(new ResourceLocation("minecraft:flowers"), 100);
        addSolid(new ResourceLocation("minecraft:fishes"), 150);
        addSolid(new ResourceLocation("forge:meat_cooked"), 200);
        addSolid(new ResourceLocation("forge:meat_uncooked"), 200);
        addSolid(new ResourceLocation("forge:seeds"), 80);
        addSolid(new ResourceLocation("forge:crops/wheat"), 80);
        addSolid(new ResourceLocation("forge:crops/carrot"), 100);
        addSolid(new ResourceLocation("forge:crops/beetroot"), 100);
        addSolid(new ResourceLocation("forge:crops/potato"), 100);
        addSolid(new ResourceLocation("forge:crops/nether_wart"), 100);
        addSolid(new ResourceLocation("forge:eggs"), 80);
        addSolid(new ResourceLocation("forge:string"), 40);
        addSolid(Items.ROTTEN_FLESH, 100);
        addSolid(Items.SPIDER_EYE, 80);
        addSolid(Items.BREAD, 160);
        addSolid(Blocks.BROWN_MUSHROOM, 100);
        addSolid(Blocks.RED_MUSHROOM, 100);
        addSolid(Items.PUMPKIN_PIE, 160);
        addSolid(ModItems.resourceMap.get(EnumResource.SILKWORM.getResourceName()).get(), 40);
        addSolid(ModItems.COOKED_SILKWORM.get(), 40);
        addSolid(Items.APPLE, 100);
        addSolid(Items.MELON_SLICE, 40);
        addSolid(Blocks.MELON, 1000/6);
        addSolid(Blocks.PUMPKIN, 1000/6);
        addSolid(Blocks.CARVED_PUMPKIN, 1000/6);
        addSolid(Blocks.JACK_O_LANTERN, 1000/6);
        addSolid(Blocks.CACTUS, 100);
        addSolid(Items.BAKED_POTATO, 150);
        addSolid(Items.POISONOUS_POTATO, 200);
        addSolid(Blocks.LILY_PAD, 100);
        addSolid(Blocks.VINE, 100);
        addSolid(Blocks.TALL_GRASS, 100);
        addSolid(Blocks.SUGAR_CANE, 80);
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
