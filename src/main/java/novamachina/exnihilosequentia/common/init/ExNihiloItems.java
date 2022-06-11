package novamachina.exnihilosequentia.common.init;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.common.Tags;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import novamachina.exnihilosequentia.common.item.CookedSilkwormItem;
import novamachina.exnihilosequentia.common.item.CrookBaseItem;
import novamachina.exnihilosequentia.common.item.DollItem;
import novamachina.exnihilosequentia.common.item.HammerBaseItem;
import novamachina.exnihilosequentia.common.item.MeshItem;
import novamachina.exnihilosequentia.common.item.PebbleItem;
import novamachina.exnihilosequentia.common.item.ResourceItem;
import novamachina.exnihilosequentia.common.item.SeedBaseItem;
import novamachina.exnihilosequentia.common.item.SilkwormItem;
import novamachina.exnihilosequentia.common.item.mesh.MeshType;
import novamachina.exnihilosequentia.common.item.ore.Ore;
import novamachina.exnihilosequentia.common.utility.Color;
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

public class ExNihiloItems {

  @Nonnull
  private static final DeferredRegister<Item> ITEMS =
      DeferredRegister.create(ForgeRegistries.ITEMS, ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA);
  // Begin Items Only
  @Nonnull
  public static final RegistryObject<CookedSilkwormItem> COOKED_SILKWORM =
      ITEMS.register(ExNihiloConstants.Items.COOKED_SILKWORM, CookedSilkwormItem::new);

  @Nonnull
  public static final RegistryObject<Item> SILKWORM =
      ITEMS.register(
          ExNihiloConstants.Items.SILKWORM,
          () -> new SilkwormItem(ExNihiloConstants.Items.SILKWORM));

  public static final Ore IRON =
      new Ore(ExNihiloConstants.Ore.IRON, true, false, false, new Color("BF8040"), ITEMS);
  public static final Ore GOLD =
      new Ore(ExNihiloConstants.Ore.GOLD, true, false, false, new Color("FFFF00"), ITEMS);
  public static final Ore COPPER =
      new Ore(ExNihiloConstants.Ore.COPPER, true, false, false, new Color("FF9933"), ITEMS);
  public static final Ore LEAD =
      new Ore(ExNihiloConstants.Ore.LEAD, true, true, true, new Color("330066"), ITEMS);
  public static final Ore NICKEL =
      new Ore(ExNihiloConstants.Ore.NICKEL, true, true, true, new Color("FFFFCC"), ITEMS);
  public static final Ore SILVER =
      new Ore(ExNihiloConstants.Ore.SILVER, true, true, true, new Color("C2FAFF"), ITEMS);
  public static final Ore TIN =
      new Ore(ExNihiloConstants.Ore.TIN, true, true, true, new Color("F5FEFF"), ITEMS);
  public static final Ore ALUMINUM =
      new Ore(ExNihiloConstants.Ore.ALUMINUM, true, true, true, new Color("BFBFBF"), ITEMS);
  public static final Ore PLATINUM =
      new Ore(ExNihiloConstants.Ore.PLATINUM, true, true, true, new Color("00FFF7"), ITEMS);
  public static final Ore URANIUM =
      new Ore(ExNihiloConstants.Ore.URANIUM, true, true, true, new Color("4E5B43"), ITEMS);
  public static final Ore ZINC =
      new Ore(ExNihiloConstants.Ore.ZINC, true, true, true, new Color("A59C74"), ITEMS);
  public static final RegistryObject<Item> NUGGET_COPPER =
      ITEMS.register(
          ExNihiloConstants.Items.COPPER_NUGGET,
          () -> new ResourceItem(ExNihiloConstants.Items.COPPER_NUGGET));

  public static final RegistryObject<DollItem> BLAZE_DOLL =
      ITEMS.register(
          ExNihiloConstants.Items.BLAZE_DOLL,
          () ->
              new DollItem(
                  ExNihiloConstants.ModIds.MINECRAFT,
                  "blaze",
                  "minecraft",
                  "lava",
                  1,
                  ExNihiloConstants.Tooltips.BLAZE));
  public static final RegistryObject<DollItem> ENDERMAN_DOLL =
      ITEMS.register(
          ExNihiloConstants.Items.ENDERMAN_DOLL,
          () ->
              new DollItem(
                  ExNihiloConstants.ModIds.MINECRAFT,
                  "enderman",
                  ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA,
                  ExNihiloConstants.Fluids.WITCH_WATER,
                  2,
                  ExNihiloConstants.Tooltips.ENDERMAN));
  public static final RegistryObject<DollItem> SHULKER_DOLL =
      ITEMS.register(
          ExNihiloConstants.Items.SHULKER_DOLL,
          () ->
              new DollItem(
                  ExNihiloConstants.ModIds.MINECRAFT,
                  "shulker",
                  ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA,
                  ExNihiloConstants.Fluids.WITCH_WATER,
                  1.5,
                  ExNihiloConstants.Tooltips.SHULKER));
  public static final RegistryObject<DollItem> GUARDIAN_DOLL =
      ITEMS.register(
          ExNihiloConstants.Items.GUARDIAN_DOLL,
          () ->
              new DollItem(
                  ExNihiloConstants.ModIds.MINECRAFT,
                  "guardian",
                  ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA,
                  ExNihiloConstants.Fluids.SEA_WATER,
                  1,
                  ExNihiloConstants.Tooltips.GUARDIAN));
  public static final RegistryObject<DollItem> BEE_DOLL =
      ITEMS.register(
          ExNihiloConstants.Items.BEE_DOLL,
          () ->
              new DollItem(
                  ExNihiloConstants.ModIds.MINECRAFT,
                  "bee",
                  ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA,
                  ExNihiloConstants.Fluids.WITCH_WATER,
                  1,
                  ExNihiloConstants.Tooltips.BEE));

