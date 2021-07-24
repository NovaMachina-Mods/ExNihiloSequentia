package novamachina.exnihilosequentia.api;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.StaticTagHelper;
import net.minecraft.tags.StaticTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.util.ReverseTagWrapper;
import novamachina.exnihilosequentia.api.datagen.AbstractBlockStateGenerator;
import novamachina.exnihilosequentia.common.item.ore.EnumOre;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

public class ExNihiloTags {
    public static final Tag.Named<Item> CLAY = createItemWrapper(forgeLoc("clay"));
    public static final Tag.Named<Item> HAMMER = createItemWrapper(new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "hammer"));
    public static final Tag.Named<Item> CROOK = createItemWrapper(new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "crook"));
	public static final Tag.Named<Item> STONE_STICK = createItemWrapper(forgeLoc("rods/stone"));
    public static final Tag.Named<Item> MEAT_COOKED = createItemWrapper(forgeLoc("meat_cooked"));
    public static final Tag.Named<Item> MEAT_UNCOOKED = createItemWrapper(forgeLoc("meat_uncooked"));
    public static final Tag.Named<Item> CRUCIBLE = createItemWrapper(new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "crucibles"));
    public static final Tag.Named<Item> BARREL = createItemWrapper(new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "barrels"));
    public static final Tag.Named<Item> SIEVE = createItemWrapper(new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "sieves"));
    private static final Map<EnumOre, OreTag> ores = new EnumMap<>(EnumOre.class);

    static {
        for (EnumOre ore : EnumOre.values()) {
            ores.put(ore, new OreTag(ore));
        }
    }

    private ExNihiloTags() {
    }

    public static Tag.Named<Item> createItemWrapper(ResourceLocation name) {
        return null;
        //return createWrapperTag(ItemTags.getAllTags(), name, ItemTags::bind);
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

    //TODO
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

    public static ResourceLocation forgeLoc(String path) {
        return new ResourceLocation("forge", path);
    }

    public static class OreTag {
        private final Tag.Named<Item> ingot;
        private final Tag.Named<Item> ore;

        public OreTag(EnumOre ore) {
            this.ingot = createItemWrapper(getIngot(ore.getOreName()));
            this.ore = createItemWrapper(getOre(ore.getOreName()));
        }

        public Tag.Named<Item> getIngotTag() {
            return ingot;
        }

        public Tag.Named<Item> getOreTag() {
            return ore;
        }
    }

}
