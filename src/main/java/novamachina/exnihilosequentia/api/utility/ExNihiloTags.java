package novamachina.exnihilosequentia.api.utility;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import novamachina.exnihilosequentia.common.item.ore.EnumOre;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class ExNihiloTags {
    public static final Tag.Named<Item> CLAY = ItemTags.createOptional(forgeLoc("clay"));
    public static final Tag.Named<Item> HAMMER = ItemTags.createOptional(modLoc("hammer"));
    public static final Tag.Named<Item> CROOK = ItemTags.createOptional(modLoc("crook"));
    public static final Tag.Named<Item> MEAT_COOKED = ItemTags.createOptional(forgeLoc("meat_cooked"));
    public static final Tag.Named<Item> MEAT_UNCOOKED = ItemTags.createOptional(forgeLoc("meat_uncooked"));
    public static final Tag.Named<Item> CRUCIBLE = ItemTags.createOptional(modLoc("crucibles"));
    public static final Tag.Named<Item> BARREL = ItemTags.createOptional(modLoc("barrels"));
    public static final Tag.Named<Item> SIEVE = ItemTags.createOptional(modLoc("sieves"));
    private static final Map<EnumOre, OreTag> ores = new EnumMap<>(EnumOre.class);

    static {
        for (EnumOre ore : EnumOre.values()) {
            ores.put(ore, new OreTag(ore));
        }
    }

    private ExNihiloTags() {
    }

    public static Tag.Named<Item> createItemWrapper(ResourceLocation name) {
        //TODO
        return null; //createWrapperTag(ItemTags.getAllTags().getAvailableTags(), name, ItemTags::bind);
    }

    public static ResourceLocation getIngot(String ingot) {
        return forgeLoc("ingots/" + ingot);
    }

    public static ResourceLocation getOre(String ore) {
        return forgeLoc("ores/" + ore);
    }

    public static OreTag getOreTags(EnumOre ore) {
        return ores.get(ore);
    }

    private static <T> Tag.Named<T> createWrapperTag(List<? extends Tag.Named<T>> allExisting, ResourceLocation name,
                                                          Function<String, Tag.Named<T>> createNew) {
        Optional<? extends Tag.Named<T>> existing = allExisting
                .stream()
                .filter(tag -> tag.getName().equals(name))
                .findAny();
        if (existing.isPresent())
            return existing.get();
        else
            return createNew.apply(name.toString());
    }

    private static ResourceLocation forgeLoc(String path) {
        return new ResourceLocation("forge", path);
    }

    private static ResourceLocation modLoc(String path) { return new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, path);}

    public static class OreTag {
        private final Tag.Named<Item> ingot;
        private final Tag.Named<Item> ore;

        public OreTag(EnumOre ore) {
            this.ingot = ItemTags.createOptional(getIngot(ore.getOreName()));
            this.ore = ItemTags.createOptional(getOre(ore.getOreName()));
        }

        public Tag.Named<Item> getIngotTag() {
            return ingot;
        }

        public Tag.Named<Item> getOreTag() {
            return ore;
        }
    }

}
