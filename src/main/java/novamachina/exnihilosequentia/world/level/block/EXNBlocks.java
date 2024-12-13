package novamachina.exnihilosequentia.world.level.block;

import java.util.List;
import javax.annotation.Nonnull;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import novamachina.exnihilosequentia.common.Config;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.world.level.material.EXNFluids;
import novamachina.novacore.NovaCore;
import novamachina.novacore.core.registries.BlockRegistry;
import novamachina.novacore.world.item.ItemDefinition;
import novamachina.novacore.world.level.block.BlockDefinition;

public class EXNBlocks {

  private static final BlockRegistry BLOCKS =
      new BlockRegistry(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, NovaCore.SERVICE_PROVIDER);

  @Nonnull
  public static final BlockDefinition<FallingBlock> DUST =
      BLOCKS.fallingBlock(
          "Dust", "dust", BlockBehaviour.Properties.of().strength(0.7F).sound(SoundType.WOOL));

  @Nonnull
  public static final BlockDefinition<FallingBlock> CRUSHED_ANDESITE =
      BLOCKS.fallingBlock(
          "Crushed Andesite",
          "crushed_andesite",
          BlockBehaviour.Properties.of().strength(0.7F).sound(SoundType.GRAVEL));

  @Nonnull
  public static final BlockDefinition<FallingBlock> CRUSHED_BASALT =
      BLOCKS.fallingBlock(
          "Crushed Basalt",
          "crushed_basalt",
          BlockBehaviour.Properties.of().strength(0.7F).sound(SoundType.GRAVEL));

  @Nonnull
  public static final BlockDefinition<FallingBlock> CRUSHED_BLACKSTONE =
      BLOCKS.fallingBlock(
          "Crushed Blackstone",
          "crushed_blackstone",
          BlockBehaviour.Properties.of().strength(0.7F).sound(SoundType.GRAVEL));

  @Nonnull
  public static final BlockDefinition<FallingBlock> CRUSHED_CALCITE =
      BLOCKS.fallingBlock(
          "Crushed Calcite",
          "crushed_calcite",
          BlockBehaviour.Properties.of().strength(0.7F).sound(SoundType.GRAVEL));

  @Nonnull
  public static final BlockDefinition<FallingBlock> CRUSHED_DEEPSLATE =
      BLOCKS.fallingBlock(
          "Crushed Deepslate",
          "crushed_deepslate",
          BlockBehaviour.Properties.of().strength(0.7F).sound(SoundType.GRAVEL));

  @Nonnull
  public static final BlockDefinition<FallingBlock> CRUSHED_DIORITE =
      BLOCKS.fallingBlock(
          "Crushed Diorite",
          "crushed_diorite",
          BlockBehaviour.Properties.of().strength(0.7F).sound(SoundType.GRAVEL));

  @Nonnull
  public static final BlockDefinition<FallingBlock> CRUSHED_DRIPSTONE =
      BLOCKS.fallingBlock(
          "Crushed Dripstone",
          "crushed_dripstone",
          BlockBehaviour.Properties.of().strength(0.7F).sound(SoundType.GRAVEL));

  @Nonnull
  public static final BlockDefinition<FallingBlock> CRUSHED_END_STONE =
      BLOCKS.fallingBlock(
          "Crushed End Stone",
          "crushed_end_stone",
          BlockBehaviour.Properties.of().strength(0.7F).sound(SoundType.GRAVEL));

  @Nonnull
  public static final BlockDefinition<FallingBlock> CRUSHED_GRANITE =
      BLOCKS.fallingBlock(
          "Crushed Granite",
          "crushed_granite",
          BlockBehaviour.Properties.of().strength(0.7F).sound(SoundType.GRAVEL));

  @Nonnull
  public static final BlockDefinition<FallingBlock> CRUSHED_NETHERRACK =
      BLOCKS.fallingBlock(
          "Crushed Netherrack",
          "crushed_netherrack",
          BlockBehaviour.Properties.of().strength(0.7F).sound(SoundType.GRAVEL));

  @Nonnull
  public static final BlockDefinition<FallingBlock> CRUSHED_TUFF =
      BLOCKS.fallingBlock(
          "Crushed Tuff",
          "crushed_tuff",
          BlockBehaviour.Properties.of().strength(0.7F).sound(SoundType.GRAVEL));

