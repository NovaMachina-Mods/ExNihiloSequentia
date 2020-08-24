package com.novamachina.exnihilosequentia.common.registries.crook;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.novamachina.exnihilosequentia.common.api.ExNihiloRegistries;
import com.novamachina.exnihilosequentia.common.compat.jei.crook.CrookRecipe;
import com.novamachina.exnihilosequentia.common.init.ModItems;
import com.novamachina.exnihilosequentia.common.json.AnnotatedDeserializer;
import com.novamachina.exnihilosequentia.common.json.CrookJson;
import com.novamachina.exnihilosequentia.common.registries.AbstractModRegistry;
import com.novamachina.exnihilosequentia.common.utility.Constants;
import com.novamachina.exnihilosequentia.common.utility.Constants.Items;
import com.novamachina.exnihilosequentia.common.utility.LogUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.ResourceLocationException;
import net.minecraftforge.registries.ForgeRegistries;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CrookRegistry extends AbstractModRegistry {

    private final List<CrookDropEntry> crookDrops = new ArrayList<>();

    public CrookRegistry(ExNihiloRegistries.ModBus bus) {
        bus.register(this);
    }

    @Override
    public void useJson() {
        if (generateJson(Constants.Json.CROOK_FILE, this)) {
            return;
        }

        try {
            List<CrookJson> list = readJson();
            for (CrookJson entry : list) {
                try {
                    if (itemExists(entry.getResult())) {
                        ResourceLocation entryID = new ResourceLocation(entry.getResult());
                        addDrop(entryID, entry.getRarity());
                    } else {
                        LogUtil.warn(String
                            .format("%s: Entry \"%s\" does not exist...Skipping...", Constants.Json.CROOK_FILE, entry
                                .getResult()));
                    }
                } catch (ResourceLocationException e) {
                    LogUtil.warn(String.format("%s: %s. Skipping...", Constants.Json.CROOK_FILE, e.getMessage()));
                }
            }
        } catch (JsonParseException e) {
            LogUtil.error(String.format("Malformed %s", Constants.Json.CROOK_FILE));
            LogUtil.error(e.getMessage());
            if (e.getMessage().contains("IllegalStateException")) {
                LogUtil.error("Please consider deleting the file and regenerating it.");
            }
            LogUtil.error("Falling back to defaults");
            clear();
            useDefaults();
        }
    }

    private boolean itemExists(String entry) throws ResourceLocationException {
        ResourceLocation itemID = new ResourceLocation(entry);
        return ForgeRegistries.BLOCKS.containsKey(itemID) || ForgeRegistries.ITEMS.containsKey(itemID);
    }

    private List<CrookJson> readJson() throws JsonParseException {
        Type listType = new TypeToken<ArrayList<CrookJson>>() {
        }.getType();
        Gson gson = new GsonBuilder().registerTypeAdapter(listType, new AnnotatedDeserializer<ArrayList<CrookJson>>())
            .create();
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

    public List<CrookDropEntry> getDrops() {

        return crookDrops;
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
        for (CrookDropEntry entry : crookDrops) {
            jsonList.add(new CrookJson(entry));
        }
        return jsonList;
    }

    @Override
    public void clear() {
        crookDrops.clear();
    }

    public List<CrookRecipe> getRecipeList() {
        List<CrookRecipe> recipes = new ArrayList<>();
        List<ItemStack> inputs = BlockTags.getCollection().get(new ResourceLocation("minecraft", "leaves"))
            .getAllElements().stream().map(ItemStack::new).collect(Collectors.toList());
        for (CrookDropEntry entry : crookDrops) {
            List<ItemStack> outputs = new ArrayList<>();
            outputs.add(new ItemStack(ForgeRegistries.ITEMS.getValue(entry.getItem())));
            recipes.add(new CrookRecipe(inputs, outputs));
        }
        return recipes;
    }
}