  public static final RegistryObject<PebbleItem> PEBBLE_ANDESITE =
      ITEMS.register(ExNihiloConstants.Items.ANDESITE_PEBBLE, PebbleItem::new);
  public static final RegistryObject<PebbleItem> PEBBLE_BASALT =
      ITEMS.register(ExNihiloConstants.Items.BASALT_PEBBLE, PebbleItem::new);
  public static final RegistryObject<PebbleItem> PEBBLE_BLACKSTONE =
      ITEMS.register(ExNihiloConstants.Items.BLACKSTONE_PEBBLE, PebbleItem::new);
  //  public static final RegistryObject<PebbleItem> PEBBLE_CALCITE = ITEMS.register(
  //      ExNihiloConstants.Items.CALCITE_PEBBLE,
  //      PebbleItem::new);
  //  public static final RegistryObject<PebbleItem> PEBBLE_DEEPSLATE = ITEMS.register(
  //      ExNihiloConstants.Items.DEEPSLATE_PEBBLE,
  //      PebbleItem::new);
  public static final RegistryObject<PebbleItem> PEBBLE_DIORITE =
      ITEMS.register(ExNihiloConstants.Items.DIORITE_PEBBLE, PebbleItem::new);
  //  public static final RegistryObject<PebbleItem> PEBBLE_DRIPSTONE = ITEMS.register(
  //      ExNihiloConstants.Items.DRIPSTONE_PEBBLE,
  //      PebbleItem::new);
  //  public static final RegistryObject<PebbleItem> PEBBLE_END_STONE = ITEMS.register(
  //      ExNihiloConstants.Items.END_STONE_PEBBLE,
  //      PebbleItem::new);
  public static final RegistryObject<PebbleItem> PEBBLE_GRANITE =
      ITEMS.register(ExNihiloConstants.Items.GRANITE_PEBBLE, PebbleItem::new);
  //  public static final RegistryObject<PebbleItem> PEBBLE_NETHERRACK = ITEMS.register(
  //      ExNihiloConstants.Items.NETHERRACK_PEBBLE,
  //      PebbleItem::new);
  public static final RegistryObject<PebbleItem> PEBBLE_STONE =
      ITEMS.register(ExNihiloConstants.Items.STONE_PEBBLE, PebbleItem::new);
  //  public static final RegistryObject<PebbleItem> PEBBLE_TUFF = ITEMS.register(
  //      ExNihiloConstants.Items.TUFF_PEBBLE, PebbleItem::new);
  @Nonnull
  public static final RegistryObject<ResourceItem> MYCELIUM_SPORE =
      ITEMS.register(
          ExNihiloConstants.Items.MYCELIUM_SPORE,
          () -> new ResourceItem(ExNihiloConstants.Items.MYCELIUM_SPORE, Blocks.DIRT, Blocks.MYCELIUM));
  //  public static final RegistryObject<ResourceItem> CRIMSON_NYLIUM_SPORE = ITEMS
  //      .register(ExNihiloConstants.Items.CRIMSON_NYLIUM_SPORE,
  //          () -> new ResourceItem(ExNihiloConstants.Items.CRIMSON_NYLIUM_SPORE));
  //  public static final RegistryObject<ResourceItem> WARPED_NYLIUM_SPORE = ITEMS
  //      .register(ExNihiloConstants.Items.WARPED_NYLIUM_SPORE,
  //          () -> new ResourceItem(ExNihiloConstants.Items.WARPED_NYLIUM_SPORE));
  @Nonnull
  public static final RegistryObject<ResourceItem> GRASS_SEED =
      ITEMS.register(
          ExNihiloConstants.Items.GRASS_SEED,
          () -> new ResourceItem(ExNihiloConstants.Items.GRASS_SEED, Blocks.DIRT, Blocks.GRASS_BLOCK));

  @Nonnull
  public static final RegistryObject<ResourceItem> PORCELAIN_CLAY =
      ITEMS.register(
          ExNihiloConstants.Items.PORCELAIN_CLAY,
          () -> new ResourceItem(ExNihiloConstants.Items.PORCELAIN_CLAY));

  @Nonnull
  public static final RegistryObject<ResourceItem> CRAFTING_DOLL =
      ITEMS.register(
          ExNihiloConstants.Items.CRAFTING_DOLL,
          () -> new ResourceItem(ExNihiloConstants.Items.CRAFTING_DOLL));

  @Nonnull
  public static final RegistryObject<ResourceItem> BLUE_CORAL_SEED =
      ITEMS.register(
          ExNihiloConstants.Items.BLUE_CORAL_SEED,
          () -> new ResourceItem(ExNihiloConstants.Items.BLUE_CORAL_SEED));

  @Nonnull
  public static final RegistryObject<ResourceItem> PINK_CORAL_SEED =
      ITEMS.register(
          ExNihiloConstants.Items.PINK_CORAL_SEED,
          () -> new ResourceItem(ExNihiloConstants.Items.PINK_CORAL_SEED));

  @Nonnull
  public static final RegistryObject<ResourceItem> PURPLE_CORAL_SEED =
      ITEMS.register(
          ExNihiloConstants.Items.PURPLE_CORAL_SEED,
          () -> new ResourceItem(ExNihiloConstants.Items.PURPLE_CORAL_SEED));

