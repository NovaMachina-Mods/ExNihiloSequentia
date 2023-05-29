package novamachina.exnihilosequentia.common.init;

import com.mojang.logging.LogUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import novamachina.exnihilosequentia.common.item.SilkwormItem;
import novamachina.exnihilosequentia.common.item.mesh.MeshType;
import novamachina.exnihilosequentia.common.item.ore.Ore;
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;

@SuppressWarnings("unused")
public class ExNihiloItems {

  public static final List<RegistryObject<CrookBaseItem>> CROOKS = new ArrayList<>();
  public static final List<RegistryObject<Item>> NUGGETS = new ArrayList<>();
  public static final List<RegistryObject<PebbleItem>> PEBBLES = new ArrayList<>();
  public static final List<RegistryObject<HammerBaseItem>> HAMMERS = new ArrayList<>();
  public static final List<RegistryObject<ResourceItem>> RESOURCE_ITEMS = new ArrayList<>();
  public static final List<Ore> ORES = new ArrayList<>();
  private static final DeferredRegister<Item> ITEMS =
      DeferredRegister.create(ForgeRegistries.ITEMS, ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA);
  // Begin Items Only
  public static final RegistryObject<CookedSilkwormItem> COOKED_SILKWORM =
      ITEMS.register(ExNihiloConstants.Items.COOKED_SILKWORM, CookedSilkwormItem::new);
  public static final RegistryObject<Item> SILKWORM =
      ITEMS.register(
          ExNihiloConstants.Items.SILKWORM,
          () -> new SilkwormItem(ExNihiloConstants.Items.SILKWORM));
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
      registerPebble(ExNihiloConstants.Items.ANDESITE_PEBBLE);
  public static final RegistryObject<PebbleItem> PEBBLE_BASALT =
      registerPebble(ExNihiloConstants.Items.BASALT_PEBBLE);
  public static final RegistryObject<PebbleItem> PEBBLE_BLACKSTONE =
      registerPebble(ExNihiloConstants.Items.BLACKSTONE_PEBBLE);
  public static final RegistryObject<PebbleItem> PEBBLE_CALCITE =
      registerPebble(ExNihiloConstants.Items.CALCITE_PEBBLE);
  public static final RegistryObject<PebbleItem> PEBBLE_DEEPSLATE =
      registerPebble(ExNihiloConstants.Items.DEEPSLATE_PEBBLE);
  public static final RegistryObject<PebbleItem> PEBBLE_DIORITE =
      registerPebble(ExNihiloConstants.Items.DIORITE_PEBBLE);
  public static final RegistryObject<PebbleItem> PEBBLE_DRIPSTONE =
      registerPebble(ExNihiloConstants.Items.DRIPSTONE_PEBBLE);
  public static final RegistryObject<PebbleItem> PEBBLE_END_STONE =
      registerPebble(ExNihiloConstants.Items.END_STONE_PEBBLE);
  public static final RegistryObject<PebbleItem> PEBBLE_GRANITE =
      registerPebble(ExNihiloConstants.Items.GRANITE_PEBBLE);
  public static final RegistryObject<PebbleItem> PEBBLE_NETHERRACK =
      registerPebble(ExNihiloConstants.Items.NETHERRACK_PEBBLE);
  public static final RegistryObject<PebbleItem> PEBBLE_STONE =
      registerPebble(ExNihiloConstants.Items.STONE_PEBBLE);
  public static final RegistryObject<PebbleItem> PEBBLE_TUFF =
      registerPebble(ExNihiloConstants.Items.TUFF_PEBBLE);
  public static final RegistryObject<ResourceItem> MYCELIUM_SPORE =
      ITEMS.register(
          ExNihiloConstants.Items.MYCELIUM_SPORE,
          () ->
              new ResourceItem(
                  ExNihiloConstants.Items.MYCELIUM_SPORE, Blocks.DIRT, Blocks.MYCELIUM));
  public static final RegistryObject<ResourceItem> CRIMSON_NYLIUM_SPORE =
      ITEMS.register(
          ExNihiloConstants.Items.CRIMSON_NYLIUM_SPORE,
          () ->
              new ResourceItem(
                  ExNihiloConstants.Items.CRIMSON_NYLIUM_SPORE,
                  Blocks.NETHERRACK,
                  Blocks.CRIMSON_NYLIUM));
  public static final RegistryObject<ResourceItem> WARPED_NYLIUM_SPORE =
      ITEMS.register(
          ExNihiloConstants.Items.WARPED_NYLIUM_SPORE,
          () ->
              new ResourceItem(
                  ExNihiloConstants.Items.WARPED_NYLIUM_SPORE,
                  Blocks.NETHERRACK,
                  Blocks.WARPED_NYLIUM));
  public static final RegistryObject<ResourceItem> GRASS_SEED =
      ITEMS.register(
          ExNihiloConstants.Items.GRASS_SEED,
          () ->
              new ResourceItem(
                  ExNihiloConstants.Items.GRASS_SEED, Blocks.DIRT, Blocks.GRASS_BLOCK));
  public static final RegistryObject<ResourceItem> PORCELAIN_CLAY =
      ITEMS.register(
          ExNihiloConstants.Items.PORCELAIN_CLAY,
          () -> new ResourceItem(ExNihiloConstants.Items.PORCELAIN_CLAY));
  public static final RegistryObject<ResourceItem> CRAFTING_DOLL =
      ITEMS.register(
          ExNihiloConstants.Items.CRAFTING_DOLL,
          () -> new ResourceItem(ExNihiloConstants.Items.CRAFTING_DOLL));
  public static final RegistryObject<ResourceItem> TUBE_CORAL_LARVA =
      ITEMS.register(
          ExNihiloConstants.Items.TUBE_CORAL_LARVA,
          () -> new ResourceItem(ExNihiloConstants.Items.TUBE_CORAL_LARVA));
  public static final RegistryObject<ResourceItem> BRAIN_CORAL_LARVA =
      ITEMS.register(
          ExNihiloConstants.Items.BRAIN_CORAL_LARVA,
          () -> new ResourceItem(ExNihiloConstants.Items.BRAIN_CORAL_LARVA));
  public static final RegistryObject<ResourceItem> BUBBLE_CORAL_LARVA =
      ITEMS.register(
          ExNihiloConstants.Items.BUBBLE_CORAL_LARVA,
          () -> new ResourceItem(ExNihiloConstants.Items.BUBBLE_CORAL_LARVA));
  public static final RegistryObject<ResourceItem> FIRE_CORAL_LARVA =
      ITEMS.register(
          ExNihiloConstants.Items.FIRE_CORAL_LARVA,
          () -> new ResourceItem(ExNihiloConstants.Items.FIRE_CORAL_LARVA));
  public static final RegistryObject<ResourceItem> HORN_CORAL_LARVA =
      ITEMS.register(
          ExNihiloConstants.Items.HORN_CORAL_LARVA,
          () -> new ResourceItem(ExNihiloConstants.Items.HORN_CORAL_LARVA));
  public static final RegistryObject<ResourceItem> BEEHIVE_FRAME =
      ITEMS.register(
          ExNihiloConstants.Items.BEEHIVE_FRAME,
          () -> new ResourceItem(ExNihiloConstants.Items.BEEHIVE_FRAME));
  public static final RegistryObject<MeshItem> MESH_STRING =
      ITEMS.register(
          ExNihiloConstants.Items.STRING_MESH,
          () ->
              new MeshItem(
                  ExNihiloConstants.Items.STRING_MESH,
                  Config.getMeshStringValue(),
                  MeshType.STRING));
  public static final RegistryObject<MeshItem> MESH_FLINT =
      ITEMS.register(
          ExNihiloConstants.Items.FLINT_MESH,
          () ->
              new MeshItem(
                  ExNihiloConstants.Items.FLINT_MESH, Config.getMeshFlintValue(), MeshType.FLINT));
  public static final RegistryObject<MeshItem> MESH_IRON =
      ITEMS.register(
          ExNihiloConstants.Items.IRON_MESH,
          () ->
              new MeshItem(
                  ExNihiloConstants.Items.IRON_MESH, Config.getMeshIronValue(), MeshType.IRON));
  public static final RegistryObject<MeshItem> MESH_DIAMOND =
      ITEMS.register(
          ExNihiloConstants.Items.DIAMOND_MESH,
          () ->
              new MeshItem(
                  ExNihiloConstants.Items.DIAMOND_MESH,
                  Config.getMeshDiamondValue(),
                  MeshType.DIAMOND));
  public static final RegistryObject<MeshItem> MESH_EMERALD =
      ITEMS.register(
          ExNihiloConstants.Items.EMERALD_MESH,
          () ->
              new MeshItem(
                  ExNihiloConstants.Items.EMERALD_MESH,
                  Config.getMeshEmeraldValue(),
                  MeshType.EMERALD));
  public static final RegistryObject<MeshItem> MESH_NETHERITE =
      ITEMS.register(
          ExNihiloConstants.Items.NETHERITE_MESH,
          () ->
              new MeshItem(
                  ExNihiloConstants.Items.NETHERITE_MESH,
                  Config.getMeshNetheriteValue(),
                  MeshType.NETHERITE));
  public static final RegistryObject<CrookBaseItem> CROOK_ANDESITE =
      registerCrook(
          ExNihiloConstants.Items.ANDISITE_CROOK, Tiers.STONE, Config.getCrookAndesiteDurability());
  public static final RegistryObject<CrookBaseItem> CROOK_BASALT =
      registerCrook(
          ExNihiloConstants.Items.BASALT_CROOK, Tiers.STONE, Config.getCrookAndesiteDurability());
  public static final RegistryObject<CrookBaseItem> CROOK_BLACKSTONE =
      registerCrook(
          ExNihiloConstants.Items.BLACKSTONE_CROOK,
          Tiers.STONE,
          Config.getCrookAndesiteDurability());
  public static final RegistryObject<CrookBaseItem> CROOK_BONE =
      registerCrook(
          ExNihiloConstants.Items.BONE_CROOK, Tiers.STONE, Config.getCrookBoneDurability());
  public static final RegistryObject<CrookBaseItem> CROOK_CALCITE =
      registerCrook(
          ExNihiloConstants.Items.CALCITE_CROOK, Tiers.STONE, Config.getCrookAndesiteDurability());
  public static final RegistryObject<CrookBaseItem> CROOK_COPPER =
      registerCrook(
          ExNihiloConstants.Items.COPPER_CROOK, Tiers.IRON, Config.getCrookIronDurability());
  //  public static final RegistryObject<CrookBaseItem> CROOK_CRIMSON_FUNGUS =
  // registerCrook(ExNihiloConstants.Items.CRIMSON_FUNGUS_CROOK, Tiers.STONE,
  // Config.getCrookWoodDurability());
  public static final RegistryObject<CrookBaseItem> CROOK_DEEPSLATE =
      registerCrook(
          ExNihiloConstants.Items.DEEPSLATE_CROOK, Tiers.STONE, Config.getCrookStoneDurability());
  public static final RegistryObject<CrookBaseItem> CROOK_DIAMOND =
      registerCrook(
          ExNihiloConstants.Items.DIAMOND_CROOK, Tiers.DIAMOND, Config.getCrookDiamondDurability());
  public static final RegistryObject<CrookBaseItem> CROOK_DIORITE =
      registerCrook(
          ExNihiloConstants.Items.DIORITE_CROOK, Tiers.STONE, Config.getCrookDioriteDurability());
  public static final RegistryObject<CrookBaseItem> CROOK_DRIPSTONE =
      registerCrook(
          ExNihiloConstants.Items.DRIPSTONE_CROOK, Tiers.STONE, Config.getCrookDioriteDurability());
  public static final RegistryObject<CrookBaseItem> CROOK_GOLD =
      registerCrook(
          ExNihiloConstants.Items.GOLD_CROOK, Tiers.IRON, Config.getCrookGoldDurability());
  public static final RegistryObject<CrookBaseItem> CROOK_GRANITE =
      registerCrook(
          ExNihiloConstants.Items.GRANITE_CROOK, Tiers.STONE, Config.getCrookGraniteDurability());
  public static final RegistryObject<CrookBaseItem> CROOK_IRON =
      registerCrook(
          ExNihiloConstants.Items.IRON_CROOK, Tiers.IRON, Config.getCrookIronDurability());
  public static final RegistryObject<CrookBaseItem> CROOK_NETHER_BRICK =
      registerCrook(
          ExNihiloConstants.Items.NETHER_BRICK_CROOK,
          Tiers.STONE,
          Config.getCrookStoneDurability());
  public static final RegistryObject<CrookBaseItem> CROOK_NETHERITE =
      registerCrook(
          ExNihiloConstants.Items.NETHERITE_CROOK,
          Tiers.NETHERITE,
          Config.getCrookNetheriteDurability());
  public static final RegistryObject<CrookBaseItem> CROOK_PRISMARINE =
      registerCrook(
          ExNihiloConstants.Items.PRISMARINE_CROOK, Tiers.STONE, Config.getCrookStoneDurability());
  public static final RegistryObject<CrookBaseItem> CROOK_RED_NETHER_BRICK =
      registerCrook(
          ExNihiloConstants.Items.RED_NETHER_BRICK_CROOK,
          Tiers.STONE,
          Config.getCrookStoneDurability());
  public static final RegistryObject<CrookBaseItem> CROOK_STONE =
      registerCrook(
          ExNihiloConstants.Items.STONE_CROOK, Tiers.STONE, Config.getCrookStoneDurability());
  public static final RegistryObject<CrookBaseItem> CROOK_TERRACOTTA =
      registerCrook(
          ExNihiloConstants.Items.TERRACOTTA_CROOK, Tiers.STONE, Config.getCrookStoneDurability());
  public static final RegistryObject<CrookBaseItem> CROOK_TUFF =
      registerCrook(
          ExNihiloConstants.Items.TUFF_CROOK, Tiers.STONE, Config.getCrookStoneDurability());
  //  public static final RegistryObject<CrookBaseItem> CROOK_WARPED_FUNGUS =
  // registerCrook(ExNihiloConstants.Items.WARPED_FUNGUS_CROOK, Tiers.STONE,
  // Config.getCrookStoneDurability());
  public static final RegistryObject<CrookBaseItem> CROOK_WOOD =
      registerCrook(
          ExNihiloConstants.Items.WOODEN_CROOK, Tiers.WOOD, Config.getCrookWoodDurability());
  public static final RegistryObject<HammerBaseItem> HAMMER_ANDESITE =
      registerHammer(
          ExNihiloConstants.Items.ANDESITE_HAMMER, Tiers.STONE, Config.getHammerStoneDurability());
  public static final RegistryObject<HammerBaseItem> HAMMER_BASALT =
      registerHammer(
          ExNihiloConstants.Items.BASALT_HAMMER, Tiers.STONE, Config.getHammerStoneDurability());
  public static final RegistryObject<HammerBaseItem> HAMMER_BLACKSTONE =
      registerHammer(
          ExNihiloConstants.Items.BLACKSTONE_HAMMER,
          Tiers.STONE,
          Config.getHammerStoneDurability());
  public static final RegistryObject<HammerBaseItem> HAMMER_CALCITE =
      registerHammer(
          ExNihiloConstants.Items.CALCITE_HAMMER, Tiers.STONE, Config.getHammerStoneDurability());
  public static final RegistryObject<HammerBaseItem> HAMMER_COPPER =
      registerHammer(
          ExNihiloConstants.Items.COPPER_HAMMER, Tiers.IRON, Config.getHammerIronDurability());
  //  public static final RegistryObject<HammerBaseItem> HAMMER_CRIMSON_FUNGUS =
  // registerHammer(ExNihiloConstants.Items.CRIMSON_FUNGUS_HAMMER, Tiers.WOOD,
  // Config.getHammerWoodDurability());
  public static final RegistryObject<HammerBaseItem> HAMMER_DEEPSLATE =
      registerHammer(
          ExNihiloConstants.Items.DEEPSLATE_HAMMER, Tiers.STONE, Config.getHammerStoneDurability());
  public static final RegistryObject<HammerBaseItem> HAMMER_DIAMOND =
      registerHammer(
          ExNihiloConstants.Items.DIAMOND_HAMMER,
          Tiers.DIAMOND,
          Config.getHammerDiamondDurability());
  public static final RegistryObject<HammerBaseItem> HAMMER_DIORITE =
      registerHammer(
          ExNihiloConstants.Items.DIORITE_HAMMER, Tiers.STONE, Config.getHammerStoneDurability());
  public static final RegistryObject<HammerBaseItem> HAMMER_DRIPSTONE =
      registerHammer(
          ExNihiloConstants.Items.DRIPSTONE_HAMMER, Tiers.STONE, Config.getHammerStoneDurability());
  public static final RegistryObject<HammerBaseItem> HAMMER_GOLD =
      registerHammer(
          ExNihiloConstants.Items.GOLD_HAMMER, Tiers.GOLD, Config.getHammerGoldDurability());
  public static final RegistryObject<HammerBaseItem> HAMMER_GRANITE =
      registerHammer(
          ExNihiloConstants.Items.GRANITE_HAMMER, Tiers.STONE, Config.getHammerStoneDurability());
  public static final RegistryObject<HammerBaseItem> HAMMER_IRON =
      registerHammer(
          ExNihiloConstants.Items.IRON_HAMMER, Tiers.IRON, Config.getHammerIronDurability());
  public static final RegistryObject<HammerBaseItem> HAMMER_NETHER_BRICK =
      registerHammer(
          ExNihiloConstants.Items.NETHER_BRICK_HAMMER,
          Tiers.STONE,
          Config.getHammerStoneDurability());
  public static final RegistryObject<HammerBaseItem> HAMMER_NETHERITE =
      registerHammer(
          ExNihiloConstants.Items.NETHERITE_HAMMER,
          Tiers.NETHERITE,
          Config.getHammerNetheriteDurability());
  public static final RegistryObject<HammerBaseItem> HAMMER_PRISMARINE =
      registerHammer(
          ExNihiloConstants.Items.PRISMARINE_HAMMER,
          Tiers.STONE,
          Config.getHammerStoneDurability());
  public static final RegistryObject<HammerBaseItem> HAMMER_RED_NETHER_BRICK =
      registerHammer(
          ExNihiloConstants.Items.RED_NETHER_BRICK_HAMMER,
          Tiers.STONE,
          Config.getHammerStoneDurability());
  public static final RegistryObject<HammerBaseItem> HAMMER_STONE =
      registerHammer(
          ExNihiloConstants.Items.STONE_HAMMER, Tiers.STONE, Config.getHammerStoneDurability());
  public static final RegistryObject<HammerBaseItem> HAMMER_TERRACOTTA =
      registerHammer(
          ExNihiloConstants.Items.TERRACOTTA_HAMMER,
          Tiers.STONE,
          Config.getHammerStoneDurability());
  public static final RegistryObject<HammerBaseItem> HAMMER_TUFF =
      registerHammer(
          ExNihiloConstants.Items.TUFF_HAMMER, Tiers.STONE, Config.getHammerStoneDurability());
  //  public static final RegistryObject<HammerBaseItem> HAMMER_WARPED_FUNGUS =
  // registerHammer(ExNihiloConstants.Items.WARPED_FUNGUS_HAMMER, Tiers.WOOD,
  // Config.getHammerWoodDurability());
  public static final RegistryObject<HammerBaseItem> HAMMER_WOOD =
      registerHammer(
          ExNihiloConstants.Items.WOODEN_HAMMER, Tiers.WOOD, Config.getHammerWoodDurability());
  private static final ExNihiloLogger logger = new ExNihiloLogger(LogUtils.getLogger());
  // Begin Block Items
  private static final Item.Properties tab =
      new Item.Properties().tab(ExNihiloInitialization.ITEM_GROUP);
  public static final RegistryObject<BlockItem> DUST =
      ITEMS.register(
          ExNihiloConstants.Blocks.DUST, () -> new BlockItem(ExNihiloBlocks.DUST.get(), tab));
  public static final RegistryObject<BlockItem> CRUSHED_ANDESITE =
      ITEMS.register(
          ExNihiloConstants.Blocks.CRUSHED_ANDESITE,
          () -> new BlockItem(ExNihiloBlocks.CRUSHED_ANDESITE.get(), tab));
  public static final RegistryObject<BlockItem> CRUSHED_BASALT =
      ITEMS.register(
          ExNihiloConstants.Blocks.CRUSHED_BASALT,
          () -> new BlockItem(ExNihiloBlocks.CRUSHED_BASALT.get(), tab));

