package com.novamachina.exnihilosequentia.common.tileentity.barrel.compost;

import com.novamachina.exnihilosequentia.common.item.resources.EnumResource;
import com.novamachina.exnihilosequentia.common.setup.ModItems;
import com.novamachina.exnihilosequentia.common.utility.LogUtil;
import com.novamachina.exnihilosequentia.common.utility.TagUtils;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompostRegistry {
    public static Map<ResourceLocation, Integer> solidsMap = new HashMap<>();

    public static boolean containsSolid(IItemProvider item) {
        Collection<ResourceLocation> tags = TagUtils.getTags(item);
        for(ResourceLocation tag : tags) {
            if(solidsMap.containsKey(tag)) {
                return true;
            }
        }
        return solidsMap.containsKey(item.asItem().getRegistryName());
    }

    public static int getSolidAmount(IItemProvider item) {
        Collection<ResourceLocation> tags = TagUtils.getTags(item);
        for(ResourceLocation tag : tags) {
            if(solidsMap.containsKey(tag)) {
                return solidsMap.get(tag);
            }
        }

        return solidsMap.getOrDefault(item.asItem().getRegistryName(), 0);
    }

    // TODO: add remaining compost values
    public static void initialize() {
        addSolid(new ResourceLocation("minecraft:saplings"), 125);
        addSolid(new ResourceLocation("minecraft:leaves"), 125);
        addSolid(new ResourceLocation("minecraft:flowers"), 100);
        addSolid(new ResourceLocation("minecraft:fishes"), 150);
        addSolid(new ResourceLocation("forge:meat_cooked"), 200);
        addSolid(new ResourceLocation("forge:meat_uncooked"), 200);
        addSolid(new ResourceLocation("forge:seeds"), 80);
        addSolid(new ResourceLocation("forge:crops/wheat"), 80);
        addSolid(new ResourceLocation("forge:crops/carrot"), 100);
        addSolid(new ResourceLocation("forge:crops/beetroot"), 100);
        addSolid(new ResourceLocation("forge:crops/potato"), 100);
        addSolid(new ResourceLocation("forge:crops/nether_wart"), 100);
        addSolid(new ResourceLocation("forge:eggs"), 80);
        addSolid(new ResourceLocation("forge:string"), 40);
        addSolid(Items.ROTTEN_FLESH, 100);
        addSolid(Items.SPIDER_EYE, 80);
        addSolid(Items.BREAD, 160);
        addSolid(Blocks.BROWN_MUSHROOM, 100);
        addSolid(Blocks.RED_MUSHROOM, 100);
        addSolid(Items.PUMPKIN_PIE, 160);
        addSolid(ModItems.resourceMap.get(EnumResource.SILKWORM.getResourceName()).get(), 40);
        addSolid(ModItems.COOKED_SILKWORM.get(), 40);
        addSolid(Items.APPLE, 100);
        addSolid(Items.MELON_SLICE, 40);
        addSolid(Blocks.MELON, 1000/6);
        addSolid(Blocks.PUMPKIN, 1000/6);
        addSolid(Blocks.CARVED_PUMPKIN, 1000/6);
        addSolid(Blocks.JACK_O_LANTERN, 1000/6);
        addSolid(Blocks.CACTUS, 100);
        addSolid(Items.BAKED_POTATO, 150);
        addSolid(Items.POISONOUS_POTATO, 200);
        addSolid(Blocks.LILY_PAD, 100);
        addSolid(Blocks.VINE, 100);
        addSolid(Blocks.TALL_GRASS, 100);
        addSolid(Blocks.SUGAR_CANE, 80);
    }

    public static void addSolid(IItemProvider item, int solidAmount) {
        addSolid(item.asItem().getRegistryName(), solidAmount);
    }

    public static void addSolid(ResourceLocation tag, int solidAmount) {
        List<ResourceLocation> idList = TagUtils.getTagsOwnedBy(tag);

        for(ResourceLocation id : idList) {
            if(solidsMap.containsKey(id)) {
                LogUtil.info(String.format("ID: %s falls under Tag: %s. Removing %s ...", id.toString(), tag.toString(), id.toString()));
                solidsMap.remove(id);
            }
        }

        // Does a tag who owns me already exist in the map?
        Collection<ResourceLocation> tags = TagUtils.getTags(tag);
        if(tags != null) {
            for(ResourceLocation id : tags) {
                if(solidsMap.containsKey(id)) {
                    LogUtil.info(String.format("Tag: %s already registered. Skipping item %s ...", id.toString(), tag));
                    return;
                }
            }
        }

        if(solidsMap.containsKey(tag)) {
            LogUtil.info(String.format("Tag: %s already registered. Skipping...", tag));
            return;
        }
        insertIntoMap(tag, solidAmount);
    }

    private static void insertIntoMap(ResourceLocation id, int amount) {
        solidsMap.put(id, amount);
    }

    public static List<CompostJSON> toJSONReady() {
        List<CompostJSON> gsonList = new ArrayList<>();

        for(Map.Entry<ResourceLocation, Integer> entry : solidsMap.entrySet()) {
            gsonList.add(new CompostJSON(entry.getKey().toString(), entry.getValue()));
        }

        return gsonList;
    }
}
