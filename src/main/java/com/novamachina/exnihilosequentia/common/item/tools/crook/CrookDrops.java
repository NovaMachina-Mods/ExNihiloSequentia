package com.novamachina.exnihilosequentia.common.item.tools.crook;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.novamachina.exnihilosequentia.common.json.AnnotatedDeserializer;
import com.novamachina.exnihilosequentia.common.json.CrookJson;
import com.novamachina.exnihilosequentia.common.json.CrucibleRegistriesJson;
import com.novamachina.exnihilosequentia.common.setup.AbstractModRegistry;
import com.novamachina.exnihilosequentia.common.setup.ModItems;
import com.novamachina.exnihilosequentia.common.setup.ModRegistries;
import com.novamachina.exnihilosequentia.common.utility.Config;
import com.novamachina.exnihilosequentia.common.utility.Constants;
import com.novamachina.exnihilosequentia.common.utility.Constants.Items;
import com.novamachina.exnihilosequentia.common.utility.LogUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CrookDrops extends AbstractModRegistry {

    public static final int numberOfTimesToTestVanillaDrops = Config.NUMBER_OF_TIMES_TO_TEST_VANILLA_DROPS.get();

    private final List<CrookDropEntry> crookDrops  = new ArrayList<>();

    public CrookDrops(ModRegistries.ModBus bus) {
        bus.register(this);
    }

    @Override
    public void useJson() {
        try {
            List<CrookJson> list = readJson();
            for(CrookJson entry : list) {
                if(itemExists(entry.getResult())) {
                    ResourceLocation entryID = new ResourceLocation(entry.getResult());
                    addDrop(entryID, entry.getRarity());
                } else {
                    LogUtil.warn(String.format("Entry \"%s\" does not exist...Skipping...", entry.getResult()));
                }
            }
        } catch (JsonParseException e) {
            LogUtil.error("Malformed CrookRegistry.json");
            LogUtil.error(e.getMessage());
            LogUtil.error("Falling back to defaults");
            clear();
            useDefaults();
        }
    }

    private boolean itemExists(String entry) {
        ResourceLocation itemID = new ResourceLocation(entry);
        return ForgeRegistries.BLOCKS.containsKey(itemID) || ForgeRegistries.ITEMS.containsKey(itemID);
    }

    private List<CrookJson> readJson() throws JsonParseException {
        Type listType = new TypeToken<ArrayList<CrookJson>>(){}.getType();
        Gson gson = new GsonBuilder().registerTypeAdapter(listType, new AnnotatedDeserializer<ArrayList<CrookJson>>()).create();
        Path path = Constants.Json.baseJsonPath.resolve(Constants.Json.CROOK_FILE);
        List<CrookJson> list = null;
        try {
            StringBuilder builder = new StringBuilder();
            Files.readAllLines(path).forEach(builder::append);
            list = gson.fromJson(builder.toString(), listType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void useDefaults() {
        addDrop(ModItems.resourceMap.get(Items.SILKWORM).get(), 0.1F);
    }

    public List<ItemStack> getDrops() {
        List<ItemStack> drops = new ArrayList<>();

        for (CrookDropEntry item : crookDrops) {
            Random random = new Random();
            if (random.nextFloat() <= item.getRarity()) {
                drops.add(new ItemStack(ForgeRegistries.ITEMS.getValue(item.getItem())));
            }
        }

        return drops;
    }

    public void addDrop(Item item, float rarity) {
        addDrop(item.getRegistryName(), rarity);
    }

    public void addDrop(ResourceLocation item, float rarity) {
        crookDrops.add(new CrookDropEntry(item, rarity));
    }

    @Override
    public List<CrookJson> toJSONReady() {
        List<CrookJson> jsonList = new ArrayList<>();
        for(CrookDropEntry entry : crookDrops) {
            jsonList.add(new CrookJson(entry));
        }
        return jsonList;
    }

    @Override
    public void clear() {
        crookDrops.clear();
    }
}