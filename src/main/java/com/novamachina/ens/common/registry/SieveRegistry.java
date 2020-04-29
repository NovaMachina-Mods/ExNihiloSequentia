package com.novamachina.ens.common.registry;

import com.novamachina.ens.common.item.mesh.EnumMesh;
import com.novamachina.ens.common.registry.registryitem.SieveRegistryItem;
import com.novamachina.ens.common.setup.Registration;
import com.novamachina.ens.common.utility.Constants;
import java.util.ArrayList;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

public class SieveRegistry extends IRegistry<ArrayList<SieveRegistryItem>> {

    @Override
    protected void useJsonRegistry() {

    }

    @Override
    protected void useDefaultRegistry() {
        //Stone Pebble
        register(Blocks.DIRT.getRegistryName().toString(),
            Registration.pebbleMap.get(Constants.Items.STONE_PEBBLE).get(), 1.0F, EnumMesh.STRING);
        register(Blocks.DIRT.getRegistryName().toString(),
            Registration.pebbleMap.get(Constants.Items.STONE_PEBBLE).get(), 1.0F, EnumMesh.STRING);
        register(Blocks.DIRT.getRegistryName().toString(),
            Registration.pebbleMap.get(Constants.Items.STONE_PEBBLE).get(), 0.5F, EnumMesh.STRING);
        register(Blocks.DIRT.getRegistryName().toString(),
            Registration.pebbleMap.get(Constants.Items.STONE_PEBBLE).get(), 0.5F, EnumMesh.STRING);
        register(Blocks.DIRT.getRegistryName().toString(),
            Registration.pebbleMap.get(Constants.Items.STONE_PEBBLE).get(), 0.1F, EnumMesh.STRING);
        register(Blocks.DIRT.getRegistryName().toString(),
            Registration.pebbleMap.get(Constants.Items.STONE_PEBBLE).get(), 0.1F, EnumMesh.STRING);
        //Granite Pebble
        register(Blocks.DIRT.getRegistryName().toString(),
            Registration.pebbleMap.get(Constants.Items.GRANITE_PEBBLE).get(), 0.5F,
            EnumMesh.STRING);
        register(Blocks.DIRT.getRegistryName().toString(),
            Registration.pebbleMap.get(Constants.Items.GRANITE_PEBBLE).get(), 0.1F,
            EnumMesh.STRING);
        //Diorite Pebble
        register(Blocks.DIRT.getRegistryName().toString(),
            Registration.pebbleMap.get(Constants.Items.DIORITE_PEBBLE).get(), 0.5F,
            EnumMesh.STRING);
        register(Blocks.DIRT.getRegistryName().toString(),
            Registration.pebbleMap.get(Constants.Items.DIORITE_PEBBLE).get(), 0.1F,
            EnumMesh.STRING);
        //Andesite Pebble
        register(Blocks.DIRT.getRegistryName().toString(),
            Registration.pebbleMap.get(Constants.Items.ANDESITE_PEBBLE).get(), 0.5F,
            EnumMesh.STRING);
        register(Blocks.DIRT.getRegistryName().toString(),
            Registration.pebbleMap.get(Constants.Items.ANDESITE_PEBBLE).get(), 0.1F,
            EnumMesh.STRING);

        register(Blocks.DIRT.getRegistryName().toString(), Items.WHEAT_SEEDS, 0.7F,
            EnumMesh.STRING);
        register(Blocks.DIRT.getRegistryName().toString(), Items.MELON_SEEDS, 0.35F,
            EnumMesh.STRING);
        register(Blocks.DIRT.getRegistryName().toString(), Items.PUMPKIN_SEEDS, 0.35F,
            EnumMesh.STRING);

        //Ancient Spores
        register(Blocks.DIRT.getRegistryName().toString(),
            Registration.resourceMap.get(Constants.Items.ANCIENT_SPORE).get(), 0.05F,
            EnumMesh.STRING);
        //Grass Seeds
        register(Blocks.DIRT.getRegistryName().toString(),
            Registration.resourceMap.get(Constants.Items.ANCIENT_SPORE).get(), 0.05F,
            EnumMesh.STRING);
    }

    public void register(String key, Item drop, float rarity, EnumMesh meshType) {
        if (registry.containsKey(key)) {
            registry.get(key).add(new SieveRegistryItem(drop, rarity, meshType));
        } else {
            ArrayList<SieveRegistryItem> list = new ArrayList<SieveRegistryItem>()
                .add(new SieveRegistryItem(drop, rarity, meshType);
            registry.put(key, list);
        }
    }
}
