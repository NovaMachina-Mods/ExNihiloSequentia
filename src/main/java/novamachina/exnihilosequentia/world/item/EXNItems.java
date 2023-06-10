package novamachina.exnihilosequentia.world.item;

import com.mojang.logging.LogUtils;
import java.util.*;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.block.Blocks;
import novamachina.exnihilosequentia.common.item.CookedSilkwormItem;
import novamachina.exnihilosequentia.common.item.CrookItem;
import novamachina.exnihilosequentia.common.item.DollItem;
import novamachina.exnihilosequentia.common.item.HammerItem;
import novamachina.exnihilosequentia.common.item.MeshItem;
import novamachina.exnihilosequentia.common.item.PebbleItem;
import novamachina.exnihilosequentia.common.item.ResourceItem;
import novamachina.exnihilosequentia.common.item.SilkwormItem;
import novamachina.exnihilosequentia.common.item.mesh.MeshType;
import novamachina.exnihilosequentia.common.item.ore.Ore;
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import novamachina.exnihilosequentia.init.ExNihiloInitialization;
import novamachina.exnihilosequentia.world.level.material.EXNFluids;
import novamachina.novacore.registries.ItemRegistry;
import novamachina.novacore.world.item.ItemDefinition;

@SuppressWarnings("unused")
public class EXNItems {

  public static List<ItemDefinition<? extends Item>> getDefinitions() {
    return ITEMS.getRegistry();
  }

