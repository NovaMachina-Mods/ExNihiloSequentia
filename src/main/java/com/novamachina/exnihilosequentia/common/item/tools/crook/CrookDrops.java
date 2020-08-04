package com.novamachina.exnihilosequentia.common.item.tools.crook;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.novamachina.exnihilosequentia.common.json.CrookJson;
import com.novamachina.exnihilosequentia.common.setup.ModItems;
import com.novamachina.exnihilosequentia.common.utility.Config;
import com.novamachina.exnihilosequentia.common.utility.Constants;
import com.novamachina.exnihilosequentia.common.utility.Constants.Items;
import com.novamachina.exnihilosequentia.common.utility.LogUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CrookDrops {

    public static final int numberOfTimesToTestVanillaDrops = Config.NUMBER_OF_TIMES_TO_TEST_VANILLA_DROPS
        .get();

    private static final List<CrookDropEntry> crookDrops  = new ArrayList<>();

    public static void initialize() {
        if(Config.USE_JSON_REGISTRIES.get()) {
            addEntries(readJson());
        } else {
            useDefaults();
        }
    }

    private static void addEntries(List<CrookJson> list) {
        for(CrookJson entry : list) {
            if(itemExists(entry.getResult())) {
                ResourceLocation entryID = new ResourceLocation(entry.getResult());
                addDrop(entryID, entry.getRarity());
            } else {
                LogUtil.warn(String.format("Entry \"%s\" does not exist...Skipping...", entry.getResult()));
            }
        }
    }

    private static boolean itemExists(String entry) {
        ResourceLocation itemID = new ResourceLocation(entry);
        return ForgeRegistries.BLOCKS.containsKey(itemID) || ForgeRegistries.ITEMS.containsKey(itemID);
    }

    private static List<CrookJson> readJson() {
        Path path = Constants.Json.baseJsonPath.resolve(Constants.Json.CROOK_FILE);
        List<CrookJson> list = null;
        try {
            StringBuilder builder = new StringBuilder();
            Files.readAllLines(path).forEach(builder::append);
            Type listType = new TypeToken<ArrayList<CrookJson>>(){}.getType();
            list = new Gson().fromJson(builder.toString(), listType);
            LogUtil.info(builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static void useDefaults() {
        addDrop(ModItems.resourceMap.get(Items.SILKWORM).get(), 0.1F);
    }

    public static List<ItemStack> getDrops() {
        List<ItemStack> drops = new ArrayList<>();

        for (CrookDropEntry item : crookDrops) {
            Random random = new Random();
            if (random.nextFloat() <= item.getRarity()) {
                drops.add(new ItemStack(ForgeRegistries.ITEMS.getValue(item.getItem())));
            }
        }

        return drops;
    }

    public static void addDrop(Item item, float rarity) {
        addDrop(item.getRegistryName(), rarity);
    }

    public static void addDrop(ResourceLocation item, float rarity) {
        crookDrops.add(new CrookDropEntry(item, rarity));
    }

    public static List<CrookJson> toJSONReady() {
        List<CrookJson> jsonList = new ArrayList<>();
        for(CrookDropEntry entry : crookDrops) {
            jsonList.add(new CrookJson(entry));
        }
        return jsonList;
    }
}