  @Nonnull
  public static final BlockDefinition<EndCakeBlock> END_CAKE =
      BLOCKS.block(
          "End Cake",
          "end_cake",
          BlockBehaviour.Properties.of().strength(0.5F).sound(SoundType.WOOL),
          EndCakeBlock::new);

  @Nonnull
  public static final BlockDefinition<LiquidBlock> WITCH_WATER =
      BLOCKS.block(
          "Witch Water",
          "witch_water",
          BlockBehaviour.Properties.of().noCollission().strength(100.0F).noLootTable(),
          WitchWaterBlock::new,
          ItemDefinition.ItemType.CUSTOM);

  @Nonnull
  public static final BlockDefinition<LiquidBlock> SEA_WATER =
      BLOCKS.liquidBlock(
          "Sea Water",
          "sea_water",
          BlockBehaviour.Properties.of()
              .noCollission()
              .strength(100.0F)
              .noLootTable()
              .setId(BLOCKS.key("sea_water")),
          EXNFluids.SEA_WATER.getStillFluid(),
          ItemDefinition.ItemType.CUSTOM);

  @Nonnull
  public static final BlockDefinition<InfestingLeavesBlock> INFESTING_LEAVES =
      BLOCKS.block(
          "Infesting Leaves",
          "infesting_leaves",
          BlockBehaviour.Properties.of()
              .strength(0.2F)
              .sound(SoundType.GRASS)
              .noOcclusion()
              .isValidSpawn((blockState, blockGetter, blockPos, entityType) -> false),
          InfestingLeavesBlock::new);

  @Nonnull
  public static final BlockDefinition<InfestedLeavesBlock> INFESTED_LEAVES =
      BLOCKS.block(
          "Infested Leaves",
          "infested_leaves",
          BlockBehaviour.Properties.of()
              .strength(0.2F)
              .sound(SoundType.GRASS)
              .noOcclusion()
              .isValidSpawn((blockState, blockGetter, blockPos, entityType) -> false),
          InfestedLeavesBlock::new);

  @Nonnull
  public static final BlockDefinition<Block> UNFIRED_CRUCIBLE =
      BLOCKS.block(
          "Unfired Crucible",
          "unfired_crucible",
          BlockBehaviour.Properties.of().strength(0.6F).sound(SoundType.GRAVEL).noOcclusion());

  @Nonnull
  public static final BlockDefinition<CrucibleBlock> FIRED_CRUCIBLE =
      BLOCKS.block(
          "Fired Crucible",
          "fired_crucible",
          BlockBehaviour.Properties.of().strength(1.5F).sound(SoundType.STONE).noOcclusion(),
          FiredCrucibleBlock::new);

  @Nonnull
  public static final BlockDefinition<CrucibleBlock> ACACIA_CRUCIBLE =
      BLOCKS.burnableBlock(
          "Acacia Crucible",
          "acacia_crucible",
          BlockBehaviour.Properties.of().strength(.75F).sound(SoundType.WOOD).noOcclusion(),
          WoodCrucibleBlock::new);

  public static final BlockDefinition<CrucibleBlock> BAMBOO_CRUCIBLE =
      BLOCKS.burnableBlock(
          "Bamboo Crucible",
          "bamboo_crucible",
          BlockBehaviour.Properties.of().strength(.75F).sound(SoundType.WOOD).noOcclusion(),
          WoodCrucibleBlock::new);

  @Nonnull
  public static final BlockDefinition<CrucibleBlock> BIRCH_CRUCIBLE =
      BLOCKS.burnableBlock(
          "Birch Crucible",
          "birch_crucible",
          BlockBehaviour.Properties.of().strength(.75F).sound(SoundType.WOOD).noOcclusion(),
          WoodCrucibleBlock::new);

  public static final BlockDefinition<CrucibleBlock> CHERRY_CRUCIBLE =
      BLOCKS.burnableBlock(
          "Cherry Crucible",
          "cherry_crucible",
          BlockBehaviour.Properties.of().strength(.75F).sound(SoundType.WOOD).noOcclusion(),
          WoodCrucibleBlock::new);

  @Nonnull
  public static final BlockDefinition<CrucibleBlock> DARK_OAK_CRUCIBLE =
      BLOCKS.burnableBlock(
          "Dark Oak Crucible",
          "dark_oak_crucible",
          BlockBehaviour.Properties.of().strength(.75F).sound(SoundType.WOOD).noOcclusion(),
          WoodCrucibleBlock::new);

