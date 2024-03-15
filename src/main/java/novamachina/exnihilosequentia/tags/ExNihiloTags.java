package novamachina.exnihilosequentia.tags;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.world.item.Ore;

public class ExNihiloTags {

  public static final TagKey<Item> CLAY = ItemTags.create(forgeLoc("clay"));
  public static final TagKey<Item> HAMMER = ItemTags.create(modLoc("hammer"));
  public static final TagKey<Item> CROOK = ItemTags.create(modLoc("crook"));
  public static final TagKey<Item> MEAT_COOKED = ItemTags.create(forgeLoc("meat_cooked"));

  public static final TagKey<Item> MEAT_UNCOOKED = ItemTags.create(forgeLoc("meat_uncooked"));

  public static final TagKey<Item> CRUCIBLE = ItemTags.create(modLoc("crucibles"));
  public static final TagKey<Item> BARREL = ItemTags.create(modLoc("barrels"));
  public static final TagKey<Item> SIEVE = ItemTags.create(modLoc("sieves"));
  public static final TagKey<Item> PIECE = ItemTags.create(modLoc("pieces"));
  public static final TagKey<Item> PEBBLES = ItemTags.create(modLoc("pebbles"));

  public static final TagKey<Block> MINEABLE_WITH_CROOK =
      BlockTags.create(modLoc("mineable/crook"));

  public static final TagKey<Block> MINEABLE_WITH_HAMMER =
      BlockTags.create(modLoc("mineable/hammer"));
  public static final TagKey<Block> INFESTABLE = BlockTags.create(modLoc("infestable"));

  public static final TagKey<Item> NUGGET_COPPER = ItemTags.create(forgeLoc("nuggets/copper"));

  private ExNihiloTags() {}

  public static ResourceLocation getIngot(final String ingot) {
    return forgeLoc("ingots/" + ingot);
  }

  public static ResourceLocation getRawMaterial(final String ore) {
    return forgeLoc("raw_materials/" + ore);
  }

  public static ResourceLocation getNugget(final String ore) {
    return forgeLoc("nuggets/" + ore);
  }

  private static ResourceLocation forgeLoc(final String path) {
    return new ResourceLocation("forge", path);
  }

  private static ResourceLocation modLoc(String path) {
    return new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, path);
  }

  public static class OreTag {

    private final TagKey<Item> ingot;
    private final TagKey<Item> rawMaterial;
    private final TagKey<Item> nugget;

    public OreTag(Ore ore) {
      this.ingot = ItemTags.create(getIngot(ore.getOreName()));
      this.rawMaterial = ItemTags.create(getRawMaterial(ore.getOreName()));
      this.nugget = ItemTags.create(getNugget(ore.getOreName()));
    }

    public TagKey<Item> getIngotTag() {
      return ingot;
    }

    public TagKey<Item> getRawMaterialTag() {
      return rawMaterial;
    }

    public TagKey<Item> getNuggetTag() {
      return nugget;
    }
  }
}
