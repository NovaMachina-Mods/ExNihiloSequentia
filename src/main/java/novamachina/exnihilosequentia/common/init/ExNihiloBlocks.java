package novamachina.exnihilosequentia.common.init;

import com.mojang.logging.LogUtils;
import javax.annotation.Nonnull;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import novamachina.exnihilosequentia.common.block.BaseBlock;
import novamachina.exnihilosequentia.common.block.BaseFallingBlock;
import novamachina.exnihilosequentia.common.block.EndCakeBlock;
import novamachina.exnihilosequentia.common.block.InfestedLeavesBlock;
import novamachina.exnihilosequentia.common.block.InfestingLeavesBlock;
import novamachina.exnihilosequentia.common.block.SeaWaterBlock;
import novamachina.exnihilosequentia.common.block.WitchWaterBlock;
import novamachina.exnihilosequentia.common.block.barrels.NetherBarrelBlock;
import novamachina.exnihilosequentia.common.block.barrels.StoneBarrelBlock;
import novamachina.exnihilosequentia.common.block.barrels.WoodBarrelBlock;
import novamachina.exnihilosequentia.common.block.crucibles.FiredCrucibleBlock;
import novamachina.exnihilosequentia.common.block.crucibles.NetherCrucibleBlock;
import novamachina.exnihilosequentia.common.block.crucibles.UnfiredCrucibleBlock;
import novamachina.exnihilosequentia.common.block.crucibles.WoodCrucibleBlock;
import novamachina.exnihilosequentia.common.block.sieves.NetherSieveBlock;
import novamachina.exnihilosequentia.common.block.sieves.WoodSieveBlock;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants.Blocks;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;

public class ExNihiloBlocks {

  @Nonnull
  private static final DeferredRegister<Block> BLOCKS =
      DeferredRegister.create(ForgeRegistries.BLOCKS, ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA);

  @Nonnull
  public static final RegistryObject<BaseFallingBlock> DUST =
      BLOCKS.register(
          ExNihiloConstants.Blocks.DUST,
          () ->
              new BaseFallingBlock(
                  new BlockBuilder()
                      .properties(
                          BlockBehaviour.Properties.of(Material.SAND)
                              .strength(0.7F)
                              .sound(SoundType.WOOL))));

  @Nonnull
  public static final RegistryObject<BaseFallingBlock> CRUSHED_NETHERRACK =
      BLOCKS.register(
          ExNihiloConstants.Blocks.CRUSHED_NETHERRACK,
          () ->
              new BaseFallingBlock(
                  new BlockBuilder()
                      .properties(
                          BlockBehaviour.Properties.of(Material.SAND)
                              .strength(0.7F)
                              .sound(SoundType.GRAVEL))));

  @Nonnull
  public static final RegistryObject<BaseFallingBlock> CRUSHED_END_STONE =
      BLOCKS.register(
          ExNihiloConstants.Blocks.CRUSHED_END_STONE,
          () ->
              new BaseFallingBlock(
                  new BlockBuilder()
                      .properties(
                          BlockBehaviour.Properties.of(Material.SAND)
                              .strength(0.7F)
                              .sound(SoundType.GRAVEL))));

  @Nonnull
  public static final RegistryObject<BaseFallingBlock> CRUSHED_ANDESITE =
      BLOCKS.register(
          ExNihiloConstants.Blocks.CRUSHED_ANDESITE,
          () ->
              new BaseFallingBlock(
                  new BlockBuilder()
                      .properties(
                          BlockBehaviour.Properties.of(Material.SAND)
                              .strength(0.7F)
                              .sound(SoundType.GRAVEL))));

  @Nonnull
  public static final RegistryObject<BaseFallingBlock> CRUSHED_DIORITE =
      BLOCKS.register(
          ExNihiloConstants.Blocks.CRUSHED_DIORITE,
          () ->
              new BaseFallingBlock(
                  new BlockBuilder()
                      .properties(
                          BlockBehaviour.Properties.of(Material.SAND)
                              .strength(0.7F)
                              .sound(SoundType.GRAVEL))));