  @Nonnull
  public static final BlockDefinition<CrucibleBlock> JUNGLE_CRUCIBLE =
      BLOCKS.burnableBlock(
          "Jungle Crucible",
          "jungle_crucible",
          BlockBehaviour.Properties.of().strength(.75F).sound(SoundType.WOOD).noOcclusion(),
          WoodCrucibleBlock::new);

  @Nonnull
  public static final BlockDefinition<CrucibleBlock> MANGROVE_CRUCIBLE =
      BLOCKS.burnableBlock(
          "Mangrove Crucible",
          "mangrove_crucible",
          BlockBehaviour.Properties.of().strength(.75F).sound(SoundType.WOOD).noOcclusion(),
          WoodCrucibleBlock::new);

  @Nonnull
  public static final BlockDefinition<CrucibleBlock> OAK_CRUCIBLE =
      BLOCKS.burnableBlock(
          "Oak Crucible",
          "oak_crucible",
          BlockBehaviour.Properties.of().strength(.75F).sound(SoundType.WOOD).noOcclusion(),
          WoodCrucibleBlock::new);

  @Nonnull
  public static final BlockDefinition<CrucibleBlock> SPRUCE_CRUCIBLE =
      BLOCKS.burnableBlock(
          "Spruce Crucible",
          "spruce_crucible",
          BlockBehaviour.Properties.of().strength(.75F).sound(SoundType.WOOD).noOcclusion(),
          WoodCrucibleBlock::new);

  @Nonnull
  public static final BlockDefinition<CrucibleBlock> CRIMSON_CRUCIBLE =
      BLOCKS.block(
          "Crimson Crucible",
          "crimson_crucible",
          BlockBehaviour.Properties.of()
              .strength(1.0F)
              .sound(Config.getNetherCrucibleSoundsEnabled() ? SoundType.STEM : SoundType.WOOD)
              .noOcclusion(),
          NetherCrucibleBlock::new);

  @Nonnull
  public static final BlockDefinition<CrucibleBlock> WARPED_CRUCIBLE =
      BLOCKS.block(
          "Warped Crucible",
          "warped_crucible",
          BlockBehaviour.Properties.of()
              .strength(1.0F)
              .sound(Config.getNetherCrucibleSoundsEnabled() ? SoundType.STEM : SoundType.WOOD)
              .noOcclusion(),
          NetherCrucibleBlock::new);

  @Nonnull
  public static final BlockDefinition<BarrelBlock> ACACIA_BARREL =
      BLOCKS.burnableBlock(
          "Acacia Barrel",
          "acacia_barrel",
          BlockBehaviour.Properties.of().strength(0.75F).sound(SoundType.WOOD).noOcclusion(),
          WoodBarrelBlock::new);

  public static final BlockDefinition<BarrelBlock> BAMBOO_BARREL =
      BLOCKS.burnableBlock(
          "Bamboo Barrel",
          "bamboo_barrel",
          BlockBehaviour.Properties.of().strength(0.75F).sound(SoundType.WOOD).noOcclusion(),
          WoodBarrelBlock::new);

  @Nonnull
  public static final BlockDefinition<BarrelBlock> BIRCH_BARREL =
      BLOCKS.burnableBlock(
          "Birch Barrel",
          "birch_barrel",
          BlockBehaviour.Properties.of().strength(0.75F).sound(SoundType.WOOD).noOcclusion(),
          WoodBarrelBlock::new);

  public static final BlockDefinition<BarrelBlock> CHERRY_BARREL =
      BLOCKS.burnableBlock(
          "Cherry Barrel",
          "cherry_barrel",
          BlockBehaviour.Properties.of().strength(0.75F).sound(SoundType.WOOD).noOcclusion(),
          WoodBarrelBlock::new);

  @Nonnull
  public static final BlockDefinition<BarrelBlock> DARK_OAK_BARREL =
      BLOCKS.burnableBlock(
          "Dark Oak Barrel",
          "dark_oak_barrel",
          BlockBehaviour.Properties.of().strength(0.75F).sound(SoundType.WOOD).noOcclusion(),
          WoodBarrelBlock::new);

  @Nonnull
  public static final BlockDefinition<BarrelBlock> JUNGLE_BARREL =
      BLOCKS.burnableBlock(
          "Jungle Barrel",
          "jungle_barrel",
          BlockBehaviour.Properties.of().strength(0.75F).sound(SoundType.WOOD).noOcclusion(),
          WoodBarrelBlock::new);

