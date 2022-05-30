package novamachina.exnihilosequentia.common;

import javax.annotation.Nonnull;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import novamachina.exnihilosequentia.api.datagen.AbstractItemTagGenerator;
import novamachina.exnihilosequentia.api.tag.ExNihiloTags;
import novamachina.exnihilosequentia.common.init.ExNihiloBlocks;
import novamachina.exnihilosequentia.common.init.ExNihiloItems;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

public class ExNihiloItemTagsGenerator extends AbstractItemTagGenerator {

  public ExNihiloItemTagsGenerator(
      @Nonnull final DataGenerator generator,
      @Nonnull final BlockTagsProvider blockTagsProvider,
      @Nonnull final ExistingFileHelper existingFileHelper) {
    super(
        generator,
        blockTagsProvider,
        ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA,
        existingFileHelper);
  }

  @Override
  protected void addTags() {
    registerOres();
    registerSeeds();
    registerMeats();
    registerHammers();
    registerCrooks();
    registerMisc();
    registerWoodenBarrel();
    registerWoodenCrucible();
    registerWoodenSieve();
  }

  private void registerCooked() {
    tag(ExNihiloTags.MEAT_COOKED).add(Items.COOKED_CHICKEN);
    tag(ExNihiloTags.MEAT_COOKED).add(Items.COOKED_COD);
    tag(ExNihiloTags.MEAT_COOKED).add(Items.COOKED_MUTTON);
    tag(ExNihiloTags.MEAT_COOKED).add(Items.COOKED_PORKCHOP);
    tag(ExNihiloTags.MEAT_COOKED).add(Items.COOKED_RABBIT);
    tag(ExNihiloTags.MEAT_COOKED).add(Items.COOKED_SALMON);
    tag(ExNihiloTags.MEAT_COOKED).add(Items.COOKED_BEEF);
  }

  private void registerCrooks() {
    tag(ExNihiloTags.CROOK).add(ExNihiloItems.CROOK_ANDESITE.get());
    //    tag(ExNihiloTags.CROOK).add(ExNihiloItems.CROOK_BASALT.get());
    //    tag(ExNihiloTags.CROOK).add(ExNihiloItems.CROOK_BLACKSTONE.get());
    tag(ExNihiloTags.CROOK).add(ExNihiloItems.CROOK_BONE.get());
    //    tag(ExNihiloTags.CROOK).add(ExNihiloItems.CROOK_CALCITE.get());
    //    tag(ExNihiloTags.CROOK).add(ExNihiloItems.CROOK_COPPER.get());
    //    tag(ExNihiloTags.CROOK).add(ExNihiloItems.CROOK_CRIMSON_FUNGUS.get());
    //    tag(ExNihiloTags.CROOK).add(ExNihiloItems.CROOK_DEEPSLATE.get());
    tag(ExNihiloTags.CROOK).add(ExNihiloItems.CROOK_DIAMOND.get());
    tag(ExNihiloTags.CROOK).add(ExNihiloItems.CROOK_DIORITE.get());
    //    tag(ExNihiloTags.CROOK).add(ExNihiloItems.CROOK_DRIPSTONE.get());
    tag(ExNihiloTags.CROOK).add(ExNihiloItems.CROOK_GRANITE.get());
    tag(ExNihiloTags.CROOK).add(ExNihiloItems.CROOK_GOLD.get());
    tag(ExNihiloTags.CROOK).add(ExNihiloItems.CROOK_IRON.get());
    //    tag(ExNihiloTags.CROOK).add(ExNihiloItems.CROOK_NETHER_BRICK.get());
    //    tag(ExNihiloTags.CROOK).add(ExNihiloItems.CROOK_NETHERITE.get());
    //    tag(ExNihiloTags.CROOK).add(ExNihiloItems.CROOK_PRISMARINE.get());
    //    tag(ExNihiloTags.CROOK).add(ExNihiloItems.CROOK_RED_NETHER_BRICK.get());
    tag(ExNihiloTags.CROOK).add(ExNihiloItems.CROOK_STONE.get());
    //    tag(ExNihiloTags.CROOK).add(ExNihiloItems.CROOK_TERRACOTTA.get());
    //    tag(ExNihiloTags.CROOK).add(ExNihiloItems.CROOK_TUFF.get());
    //    tag(ExNihiloTags.CROOK).add(ExNihiloItems.CROOK_WARPED_FUNGUS.get());
    tag(ExNihiloTags.CROOK).add(ExNihiloItems.CROOK_WOOD.get());
  }