  @Nonnull
  public static final RegistryObject<ResourceItem> RED_CORAL_SEED =
      ITEMS.register(
          ExNihiloConstants.Items.RED_CORAL_SEED,
          () -> new ResourceItem(ExNihiloConstants.Items.RED_CORAL_SEED));

  @Nonnull
  public static final RegistryObject<ResourceItem> YELLOW_CORAL_SEED =
      ITEMS.register(
          ExNihiloConstants.Items.YELLOW_CORAL_SEED,
          () -> new ResourceItem(ExNihiloConstants.Items.YELLOW_CORAL_SEED));

  @Nonnull
  public static final RegistryObject<ResourceItem> BEEHIVE_FRAME =
      ITEMS.register(
          ExNihiloConstants.Items.BEEHIVE_FRAME,
          () -> new ResourceItem(ExNihiloConstants.Items.BEEHIVE_FRAME));

  @Nonnull
  public static final RegistryObject<SeedBaseItem> SEED_OAK =
      ITEMS.register(
          ExNihiloConstants.Items.SEED_OAK,
          () -> new SeedBaseItem(Blocks.OAK_SAPLING.defaultBlockState(), PlantType.PLAINS));

  @Nonnull
  public static final RegistryObject<SeedBaseItem> SEED_SPRUCE =
      ITEMS.register(
          ExNihiloConstants.Items.SEED_SPRUCE,
          () -> new SeedBaseItem(Blocks.SPRUCE_SAPLING.defaultBlockState(), PlantType.PLAINS));

  @Nonnull
  public static final RegistryObject<SeedBaseItem> SEED_BIRCH =
      ITEMS.register(
          ExNihiloConstants.Items.SEED_BIRCH,
          () -> new SeedBaseItem(Blocks.BIRCH_SAPLING.defaultBlockState(), PlantType.PLAINS));

  @Nonnull
  public static final RegistryObject<SeedBaseItem> SEED_JUNGLE =
      ITEMS.register(
          ExNihiloConstants.Items.SEED_JUNGLE,
          () -> new SeedBaseItem(Blocks.JUNGLE_SAPLING.defaultBlockState(), PlantType.PLAINS));

  @Nonnull
  public static final RegistryObject<SeedBaseItem> SEED_ACACIA =
      ITEMS.register(
          ExNihiloConstants.Items.ACACIA_SEED,
          () -> new SeedBaseItem(Blocks.ACACIA_SAPLING.defaultBlockState(), PlantType.PLAINS));

  @Nonnull
  public static final RegistryObject<SeedBaseItem> SEED_DARK_OAK =
      ITEMS.register(
          ExNihiloConstants.Items.SEED_DARK_OAK,
          () -> new SeedBaseItem(Blocks.DARK_OAK_SAPLING.defaultBlockState(), PlantType.PLAINS));

  @Nonnull
  public static final RegistryObject<SeedBaseItem> SEED_CACTUS =
      ITEMS.register(
          ExNihiloConstants.Items.SEED_CACTUS,
          () -> new SeedBaseItem(Blocks.CACTUS.defaultBlockState(), PlantType.DESERT));

  @Nonnull
  public static final RegistryObject<SeedBaseItem> SEED_SUGARCANE =
      ITEMS.register(
          ExNihiloConstants.Items.SEED_SUGARCANE,
          () -> new SeedBaseItem(Blocks.SUGAR_CANE.defaultBlockState(), PlantType.BEACH));

  @Nonnull
  public static final RegistryObject<SeedBaseItem> SEED_CARROT =
      ITEMS.register(
          ExNihiloConstants.Items.SEED_CARROT,
          () -> new SeedBaseItem(Blocks.CARROTS.defaultBlockState(), PlantType.CROP));

  @Nonnull
  public static final RegistryObject<SeedBaseItem> SEED_POTATO =
      ITEMS.register(
          ExNihiloConstants.Items.SEED_POTATO,
          () -> new SeedBaseItem(Blocks.POTATOES.defaultBlockState(), PlantType.CROP));

  @Nonnull
  public static final RegistryObject<SeedBaseItem> SEED_SWEET_BERRY =
      ITEMS.register(
          ExNihiloConstants.Items.SEED_SWEET_BERRY,
          () -> new SeedBaseItem(Blocks.SWEET_BERRY_BUSH.defaultBlockState(), PlantType.PLAINS));

  @Nonnull
  public static final RegistryObject<SeedBaseItem> SEED_KELP =
      ITEMS.register(
          ExNihiloConstants.Items.SEED_KELP,
          () -> new SeedBaseItem(Blocks.KELP_PLANT.defaultBlockState(), PlantType.WATER));

  @Nonnull
  public static final RegistryObject<SeedBaseItem> SEED_PICKLE =
      ITEMS.register(
          ExNihiloConstants.Items.SEED_PICKLE,
          () -> new SeedBaseItem(Blocks.SEA_PICKLE.defaultBlockState(), PlantType.WATER));

  @Nonnull
  public static final RegistryObject<SeedBaseItem> SEED_BAMBOO =
      ITEMS.register(
          ExNihiloConstants.Items.SEED_BAMBOO,
          () -> new SeedBaseItem(Blocks.BAMBOO_SAPLING.defaultBlockState(), PlantType.PLAINS));

