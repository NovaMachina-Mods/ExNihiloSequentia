package novamachina.exnihilosequentia.data.tags;

import com.mojang.datafixers.util.Either;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import novamachina.exnihilosequentia.ExNihiloSequentia;
import novamachina.exnihilosequentia.tags.ExNihiloTags;
import novamachina.exnihilosequentia.world.item.EXNItems;
import novamachina.exnihilosequentia.world.item.Ore;
import novamachina.exnihilosequentia.world.item.OreItem;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;
import novamachina.exnihilosequentia.world.level.material.EXNFluids;
import novamachina.novacore.data.tags.TagProvider;
import novamachina.novacore.world.item.ItemDefinition;

public class EXNTagProvider extends TagProvider {
  public EXNTagProvider(
      PackOutput output,
      CompletableFuture<HolderLookup.Provider> lookupProvider,
      ExistingFileHelper existingFileHelper) {
    super(output, lookupProvider, ExNihiloSequentia.MOD_ID, existingFileHelper);
  }

  @Override
  protected void registerTags() {
    addBlocks();
    addFluids();
    addItems();
  }

  private void addItems() {
    addOres();
    addMeats();
    addHammers();
    addCrooks();
    addMisc();
    addWoodenBarrel();
    addWoodenCrucible();
    addWoodenSieve();
    addPebbles();
  }

  private void addPebbles() {
    addToTag(
        ExNihiloTags.PEBBLES,
        EXNItems.PEBBLE_ANDESITE,
        EXNItems.PEBBLE_BASALT,
        EXNItems.PEBBLE_BLACKSTONE,
        EXNItems.PEBBLE_CALCITE,
        EXNItems.PEBBLE_DEEPSLATE,
        EXNItems.PEBBLE_DIORITE,
        EXNItems.PEBBLE_DRIPSTONE,
        EXNItems.PEBBLE_END_STONE,
        EXNItems.PEBBLE_GRANITE,
        EXNItems.PEBBLE_NETHERRACK,
        EXNItems.PEBBLE_STONE,
        EXNItems.PEBBLE_TUFF);
  }

  private void addWoodenSieve() {
    addToTag(
        ExNihiloTags.SIEVE,
        EXNBlocks.ACACIA_SIEVE,
        EXNBlocks.BAMBOO_SIEVE,
        EXNBlocks.BIRCH_SIEVE,
        EXNBlocks.CHERRY_SIEVE,
        EXNBlocks.DARK_OAK_SIEVE,
        EXNBlocks.JUNGLE_SIEVE,
        EXNBlocks.MANGROVE_SIEVE,
        EXNBlocks.OAK_SIEVE,
        EXNBlocks.SPRUCE_SIEVE,
        EXNBlocks.CRIMSON_SIEVE,
        EXNBlocks.WARPED_SIEVE);
  }

  private void addWoodenCrucible() {
    addToTag(
        ExNihiloTags.CRUCIBLE,
        EXNBlocks.ACACIA_CRUCIBLE,
        EXNBlocks.BAMBOO_CRUCIBLE,
        EXNBlocks.BIRCH_CRUCIBLE,
        EXNBlocks.CHERRY_CRUCIBLE,
        EXNBlocks.DARK_OAK_CRUCIBLE,
        EXNBlocks.JUNGLE_CRUCIBLE,
        EXNBlocks.MANGROVE_CRUCIBLE,
        EXNBlocks.OAK_CRUCIBLE,
        EXNBlocks.SPRUCE_CRUCIBLE,
        EXNBlocks.CRIMSON_CRUCIBLE,
        EXNBlocks.WARPED_CRUCIBLE);
  }

  private void addWoodenBarrel() {
    addToTag(
        ExNihiloTags.BARREL,
        EXNBlocks.ACACIA_BARREL,
        EXNBlocks.BAMBOO_BARREL,
        EXNBlocks.BIRCH_BARREL,
        EXNBlocks.CHERRY_BARREL,
        EXNBlocks.DARK_OAK_BARREL,
        EXNBlocks.JUNGLE_BARREL,
        EXNBlocks.MANGROVE_BARREL,
        EXNBlocks.OAK_BARREL,
        EXNBlocks.SPRUCE_BARREL,
        EXNBlocks.CRIMSON_BARREL,
        EXNBlocks.WARPED_BARREL);
  }

  private void addMisc() {
    addToTag(ExNihiloTags.CLAY, Items.CLAY_BALL);
  }

