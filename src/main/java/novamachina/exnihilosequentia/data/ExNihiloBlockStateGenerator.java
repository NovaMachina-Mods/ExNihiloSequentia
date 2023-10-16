package novamachina.exnihilosequentia.data;

import javax.annotation.Nonnull;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;
import novamachina.exnihilosequentia.world.level.material.EXNFluids;
import novamachina.exnihilosequentia.world.level.material.SeaWaterFluidType;
import novamachina.exnihilosequentia.world.level.material.WitchWaterFluidType;

public class ExNihiloBlockStateGenerator extends AbstractBlockStateGenerator {

  public ExNihiloBlockStateGenerator(
      @Nonnull final PackOutput output, @Nonnull final ExistingFileHelper exFileHelper) {
    super(output, ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, exFileHelper);
  }

  @Override
  protected void registerStatesAndModels() {
    registerSimpleBlocks();
    registerSieves();
    registerBarrels();
    registerCrucibles();
    registerCakes();
    registerFluids();
  }

  private void registerCakes() {
    createCake(EXNBlocks.END_CAKE.block());
  }

  private void registerBarrels() {
    createBarrel(
        EXNBlocks.STONE_BARREL.block(),
        exnihiloLoc("block/stone_barrel"),
        vanillaLoc("block/stone"));
    createBarrel(
        EXNBlocks.ACACIA_BARREL.block(),
        exnihiloLoc("block/acacia_barrel"),
        vanillaLoc("block/acacia_planks"));
    createBarrel(
        EXNBlocks.BAMBOO_BARREL.block(),
        exnihiloLoc("block/bamboo_barrel"),
        vanillaLoc("block/bamboo_planks"));
    createBarrel(
        EXNBlocks.BIRCH_BARREL.block(),
        exnihiloLoc("block/birch_barrel"),
        vanillaLoc("block/birch_planks"));
    createBarrel(
        EXNBlocks.CHERRY_BARREL.block(),
        exnihiloLoc("block/cherry_barrel"),
        vanillaLoc("block/cherry_planks"));
    createBarrel(
        EXNBlocks.DARK_OAK_BARREL.block(),
        exnihiloLoc("block/dark_oak_barrel"),
        vanillaLoc("block/dark_oak_planks"));
    createBarrel(
        EXNBlocks.JUNGLE_BARREL.block(),
        exnihiloLoc("block/jungle_barrel"),
        vanillaLoc("block/jungle_planks"));
    createBarrel(
        EXNBlocks.MANGROVE_BARREL.block(),
        exnihiloLoc("block/mangrove_barrel"),
        vanillaLoc("block/mangrove_planks"));
    createBarrel(
        EXNBlocks.OAK_BARREL.block(),
        exnihiloLoc("block/oak_barrel"),
        vanillaLoc("block/oak_planks"));
    createBarrel(
        EXNBlocks.SPRUCE_BARREL.block(),
        exnihiloLoc("block/spruce_barrel"),
        vanillaLoc("block/spruce_planks"));
    createBarrel(
        EXNBlocks.CRIMSON_BARREL.block(),
        exnihiloLoc("block/crimson_barrel"),
        vanillaLoc("block/crimson_planks"));
    createBarrel(
        EXNBlocks.WARPED_BARREL.block(),
        exnihiloLoc("block/warped_barrel"),
        vanillaLoc("block/warped_planks"));
  }

  private void registerCrucibles() {
    createCrucible(
        EXNBlocks.FIRED_CRUCIBLE.block(),
        exnihiloLoc("block/fired_crucible"),
        exnihiloLoc("block/fired_crucible"));
    createCrucible(
        EXNBlocks.UNFIRED_CRUCIBLE.block(),
        exnihiloLoc("block/unfired_crucible"),
        exnihiloLoc("block/unfired_crucible"));
    createCrucible(
        EXNBlocks.ACACIA_CRUCIBLE.block(),
        exnihiloLoc("block/acacia_crucible"),
        vanillaLoc("block/acacia_planks"));
    createCrucible(
        EXNBlocks.BAMBOO_CRUCIBLE.block(),
        exnihiloLoc("block/bamboo_crucible"),
        vanillaLoc("block/bamboo_planks"));
    createCrucible(
        EXNBlocks.BIRCH_CRUCIBLE.block(),
        exnihiloLoc("block/birch_crucible"),
        vanillaLoc("block/birch_planks"));
    createCrucible(
        EXNBlocks.CHERRY_CRUCIBLE.block(),
        exnihiloLoc("block/cherry_crucible"),
        vanillaLoc("block/cherry_planks"));
    createCrucible(
        EXNBlocks.DARK_OAK_CRUCIBLE.block(),
        exnihiloLoc("block/dark_oak_crucible"),
        vanillaLoc("block/dark_oak_planks"));
    createCrucible(
        EXNBlocks.JUNGLE_CRUCIBLE.block(),
        exnihiloLoc("block/jungle_crucible"),
        vanillaLoc("block/jungle_planks"));
    createCrucible(
        EXNBlocks.MANGROVE_CRUCIBLE.block(),
        exnihiloLoc("block/mangrove_crucible"),
        vanillaLoc("block/mangrove_planks"));
    createCrucible(
        EXNBlocks.OAK_CRUCIBLE.block(),
        exnihiloLoc("block/oak_crucible"),
        vanillaLoc("block/oak_planks"));
    createCrucible(
        EXNBlocks.SPRUCE_CRUCIBLE.block(),
        exnihiloLoc("block/spruce_crucible"),
        vanillaLoc("block/spruce_planks"));
    createCrucible(
        EXNBlocks.CRIMSON_CRUCIBLE.block(),
        exnihiloLoc("block/crimson_crucible"),
        vanillaLoc("block/crimson_planks"));
    createCrucible(
        EXNBlocks.WARPED_CRUCIBLE.block(),
        exnihiloLoc("block/warped_crucible"),
        vanillaLoc("block/warped_planks"));
  }