  public static final RegistryObject<BlockItem> CRUSHED_BLACKSTONE =
      ITEMS.register(
          ExNihiloConstants.Blocks.CRUSHED_BLACKSTONE,
          () -> new BlockItem(ExNihiloBlocks.CRUSHED_BLACKSTONE.get(), tab));
  public static final RegistryObject<BlockItem> CRUSHED_CALCITE =
      ITEMS.register(
          ExNihiloConstants.Blocks.CRUSHED_CALCITE,
          () -> new BlockItem(ExNihiloBlocks.CRUSHED_CALCITE.get(), tab));

  public static final RegistryObject<BlockItem> CRUSHED_DEEPSLATE =
      ITEMS.register(
          ExNihiloConstants.Blocks.CRUSHED_DEEPSLATE,
          () -> new BlockItem(ExNihiloBlocks.CRUSHED_DEEPSLATE.get(), tab));
  public static final RegistryObject<BlockItem> CRUSHED_DIORITE =
      ITEMS.register(
          ExNihiloConstants.Blocks.CRUSHED_DIORITE,
          () -> new BlockItem(ExNihiloBlocks.CRUSHED_DIORITE.get(), tab));

  public static final RegistryObject<BlockItem> CRUSHED_DRIPSTONE =
      ITEMS.register(
          ExNihiloConstants.Blocks.CRUSHED_DRIPSTONE,
          () -> new BlockItem(ExNihiloBlocks.CRUSHED_DRIPSTONE.get(), tab));
  public static final RegistryObject<BlockItem> CRUSHED_END_STONE =
      ITEMS.register(
          ExNihiloConstants.Blocks.CRUSHED_END_STONE,
          () -> new BlockItem(ExNihiloBlocks.CRUSHED_END_STONE.get(), tab));
  public static final RegistryObject<BlockItem> CRUSHED_GRANITE =
      ITEMS.register(
          ExNihiloConstants.Blocks.CRUSHED_GRANITE,
          () -> new BlockItem(ExNihiloBlocks.CRUSHED_GRANITE.get(), tab));
  public static final RegistryObject<BlockItem> CRUSHED_NETHERRACK =
      ITEMS.register(
          ExNihiloConstants.Blocks.CRUSHED_NETHERRACK,
          () -> new BlockItem(ExNihiloBlocks.CRUSHED_NETHERRACK.get(), tab));

