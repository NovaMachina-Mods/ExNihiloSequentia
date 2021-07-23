package novamachina.exnihilosequentia.api.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import novamachina.exnihilosequentia.common.block.BlockSieve;
import novamachina.exnihilosequentia.common.item.mesh.EnumMesh;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

import static net.minecraft.world.level.block.CakeBlock.BITES;


public abstract class AbstractBlockStateGenerator extends BlockStateProvider {
    protected static final String PARTICLE_TAG = "particle";

    protected AbstractBlockStateGenerator(DataGenerator gen, String modId, ExistingFileHelper exFileHelper) {
        super(gen, modId, exFileHelper);
    }

    protected ResourceLocation exnihiloLoc(String string) {
        return new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, string);
    }

    protected void basicBlock(Block block) {
        simpleItemBlock(block, cubeAll(block));
    }

    protected void registerFluid(Fluid fluid) {
        ResourceLocation stillTexture = fluid.getAttributes().getStillTexture();
        ModelFile model = models().getBuilder("block/" + fluid.getRegistryName().getPath())
                .texture(PARTICLE_TAG, stillTexture);
        getVariantBuilder(fluid.defaultFluidState().createLegacyBlock().getBlock()).partialState()
                .setModels(new ConfiguredModel(model));
    }

    protected void simpleItemBlock(Block block, ModelFile modelFile) {
        simpleBlock(block, modelFile);
        simpleBlockItem(block, modelFile);
    }

    protected String getRegistryName(Block b) {
        return b.getRegistryName().toString();
    }

    protected void createBarrel(Block block, ResourceLocation texture) {
        ConfiguredModel model = new ConfiguredModel(models().withExistingParent(getRegistryName(block), exnihiloLoc("block/barrel"))
                .texture("texture", texture)
                .texture(PARTICLE_TAG, texture));

        simpleItemBlock(block, model.model);
    }

    protected void createCake(Block block) {
        VariantBlockStateBuilder builder = getVariantBuilder(block);

        for (int i = 1; i < 7; i++) {
            VariantBlockStateBuilder.PartialBlockstate partialBlockstate = builder.partialState();
            ConfiguredModel model = new ConfiguredModel(models().getExistingFile(modLoc("block/cake_slice" + i)));
            partialBlockstate.with(BITES, i).addModels(model);
        }
        VariantBlockStateBuilder.PartialBlockstate partialBlockstate = builder.partialState();
        ConfiguredModel model = new ConfiguredModel(models().getExistingFile(modLoc("block/cake_uneaten")));
        partialBlockstate.with(BITES, 0).addModels(model);
    }

    protected void createCrucible(Block block, ResourceLocation texture) {
        ConfiguredModel model = new ConfiguredModel(models().withExistingParent(getRegistryName(block), modLoc("block/crucible"))
                .texture(PARTICLE_TAG, texture)
                .texture("0", texture));

        simpleItemBlock(block, model.model);
    }

    protected void createSieve(Block block, ResourceLocation texture) {
        ConfiguredModel model = new ConfiguredModel(models().withExistingParent(getRegistryName(block), modLoc("block/sieve_base"))
                .texture("texture", texture)
                .texture(PARTICLE_TAG, texture));
        MultiPartBlockStateBuilder builder = getMultipartBuilder(block);
        MultiPartBlockStateBuilder.PartBuilder partBuilder = builder.part().modelFile(model.model).addModel();
        partBuilder.end();

        for (EnumMesh mesh : EnumMesh.values()) {
            if (mesh != EnumMesh.NONE) {
                partBuilder = builder.part().modelFile(
                        new ModelFile.ExistingModelFile(
                                new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA,
                                        "block/" + mesh.getMeshName()), models().existingFileHelper))
                        .addModel();
                partBuilder.condition(BlockSieve.MESH, mesh);
                partBuilder.end();
            }
        }
        simpleBlockItem(block, model.model);
    }
}
