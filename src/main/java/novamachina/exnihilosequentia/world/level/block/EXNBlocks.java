package novamachina.exnihilosequentia.world.level.block;

import java.util.List;
import javax.annotation.Nonnull;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import novamachina.exnihilosequentia.common.block.*;
import novamachina.exnihilosequentia.common.block.barrels.NetherBarrelBlock;
import novamachina.exnihilosequentia.common.block.barrels.StoneBarrelBlock;
import novamachina.exnihilosequentia.common.block.barrels.WoodBarrelBlock;
import novamachina.exnihilosequentia.common.block.crucibles.FiredCrucibleBlock;
import novamachina.exnihilosequentia.common.block.crucibles.NetherCrucibleBlock;
import novamachina.exnihilosequentia.common.block.crucibles.WoodCrucibleBlock;
import novamachina.exnihilosequentia.common.block.sieves.NetherSieveBlock;
import novamachina.exnihilosequentia.common.block.sieves.WoodSieveBlock;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.world.level.material.EXNFluids;
import novamachina.novacore.registries.BlockRegistry;
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
          () ->
              BlockBuilder.create(Material.SAND)
                  .strength(0.7F)
                  .sound(SoundType.WOOL)
                  .buildFallingBlock());

  @Nonnull
  public static final BlockDefinition<Block> CRUSHED_ANDESITE =
      BLOCKS.block(
          "Crushed Andesite",
          "crushed_andesite",
          () ->
              BlockBuilder.create(Material.SAND)
                  .strength(0.7F)
                  .sound(SoundType.GRAVEL)
                  .buildFallingBlock());

  @Nonnull
  public static final BlockDefinition<Block> CRUSHED_BASALT =
      BLOCKS.block(
          "Crushed Basalt",
          "crushed_basalt",
          () ->
              BlockBuilder.create(Material.SAND)
                  .strength(0.7F)
                  .sound(SoundType.GRAVEL)
                  .buildFallingBlock());

  @Nonnull
  public static final BlockDefinition<Block> CRUSHED_BLACKSTONE =
      BLOCKS.block(
          "Crushed Blackstone",
          "crushed_blackstone",
          () ->
              BlockBuilder.create(Material.SAND)
                  .strength(0.7F)
                  .sound(SoundType.GRAVEL)
                  .buildFallingBlock());

  @Nonnull
  public static final BlockDefinition<Block> CRUSHED_CALCITE =
      BLOCKS.block(
          "Crushed Calcite",
          "crushed_calcite",
          () ->
              BlockBuilder.create(Material.SAND)
                  .strength(0.7F)
                  .sound(SoundType.GRAVEL)
                  .buildFallingBlock());

  @Nonnull
  public static final BlockDefinition<Block> CRUSHED_DEEPSLATE =
      BLOCKS.block(
          "Crushed Deepslate",
          "crushed_deepslate",
          () ->
              BlockBuilder.create(Material.SAND)
                  .strength(0.7F)
                  .sound(SoundType.GRAVEL)
                  .buildFallingBlock());

  @Nonnull
  public static final BlockDefinition<Block> CRUSHED_DIORITE =
      BLOCKS.block(
          "Crushed Diorite",
          "crushed_diorite",
          () ->
              BlockBuilder.create(Material.SAND)
                  .strength(0.7F)
                  .sound(SoundType.GRAVEL)
                  .buildFallingBlock());

  @Nonnull
  public static final BlockDefinition<Block> CRUSHED_DRIPSTONE =
      BLOCKS.block(
          "Crushed Dripstone",
          "crushed_dripstone",
          () ->
              BlockBuilder.create(Material.SAND)
                  .strength(0.7F)
                  .sound(SoundType.GRAVEL)
                  .buildFallingBlock());

  @Nonnull
  public static final BlockDefinition<Block> CRUSHED_END_STONE =
      BLOCKS.block(
          "Crushed End Stone",
          "crushed_end_stone",
          () ->
              BlockBuilder.create(Material.SAND)
                  .strength(0.7F)
                  .sound(SoundType.GRAVEL)
                  .buildFallingBlock());

  @Nonnull
  public static final BlockDefinition<Block> CRUSHED_GRANITE =
      BLOCKS.block(
          "Crushed Granite",
          "crushed_granite",
          () ->
              BlockBuilder.create(Material.SAND)
                  .strength(0.7F)
                  .sound(SoundType.GRAVEL)
                  .buildFallingBlock());

  @Nonnull
  public static final BlockDefinition<Block> CRUSHED_NETHERRACK =
      BLOCKS.block(
          "Crushed Netherrack",
          "crushed_netherrack",
          () ->
              BlockBuilder.create(Material.SAND)
                  .strength(0.7F)
                  .sound(SoundType.GRAVEL)
                  .buildFallingBlock());

  @Nonnull
  public static final BlockDefinition<Block> CRUSHED_TUFF =
      BLOCKS.block(
          "Crushed Tuff",
          "crushed_tuff",
          () ->
              BlockBuilder.create(Material.SAND)
                  .strength(0.7F)
                  .sound(SoundType.GRAVEL)
                  .buildFallingBlock());

  @Nonnull
  public static final BlockDefinition<EndCakeBlock> END_CAKE =
      BLOCKS.block("End Cake", "end_cake", EndCakeBlock::new);

  @Nonnull
  public static final BlockDefinition<LiquidBlock> WITCH_WATER =
      BLOCKS.block("Witch Water", "witch_water", WitchWaterBlock::new);

  @Nonnull
  public static final BlockDefinition<LiquidBlock> SEA_WATER =
      BLOCKS.block(
          "Sea Water",
          "sea_water",
          () ->
              BlockBuilder.create(Material.WATER)
                  .noCollision()
                  .strength(100.0F)
                  .noLootTable()
                  .buildLiquidBlock(EXNFluids.SEA_WATER::getStillFluid));

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
              BlockBuilder.create(Material.CLAY)
                  .strength(0.6F)
                  .sound(SoundType.GRAVEL)
                  .noOcclusion()
                  .buildBlock());

  @Nonnull
  public static final BlockDefinition<CrucibleBaseBlock> FIRED_CRUCIBLE =
      BLOCKS.block("Fired Crucible", "fired_crucible", FiredCrucibleBlock::new);

  @Nonnull
  public static final BlockDefinition<CrucibleBaseBlock> ACACIA_CRUCIBLE =
      BLOCKS.burnableBlock("Acacia Crucible", "acacia_crucible", WoodCrucibleBlock::new);

  @Nonnull
  public static final BlockDefinition<CrucibleBaseBlock> BIRCH_CRUCIBLE =
      BLOCKS.burnableBlock("Birch Crucible", "birch_crucible", WoodCrucibleBlock::new);

  @Nonnull
  public static final BlockDefinition<CrucibleBaseBlock> DARK_OAK_CRUCIBLE =
      BLOCKS.burnableBlock("Dark Oak Crucible", "dark_oak_crucible", WoodCrucibleBlock::new);

  @Nonnull
  public static final BlockDefinition<CrucibleBaseBlock> JUNGLE_CRUCIBLE =
      BLOCKS.burnableBlock("Jungle Crucible", "jungle_crucible", WoodCrucibleBlock::new);

  @Nonnull
  public static final BlockDefinition<CrucibleBaseBlock> MANGROVE_CRUCIBLE =
      BLOCKS.burnableBlock("Mangrove Crucible", "mangrove_crucible", WoodCrucibleBlock::new);

  @Nonnull
  public static final BlockDefinition<CrucibleBaseBlock> OAK_CRUCIBLE =
      BLOCKS.burnableBlock("Oak Crucible", "oak_crucible", WoodCrucibleBlock::new);

  @Nonnull
  public static final BlockDefinition<CrucibleBaseBlock> SPRUCE_CRUCIBLE =
      BLOCKS.burnableBlock("Spruce Crucible", "spruce_crucible", WoodCrucibleBlock::new);

  @Nonnull
  public static final BlockDefinition<CrucibleBaseBlock> CRIMSON_CRUCIBLE =
      BLOCKS.block("Crimson Crucible", "crimson_crucible", NetherCrucibleBlock::new);

  @Nonnull
  public static final BlockDefinition<CrucibleBaseBlock> WARPED_CRUCIBLE =
      BLOCKS.block("Warped Crucible", "warped_crucible", NetherCrucibleBlock::new);

  @Nonnull
  public static final BlockDefinition<BlockBarrel> ACACIA_BARREL =
      BLOCKS.burnableBlock("Acacia Barrel", "acacia_barrel", WoodBarrelBlock::new);

  @Nonnull
  public static final BlockDefinition<BlockBarrel> BIRCH_BARREL =
      BLOCKS.burnableBlock("Birch Barrel", "birch_barrel", WoodBarrelBlock::new);

  @Nonnull
  public static final BlockDefinition<BlockBarrel> DARK_OAK_BARREL =
      BLOCKS.burnableBlock("Dark Oak Barrel", "dark_oak_barrel", WoodBarrelBlock::new);

  @Nonnull
  public static final BlockDefinition<BlockBarrel> JUNGLE_BARREL =
      BLOCKS.burnableBlock("Jungle Barrel", "jungle_barrel", WoodBarrelBlock::new);

  @Nonnull
  public static final BlockDefinition<BlockBarrel> MANGROVE_BARREL =
      BLOCKS.burnableBlock("Mangrove Barrel", "mangrove_barrel", WoodBarrelBlock::new);

  @Nonnull
  public static final BlockDefinition<BlockBarrel> OAK_BARREL =
      BLOCKS.burnableBlock("Oak Barrel", "oak_barrel", WoodBarrelBlock::new);

  @Nonnull
  public static final BlockDefinition<BlockBarrel> SPRUCE_BARREL =
      BLOCKS.burnableBlock("Spruce Barrel", "spruce_barrel", WoodBarrelBlock::new);

  @Nonnull
  public static final BlockDefinition<BlockBarrel> CRIMSON_BARREL =
      BLOCKS.block("Crimson Barrel", "crimson_barrel", NetherBarrelBlock::new);

  @Nonnull
  public static final BlockDefinition<BlockBarrel> WARPED_BARREL =
      BLOCKS.block("Warped Barrel", "warped_barrel", NetherBarrelBlock::new);

  @Nonnull
  public static final BlockDefinition<BlockBarrel> STONE_BARREL =
      BLOCKS.block("Stone Barrel", "stone_barrel", StoneBarrelBlock::new);

  @Nonnull
  public static final BlockDefinition<BlockSieve> ACACIA_SIEVE =
      BLOCKS.burnableBlock("Acacia Sieve", "acacia_sieve", WoodSieveBlock::new);

  @Nonnull
  public static final BlockDefinition<BlockSieve> BIRCH_SIEVE =
      BLOCKS.burnableBlock("Birch Sieve", "birch_sieve", WoodSieveBlock::new);

  @Nonnull
  public static final BlockDefinition<BlockSieve> DARK_OAK_SIEVE =
      BLOCKS.burnableBlock("Dark Oak Sieve", "dark_oak_sieve", WoodSieveBlock::new);

  @Nonnull
  public static final BlockDefinition<BlockSieve> JUNGLE_SIEVE =
      BLOCKS.burnableBlock("Jungle Sieve", "jungle_sieve", WoodSieveBlock::new);

  @Nonnull
  public static final BlockDefinition<BlockSieve> MANGROVE_SIEVE =
      BLOCKS.burnableBlock("Mangrove Sieve", "mangrove_sieve", WoodSieveBlock::new);

  @Nonnull
  public static final BlockDefinition<BlockSieve> OAK_SIEVE =
      BLOCKS.burnableBlock("Oak Sieve", "oak_sieve", WoodSieveBlock::new);

  @Nonnull
  public static final BlockDefinition<BlockSieve> SPRUCE_SIEVE =
      BLOCKS.burnableBlock("Spruce Sieve", "spruce_sieve", WoodSieveBlock::new);

  @Nonnull
  public static final BlockDefinition<BlockSieve> CRIMSON_SIEVE =
      BLOCKS.block("Crimson Sieve", "crimson_sieve", NetherSieveBlock::new);

  @Nonnull
  public static final BlockDefinition<BlockSieve> WARPED_SIEVE =
      BLOCKS.block("Warped Sieve", "warped_sieve", NetherSieveBlock::new);

  private EXNBlocks() {}

  public static List<BlockDefinition<?>> getDefinitions() {
    return BLOCKS.getRegistry();
  }
}
