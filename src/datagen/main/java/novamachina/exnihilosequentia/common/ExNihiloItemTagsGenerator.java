package novamachina.exnihilosequentia.common;

import javax.annotation.Nonnull;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import novamachina.exnihilosequentia.api.datagen.AbstractItemTagGenerator;
import novamachina.exnihilosequentia.api.tag.ExNihiloTags;
import novamachina.exnihilosequentia.common.init.ExNihiloBlocks;
import novamachina.exnihilosequentia.common.init.ExNihiloItems;
import novamachina.exnihilosequentia.common.item.CrookBaseItem;
import novamachina.exnihilosequentia.common.item.HammerBaseItem;
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
    registerNuggets();
    registerMisc();
    registerWoodenBarrel();
    registerWoodenCrucible();
    registerWoodenSieve();
  }

  private void registerNuggets() {
    for (RegistryObject<Item> nugget : ExNihiloItems.NUGGETS) {
      tag(Tags.Items.NUGGETS).add(nugget.get());
    }
    // TODO: Add nuggets
    //    tag(ExNihiloTags.NUGGET_COPPER).add(ExNihiloItems.COPPER.getNuggetItem());
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
    for (RegistryObject<CrookBaseItem> crook : ExNihiloItems.CROOKS) {
      tag(ExNihiloTags.CROOK).add(crook.get());
    }
  }

  private void registerHammers() {
    for (RegistryObject<HammerBaseItem> hammer : ExNihiloItems.HAMMERS) {
      tag(ExNihiloTags.HAMMER).add(hammer.get());
    }
  }

  private void registerMeats() {
    registerUncooked();
    registerCooked();
  }

  private void registerMisc() {
    tag(ExNihiloTags.CLAY).add(Items.CLAY_BALL);
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
    //    tag(Tags.Items.SEEDS).add(ExNihiloItems.SEED_OAK.get());
    //    tag(Tags.Items.SEEDS).add(ExNihiloItems.SEED_SPRUCE.get());
    //    tag(Tags.Items.SEEDS).add(ExNihiloItems.SEED_BIRCH.get());
    //    tag(Tags.Items.SEEDS).add(ExNihiloItems.SEED_JUNGLE.get());
    //    tag(Tags.Items.SEEDS).add(ExNihiloItems.SEED_ACACIA.get());
    //    tag(Tags.Items.SEEDS).add(ExNihiloItems.SEED_DARK_OAK.get());
    //    tag(Tags.Items.SEEDS).add(ExNihiloItems.SEED_CACTUS.get());
    //    tag(Tags.Items.SEEDS).add(ExNihiloItems.SEED_SUGARCANE.get());
    //    tag(Tags.Items.SEEDS).add(ExNihiloItems.SEED_CARROT.get());
    //    tag(Tags.Items.SEEDS).add(ExNihiloItems.SEED_POTATO.get());
    //    tag(Tags.Items.SEEDS).add(ExNihiloItems.SEED_SWEET_BERRY.get());
    //    tag(Tags.Items.SEEDS).add(ExNihiloItems.SEED_KELP.get());
    //    tag(Tags.Items.SEEDS).add(ExNihiloItems.SEED_PICKLE.get());
    //    tag(Tags.Items.SEEDS).add(ExNihiloItems.SEED_BAMBOO.get());
    //    tag(Tags.Items.SEEDS).add(ExNihiloItems.SEED_FERN.get());
    //    tag(Tags.Items.SEEDS).add(ExNihiloItems.SEED_LARGE_FERN.get());
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
