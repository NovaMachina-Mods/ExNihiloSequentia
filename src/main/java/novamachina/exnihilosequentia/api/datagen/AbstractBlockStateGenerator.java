package novamachina.exnihilosequentia.api.datagen;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.Objects;

public abstract class AbstractBlockStateGenerator extends BlockStateProvider {
    protected static final String PARTICLE_TAG = "particle";

    protected AbstractBlockStateGenerator(DataGenerator gen, String modId, ExistingFileHelper exFileHelper) {
        super(gen, modId, exFileHelper);
    }

    protected String getRegistryName(Block b) {
        return b.getRegistryName().toString();
    }

    protected void basicBlock(Block block) {
        simpleItemBlock(block, cubeAll(block));
    }

    protected void registerFluid(Fluid fluid) {
        ResourceLocation stillTexture = fluid.getAttributes().getStillTexture();
        ModelFile model = models().getBuilder("block/" + Objects.requireNonNull(fluid.getRegistryName()).getPath())
                .texture(PARTICLE_TAG, stillTexture);
        getVariantBuilder(fluid.defaultFluidState().createLegacyBlock().getBlock()).partialState()
                .setModels(new ConfiguredModel(model));
    }

    protected void simpleItemBlock(Block block, ModelFile modelFile) {
        simpleBlock(block, modelFile);
        simpleBlockItem(block, modelFile);
    }

    protected void createBarrel(Block block, ResourceLocation texture) {
        ConfiguredModel model = new ConfiguredModel(models().withExistingParent(getRegistryName(block), modLoc("block/barrel"))
                .texture("texture", texture)
                .texture(PARTICLE_TAG, texture));

        simpleItemBlock(block, model.model);
    }

    protected void createGlassBarrel(Block block, ResourceLocation texture) {
        ConfiguredModel model = new ConfiguredModel(models().withExistingParent(getRegistryName(block), modLoc("block/glass_barrels"))
                .texture("texture", texture)
                .texture(PARTICLE_TAG, texture));

        simpleItemBlock(block, model.model);
    }
}