  private void addCrooks() {
    addToTag(
        ExNihiloTags.CROOK,
        EXNItems.CROOK_ANDESITE,
        EXNItems.CROOK_BAMBOO,
        EXNItems.CROOK_BASALT,
        EXNItems.CROOK_BLACKSTONE,
        EXNItems.CROOK_BONE,
        EXNItems.CROOK_CALCITE,
        EXNItems.CROOK_CHERRY,
        EXNItems.CROOK_COPPER,
        EXNItems.CROOK_DEEPSLATE,
        EXNItems.CROOK_DIAMOND,
        EXNItems.CROOK_DIORITE,
        EXNItems.CROOK_DRIPSTONE,
        EXNItems.CROOK_GOLD,
        EXNItems.CROOK_GRANITE,
        EXNItems.CROOK_IRON,
        EXNItems.CROOK_NETHER_BRICK,
        EXNItems.CROOK_NETHERITE,
        EXNItems.CROOK_RED_NETHER_BRICK,
        EXNItems.CROOK_STONE,
        EXNItems.CROOK_TERRACOTTA,
        EXNItems.CROOK_TUFF,
        EXNItems.CROOK_WOOD);
  }

  private void addHammers() {
    addToTag(
        ExNihiloTags.HAMMER,
        EXNItems.HAMMER_ANDESITE,
        EXNItems.HAMMER_BAMBOO,
        EXNItems.HAMMER_BASALT,
        EXNItems.HAMMER_BLACKSTONE,
        EXNItems.HAMMER_BONE,
        EXNItems.HAMMER_CALCITE,
        EXNItems.HAMMER_CHERRY,
        EXNItems.HAMMER_COPPER,
        EXNItems.HAMMER_DEEPSLATE,
        EXNItems.HAMMER_DIAMOND,
        EXNItems.HAMMER_DIORITE,
        EXNItems.HAMMER_DRIPSTONE,
        EXNItems.HAMMER_GOLD,
        EXNItems.HAMMER_GRANITE,
        EXNItems.HAMMER_IRON,
        EXNItems.HAMMER_NETHER_BRICK,
        EXNItems.HAMMER_NETHERITE,
        EXNItems.HAMMER_RED_NETHER_BRICK,
        EXNItems.HAMMER_STONE,
        EXNItems.HAMMER_TERRACOTTA,
        EXNItems.HAMMER_TUFF,
        EXNItems.HAMMER_WOOD);
  }

  private void addMeats() {
    addUncooked();
    addCooked();
  }

  private void addCooked() {
    addToTag(
        ExNihiloTags.MEAT_COOKED,
        Items.COOKED_CHICKEN,
        Items.COOKED_COD,
        Items.COOKED_MUTTON,
        Items.COOKED_PORKCHOP,
        Items.COOKED_RABBIT,
        Items.COOKED_SALMON,
        Items.COOKED_BEEF);
  }

  private void addUncooked() {
    addToTag(
        ExNihiloTags.MEAT_UNCOOKED,
        Items.CHICKEN,
        Items.COD,
        Items.MUTTON,
        Items.PORKCHOP,
        Items.RABBIT,
        Items.SALMON,
        Items.BEEF);
  }

  private void addOres() {
    addOre(EXNItems.IRON, new ExNihiloTags.OreTag(EXNItems.IRON));
    addOre(EXNItems.GOLD, new ExNihiloTags.OreTag(EXNItems.GOLD));
    addOre(EXNItems.COPPER, new ExNihiloTags.OreTag(EXNItems.COPPER));
    addOre(EXNItems.LEAD, new ExNihiloTags.OreTag(EXNItems.LEAD));
    addOre(EXNItems.NICKEL, new ExNihiloTags.OreTag(EXNItems.NICKEL));
    addOre(EXNItems.SILVER, new ExNihiloTags.OreTag(EXNItems.SILVER));
    addOre(EXNItems.TIN, new ExNihiloTags.OreTag(EXNItems.TIN));
    addOre(EXNItems.ALUMINUM, new ExNihiloTags.OreTag(EXNItems.ALUMINUM));
    addOre(EXNItems.PLATINUM, new ExNihiloTags.OreTag(EXNItems.PLATINUM));
    addOre(EXNItems.URANIUM, new ExNihiloTags.OreTag(EXNItems.URANIUM));
    addOre(EXNItems.ZINC, new ExNihiloTags.OreTag(EXNItems.ZINC));
  }

  private void addOre(Ore ore, ExNihiloTags.OreTag tags) {
    addIngot(ore, tags);
    addPiece(ore);
    addRaw(ore, tags);
    addNugget(ore, tags);
  }

  private void addNugget(Ore ore, ExNihiloTags.OreTag tags) {
    Either<ItemDefinition<OreItem>, Item> either = ore.getNuggetItem();
    if (either.left().isPresent()) {
      addToTag(tags.getNuggetTag(), either.left().get());
      getItemBuilder(Tags.Items.NUGGETS).add(tags.getNuggetTag());
    }
  }

  private void addRaw(Ore ore, ExNihiloTags.OreTag tags) {
    Either<ItemDefinition<OreItem>, Item> either = ore.getRawOreItem();
    if (either.left().isPresent()) {
      addToTag(tags.getRawMaterialTag(), either.left().get());
      getItemBuilder(Tags.Items.RAW_MATERIALS).add(tags.getRawMaterialTag());
    }
  }

  private void addPiece(Ore ore) {
    addToTag(ExNihiloTags.PIECE, ore.getPieceItem());
  }

