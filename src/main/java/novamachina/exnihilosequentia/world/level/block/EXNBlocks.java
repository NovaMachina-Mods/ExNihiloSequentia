package novamachina.exnihilosequentia.world.level.block;

import java.util.List;
import javax.annotation.Nonnull;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.world.level.material.EXNFluids;
import novamachina.novacore.core.registries.BlockRegistry;
import novamachina.novacore.world.item.ItemDefinition;
import novamachina.novacore.world.level.block.BlockBuilder;
import novamachina.novacore.world.level.block.BlockDefinition;

public class EXNBlocks {

  private static final BlockRegistry BLOCKS =
      new BlockRegistry(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA);

  @Nonnull
  public static final BlockDefinition<Block> DUST =
      BLOCKS.block(
          "Dust",
          "dust",
          () -> BlockBuilder.create().strength(0.7F).sound(SoundType.WOOL).buildFallingBlock());

  @Nonnull
  public static final BlockDefinition<Block> CRUSHED_ANDESITE =
      BLOCKS.block(
          "Crushed Andesite",
          "crushed_andesite",
          () -> BlockBuilder.create().strength(0.7F).sound(SoundType.GRAVEL).buildFallingBlock());

  @Nonnull
  public static final BlockDefinition<Block> CRUSHED_BASALT =
      BLOCKS.block(
          "Crushed Basalt",
          "crushed_basalt",
          () -> BlockBuilder.create().strength(0.7F).sound(SoundType.GRAVEL).buildFallingBlock());

  @Nonnull
  public static final BlockDefinition<Block> CRUSHED_BLACKSTONE =
      BLOCKS.block(
          "Crushed Blackstone",
          "crushed_blackstone",
          () -> BlockBuilder.create().strength(0.7F).sound(SoundType.GRAVEL).buildFallingBlock());

  @Nonnull
  public static final BlockDefinition<Block> CRUSHED_CALCITE =
      BLOCKS.block(
          "Crushed Calcite",
          "crushed_calcite",
          () -> BlockBuilder.create().strength(0.7F).sound(SoundType.GRAVEL).buildFallingBlock());

  @Nonnull
  public static final BlockDefinition<Block> CRUSHED_DEEPSLATE =
      BLOCKS.block(
          "Crushed Deepslate",
          "crushed_deepslate",
          () -> BlockBuilder.create().strength(0.7F).sound(SoundType.GRAVEL).buildFallingBlock());

  @Nonnull
  public static final BlockDefinition<Block> CRUSHED_DIORITE =
      BLOCKS.block(
          "Crushed Diorite",
          "crushed_diorite",
          () -> BlockBuilder.create().strength(0.7F).sound(SoundType.GRAVEL).buildFallingBlock());

  @Nonnull
  public static final BlockDefinition<Block> CRUSHED_DRIPSTONE =
      BLOCKS.block(
          "Crushed Dripstone",
          "crushed_dripstone",
          () -> BlockBuilder.create().strength(0.7F).sound(SoundType.GRAVEL).buildFallingBlock());

  @Nonnull
  public static final BlockDefinition<Block> CRUSHED_END_STONE =
      BLOCKS.block(
          "Crushed End Stone",
          "crushed_end_stone",
          () -> BlockBuilder.create().strength(0.7F).sound(SoundType.GRAVEL).buildFallingBlock());

  @Nonnull
  public static final BlockDefinition<Block> CRUSHED_GRANITE =
      BLOCKS.block(
          "Crushed Granite",
          "crushed_granite",
          () -> BlockBuilder.create().strength(0.7F).sound(SoundType.GRAVEL).buildFallingBlock());

  @Nonnull
  public static final BlockDefinition<Block> CRUSHED_NETHERRACK =
      BLOCKS.block(
          "Crushed Netherrack",
          "crushed_netherrack",
          () -> BlockBuilder.create().strength(0.7F).sound(SoundType.GRAVEL).buildFallingBlock());

  @Nonnull
  public static final BlockDefinition<Block> CRUSHED_TUFF =
      BLOCKS.block(
          "Crushed Tuff",
          "crushed_tuff",
          () -> BlockBuilder.create().strength(0.7F).sound(SoundType.GRAVEL).buildFallingBlock());

  @Nonnull
  public static final BlockDefinition<EndCakeBlock> END_CAKE =
      BLOCKS.block("End Cake", "end_cake", EndCakeBlock::new);

  @Nonnull
  public static final BlockDefinition<LiquidBlock> WITCH_WATER =
      BLOCKS.block(
          "Witch Water", "witch_water", WitchWaterBlock::new, ItemDefinition.ItemType.CUSTOM);

