package novamachina.exnihilosequentia.api.datagen;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public abstract class AbstractBlockStateGenerator extends BlockStateProvider {
    protected static final String PARTICLE_TAG = "particle";

    protected AbstractBlockStateGenerator(DataGenerator gen, String modId, ExistingFileHelper exFileHelper) {
        super(gen, modId, exFileHelper);
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
}
