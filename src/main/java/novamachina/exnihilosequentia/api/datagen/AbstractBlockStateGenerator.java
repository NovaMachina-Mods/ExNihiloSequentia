package novamachina.exnihilosequentia.api.datagen;

import net.minecraft.world.level.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import novamachina.exnihilosequentia.common.block.BlockSieve;
import novamachina.exnihilosequentia.common.item.mesh.EnumMesh;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class AbstractBlockStateGenerator extends BlockStateProvider {
    @Nonnull protected static final String PARTICLE_TAG = "particle";

    protected AbstractBlockStateGenerator(@Nonnull final DataGenerator gen, @Nonnull final String modId,
                                          @Nonnull final ExistingFileHelper exFileHelper) {
        super(gen, modId, exFileHelper);
    }

    /**
     * like forgeLoc just for EXN
     * @param string name of item/block/fluid using exnihilo's RL
     * @return returns item/block/fluid's RL
     */
    @Nonnull
    protected ResourceLocation exnihiloLoc(@Nonnull final String string) {
        return new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, string);
    }

    /**
     * @param block create simple item block state out of a block
     */
    protected void basicBlock(@Nonnull final Block block) {
        simpleItemBlock(block, cubeAll(block));
    }

    /**
     * @param fluid creates fluid model out of fluid
     */
    protected void registerFluid(@Nonnull final Fluid fluid) {
        @Nonnull final ResourceLocation stillTexture = fluid.getAttributes().getStillTexture();
        @Nullable final ResourceLocation resourceLocation = fluid.getRegistryName();
        if (resourceLocation != null) {
            @Nonnull final ModelFile model = models().getBuilder("block/" + resourceLocation.getPath())
                    .texture(PARTICLE_TAG, stillTexture);
            getVariantBuilder(fluid.defaultFluidState().createLegacyBlock().getBlock()).partialState()
                    .setModels(new ConfiguredModel(model));
        }
    }

    /**
     * @param block create simple item block state out of a block
     * @param modelFile needs model of block (what should the block look like?)
     */
    protected void simpleItemBlock(@Nonnull final Block block, @Nonnull final ModelFile modelFile) {
        simpleBlock(block, modelFile);
        simpleBlockItem(block, modelFile);
    }

    /**
     * @param block need block to return registry name
     * @return returns registry name as string
     */
    @Nullable
    protected String getRegistryName(@Nonnull final Block block) {
        @Nullable final ResourceLocation resourceLocation = block.getRegistryName();
        if (resourceLocation != null)
            return block.getRegistryName().toString();
        return null;
    }

    protected void createBarrel(@Nonnull final Block block, @Nonnull final ResourceLocation texture) {
        @Nonnull final ConfiguredModel model = new ConfiguredModel(models().withExistingParent(getRegistryName(block), exnihiloLoc("block/barrel"))
                .texture("texture", texture)
                .texture(PARTICLE_TAG, texture));

        simpleItemBlock(block, model.model);
    }

    protected void createCake(@Nonnull final Block block) {
        @Nonnull final VariantBlockStateBuilder builder = getVariantBuilder(block);

        for (int i = 1; i < 7; i++) {
            VariantBlockStateBuilder.PartialBlockstate partialBlockstate = builder.partialState();
            @Nonnull final ConfiguredModel model = new ConfiguredModel(models().getExistingFile(exnihiloLoc("block/cake_slice" + i)));
            partialBlockstate.with(CakeBlock.BITES, i).addModels(model);
        }
        VariantBlockStateBuilder.PartialBlockstate partialBlockstate = builder.partialState();
        @Nonnull final ConfiguredModel model = new ConfiguredModel(models().getExistingFile(exnihiloLoc("block/cake_uneaten")));
        partialBlockstate.with(CakeBlock.BITES, 0).addModels(model);
    }

    protected void createCrucible(@Nonnull final Block block, @Nonnull final ResourceLocation texture) {
        @Nonnull final ConfiguredModel model = new ConfiguredModel(models().withExistingParent(getRegistryName(block),
                        exnihiloLoc("block/crucible"))
                .texture(PARTICLE_TAG, texture)
                .texture("top", texture)
                .texture("bottom", texture)
                .texture("side", texture)
                .texture("inside", texture));

        simpleItemBlock(block, model.model);
    }

    protected void createSieve(@Nonnull final Block block, @Nonnull final ResourceLocation texture) {
        @Nonnull final ConfiguredModel model = new ConfiguredModel(models().withExistingParent(getRegistryName(block),
                        exnihiloLoc("block/sieve_base"))
                .texture("texture", texture)
                .texture(PARTICLE_TAG, texture));
        @Nonnull final MultiPartBlockStateBuilder builder = getMultipartBuilder(block);
        @Nonnull MultiPartBlockStateBuilder.PartBuilder partBuilder = builder.part().modelFile(model.model).addModel();
        partBuilder.end();

        for (@Nonnull final EnumMesh mesh : EnumMesh.values()) {
            if (mesh != EnumMesh.NONE) {
                partBuilder = builder.part()
                        .modelFile(new ModelFile.ExistingModelFile(exnihiloLoc( "block/" + mesh.getMeshName()), models().existingFileHelper)).addModel();
                partBuilder.condition(BlockSieve.MESH, mesh);
                partBuilder.end();
            }
        }
        simpleBlockItem(block, model.model);
    }
}
