package com.novamachina.exnihilosequentia.common.api;

import com.novamachina.exnihilosequentia.common.item.ore.EnumOre;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class ExNihiloTags {
    private static Map<EnumOre, OreTag> ores = new HashMap<>();

    public static final ITag.INamedTag<Item> MEAT_UNCOOKED = createItemWrapper(forgeLoc("meat_uncooked"));
    public static final ITag.INamedTag<Item> MEAT_COOKED = createItemWrapper(forgeLoc("meat_cooked"));

    static {
        for(EnumOre ore : EnumOre.values()) {
            ores.put(ore, new OreTag(ore));
        }
    }

    public static OreTag getOreTags(EnumOre ore) {
        return ores.get(ore);
    }

    public static class OreTag {
        private final ITag.INamedTag<Item> ingot;
        private final ITag.INamedTag<Item> ore;

        public OreTag(EnumOre ore) {
            this.ingot = createItemWrapper(getIngot(ore.getName()));
            this.ore = createItemWrapper(getOre(ore.getName()));
        }

        public ITag.INamedTag<Item> getIngotTag() {
            return ingot;
        }

        public ITag.INamedTag<Item> getOreTag() {
            return ore;
        }
    }

    public static ResourceLocation getIngot(String ingot) {
        return forgeLoc("ingots/" + ingot);
    }

    public static ResourceLocation getOre(String ore) {
        return forgeLoc("ores/" + ore);
    }

    private static ResourceLocation forgeLoc(String path) {
        return new ResourceLocation("forge", path);
    }

    public static ITag.INamedTag<Item> createItemWrapper(ResourceLocation name)
    {
        return createWrapperTag(ItemTags.func_242177_b(), name, ItemTags::makeWrapperTag);
    }

    private static <T> ITag.INamedTag<T> createWrapperTag(List<? extends ITag.INamedTag<T>> allExisting, ResourceLocation name,
                                                          Function<String, ITag.INamedTag<T>> createNew)
    {
        Optional<? extends ITag.INamedTag<T>> existing = allExisting
            .stream()
            .filter(tag -> tag.func_230234_a_().equals(name))
            .findAny();
        if(existing.isPresent())
            return existing.get();
        else
            return createNew.apply(name.toString());
    }

}
