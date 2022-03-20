package novamachina.exnihilosequentia.api;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import novamachina.exnihilosequentia.common.item.ore.EnumOre;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

import javax.annotation.Nonnull;
import java.util.EnumMap;
import java.util.Map;

public class ExNihiloTags {
    @Nonnull public static final TagKey<Item> CLAY = ItemTags.create(forgeLoc("clay"));
    @Nonnull public static final TagKey<Item> HAMMER = ItemTags.create(modLoc("hammer"));
    @Nonnull public static final TagKey<Item> CROOK = ItemTags.create(modLoc("crook"));
    @Nonnull public static final TagKey<Item> MEAT_COOKED = ItemTags.create(forgeLoc("meat_cooked"));
    @Nonnull public static final TagKey<Item> MEAT_UNCOOKED = ItemTags.create(forgeLoc("meat_uncooked"));
    @Nonnull public static final TagKey<Item> CRUCIBLE = ItemTags.create(modLoc("crucibles"));
    @Nonnull public static final TagKey<Item> BARREL = ItemTags.create(modLoc("barrels"));
    @Nonnull public static final TagKey<Item> SIEVE = ItemTags.create(modLoc("sieves"));
    @Nonnull public static final TagKey<Block> MINEABLE_WITH_CROOK = BlockTags.create(modLoc("mineable/crook"));
    @Nonnull public static final TagKey<Block> MINEABLE_WITH_HAMMER = BlockTags.create(modLoc("mineable/hammer"));
    @Nonnull private static final Map<EnumOre, OreTag> ores = new EnumMap<>(EnumOre.class);

    static {
        for (@Nonnull final EnumOre ore : EnumOre.values()) {
            ores.put(ore, new OreTag(ore));
        }
    }

    private ExNihiloTags() {
    }

    public static void init() {
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
        @Nonnull private final TagKey<Item> ingot;
        @Nonnull private final TagKey<Item> ore;

        public OreTag(@Nonnull EnumOre ore) {
            this.ingot = ItemTags.create(getIngot(ore.getOreName()));
            this.ore = ItemTags.create(getOre(ore.getOreName()));
        }

        @Nonnull
        public TagKey<Item> getIngotTag() {
            return ingot;
        }

        @Nonnull
        public TagKey<Item> getOreTag() {
            return ore;
        }
    }

}