  private void registerHammers() {
    //    tag(ExNihiloTags.HAMMER).add(ExNihiloItems.HAMMER_ANDESITE.get());
    //    tag(ExNihiloTags.HAMMER).add(ExNihiloItems.HAMMER_BASALT.get());
    //    tag(ExNihiloTags.HAMMER).add(ExNihiloItems.HAMMER_BLACKSTONE.get());
    //    tag(ExNihiloTags.HAMMER).add(ExNihiloItems.HAMMER_CALCITE.get());
    //    tag(ExNihiloTags.HAMMER).add(ExNihiloItems.HAMMER_COPPER.get());
    //    tag(ExNihiloTags.HAMMER).add(ExNihiloItems.HAMMER_CRIMSON_FUNGUS.get());
    //    tag(ExNihiloTags.HAMMER).add(ExNihiloItems.HAMMER_DEEPSLATE.get());
    tag(ExNihiloTags.HAMMER).add(ExNihiloItems.HAMMER_DIAMOND.get());
    //    tag(ExNihiloTags.HAMMER).add(ExNihiloItems.HAMMER_DIORITE.get());
    //    tag(ExNihiloTags.HAMMER).add(ExNihiloItems.HAMMER_DRIPSTONE.get());
    tag(ExNihiloTags.HAMMER).add(ExNihiloItems.HAMMER_GOLD.get());
    //    tag(ExNihiloTags.HAMMER).add(ExNihiloItems.HAMMER_GRANITE.get());
    tag(ExNihiloTags.HAMMER).add(ExNihiloItems.HAMMER_IRON.get());
    //    tag(ExNihiloTags.HAMMER).add(ExNihiloItems.HAMMER_NETHER_BRICK.get());
    tag(ExNihiloTags.HAMMER).add(ExNihiloItems.HAMMER_NETHERITE.get());
    //    tag(ExNihiloTags.HAMMER).add(ExNihiloItems.HAMMER_PRISMARINE.get());
    //    tag(ExNihiloTags.HAMMER).add(ExNihiloItems.HAMMER_RED_NETHER_BRICK.get());
    tag(ExNihiloTags.HAMMER).add(ExNihiloItems.HAMMER_STONE.get());
    //    tag(ExNihiloTags.HAMMER).add(ExNihiloItems.HAMMER_TERRACOTTA.get());
    //    tag(ExNihiloTags.HAMMER).add(ExNihiloItems.HAMMER_TUFF.get());
    //    tag(ExNihiloTags.HAMMER).add(ExNihiloItems.HAMMER_WARPED_FUNGUS.get());
    tag(ExNihiloTags.HAMMER).add(ExNihiloItems.HAMMER_WOOD.get());
  }

  private void registerMeats() {
    registerUncooked();
    registerCooked();
  }

  private void registerMisc() {
    tag(ExNihiloTags.CLAY).add(Items.CLAY_BALL);
    tag(ExNihiloTags.NUGGET_COPPER).add(ExNihiloItems.NUGGET_COPPER.get());
  }

  private void registerOres() {
    registerOre(ExNihiloItems.IRON, ExNihiloTags.IRON);
    registerOre(ExNihiloItems.GOLD, ExNihiloTags.GOLD);
    registerOre(ExNihiloItems.COPPER, ExNihiloTags.COPPER);
    registerOre(ExNihiloItems.LEAD, ExNihiloTags.LEAD);
    registerOre(ExNihiloItems.NICKEL, ExNihiloTags.NICKEL);
    registerOre(ExNihiloItems.SILVER, ExNihiloTags.SILVER);
    registerOre(ExNihiloItems.TIN, ExNihiloTags.TIN);
    registerOre(ExNihiloItems.ALUMINUM, ExNihiloTags.ALUMINUM);
    registerOre(ExNihiloItems.PLATINUM, ExNihiloTags.PLATINUM);
    registerOre(ExNihiloItems.URANIUM, ExNihiloTags.URANIUM);
    registerOre(ExNihiloItems.ZINC, ExNihiloTags.ZINC);
  }

  private void registerSeeds() {
    tag(Tags.Items.SEEDS).add(ExNihiloItems.SEED_OAK.get());
    tag(Tags.Items.SEEDS).add(ExNihiloItems.SEED_SPRUCE.get());
    tag(Tags.Items.SEEDS).add(ExNihiloItems.SEED_BIRCH.get());
    tag(Tags.Items.SEEDS).add(ExNihiloItems.SEED_JUNGLE.get());
    tag(Tags.Items.SEEDS).add(ExNihiloItems.SEED_ACACIA.get());
    tag(Tags.Items.SEEDS).add(ExNihiloItems.SEED_DARK_OAK.get());
    tag(Tags.Items.SEEDS).add(ExNihiloItems.SEED_CACTUS.get());
    tag(Tags.Items.SEEDS).add(ExNihiloItems.SEED_SUGARCANE.get());
    tag(Tags.Items.SEEDS).add(ExNihiloItems.SEED_CARROT.get());
    tag(Tags.Items.SEEDS).add(ExNihiloItems.SEED_POTATO.get());
    tag(Tags.Items.SEEDS).add(ExNihiloItems.SEED_SWEET_BERRY.get());
    tag(Tags.Items.SEEDS).add(ExNihiloItems.SEED_KELP.get());
    tag(Tags.Items.SEEDS).add(ExNihiloItems.SEED_PICKLE.get());
    tag(Tags.Items.SEEDS).add(ExNihiloItems.SEED_BAMBOO.get());
    tag(Tags.Items.SEEDS).add(ExNihiloItems.SEED_FERN.get());
    tag(Tags.Items.SEEDS).add(ExNihiloItems.SEED_LARGE_FERN.get());
  }

