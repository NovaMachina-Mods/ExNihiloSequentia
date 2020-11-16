package novamachina.exnihilosequentia.common.datagen;

import novamachina.exnihilosequentia.common.block.BlockSieve;
import novamachina.exnihilosequentia.common.block.EndCakeBlock;
import novamachina.exnihilosequentia.common.init.ModBlocks;
import novamachina.exnihilosequentia.common.init.ModFluids;
import novamachina.exnihilosequentia.common.item.mesh.EnumMesh;
import novamachina.exnihilosequentia.common.utility.Constants;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.MultiPartBlockStateBuilder;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.HashMap;
import java.util.Map;

public class BlockStates extends BlockStateProvider {
    private Map<Block, ModelFile> itemModels = new HashMap<>();

    public BlockStates(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, Constants.ModIds.EX_NIHILO_SEQUENTIA, exFileHelper);
    }

    private String name(Block b) {
        return b.getRegistryName().toString();
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

    private void registerFluids() {
        registerFluid(ModFluids.WITCH_WATER.get());
        registerFluid(ModFluids.SEA_WATER.get());
    }

    private void registerFluid(Fluid fluid) {
        ResourceLocation stillTexture = fluid.getAttributes().getStillTexture();
        ModelFile model = models().getBuilder("block/" + fluid.getRegistryName().getPath())
            .texture("particle", stillTexture);
        getVariantBuilder(fluid.getDefaultState().getBlockState().getBlock()).partialState().setModels(new ConfiguredModel(model));
    }

    private void registerCrucibles() {

        registerCrucible(ModBlocks.CRUCIBLE_FIRED.get(), new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, "block/crucible_fired"));
        registerCrucible(ModBlocks.CRUCIBLE_UNFIRED.get(), new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, "block/crucible_unfired"));
        registerCrucible(ModBlocks.CRUCIBLE_WOOD.get(), new ResourceLocation(Constants.ModIds.MINECRAFT, "block/oak_log"));
    }

    private void registerCrucible(Block block, ResourceLocation texture) {
        ConfiguredModel model = new ConfiguredModel(models().withExistingParent(name(block), modLoc("block/crucible"))
            .texture("particle", texture)
            .texture("top", texture)
            .texture("bottom", texture)
            .texture("side", texture)
            .texture("inside", texture));

        simpleItemBlock(block, model.model);
    }

    private void registerBarrels() {
        registerBarrel(ModBlocks.BARREL_STONE.get(), new ResourceLocation(Constants.ModIds.MINECRAFT, "block/stone"));
        registerBarrel(ModBlocks.BARREL_WOOD.get(), new ResourceLocation(Constants.ModIds.MINECRAFT, "block/oak_planks"));
    }

    private void registerBarrel(Block block, ResourceLocation texture) {
        ConfiguredModel model = new ConfiguredModel(models().withExistingParent(name(block), modLoc("block/barrel"))
            .texture("texture", texture)
            .texture("particle", texture));

        simpleItemBlock(block, model.model);
    }

    private void registerSieves() {
        registerSieve(ModBlocks.SIEVE.get(), new ResourceLocation(Constants.ModIds.MINECRAFT, "block/oak_planks"));
    }

    private void registerSieve(Block block, ResourceLocation texture) {
        ConfiguredModel model = new ConfiguredModel(models().withExistingParent(name(block), modLoc("block/sieve_base"))
            .texture("texture", texture)
            .texture("particle", texture));
        MultiPartBlockStateBuilder builder = getMultipartBuilder(block);
        MultiPartBlockStateBuilder.PartBuilder partBuilder = builder.part().modelFile(model.model).addModel();
        partBuilder.end();

        for(EnumMesh mesh : EnumMesh.values()) {
            if(mesh != EnumMesh.NONE) {
                partBuilder = builder.part()
                    .modelFile(new ModelFile.ExistingModelFile(new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, "block/" + mesh.getMeshName()), models().existingFileHelper)).addModel();
                partBuilder.condition(BlockSieve.MESH, mesh);
                partBuilder.end();
            }
        }
        simpleBlockItem(block, model.model);
    }

    private void registerCake() {
        VariantBlockStateBuilder builder = getVariantBuilder(ModBlocks.END_CAKE.get());

        for(int i = 1; i < 7; i++) {
            VariantBlockStateBuilder.PartialBlockstate partialBlockstate = builder.partialState();
            ConfiguredModel model = new ConfiguredModel(models().getExistingFile(new ResourceLocation("exnihilosequentia:block/cake_slice" + i)));
            partialBlockstate.with(EndCakeBlock.BITES, i).addModels(model);
        }
        VariantBlockStateBuilder.PartialBlockstate partialBlockstate = builder.partialState();
        ConfiguredModel model = new ConfiguredModel(models().getExistingFile(new ResourceLocation("exnihilosequentia:block/cake_uneaten")));
        partialBlockstate.with(EndCakeBlock.BITES, 0).addModels(model);
    }

    private void registerSimpleBlocks() {
        basicBlock(ModBlocks.CRUSHED_ANDESITE.get());
        basicBlock(ModBlocks.CRUSHED_DIORITE.get());
        basicBlock(ModBlocks.CRUSHED_GRANITE.get());
        basicBlock(ModBlocks.CRUSHED_NETHERRACK.get());
        basicBlock(ModBlocks.CRUSHED_END_STONE.get());
        basicBlock(ModBlocks.CRUSHED_SKYSTONE.get());
        basicBlock(ModBlocks.DUST.get());
        basicBlock(ModBlocks.INFESTED_LEAVES.get());
        basicBlock(ModBlocks.INFESTING_LEAVES.get());
    }

    private void basicBlock(Block block) {
        simpleItemBlock(block, cubeAll(block));
    }

    private void simpleItemBlock(Block block, ModelFile modelFile) {
        simpleBlock(block, modelFile);
        simpleBlockItem(block, modelFile);
    }
}
