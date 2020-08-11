package com.novamachina.exnihilosequentia.common.tileentity.crucible;

import com.novamachina.exnihilosequentia.common.json.CrucibleJson;
import com.novamachina.exnihilosequentia.common.setup.AbstractModRegistry;
import com.novamachina.exnihilosequentia.common.utility.TagUtils;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.ResourceLocationException;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseCrucibleMeltableItems extends AbstractModRegistry {
    private final Map<ResourceLocation, Meltable> meltableMap = new HashMap<>();

    public void addMeltable(ForgeRegistryEntry<? extends IItemProvider> entry, int amount, Fluid fluid) {
        addMeltable(entry.getRegistryName(), amount, fluid.getRegistryName());
    }

    public void addMeltable(ResourceLocation entry, int amount, ResourceLocation fluid) {
        insertIntoMap(entry, new Meltable(amount, fluid));
    }

    private void insertIntoMap(ResourceLocation name, Meltable meltable) {
        meltableMap.put(name, meltable);
    }

    public boolean isMeltable(ForgeRegistryEntry<? extends IItemProvider> entry) {
        return meltableMap.containsKey(entry.getRegistryName());
    }

    public Meltable getMeltable(ForgeRegistryEntry<? extends IItemProvider> entry) {
        return meltableMap.getOrDefault(entry.getRegistryName(), Meltable.DEFAULT);
    }

    @Override
    public void clear() {
        meltableMap.clear();
    }

    @Override
    public List<CrucibleJson> toJSONReady() {
        List<CrucibleJson> jsonList = new ArrayList<>();
        for(Map.Entry<ResourceLocation, Meltable> entry : meltableMap.entrySet()) {
            jsonList.add(new CrucibleJson(entry.getKey().toString(), entry.getValue()));
        }
        return jsonList;
    }

    protected boolean itemExists(String entry) throws ResourceLocationException {
        ResourceLocation itemID = new ResourceLocation(entry);
        return TagUtils.isTag(itemID) || ForgeRegistries.BLOCKS.containsKey(itemID) || ForgeRegistries.ITEMS.containsKey(itemID) || ForgeRegistries.FLUIDS.containsKey(itemID);
    }
}