  @Nonnull
  public static final RegistryObject<SeedBaseItem> SEED_FERN =
      ITEMS.register(
          ExNihiloConstants.Items.SEED_FERN,
          () -> new SeedBaseItem(Blocks.FERN.defaultBlockState(), PlantType.PLAINS));

  @Nonnull
  public static final RegistryObject<SeedBaseItem> SEED_LARGE_FERN =
      ITEMS.register(
          ExNihiloConstants.Items.SEED_LARGE_FERN,
          () -> new SeedBaseItem(Blocks.LARGE_FERN.defaultBlockState(), PlantType.PLAINS));

  @Nonnull
  public static final RegistryObject<MeshItem> MESH_STRING =
      ITEMS.register(
          ExNihiloConstants.Items.STRING_MESH,
          () ->
              new MeshItem(
                  ExNihiloConstants.Items.STRING_MESH,
                  Config.getMeshStringValue(),
                  MeshType.STRING));

  @Nonnull
  public static final RegistryObject<MeshItem> MESH_FLINT =
      ITEMS.register(
          ExNihiloConstants.Items.FLINT_MESH,
          () ->
              new MeshItem(
                  ExNihiloConstants.Items.FLINT_MESH, Config.getMeshFlintValue(), MeshType.FLINT));

  @Nonnull
  public static final RegistryObject<MeshItem> MESH_IRON =
      ITEMS.register(
          ExNihiloConstants.Items.IRON_MESH,
          () ->
              new MeshItem(
                  ExNihiloConstants.Items.IRON_MESH, Config.getMeshIronValue(), MeshType.IRON));

  @Nonnull
  public static final RegistryObject<MeshItem> MESH_DIAMOND =
      ITEMS.register(
          ExNihiloConstants.Items.DIAMOND_MESH,
          () ->
              new MeshItem(
                  ExNihiloConstants.Items.DIAMOND_MESH,
                  Config.getMeshDiamondValue(),
                  MeshType.DIAMOND));

  @Nonnull
  public static final RegistryObject<MeshItem> MESH_EMERALD =
      ITEMS.register(
          ExNihiloConstants.Items.EMERALD_MESH,
          () ->
              new MeshItem(
                  ExNihiloConstants.Items.EMERALD_MESH,
                  Config.getMeshEmeraldValue(),
                  MeshType.EMERALD));

  @Nonnull
  public static final RegistryObject<MeshItem> MESH_NETHERITE =
      ITEMS.register(
          ExNihiloConstants.Items.NETHERITE_MESH,
          () ->
              new MeshItem(
                  ExNihiloConstants.Items.NETHERITE_MESH,
                  Config.getMeshNetheriteValue(),
                  MeshType.NETHERITE));

