package novamachina.exnihilosequentia.api;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.tags.Tag;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import novamachina.exnihilosequentia.common.item.ore.EnumOre;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

import javax.annotation.Nonnull;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class ExNihiloTags {
    @Nonnull public static final Tag.Named<Item> CLAY = ItemTags.createOptional(forgeLoc("clay"));
    @Nonnull public static final Tag.Named<Item> HAMMER = ItemTags.createOptional(modLoc("hammer"));
    @Nonnull public static final Tag.Named<Item> CROOK = ItemTags.createOptional(modLoc("crook"));
    @Nonnull public static final Tag.Named<Item> MEAT_COOKED = ItemTags.createOptional(forgeLoc("meat_cooked"));
    @Nonnull public static final Tag.Named<Item> MEAT_UNCOOKED = ItemTags.createOptional(forgeLoc("meat_uncooked"));
    @Nonnull public static final Tag.Named<Item> CRUCIBLE = ItemTags.createOptional(modLoc("crucibles"));
    @Nonnull public static final Tag.Named<Item> BARREL = ItemTags.createOptional(modLoc("barrels"));
    @Nonnull public static final Tag.Named<Item> SIEVE = ItemTags.createOptional(modLoc("sieves"));
    @Nonnull public static final Tag.Named<Block> MINEABLE_WITH_CROOK = BlockTags.createOptional(modLoc("mineable/crook"));
    @Nonnull public static final Tag.Named<Block> MINEABLE_WITH_HAMMER = BlockTags.createOptional(modLoc("mineable/hammer"));
    @Nonnull private static final Map<EnumOre, OreTag> ores = new EnumMap<>(EnumOre.class);

    static {
        for (@Nonnull final EnumOre ore : EnumOre.values()) {
            ores.put(ore, new OreTag(ore));
        }
    }

    private ExNihiloTags() {
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
    private static ResourceLocation forgeLoc(@Nonnull final String path) {
        return new ResourceLocation("forge", path);
    }

    @Nonnull
    private static ResourceLocation modLoc(@Nonnull String path) {
        return new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, path);
    }

    public static class OreTag {
        @Nonnull private final Tag.Named<Item> ingot;
        @Nonnull private final Tag.Named<Item> ore;

        public OreTag(@Nonnull EnumOre ore) {
            this.ingot = ItemTags.createOptional(getIngot(ore.getOreName()));
            this.ore = ItemTags.createOptional(getOre(ore.getOreName()));
        }

        @Nonnull
        public Tag.Named<Item> getIngotTag() {
            return ingot;
        }

        @Nonnull
        public Tag.Named<Item> getOreTag() {
            return ore;
        }
    }

}
