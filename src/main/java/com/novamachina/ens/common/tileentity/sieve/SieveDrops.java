package com.novamachina.ens.common.tileentity.sieve;

import com.novamachina.ens.common.item.mesh.EnumMesh;
import com.novamachina.ens.common.item.ore.EnumOre;
import com.novamachina.ens.common.item.resources.EnumResource;
import com.novamachina.ens.common.setup.ModBlocks;
import com.novamachina.ens.common.setup.ModItems;
import com.novamachina.ens.common.utility.Constants;
import com.novamachina.ens.common.utility.LogUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

public class SieveDrops {

    private static final Map<String, List<SieveDropEntry>> stringMeshMap  = new HashMap<>();
    private static final Map<String, List<SieveDropEntry>> flintMeshMap   = new HashMap<>();
    private static final Map<String, List<SieveDropEntry>> ironMeshMap    = new HashMap<>();
    private static final Map<String, List<SieveDropEntry>> diamondMeshMap = new HashMap<>();
    private static       boolean                           initialized    = false;

    public static void insertItem(String input, Item result, float rarity, EnumMesh meshType) {
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

    private static void insertIntoMap(Map<String, List<SieveDropEntry>> map, String input,
        Item result, float rarity) {
        if (map.containsKey(input)) {
            map.get(input).add(new SieveDropEntry(result, rarity));
        } else {
            List<SieveDropEntry> list = new ArrayList<>();
            list.add(new SieveDropEntry(result, rarity));
            map.put(input, list);
        }
    }

    public static List<Item> retieveResult(String input, EnumMesh meshType) {
        if (!initialized) {
            useDefaults();
            initialized = true;
        }

        List<Item> returnList = new ArrayList<>();
        switch (meshType) {
            case STRING:
                returnList.addAll(retrieveFromMap(stringMeshMap, input, meshType));
                break;
            case FLINT:
                returnList.addAll(retrieveFromMap(flintMeshMap, input, meshType));
                break;
            case IRON:
                returnList.addAll(retrieveFromMap(ironMeshMap, input, meshType));
                break;
            case DIAMOND:
                returnList.addAll(retrieveFromMap(diamondMeshMap, input, meshType));
                break;
            default:
                LogUtil.warn(String.format("Mesh type \"%s\" does not exist.", meshType.getName()));
                break;
        }
        return returnList;
    }

    private static void useDefaults() {
        // Stone Pebble
        insertItem(Blocks.DIRT.getRegistryName().toString(),
            ModItems.pebbleMap.get(Constants.Items.STONE_PEBBLE).get(), 1.0F, EnumMesh.STRING);
        insertItem(Blocks.DIRT.getRegistryName().toString(),
            ModItems.pebbleMap.get(Constants.Items.STONE_PEBBLE).get(), 1.0F, EnumMesh.STRING);
        insertItem(Blocks.DIRT.getRegistryName().toString(),
            ModItems.pebbleMap.get(Constants.Items.STONE_PEBBLE).get(), 0.5F, EnumMesh.STRING);
        insertItem(Blocks.DIRT.getRegistryName().toString(),
            ModItems.pebbleMap.get(Constants.Items.STONE_PEBBLE).get(), 0.5F, EnumMesh.STRING);
        insertItem(Blocks.DIRT.getRegistryName().toString(),
            ModItems.pebbleMap.get(Constants.Items.STONE_PEBBLE).get(), 0.1F, EnumMesh.STRING);
        insertItem(Blocks.DIRT.getRegistryName().toString(),
            ModItems.pebbleMap.get(Constants.Items.STONE_PEBBLE).get(), 0.1F, EnumMesh.STRING);

        // Andesite Pebble
        insertItem(Blocks.DIRT.getRegistryName().toString(),
            ModItems.pebbleMap.get(Constants.Items.ANDESITE_PEBBLE).get(), 0.5F, EnumMesh.STRING);
        insertItem(Blocks.DIRT.getRegistryName().toString(),
            ModItems.pebbleMap.get(Constants.Items.ANDESITE_PEBBLE).get(), 0.1F, EnumMesh.STRING);

        // Diorite Pebble
        insertItem(Blocks.DIRT.getRegistryName().toString(),
            ModItems.pebbleMap.get(Constants.Items.DIORITE_PEBBLE).get(), 0.5F, EnumMesh.STRING);
        insertItem(Blocks.DIRT.getRegistryName().toString(),
            ModItems.pebbleMap.get(Constants.Items.DIORITE_PEBBLE).get(), 0.1F, EnumMesh.STRING);

        // Granite Pebble
        insertItem(Blocks.DIRT.getRegistryName().toString(),
            ModItems.pebbleMap.get(Constants.Items.GRANITE_PEBBLE).get(), 0.5F, EnumMesh.STRING);
        insertItem(Blocks.DIRT.getRegistryName().toString(),
            ModItems.pebbleMap.get(Constants.Items.GRANITE_PEBBLE).get(), 0.1F, EnumMesh.STRING);

        // Vanilla Seeds
        insertItem(Blocks.DIRT.getRegistryName().toString(), Items.WHEAT_SEEDS, 0.7F,
            EnumMesh.STRING);
        insertItem(Blocks.DIRT.getRegistryName().toString(), Items.MELON_SEEDS, 0.35F,
            EnumMesh.STRING);
        insertItem(Blocks.DIRT.getRegistryName().toString(), Items.PUMPKIN_SEEDS, 0.35F,
            EnumMesh.STRING);

        // Ancient Spores
        insertItem(Blocks.DIRT.getRegistryName().toString(),
            ModItems.resourceMap.get(EnumResource.ANCIENT_SPORE.getResourceName()).get(), 0.05F,
            EnumMesh.STRING);

        // Grass Seeds
        insertItem(Blocks.DIRT.getRegistryName().toString(),
            ModItems.resourceMap.get(EnumResource.GRASS_SEED.getResourceName()).get(), 0.05F,
            EnumMesh.STRING);

        // Misc Vanilla Drops
        // Some Dye from sand
        insertItem(Blocks.SAND.getRegistryName().toString(), Items.PRISMARINE_SHARD, 0.02F,
            EnumMesh.DIAMOND);

        // Flint
        insertItem(Blocks.GRAVEL.getRegistryName().toString(), Items.FLINT, 0.25F, EnumMesh.STRING);
        insertItem(Blocks.GRAVEL.getRegistryName().toString(), Items.FLINT, 0.25F, EnumMesh.FLINT);

        // Coal
        insertItem(Blocks.GRAVEL.getRegistryName().toString(), Items.COAL, 0.125F, EnumMesh.FLINT);

        // Some dye from gravel

        // Diamond
        insertItem(Blocks.GRAVEL.getRegistryName().toString(), Items.DIAMOND, 0.008F,
            EnumMesh.IRON);
        insertItem(Blocks.GRAVEL.getRegistryName().toString(), Items.DIAMOND, 0.016F,
            EnumMesh.DIAMOND);

        // Emerald
        insertItem(Blocks.GRAVEL.getRegistryName().toString(), Items.EMERALD, 0.008F,
            EnumMesh.IRON);
        insertItem(Blocks.GRAVEL.getRegistryName().toString(), Items.EMERALD, 0.016F,
            EnumMesh.DIAMOND);

        // Quartz
        insertItem(Blocks.SOUL_SAND.getRegistryName().toString(), Items.QUARTZ, 1.0F,
            EnumMesh.FLINT);
        insertItem(Blocks.SOUL_SAND.getRegistryName().toString(), Items.QUARTZ, 0.33F,
            EnumMesh.FLINT);
        insertItem(Blocks.SOUL_SAND.getRegistryName().toString(), Items.QUARTZ, 1.0F,
            EnumMesh.DIAMOND);
        insertItem(Blocks.SOUL_SAND.getRegistryName().toString(), Items.QUARTZ, 0.8F,
            EnumMesh.DIAMOND);

        // Nether Wart
        insertItem(Blocks.SOUL_SAND.getRegistryName().toString(), Items.NETHER_WART, 0.1F,
            EnumMesh.STRING);

        // Ghast Tear
        insertItem(Blocks.SOUL_SAND.getRegistryName().toString(), Items.GHAST_TEAR, 0.02F,
            EnumMesh.DIAMOND);

        // Some Dye from dust

        // Gunpowder
        insertItem(ModBlocks.BLOCK_DUST.get().getRegistryName().toString(), Items.GUNPOWDER, 0.07F,
            EnumMesh.STRING);

        // Redstone
        insertItem(ModBlocks.BLOCK_DUST.get().getRegistryName().toString(), Items.REDSTONE, 0.125F,
            EnumMesh.IRON);
        insertItem(ModBlocks.BLOCK_DUST.get().getRegistryName().toString(), Items.REDSTONE, 0.25F,
            EnumMesh.DIAMOND);

        // Glowstone
        insertItem(ModBlocks.BLOCK_DUST.get().getRegistryName().toString(), Items.GLOWSTONE_DUST,
            0.0625F, EnumMesh.IRON);

        // Blaze Powder
        insertItem(ModBlocks.BLOCK_DUST.get().getRegistryName().toString(), Items.BLAZE_POWDER,
            0.05F, EnumMesh.IRON);

        for (EnumOre ore : EnumOre.values()) {
            switch (ore) {
                case IRON: {
                    multiMeshInsert(Blocks.GRAVEL.getRegistryName().toString(),
                        ModItems.pieceMap.get(ore.getName()).get(), null, 0.1F, 0.15F, 0.25F);
                    insertItem(ModBlocks.BLOCK_DUST.get().getRegistryName().toString(),
                        ModItems.pieceMap.get(ore.getName()).get(), 0.5F, EnumMesh.DIAMOND);
                    break;
                }
                case GOLD: {
                    multiMeshInsert(
                        ModBlocks.BLOCK_CRUSHED_NETHERRACK.get().getRegistryName().toString(),
                        ModItems.pieceMap.get(ore.getName()).get(), null, 0.25F, 0.25F, 0.4F);
                    multiMeshInsert(Blocks.GRAVEL.getRegistryName().toString(),
                        ModItems.pieceMap.get(ore.getName()).get(), null, 0.05F, 0.075F, 0.15F);
                    break;
                }
            }
        }
    }

    private static void multiMeshInsert(String input, Item result, Float stringRarity,
        Float flintRarity, Float ironRarity, Float diamondRarity) {
        if (stringRarity != null) {
            insertItem(input, result, stringRarity, EnumMesh.STRING);
        }
        if (flintRarity != null) {
            insertItem(input, result, flintRarity, EnumMesh.FLINT);
        }
        if (ironRarity != null) {
            insertItem(input, result, ironRarity, EnumMesh.IRON);
        }
        if (diamondRarity != null) {
            insertItem(input, result, diamondRarity, EnumMesh.DIAMOND);
        }
    }

    private static Collection<? extends Item> retrieveFromMap(
        Map<String, List<SieveDropEntry>> dropsMap, String input, EnumMesh meshType) {
        List<Item> returnList = new ArrayList<>();
        Random     random     = new Random();
        for (SieveDropEntry entry : dropsMap.get(input)) {
            if (random.nextFloat() <= entry.getRarity()) {
                returnList.add(entry.getResult());
            }
        }
        return returnList;
    }
}
