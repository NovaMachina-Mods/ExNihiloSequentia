package novamachina.exnihilosequentia.api;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import novamachina.exnihilosequentia.common.item.ore.EnumOre;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

public class ExNihiloTags {
    public static final ITag.INamedTag<Item> CLAY = createItemWrapper(forgeLoc("clay"));
    public static final ITag.INamedTag<Item> MEAT_COOKED = createItemWrapper(forgeLoc("meat_cooked"));
    public static final ITag.INamedTag<Item> MEAT_UNCOOKED = createItemWrapper(forgeLoc("meat_uncooked"));
    public static final ITag.INamedTag<Item> HAMMER = createItemWrapper(exNihiloLoc("hammer"));
    public static final ITag.INamedTag<Item> CROOK = createItemWrapper(exNihiloLoc("crook"));
    public static final ITag.INamedTag<Item> WOODEN_CRUCIBLE = createItemWrapper(exNihiloLoc("wooden_crucible"));
    public static final ITag.INamedTag<Item> FIRED_CRUCIBLE = createItemWrapper(exNihiloLoc("fired_crucible"));
    public static final ITag.INamedTag<Item> WOODEN_BARREL = createItemWrapper(exNihiloLoc("wooden_barrel"));
    public static final ITag.INamedTag<Item> FIRED_BARREL = createItemWrapper(exNihiloLoc("fired_barrel"));
    public static final ITag.INamedTag<Item> SIEVE = createItemWrapper(exNihiloLoc("sieve"));
    public static final ITag.INamedTag<Block> WOODEN_CRUCIBLE_BLOCK = createBlockWrapper(exNihiloLoc("wooden_crucible"));
    public static final ITag.INamedTag<Block> FIRED_CRUCIBLE_BLOCK = createBlockWrapper(exNihiloLoc("fired_crucible"));
    public static final ITag.INamedTag<Block> WOODEN_BARREL_BLOCK = createBlockWrapper(exNihiloLoc("wooden_barrel"));
    public static final ITag.INamedTag<Block> FIRED_BARREL_BLOCK = createBlockWrapper(exNihiloLoc("fired_barrel"));
    public static final ITag.INamedTag<Block> SIEVE_BLOCK = createBlockWrapper(exNihiloLoc("sieve"));
    private static final Map<EnumOre, OreTag> ores = new EnumMap<>(EnumOre.class);

    static {
        for (EnumOre ore : EnumOre.values()) {
            ores.put(ore, new OreTag(ore));
        }
    }

    private ExNihiloTags() {
    }

    public static ITag.INamedTag<Block> createBlockWrapper(ResourceLocation name) {
        return createWrapperTag(BlockTags.getAllTags(), name, BlockTags::makeWrapperTag);
    }

    public static ITag.INamedTag<Item> createItemWrapper(ResourceLocation name) {
        return createWrapperTag(ItemTags.getAllTags(), name, ItemTags::makeWrapperTag);
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

    private static <T> ITag.INamedTag<T> createWrapperTag(List<? extends ITag.INamedTag<T>> allExisting, ResourceLocation name,
                                                          Function<String, ITag.INamedTag<T>> createNew) {
        Optional<? extends ITag.INamedTag<T>> existing = allExisting
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

    private static ResourceLocation exNihiloLoc(String path) { return new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, path); }

    public static class OreTag {
        private final ITag.INamedTag<Item> ingot;
        private final ITag.INamedTag<Item> ore;

        public OreTag(EnumOre ore) {
            this.ingot = createItemWrapper(getIngot(ore.getOreName()));
            this.ore = createItemWrapper(getOre(ore.getOreName()));
        }

        public ITag.INamedTag<Item> getIngotTag() {
            return ingot;
        }

        public ITag.INamedTag<Item> getOreTag() {
            return ore;
        }
    }

}