  public static final RegistryObject<CrookBaseItem> CROOK_ANDESITE =
      ITEMS.register(
          ExNihiloConstants.Items.ANDISITE_CROOK,
          () -> new CrookBaseItem(Tiers.STONE, Config.getCrookAndesiteDurability()));
  //  public static final RegistryObject<CrookBaseItem> CROOK_BASALT = ITEMS.register(
  //      ExNihiloConstants.Items.BASALT_CROOK,
  //      () -> new CrookBaseItem(Tiers.STONE, Config.getCrookAndesiteDurability()));
  //  public static final RegistryObject<CrookBaseItem> CROOK_BLACKSTONE = ITEMS.register(
  //      ExNihiloConstants.Items.BLACKSTONE_CROOK,
  //      () -> new CrookBaseItem(Tiers.STONE, Config.getCrookAndesiteDurability()));
  public static final RegistryObject<CrookBaseItem> CROOK_BONE =
      ITEMS.register(
          ExNihiloConstants.Items.BONE_CROOK,
          () -> new CrookBaseItem(Tiers.STONE, Config.getCrookBoneDurability()));
  //  public static final RegistryObject<CrookBaseItem> CROOK_CALCITE = ITEMS.register(
  //      ExNihiloConstants.Items.CALCITE_CROOK,
  //      () -> new CrookBaseItem(Tiers.STONE, Config.getCrookAndesiteDurability()));
  //  public static final RegistryObject<CrookBaseItem> CROOK_COPPER = ITEMS.register(
  //      ExNihiloConstants.Items.COPPER_CROOK,
  //      () -> new CrookBaseItem(Tiers.IRON, Config.getCrookIronDurability()));
  //  public static final RegistryObject<CrookBaseItem> CROOK_CRIMSON_FUNGUS = ITEMS.register(
  //      ExNihiloConstants.Items.CRIMSON_FUNGUS_CROOK,
  //      () -> new CrookBaseItem(Tiers.STONE, Config.getCrookWoodDurability()));
  //  public static final RegistryObject<CrookBaseItem> CROOK_DEEPSLATE = ITEMS.register(
  //      ExNihiloConstants.Items.DEEPSLATE_CROOK,
  //      () -> new CrookBaseItem(Tiers.STONE, Config.getCrookStoneDurability()));
  public static final RegistryObject<CrookBaseItem> CROOK_DIAMOND =
      ITEMS.register(
          ExNihiloConstants.Items.DIAMOND_CROOK,
          () -> new CrookBaseItem(Tiers.DIAMOND, Config.getCrookDiamondDurability()));
  public static final RegistryObject<CrookBaseItem> CROOK_DIORITE =
      ITEMS.register(
          ExNihiloConstants.Items.DIORITE_CROOK,
          () -> new CrookBaseItem(Tiers.STONE, Config.getCrookDioriteDurability()));
  //  public static final RegistryObject<CrookBaseItem> CROOK_DRIPSTONE = ITEMS.register(
  //      ExNihiloConstants.Items.DRIPSTONE_CROOK,
  //      () -> new CrookBaseItem(Tiers.STONE, Config.getCrookDioriteDurability()));
  public static final RegistryObject<CrookBaseItem> CROOK_GOLD =
      ITEMS.register(
          ExNihiloConstants.Items.GOLD_CROOK,
          () -> new CrookBaseItem(Tiers.IRON, Config.getCrookGoldDurability()));
  public static final RegistryObject<CrookBaseItem> CROOK_GRANITE =
      ITEMS.register(
          ExNihiloConstants.Items.GRANITE_CROOK,
          () -> new CrookBaseItem(Tiers.STONE, Config.getCrookGraniteDurability()));
  public static final RegistryObject<CrookBaseItem> CROOK_IRON =
      ITEMS.register(
          ExNihiloConstants.Items.IRON_CROOK,
          () -> new CrookBaseItem(Tiers.IRON, Config.getCrookIronDurability()));
  //  public static final RegistryObject<CrookBaseItem> CROOK_NETHER_BRICK = ITEMS.register(
  //      ExNihiloConstants.Items.NETHER_BRICK_CROOK,
  //      () -> new CrookBaseItem(Tiers.STONE, Config.getCrookStoneDurability()));
  //  public static final RegistryObject<CrookBaseItem> CROOK_NETHERITE = ITEMS.register(
  //      ExNihiloConstants.Items.NETHERITE_CROOK,
  //      () -> new CrookBaseItem(Tiers.NETHERITE, Config.getCrookNetheriteDurability()));
  //  public static final RegistryObject<CrookBaseItem> CROOK_PRISMARINE = ITEMS.register(
  //      ExNihiloConstants.Items.PRISMARINE_CROOK,
  //      () -> new CrookBaseItem(Tiers.STONE, Config.getCrookStoneDurability()));
  //  public static final RegistryObject<CrookBaseItem> CROOK_RED_NETHER_BRICK = ITEMS.register(
  //      ExNihiloConstants.Items.RED_NETHER_BRICK_CROOK,
  //      () -> new CrookBaseItem(Tiers.STONE, Config.getCrookStoneDurability()));
  public static final RegistryObject<CrookBaseItem> CROOK_STONE =
      ITEMS.register(
          ExNihiloConstants.Items.STONE_CROOK,
          () -> new CrookBaseItem(Tiers.STONE, Config.getCrookStoneDurability()));
  //  public static final RegistryObject<CrookBaseItem> CROOK_TERRACOTTA = ITEMS.register(
  //      ExNihiloConstants.Items.TERRACOTTA_CROOK,
  //      () -> new CrookBaseItem(Tiers.STONE, Config.getCrookStoneDurability()));
  //  public static final RegistryObject<CrookBaseItem> CROOK_TUFF = ITEMS.register(
  //      ExNihiloConstants.Items.TUFF_CROOK,
  //      () -> new CrookBaseItem(Tiers.STONE, Config.getCrookStoneDurability()));
  //  public static final RegistryObject<CrookBaseItem> CROOK_WARPED_FUNGUS = ITEMS.register(
  //      ExNihiloConstants.Items.WARPED_FUNGUS_CROOK,
  //      () -> new CrookBaseItem(Tiers.STONE, Config.getCrookStoneDurability()));
  public static final RegistryObject<CrookBaseItem> CROOK_WOOD =
      ITEMS.register(
          ExNihiloConstants.Items.WOODEN_CROOK,
          () -> new CrookBaseItem(Tiers.WOOD, Config.getCrookWoodDurability()));