  @Nonnull
  public static final BlockDefinition<BarrelBlock> MANGROVE_BARREL =
      BLOCKS.burnableBlock(
          "Mangrove Barrel",
          "mangrove_barrel",
          BlockBehaviour.Properties.of().strength(0.75F).sound(SoundType.WOOD).noOcclusion(),
          WoodBarrelBlock::new);

  @Nonnull
  public static final BlockDefinition<BarrelBlock> OAK_BARREL =
      BLOCKS.burnableBlock(
          "Oak Barrel",
          "oak_barrel",
          BlockBehaviour.Properties.of().strength(0.75F).sound(SoundType.WOOD).noOcclusion(),
          WoodBarrelBlock::new);

  @Nonnull
  public static final BlockDefinition<BarrelBlock> SPRUCE_BARREL =
      BLOCKS.burnableBlock(
          "Spruce Barrel",
          "spruce_barrel",
          BlockBehaviour.Properties.of().strength(0.75F).sound(SoundType.WOOD).noOcclusion(),
          WoodBarrelBlock::new);

  @Nonnull
  public static final BlockDefinition<BarrelBlock> CRIMSON_BARREL =
      BLOCKS.block(
          "Crimson Barrel",
          "crimson_barrel",
          BlockBehaviour.Properties.of()
              .strength(1.0F)
              .sound(Config.getNetherBarrelSoundsEnabled() ? SoundType.STEM : SoundType.WOOD)
              .noOcclusion(),
          NetherBarrelBlock::new);

  @Nonnull
  public static final BlockDefinition<BarrelBlock> WARPED_BARREL =
      BLOCKS.block(
          "Warped Barrel",
          "warped_barrel",
          BlockBehaviour.Properties.of()
              .strength(1.0F)
              .sound(Config.getNetherBarrelSoundsEnabled() ? SoundType.STEM : SoundType.WOOD)
              .noOcclusion(),
          NetherBarrelBlock::new);

  @Nonnull
  public static final BlockDefinition<BarrelBlock> STONE_BARREL =
      BLOCKS.block(
          "Stone Barrel",
          "stone_barrel",
          BlockBehaviour.Properties.of().strength(0.75F).sound(SoundType.STONE).noOcclusion(),
          StoneBarrelBlock::new);

  @Nonnull
  public static final BlockDefinition<SieveBlock> ACACIA_SIEVE =
      BLOCKS.burnableBlock(
          "Acacia Sieve",
          "acacia_sieve",
          BlockBehaviour.Properties.of()
              .strength(0.7F)
              .sound(SoundType.SCAFFOLDING)
              .noOcclusion()
              .isRedstoneConductor((state, reader, pos) -> false)
              .isSuffocating((state, reader, pos) -> false)
              .isViewBlocking((state, reader, pos) -> false),
          WoodSieveBlock::new);

  public static final BlockDefinition<SieveBlock> BAMBOO_SIEVE =
      BLOCKS.burnableBlock(
          "Bamboo Sieve",
          "bamboo_sieve",
          BlockBehaviour.Properties.of()
              .strength(0.7F)
              .sound(SoundType.SCAFFOLDING)
              .noOcclusion()
              .isRedstoneConductor((state, reader, pos) -> false)
              .isSuffocating((state, reader, pos) -> false)
              .isViewBlocking((state, reader, pos) -> false),
          WoodSieveBlock::new);

  @Nonnull
  public static final BlockDefinition<SieveBlock> BIRCH_SIEVE =
      BLOCKS.burnableBlock(
          "Birch Sieve",
          "birch_sieve",
          BlockBehaviour.Properties.of()
              .strength(0.7F)
              .sound(SoundType.SCAFFOLDING)
              .noOcclusion()
              .isRedstoneConductor((state, reader, pos) -> false)
              .isSuffocating((state, reader, pos) -> false)
              .isViewBlocking((state, reader, pos) -> false),
          WoodSieveBlock::new);

  public static final BlockDefinition<SieveBlock> CHERRY_SIEVE =
      BLOCKS.burnableBlock(
          "Cherry Sieve",
          "cherry_sieve",
          BlockBehaviour.Properties.of()
              .strength(0.7F)
              .sound(SoundType.SCAFFOLDING)
              .noOcclusion()
              .isRedstoneConductor((state, reader, pos) -> false)
              .isSuffocating((state, reader, pos) -> false)
              .isViewBlocking((state, reader, pos) -> false),
          WoodSieveBlock::new);