  public static final RegistryObject<BlockItem> CRUSHED_TUFF =
      ITEMS.register(
          ExNihiloConstants.Blocks.CRUSHED_TUFF,
          () -> new BlockItem(ExNihiloBlocks.CRUSHED_TUFF.get(), tab));
  public static final RegistryObject<BlockItem> END_CAKE =
      ITEMS.register(
          ExNihiloConstants.Blocks.END_CAKE,
          () -> new BlockItem(ExNihiloBlocks.END_CAKE.get(), tab));
  public static final RegistryObject<BlockItem> INFESTING_LEAVES =
      ITEMS.register(
          ExNihiloConstants.Blocks.INFESTING_LEAVES,
          () -> new BlockItem(ExNihiloBlocks.INFESTING_LEAVES.get(), tab));
  public static final RegistryObject<BlockItem> INFESTED_LEAVES =
      ITEMS.register(
          ExNihiloConstants.Blocks.INFESTED_LEAVES,
          () -> new BlockItem(ExNihiloBlocks.INFESTED_LEAVES.get(), tab));
  public static final RegistryObject<BlockItem> SIEVE_ACACIA =
      ITEMS.register(
          ExNihiloConstants.Blocks.ACACIA_SIEVE,
          () -> createBurnableItem(ExNihiloBlocks.SIEVE_ACACIA.get()));
  public static final RegistryObject<BlockItem> SIEVE_BIRCH =
      ITEMS.register(
          ExNihiloConstants.Blocks.BIRCH_SIEVE,
          () -> createBurnableItem(ExNihiloBlocks.SIEVE_BIRCH.get()));
  public static final RegistryObject<BlockItem> SIEVE_DARK_OAK =
      ITEMS.register(
          ExNihiloConstants.Blocks.DARK_OAK_SIEVE,
          () -> createBurnableItem(ExNihiloBlocks.SIEVE_DARK_OAK.get()));
  public static final RegistryObject<BlockItem> SIEVE_JUNGLE =
      ITEMS.register(
          ExNihiloConstants.Blocks.JUNGLE_SIEVE,
          () -> createBurnableItem(ExNihiloBlocks.SIEVE_JUNGLE.get()));
  public static final RegistryObject<BlockItem> SIEVE_MANGROVE =
      ITEMS.register(
          ExNihiloConstants.Blocks.MANGROVE_SIEVE,
          () -> createBurnableItem(ExNihiloBlocks.SIEVE_MANGROVE.get()));
  public static final RegistryObject<BlockItem> SIEVE_OAK =
      ITEMS.register(
          ExNihiloConstants.Blocks.OAK_SIEVE,
          () -> createBurnableItem(ExNihiloBlocks.SIEVE_OAK.get()));
  public static final RegistryObject<BlockItem> SIEVE_SPRUCE =
      ITEMS.register(
          ExNihiloConstants.Blocks.SPRUCE_SIEVE,
          () -> createBurnableItem(ExNihiloBlocks.SIEVE_SPRUCE.get()));
  public static final RegistryObject<BlockItem> SIEVE_CRIMSON =
      ITEMS.register(
          ExNihiloConstants.Blocks.CRIMSON_SIEVE,
          () -> new BlockItem(ExNihiloBlocks.SIEVE_CRIMSON.get(), tab));
  public static final RegistryObject<BlockItem> SIEVE_WARPED =
      ITEMS.register(
          ExNihiloConstants.Blocks.WARPED_SIEVE,
          () -> new BlockItem(ExNihiloBlocks.SIEVE_WARPED.get(), tab));
  public static final RegistryObject<BlockItem> CRUCIBLE_UNFIRED =
      ITEMS.register(
          ExNihiloConstants.Blocks.UNFIRED_CRUCIBLE,
          () -> new BlockItem(ExNihiloBlocks.CRUCIBLE_UNFIRED.get(), tab));
  public static final RegistryObject<BlockItem> CRUCIBLE_FIRED =
      ITEMS.register(
          ExNihiloConstants.Blocks.FIRED_CRUCIBLE,
          () -> new BlockItem(ExNihiloBlocks.CRUCIBLE_FIRED.get(), tab));
  public static final RegistryObject<BlockItem> CRUCIBLE_ACACIA =
      ITEMS.register(
          ExNihiloConstants.Blocks.ACACIA_CRUCIBLE,
          () -> createBurnableItem(ExNihiloBlocks.CRUCIBLE_ACACIA.get()));
  public static final RegistryObject<BlockItem> CRUCIBLE_BIRCH =
      ITEMS.register(
          ExNihiloConstants.Blocks.BIRCH_CRUCIBLE,
          () -> createBurnableItem(ExNihiloBlocks.CRUCIBLE_BIRCH.get()));
  public static final RegistryObject<BlockItem> CRUCIBLE_DARK_OAK =
      ITEMS.register(
          ExNihiloConstants.Blocks.DARK_OAK_CRUCIBLE,
          () -> createBurnableItem(ExNihiloBlocks.CRUCIBLE_DARK_OAK.get()));
  public static final RegistryObject<BlockItem> CRUCIBLE_JUNGLE =
      ITEMS.register(
          ExNihiloConstants.Blocks.JUNGLE_CRUCIBLE,
          () -> createBurnableItem(ExNihiloBlocks.CRUCIBLE_JUNGLE.get()));
  public static final RegistryObject<BlockItem> CRUCIBLE_MANGROVE =
      ITEMS.register(
          ExNihiloConstants.Blocks.MANGROVE_CRUCIBLE,
          () -> createBurnableItem(ExNihiloBlocks.CRUCIBLE_MANGROVE.get()));
  public static final RegistryObject<BlockItem> CRUCIBLE_OAK =
      ITEMS.register(
          ExNihiloConstants.Blocks.OAK_CRUCIBLE,
          () -> createBurnableItem(ExNihiloBlocks.CRUCIBLE_OAK.get()));
  public static final RegistryObject<BlockItem> CRUCIBLE_SPRUCE =
      ITEMS.register(
          ExNihiloConstants.Blocks.SPRUCE_CRUCIBLE,
          () -> createBurnableItem(ExNihiloBlocks.CRUCIBLE_SPRUCE.get()));
  public static final RegistryObject<BlockItem> CRUCIBLE_CRIMSON =
      ITEMS.register(
          ExNihiloConstants.Blocks.CRIMSON_CRUCIBLE,
          () -> new BlockItem(ExNihiloBlocks.CRUCIBLE_CRIMSON.get(), tab));
  public static final RegistryObject<BlockItem> CRUCIBLE_WARPED =
      ITEMS.register(
          ExNihiloConstants.Blocks.WARPED_CRUCIBLE,
          () -> new BlockItem(ExNihiloBlocks.CRUCIBLE_WARPED.get(), tab));
  public static final RegistryObject<BlockItem> BARREL_ACACIA =
      ITEMS.register(
          ExNihiloConstants.Blocks.ACACIA_BARREL,
          () -> createBurnableItem(ExNihiloBlocks.BARREL_ACACIA.get()));
  public static final RegistryObject<BlockItem> BARREL_BIRCH =
      ITEMS.register(
          ExNihiloConstants.Blocks.BIRCH_BARREL,
          () -> createBurnableItem(ExNihiloBlocks.BARREL_BIRCH.get()));
  public static final RegistryObject<BlockItem> BARREL_DARK_OAK =
      ITEMS.register(
          ExNihiloConstants.Blocks.DARK_OAK_BARREL,
          () -> createBurnableItem(ExNihiloBlocks.BARREL_DARK_OAK.get()));
  public static final RegistryObject<BlockItem> BARREL_JUNGLE =
      ITEMS.register(
          ExNihiloConstants.Blocks.JUNGLE_BARREL,
          () -> createBurnableItem(ExNihiloBlocks.BARREL_JUNGLE.get()));
  public static final RegistryObject<BlockItem> BARREL_MANGROVE =
      ITEMS.register(
          ExNihiloConstants.Blocks.MANGROVE_BARREL,
          () -> createBurnableItem(ExNihiloBlocks.BARREL_MANGROVE.get()));
  public static final RegistryObject<BlockItem> BARREL_OAK =
      ITEMS.register(
          ExNihiloConstants.Blocks.OAK_BARREL,
          () -> createBurnableItem(ExNihiloBlocks.BARREL_OAK.get()));
  public static final RegistryObject<BlockItem> BARREL_SPRUCE =
      ITEMS.register(
          ExNihiloConstants.Blocks.SPRUCE_BARREL,
          () -> createBurnableItem(ExNihiloBlocks.BARREL_SPRUCE.get()));
  public static final RegistryObject<BlockItem> BARREL_CRIMSON =
      ITEMS.register(
          ExNihiloConstants.Blocks.CRIMSON_BARREL,
          () -> new BlockItem(ExNihiloBlocks.BARREL_CRIMSON.get(), tab));
  public static final RegistryObject<BlockItem> BARREL_WARPED =
      ITEMS.register(
          ExNihiloConstants.Blocks.WARPED_BARREL,
          () -> new BlockItem(ExNihiloBlocks.BARREL_WARPED.get(), tab));
  public static final RegistryObject<BlockItem> BARREL_STONE =
      ITEMS.register(
          ExNihiloConstants.Blocks.STONE_BARREL,
          () -> new BlockItem(ExNihiloBlocks.BARREL_STONE.get(), tab));
  public static final RegistryObject<BucketItem> WITCH_WATER_BUCKET =
      ITEMS.register(
          ExNihiloConstants.Items.WITCH_WATER_BUCKET,
          () -> new BucketItem(ExNihiloFluids.WITCH_WATER, tab.stacksTo(1)));
  public static final RegistryObject<BucketItem> SEA_WATER_BUCKET =
      ITEMS.register(
          ExNihiloConstants.Items.SEA_WATER_BUCKET,
          () -> new BucketItem(ExNihiloFluids.SEA_WATER, tab.stacksTo(1)));