  @Nonnull
  public static final BlockDefinition<LiquidBlock> SEA_WATER =
      BLOCKS.block(
          "Sea Water",
          "sea_water",
          () ->
              BlockBuilder.create()
                  .noCollision()
                  .strength(100.0F)
                  .noLootTable()
                  .buildLiquidBlock(EXNFluids.SEA_WATER::getStillFluid),
          ItemDefinition.ItemType.CUSTOM);

  @Nonnull
  public static final BlockDefinition<InfestingLeavesBlock> INFESTING_LEAVES =
      BLOCKS.block("Infesting Leaves", "infesting_leaves", InfestingLeavesBlock::new);

  @Nonnull
  public static final BlockDefinition<InfestedLeavesBlock> INFESTED_LEAVES =
      BLOCKS.block("Infested Leaves", "infested_leaves", InfestedLeavesBlock::new);

  @Nonnull
  public static final BlockDefinition<Block> UNFIRED_CRUCIBLE =
      BLOCKS.block(
          "Unfired Crucible",
          "unfired_crucible",
          () ->
              BlockBuilder.create()
                  .strength(0.6F)
                  .sound(SoundType.GRAVEL)
                  .noOcclusion()
                  .buildBlock());

  @Nonnull
  public static final BlockDefinition<CrucibleBlock> FIRED_CRUCIBLE =
      BLOCKS.block("Fired Crucible", "fired_crucible", FiredCrucibleBlock::new);

  @Nonnull
  public static final BlockDefinition<CrucibleBlock> ACACIA_CRUCIBLE =
      BLOCKS.burnableBlock("Acacia Crucible", "acacia_crucible", WoodCrucibleBlock::new);

  public static final BlockDefinition<CrucibleBlock> BAMBOO_CRUCIBLE =
      BLOCKS.burnableBlock("Bamboo Crucible", "bamboo_crucible", WoodCrucibleBlock::new);

  @Nonnull
  public static final BlockDefinition<CrucibleBlock> BIRCH_CRUCIBLE =
      BLOCKS.burnableBlock("Birch Crucible", "birch_crucible", WoodCrucibleBlock::new);

  public static final BlockDefinition<CrucibleBlock> CHERRY_CRUCIBLE =
      BLOCKS.burnableBlock("Cherry Crucible", "cherry_crucible", WoodCrucibleBlock::new);

  @Nonnull
  public static final BlockDefinition<CrucibleBlock> DARK_OAK_CRUCIBLE =
      BLOCKS.burnableBlock("Dark Oak Crucible", "dark_oak_crucible", WoodCrucibleBlock::new);

  @Nonnull
  public static final BlockDefinition<CrucibleBlock> JUNGLE_CRUCIBLE =
      BLOCKS.burnableBlock("Jungle Crucible", "jungle_crucible", WoodCrucibleBlock::new);

  @Nonnull
  public static final BlockDefinition<CrucibleBlock> MANGROVE_CRUCIBLE =
      BLOCKS.burnableBlock("Mangrove Crucible", "mangrove_crucible", WoodCrucibleBlock::new);

  @Nonnull
  public static final BlockDefinition<CrucibleBlock> OAK_CRUCIBLE =
      BLOCKS.burnableBlock("Oak Crucible", "oak_crucible", WoodCrucibleBlock::new);

  @Nonnull
  public static final BlockDefinition<CrucibleBlock> SPRUCE_CRUCIBLE =
      BLOCKS.burnableBlock("Spruce Crucible", "spruce_crucible", WoodCrucibleBlock::new);

  @Nonnull
  public static final BlockDefinition<CrucibleBlock> CRIMSON_CRUCIBLE =
      BLOCKS.block("Crimson Crucible", "crimson_crucible", NetherCrucibleBlock::new);

  @Nonnull
  public static final BlockDefinition<CrucibleBlock> WARPED_CRUCIBLE =
      BLOCKS.block("Warped Crucible", "warped_crucible", NetherCrucibleBlock::new);

  @Nonnull
  public static final BlockDefinition<BarrelBlock> ACACIA_BARREL =
      BLOCKS.burnableBlock("Acacia Barrel", "acacia_barrel", WoodBarrelBlock::new);

  public static final BlockDefinition<BarrelBlock> BAMBOO_BARREL =
      BLOCKS.burnableBlock("Bamboo Barrel", "bamboo_barrel", WoodBarrelBlock::new);

