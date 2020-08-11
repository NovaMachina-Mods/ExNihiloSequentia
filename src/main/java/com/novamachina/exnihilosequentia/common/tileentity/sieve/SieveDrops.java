package com.novamachina.exnihilosequentia.common.tileentity.sieve;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.novamachina.exnihilosequentia.common.item.mesh.EnumMesh;
import com.novamachina.exnihilosequentia.common.item.ore.EnumOre;
import com.novamachina.exnihilosequentia.common.item.resources.EnumResource;
import com.novamachina.exnihilosequentia.common.item.seeds.EnumSeed;
import com.novamachina.exnihilosequentia.common.json.AnnotatedDeserializer;
import com.novamachina.exnihilosequentia.common.json.SieveJson;
import com.novamachina.exnihilosequentia.common.setup.AbstractModRegistry;
import com.novamachina.exnihilosequentia.common.setup.ModBlocks;
import com.novamachina.exnihilosequentia.common.setup.ModItems;
import com.novamachina.exnihilosequentia.common.setup.ModRegistries;
import com.novamachina.exnihilosequentia.common.utility.Config;
import com.novamachina.exnihilosequentia.common.utility.Constants;
import com.novamachina.exnihilosequentia.common.utility.LogUtil;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

import com.novamachina.exnihilosequentia.common.utility.TagUtils;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public class SieveDrops extends AbstractModRegistry {

    private final Map<ResourceLocation, List<SieveDropEntry>> stringMeshMap = new HashMap<>();
    private final Map<ResourceLocation, List<SieveDropEntry>> flintMeshMap = new HashMap<>();
    private final Map<ResourceLocation, List<SieveDropEntry>> ironMeshMap = new HashMap<>();
    private final Map<ResourceLocation, List<SieveDropEntry>> diamondMeshMap = new HashMap<>();

    private final Map<ResourceLocation, List<SieveDropEntry>> waterloggedStringMeshMap = new HashMap<>();
    private final Map<ResourceLocation, List<SieveDropEntry>> waterloggedFlintMeshMap = new HashMap<>();
    private final Map<ResourceLocation, List<SieveDropEntry>> waterloggedIronMeshMap = new HashMap<>();
    private final Map<ResourceLocation, List<SieveDropEntry>> waterloggedDiamondMeshMap = new HashMap<>();

    private final boolean flattenRecipes = Config.FLATTEN_SIEVE_RECIPES.get();

    public SieveDrops(ModRegistries.ModBus bus) {
        bus.register(this);
    }

    public void addDrop(Block input, Item result, float rarity, EnumMesh meshType, boolean isWaterlogged) {
        addDrop(input.getRegistryName(), result.getRegistryName(), rarity, meshType, isWaterlogged);
    }

    public void addDrop(ResourceLocation input, ResourceLocation result, float rarity, EnumMesh meshType, boolean isWaterlogged) {
        if(isWaterlogged) {
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

    public List<Item> getDrops(Block input, EnumMesh meshType, boolean isWaterlogged) {
        List<Item> returnList = new ArrayList<>();
        ResourceLocation blockID = input.getRegistryName();
        switch (meshType) {
            case DIAMOND:
                returnList.addAll(retrieveFromMap(isWaterlogged ? waterloggedDiamondMeshMap : diamondMeshMap, blockID));
                if(!flattenRecipes) {
                    break;
                }
            case IRON:
                returnList.addAll(retrieveFromMap(isWaterlogged ? waterloggedIronMeshMap : ironMeshMap, blockID));
                if(!flattenRecipes) {
                    break;
                }
            case FLINT:
                returnList.addAll(retrieveFromMap(isWaterlogged ? waterloggedFlintMeshMap : flintMeshMap, blockID));
                if(!flattenRecipes) {
                    break;
                }
            case STRING:
                returnList.addAll(retrieveFromMap(isWaterlogged ? waterloggedStringMeshMap : stringMeshMap, blockID));
                break;
            default:
                LogUtil.warn(String.format("Mesh type \"%s\" does not exist.", meshType.getName()));
                break;
        }
        return returnList;
    }

    @Override
    protected void useJson() {
        try {
            List<SieveJson> registryJson = readJson();
            for(SieveJson entry : registryJson) {
                if(itemExists(entry.getInput())) {
                    ResourceLocation inputID = new ResourceLocation(entry.getInput());
                    if(itemExists(entry.getResult())) {
                        ResourceLocation outputID = new ResourceLocation(entry.getResult());
                        addDrop(inputID, outputID, entry.getRarity(), entry.getMesh(), entry.isWaterlogged());
                    } else {
                        LogUtil.warn(String.format("Entry \"%s\" does not exist...Skipping...", entry.getResult()));
                    }
                } else {
                    LogUtil.warn(String.format("Entry \"%s\" does not exist...Skipping...", entry.getInput()));
                }
            }
        } catch (JsonParseException e) {
            LogUtil.error(String.format("Malformed %s", Constants.Json.SIEVE_FILE));
            LogUtil.error(e.getMessage());
            if(e.getMessage().contains("IllegalStateException")) {
                LogUtil.error("Please consider deleting the file and regenerating it.");
            }
            LogUtil.error("Falling back to defaults");
            clear();
            useDefaults();
        }
    }

    private boolean itemExists(String entry) {
        ResourceLocation itemID = new ResourceLocation(entry);
        return TagUtils.isTag(itemID) || ForgeRegistries.BLOCKS.containsKey(itemID) || ForgeRegistries.ITEMS.containsKey(itemID);
    }

    protected List<SieveJson> readJson() throws JsonParseException {
        Type listType = new TypeToken<ArrayList<SieveJson>>(){}.getType();
        Gson gson = new GsonBuilder().registerTypeAdapter(listType, new AnnotatedDeserializer<ArrayList<SieveJson>>()).create();
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

    @Override
    protected void useDefaults() {
        // Stone Pebble
        addDrop(Blocks.DIRT,
            ModItems.pebbleMap.get(Constants.Items.PEBBLE_STONE).get(), 1.0F, EnumMesh.STRING, false);
        addDrop(Blocks.DIRT,
            ModItems.pebbleMap.get(Constants.Items.PEBBLE_STONE).get(), 1.0F, EnumMesh.STRING, false);
        addDrop(Blocks.DIRT,
            ModItems.pebbleMap.get(Constants.Items.PEBBLE_STONE).get(), 0.5F, EnumMesh.STRING, false);
        addDrop(Blocks.DIRT,
            ModItems.pebbleMap.get(Constants.Items.PEBBLE_STONE).get(), 0.5F, EnumMesh.STRING, false);
        addDrop(Blocks.DIRT,
            ModItems.pebbleMap.get(Constants.Items.PEBBLE_STONE).get(), 0.1F, EnumMesh.STRING, false);
        addDrop(Blocks.DIRT,
            ModItems.pebbleMap.get(Constants.Items.PEBBLE_STONE).get(), 0.1F, EnumMesh.STRING, false);

        // Andesite Pebble
        addDrop(Blocks.DIRT,
            ModItems.pebbleMap.get(Constants.Items.PEBBLE_ANDESITE).get(), 0.5F, EnumMesh.STRING, false);
        addDrop(Blocks.DIRT,
            ModItems.pebbleMap.get(Constants.Items.PEBBLE_ANDESITE).get(), 0.1F, EnumMesh.STRING, false);

        // Diorite Pebble
        addDrop(Blocks.DIRT,
            ModItems.pebbleMap.get(Constants.Items.PEBBLE_DIORITE).get(), 0.5F, EnumMesh.STRING, false);
        addDrop(Blocks.DIRT,
            ModItems.pebbleMap.get(Constants.Items.PEBBLE_DIORITE).get(), 0.1F, EnumMesh.STRING, false);

        // Granite Pebble
        addDrop(Blocks.DIRT,
            ModItems.pebbleMap.get(Constants.Items.PEBBLE_GRANITE).get(), 0.5F, EnumMesh.STRING, false);
        addDrop(Blocks.DIRT,
            ModItems.pebbleMap.get(Constants.Items.PEBBLE_GRANITE).get(), 0.1F, EnumMesh.STRING, false);

        // Vanilla Seeds
        addDrop(Blocks.DIRT, Items.WHEAT_SEEDS, 0.7F, EnumMesh.STRING, false);
        addDrop(Blocks.DIRT, Items.MELON_SEEDS, 0.35F, EnumMesh.STRING, false);
        addDrop(Blocks.DIRT, Items.PUMPKIN_SEEDS, 0.35F, EnumMesh.STRING, false);

        // Ancient Spores
        addDrop(Blocks.DIRT,
            ModItems.resourceMap.get(EnumResource.ANCIENT_SPORE.getResourceName()).get(), 0.05F, EnumMesh.STRING, false);

        // Grass Seeds
        addDrop(Blocks.DIRT,
            ModItems.resourceMap.get(EnumResource.GRASS_SEED.getResourceName()).get(), 0.05F, EnumMesh.STRING, false);

        // Misc Vanilla Drops
        addDrop(Blocks.SAND, Items.COCOA_BEANS, 0.03F, EnumMesh.STRING, false);
        addDrop(Blocks.SAND, Items.PRISMARINE_SHARD, 0.02F, EnumMesh.DIAMOND, false);

        // Flint
        addDrop(Blocks.GRAVEL, Items.FLINT, 0.25F, EnumMesh.STRING, false);
        addDrop(Blocks.GRAVEL, Items.FLINT, 0.25F, EnumMesh.FLINT, false);

        // Coal
        addDrop(Blocks.GRAVEL, Items.COAL, 0.125F, EnumMesh.FLINT, false);

        addDrop(Blocks.GRAVEL, Items.LAPIS_LAZULI, 0.05F, EnumMesh.FLINT, false);

        // Diamond
        addDrop(Blocks.GRAVEL, Items.DIAMOND, 0.008F, EnumMesh.IRON, false);
        addDrop(Blocks.GRAVEL, Items.DIAMOND, 0.016F, EnumMesh.DIAMOND, false);

        // Emerald
        addDrop(Blocks.GRAVEL, Items.EMERALD, 0.008F, EnumMesh.IRON, false);
        addDrop(Blocks.GRAVEL, Items.EMERALD, 0.016F, EnumMesh.DIAMOND, false);

        // Quartz
        addDrop(Blocks.SOUL_SAND, Items.QUARTZ, 1.0F, EnumMesh.FLINT, false);
        addDrop(Blocks.SOUL_SAND, Items.QUARTZ, 0.33F, EnumMesh.FLINT, false);
        addDrop(Blocks.SOUL_SAND, Items.QUARTZ, 1.0F, EnumMesh.DIAMOND, false);
        addDrop(Blocks.SOUL_SAND, Items.QUARTZ, 0.8F, EnumMesh.DIAMOND, false);

        // Nether Wart
        addDrop(Blocks.SOUL_SAND, Items.NETHER_WART, 0.1F, EnumMesh.STRING, false);

        // Ghast Tear
        addDrop(Blocks.SOUL_SAND, Items.GHAST_TEAR, 0.02F, EnumMesh.DIAMOND, false);

        addDrop(ModBlocks.DUST.get(), Items.BONE_MEAL, 0.2F, EnumMesh.STRING, false);

        // Gunpowder
        addDrop(ModBlocks.DUST.get(), Items.GUNPOWDER, 0.07F, EnumMesh.STRING, false);

        // Redstone
        addDrop(ModBlocks.DUST.get(), Items.REDSTONE, 0.125F, EnumMesh.IRON, false);
        addDrop(ModBlocks.DUST.get(), Items.REDSTONE, 0.25F, EnumMesh.DIAMOND, false);

        // Glowstone
        addDrop(ModBlocks.DUST.get(), Items.GLOWSTONE_DUST, 0.0625F, EnumMesh.IRON, false);

        // Blaze Powder
        addDrop(ModBlocks.DUST.get(), Items.BLAZE_POWDER, 0.05F, EnumMesh.IRON, false);

        // Ores
        for (EnumOre ore : EnumOre.values()) {
            switch (ore) {
                case IRON: {
                    addMultiMeshDrop(Blocks.GRAVEL,
                        ModItems.pieceMap.get(ore.getName()).get(), null, 0.1F, 0.15F, 0.25F, false);
                    addDrop(Blocks.SAND, ModItems.pieceMap.get(ore.getName()).get(), 0.5F, EnumMesh.DIAMOND, false);
                    break;
                }
                case GOLD: {
                    addMultiMeshDrop(
                        ModBlocks.CRUSHED_NETHERRACK.get(),
                        ModItems.pieceMap.get(ore.getName()).get(), null, 0.25F, 0.25F, 0.4F, false);
                    addMultiMeshDrop(Blocks.GRAVEL,
                        ModItems.pieceMap.get(ore.getName()).get(), null, 0.05F, 0.075F, 0.15F, false);
                    break;
                }
            }
        }

        // Seeds
        for (EnumSeed seed : EnumSeed.values()) {
            if(seed != EnumSeed.SEED_PICKLE && seed != EnumSeed.SEED_KELP) {
                addDrop(Blocks.DIRT, ModItems.seedMap.get(seed.getSeedName()).get(), 0.05F, EnumMesh.STRING, false);
            } else {
                addDrop(Blocks.SAND, ModItems.seedMap.get(seed.getSeedName()).get(), 0.05F, EnumMesh.STRING, true);
            }
        }

        getLeavesSaplings().forEach((input, drop) -> {
            LeavesBlock leavesBlock = (LeavesBlock) input;
            if (leavesBlock.getRegistryName().equals(new ResourceLocation("jungle_leaves"))) {
                addMultiMeshDrop(leavesBlock, drop, 0.025F, 0.05F, 0.075F, 0.1F, false);
            } else {
                addMultiMeshDrop(leavesBlock, drop, 0.05F, 0.10F, 0.15F, 0.2F, false);
            }

            // Apple
            addMultiMeshDrop(leavesBlock, Items.APPLE, 0.05F, 0.10F, 0.15F, 0.20F, false);

            // Golden Apple
            addMultiMeshDrop(leavesBlock, Items.GOLDEN_APPLE, 0.001F, 0.003F, 0.005F, 0.01F, false);

            // Silk Worm
            addMultiMeshDrop(leavesBlock,
                ModItems.resourceMap.get(Constants.Items.SILKWORM).get(), 0.025F, 0.05F, 0.1F, 0.2F, false);
        });

        // Coral Seeds
        addDrop(Blocks.SAND, ModItems.resourceMap.get(EnumResource.BLUE_CORAL_SEED.getResourceName()).get(), 0.05F, EnumMesh.IRON, true);
        addDrop(Blocks.SAND, ModItems.resourceMap.get(EnumResource.PURPLE_CORAL_SEED.getResourceName()).get(), 0.05F, EnumMesh.IRON, true);
        addDrop(Blocks.SAND, ModItems.resourceMap.get(EnumResource.PINK_CORAL_SEED.getResourceName()).get(), 0.05F, EnumMesh.IRON, true);
        addDrop(Blocks.SAND, ModItems.resourceMap.get(EnumResource.YELLOW_CORAL_SEED.getResourceName()).get(), 0.05F, EnumMesh.IRON, true);
        addDrop(Blocks.SAND, ModItems.resourceMap.get(EnumResource.RED_CORAL_SEED.getResourceName()).get(), 0.05F, EnumMesh.IRON, true);
    }

    private Map<Block, Item> getLeavesSaplings() {
        Map<Block, Item> saplingMap = new HashMap<>();
        saplingMap.put(Blocks.ACACIA_LEAVES, Items.ACACIA_SAPLING);
        saplingMap.put(Blocks.BIRCH_LEAVES, Items.BIRCH_SAPLING);
        saplingMap.put(Blocks.DARK_OAK_LEAVES, Items.DARK_OAK_SAPLING);
        saplingMap.put(Blocks.JUNGLE_LEAVES, Items.JUNGLE_SAPLING);
        saplingMap.put(Blocks.OAK_LEAVES, Items.OAK_SAPLING);
        saplingMap.put(Blocks.SPRUCE_LEAVES, Items.SPRUCE_SAPLING);

        return saplingMap;
    }

    private void addMultiMeshDrop(Block input, Item result, Float stringRarity,
                                         Float flintRarity, Float ironRarity, Float diamondRarity, boolean isWaterlogged) {
        addMultiMeshDrop(input.getRegistryName(), result.getRegistryName(), stringRarity, flintRarity,ironRarity, diamondRarity, isWaterlogged);
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

    private Collection<? extends Item> retrieveFromMap(
        Map<ResourceLocation, List<SieveDropEntry>> dropsMap, ResourceLocation input) {
        List<Item> returnList = new ArrayList<>();
        Random random = new Random();
        if (!dropsMap.containsKey(input)) {
            return returnList;
        }
        for (SieveDropEntry entry : dropsMap.get(input)) {
            if (random.nextFloat() <= entry.getRarity()) {
                returnList.add(ForgeRegistries.ITEMS.getValue(entry.getResult()));
            }
        }
        return returnList;
    }

    public boolean isBlockSiftable(Block block, EnumMesh mesh, boolean isWaterlogged) {
        Objects.requireNonNull(block.getRegistryName());

        ResourceLocation blockId = block.getRegistryName();

        boolean isSiftable = false;

        switch (mesh) {
            case DIAMOND: {
                isSiftable = isWaterlogged ? waterloggedDiamondMeshMap.containsKey(blockId) : diamondMeshMap.containsKey(blockId);
                if(!flattenRecipes || isSiftable) {
                    break;
                }
            }
            case IRON: {
                isSiftable = isWaterlogged ? waterloggedIronMeshMap.containsKey(blockId) : ironMeshMap.containsKey(blockId);
                if(!flattenRecipes || isSiftable) {
                    break;
                }
            }
            case FLINT: {
                isSiftable = isWaterlogged ? waterloggedFlintMeshMap.containsKey(blockId) : flintMeshMap.containsKey(blockId);
                if(!flattenRecipes || isSiftable) {
                    break;
                }
            }
            case STRING: {
                isSiftable = isWaterlogged ? waterloggedStringMeshMap.containsKey(blockId) : stringMeshMap.containsKey(blockId);
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
        for(Map.Entry<ResourceLocation, List<SieveDropEntry>> entry : stringMeshMap.entrySet()) {
            ResourceLocation input = entry.getKey();
            List<SieveDropEntry> dropList = entry.getValue();
            for(SieveDropEntry dropEntry : dropList) {
                jsonList.add(new SieveJson(input.toString(), dropEntry.getResult().toString(), dropEntry.getRarity(), EnumMesh.STRING, false));
            }
        }

        for(Map.Entry<ResourceLocation, List<SieveDropEntry>> entry : flintMeshMap.entrySet()) {
            ResourceLocation input = entry.getKey();
            List<SieveDropEntry> dropList = entry.getValue();
            for(SieveDropEntry dropEntry : dropList) {
                jsonList.add(new SieveJson(input.toString(), dropEntry.getResult().toString(), dropEntry.getRarity(), EnumMesh.FLINT, false));
            }
        }

        for(Map.Entry<ResourceLocation, List<SieveDropEntry>> entry : ironMeshMap.entrySet()) {
            ResourceLocation input = entry.getKey();
            List<SieveDropEntry> dropList = entry.getValue();
            for(SieveDropEntry dropEntry : dropList) {
                jsonList.add(new SieveJson(input.toString(), dropEntry.getResult().toString(), dropEntry.getRarity(), EnumMesh.IRON, false));
            }
        }

        for(Map.Entry<ResourceLocation, List<SieveDropEntry>> entry : diamondMeshMap.entrySet()) {
            ResourceLocation input = entry.getKey();
            List<SieveDropEntry> dropList = entry.getValue();
            for(SieveDropEntry dropEntry : dropList) {
                jsonList.add(new SieveJson(input.toString(), dropEntry.getResult().toString(), dropEntry.getRarity(), EnumMesh.DIAMOND, false));
            }
        }

        for(Map.Entry<ResourceLocation, List<SieveDropEntry>> entry : waterloggedStringMeshMap.entrySet()) {
            ResourceLocation input = entry.getKey();
            List<SieveDropEntry> dropList = entry.getValue();
            for(SieveDropEntry dropEntry : dropList) {
                jsonList.add(new SieveJson(input.toString(), dropEntry.getResult().toString(), dropEntry.getRarity(), EnumMesh.STRING, true));
            }
        }

        for(Map.Entry<ResourceLocation, List<SieveDropEntry>> entry : waterloggedFlintMeshMap.entrySet()) {
            ResourceLocation input = entry.getKey();
            List<SieveDropEntry> dropList = entry.getValue();
            for(SieveDropEntry dropEntry : dropList) {
                jsonList.add(new SieveJson(input.toString(), dropEntry.getResult().toString(), dropEntry.getRarity(), EnumMesh.FLINT, true));
            }
        }

        for(Map.Entry<ResourceLocation, List<SieveDropEntry>> entry : waterloggedIronMeshMap.entrySet()) {
            ResourceLocation input = entry.getKey();
            List<SieveDropEntry> dropList = entry.getValue();
            for(SieveDropEntry dropEntry : dropList) {
                jsonList.add(new SieveJson(input.toString(), dropEntry.getResult().toString(), dropEntry.getRarity(), EnumMesh.IRON, true));
            }
        }

        for(Map.Entry<ResourceLocation, List<SieveDropEntry>> entry : waterloggedDiamondMeshMap.entrySet()) {
            ResourceLocation input = entry.getKey();
            List<SieveDropEntry> dropList = entry.getValue();
            for(SieveDropEntry dropEntry : dropList) {
                jsonList.add(new SieveJson(input.toString(), dropEntry.getResult().toString(), dropEntry.getRarity(), EnumMesh.DIAMOND, true));
            }
        }
        return jsonList;
    }
}
