package novamachina.exnihilosequentia.datagen.common;

import java.util.Set;
import javax.annotation.Nonnull;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.data.ExistingFileHelper;
import novamachina.exnihilosequentia.api.tag.ExNihiloTags;
import novamachina.exnihilosequentia.common.item.CrookItem;
import novamachina.exnihilosequentia.common.item.HammerItem;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.datagen.api.datagen.AbstractItemTagGenerator;
import novamachina.exnihilosequentia.world.item.EXNItems;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;
import novamachina.novacore.world.item.ItemDefinition;

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
    Set<ItemDefinition<CrookItem>> crooks =
        Set.of(
            EXNItems.CROOK_ANDESITE,
            EXNItems.CROOK_BASALT,
            EXNItems.CROOK_BLACKSTONE,
            EXNItems.CROOK_BONE,
            EXNItems.CROOK_CALCITE,
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
    for (ItemDefinition<CrookItem> crook : crooks) {
      tag(ExNihiloTags.CROOK).add(crook.asItem());
    }
  }

  private void registerHammers() {
    Set<ItemDefinition<HammerItem>> hammers =
        Set.of(
            EXNItems.HAMMER_ANDESITE,
            EXNItems.HAMMER_BASALT,
            EXNItems.HAMMER_BLACKSTONE,
            EXNItems.HAMMER_BONE,
            EXNItems.HAMMER_CALCITE,
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
    for (ItemDefinition<HammerItem> hammer : hammers) {
      tag(ExNihiloTags.HAMMER).add(hammer.asItem());
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
    registerOre(EXNItems.IRON, ExNihiloTags.IRON);
    registerOre(EXNItems.GOLD, ExNihiloTags.GOLD);
    registerOre(EXNItems.COPPER, ExNihiloTags.COPPER);
    registerOre(EXNItems.LEAD, ExNihiloTags.LEAD);
    registerOre(EXNItems.NICKEL, ExNihiloTags.NICKEL);
    registerOre(EXNItems.SILVER, ExNihiloTags.SILVER);
    registerOre(EXNItems.TIN, ExNihiloTags.TIN);
    registerOre(EXNItems.ALUMINUM, ExNihiloTags.ALUMINUM);
    registerOre(EXNItems.PLATINUM, ExNihiloTags.PLATINUM);
    registerOre(EXNItems.URANIUM, ExNihiloTags.URANIUM);
    registerOre(EXNItems.ZINC, ExNihiloTags.ZINC);
    tag(ExNihiloTags.NUGGET_COPPER).add(EXNItems.COPPER.getNuggetItem().left().get().asItem());
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
    tag(ExNihiloTags.BARREL).add(EXNBlocks.ACACIA_BARREL.asItem());
    tag(ExNihiloTags.BARREL).add(EXNBlocks.BIRCH_BARREL.asItem());
    tag(ExNihiloTags.BARREL).add(EXNBlocks.DARK_OAK_BARREL.asItem());
    tag(ExNihiloTags.BARREL).add(EXNBlocks.JUNGLE_BARREL.asItem());
    tag(ExNihiloTags.BARREL).add(EXNBlocks.MANGROVE_BARREL.asItem());
    tag(ExNihiloTags.BARREL).add(EXNBlocks.OAK_BARREL.asItem());
    tag(ExNihiloTags.BARREL).add(EXNBlocks.SPRUCE_BARREL.asItem());
    tag(ExNihiloTags.BARREL).add(EXNBlocks.CRIMSON_BARREL.asItem());
    tag(ExNihiloTags.BARREL).add(EXNBlocks.WARPED_BARREL.asItem());
  }

  private void registerWoodenCrucible() {
    tag(ExNihiloTags.CRUCIBLE).add(EXNBlocks.ACACIA_CRUCIBLE.asItem());
    tag(ExNihiloTags.CRUCIBLE).add(EXNBlocks.BIRCH_CRUCIBLE.asItem());
    tag(ExNihiloTags.CRUCIBLE).add(EXNBlocks.DARK_OAK_CRUCIBLE.asItem());
    tag(ExNihiloTags.CRUCIBLE).add(EXNBlocks.JUNGLE_CRUCIBLE.asItem());
    tag(ExNihiloTags.CRUCIBLE).add(EXNBlocks.MANGROVE_CRUCIBLE.asItem());
    tag(ExNihiloTags.CRUCIBLE).add(EXNBlocks.OAK_CRUCIBLE.asItem());
    tag(ExNihiloTags.CRUCIBLE).add(EXNBlocks.SPRUCE_CRUCIBLE.asItem());
    tag(ExNihiloTags.CRUCIBLE).add(EXNBlocks.CRIMSON_CRUCIBLE.asItem());
    tag(ExNihiloTags.CRUCIBLE).add(EXNBlocks.WARPED_CRUCIBLE.asItem());
  }

  private void registerWoodenSieve() {
    tag(ExNihiloTags.SIEVE).add(EXNBlocks.ACACIA_SIEVE.asItem());
    tag(ExNihiloTags.SIEVE).add(EXNBlocks.BIRCH_SIEVE.asItem());
    tag(ExNihiloTags.SIEVE).add(EXNBlocks.DARK_OAK_SIEVE.asItem());
    tag(ExNihiloTags.SIEVE).add(EXNBlocks.JUNGLE_SIEVE.asItem());
    tag(ExNihiloTags.SIEVE).add(EXNBlocks.MANGROVE_SIEVE.asItem());
    tag(ExNihiloTags.SIEVE).add(EXNBlocks.OAK_SIEVE.asItem());
    tag(ExNihiloTags.SIEVE).add(EXNBlocks.SPRUCE_SIEVE.asItem());
    tag(ExNihiloTags.SIEVE).add(EXNBlocks.CRIMSON_SIEVE.asItem());
    tag(ExNihiloTags.SIEVE).add(EXNBlocks.WARPED_SIEVE.asItem());
  }
}
