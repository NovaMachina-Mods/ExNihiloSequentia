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
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import static net.minecraft.block.CakeBlock.BITES;

public class ExNihiloBlockStateGenerator extends AbstractBlockStateGenerator {

    public ExNihiloBlockStateGenerator(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, exFileHelper);
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

    private String getRegistryName(Block b) {
        return b.getRegistryName().toString();
    }

    private void registerBarrel(Block block, ResourceLocation texture) {
        ConfiguredModel model = new ConfiguredModel(models().withExistingParent(getRegistryName(block), modLoc("block/barrel"))
                .texture("texture", texture)
                .texture(PARTICLE_TAG, texture));

        simpleItemBlock(block, model.model);
    }

    private void registerBarrels() {
        registerBarrel(ExNihiloBlocks.BARREL_STONE.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/stone"));
        registerBarrel(ExNihiloBlocks.BARREL_WOOD.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/oak_planks"));
    }

    private void registerCake() {
        VariantBlockStateBuilder builder = getVariantBuilder(ExNihiloBlocks.END_CAKE.get());

        for (int i = 1; i < 7; i++) {
            VariantBlockStateBuilder.PartialBlockstate partialBlockstate = builder.partialState();
            ConfiguredModel model = new ConfiguredModel(models().getExistingFile(new ResourceLocation("exnihilosequentia:block/cake_slice" + i)));
            partialBlockstate.with(BITES, i).addModels(model);
        }
        VariantBlockStateBuilder.PartialBlockstate partialBlockstate = builder.partialState();
        ConfiguredModel model = new ConfiguredModel(models().getExistingFile(new ResourceLocation("exnihilosequentia:block/cake_uneaten")));
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

        registerCrucible(ExNihiloBlocks.CRUCIBLE_FIRED.get(), new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "block/crucible_fired"));
        registerCrucible(ExNihiloBlocks.CRUCIBLE_UNFIRED.get(), new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "block/crucible_unfired"));
        registerCrucible(ExNihiloBlocks.CRUCIBLE_WOOD.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/oak_log"));
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
                        .modelFile(new ModelFile.ExistingModelFile(new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "block/" + mesh.getMeshName()), models().existingFileHelper)).addModel();
                partBuilder.condition(BlockSieve.MESH, mesh);
                partBuilder.end();
            }
        }
        simpleBlockItem(block, model.model);
    }

    private void registerSieves() {
        registerSieve(ExNihiloBlocks.SIEVE.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/oak_planks"));
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