  private ExNihiloItems() {}

  private static Ore createOre(
      String id, Optional<Item> rawItem, Optional<Item> ingotItem, Optional<Item> nuggetItem) {
    Ore ore = new Ore(id, true, rawItem, ingotItem, nuggetItem, ITEMS);
    ORES.add(ore);
    return ore;
  }

  private static RegistryObject<HammerBaseItem> registerHammer(
      String id, Tiers tier, int durability) {
    RegistryObject<HammerBaseItem> hammer =
        ITEMS.register(id, () -> new HammerBaseItem(tier, durability));
    HAMMERS.add(hammer);
    return hammer;
  }

  private static RegistryObject<PebbleItem> registerPebble(String id) {
    RegistryObject<PebbleItem> pebble = ITEMS.register(id, PebbleItem::new);
    PEBBLES.add(pebble);
    return pebble;
  }

  private static RegistryObject<CrookBaseItem> registerCrook(
      String id, Tiers toolTier, int durability) {
    RegistryObject<CrookBaseItem> crook =
        ITEMS.register(id, () -> new CrookBaseItem(toolTier, durability));
    CROOKS.add(crook);
    return crook;
  }

  private static RegistryObject<ResourceItem> registerResourceItem(String id) {
    RegistryObject<ResourceItem> resourceItem = ITEMS.register(id, () -> new ResourceItem(id));
    RESOURCE_ITEMS.add(resourceItem);
    return resourceItem;
  }

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