  @Nonnull
  public static final BlockDefinition<BarrelBlock> BIRCH_BARREL =
      BLOCKS.burnableBlock("Birch Barrel", "birch_barrel", WoodBarrelBlock::new);

  public static final BlockDefinition<BarrelBlock> CHERRY_BARREL =
      BLOCKS.burnableBlock("Cherry Barrel", "cherry_barrel", WoodBarrelBlock::new);

  @Nonnull
  public static final BlockDefinition<BarrelBlock> DARK_OAK_BARREL =
      BLOCKS.burnableBlock("Dark Oak Barrel", "dark_oak_barrel", WoodBarrelBlock::new);

  @Nonnull
  public static final BlockDefinition<BarrelBlock> JUNGLE_BARREL =
      BLOCKS.burnableBlock("Jungle Barrel", "jungle_barrel", WoodBarrelBlock::new);

  @Nonnull
  public static final BlockDefinition<BarrelBlock> MANGROVE_BARREL =
      BLOCKS.burnableBlock("Mangrove Barrel", "mangrove_barrel", WoodBarrelBlock::new);

  @Nonnull
  public static final BlockDefinition<BarrelBlock> OAK_BARREL =
      BLOCKS.burnableBlock("Oak Barrel", "oak_barrel", WoodBarrelBlock::new);

  @Nonnull
  public static final BlockDefinition<BarrelBlock> SPRUCE_BARREL =
      BLOCKS.burnableBlock("Spruce Barrel", "spruce_barrel", WoodBarrelBlock::new);

  @Nonnull
  public static final BlockDefinition<BarrelBlock> CRIMSON_BARREL =
      BLOCKS.block("Crimson Barrel", "crimson_barrel", NetherBarrelBlock::new);

  @Nonnull
  public static final BlockDefinition<BarrelBlock> WARPED_BARREL =
      BLOCKS.block("Warped Barrel", "warped_barrel", NetherBarrelBlock::new);

  @Nonnull
  public static final BlockDefinition<BarrelBlock> STONE_BARREL =
      BLOCKS.block("Stone Barrel", "stone_barrel", StoneBarrelBlock::new);

  @Nonnull
  public static final BlockDefinition<SieveBlock> ACACIA_SIEVE =
      BLOCKS.burnableBlock("Acacia Sieve", "acacia_sieve", WoodSieveBlock::new);

  public static final BlockDefinition<SieveBlock> BAMBOO_SIEVE =
      BLOCKS.burnableBlock("Bamboo Sieve", "bamboo_sieve", WoodSieveBlock::new);

  @Nonnull
  public static final BlockDefinition<SieveBlock> BIRCH_SIEVE =
      BLOCKS.burnableBlock("Birch Sieve", "birch_sieve", WoodSieveBlock::new);

  public static final BlockDefinition<SieveBlock> CHERRY_SIEVE =
      BLOCKS.burnableBlock("Cherry Sieve", "cherry_sieve", WoodSieveBlock::new);

  @Nonnull
  public static final BlockDefinition<SieveBlock> DARK_OAK_SIEVE =
      BLOCKS.burnableBlock("Dark Oak Sieve", "dark_oak_sieve", WoodSieveBlock::new);

  @Nonnull
  public static final BlockDefinition<SieveBlock> JUNGLE_SIEVE =
      BLOCKS.burnableBlock("Jungle Sieve", "jungle_sieve", WoodSieveBlock::new);

  @Nonnull
  public static final BlockDefinition<SieveBlock> MANGROVE_SIEVE =
      BLOCKS.burnableBlock("Mangrove Sieve", "mangrove_sieve", WoodSieveBlock::new);

  @Nonnull
  public static final BlockDefinition<SieveBlock> OAK_SIEVE =
      BLOCKS.burnableBlock("Oak Sieve", "oak_sieve", WoodSieveBlock::new);

  @Nonnull
  public static final BlockDefinition<SieveBlock> SPRUCE_SIEVE =
      BLOCKS.burnableBlock("Spruce Sieve", "spruce_sieve", WoodSieveBlock::new);

  @Nonnull
  public static final BlockDefinition<SieveBlock> CRIMSON_SIEVE =
      BLOCKS.block("Crimson Sieve", "crimson_sieve", NetherSieveBlock::new);

  @Nonnull
  public static final BlockDefinition<SieveBlock> WARPED_SIEVE =
      BLOCKS.block("Warped Sieve", "warped_sieve", NetherSieveBlock::new);

  private EXNBlocks() {}

  public static List<BlockDefinition<?>> getDefinitions() {
    return BLOCKS.getRegistry();
  }
}
