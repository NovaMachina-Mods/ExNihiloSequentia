package novamachina.exnihilosequentia.api.tag;

import javax.annotation.Nonnull;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import novamachina.exnihilosequentia.common.item.ore.Ore;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.world.item.EXNItems;

public class ExNihiloTags {

  @Nonnull public static final TagKey<Item> CLAY = ItemTags.create(forgeLoc("clay"));
  @Nonnull public static final TagKey<Item> HAMMER = ItemTags.create(modLoc("hammer"));
  @Nonnull public static final TagKey<Item> CROOK = ItemTags.create(modLoc("crook"));
  @Nonnull public static final TagKey<Item> MEAT_COOKED = ItemTags.create(forgeLoc("meat_cooked"));

  @Nonnull
  public static final TagKey<Item> MEAT_UNCOOKED = ItemTags.create(forgeLoc("meat_uncooked"));

  @Nonnull public static final TagKey<Item> CRUCIBLE = ItemTags.create(modLoc("crucibles"));
  @Nonnull public static final TagKey<Item> BARREL = ItemTags.create(modLoc("barrels"));
  @Nonnull public static final TagKey<Item> SIEVE = ItemTags.create(modLoc("sieves"));
  @Nonnull public static final TagKey<Item> PIECE = ItemTags.create(modLoc("pieces"));

  @Nonnull
  public static final TagKey<Block> MINEABLE_WITH_CROOK =
      BlockTags.create(modLoc("mineable/crook"));

  @Nonnull
  public static final TagKey<Block> MINEABLE_WITH_HAMMER =
      BlockTags.create(modLoc("mineable/hammer"));

  @Nonnull public static final OreTag IRON = new OreTag(EXNItems.IRON);
  @Nonnull public static final OreTag GOLD = new OreTag(EXNItems.GOLD);
  @Nonnull public static final OreTag COPPER = new OreTag(EXNItems.COPPER);
  @Nonnull public static final OreTag LEAD = new OreTag(EXNItems.LEAD);
  @Nonnull public static final OreTag NICKEL = new OreTag(EXNItems.NICKEL);
  @Nonnull public static final OreTag SILVER = new OreTag(EXNItems.SILVER);
  @Nonnull public static final OreTag TIN = new OreTag(EXNItems.TIN);
  @Nonnull public static final OreTag ALUMINUM = new OreTag(EXNItems.ALUMINUM);
  @Nonnull public static final OreTag PLATINUM = new OreTag(EXNItems.PLATINUM);
  @Nonnull public static final OreTag URANIUM = new OreTag(EXNItems.URANIUM);
  @Nonnull public static final OreTag ZINC = new OreTag(EXNItems.ZINC);
  public static final TagKey<Item> NUGGET_COPPER = ItemTags.create(forgeLoc("nuggets/copper"));

  private ExNihiloTags() {}

  @Nonnull
  public static ResourceLocation getIngot(@Nonnull final String ingot) {
    return forgeLoc("ingots/" + ingot);
  }

  @Nonnull
  public static ResourceLocation getRawMaterial(@Nonnull final String ore) {
    return forgeLoc("raw_materials/" + ore);
  }

  @Nonnull
  public static ResourceLocation getNugget(@Nonnull final String ore) {
    return forgeLoc("nuggets/" + ore);
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
    @Nonnull private final TagKey<Item> rawMaterial;
    @Nonnull private final TagKey<Item> nugget;

    public OreTag(@Nonnull Ore ore) {
      this.ingot = ItemTags.create(getIngot(ore.getOreName()));
      this.rawMaterial = ItemTags.create(getRawMaterial(ore.getOreName()));
      this.nugget = ItemTags.create(getNugget(ore.getOreName()));
    }

    @Nonnull
    public TagKey<Item> getIngotTag() {
      return ingot;
    }

    @Nonnull
    public TagKey<Item> getRawMaterialTag() {
      return rawMaterial;
    }

    public TagKey<Item> getNuggetTag() {
      return nugget;
    }
  }
}
