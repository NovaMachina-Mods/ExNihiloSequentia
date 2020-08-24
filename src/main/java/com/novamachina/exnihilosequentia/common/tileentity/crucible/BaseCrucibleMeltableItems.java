package com.novamachina.exnihilosequentia.common.tileentity.crucible;

import com.novamachina.exnihilosequentia.common.jei.compost.CompostRecipe;
import com.novamachina.exnihilosequentia.common.jei.crucible.CrucibleRecipe;
import com.novamachina.exnihilosequentia.common.json.CrucibleJson;
import com.novamachina.exnihilosequentia.common.setup.AbstractModRegistry;
import com.novamachina.exnihilosequentia.common.utility.LogUtil;
import com.novamachina.exnihilosequentia.common.utility.TagUtils;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.ResourceLocationException;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class BaseCrucibleMeltableItems extends AbstractModRegistry {
    private final Map<ResourceLocation, Meltable> meltableMap = new HashMap<>();

    public void addMeltable(ForgeRegistryEntry<? extends IItemProvider> entry, int amount, Fluid fluid) {
        addMeltable(entry.getRegistryName(), amount, fluid.getRegistryName());
    }

    public void addMeltable(ResourceLocation entry, int amount, ResourceLocation fluid) {
        List<ResourceLocation> idList = TagUtils.getTagsOwnedBy(entry);

        for(ResourceLocation id : idList) {
            if(meltableMap.containsKey(id)) {
                LogUtil.info(String.format("ID: %s falls under Tag: %s. Removing %s ...", id.toString(), meltableMap.toString(), id.toString()));
                meltableMap.remove(id);
            }
        }

        // Does a tag who owns me already exist in the map?
        Collection<ResourceLocation> tags = TagUtils.getTags(entry);
        if(tags != null) {
            for(ResourceLocation id : tags) {
                if(meltableMap.containsKey(id)) {
                    LogUtil.info(String.format("Tag: %s already registered. Skipping item %s ...", id.toString(), entry));
                    return;
                }
            }
        }

        if(meltableMap.containsKey(entry)) {
            LogUtil.info(String.format("Tag: %s already registered. Skipping...", entry));
            return;
        }

        insertIntoMap(entry, new Meltable(amount, fluid));
    }

    private void insertIntoMap(ResourceLocation name, Meltable meltable) {
        meltableMap.put(name, meltable);
    }

    public boolean isMeltable(ForgeRegistryEntry<? extends IItemProvider> entry) {
        Collection<ResourceLocation> tags = TagUtils.getTags(entry.getRegistryName());
        for(ResourceLocation tag : tags) {
            if(meltableMap.containsKey(tag)) {
                return true;
            }
        }
        return meltableMap.containsKey(entry.getRegistryName());
    }

    public Meltable getMeltable(ForgeRegistryEntry<? extends IItemProvider> entry) {
        Collection<ResourceLocation> tags = TagUtils.getTags(entry.getRegistryName());
        for(ResourceLocation tag : tags) {
            if(meltableMap.containsKey(tag)) {
                return meltableMap.get(tag);
            }
        }
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

    public List<CrucibleRecipe> getRecipeList() {
        List<CrucibleRecipe> recipes = new ArrayList<>();

        for(ResourceLocation entry : meltableMap.keySet()) {
            Meltable meltable = getMeltable(entry);
            List<ItemStack> blocks;
            Tag<Item> itemTag = ItemTags.getCollection().get(entry);
            if(itemTag != null) {
                blocks = itemTag.getAllElements().stream().map(ItemStack::new).collect(Collectors.toList());
            } else {
                blocks = new ArrayList<>();
                blocks.add(new ItemStack(ForgeRegistries.ITEMS.getValue(entry)));
            }
            recipes.add(new CrucibleRecipe(blocks, new FluidStack(meltable.getFluid(), FluidAttributes.BUCKET_VOLUME)));
        }

        return recipes;
    }

    private Meltable getMeltable(ResourceLocation entry) {
        return meltableMap.get(entry);
    }
}