  @Nonnull
  public static final RegistryObject<BaseFallingBlock> CRUSHED_GRANITE =
      BLOCKS.register(
          ExNihiloConstants.Blocks.CRUSHED_GRANITE,
          () ->
              new BaseFallingBlock(
                  new BlockBuilder()
                      .properties(
                          BlockBehaviour.Properties.of(Material.SAND)
                              .strength(0.7F)
                              .sound(SoundType.GRAVEL))));

  @Nonnull
  public static final RegistryObject<EndCakeBlock> END_CAKE =
      BLOCKS.register(ExNihiloConstants.Blocks.END_CAKE, EndCakeBlock::new);

  @Nonnull
  public static final RegistryObject<LiquidBlock> WITCH_WATER =
      BLOCKS.register(ExNihiloConstants.Blocks.WITCH_WATER, WitchWaterBlock::new);

  @Nonnull
  public static final RegistryObject<LiquidBlock> SEA_WATER =
      BLOCKS.register(ExNihiloConstants.Blocks.SEA_WATER, SeaWaterBlock::new);

  @Nonnull
  public static final RegistryObject<BaseBlock> INFESTING_LEAVES =
      BLOCKS.register(Blocks.INFESTING_LEAVES, InfestingLeavesBlock::new);

  @Nonnull
  public static final RegistryObject<BaseBlock> INFESTED_LEAVES =
      BLOCKS.register(Blocks.INFESTED_LEAVES, InfestedLeavesBlock::new);

  @Nonnull
  public static final RegistryObject<BaseBlock> CRUCIBLE_UNFIRED =
      BLOCKS.register(Blocks.UNFIRED_CRUCIBLE, UnfiredCrucibleBlock::new);

  @Nonnull
  public static final RegistryObject<BaseBlock> CRUCIBLE_FIRED =
      BLOCKS.register(Blocks.FIRED_CRUCIBLE, FiredCrucibleBlock::new);

  @Nonnull
  public static final RegistryObject<BaseBlock> CRUCIBLE_ACACIA =
      BLOCKS.register(Blocks.ACACIA_CRUCIBLE, WoodCrucibleBlock::new);

  @Nonnull
  public static final RegistryObject<BaseBlock> CRUCIBLE_BIRCH =
      BLOCKS.register(Blocks.BIRCH_CRUCIBLE, WoodCrucibleBlock::new);

  @Nonnull
  public static final RegistryObject<BaseBlock> CRUCIBLE_DARK_OAK =
      BLOCKS.register(Blocks.DARK_OAK_CRUCIBLE, WoodCrucibleBlock::new);

  @Nonnull
  public static final RegistryObject<BaseBlock> CRUCIBLE_JUNGLE =
      BLOCKS.register(Blocks.JUNGLE_CRUCIBLE, WoodCrucibleBlock::new);

  @Nonnull
  public static final RegistryObject<BaseBlock> CRUCIBLE_OAK =
      BLOCKS.register(Blocks.OAK_CRUCIBLE, WoodCrucibleBlock::new);

  @Nonnull
  public static final RegistryObject<BaseBlock> CRUCIBLE_SPRUCE =
      BLOCKS.register(Blocks.SPRUCE_CRUCIBLE, WoodCrucibleBlock::new);

  @Nonnull
  public static final RegistryObject<BaseBlock> CRUCIBLE_CRIMSON =
      BLOCKS.register(Blocks.CRIMSON_CRUCIBLE, NetherCrucibleBlock::new);

  @Nonnull
  public static final RegistryObject<BaseBlock> CRUCIBLE_WARPED =
      BLOCKS.register(Blocks.WARPED_CRUCIBLE, NetherCrucibleBlock::new);

