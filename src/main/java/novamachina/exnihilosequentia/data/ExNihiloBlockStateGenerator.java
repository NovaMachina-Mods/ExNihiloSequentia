package novamachina.exnihilosequentia.data;

import static net.minecraft.world.level.block.CakeBlock.BITES;

import javax.annotation.Nonnull;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.VariantBlockStateBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;
import novamachina.exnihilosequentia.world.level.material.EXNFluids;
import novamachina.exnihilosequentia.world.level.material.SeaWaterFluidType;
import novamachina.exnihilosequentia.world.level.material.WitchWaterFluidType;
import novamachina.novacore.client.model.generators.AbstractBlockStateProvider;

public class ExNihiloBlockStateGenerator extends AbstractBlockStateProvider {

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
        EXNBlocks.STONE_BARREL.block(), modLoc("block/stone_barrel"), mcLoc("block/stone"));
    createBarrel(
        EXNBlocks.ACACIA_BARREL.block(),
        modLoc("block/acacia_barrel"),
        mcLoc("block/acacia_planks"));
    createBarrel(
        EXNBlocks.BAMBOO_BARREL.block(),
        modLoc("block/bamboo_barrel"),
        mcLoc("block/bamboo_planks"));
    createBarrel(
        EXNBlocks.BIRCH_BARREL.block(), modLoc("block/birch_barrel"), mcLoc("block/birch_planks"));
    createBarrel(
        EXNBlocks.CHERRY_BARREL.block(),
        modLoc("block/cherry_barrel"),
        mcLoc("block/cherry_planks"));
    createBarrel(
        EXNBlocks.DARK_OAK_BARREL.block(),
        modLoc("block/dark_oak_barrel"),
        mcLoc("block/dark_oak_planks"));
    createBarrel(
        EXNBlocks.JUNGLE_BARREL.block(),
        modLoc("block/jungle_barrel"),
        mcLoc("block/jungle_planks"));
    createBarrel(
        EXNBlocks.MANGROVE_BARREL.block(),
        modLoc("block/mangrove_barrel"),
        mcLoc("block/mangrove_planks"));
    createBarrel(
        EXNBlocks.OAK_BARREL.block(), modLoc("block/oak_barrel"), mcLoc("block/oak_planks"));
    createBarrel(
        EXNBlocks.SPRUCE_BARREL.block(),
        modLoc("block/spruce_barrel"),
        mcLoc("block/spruce_planks"));
    createBarrel(
        EXNBlocks.CRIMSON_BARREL.block(),
        modLoc("block/crimson_barrel"),
        mcLoc("block/crimson_planks"));
    createBarrel(
        EXNBlocks.WARPED_BARREL.block(),
        modLoc("block/warped_barrel"),
        mcLoc("block/warped_planks"));
  }

  private void registerCrucibles() {
    createCrucible(
        EXNBlocks.FIRED_CRUCIBLE.block(),
        modLoc("block/fired_crucible"),
        modLoc("block/fired_crucible"));
    createCrucible(
        EXNBlocks.UNFIRED_CRUCIBLE.block(),
        modLoc("block/unfired_crucible"),
        modLoc("block/unfired_crucible"));
    createCrucible(
        EXNBlocks.ACACIA_CRUCIBLE.block(),
        modLoc("block/acacia_crucible"),
        mcLoc("block/acacia_planks"));
    createCrucible(
        EXNBlocks.BAMBOO_CRUCIBLE.block(),
        modLoc("block/bamboo_crucible"),
        mcLoc("block/bamboo_planks"));
    createCrucible(
        EXNBlocks.BIRCH_CRUCIBLE.block(),
        modLoc("block/birch_crucible"),
        mcLoc("block/birch_planks"));
    createCrucible(
        EXNBlocks.CHERRY_CRUCIBLE.block(),
        modLoc("block/cherry_crucible"),
        mcLoc("block/cherry_planks"));
    createCrucible(
        EXNBlocks.DARK_OAK_CRUCIBLE.block(),
        modLoc("block/dark_oak_crucible"),
        mcLoc("block/dark_oak_planks"));
    createCrucible(
        EXNBlocks.JUNGLE_CRUCIBLE.block(),
        modLoc("block/jungle_crucible"),
        mcLoc("block/jungle_planks"));
    createCrucible(
        EXNBlocks.MANGROVE_CRUCIBLE.block(),
        modLoc("block/mangrove_crucible"),
        mcLoc("block/mangrove_planks"));
    createCrucible(
        EXNBlocks.OAK_CRUCIBLE.block(), modLoc("block/oak_crucible"), mcLoc("block/oak_planks"));
    createCrucible(
        EXNBlocks.SPRUCE_CRUCIBLE.block(),
        modLoc("block/spruce_crucible"),
        mcLoc("block/spruce_planks"));
    createCrucible(
        EXNBlocks.CRIMSON_CRUCIBLE.block(),
        modLoc("block/crimson_crucible"),
        mcLoc("block/crimson_planks"));
    createCrucible(
        EXNBlocks.WARPED_CRUCIBLE.block(),
        modLoc("block/warped_crucible"),
        mcLoc("block/warped_planks"));
  }

  private void registerFluids() {
    registerFluid(EXNFluids.WITCH_WATER.getStillFluid(), WitchWaterFluidType.STILL);
    registerFluid(EXNFluids.SEA_WATER.getStillFluid(), SeaWaterFluidType.STILL);
  }

  private void registerSieves() {
    createSieve(
        EXNBlocks.ACACIA_SIEVE.block(), modLoc("block/acacia_sieve"), mcLoc("block/acacia_planks"));
    createSieve(
        EXNBlocks.BAMBOO_SIEVE.block(), modLoc("block/bamboo_sieve"), mcLoc("block/bamboo_planks"));
    createSieve(
        EXNBlocks.BIRCH_SIEVE.block(), modLoc("block/birch_sieve"), mcLoc("block/birch_planks"));
    createSieve(
        EXNBlocks.CHERRY_SIEVE.block(), modLoc("block/cherry_sieve"), mcLoc("block/cherry_planks"));
    createSieve(
        EXNBlocks.DARK_OAK_SIEVE.block(),
        modLoc("block/dark_oak_sieve"),
        mcLoc("block/dark_oak_planks"));
    createSieve(
        EXNBlocks.JUNGLE_SIEVE.block(), modLoc("block/jungle_sieve"), mcLoc("block/jungle_planks"));
    createSieve(
        EXNBlocks.MANGROVE_SIEVE.block(),
        modLoc("block/mangrove_sieve"),
        mcLoc("block/mangrove_planks"));
    createSieve(EXNBlocks.OAK_SIEVE.block(), modLoc("block/oak_sieve"), mcLoc("block/oak_planks"));
    createSieve(
        EXNBlocks.SPRUCE_SIEVE.block(), modLoc("block/spruce_sieve"), mcLoc("block/spruce_planks"));
    createSieve(
        EXNBlocks.CRIMSON_SIEVE.block(),
        modLoc("block/crimson_sieve"),
        mcLoc("block/crimson_planks"));
    createSieve(
        EXNBlocks.WARPED_SIEVE.block(), modLoc("block/warped_sieve"), mcLoc("block/warped_planks"));
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
                BuiltInRegistries.BLOCK.getKey(EXNBlocks.INFESTED_LEAVES.block()).getPath(),
                blockTexture(EXNBlocks.INFESTED_LEAVES.block()))
            .renderType("cutout"));

    simpleItemBlock(
        EXNBlocks.INFESTING_LEAVES.block(),
        models()
            .cubeAll(
                BuiltInRegistries.BLOCK.getKey(EXNBlocks.INFESTING_LEAVES.block()).getPath(),
                blockTexture(EXNBlocks.INFESTING_LEAVES.block()))
            .renderType("cutout"));
  }

  protected void createBarrel(Block block, ResourceLocation texture, ResourceLocation particle) {
    ConfiguredModel model =
        new ConfiguredModel(
            models()
                .withExistingParent(getRegistryName(block), modLoc("block/barrel_base"))
                .texture("texture", texture)
                .texture(PARTICLE_TAG, particle)
                .renderType("cutout_mipped"));

    simpleItemBlock(block, model.model);
  }

  protected void createCake(Block block) {
    VariantBlockStateBuilder builder = getVariantBuilder(block);

    for (int i = 1; i < 7; i++) {
      VariantBlockStateBuilder.PartialBlockstate partialBlockstate = builder.partialState();
      ConfiguredModel model =
          new ConfiguredModel(models().getExistingFile(modLoc("block/cake_slice" + i)));
      partialBlockstate.with(BITES, i).addModels(model);
    }
    VariantBlockStateBuilder.PartialBlockstate partialBlockstate = builder.partialState();
    ConfiguredModel model =
        new ConfiguredModel(models().getExistingFile(modLoc("block/cake_uneaten")));
    partialBlockstate.with(BITES, 0).addModels(model);
  }

  protected void createCrucible(Block block, ResourceLocation texture, ResourceLocation particle) {
    ConfiguredModel model =
        new ConfiguredModel(
            models()
                .withExistingParent(getRegistryName(block), modLoc("block/crucible_base"))
                .texture(PARTICLE_TAG, particle)
                .texture("texture", texture)
                .renderType("cutout_mipped"));

    simpleItemBlock(block, model.model);
  }

  protected void createSieve(Block block, ResourceLocation texture, ResourceLocation particle) {
    ConfiguredModel model =
        new ConfiguredModel(
            models()
                .withExistingParent(getRegistryName(block), modLoc("block/sieve_base"))
                .texture("texture", texture)
                .texture(PARTICLE_TAG, particle)
                .renderType("cutout_mipped"));

    simpleItemBlock(block, model.model);
  }
}