  private void registerUncooked() {
    tag(ExNihiloTags.MEAT_UNCOOKED).add(Items.CHICKEN);
    tag(ExNihiloTags.MEAT_UNCOOKED).add(Items.COD);
    tag(ExNihiloTags.MEAT_UNCOOKED).add(Items.MUTTON);
    tag(ExNihiloTags.MEAT_UNCOOKED).add(Items.PORKCHOP);
    tag(ExNihiloTags.MEAT_UNCOOKED).add(Items.RABBIT);
    tag(ExNihiloTags.MEAT_UNCOOKED).add(Items.SALMON);
    tag(ExNihiloTags.MEAT_UNCOOKED).add(Items.BEEF);
  }

  private void registerWoodenBarrel() {
    tag(ExNihiloTags.BARREL).add(ExNihiloBlocks.BARREL_ACACIA.get().asItem());
    tag(ExNihiloTags.BARREL).add(ExNihiloBlocks.BARREL_BIRCH.get().asItem());
    tag(ExNihiloTags.BARREL).add(ExNihiloBlocks.BARREL_DARK_OAK.get().asItem());
    tag(ExNihiloTags.BARREL).add(ExNihiloBlocks.BARREL_JUNGLE.get().asItem());
    tag(ExNihiloTags.BARREL).add(ExNihiloBlocks.BARREL_OAK.get().asItem());
    tag(ExNihiloTags.BARREL).add(ExNihiloBlocks.BARREL_SPRUCE.get().asItem());
    tag(ExNihiloTags.BARREL).add(ExNihiloBlocks.BARREL_CRIMSON.get().asItem());
    tag(ExNihiloTags.BARREL).add(ExNihiloBlocks.BARREL_WARPED.get().asItem());
  }

  private void registerWoodenCrucible() {
    tag(ExNihiloTags.CRUCIBLE).add(ExNihiloBlocks.CRUCIBLE_ACACIA.get().asItem());
    tag(ExNihiloTags.CRUCIBLE).add(ExNihiloBlocks.CRUCIBLE_BIRCH.get().asItem());
    tag(ExNihiloTags.CRUCIBLE).add(ExNihiloBlocks.CRUCIBLE_DARK_OAK.get().asItem());
    tag(ExNihiloTags.CRUCIBLE).add(ExNihiloBlocks.CRUCIBLE_JUNGLE.get().asItem());
    tag(ExNihiloTags.CRUCIBLE).add(ExNihiloBlocks.CRUCIBLE_OAK.get().asItem());
    tag(ExNihiloTags.CRUCIBLE).add(ExNihiloBlocks.CRUCIBLE_SPRUCE.get().asItem());
    tag(ExNihiloTags.CRUCIBLE).add(ExNihiloBlocks.CRUCIBLE_CRIMSON.get().asItem());
    tag(ExNihiloTags.CRUCIBLE).add(ExNihiloBlocks.CRUCIBLE_WARPED.get().asItem());
  }

  private void registerWoodenSieve() {
    tag(ExNihiloTags.SIEVE).add(ExNihiloBlocks.SIEVE_ACACIA.get().asItem());
    tag(ExNihiloTags.SIEVE).add(ExNihiloBlocks.SIEVE_BIRCH.get().asItem());
    tag(ExNihiloTags.SIEVE).add(ExNihiloBlocks.SIEVE_DARK_OAK.get().asItem());
    tag(ExNihiloTags.SIEVE).add(ExNihiloBlocks.SIEVE_JUNGLE.get().asItem());
    tag(ExNihiloTags.SIEVE).add(ExNihiloBlocks.SIEVE_OAK.get().asItem());
    tag(ExNihiloTags.SIEVE).add(ExNihiloBlocks.SIEVE_SPRUCE.get().asItem());
    tag(ExNihiloTags.SIEVE).add(ExNihiloBlocks.SIEVE_CRIMSON.get().asItem());
    tag(ExNihiloTags.SIEVE).add(ExNihiloBlocks.SIEVE_WARPED.get().asItem());
  }
}
