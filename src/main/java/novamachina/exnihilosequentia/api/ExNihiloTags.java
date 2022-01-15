package novamachina.exnihilosequentia.api;

import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import novamachina.exnihilosequentia.common.item.ore.EnumOre;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

import javax.annotation.Nonnull;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class ExNihiloTags {
    @Nonnull public static final ITag.INamedTag<Item> CLAY = createItemWrapper(forgeLoc("clay"));
    @Nonnull public static final ITag.INamedTag<Item> HAMMER = createItemWrapper(modLoc("hammer"));
    @Nonnull public static final ITag.INamedTag<Item> CROOK = createItemWrapper(modLoc("crook"));
    @Nonnull public static final ITag.INamedTag<Item> MEAT_COOKED = createItemWrapper(forgeLoc("meat_cooked"));
    @Nonnull public static final ITag.INamedTag<Item> MEAT_UNCOOKED = createItemWrapper(forgeLoc("meat_uncooked"));
    @Nonnull public static final ITag.INamedTag<Item> CRUCIBLE = createItemWrapper(modLoc("crucibles"));
    @Nonnull public static final ITag.INamedTag<Item> BARREL = createItemWrapper(modLoc("barrels"));
    @Nonnull public static final ITag.INamedTag<Item> SIEVE = createItemWrapper(modLoc("sieves"));
    @Nonnull private static final Map<EnumOre, OreTag> ores = new EnumMap<>(EnumOre.class);

    static {
        for (@Nonnull final EnumOre ore : EnumOre.values()) {
            ores.put(ore, new OreTag(ore));
        }
    }

    private ExNihiloTags() {
    }

    public static ITag.INamedTag<Item> createItemWrapper(@Nonnull final ResourceLocation name) {
        return createWrapperTag(ItemTags.getWrappers(), name, ItemTags::bind);
    }

    @Nonnull
    public static ResourceLocation getIngot(@Nonnull final String ingot) {
        return forgeLoc("ingots/" + ingot);
    }

    @Nonnull
    public static ResourceLocation getOre(@Nonnull final String ore) {
        return forgeLoc("ores/" + ore);
    }

    @Nonnull
    public static OreTag getOreTags(@Nonnull final EnumOre ore) {
        return ores.get(ore);
    }

    @Nonnull
    private static <T> ITag.INamedTag<T> createWrapperTag(@Nonnull final List<? extends ITag.INamedTag<T>> allExisting,
                                                          @Nonnull final ResourceLocation name,
                                                          @Nonnull final Function<String, ITag.INamedTag<T>> createNew) {
        @Nonnull final Optional<? extends ITag.INamedTag<T>> existing = allExisting
                .stream()
                .filter(tag -> tag.getName().equals(name))
                .findAny();
        if (existing.isPresent())
            return existing.get();
        else
            return createNew.apply(name.toString());
    }

    @Nonnull
    private static ResourceLocation forgeLoc(@Nonnull final String path) {
        return new ResourceLocation("forge", path);
    }

    @Nonnull
    private static ResourceLocation modLoc(@Nonnull String path) {
        return new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, path);
    }

    public static class OreTag {
        @Nonnull private final ITag.INamedTag<Item> ingot;
        @Nonnull private final ITag.INamedTag<Item> ore;

        public OreTag(@Nonnull EnumOre ore) {
            this.ingot = createItemWrapper(getIngot(ore.getOreName()));
            this.ore = createItemWrapper(getOre(ore.getOreName()));
        }

        @Nonnull
        public ITag.INamedTag<Item> getIngotTag() {
            return ingot;
        }

        @Nonnull
        public ITag.INamedTag<Item> getOreTag() {
            return ore;
        }
    }

}