  private void addIngot(Ore ore, ExNihiloTags.OreTag tags) {
    Either<ItemDefinition<OreItem>, Item> either = ore.getIngotItem();
    if (either.left().isPresent()) {
      addToTag(tags.getIngotTag(), either.left().get());
      getItemBuilder(Tags.Items.INGOTS).add(tags.getIngotTag());
    }
  }

  private void addFluids() {
    addToTag(FluidTags.WATER, EXNFluids.SEA_WATER);
  }

  private void addBlocks() {
    getBlockBuilder(ExNihiloTags.INFESTABLE)
      .add(BlockTags.LEAVES);
    getBlockBuilder(ExNihiloTags.MINEABLE_WITH_HAMMER)
      .add(Blocks.GRAVEL,
        Blocks.SAND,
        Blocks.TUBE_CORAL_BLOCK,
        Blocks.BRAIN_CORAL_BLOCK,
        Blocks.BUBBLE_CORAL_BLOCK,
        Blocks.FIRE_CORAL_BLOCK,
        Blocks.HORN_CORAL_BLOCK,
        Blocks.TUBE_CORAL,
        Blocks.BRAIN_CORAL,
        Blocks.BUBBLE_CORAL,
        Blocks.FIRE_CORAL,
        Blocks.HORN_CORAL,
        EXNBlocks.STONE_BARREL.block(),
        EXNBlocks.FIRED_CRUCIBLE.block())
      .add(BlockTags.MINEABLE_WITH_PICKAXE);
    getBlockBuilder(ExNihiloTags.MINEABLE_WITH_CROOK)
        .add(BlockTags.LEAVES)
        .add(EXNBlocks.INFESTING_LEAVES.block(), EXNBlocks.INFESTED_LEAVES.block());
    addToTag(
        BlockTags.MINEABLE_WITH_AXE,
        EXNBlocks.ACACIA_BARREL.block(),
        EXNBlocks.BAMBOO_BARREL.block(),
        EXNBlocks.BIRCH_BARREL.block(),
        EXNBlocks.CHERRY_BARREL.block(),
        EXNBlocks.CRIMSON_BARREL.block(),
        EXNBlocks.DARK_OAK_BARREL.block(),
        EXNBlocks.JUNGLE_BARREL.block(),
        EXNBlocks.MANGROVE_BARREL.block(),
        EXNBlocks.OAK_BARREL.block(),
        EXNBlocks.SPRUCE_BARREL.block(),
        EXNBlocks.WARPED_BARREL.block(),
        EXNBlocks.ACACIA_CRUCIBLE.block(),
        EXNBlocks.BAMBOO_CRUCIBLE.block(),
        EXNBlocks.BIRCH_CRUCIBLE.block(),
        EXNBlocks.CHERRY_CRUCIBLE.block(),
        EXNBlocks.CRIMSON_CRUCIBLE.block(),
        EXNBlocks.DARK_OAK_CRUCIBLE.block(),
        EXNBlocks.JUNGLE_CRUCIBLE.block(),
        EXNBlocks.MANGROVE_CRUCIBLE.block(),
        EXNBlocks.OAK_CRUCIBLE.block(),
        EXNBlocks.SPRUCE_CRUCIBLE.block(),
        EXNBlocks.WARPED_CRUCIBLE.block(),
        EXNBlocks.ACACIA_SIEVE.block(),
        EXNBlocks.BAMBOO_SIEVE.block(),
        EXNBlocks.BIRCH_SIEVE.block(),
        EXNBlocks.CHERRY_SIEVE.block(),
        EXNBlocks.CRIMSON_SIEVE.block(),
        EXNBlocks.DARK_OAK_SIEVE.block(),
        EXNBlocks.JUNGLE_SIEVE.block(),
        EXNBlocks.MANGROVE_SIEVE.block(),
        EXNBlocks.OAK_SIEVE.block(),
        EXNBlocks.SPRUCE_SIEVE.block(),
        EXNBlocks.WARPED_SIEVE.block());
    addToTag(
        BlockTags.MINEABLE_WITH_SHOVEL,
        EXNBlocks.CRUSHED_ANDESITE.block(),
        EXNBlocks.CRUSHED_BASALT.block(),
        EXNBlocks.CRUSHED_BLACKSTONE.block(),
        EXNBlocks.CRUSHED_CALCITE.block(),
        EXNBlocks.CRUSHED_DEEPSLATE.block(),
        EXNBlocks.CRUSHED_DIORITE.block(),
        EXNBlocks.CRUSHED_DRIPSTONE.block(),
        EXNBlocks.CRUSHED_END_STONE.block(),
        EXNBlocks.CRUSHED_GRANITE.block(),
        EXNBlocks.CRUSHED_NETHERRACK.block(),
        EXNBlocks.CRUSHED_TUFF.block(),
        EXNBlocks.DUST.block(),
        EXNBlocks.UNFIRED_CRUCIBLE.block());
  }
}