  @Nonnull
  public static final RegistryObject<BaseBlock> BARREL_ACACIA =
      BLOCKS.register(Blocks.ACACIA_BARREL, WoodBarrelBlock::new);

  @Nonnull
  public static final RegistryObject<BaseBlock> BARREL_BIRCH =
      BLOCKS.register(Blocks.BIRCH_BARREL, WoodBarrelBlock::new);

  @Nonnull
  public static final RegistryObject<BaseBlock> BARREL_DARK_OAK =
      BLOCKS.register(Blocks.DARK_OAK_BARREL, WoodBarrelBlock::new);

  @Nonnull
  public static final RegistryObject<BaseBlock> BARREL_JUNGLE =
      BLOCKS.register(Blocks.JUNGLE_BARREL, WoodBarrelBlock::new);

  @Nonnull
  public static final RegistryObject<BaseBlock> BARREL_OAK =
      BLOCKS.register(Blocks.OAK_BARREL, WoodBarrelBlock::new);

  @Nonnull
  public static final RegistryObject<BaseBlock> BARREL_SPRUCE =
      BLOCKS.register(Blocks.SPRUCE_BARREL, WoodBarrelBlock::new);

  @Nonnull
  public static final RegistryObject<BaseBlock> BARREL_CRIMSON =
      BLOCKS.register(Blocks.CRIMSON_BARREL, NetherBarrelBlock::new);

  @Nonnull
  public static final RegistryObject<BaseBlock> BARREL_WARPED =
      BLOCKS.register(Blocks.WARPED_BARREL, NetherBarrelBlock::new);

  @Nonnull
  public static final RegistryObject<BaseBlock> BARREL_STONE =
      BLOCKS.register(
          Blocks.STONE_BARREL,
          () ->
              new StoneBarrelBlock(
                  new BlockBuilder()
                      .properties(
                          BlockBehaviour.Properties.of(Material.STONE)
                              .strength(0.75F)
                              .sound(SoundType.STONE))));

  @Nonnull
  public static final RegistryObject<WoodSieveBlock> SIEVE_ACACIA =
      BLOCKS.register(Blocks.ACACIA_SIEVE, WoodSieveBlock::new);

  @Nonnull
  public static final RegistryObject<WoodSieveBlock> SIEVE_BIRCH =
      BLOCKS.register(Blocks.BIRCH_SIEVE, WoodSieveBlock::new);

  @Nonnull
  public static final RegistryObject<WoodSieveBlock> SIEVE_DARK_OAK =
      BLOCKS.register(Blocks.DARK_OAK_SIEVE, WoodSieveBlock::new);

  @Nonnull
  public static final RegistryObject<WoodSieveBlock> SIEVE_JUNGLE =
      BLOCKS.register(Blocks.JUNGLE_SIEVE, WoodSieveBlock::new);

  @Nonnull
  public static final RegistryObject<WoodSieveBlock> SIEVE_OAK =
      BLOCKS.register(Blocks.OAK_SIEVE, WoodSieveBlock::new);

  @Nonnull
  public static final RegistryObject<WoodSieveBlock> SIEVE_SPRUCE =
      BLOCKS.register(Blocks.SPRUCE_SIEVE, WoodSieveBlock::new);

  @Nonnull
  public static final RegistryObject<NetherSieveBlock> SIEVE_CRIMSON =
      BLOCKS.register(Blocks.CRIMSON_SIEVE, NetherSieveBlock::new);

  @Nonnull
  public static final RegistryObject<NetherSieveBlock> SIEVE_WARPED =
      BLOCKS.register(Blocks.WARPED_SIEVE, NetherSieveBlock::new);

  @Nonnull private static final ExNihiloLogger logger = new ExNihiloLogger(LogUtils.getLogger());

  private ExNihiloBlocks() {}

  public static void init(@Nonnull final IEventBus modEventBus) {
    logger.debug("Register blocks");
    BLOCKS.register(modEventBus);
  }
}
