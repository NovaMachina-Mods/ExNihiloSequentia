package com.novamachina.exnihilosequentia.common.registries.crucible;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.novamachina.exnihilosequentia.common.api.ExNihiloRegistries;
import com.novamachina.exnihilosequentia.common.compat.jei.crucible.CrucibleRecipe;
import com.novamachina.exnihilosequentia.common.json.AnnotatedDeserializer;
import com.novamachina.exnihilosequentia.common.json.CrucibleJson;
import com.novamachina.exnihilosequentia.common.registries.AbstractModRegistry;
import com.novamachina.exnihilosequentia.common.tileentity.crucible.CrucilbeTypeEnum;
import com.novamachina.exnihilosequentia.common.utility.Constants;
import com.novamachina.exnihilosequentia.common.utility.LogUtil;
import com.novamachina.exnihilosequentia.common.utility.TagUtils;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.ResourceLocationException;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CrucibleRegistry extends AbstractModRegistry {
    private final Map<ResourceLocation, Meltable> meltableMap = new HashMap<>();

    public CrucibleRegistry(ExNihiloRegistries.ModBus bus) {
        bus.register(this);
    }

    public void addMeltable(ForgeRegistryEntry<? extends IItemProvider> entry, int amount, Fluid fluid, CrucilbeTypeEnum crucibleType) {
        addMeltable(entry.getRegistryName(), amount, fluid.getRegistryName(), crucibleType);
    }

    public void addMeltable(ResourceLocation entry, int amount, ResourceLocation fluid, CrucilbeTypeEnum crucibleType) {
        List<ResourceLocation> idList = TagUtils.getTagsOwnedBy(entry);

        for (ResourceLocation id : idList) {
            if (meltableMap.containsKey(id)) {
                LogUtil.info(String
                    .format("ID: %s falls under Tag: %s. Removing %s ...", id.toString(), meltableMap.toString(), id
                        .toString()));
                meltableMap.remove(id);
            }
        }

        // Does a tag who owns me already exist in the map?
        Collection<ResourceLocation> tags = TagUtils.getTags(entry);
        if (tags != null) {
            for (ResourceLocation id : tags) {
                if (meltableMap.containsKey(id)) {
                    LogUtil
                        .info(String.format("Tag: %s already registered. Skipping item %s ...", id.toString(), entry));
                    return;
                }
            }
        }

        if (meltableMap.containsKey(entry)) {
            LogUtil.info(String.format("Tag: %s already registered. Skipping...", entry));
            return;
        }

        insertIntoMap(entry, new Meltable(amount, fluid, crucibleType));
    }

    private void insertIntoMap(ResourceLocation name, Meltable meltable) {
        meltableMap.put(name, meltable);
    }

    public boolean isMeltable(ForgeRegistryEntry<? extends IItemProvider> entry, int level) {
        Collection<ResourceLocation> tags = TagUtils.getTags(entry.getRegistryName());
        for (ResourceLocation tag : tags) {
            if (meltableMap.containsKey(tag)) {
                if (meltableMap.get(tag).getCrucibleType().getLevel() <= level) {
                    return true;
                }
            }
        }
        return meltableMap.containsKey(entry.getRegistryName()) && meltableMap.get(entry.getRegistryName())
            .getCrucibleType().getLevel() <= level;
    }

    public Meltable getMeltable(ForgeRegistryEntry<? extends IItemProvider> entry) {
        Collection<ResourceLocation> tags = TagUtils.getTags(entry.getRegistryName());
        for (ResourceLocation tag : tags) {
            if (meltableMap.containsKey(tag)) {
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
        for (Map.Entry<ResourceLocation, Meltable> entry : meltableMap.entrySet()) {
            jsonList.add(new CrucibleJson(entry.getKey().toString(), entry.getValue()));
        }
        return jsonList;
    }

    protected boolean itemExists(String entry) throws ResourceLocationException {
        ResourceLocation itemID = new ResourceLocation(entry);
        return TagUtils.isTag(itemID) || ForgeRegistries.BLOCKS.containsKey(itemID) || ForgeRegistries.ITEMS
            .containsKey(itemID) || ForgeRegistries.FLUIDS.containsKey(itemID);
    }

    public List<CrucibleRecipe> getRecipeList() {
        List<CrucibleRecipe> recipes = new ArrayList<>();

        for (ResourceLocation entry : meltableMap.keySet()) {
            Meltable meltable = getMeltable(entry);
            List<ItemStack> blocks;
            ITag<Item> itemTag = ItemTags.getCollection().get(entry);
            if (itemTag != null) {
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

    @Override
    public void useJson() {
        if (generateJson(Constants.Json.CRUCIBLE_FILE, this)) {
            return;
        }

        try {
            List<CrucibleJson> registriesJson = readJson();
            for (CrucibleJson entry : registriesJson) {
                try {
                    if (itemExists(entry.getEntry())) {
                        ResourceLocation entryID = new ResourceLocation(entry.getEntry());
                        if (itemExists(entry.getFluid())) {
                            ResourceLocation fluidID = new ResourceLocation(entry.getFluid());
                            addMeltable(entryID, entry.getAmount(), fluidID, entry.getCrucibleType());
                        } else {
                            LogUtil.warn(String
                                .format("%s: Entry \"%s\" does not exist...Skipping...", Constants.Json.CRUCIBLE_FILE, entry
                                    .getFluid()));
                        }
                    } else {
                        LogUtil.warn(String
                            .format("%s: Entry \"%s\" does not exist...Skipping...", Constants.Json.CRUCIBLE_FILE, entry
                                .getEntry()));
                    }
                } catch (ResourceLocationException e) {
                    LogUtil.warn(String.format("%s: %s. Skipping...", Constants.Json.CRUCIBLE_FILE, e.getMessage()));
                }
            }
        } catch (JsonParseException e) {
            LogUtil.error(String.format("Malformed %s", Constants.Json.CRUCIBLE_FILE));
            LogUtil.error(e.getMessage());
            if (e.getMessage().contains("IllegalStateException")) {
                LogUtil.error("Please consider deleting the file and regenerating it.");
            }
            LogUtil.error("Falling back to defaults");
            clear();
            ExNihiloRegistries.BUS.getDefaults().forEach(registry -> registry.registerFiredCrucible(this));
        }
    }

    private List<CrucibleJson> readJson() throws JsonParseException {
        Type listType = new TypeToken<ArrayList<CrucibleJson>>() {
        }.getType();
        Gson gson = new GsonBuilder()
            .registerTypeAdapter(listType, new AnnotatedDeserializer<ArrayList<CrucibleJson>>()).create();
        Path path = Constants.Json.baseJsonPath.resolve(Constants.Json.CRUCIBLE_FILE);
        List<CrucibleJson> registryJson = null;
        try {
            StringBuilder builder = new StringBuilder();
            Files.readAllLines(path).forEach(builder::append);
            registryJson = gson.fromJson(builder.toString(), listType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return registryJson;
    }

    public List<CrucibleRecipe> getFiredRecipeList() {
        List<CrucibleRecipe> recipes = new ArrayList<>();

        for (ResourceLocation entry : meltableMap.keySet()) {
            Meltable meltable = getMeltable(entry);
            List<ItemStack> blocks;
            ITag<Item> itemTag = ItemTags.getCollection().get(entry);
            if (itemTag != null) {
                blocks = itemTag.getAllElements().stream().map(ItemStack::new).collect(Collectors.toList());
            } else {
                blocks = new ArrayList<>();
                blocks.add(new ItemStack(ForgeRegistries.ITEMS.getValue(entry)));
            }
            recipes.add(new CrucibleRecipe(blocks, new FluidStack(meltable.getFluid(), FluidAttributes.BUCKET_VOLUME)));
        }

        return recipes;
    }

    public List<CrucibleRecipe> getWoodRecipeList() {
        List<CrucibleRecipe> recipes = new ArrayList<>();

        for (ResourceLocation entry : meltableMap.keySet()) {
            Meltable meltable = getMeltable(entry);
            if (meltable.getCrucibleType() == CrucilbeTypeEnum.WOOD) {
                List<ItemStack> blocks;
                ITag<Item> itemTag = ItemTags.getCollection().get(entry);
                if (itemTag != null) {
                    blocks = itemTag.getAllElements().stream().map(ItemStack::new).collect(Collectors.toList());
                } else {
                    blocks = new ArrayList<>();
                    blocks.add(new ItemStack(ForgeRegistries.ITEMS.getValue(entry)));
                }
                recipes.add(new CrucibleRecipe(blocks, new FluidStack(meltable
                    .getFluid(), FluidAttributes.BUCKET_VOLUME)));
            }
        }

        return recipes;
    }
}
