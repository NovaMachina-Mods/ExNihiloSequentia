package com.novamachina.exnihilosequentia.common.registries.sieve;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.novamachina.exnihilosequentia.common.api.ExNihiloRegistries;
import com.novamachina.exnihilosequentia.common.compat.jei.sieve.SieveRecipe;
import com.novamachina.exnihilosequentia.common.item.mesh.EnumMesh;
import com.novamachina.exnihilosequentia.common.json.AnnotatedDeserializer;
import com.novamachina.exnihilosequentia.common.json.SieveJson;
import com.novamachina.exnihilosequentia.common.registries.AbstractModRegistry;
import com.novamachina.exnihilosequentia.common.utility.Config;
import com.novamachina.exnihilosequentia.common.utility.Constants;
import com.novamachina.exnihilosequentia.common.utility.LogUtil;
import com.novamachina.exnihilosequentia.common.utility.TagUtils;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.ResourceLocationException;
import net.minecraftforge.registries.ForgeRegistries;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class SieveRegistry extends AbstractModRegistry {

    private final Map<ResourceLocation, List<SieveDropEntry>> stringMeshMap = new HashMap<>();
    private final Map<ResourceLocation, List<SieveDropEntry>> flintMeshMap = new HashMap<>();
    private final Map<ResourceLocation, List<SieveDropEntry>> ironMeshMap = new HashMap<>();
    private final Map<ResourceLocation, List<SieveDropEntry>> diamondMeshMap = new HashMap<>();

    private final Map<ResourceLocation, List<SieveDropEntry>> waterloggedStringMeshMap = new HashMap<>();
    private final Map<ResourceLocation, List<SieveDropEntry>> waterloggedFlintMeshMap = new HashMap<>();
    private final Map<ResourceLocation, List<SieveDropEntry>> waterloggedIronMeshMap = new HashMap<>();
    private final Map<ResourceLocation, List<SieveDropEntry>> waterloggedDiamondMeshMap = new HashMap<>();

    private final boolean flattenRecipes = Config.FLATTEN_SIEVE_RECIPES.get();

    public SieveRegistry(ExNihiloRegistries.ModBus bus) {
        bus.register(this);
    }

    public void addDrop(Block input, Item result, float rarity, EnumMesh meshType, boolean isWaterlogged) {
        addDrop(input.getRegistryName(), result.getRegistryName(), rarity, meshType, isWaterlogged);
    }

    public void addDrop(ResourceLocation input, ResourceLocation result, float rarity, EnumMesh meshType, boolean isWaterlogged) {
        if (isWaterlogged) {
            insertIntoWaterloggedMap(input, result, rarity, meshType);
        } else {
            insertIntoDryMap(input, result, rarity, meshType);
        }
    }

    private void insertIntoDryMap(ResourceLocation input, ResourceLocation result, float rarity, EnumMesh meshType) {
        switch (meshType) {
            case STRING:
                insertIntoMap(stringMeshMap, input, result, rarity);
                break;
            case FLINT:
                insertIntoMap(flintMeshMap, input, result, rarity);
                break;
            case IRON:
                insertIntoMap(ironMeshMap, input, result, rarity);
                break;
            case DIAMOND:
                insertIntoMap(diamondMeshMap, input, result, rarity);
                break;
            default:
                LogUtil.warn(String.format("Mesh type \"%s\" does not exist.", meshType.getName()));
                break;
        }
    }

    private void insertIntoWaterloggedMap(ResourceLocation input, ResourceLocation result, float rarity, EnumMesh meshType) {
        switch (meshType) {
            case STRING:
                insertIntoMap(waterloggedStringMeshMap, input, result, rarity);
                break;
            case FLINT:
                insertIntoMap(waterloggedFlintMeshMap, input, result, rarity);
                break;
            case IRON:
                insertIntoMap(waterloggedIronMeshMap, input, result, rarity);
                break;
            case DIAMOND:
                insertIntoMap(waterloggedDiamondMeshMap, input, result, rarity);
                break;
            default:
                LogUtil.warn(String.format("Mesh type \"%s\" does not exist.", meshType.getName()));
                break;
        }
    }

    private void insertIntoMap(Map<ResourceLocation, List<SieveDropEntry>> map, ResourceLocation input, ResourceLocation result, float rarity) {
        if (map.containsKey(input)) {
            map.get(input).add(new SieveDropEntry(result, rarity));
        } else {
            List<SieveDropEntry> list = new ArrayList<>();
            list.add(new SieveDropEntry(result, rarity));
            map.put(input, list);
        }
    }

    public List<SieveDropEntry> getDrops(Block input, EnumMesh meshType, boolean isWaterlogged) {
        return getDrops(input.getRegistryName(), meshType, isWaterlogged);
    }

    public List<SieveDropEntry> getDrops(ResourceLocation input, EnumMesh meshType, boolean isWaterlogged) {
        List<SieveDropEntry> returnList = new ArrayList<>();
        switch (meshType) {
            case DIAMOND:
                returnList.addAll(retrieveFromMap(isWaterlogged ? waterloggedDiamondMeshMap : diamondMeshMap, input));
                if (!flattenRecipes) {
                    break;
                }
            case IRON:
                returnList.addAll(retrieveFromMap(isWaterlogged ? waterloggedIronMeshMap : ironMeshMap, input));
                if (!flattenRecipes) {
                    break;
                }
            case FLINT:
                returnList.addAll(retrieveFromMap(isWaterlogged ? waterloggedFlintMeshMap : flintMeshMap, input));
                if (!flattenRecipes) {
                    break;
                }
            case STRING:
                returnList.addAll(retrieveFromMap(isWaterlogged ? waterloggedStringMeshMap : stringMeshMap, input));
                break;
            default:
                LogUtil.warn(String.format("Mesh type \"%s\" does not exist.", meshType.getName()));
                break;
        }
        return returnList;
    }

    @Override
    public void useJson() {
        if (generateJson(Constants.Json.SIEVE_FILE, this)) {
            return;
        }

        try {
            List<SieveJson> registryJson = readJson();
            for (SieveJson entry : registryJson) {
                try {
                    if (itemExists(entry.getInput())) {
                        ResourceLocation inputID = new ResourceLocation(entry.getInput());
                        if (itemExists(entry.getResult())) {
                            ResourceLocation outputID = new ResourceLocation(entry.getResult());
                            addDrop(inputID, outputID, entry.getRarity(), entry.getMesh(), entry.isWaterlogged());
                        } else {
                            LogUtil.warn(String
                                .format("%s: Entry \"%s\" does not exist...Skipping...", Constants.Json.SIEVE_FILE, entry
                                    .getResult()));
                        }
                    } else {
                        LogUtil.warn(String
                            .format("%s: Entry \"%s\" does not exist...Skipping...", Constants.Json.SIEVE_FILE, entry
                                .getInput()));
                    }
                } catch (ResourceLocationException e) {
                    LogUtil.warn(String.format("%s: %s. Skipping...", Constants.Json.SIEVE_FILE, e.getMessage()));
                }
            }
        } catch (JsonParseException e) {
            LogUtil.error(String.format("Malformed %s", Constants.Json.SIEVE_FILE));
            LogUtil.error(e.getMessage());
            if (e.getMessage().contains("IllegalStateException")) {
                LogUtil.error("Please consider deleting the file and regenerating it.");
            }
            LogUtil.error("Falling back to defaults");
            clear();
            ExNihiloRegistries.BUS.getDefaults().forEach(registry -> registry.registerSieve(this));
        }
    }

    private boolean itemExists(String entry) throws ResourceLocationException {
        ResourceLocation itemID = new ResourceLocation(entry);
        return TagUtils.isTag(itemID) || ForgeRegistries.BLOCKS.containsKey(itemID) || ForgeRegistries.ITEMS
            .containsKey(itemID);
    }

    protected List<SieveJson> readJson() throws JsonParseException {
        Type listType = new TypeToken<ArrayList<SieveJson>>() {
        }.getType();
        Gson gson = new GsonBuilder().registerTypeAdapter(listType, new AnnotatedDeserializer<ArrayList<SieveJson>>())
            .create();
        Path path = Constants.Json.baseJsonPath.resolve(Constants.Json.SIEVE_FILE);
        List<SieveJson> registryJson = null;
        try {
            StringBuilder builder = new StringBuilder();
            Files.readAllLines(path).forEach(builder::append);
            registryJson = gson.fromJson(builder.toString(), listType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return registryJson;
    }


    public void addMultiMeshDrop(Block input, Item result, Float stringRarity,
                                 Float flintRarity, Float ironRarity, Float diamondRarity, boolean isWaterlogged) {
        addMultiMeshDrop(input.getRegistryName(), result
            .getRegistryName(), stringRarity, flintRarity, ironRarity, diamondRarity, isWaterlogged);
    }

    private void addMultiMeshDrop(ResourceLocation input, ResourceLocation result, Float stringRarity,
                                  Float flintRarity, Float ironRarity, Float diamondRarity, boolean isWaterlogged) {
        if (stringRarity != null) {
            addDrop(input, result, stringRarity, EnumMesh.STRING, isWaterlogged);
        }
        if (flintRarity != null) {
            addDrop(input, result, flintRarity, EnumMesh.FLINT, isWaterlogged);
        }
        if (ironRarity != null) {
            addDrop(input, result, ironRarity, EnumMesh.IRON, isWaterlogged);
        }
        if (diamondRarity != null) {
            addDrop(input, result, diamondRarity, EnumMesh.DIAMOND, isWaterlogged);
        }
    }

    private Collection<SieveDropEntry> retrieveFromMap(
        Map<ResourceLocation, List<SieveDropEntry>> dropsMap, ResourceLocation input) {
        List<SieveDropEntry> returnList = new ArrayList<>();
        Collection<ResourceLocation> tags = TagUtils.getTags(input);
        for (ResourceLocation tag : tags) {
            if (dropsMap.containsKey(tag)) {
                returnList.addAll(dropsMap.get(tag));
                return returnList;
            }
        }

        if (!dropsMap.containsKey(input)) {
            return returnList;
        }
        returnList.addAll(dropsMap.get(input));
        return returnList;
    }

    public boolean isBlockSiftable(Block block, EnumMesh mesh, boolean isWaterlogged) {
        Objects.requireNonNull(block.getRegistryName());

        ResourceLocation blockId = block.getRegistryName();

        boolean isSiftable = false;

        switch (mesh) {
            case DIAMOND: {
                isSiftable = isWaterlogged ? waterloggedDiamondMeshMap.containsKey(blockId) : diamondMeshMap
                    .containsKey(blockId);
                if (!flattenRecipes || isSiftable) {
                    break;
                }
            }
            case IRON: {
                isSiftable = isWaterlogged ? waterloggedIronMeshMap.containsKey(blockId) : ironMeshMap
                    .containsKey(blockId);
                if (!flattenRecipes || isSiftable) {
                    break;
                }
            }
            case FLINT: {
                isSiftable = isWaterlogged ? waterloggedFlintMeshMap.containsKey(blockId) : flintMeshMap
                    .containsKey(blockId);
                if (!flattenRecipes || isSiftable) {
                    break;
                }
            }
            case STRING: {
                isSiftable = isWaterlogged ? waterloggedStringMeshMap.containsKey(blockId) : stringMeshMap
                    .containsKey(blockId);
                break;
            }
        }
        return isSiftable;
    }

    @Override
    public void clear() {
        stringMeshMap.clear();
        flintMeshMap.clear();
        ironMeshMap.clear();
        diamondMeshMap.clear();
    }

    @Override
    public List<SieveJson> toJSONReady() {
        List<SieveJson> jsonList = new ArrayList<>();
        for (Map.Entry<ResourceLocation, List<SieveDropEntry>> entry : stringMeshMap.entrySet()) {
            ResourceLocation input = entry.getKey();
            List<SieveDropEntry> dropList = entry.getValue();
            for (SieveDropEntry dropEntry : dropList) {
                jsonList.add(new SieveJson(input.toString(), dropEntry.getResult().toString(), dropEntry
                    .getRarity(), EnumMesh.STRING, false));
            }
        }

        for (Map.Entry<ResourceLocation, List<SieveDropEntry>> entry : flintMeshMap.entrySet()) {
            ResourceLocation input = entry.getKey();
            List<SieveDropEntry> dropList = entry.getValue();
            for (SieveDropEntry dropEntry : dropList) {
                jsonList.add(new SieveJson(input.toString(), dropEntry.getResult().toString(), dropEntry
                    .getRarity(), EnumMesh.FLINT, false));
            }
        }

        for (Map.Entry<ResourceLocation, List<SieveDropEntry>> entry : ironMeshMap.entrySet()) {
            ResourceLocation input = entry.getKey();
            List<SieveDropEntry> dropList = entry.getValue();
            for (SieveDropEntry dropEntry : dropList) {
                jsonList.add(new SieveJson(input.toString(), dropEntry.getResult().toString(), dropEntry
                    .getRarity(), EnumMesh.IRON, false));
            }
        }

        for (Map.Entry<ResourceLocation, List<SieveDropEntry>> entry : diamondMeshMap.entrySet()) {
            ResourceLocation input = entry.getKey();
            List<SieveDropEntry> dropList = entry.getValue();
            for (SieveDropEntry dropEntry : dropList) {
                jsonList.add(new SieveJson(input.toString(), dropEntry.getResult().toString(), dropEntry
                    .getRarity(), EnumMesh.DIAMOND, false));
            }
        }

        for (Map.Entry<ResourceLocation, List<SieveDropEntry>> entry : waterloggedStringMeshMap.entrySet()) {
            ResourceLocation input = entry.getKey();
            List<SieveDropEntry> dropList = entry.getValue();
            for (SieveDropEntry dropEntry : dropList) {
                jsonList.add(new SieveJson(input.toString(), dropEntry.getResult().toString(), dropEntry
                    .getRarity(), EnumMesh.STRING, true));
            }
        }

        for (Map.Entry<ResourceLocation, List<SieveDropEntry>> entry : waterloggedFlintMeshMap.entrySet()) {
            ResourceLocation input = entry.getKey();
            List<SieveDropEntry> dropList = entry.getValue();
            for (SieveDropEntry dropEntry : dropList) {
                jsonList.add(new SieveJson(input.toString(), dropEntry.getResult().toString(), dropEntry
                    .getRarity(), EnumMesh.FLINT, true));
            }
        }

        for (Map.Entry<ResourceLocation, List<SieveDropEntry>> entry : waterloggedIronMeshMap.entrySet()) {
            ResourceLocation input = entry.getKey();
            List<SieveDropEntry> dropList = entry.getValue();
            for (SieveDropEntry dropEntry : dropList) {
                jsonList.add(new SieveJson(input.toString(), dropEntry.getResult().toString(), dropEntry
                    .getRarity(), EnumMesh.IRON, true));
            }
        }

        for (Map.Entry<ResourceLocation, List<SieveDropEntry>> entry : waterloggedDiamondMeshMap.entrySet()) {
            ResourceLocation input = entry.getKey();
            List<SieveDropEntry> dropList = entry.getValue();
            for (SieveDropEntry dropEntry : dropList) {
                jsonList.add(new SieveJson(input.toString(), dropEntry.getResult().toString(), dropEntry
                    .getRarity(), EnumMesh.DIAMOND, true));
            }
        }
        return jsonList;
    }

    public List<SieveRecipe> getDryRecipeList() {
        List<SieveRecipe> recipes = new ArrayList<>();

        recipes.addAll(collectRecipes(EnumMesh.STRING, stringMeshMap, false));
        recipes.addAll(collectRecipes(EnumMesh.FLINT, flintMeshMap, false));
        recipes.addAll(collectRecipes(EnumMesh.IRON, ironMeshMap, false));
        recipes.addAll(collectRecipes(EnumMesh.DIAMOND, diamondMeshMap, false));

        return recipes;
    }

    private List<SieveRecipe> collectRecipes(EnumMesh mesh, Map<ResourceLocation, List<SieveDropEntry>> dropMap, boolean isWaterlogged) {
        List<SieveRecipe> recipes = new ArrayList<>();
        for (ResourceLocation inputID : dropMap.keySet()) {
            SieveRecipe recipe = createRecipe(mesh, inputID, isWaterlogged);
            if (!recipes.contains(recipe)) {
                recipes.add(createRecipe(mesh, inputID, isWaterlogged));
            }
        }
        return recipes;
    }

    private SieveRecipe createRecipe(EnumMesh mesh, ResourceLocation inputID, boolean isWaterlogged) {
        List<List<ItemStack>> inputs = new ArrayList<>();
        inputs.add(Collections.singletonList(new ItemStack(mesh.getRegistryObject().get())));
        Tag<Block> blockTag = BlockTags.getCollection().get(inputID);
        List<ItemStack> inputBlocks = new ArrayList<>();
        if (blockTag != null) {
            inputBlocks.addAll(blockTag.getAllElements().stream().map(ItemStack::new).collect(Collectors.toList()));
        } else {
            inputBlocks.add(new ItemStack(ForgeRegistries.BLOCKS.getValue(inputID)));
        }
        List<SieveDropEntry> dropEntries = getDrops(ForgeRegistries.BLOCKS.getValue(inputID), mesh, isWaterlogged);
        List<ItemStack> drops = new ArrayList<>();
        for (SieveDropEntry entry : dropEntries) {
            boolean alreadyExists = false;
            ItemStack stack = new ItemStack(ForgeRegistries.ITEMS.getValue(entry.getResult()));
            for (ItemStack itemStack : drops) {
                if (itemStack.getItem() == stack.getItem()) {
                    itemStack.grow(1);
                    alreadyExists = true;
                }
            }

            if (!alreadyExists) {
                drops.add(new ItemStack(ForgeRegistries.ITEMS.getValue(entry.getResult())));
            }
        }
        inputs.add(inputBlocks);
        return new SieveRecipe(inputs, drops);
    }

    @Deprecated
    public List<SieveDropEntry> getAllDrops(ResourceLocation input, EnumMesh mesh, boolean isWaterlogged) {
        Block block = ForgeRegistries.BLOCKS.getValue(input);
        return getAllDrops(block, mesh, isWaterlogged);
    }

    @Deprecated
    private List<SieveDropEntry> getAllDrops(Block input, EnumMesh meshType, boolean isWaterlogged) {
        List<SieveDropEntry> returnList = new ArrayList<>();
        List<SieveDropEntry> list = null;
        ResourceLocation blockID = input.getRegistryName();
        switch (meshType) {
            case DIAMOND:
                list = isWaterlogged ? waterloggedDiamondMeshMap.get(blockID) : diamondMeshMap.get(blockID);
                if (list != null) {
                    for (SieveDropEntry element : list) {
                        if (!returnList.contains(element)) {
                            returnList.add(element);
                        }
                    }
                }
                if (!flattenRecipes) {
                    break;
                }
            case IRON:
                list = isWaterlogged ? waterloggedIronMeshMap.get(blockID) : ironMeshMap.get(blockID);
                if (list != null) {
                    for (SieveDropEntry element : list) {
                        if (!returnList.contains(element)) {
                            returnList.add(element);
                        }
                    }
                }
                if (!flattenRecipes) {
                    break;
                }
            case FLINT:
                list = isWaterlogged ? waterloggedFlintMeshMap.get(blockID) : flintMeshMap.get(blockID);
                if (list != null) {
                    for (SieveDropEntry element : list) {
                        if (!returnList.contains(element)) {
                            returnList.add(element);
                        }
                    }
                }
                if (!flattenRecipes) {
                    break;
                }
            case STRING:
                list = isWaterlogged ? waterloggedStringMeshMap.get(blockID) : stringMeshMap.get(blockID);
                if (list != null) {
                    for (SieveDropEntry element : list) {
                        if (!returnList.contains(element)) {
                            returnList.add(element);
                        }
                    }
                }
                break;
            default:
                LogUtil.warn(String.format("Mesh type \"%s\" does not exist.", meshType.getName()));
                break;
        }
        if (list == null) {
            list = new ArrayList<>();
        }
        return returnList;
    }

    public List<SieveRecipe> getWetRecipeList() {
        List<SieveRecipe> recipes = new ArrayList<>();

        recipes.addAll(collectRecipes(EnumMesh.STRING, waterloggedStringMeshMap, true));
        recipes.addAll(collectRecipes(EnumMesh.FLINT, waterloggedFlintMeshMap, true));
        recipes.addAll(collectRecipes(EnumMesh.IRON, waterloggedIronMeshMap, true));
        recipes.addAll(collectRecipes(EnumMesh.DIAMOND, waterloggedDiamondMeshMap, true));

        return recipes;
    }
}