  //  public static final RegistryObject<HammerBaseItem> HAMMER_ANDESITE =
  //      ITEMS.register(ExNihiloConstants.Items.ANDESITE_HAMMER, () -> new
  // HammerBaseItem(Tiers.STONE,
  //          Config.getHammerStoneDurability()));
  //  public static final RegistryObject<HammerBaseItem> HAMMER_BASALT =
  //      ITEMS.register(ExNihiloConstants.Items.BASALT_HAMMER, () -> new
  // HammerBaseItem(Tiers.STONE,
  //          Config.getHammerStoneDurability()));
  //  public static final RegistryObject<HammerBaseItem> HAMMER_BLACKSTONE =
  //      ITEMS.register(ExNihiloConstants.Items.BLACKSTONE_HAMMER,
  //          () -> new HammerBaseItem(Tiers.STONE,
  //              Config.getHammerStoneDurability()));
  //  public static final RegistryObject<HammerBaseItem> HAMMER_CALCITE =
  //      ITEMS.register(ExNihiloConstants.Items.CALCITE_HAMMER, () -> new
  // HammerBaseItem(Tiers.STONE,
  //          Config.getHammerStoneDurability()));
  //  public static final RegistryObject<HammerBaseItem> HAMMER_COPPER =
  //      ITEMS.register(ExNihiloConstants.Items.COPPER_HAMMER, () -> new HammerBaseItem(Tiers.IRON,
  //          Config.getHammerIronDurability()));
  //  public static final RegistryObject<HammerBaseItem> HAMMER_CRIMSON_FUNGUS =
  //      ITEMS.register(ExNihiloConstants.Items.CRIMSON_FUNGUS_HAMMER,
  //          () -> new HammerBaseItem(Tiers.WOOD,
  //              Config.getHammerWoodDurability()));
  //  public static final RegistryObject<HammerBaseItem> HAMMER_DEEPSLATE =
  //      ITEMS.register(ExNihiloConstants.Items.DEEPSLATE_HAMMER, () -> new
  // HammerBaseItem(Tiers.STONE,
  //          Config.getHammerStoneDurability()));
  public static final RegistryObject<HammerBaseItem> HAMMER_DIAMOND =
      ITEMS.register(
          ExNihiloConstants.Items.DIAMOND_HAMMER,
          () -> new HammerBaseItem(Tiers.DIAMOND, Config.getHammerDiamondDurability()));
  //  public static final RegistryObject<HammerBaseItem> HAMMER_DIORITE =
  //      ITEMS.register(ExNihiloConstants.Items.DIORITE_HAMMER, () -> new
  // HammerBaseItem(Tiers.STONE,
  //          Config.getHammerStoneDurability()));
  //  public static final RegistryObject<HammerBaseItem> HAMMER_DRIPSTONE =
  //      ITEMS.register(ExNihiloConstants.Items.DRIPSTONE_HAMMER, () -> new
  // HammerBaseItem(Tiers.STONE,
  //          Config.getHammerStoneDurability()));
  public static final RegistryObject<HammerBaseItem> HAMMER_GOLD =
      ITEMS.register(
          ExNihiloConstants.Items.GOLD_HAMMER,
          () -> new HammerBaseItem(Tiers.GOLD, Config.getHammerGoldDurability()));
  //  public static final RegistryObject<HammerBaseItem> HAMMER_GRANITE =
  //      ITEMS.register(ExNihiloConstants.Items.GRANITE_HAMMER, () -> new
  // HammerBaseItem(Tiers.STONE,
  //          Config.getHammerStoneDurability()));
  public static final RegistryObject<HammerBaseItem> HAMMER_IRON =
      ITEMS.register(
          ExNihiloConstants.Items.IRON_HAMMER,
          () -> new HammerBaseItem(Tiers.IRON, Config.getHammerIronDurability()));
  //  public static final RegistryObject<HammerBaseItem> HAMMER_NETHER_BRICK =
  //      ITEMS.register(ExNihiloConstants.Items.NETHER_BRICK_HAMMER,
  //          () -> new HammerBaseItem(Tiers.STONE,
  //              Config.getHammerStoneDurability()));
  public static final RegistryObject<HammerBaseItem> HAMMER_NETHERITE =
      ITEMS.register(
          ExNihiloConstants.Items.NETHERITE_HAMMER,
          () -> new HammerBaseItem(Tiers.NETHERITE, Config.getHammerNetheriteDurability()));
  //  public static final RegistryObject<HammerBaseItem> HAMMER_PRISMARINE =
  //      ITEMS.register(ExNihiloConstants.Items.PRISMARINE_HAMMER,
  //          () -> new HammerBaseItem(Tiers.STONE,
  //              Config.getHammerStoneDurability()));
  //  public static final RegistryObject<HammerBaseItem> HAMMER_RED_NETHER_BRICK =
  //      ITEMS.register(ExNihiloConstants.Items.RED_NETHER_BRICK_HAMMER,
  //          () -> new HammerBaseItem(Tiers.STONE,
  //              Config.getHammerStoneDurability()));
  public static final RegistryObject<HammerBaseItem> HAMMER_STONE =
      ITEMS.register(
          ExNihiloConstants.Items.STONE_HAMMER,
          () -> new HammerBaseItem(Tiers.STONE, Config.getHammerStoneDurability()));
  //  public static final RegistryObject<HammerBaseItem> HAMMER_TERRACOTTA =
  //      ITEMS.register(ExNihiloConstants.Items.TERRACOTTA_HAMMER,
  //          () -> new HammerBaseItem(Tiers.STONE,
  //              Config.getHammerStoneDurability()));
  //  public static final RegistryObject<HammerBaseItem> HAMMER_TUFF =
  //      ITEMS.register(ExNihiloConstants.Items.TUFF_HAMMER, () -> new HammerBaseItem(Tiers.STONE,
  //          Config.getHammerStoneDurability()));
  //  public static final RegistryObject<HammerBaseItem> HAMMER_WARPED_FUNGUS =
  //      ITEMS.register(ExNihiloConstants.Items.WARPED_FUNGUS_HAMMER,
  //          () -> new HammerBaseItem(Tiers.WOOD,
  //              Config.getHammerWoodDurability()));
  public static final RegistryObject<HammerBaseItem> HAMMER_WOOD =
      ITEMS.register(
          ExNihiloConstants.Items.WOODEN_HAMMER,
          () -> new HammerBaseItem(Tiers.WOOD, Config.getHammerWoodDurability()));
  @Nonnull private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());
  // Begin Block Items
  @Nonnull
  private static final Item.Properties tab =
      new Item.Properties().tab(ExNihiloInitialization.ITEM_GROUP);

  @Nonnull
  public static final RegistryObject<BlockItem> DUST =
      ITEMS.register(
          ExNihiloConstants.Blocks.DUST, () -> new BlockItem(ExNihiloBlocks.DUST.get(), tab));

  @Nonnull
  public static final RegistryObject<BlockItem> CRUSHED_NETHERRACK =
      ITEMS.register(
          ExNihiloConstants.Blocks.CRUSHED_NETHERRACK,
          () -> new BlockItem(ExNihiloBlocks.CRUSHED_NETHERRACK.get(), tab));

  @Nonnull
  public static final RegistryObject<BlockItem> CRUSHED_END_STONE =
      ITEMS.register(
          ExNihiloConstants.Blocks.CRUSHED_END_STONE,
          () -> new BlockItem(ExNihiloBlocks.CRUSHED_END_STONE.get(), tab));

  @Nonnull
  public static final RegistryObject<BlockItem> CRUSHED_ANDESITE =
      ITEMS.register(
          ExNihiloConstants.Blocks.CRUSHED_ANDESITE,
          () -> new BlockItem(ExNihiloBlocks.CRUSHED_ANDESITE.get(), tab));

  @Nonnull
  public static final RegistryObject<BlockItem> CRUSHED_DIORITE =
      ITEMS.register(
          ExNihiloConstants.Blocks.CRUSHED_DIORITE,
          () -> new BlockItem(ExNihiloBlocks.CRUSHED_DIORITE.get(), tab));

  @Nonnull
  public static final RegistryObject<BlockItem> CRUSHED_GRANITE =
      ITEMS.register(
          ExNihiloConstants.Blocks.CRUSHED_GRANITE,
          () -> new BlockItem(ExNihiloBlocks.CRUSHED_GRANITE.get(), tab));

  @Nonnull
  public static final RegistryObject<BlockItem> END_CAKE =
      ITEMS.register(
          ExNihiloConstants.Blocks.END_CAKE,
          () -> new BlockItem(ExNihiloBlocks.END_CAKE.get(), tab));

  @Nonnull
  public static final RegistryObject<BlockItem> INFESTING_LEAVES =
      ITEMS.register(
          ExNihiloConstants.Blocks.INFESTING_LEAVES,
          () -> new BlockItem(ExNihiloBlocks.INFESTING_LEAVES.get(), tab));

  @Nonnull
  public static final RegistryObject<BlockItem> INFESTED_LEAVES =
      ITEMS.register(
          ExNihiloConstants.Blocks.INFESTED_LEAVES,
          () -> new BlockItem(ExNihiloBlocks.INFESTED_LEAVES.get(), tab));

  @Nonnull
  public static final RegistryObject<BlockItem> SIEVE_ACACIA =
      ITEMS.register(
          ExNihiloConstants.Blocks.ACACIA_SIEVE,
          () -> createBurnableItem(ExNihiloBlocks.SIEVE_ACACIA.get()));

  @Nonnull
  public static final RegistryObject<BlockItem> SIEVE_BIRCH =
      ITEMS.register(
          ExNihiloConstants.Blocks.BIRCH_SIEVE,
          () -> createBurnableItem(ExNihiloBlocks.SIEVE_BIRCH.get()));

  @Nonnull
  public static final RegistryObject<BlockItem> SIEVE_DARK_OAK =
      ITEMS.register(
          ExNihiloConstants.Blocks.DARK_OAK_SIEVE,
          () -> createBurnableItem(ExNihiloBlocks.SIEVE_DARK_OAK.get()));

  @Nonnull
  public static final RegistryObject<BlockItem> SIEVE_JUNGLE =
      ITEMS.register(
          ExNihiloConstants.Blocks.JUNGLE_SIEVE,
          () -> createBurnableItem(ExNihiloBlocks.SIEVE_JUNGLE.get()));

  @Nonnull
  public static final RegistryObject<BlockItem> SIEVE_OAK =
      ITEMS.register(
          ExNihiloConstants.Blocks.OAK_SIEVE,
          () -> createBurnableItem(ExNihiloBlocks.SIEVE_OAK.get()));

  @Nonnull
  public static final RegistryObject<BlockItem> SIEVE_SPRUCE =
      ITEMS.register(
          ExNihiloConstants.Blocks.SPRUCE_SIEVE,
          () -> createBurnableItem(ExNihiloBlocks.SIEVE_SPRUCE.get()));

  @Nonnull
  public static final RegistryObject<BlockItem> SIEVE_CRIMSON =
      ITEMS.register(
          ExNihiloConstants.Blocks.CRIMSON_SIEVE,
          () -> createBurnableItem(ExNihiloBlocks.SIEVE_CRIMSON.get()));

  @Nonnull
  public static final RegistryObject<BlockItem> SIEVE_WARPED =
      ITEMS.register(
          ExNihiloConstants.Blocks.WARPED_SIEVE,
          () -> createBurnableItem(ExNihiloBlocks.SIEVE_WARPED.get()));

  @Nonnull
  public static final RegistryObject<BlockItem> CRUCIBLE_UNFIRED =
      ITEMS.register(
          ExNihiloConstants.Blocks.UNFIRED_CRUCIBLE,
          () -> new BlockItem(ExNihiloBlocks.CRUCIBLE_UNFIRED.get(), tab));

  @Nonnull
  public static final RegistryObject<BlockItem> CRUCIBLE_FIRED =
      ITEMS.register(
          ExNihiloConstants.Blocks.FIRED_CRUCIBLE,
          () -> new BlockItem(ExNihiloBlocks.CRUCIBLE_FIRED.get(), tab));

  @Nonnull
  public static final RegistryObject<BlockItem> CRUCIBLE_ACACIA =
      ITEMS.register(
          ExNihiloConstants.Blocks.ACACIA_CRUCIBLE,
          () -> createBurnableItem(ExNihiloBlocks.CRUCIBLE_ACACIA.get()));

  @Nonnull
  public static final RegistryObject<BlockItem> CRUCIBLE_BIRCH =
      ITEMS.register(
          ExNihiloConstants.Blocks.BIRCH_CRUCIBLE,
          () -> createBurnableItem(ExNihiloBlocks.CRUCIBLE_BIRCH.get()));

  @Nonnull
  public static final RegistryObject<BlockItem> CRUCIBLE_DARK_OAK =
      ITEMS.register(
          ExNihiloConstants.Blocks.DARK_OAK_CRUCIBLE,
          () -> createBurnableItem(ExNihiloBlocks.CRUCIBLE_DARK_OAK.get()));

  @Nonnull
  public static final RegistryObject<BlockItem> CRUCIBLE_JUNGLE =
      ITEMS.register(
          ExNihiloConstants.Blocks.JUNGLE_CRUCIBLE,
          () -> createBurnableItem(ExNihiloBlocks.CRUCIBLE_JUNGLE.get()));

  @Nonnull
  public static final RegistryObject<BlockItem> CRUCIBLE_OAK =
      ITEMS.register(
          ExNihiloConstants.Blocks.OAK_CRUCIBLE,
          () -> createBurnableItem(ExNihiloBlocks.CRUCIBLE_OAK.get()));

  @Nonnull
  public static final RegistryObject<BlockItem> CRUCIBLE_SPRUCE =
      ITEMS.register(
          ExNihiloConstants.Blocks.SPRUCE_CRUCIBLE,
          () -> createBurnableItem(ExNihiloBlocks.CRUCIBLE_SPRUCE.get()));

  @Nonnull
  public static final RegistryObject<BlockItem> CRUCIBLE_CRIMSON =
      ITEMS.register(
          ExNihiloConstants.Blocks.CRIMSON_CRUCIBLE,
          () -> createBurnableItem(ExNihiloBlocks.CRUCIBLE_CRIMSON.get()));

  @Nonnull
  public static final RegistryObject<BlockItem> CRUCIBLE_WARPED =
      ITEMS.register(
          ExNihiloConstants.Blocks.WARPED_CRUCIBLE,
          () -> createBurnableItem(ExNihiloBlocks.CRUCIBLE_WARPED.get()));

  @Nonnull
  public static final RegistryObject<BlockItem> BARREL_ACACIA =
      ITEMS.register(
          ExNihiloConstants.Blocks.ACACIA_BARREL,
          () -> createBurnableItem(ExNihiloBlocks.BARREL_ACACIA.get()));

  @Nonnull
  public static final RegistryObject<BlockItem> BARREL_BIRCH =
      ITEMS.register(
          ExNihiloConstants.Blocks.BIRCH_BARREL,
          () -> createBurnableItem(ExNihiloBlocks.BARREL_BIRCH.get()));

  @Nonnull
  public static final RegistryObject<BlockItem> BARREL_DARK_OAK =
      ITEMS.register(
          ExNihiloConstants.Blocks.DARK_OAK_BARREL,
          () -> createBurnableItem(ExNihiloBlocks.BARREL_DARK_OAK.get()));

  @Nonnull
  public static final RegistryObject<BlockItem> BARREL_JUNGLE =
      ITEMS.register(
          ExNihiloConstants.Blocks.JUNGLE_BARREL,
          () -> createBurnableItem(ExNihiloBlocks.BARREL_JUNGLE.get()));

  @Nonnull
  public static final RegistryObject<BlockItem> BARREL_OAK =
      ITEMS.register(
          ExNihiloConstants.Blocks.OAK_BARREL,
          () -> createBurnableItem(ExNihiloBlocks.BARREL_OAK.get()));

  @Nonnull
  public static final RegistryObject<BlockItem> BARREL_SPRUCE =
      ITEMS.register(
          ExNihiloConstants.Blocks.SPRUCE_BARREL,
          () -> createBurnableItem(ExNihiloBlocks.BARREL_SPRUCE.get()));

  @Nonnull
  public static final RegistryObject<BlockItem> BARREL_CRIMSON =
      ITEMS.register(
          ExNihiloConstants.Blocks.CRIMSON_BARREL,
          () -> createBurnableItem(ExNihiloBlocks.BARREL_CRIMSON.get()));

  @Nonnull
  public static final RegistryObject<BlockItem> BARREL_WARPED =
      ITEMS.register(
          ExNihiloConstants.Blocks.WARPED_BARREL,
          () -> createBurnableItem(ExNihiloBlocks.BARREL_WARPED.get()));

  @Nonnull
  public static final RegistryObject<BlockItem> BARREL_STONE =
      ITEMS.register(
          ExNihiloConstants.Blocks.STONE_BARREL,
          () -> new BlockItem(ExNihiloBlocks.BARREL_STONE.get(), tab));

  @Nonnull
  public static final RegistryObject<BucketItem> WITCH_WATER_BUCKET =
      ITEMS.register(
          ExNihiloConstants.Items.WITCH_WATER_BUCKET,
          () -> new BucketItem(ExNihiloFluids.WITCH_WATER, tab.stacksTo(1)));

  @Nonnull
  public static final RegistryObject<BucketItem> SEA_WATER_BUCKET =
      ITEMS.register(
          ExNihiloConstants.Items.SEA_WATER_BUCKET,
          () -> new BucketItem(ExNihiloFluids.SEA_WATER, tab.stacksTo(1)));

  private ExNihiloItems() {}

  private static BlockItem createBurnableItem(@Nonnull final Block block) {
    return new BlockItem(block, tab) {
      @Override
      public int getBurnTime(
          @Nonnull final ItemStack itemStack, @Nullable final RecipeType<?> recipeType) {
        return 400;
      }
    };
  }

  public static void init(@Nonnull final IEventBus modEventBus) {
    logger.debug("Register items");
    ITEMS.register(modEventBus);
  }
}
