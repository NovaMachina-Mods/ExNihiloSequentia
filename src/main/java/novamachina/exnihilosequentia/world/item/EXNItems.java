package novamachina.exnihilosequentia.world.item;

import java.util.*;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.level.block.Blocks;
import novamachina.exnihilosequentia.common.Config;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.core.registries.ExNihiloItemRegistry;
import novamachina.exnihilosequentia.world.level.material.EXNFluids;
import novamachina.novacore.world.item.ItemDefinition;

@SuppressWarnings("unused")
public class EXNItems {

  public static List<ItemDefinition<? extends Item>> getDefinitions() {
    return ITEMS.getRegistry();
  }

  public static final ExNihiloItemRegistry ITEMS =
      new ExNihiloItemRegistry(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA);
  // Begin Items Only
  public static final ItemDefinition<CookedSilkwormItem> COOKED_SILKWORM =
      ITEMS.item(
          "Cooked Silkworm",
          "cooked_silkworm",
          new Item.Properties()
              .food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.6F).build()),
          CookedSilkwormItem::new,
          ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<SilkwormItem> SILKWORM =
      ITEMS.item(
          "Silkworm",
          "silkworm",
          new Item.Properties(),
          SilkwormItem::new,
          ItemDefinition.ItemType.OTHER);
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
      ITEMS.dollItem(
          "Blazing Doll",
          "blaze_doll",
          ExNihiloConstants.ModIds.MINECRAFT,
          "blaze",
          "minecraft",
          "lava",
          1,
          ExNihiloConstants.Tooltips.BLAZE,
          new Item.Properties(),
          DollItem::new,
          ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<DollItem> ENDERMAN_DOLL =
      ITEMS.dollItem(
          "Creeping Doll",
          "enderman_doll",
          ExNihiloConstants.ModIds.MINECRAFT,
          "enderman",
          ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA,
          ExNihiloConstants.Fluids.WITCH_WATER,
          2,
          ExNihiloConstants.Tooltips.ENDERMAN,
          new Item.Properties(),
          DollItem::new,
          ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<DollItem> SHULKER_DOLL =
      ITEMS.dollItem(
          "Floating Doll",
          "shulker_doll",
          ExNihiloConstants.ModIds.MINECRAFT,
          "shulker",
          ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA,
          ExNihiloConstants.Fluids.WITCH_WATER,
          1.5,
          ExNihiloConstants.Tooltips.SHULKER,
          new Item.Properties(),
          DollItem::new,
          ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<DollItem> GUARDIAN_DOLL =
      ITEMS.dollItem(
          "Protecting Doll",
          "guardian_doll",
          ExNihiloConstants.ModIds.MINECRAFT,
          "guardian",
          ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA,
          ExNihiloConstants.Fluids.SEA_WATER,
          1,
          ExNihiloConstants.Tooltips.GUARDIAN,
          new Item.Properties(),
          DollItem::new,
          ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<DollItem> BEE_DOLL =
      ITEMS.dollItem(
          "Buzzing Doll",
          "bee_doll",
          ExNihiloConstants.ModIds.MINECRAFT,
          "bee",
          ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA,
          ExNihiloConstants.Fluids.WITCH_WATER,
          1,
          ExNihiloConstants.Tooltips.BEE,
          new Item.Properties(),
          DollItem::new,
          ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<PebbleItem> PEBBLE_ANDESITE =
      ITEMS.item(
          "Andesite Pebble",
          "andesite_pebble",
          new Item.Properties(),
          PebbleItem::new,
          ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<PebbleItem> PEBBLE_BASALT =
      ITEMS.item(
          "Basalt Pebble",
          "basalt_pebble",
          new Item.Properties(),
          PebbleItem::new,
          ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<PebbleItem> PEBBLE_BLACKSTONE =
      ITEMS.item(
          "Blackstone Pebble",
          "blackstone_pebble",
          new Item.Properties(),
          PebbleItem::new,
          ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<PebbleItem> PEBBLE_CALCITE =
      ITEMS.item(
          "Calcite Pebble",
          "calcite_pebble",
          new Item.Properties(),
          PebbleItem::new,
          ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<PebbleItem> PEBBLE_DEEPSLATE =
      ITEMS.item(
          "Deepslate Pebble",
          "deepslate_pebble",
          new Item.Properties(),
          PebbleItem::new,
          ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<PebbleItem> PEBBLE_DIORITE =
      ITEMS.item(
          "Diorite Pebble",
          "diorite_pebble",
          new Item.Properties(),
          PebbleItem::new,
          ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<PebbleItem> PEBBLE_DRIPSTONE =
      ITEMS.item(
          "Dripstone Pebble",
          "dripstone_pebble",
          new Item.Properties(),
          PebbleItem::new,
          ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<PebbleItem> PEBBLE_END_STONE =
      ITEMS.item(
          "End Stone Pebble",
          "end_stone_pebble",
          new Item.Properties(),
          PebbleItem::new,
          ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<PebbleItem> PEBBLE_GRANITE =
      ITEMS.item(
          "Granite Pebble",
          "granite_pebble",
          new Item.Properties(),
          PebbleItem::new,
          ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<PebbleItem> PEBBLE_NETHERRACK =
      ITEMS.item(
          "Netherrack Pebble",
          "netherrack_pebble",
          new Item.Properties(),
          PebbleItem::new,
          ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<PebbleItem> PEBBLE_STONE =
      ITEMS.item(
          "Stone Pebble",
          "stone_pebble",
          new Item.Properties(),
          PebbleItem::new,
          ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<PebbleItem> PEBBLE_TUFF =
      ITEMS.item(
          "Tuff Pebble",
          "tuff_pebble",
          new Item.Properties(),
          PebbleItem::new,
          ItemDefinition.ItemType.OTHER);
  // TODO: Rename ResourceItem class
  public static final ItemDefinition<ResourceItem> MYCELIUM_SPORE =
      ITEMS.resourceItem(
          "Mycelium Spores",
          "mycelium_spores",
          Blocks.DIRT,
          Blocks.MYCELIUM,
          new Item.Properties(),
          ResourceItem::new,
          ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<ResourceItem> CRIMSON_NYLIUM_SPORE =
      ITEMS.resourceItem(
          "Crimson Nylium Spores",
          "crimson_nylium_spores",
          Blocks.NETHERRACK,
          Blocks.CRIMSON_NYLIUM,
          new Item.Properties(),
          ResourceItem::new,
          ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<ResourceItem> WARPED_NYLIUM_SPORE =
      ITEMS.resourceItem(
          "Warped Nylium Spores",
          "warped_nylium_spores",
          Blocks.NETHERRACK,
          Blocks.WARPED_NYLIUM,
          new Item.Properties(),
          ResourceItem::new,
          ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<ResourceItem> GRASS_SEED =
      ITEMS.resourceItem(
          "Grass Seeds",
          "grass_seeds",
          Blocks.DIRT,
          Blocks.GRASS_BLOCK,
          new Item.Properties(),
          ResourceItem::new,
          ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<Item> PORCELAIN_CLAY =
      ITEMS.item(
          "Porcelain Clay",
          "porcelain_clay",
          new Item.Properties(),
          Item::new,
          ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<Item> CRAFTING_DOLL =
      ITEMS.item(
          "Porcelain Doll",
          "porcelain_doll",
          new Item.Properties(),
          Item::new,
          ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<Item> TUBE_CORAL_LARVA =
      ITEMS.item(
          "Tube Coral Larva",
          "tube_coral_larva",
          new Item.Properties(),
          Item::new,
          ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<Item> BRAIN_CORAL_LARVA =
      ITEMS.item(
          "Brain Coral Larva",
          "brain_coral_larva",
          new Item.Properties(),
          Item::new,
          ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<Item> BUBBLE_CORAL_LARVA =
      ITEMS.item(
          "Bubble Coral Larva",
          "bubble_coral_larva",
          new Item.Properties(),
          Item::new,
          ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<Item> FIRE_CORAL_LARVA =
      ITEMS.item(
          "Fire Coral Larva",
          "fire_coral_larva",
          new Item.Properties(),
          Item::new,
          ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<Item> HORN_CORAL_LARVA =
      ITEMS.item(
          "Horn Coral Larva",
          "horn_coral_larva",
          new Item.Properties(),
          Item::new,
          ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<Item> BEEHIVE_FRAME =
      ITEMS.item(
          "Beehive Frame",
          "beehive_frame",
          new Item.Properties(),
          Item::new,
          ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<MeshItem> MESH_STRING =
      ITEMS.meshItem(
          "String Mesh",
          "string_mesh",
          MeshType.STRING,
          Config.enableMeshDurability()
              ? new Item.Properties().durability(Config.getMeshStringValue())
              : new Item.Properties().stacksTo(Config.getMeshStackSize()),
          MeshItem::new,
          ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<MeshItem> MESH_FLINT =
      ITEMS.meshItem(
          "Flint Mesh",
          "flint_mesh",
          MeshType.FLINT,
          Config.enableMeshDurability()
              ? new Item.Properties().durability(Config.getMeshFlintValue())
              : new Item.Properties().stacksTo(Config.getMeshStackSize()),
          MeshItem::new,
          ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<MeshItem> MESH_IRON =
      ITEMS.meshItem(
          "Iron Mesh",
          "iron_mesh",
          MeshType.IRON,
          Config.enableMeshDurability()
              ? new Item.Properties().durability(Config.getMeshIronValue())
              : new Item.Properties().stacksTo(Config.getMeshStackSize()),
          MeshItem::new,
          ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<MeshItem> MESH_DIAMOND =
      ITEMS.meshItem(
          "Diamond Mesh",
          "diamond_mesh",
          MeshType.DIAMOND,
          Config.enableMeshDurability()
              ? new Item.Properties().durability(Config.getMeshDiamondValue())
              : new Item.Properties().stacksTo(Config.getMeshStackSize()),
          MeshItem::new,
          ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<MeshItem> MESH_EMERALD =
      ITEMS.meshItem(
          "Emerald Mesh",
          "emerald_mesh",
          MeshType.EMERALD,
          Config.enableMeshDurability()
              ? new Item.Properties().durability(Config.getMeshEmeraldValue())
              : new Item.Properties().stacksTo(Config.getMeshStackSize()),
          MeshItem::new,
          ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<MeshItem> MESH_NETHERITE =
      ITEMS.meshItem(
          "Netherite Mesh",
          "netherite_mesh",
          MeshType.NETHERITE,
          Config.enableMeshDurability()
              ? new Item.Properties().durability(Config.getMeshNetheriteValue())
              : new Item.Properties().stacksTo(Config.getMeshStackSize()),
          MeshItem::new,
          ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<CrookItem> CROOK_ANDESITE =
      ITEMS.crookItem(
          "Andesite Crook",
          "andesite_crook",
          ToolMaterial.STONE,
          -1.0F,
          -2.0F,
          new Item.Properties(),
          CrookItem::new,
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<CrookItem> CROOK_BAMBOO =
      ITEMS.crookItem(
          "Bamboo Crook",
          "bamboo_crook",
          ToolMaterial.STONE,
          -1.0F,
          -2.0F,
          new Item.Properties(),
          CrookItem::new,
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<CrookItem> CROOK_BASALT =
      ITEMS.crookItem(
          "Basalt Crook",
          "basalt_crook",
          ToolMaterial.STONE,
          -1.0F,
          -2.0F,
          new Item.Properties(),
          CrookItem::new,
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<CrookItem> CROOK_BLACKSTONE =
      ITEMS.crookItem(
          "Blackstone Crook",
          "blackstone_crook",
          ToolMaterial.STONE,
          -1.0F,
          -2.0F,
          new Item.Properties(),
          CrookItem::new,
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<CrookItem> CROOK_BONE =
      ITEMS.crookItem(
          "Bone Crook",
          "bone_crook",
          ToolMaterial.STONE,
          -1.0F,
          -2.0F,
          new Item.Properties(),
          CrookItem::new,
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<CrookItem> CROOK_CALCITE =
      ITEMS.crookItem(
          "Calcite Crook",
          "calcite_crook",
          ToolMaterial.STONE,
          -1.0F,
          -2.0F,
          new Item.Properties(),
          CrookItem::new,
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<CrookItem> CROOK_CHERRY =
      ITEMS.crookItem(
          "Cherry Crook",
          "cherry_crook",
          ToolMaterial.WOOD,
          0.0F,
          -3.0F,
          new Item.Properties(),
          CrookItem::new,
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<CrookItem> CROOK_COPPER =
      ITEMS.crookItem(
          "Copper Crook",
          "copper_crook",
          ToolMaterial.IRON,
          -2.0F,
          -1.0F,
          new Item.Properties(),
          CrookItem::new,
          ItemDefinition.ItemType.TOOL);
  //  public static final ItemDefinition<CrookItem> CROOK_CRIMSON_FUNGUS =
  //      ITEMS.item(
  //          "Crimson Fungus Crook",
  //          "crimson_fungus_crook",
  //          () -> new CrookItem(Tiers.STONE, Config.getCrookWoodDurability()),
  // ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<CrookItem> CROOK_DEEPSLATE =
      ITEMS.crookItem(
          "Deepslate Crook",
          "deepslate_crook",
          ToolMaterial.STONE,
          -1.0F,
          -2.0F,
          new Item.Properties(),
          CrookItem::new,
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<CrookItem> CROOK_DIAMOND =
      ITEMS.crookItem(
          "Diamond Crook",
          "diamond_crook",
          ToolMaterial.DIAMOND,
          -3.0F,
          0.0F,
          new Item.Properties(),
          CrookItem::new,
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<CrookItem> CROOK_DIORITE =
      ITEMS.crookItem(
          "Diorite Crook",
          "diorite_crook",
          ToolMaterial.STONE,
          -1.0F,
          -2.0F,
          new Item.Properties(),
          CrookItem::new,
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<CrookItem> CROOK_DRIPSTONE =
      ITEMS.crookItem(
          "Dripstone Crook",
          "dripstone_crook",
          ToolMaterial.STONE,
          -1.0F,
          -2.0F,
          new Item.Properties(),
          CrookItem::new,
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<CrookItem> CROOK_GOLD =
      ITEMS.crookItem(
          "Golden Crook",
          "golden_crook",
          ToolMaterial.GOLD,
          0.0F,
          -3.0F,
          new Item.Properties(),
          CrookItem::new,
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<CrookItem> CROOK_GRANITE =
      ITEMS.crookItem(
          "Granite Crook",
          "granite_crook",
          ToolMaterial.STONE,
          -1.0F,
          -2.0F,
          new Item.Properties(),
          CrookItem::new,
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<CrookItem> CROOK_IRON =
      ITEMS.crookItem(
          "Iron Crook",
          "iron_crook",
          ToolMaterial.IRON,
          -2.0F,
          -1.0F,
          new Item.Properties(),
          CrookItem::new,
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<CrookItem> CROOK_NETHER_BRICK =
      ITEMS.crookItem(
          "Nether Brick Crook",
          "nether_brick_crook",
          ToolMaterial.STONE,
          -1.0F,
          -2.0F,
          new Item.Properties(),
          CrookItem::new,
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<CrookItem> CROOK_NETHERITE =
      ITEMS.crookItem(
          "Netherite Crook",
          "netherite_crook",
          ToolMaterial.NETHERITE,
          -4.0F,
          0.0F,
          new Item.Properties(),
          CrookItem::new,
          ItemDefinition.ItemType.TOOL);
  //    public static final ItemDefinition<CrookItem> CROOK_PRISMARINE =
  //        ITEMS.item(
  //            "Prismarine Crook",
  //            "prismarine_crook",
  //            () -> new CrookItem(Tiers.STONE,
  //   Config.getCrookStoneDurability()), ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<CrookItem> CROOK_RED_NETHER_BRICK =
      ITEMS.crookItem(
          "Red Nether Brick Crook",
          "red_nether_brick_crook",
          ToolMaterial.STONE,
          -1.0F,
          -2.0F,
          new Item.Properties(),
          CrookItem::new,
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<CrookItem> CROOK_STONE =
      ITEMS.crookItem(
          "Stone Crook",
          "stone_crook",
          ToolMaterial.STONE,
          -1.0F,
          -2.0F,
          new Item.Properties(),
          CrookItem::new,
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<CrookItem> CROOK_TERRACOTTA =
      ITEMS.crookItem(
          "Terracotta Crook",
          "terracotta_crook",
          ToolMaterial.STONE,
          -1.0F,
          -2.0F,
          new Item.Properties(),
          CrookItem::new,
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<CrookItem> CROOK_TUFF =
      ITEMS.crookItem(
          "Tuff Crook",
          "tuff_crook",
          ToolMaterial.STONE,
          -1.0F,
          -2.0F,
          new Item.Properties(),
          CrookItem::new,
          ItemDefinition.ItemType.TOOL);
  //  public static final ItemDefinition<CrookItem> CROOK_WARPED_FUNGUS =
  //      ITEMS.item(
  //          "Warped Fungus Crook",
  //          "warped_fungus_crook",
  //          () -> new CrookItem(Tiers.STONE, Config.getCrookStoneDurability()),
  // ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<CrookItem> CROOK_WOOD =
      ITEMS.crookItem(
          "Wooden Crook",
          "wooden_crook",
          ToolMaterial.WOOD,
          0.0F,
          -3.0F,
          new Item.Properties(),
          CrookItem::new,
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<HammerItem> HAMMER_ANDESITE =
      ITEMS.hammerItem(
          "Andesite Hammer",
          "andesite_hammer",
          ToolMaterial.STONE,
          -1.0F,
          -2.0F,
          new Item.Properties(),
          HammerItem::new,
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<HammerItem> HAMMER_BAMBOO =
      ITEMS.hammerItem(
          "Bamboo Hammer",
          "bamboo_hammer",
          ToolMaterial.WOOD,
          0.0F,
          -3.0F,
          new Item.Properties(),
          HammerItem::new,
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<HammerItem> HAMMER_BASALT =
      ITEMS.hammerItem(
          "Basalt Hammer",
          "basalt_hammer",
          ToolMaterial.STONE,
          -1.0F,
          -2.0F,
          new Item.Properties(),
          HammerItem::new,
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<HammerItem> HAMMER_BLACKSTONE =
      ITEMS.hammerItem(
          "Blackstone Hammer",
          "blackstone_hammer",
          ToolMaterial.STONE,
          -1.0F,
          -2.0F,
          new Item.Properties(),
          HammerItem::new,
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<HammerItem> HAMMER_BONE =
      ITEMS.hammerItem(
          "Bone Hammer",
          "bone_hammer",
          ToolMaterial.STONE,
          -1.0F,
          -2.0F,
          new Item.Properties(),
          HammerItem::new,
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<HammerItem> HAMMER_CALCITE =
      ITEMS.hammerItem(
          "Calcite Hammer",
          "calcite_hammer",
          ToolMaterial.STONE,
          -1.0F,
          -2.0F,
          new Item.Properties(),
          HammerItem::new,
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<HammerItem> HAMMER_CHERRY =
      ITEMS.hammerItem(
          "Cherry Hammer",
          "cherry_hammer",
          ToolMaterial.WOOD,
          0.0F,
          -3.0F,
          new Item.Properties(),
          HammerItem::new,
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<HammerItem> HAMMER_COPPER =
      ITEMS.hammerItem(
          "Copper Hammer",
          "copper_hammer",
          ToolMaterial.IRON,
          -2.0F,
          -1.0F,
          new Item.Properties(),
          HammerItem::new,
          ItemDefinition.ItemType.TOOL);
  //  public static final ItemDefinition<HammerItem> HAMMER_CRIMSON_FUNGUS =
  //      ITEMS.item(
  //          "Crimson Fungus Hammer",
  //          "crimson_fungus_hammer",
  //          () -> new HammerItem(Tiers.STONE, Config.getCrookWoodDurability()),
  // ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<HammerItem> HAMMER_DEEPSLATE =
      ITEMS.hammerItem(
          "Deepslate Hammer",
          "deepslate_hammer",
          ToolMaterial.STONE,
          -1.0F,
          -2.0F,
          new Item.Properties(),
          HammerItem::new,
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<HammerItem> HAMMER_DIAMOND =
      ITEMS.hammerItem(
          "Diamond Hammer",
          "diamond_hammer",
          ToolMaterial.DIAMOND,
          -3.0F,
          0.0F,
          new Item.Properties(),
          HammerItem::new,
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<HammerItem> HAMMER_DIORITE =
      ITEMS.hammerItem(
          "Diorite Hammer",
          "diorite_hammer",
          ToolMaterial.STONE,
          -1.0F,
          -2.0F,
          new Item.Properties(),
          HammerItem::new,
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<HammerItem> HAMMER_DRIPSTONE =
      ITEMS.hammerItem(
          "Dripstone Hammer",
          "dripstone_hammer",
          ToolMaterial.STONE,
          -1.0F,
          -2.0F,
          new Item.Properties(),
          HammerItem::new,
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<HammerItem> HAMMER_GOLD =
      ITEMS.hammerItem(
          "Golden Hammer",
          "golden_hammer",
          ToolMaterial.GOLD,
          0.0F,
          -3.0F,
          new Item.Properties(),
          HammerItem::new,
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<HammerItem> HAMMER_GRANITE =
      ITEMS.hammerItem(
          "Granite Hammer",
          "granite_hammer",
          ToolMaterial.STONE,
          -1.0F,
          -2.0F,
          new Item.Properties(),
          HammerItem::new,
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<HammerItem> HAMMER_IRON =
      ITEMS.hammerItem(
          "Iron Hammer",
          "iron_hammer",
          ToolMaterial.IRON,
          -2.0F,
          -1.0F,
          new Item.Properties(),
          HammerItem::new,
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<HammerItem> HAMMER_NETHER_BRICK =
      ITEMS.hammerItem(
          "Nether Brick Hammer",
          "nether_brick_hammer",
          ToolMaterial.STONE,
          -1.0F,
          -2.0F,
          new Item.Properties(),
          HammerItem::new,
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<HammerItem> HAMMER_NETHERITE =
      ITEMS.hammerItem(
          "Netherite Hammer",
          "netherite_hammer",
          ToolMaterial.NETHERITE,
          -4.0F,
          0.0F,
          new Item.Properties(),
          HammerItem::new,
          ItemDefinition.ItemType.TOOL);
  //    public static final ItemDefinition<HammerItem> HAMMER_PRISMARINE =
  //        ITEMS.item(
  //            "Prismarine Hammer",
  //            "prismarine_hammer",
  //            () -> new HammerItem(Tiers.STONE,
  //   Config.getCrookStoneDurability()), ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<HammerItem> HAMMER_RED_NETHER_BRICK =
      ITEMS.hammerItem(
          "Red Nether Brick Hammer",
          "red_nether_brick_hammer",
          ToolMaterial.STONE,
          -1.0F,
          -2.0F,
          new Item.Properties(),
          HammerItem::new,
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<HammerItem> HAMMER_STONE =
      ITEMS.hammerItem(
          "Stone Hammer",
          "stone_hammer",
          ToolMaterial.STONE,
          -1.0F,
          -2.0F,
          new Item.Properties(),
          HammerItem::new,
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<HammerItem> HAMMER_TERRACOTTA =
      ITEMS.hammerItem(
          "Terracotta Hammer",
          "terracotta_hammer",
          ToolMaterial.STONE,
          -1.0F,
          -2.0F,
          new Item.Properties(),
          HammerItem::new,
          ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<HammerItem> HAMMER_TUFF =
      ITEMS.hammerItem(
          "Tuff Hammer",
          "tuff_hammer",
          ToolMaterial.STONE,
          -1.0F,
          -2.0F,
          new Item.Properties(),
          HammerItem::new,
          ItemDefinition.ItemType.TOOL);
  //  public static final ItemDefinition<HammerItem> HAMMER_WARPED_FUNGUS =
  //      ITEMS.item(
  //          "Warped Fungus Hammer",
  //          "warped_fungus_hammer",
  //          () -> new HammerItem(Tiers.STONE, Config.getCrookStoneDurability()),
  // ItemDefinition.ItemType.TOOL);
  public static final ItemDefinition<HammerItem> HAMMER_WOOD =
      ITEMS.hammerItem(
          "Wooden Hammer",
          "wooden_hammer",
          ToolMaterial.WOOD,
          0.0F,
          -3.0F,
          new Item.Properties(),
          HammerItem::new,
          ItemDefinition.ItemType.TOOL);
  // Begin Block Items
  public static final ItemDefinition<BucketItem> WITCH_WATER_BUCKET =
      ITEMS.bucketItem(
          "Witch Water Bucket",
          "witch_water_bucket",
          EXNFluids.WITCH_WATER.getStillFluid(),
          new Item.Properties().stacksTo(1),
          BucketItem::new,
          ItemDefinition.ItemType.OTHER);
  public static final ItemDefinition<BucketItem> SEA_WATER_BUCKET =
      ITEMS.bucketItem(
          "Sea Water Bucket",
          "sea_water_bucket",
          EXNFluids.SEA_WATER.getStillFluid(),
          new Item.Properties().stacksTo(1),
          BucketItem::new,
          ItemDefinition.ItemType.OTHER);

  private EXNItems() {}

  private static Ore createOre(
      String id, Optional<Item> rawItem, Optional<Item> ingotItem, Optional<Item> nuggetItem) {
    return new Ore(id, true, rawItem, ingotItem, nuggetItem, ITEMS);
  }
}
