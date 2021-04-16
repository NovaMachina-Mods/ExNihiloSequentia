package novamachina.exnihilosequentia.common.datagen;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.MultiPartBlockStateBuilder;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import novamachina.exnihilosequentia.api.datagen.AbstractBlockStateGenerator;
import novamachina.exnihilosequentia.common.block.BlockSieve;
import novamachina.exnihilosequentia.common.init.ExNihiloBlocks;
import novamachina.exnihilosequentia.common.init.ExNihiloFluids;
import novamachina.exnihilosequentia.common.item.mesh.EnumMesh;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants.ModIds;
import static net.minecraft.block.CakeBlock.BITES;

public class ExNihiloBlockStateGenerator extends AbstractBlockStateGenerator {

    public ExNihiloBlockStateGenerator(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, ModIds.EX_NIHILO_SEQUENTIA, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        registerSimpleBlocks();
        registerSieves();
        registerBarrels();
        registerCrucibles();
        registerCake();
        registerFluids();
    }

    private void registerBarrels() {
        createBarrel(ExNihiloBlocks.BARREL_STONE.get(), mcLoc("block/stone"));
        createBarrel(ExNihiloBlocks.BARREL_ACACIA.get(), mcLoc("block/acacia_planks"));
        createBarrel(ExNihiloBlocks.BARREL_BIRCH.get(), mcLoc("block/birch_planks"));
        createBarrel(ExNihiloBlocks.BARREL_DARK_OAK.get(), mcLoc("block/dark_oak_planks"));
        createBarrel(ExNihiloBlocks.BARREL_JUNGLE.get(), mcLoc("block/jungle_planks"));
        createBarrel(ExNihiloBlocks.BARREL_SPRUCE.get(), mcLoc("block/spruce_planks"));
        createBarrel(ExNihiloBlocks.BARREL_OAK.get(), mcLoc("block/oak_planks"));
        createBarrel(ExNihiloBlocks.BARREL_CRIMSON.get(), mcLoc("block/crimson_planks"));
        createBarrel(ExNihiloBlocks.BARREL_WARPED.get(), mcLoc("block/warped_planks"));
    }

    private void registerCake() {
        VariantBlockStateBuilder builder = getVariantBuilder(ExNihiloBlocks.END_CAKE.get());

        for (int i = 1; i < 7; i++) {
            VariantBlockStateBuilder.PartialBlockstate partialBlockstate = builder.partialState();
            ConfiguredModel model = new ConfiguredModel(models().getExistingFile(modLoc("block/cake_slice" + i)));
            partialBlockstate.with(BITES, i).addModels(model);
        }
        VariantBlockStateBuilder.PartialBlockstate partialBlockstate = builder.partialState();
        ConfiguredModel model = new ConfiguredModel(models().getExistingFile(modLoc("block/cake_uneaten")));
        partialBlockstate.with(BITES, 0).addModels(model);
    }

    private void registerCrucible(Block block, ResourceLocation texture) {
        ConfiguredModel model = new ConfiguredModel(models().withExistingParent(getRegistryName(block), modLoc("block/crucible"))
                .texture(PARTICLE_TAG, texture)
                .texture("top", texture)
                .texture("bottom", texture)
                .texture("side", texture)
                .texture("inside", texture));

        simpleItemBlock(block, model.model);
    }

    private void registerCrucibles() {

        registerCrucible(ExNihiloBlocks.CRUCIBLE_FIRED.get(), modLoc("block/crucible_fired"));
        registerCrucible(ExNihiloBlocks.CRUCIBLE_UNFIRED.get(), modLoc("block/crucible_unfired"));
        registerCrucible(ExNihiloBlocks.CRUCIBLE_ACACIA.get(), mcLoc("block/acacia_log"));
        registerCrucible(ExNihiloBlocks.CRUCIBLE_BIRCH.get(), mcLoc("block/birch_log"));
        registerCrucible(ExNihiloBlocks.CRUCIBLE_DARK_OAK.get(), mcLoc("block/dark_oak_log"));
        registerCrucible(ExNihiloBlocks.CRUCIBLE_JUNGLE.get(), mcLoc("block/jungle_log"));
        registerCrucible(ExNihiloBlocks.CRUCIBLE_SPRUCE.get(), mcLoc("block/spruce_log"));
        registerCrucible(ExNihiloBlocks.CRUCIBLE_OAK.get(), mcLoc("block/oak_log"));
        registerCrucible(ExNihiloBlocks.CRUCIBLE_CRIMSON.get(), mcLoc("block/crimson_stem"));
        registerCrucible(ExNihiloBlocks.CRUCIBLE_WARPED.get(), mcLoc("block/warped_stem"));
    }

    private void registerFluids() {
        registerFluid(ExNihiloFluids.WITCH_WATER.get());
        registerFluid(ExNihiloFluids.SEA_WATER.get());
    }

    private void registerSieve(Block block, ResourceLocation texture) {
        ConfiguredModel model = new ConfiguredModel(models().withExistingParent(getRegistryName(block), modLoc("block/sieve_base"))
                .texture("texture", texture)
                .texture(PARTICLE_TAG, texture));
        MultiPartBlockStateBuilder builder = getMultipartBuilder(block);
        MultiPartBlockStateBuilder.PartBuilder partBuilder = builder.part().modelFile(model.model).addModel();
        partBuilder.end();

        for (EnumMesh mesh : EnumMesh.values()) {
            if (mesh != EnumMesh.NONE) {
                partBuilder = builder.part()
                        .modelFile(new ModelFile.ExistingModelFile(modLoc("block/" + mesh.getMeshName()), models().existingFileHelper)).addModel();
                partBuilder.condition(BlockSieve.MESH, mesh);
                partBuilder.end();
            }
        }
        simpleBlockItem(block, model.model);
    }

    private void registerSieves() {
        registerSieve(ExNihiloBlocks.SIEVE_ACACIA.get(), mcLoc("block/acacia_planks"));
        registerSieve(ExNihiloBlocks.SIEVE_BIRCH.get(), mcLoc("block/birch_planks"));
        registerSieve(ExNihiloBlocks.SIEVE_DARK_OAK.get(), mcLoc("block/dark_oak_planks"));
        registerSieve(ExNihiloBlocks.SIEVE_JUNGLE.get(), mcLoc("block/jungle_planks"));
        registerSieve(ExNihiloBlocks.SIEVE_SPRUCE.get(), mcLoc("block/spruce_planks"));
        registerSieve(ExNihiloBlocks.SIEVE_OAK.get(), mcLoc("block/oak_planks"));
        registerSieve(ExNihiloBlocks.SIEVE_CRIMSON.get(), mcLoc("block/crimson_planks"));
        registerSieve(ExNihiloBlocks.SIEVE_WARPED.get(), mcLoc("block/warped_planks"));
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