  public static final ItemRegistry ITEMS =
      new ItemRegistry(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA);
  // Begin Items Only
  public static final ItemDefinition<CookedSilkwormItem> COOKED_SILKWORM =
      ITEMS.item(
          "Cooked Silkworm",
          "cooked_silkworm",
          CookedSilkwormItem::new,
          ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<SilkwormItem> SILKWORM =
      ITEMS.item("Silkworm", "silkworm", SilkwormItem::new, ItemDefinition.ItemType.OTHER);
  public static final Ore IRON =
      createOre(
          ExNihiloConstants.Ore.IRON,
          Optional.of(net.minecraft.world.item.Items.RAW_IRON),
          Optional.of(net.minecraft.world.item.Items.IRON_INGOT),
          Optional.of(net.minecraft.world.item.Items.IRON_NUGGET));
  public static final Ore GOLD =
      createOre(
          ExNihiloConstants.Ore.GOLD,
          Optional.of(net.minecraft.world.item.Items.RAW_GOLD),
          Optional.of(net.minecraft.world.item.Items.GOLD_INGOT),
          Optional.of(net.minecraft.world.item.Items.GOLD_NUGGET));
  public static final Ore COPPER =
      createOre(
          ExNihiloConstants.Ore.COPPER,
          Optional.of(net.minecraft.world.item.Items.RAW_COPPER),
          Optional.of(net.minecraft.world.item.Items.COPPER_INGOT),
          Optional.empty());
  public static final Ore LEAD =
      createOre(ExNihiloConstants.Ore.LEAD, Optional.empty(), Optional.empty(), Optional.empty());
  public static final Ore NICKEL =
      createOre(ExNihiloConstants.Ore.NICKEL, Optional.empty(), Optional.empty(), Optional.empty());
  public static final Ore SILVER =
      createOre(ExNihiloConstants.Ore.SILVER, Optional.empty(), Optional.empty(), Optional.empty());
  public static final Ore TIN =
      createOre(ExNihiloConstants.Ore.TIN, Optional.empty(), Optional.empty(), Optional.empty());
  public static final Ore ALUMINUM =
      createOre(
          ExNihiloConstants.Ore.ALUMINUM, Optional.empty(), Optional.empty(), Optional.empty());
  public static final Ore PLATINUM =
      createOre(
          ExNihiloConstants.Ore.PLATINUM, Optional.empty(), Optional.empty(), Optional.empty());
  public static final Ore URANIUM =
      createOre(
          ExNihiloConstants.Ore.URANIUM, Optional.empty(), Optional.empty(), Optional.empty());
  public static final Ore ZINC =
      createOre(ExNihiloConstants.Ore.ZINC, Optional.empty(), Optional.empty(), Optional.empty());
  public static final ItemDefinition<DollItem> BLAZE_DOLL =
      ITEMS.item(
          "Blazing Doll",
          "blaze_doll",
          () ->
              new DollItem(
                  ExNihiloConstants.ModIds.MINECRAFT,
                  "blaze",
                  "minecraft",
                  "lava",
                  1,
                  ExNihiloConstants.Tooltips.BLAZE),
          ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<DollItem> ENDERMAN_DOLL =
      ITEMS.item(
          "Creeping Doll",
          "enderman_doll",
          () ->
              new DollItem(
                  ExNihiloConstants.ModIds.MINECRAFT,
                  "enderman",
                  ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA,
                  ExNihiloConstants.Fluids.WITCH_WATER,
                  2,
                  ExNihiloConstants.Tooltips.ENDERMAN),
          ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<DollItem> SHULKER_DOLL =
      ITEMS.item(
          "Floating Doll",
          "shulker_doll",
          () ->
              new DollItem(
                  ExNihiloConstants.ModIds.MINECRAFT,
                  "shulker",
                  ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA,
                  ExNihiloConstants.Fluids.WITCH_WATER,
                  1.5,
                  ExNihiloConstants.Tooltips.SHULKER),
          ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<DollItem> GUARDIAN_DOLL =
      ITEMS.item(
          "Protecting Doll",
          "guardian_doll",
          () ->
              new DollItem(
                  ExNihiloConstants.ModIds.MINECRAFT,
                  "guardian",
                  ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA,
                  ExNihiloConstants.Fluids.SEA_WATER,
                  1,
                  ExNihiloConstants.Tooltips.GUARDIAN),
          ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<DollItem> BEE_DOLL =
      ITEMS.item(
          "Buzzing Doll",
          "bee_doll",
          () ->
              new DollItem(
                  ExNihiloConstants.ModIds.MINECRAFT,
                  "bee",
                  ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA,
                  ExNihiloConstants.Fluids.WITCH_WATER,
                  1,
                  ExNihiloConstants.Tooltips.BEE),
          ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<PebbleItem> PEBBLE_ANDESITE =
      ITEMS.item(
          "Andesite Pebble", "andesite_pebble", PebbleItem::new, ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<PebbleItem> PEBBLE_BASALT =
      ITEMS.item("Basalt Pebble", "basalt_pebble", PebbleItem::new, ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<PebbleItem> PEBBLE_BLACKSTONE =
      ITEMS.item(
          "Blackstone Pebble", "blackstone_pebble", PebbleItem::new, ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<PebbleItem> PEBBLE_CALCITE =
      ITEMS.item(
          "Calcite Pebble", "calcite_pebble", PebbleItem::new, ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<PebbleItem> PEBBLE_DEEPSLATE =
      ITEMS.item(
          "Deepslate Pebble", "deepslate_pebble", PebbleItem::new, ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<PebbleItem> PEBBLE_DIORITE =
      ITEMS.item(
          "Diorite Pebble", "diorite_pebble", PebbleItem::new, ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<PebbleItem> PEBBLE_DRIPSTONE =
      ITEMS.item(
          "Dripstone Pebble", "dripstone_pebble", PebbleItem::new, ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<PebbleItem> PEBBLE_END_STONE =
      ITEMS.item(
          "End Stone Pebble", "end_stone_pebble", PebbleItem::new, ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<PebbleItem> PEBBLE_GRANITE =
      ITEMS.item(
          "Granite Pebble", "granite_pebble", PebbleItem::new, ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<PebbleItem> PEBBLE_NETHERRACK =
      ITEMS.item(
          "Netherrack Pebble", "netherrack_pebble", PebbleItem::new, ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<PebbleItem> PEBBLE_STONE =
      ITEMS.item("Stone Pebble", "stone_pebble", PebbleItem::new, ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<PebbleItem> PEBBLE_TUFF =
      ITEMS.item("Tuff Pebble", "tuff_pebble", PebbleItem::new, ItemDefinition.ItemType.OTHER);
  // TODO: Rename ResourceItem class
  public static final ItemDefinition<ResourceItem> MYCELIUM_SPORE =
      ITEMS.item(
          "Mycelium Spores",
          "mycelium_spore",
          () ->
              new ResourceItem(
                  ExNihiloConstants.Items.MYCELIUM_SPORE, Blocks.DIRT, Blocks.MYCELIUM),
          ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<ResourceItem> CRIMSON_NYLIUM_SPORE =
      ITEMS.item(
          "Crimson Nylium Spores",
          "crimson_nylium_spore",
          () ->
              new ResourceItem(
                  ExNihiloConstants.Items.CRIMSON_NYLIUM_SPORE,
                  Blocks.NETHERRACK,
                  Blocks.CRIMSON_NYLIUM),
          ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<ResourceItem> WARPED_NYLIUM_SPORE =
      ITEMS.item(
          "Warped Nylium Spores",
          "warped_nylium_spore",
          () ->
              new ResourceItem(
                  ExNihiloConstants.Items.WARPED_NYLIUM_SPORE,
                  Blocks.NETHERRACK,
                  Blocks.WARPED_NYLIUM),
          ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<ResourceItem> GRASS_SEED =
      ITEMS.item(
          "Grass Seeds",
          "grass_seeds",
          () ->
              new ResourceItem(ExNihiloConstants.Items.GRASS_SEED, Blocks.DIRT, Blocks.GRASS_BLOCK),
          ItemDefinition.ItemType.OTHER);
  // TODO: Replace below ResourceItem with a generic base item
  public static final ItemDefinition<ResourceItem> PORCELAIN_CLAY =
      ITEMS.item(
          "Porcelain Clay",
          "porcelain_clay",
          () -> new ResourceItem(ExNihiloConstants.Items.PORCELAIN_CLAY),
          ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<ResourceItem> CRAFTING_DOLL =
      ITEMS.item(
          "Porcelain Doll",
          "porcelain_doll",
          () -> new ResourceItem(ExNihiloConstants.Items.CRAFTING_DOLL),
          ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<ResourceItem> TUBE_CORAL_LARVA =
      ITEMS.item(
          "Tube Coral Larva",
          "tube_coral_larva",
          () -> new ResourceItem(ExNihiloConstants.Items.TUBE_CORAL_LARVA),
          ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<ResourceItem> BRAIN_CORAL_LARVA =
      ITEMS.item(
          "Brain Coral Larva",
          "brain_coral_larva",
          () -> new ResourceItem(ExNihiloConstants.Items.BRAIN_CORAL_LARVA),
          ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<ResourceItem> BUBBLE_CORAL_LARVA =
      ITEMS.item(
          "Bubble Coral Larva",
          "bubble_coral_larva",
          () -> new ResourceItem(ExNihiloConstants.Items.BUBBLE_CORAL_LARVA),
          ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<ResourceItem> FIRE_CORAL_LARVA =
      ITEMS.item(
          "Fire Coral Larva",
          "fire_coral_larva",
          () -> new ResourceItem(ExNihiloConstants.Items.FIRE_CORAL_LARVA),
          ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<ResourceItem> HORN_CORAL_LARVA =
      ITEMS.item(
          "Horn Coral Larva",
          "horn_coral_larva",
          () -> new ResourceItem(ExNihiloConstants.Items.HORN_CORAL_LARVA),
          ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<ResourceItem> BEEHIVE_FRAME =
      ITEMS.item(
          "Beehive Frame",
          "beehive_frame",
          () -> new ResourceItem(ExNihiloConstants.Items.BEEHIVE_FRAME),
          ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<MeshItem> MESH_STRING =
      ITEMS.item(
          "String Mesh",
          "string_mesh",
          () ->
              new MeshItem(
                  ExNihiloConstants.Items.STRING_MESH,
                  Config.getMeshStringValue(),
                  MeshType.STRING),
          ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<MeshItem> MESH_FLINT =
      ITEMS.item(
          "Flint Mesh",
          "flint_mesh",
          () ->
              new MeshItem(
                  ExNihiloConstants.Items.FLINT_MESH, Config.getMeshFlintValue(), MeshType.FLINT),
          ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<MeshItem> MESH_IRON =
      ITEMS.item(
          "Iron Mesh",
          "iron_mesh",
          () ->
              new MeshItem(
                  ExNihiloConstants.Items.IRON_MESH, Config.getMeshIronValue(), MeshType.IRON),
          ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<MeshItem> MESH_DIAMOND =
      ITEMS.item(
          "Diamond Mesh",
          "diamond_mesh",
          () ->
              new MeshItem(
                  ExNihiloConstants.Items.DIAMOND_MESH,
                  Config.getMeshDiamondValue(),
                  MeshType.DIAMOND),
          ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<MeshItem> MESH_EMERALD =
      ITEMS.item(
          "Emerald Mesh",
          "emerald_mesh",
          () ->
              new MeshItem(
                  ExNihiloConstants.Items.EMERALD_MESH,
                  Config.getMeshEmeraldValue(),
                  MeshType.EMERALD),
          ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<MeshItem> MESH_NETHERITE =
      ITEMS.item(
          "Netherite Mesh",
          "netherite_mesh",
          () ->
              new MeshItem(
                  ExNihiloConstants.Items.NETHERITE_MESH,
                  Config.getMeshNetheriteValue(),
                  MeshType.NETHERITE),
          ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<CrookItem> CROOK_ANDESITE =
      ITEMS.item(
          "Andesite Crook",
          "andesite_crook",
          () -> new CrookItem(Tiers.STONE, Config.getCrookAndesiteDurability()),
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<CrookItem> CROOK_BASALT =
      ITEMS.item(
          "Basalt Crook",
          "basalt_crook",
          () -> new CrookItem(Tiers.STONE, Config.getCrookAndesiteDurability()),
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<CrookItem> CROOK_BLACKSTONE =
      ITEMS.item(
          "Blackstone Crook",
          "blackstone_crook",
          () -> new CrookItem(Tiers.STONE, Config.getCrookAndesiteDurability()),
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<CrookItem> CROOK_BONE =
      ITEMS.item(
          "Bone Crook",
          "bone_crook",
          () -> new CrookItem(Tiers.STONE, Config.getCrookBoneDurability()),
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<CrookItem> CROOK_CALCITE =
      ITEMS.item(
          "Calcite Crook",
          "calcite_crook",
          () -> new CrookItem(Tiers.STONE, Config.getCrookAndesiteDurability()),
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<CrookItem> CROOK_COPPER =
      ITEMS.item(
          "Copper Crook",
          "copper_crook",
          () -> new CrookItem(Tiers.IRON, Config.getCrookIronDurability()),
          ItemDefinition.ItemType.TOOL);
  //  public static final ItemDefinition<CrookItem> CROOK_CRIMSON_FUNGUS =
  //      ITEMS.item(
  //          "Crimson Fungus Crook",
  //          "crimson_fungus_crook",
  //          () -> new CrookItem(Tiers.STONE, Config.getCrookWoodDurability()),
  // ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<CrookItem> CROOK_DEEPSLATE =
      ITEMS.item(
          "Deepslate Crook",
          "deepslate_crook",
          () -> new CrookItem(Tiers.STONE, Config.getCrookStoneDurability()),
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<CrookItem> CROOK_DIAMOND =
      ITEMS.item(
          "Diamond Crook",
          "diamond_crook",
          () -> new CrookItem(Tiers.DIAMOND, Config.getCrookDiamondDurability()),
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<CrookItem> CROOK_DIORITE =
      ITEMS.item(
          "Diorite Crook",
          "diorite_crook",
          () -> new CrookItem(Tiers.STONE, Config.getCrookDioriteDurability()),
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<CrookItem> CROOK_DRIPSTONE =
      ITEMS.item(
          "Dripstone Crook",
          "dripstone_crook",
          () -> new CrookItem(Tiers.STONE, Config.getCrookDioriteDurability()),
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<CrookItem> CROOK_GOLD =
      ITEMS.item(
          "Gold Crook",
          "gold_crook",
          () -> new CrookItem(Tiers.IRON, Config.getCrookGoldDurability()),
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<CrookItem> CROOK_GRANITE =
      ITEMS.item(
          "Granite Crook",
          "granite_crook",
          () -> new CrookItem(Tiers.STONE, Config.getCrookGraniteDurability()),
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<CrookItem> CROOK_IRON =
      ITEMS.item(
          "Iron Crook",
          "iron_crook",
          () -> new CrookItem(Tiers.IRON, Config.getCrookIronDurability()),
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<CrookItem> CROOK_NETHER_BRICK =
      ITEMS.item(
          "Nether Brick Crook",
          "nether_brick_crook",
          () -> new CrookItem(Tiers.STONE, Config.getCrookStoneDurability()),
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<CrookItem> CROOK_NETHERITE =
      ITEMS.item(
          "Netherite Crook",
          "netherite_crook",
          () -> new CrookItem(Tiers.NETHERITE, Config.getCrookNetheriteDurability()),
          ItemDefinition.ItemType.TOOL);
  //    public static final ItemDefinition<CrookItem> CROOK_PRISMARINE =
  //        ITEMS.item(
  //            "Prismarine Crook",
  //            "prismarine_crook",
  //            () -> new CrookItem(Tiers.STONE,
  //   Config.getCrookStoneDurability()), ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<CrookItem> CROOK_RED_NETHER_BRICK =
      ITEMS.item(
          "Red Nether Brick Crook",
          "red_nether_brick_crook",
          () -> new CrookItem(Tiers.STONE, Config.getCrookStoneDurability()),
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<CrookItem> CROOK_STONE =
      ITEMS.item(
          "Stone Crook",
          "stone_crook",
          () -> new CrookItem(Tiers.STONE, Config.getCrookStoneDurability()),
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<CrookItem> CROOK_TERRACOTTA =
      ITEMS.item(
          "Terracotta Crook",
          "terracotta_crook",
          () -> new CrookItem(Tiers.STONE, Config.getCrookStoneDurability()),
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<CrookItem> CROOK_TUFF =
      ITEMS.item(
          "Tuff Crook",
          "tuff_crook",
          () -> new CrookItem(Tiers.STONE, Config.getCrookStoneDurability()),
          ItemDefinition.ItemType.TOOL);
  //  public static final ItemDefinition<CrookItem> CROOK_WARPED_FUNGUS =
  //      ITEMS.item(
  //          "Warped Fungus Crook",
  //          "warped_fungus_crook",
  //          () -> new CrookItem(Tiers.STONE, Config.getCrookStoneDurability()),
  // ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<CrookItem> CROOK_WOOD =
      ITEMS.item(
          "Wooden Crook",
          "wooden_crook",
          () -> new CrookItem(Tiers.WOOD, Config.getCrookWoodDurability()),
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<HammerItem> HAMMER_ANDESITE =
      ITEMS.item(
          "Andesite Hammer",
          "andesite_hammer",
          () -> new HammerItem(Tiers.STONE, Config.getCrookAndesiteDurability()),
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<HammerItem> HAMMER_BASALT =
      ITEMS.item(
          "Basalt Hammer",
          "basalt_hammer",
          () -> new HammerItem(Tiers.STONE, Config.getCrookAndesiteDurability()),
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<HammerItem> HAMMER_BLACKSTONE =
      ITEMS.item(
          "Blackstone Hammer",
          "blackstone_hammer",
          () -> new HammerItem(Tiers.STONE, Config.getCrookAndesiteDurability()),
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<HammerItem> HAMMER_BONE =
      ITEMS.item(
          "Bone Hammer",
          "bone_hammer",
          () -> new HammerItem(Tiers.STONE, Config.getCrookBoneDurability()),
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<HammerItem> HAMMER_CALCITE =
      ITEMS.item(
          "Calcite Hammer",
          "calcite_hammer",
          () -> new HammerItem(Tiers.STONE, Config.getCrookAndesiteDurability()),
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<HammerItem> HAMMER_COPPER =
      ITEMS.item(
          "Copper Hammer",
          "copper_hammer",
          () -> new HammerItem(Tiers.IRON, Config.getCrookIronDurability()),
          ItemDefinition.ItemType.TOOL);
  //  public static final ItemDefinition<HammerItem> HAMMER_CRIMSON_FUNGUS =
  //      ITEMS.item(
  //          "Crimson Fungus Hammer",
  //          "crimson_fungus_hammer",
  //          () -> new HammerItem(Tiers.STONE, Config.getCrookWoodDurability()),
  // ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<HammerItem> HAMMER_DEEPSLATE =
      ITEMS.item(
          "Deepslate Hammer",
          "deepslate_hammer",
          () -> new HammerItem(Tiers.STONE, Config.getCrookStoneDurability()),
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<HammerItem> HAMMER_DIAMOND =
      ITEMS.item(
          "Diamond Hammer",
          "diamond_hammer",
          () -> new HammerItem(Tiers.DIAMOND, Config.getCrookDiamondDurability()),
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<HammerItem> HAMMER_DIORITE =
      ITEMS.item(
          "Diorite Hammer",
          "diorite_hammer",
          () -> new HammerItem(Tiers.STONE, Config.getCrookDioriteDurability()),
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<HammerItem> HAMMER_DRIPSTONE =
      ITEMS.item(
          "Dripstone Hammer",
          "dripstone_hammer",
          () -> new HammerItem(Tiers.STONE, Config.getCrookDioriteDurability()),
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<HammerItem> HAMMER_GOLD =
      ITEMS.item(
          "Gold Hammer",
          "gold_hammer",
          () -> new HammerItem(Tiers.IRON, Config.getCrookGoldDurability()),
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<HammerItem> HAMMER_GRANITE =
      ITEMS.item(
          "Granite Hammer",
          "granite_hammer",
          () -> new HammerItem(Tiers.STONE, Config.getCrookGraniteDurability()),
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<HammerItem> HAMMER_IRON =
      ITEMS.item(
          "Iron Hammer",
          "iron_hammer",
          () -> new HammerItem(Tiers.IRON, Config.getCrookIronDurability()),
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<HammerItem> HAMMER_NETHER_BRICK =
      ITEMS.item(
          "Nether Brick Hammer",
          "nether_brick_hammer",
          () -> new HammerItem(Tiers.STONE, Config.getCrookStoneDurability()),
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<HammerItem> HAMMER_NETHERITE =
      ITEMS.item(
          "Netherite Hammer",
          "netherite_hammer",
          () -> new HammerItem(Tiers.NETHERITE, Config.getCrookNetheriteDurability()),
          ItemDefinition.ItemType.TOOL);
  //    public static final ItemDefinition<HammerItem> HAMMER_PRISMARINE =
  //        ITEMS.item(
  //            "Prismarine Hammer",
  //            "prismarine_hammer",
  //            () -> new HammerItem(Tiers.STONE,
  //   Config.getCrookStoneDurability()), ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<HammerItem> HAMMER_RED_NETHER_BRICK =
      ITEMS.item(
          "Red Nether Brick Hammer",
          "red_nether_brick_hammer",
          () -> new HammerItem(Tiers.STONE, Config.getCrookStoneDurability()),
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<HammerItem> HAMMER_STONE =
      ITEMS.item(
          "Stone Hammer",
          "stone_hammer",
          () -> new HammerItem(Tiers.STONE, Config.getCrookStoneDurability()),
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<HammerItem> HAMMER_TERRACOTTA =
      ITEMS.item(
          "Terracotta Hammer",
          "terracotta_hammer",
          () -> new HammerItem(Tiers.STONE, Config.getCrookStoneDurability()),
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<HammerItem> HAMMER_TUFF =
      ITEMS.item(
          "Tuff Hammer",
          "tuff_hammer",
          () -> new HammerItem(Tiers.STONE, Config.getCrookStoneDurability()),
          ItemDefinition.ItemType.TOOL);
  //  public static final ItemDefinition<HammerItem> HAMMER_WARPED_FUNGUS =
  //      ITEMS.item(
  //          "Warped Fungus Hammer",
  //          "warped_fungus_hammer",
  //          () -> new HammerItem(Tiers.STONE, Config.getCrookStoneDurability()),
  // ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<HammerItem> HAMMER_WOOD =
      ITEMS.item(
          "Wooden Hammer",
          "wooden_hammer",
          () -> new HammerItem(Tiers.WOOD, Config.getCrookWoodDurability()),
          ItemDefinition.ItemType.TOOL);
  private static final ExNihiloLogger logger = new ExNihiloLogger(LogUtils.getLogger());
  // Begin Block Items
  private static final Item.Properties tab =
      new Item.Properties().tab(ExNihiloInitialization.ITEM_GROUP);
  public static final ItemDefinition<BucketItem> WITCH_WATER_BUCKET =
      ITEMS.item(
          "Witch Water Bucket",
          "witch_water_bucket",
          () -> new BucketItem(EXNFluids.WITCH_WATER.getStillFluid(), tab.stacksTo(1)),
          ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<BucketItem> SEA_WATER_BUCKET =
      ITEMS.item(
          "Sea Water Bucket",
          "sea_water_bucket",
          () -> new BucketItem(EXNFluids.SEA_WATER.getStillFluid(), tab.stacksTo(1)),
          ItemDefinition.ItemType.OTHER);

  private EXNItems() {}

  private static Ore createOre(
      String id, Optional<Item> rawItem, Optional<Item> ingotItem, Optional<Item> nuggetItem) {
    return new Ore(id, true, rawItem, ingotItem, nuggetItem, ITEMS);
  }
}
