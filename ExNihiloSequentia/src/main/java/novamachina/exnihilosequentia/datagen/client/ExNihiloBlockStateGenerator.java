package novamachina.exnihilosequentia.datagen.client;

import javax.annotation.Nonnull;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import novamachina.exnihilosequentia.datagen.api.AbstractBlockStateGenerator;
import novamachina.exnihilosequentia.common.fluid.SeaWaterFluidType;
import novamachina.exnihilosequentia.common.fluid.WitchWaterFluidType;
import novamachina.exnihilosequentia.common.init.ExNihiloBlocks;
import novamachina.exnihilosequentia.common.init.ExNihiloFluids;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

public class ExNihiloBlockStateGenerator extends AbstractBlockStateGenerator {

  public ExNihiloBlockStateGenerator(
      @Nonnull final DataGenerator gen, @Nonnull final ExistingFileHelper exFileHelper) {
    super(gen, ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, exFileHelper);
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
    createCake(ExNihiloBlocks.END_CAKE.get());
  }

  private void registerBarrels() {
    createBarrel(
        ExNihiloBlocks.BARREL_STONE.get(),
        exnihiloLoc("block/stone_barrel"),
        vanillaLoc("block/stone"));
    createBarrel(
        ExNihiloBlocks.BARREL_ACACIA.get(),
        exnihiloLoc("block/acacia_barrel"),
        vanillaLoc("block/acacia_planks"));
    createBarrel(
        ExNihiloBlocks.BARREL_BIRCH.get(),
        exnihiloLoc("block/birch_barrel"),
        vanillaLoc("block/birch_planks"));
    createBarrel(
        ExNihiloBlocks.BARREL_DARK_OAK.get(),
        exnihiloLoc("block/dark_oak_barrel"),
        vanillaLoc("block/dark_oak_planks"));
    createBarrel(
        ExNihiloBlocks.BARREL_JUNGLE.get(),
        exnihiloLoc("block/jungle_barrel"),
        vanillaLoc("block/jungle_planks"));
    createBarrel(
        ExNihiloBlocks.BARREL_OAK.get(),
        exnihiloLoc("block/oak_barrel"),
        vanillaLoc("block/oak_planks"));
    createBarrel(
        ExNihiloBlocks.BARREL_SPRUCE.get(),
        exnihiloLoc("block/spruce_barrel"),
        vanillaLoc("block/spruce_planks"));
    createBarrel(
        ExNihiloBlocks.BARREL_CRIMSON.get(),
        exnihiloLoc("block/crimson_barrel"),
        vanillaLoc("block/crimson_planks"));
    createBarrel(
        ExNihiloBlocks.BARREL_WARPED.get(),
        exnihiloLoc("block/warped_barrel"),
        vanillaLoc("block/warped_planks"));
  }

  private void registerCrucibles() {
    createCrucible(
        ExNihiloBlocks.CRUCIBLE_FIRED.get(),
        exnihiloLoc("block/fired_crucible"),
        exnihiloLoc("block/fired_crucible"));
    createCrucible(
        ExNihiloBlocks.CRUCIBLE_UNFIRED.get(),
        exnihiloLoc("block/unfired_crucible"),
        exnihiloLoc("block/unfired_crucible"));
    createCrucible(
        ExNihiloBlocks.CRUCIBLE_ACACIA.get(),
        exnihiloLoc("block/acacia_crucible"),
        vanillaLoc("block/acacia_planks"));
    createCrucible(
        ExNihiloBlocks.CRUCIBLE_BIRCH.get(),
        exnihiloLoc("block/birch_crucible"),
        vanillaLoc("block/birch_planks"));
    createCrucible(
        ExNihiloBlocks.CRUCIBLE_DARK_OAK.get(),
        exnihiloLoc("block/dark_oak_crucible"),
        vanillaLoc("block/dark_oak_planks"));
    createCrucible(
        ExNihiloBlocks.CRUCIBLE_JUNGLE.get(),
        exnihiloLoc("block/jungle_crucible"),
        vanillaLoc("block/jungle_planks"));
    createCrucible(
        ExNihiloBlocks.CRUCIBLE_OAK.get(),
        exnihiloLoc("block/oak_crucible"),
        vanillaLoc("block/oak_planks"));
    createCrucible(
        ExNihiloBlocks.CRUCIBLE_SPRUCE.get(),
        exnihiloLoc("block/spruce_crucible"),
        vanillaLoc("block/spruce_planks"));
    createCrucible(
        ExNihiloBlocks.CRUCIBLE_CRIMSON.get(),
        exnihiloLoc("block/crimson_crucible"),
        vanillaLoc("block/crimson_planks"));
    createCrucible(
        ExNihiloBlocks.CRUCIBLE_WARPED.get(),
        exnihiloLoc("block/warped_crucible"),
        vanillaLoc("block/warped_planks"));
  }

  private void registerFluids() {
    registerFluid(ExNihiloFluids.WITCH_WATER.get(), WitchWaterFluidType.STILL);
    registerFluid(ExNihiloFluids.SEA_WATER.get(), SeaWaterFluidType.STILL);
  }

  private void registerSieves() {
    createSieve(
        ExNihiloBlocks.SIEVE_ACACIA.get(),
        exnihiloLoc("block/acacia_sieve"),
        vanillaLoc("block/acacia_planks"));
    createSieve(
        ExNihiloBlocks.SIEVE_BIRCH.get(),
        exnihiloLoc("block/birch_sieve"),
        vanillaLoc("block/birch_planks"));
    createSieve(
        ExNihiloBlocks.SIEVE_DARK_OAK.get(),
        exnihiloLoc("block/dark_oak_sieve"),
        vanillaLoc("block/dark_oak_planks"));
    createSieve(
        ExNihiloBlocks.SIEVE_JUNGLE.get(),
        exnihiloLoc("block/jungle_sieve"),
        vanillaLoc("block/jungle_planks"));
    createSieve(
        ExNihiloBlocks.SIEVE_OAK.get(),
        exnihiloLoc("block/oak_sieve"),
        vanillaLoc("block/oak_planks"));
    createSieve(
        ExNihiloBlocks.SIEVE_SPRUCE.get(),
        exnihiloLoc("block/spruce_sieve"),
        vanillaLoc("block/spruce_planks"));
    createSieve(
        ExNihiloBlocks.SIEVE_CRIMSON.get(),
        exnihiloLoc("block/crimson_sieve"),
        vanillaLoc("block/crimson_planks"));
    createSieve(
        ExNihiloBlocks.SIEVE_WARPED.get(),
        exnihiloLoc("block/warped_sieve"),
        vanillaLoc("block/warped_planks"));
  }

  private void registerSimpleBlocks() {
    basicBlock(ExNihiloBlocks.CRUSHED_ANDESITE.get());
    basicBlock(ExNihiloBlocks.CRUSHED_DIORITE.get());
    basicBlock(ExNihiloBlocks.CRUSHED_GRANITE.get());
    basicBlock(ExNihiloBlocks.CRUSHED_NETHERRACK.get());
    basicBlock(ExNihiloBlocks.CRUSHED_END_STONE.get());
    basicBlock(ExNihiloBlocks.DUST.get());
    basicBlock(ExNihiloBlocks.INFESTED_LEAVES.get());
    basicBlock(ExNihiloBlocks.INFESTING_LEAVES.get());
  }
}