  @Nonnull
  public static final BlockDefinition<SieveBlock> DARK_OAK_SIEVE =
      BLOCKS.burnableBlock(
          "Dark Oak Sieve",
          "dark_oak_sieve",
          BlockBehaviour.Properties.of()
              .strength(0.7F)
              .sound(SoundType.SCAFFOLDING)
              .noOcclusion()
              .isRedstoneConductor((state, reader, pos) -> false)
              .isSuffocating((state, reader, pos) -> false)
              .isViewBlocking((state, reader, pos) -> false),
          WoodSieveBlock::new);

  @Nonnull
  public static final BlockDefinition<SieveBlock> JUNGLE_SIEVE =
      BLOCKS.burnableBlock(
          "Jungle Sieve",
          "jungle_sieve",
          BlockBehaviour.Properties.of()
              .strength(0.7F)
              .sound(SoundType.SCAFFOLDING)
              .noOcclusion()
              .isRedstoneConductor((state, reader, pos) -> false)
              .isSuffocating((state, reader, pos) -> false)
              .isViewBlocking((state, reader, pos) -> false),
          WoodSieveBlock::new);

  @Nonnull
  public static final BlockDefinition<SieveBlock> MANGROVE_SIEVE =
      BLOCKS.burnableBlock(
          "Mangrove Sieve",
          "mangrove_sieve",
          BlockBehaviour.Properties.of()
              .strength(0.7F)
              .sound(SoundType.SCAFFOLDING)
              .noOcclusion()
              .isRedstoneConductor((state, reader, pos) -> false)
              .isSuffocating((state, reader, pos) -> false)
              .isViewBlocking((state, reader, pos) -> false),
          WoodSieveBlock::new);

  @Nonnull
  public static final BlockDefinition<SieveBlock> OAK_SIEVE =
      BLOCKS.burnableBlock(
          "Oak Sieve",
          "oak_sieve",
          BlockBehaviour.Properties.of()
              .strength(0.7F)
              .sound(SoundType.SCAFFOLDING)
              .noOcclusion()
              .isRedstoneConductor((state, reader, pos) -> false)
              .isSuffocating((state, reader, pos) -> false)
              .isViewBlocking((state, reader, pos) -> false),
          WoodSieveBlock::new);

  @Nonnull
  public static final BlockDefinition<SieveBlock> SPRUCE_SIEVE =
      BLOCKS.burnableBlock(
          "Spruce Sieve",
          "spruce_sieve",
          BlockBehaviour.Properties.of()
              .strength(0.7F)
              .sound(SoundType.SCAFFOLDING)
              .noOcclusion()
              .isRedstoneConductor((state, reader, pos) -> false)
              .isSuffocating((state, reader, pos) -> false)
              .isViewBlocking((state, reader, pos) -> false),
          WoodSieveBlock::new);

  @Nonnull
  public static final BlockDefinition<SieveBlock> CRIMSON_SIEVE =
      BLOCKS.block(
          "Crimson Sieve",
          "crimson_sieve",
          BlockBehaviour.Properties.of()
              .strength(1.0F)
              .sound(Config.getNetherSieveSoundsEnabled() ? SoundType.STEM : SoundType.SCAFFOLDING)
              .noOcclusion()
              .isRedstoneConductor((state, reader, pos) -> false)
              .isSuffocating((state, reader, pos) -> false)
              .isViewBlocking((state, reader, pos) -> false),
          NetherSieveBlock::new);

  @Nonnull
  public static final BlockDefinition<SieveBlock> WARPED_SIEVE =
      BLOCKS.block(
          "Warped Sieve",
          "warped_sieve",
          BlockBehaviour.Properties.of()
              .strength(1.0F)
              .sound(Config.getNetherSieveSoundsEnabled() ? SoundType.STEM : SoundType.SCAFFOLDING)
              .noOcclusion()
              .isRedstoneConductor((state, reader, pos) -> false)
              .isSuffocating((state, reader, pos) -> false)
              .isViewBlocking((state, reader, pos) -> false),
          NetherSieveBlock::new);

  private EXNBlocks() {}

  public static List<BlockDefinition<?>> getDefinitions() {
    return BLOCKS.getRegistry();
  }
}