  private void registerFluids() {
    registerFluid(EXNFluids.WITCH_WATER.getStillFluid(), WitchWaterFluidType.STILL);
    registerFluid(EXNFluids.SEA_WATER.getStillFluid(), SeaWaterFluidType.STILL);
  }

  private void registerSieves() {
    createSieve(
        EXNBlocks.ACACIA_SIEVE.block(),
        exnihiloLoc("block/acacia_sieve"),
        vanillaLoc("block/acacia_planks"));
    createSieve(
        EXNBlocks.BAMBOO_SIEVE.block(),
        exnihiloLoc("block/bamboo_sieve"),
        vanillaLoc("block/bamboo_planks"));
    createSieve(
        EXNBlocks.BIRCH_SIEVE.block(),
        exnihiloLoc("block/birch_sieve"),
        vanillaLoc("block/birch_planks"));
    createSieve(
        EXNBlocks.CHERRY_SIEVE.block(),
        exnihiloLoc("block/cherry_sieve"),
        vanillaLoc("block/cherry_planks"));
    createSieve(
        EXNBlocks.DARK_OAK_SIEVE.block(),
        exnihiloLoc("block/dark_oak_sieve"),
        vanillaLoc("block/dark_oak_planks"));
    createSieve(
        EXNBlocks.JUNGLE_SIEVE.block(),
        exnihiloLoc("block/jungle_sieve"),
        vanillaLoc("block/jungle_planks"));
    createSieve(
        EXNBlocks.MANGROVE_SIEVE.block(),
        exnihiloLoc("block/mangrove_sieve"),
        vanillaLoc("block/mangrove_planks"));
    createSieve(
        EXNBlocks.OAK_SIEVE.block(),
        exnihiloLoc("block/oak_sieve"),
        vanillaLoc("block/oak_planks"));
    createSieve(
        EXNBlocks.SPRUCE_SIEVE.block(),
        exnihiloLoc("block/spruce_sieve"),
        vanillaLoc("block/spruce_planks"));
    createSieve(
        EXNBlocks.CRIMSON_SIEVE.block(),
        exnihiloLoc("block/crimson_sieve"),
        vanillaLoc("block/crimson_planks"));
    createSieve(
        EXNBlocks.WARPED_SIEVE.block(),
        exnihiloLoc("block/warped_sieve"),
        vanillaLoc("block/warped_planks"));
  }

  private void registerSimpleBlocks() {
    basicBlock(EXNBlocks.CRUSHED_ANDESITE.block());
    basicBlock(EXNBlocks.CRUSHED_BASALT.block());
    basicBlock(EXNBlocks.CRUSHED_BLACKSTONE.block());
    basicBlock(EXNBlocks.CRUSHED_CALCITE.block());
    basicBlock(EXNBlocks.CRUSHED_DEEPSLATE.block());
    basicBlock(EXNBlocks.CRUSHED_DIORITE.block());
    basicBlock(EXNBlocks.CRUSHED_DRIPSTONE.block());
    basicBlock(EXNBlocks.CRUSHED_GRANITE.block());
    basicBlock(EXNBlocks.CRUSHED_NETHERRACK.block());
    basicBlock(EXNBlocks.CRUSHED_TUFF.block());
    basicBlock(EXNBlocks.CRUSHED_END_STONE.block());
    basicBlock(EXNBlocks.DUST.block());

    simpleItemBlock(
        EXNBlocks.INFESTED_LEAVES.block(),
        models()
            .cubeAll(
                ForgeRegistries.BLOCKS.getKey(EXNBlocks.INFESTED_LEAVES.block()).getPath(),
                blockTexture(EXNBlocks.INFESTED_LEAVES.block()))
            .renderType("cutout"));

    simpleItemBlock(
        EXNBlocks.INFESTING_LEAVES.block(),
        models()
            .cubeAll(
                ForgeRegistries.BLOCKS.getKey(EXNBlocks.INFESTING_LEAVES.block()).getPath(),
                blockTexture(EXNBlocks.INFESTING_LEAVES.block()))
            .renderType("cutout"));
  }
}
