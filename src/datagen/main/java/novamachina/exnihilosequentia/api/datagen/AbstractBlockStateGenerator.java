package novamachina.exnihilosequentia.api.datagen;

import static net.minecraft.world.level.block.CakeBlock.BITES;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.MultiPartBlockStateBuilder;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import novamachina.exnihilosequentia.common.block.BlockSieve;
import novamachina.exnihilosequentia.common.item.mesh.MeshType;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants.ModIds;

public abstract class AbstractBlockStateGenerator extends BlockStateProvider {

  @Nonnull protected static final String PARTICLE_TAG = "particle";

  protected AbstractBlockStateGenerator(
      @Nonnull final DataGenerator gen,
      @Nonnull final String modId,
      @Nonnull final ExistingFileHelper exFileHelper) {
    super(gen, modId, exFileHelper);
  }

  @Nonnull
  protected ResourceLocation exnihiloLoc(@Nonnull final String string) {
    return new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, string);
  }

  protected ResourceLocation vanillaLoc(@Nonnull final String string) {
    return new ResourceLocation(ModIds.MINECRAFT, string);
  }

  protected void basicBlock(@Nonnull final Block block) {
    simpleItemBlock(block, cubeAll(block));
  }

  protected void registerFluid(@Nonnull final Fluid fluid, ResourceLocation stillTexture) {
    @Nullable final ResourceLocation resourceLocation = ForgeRegistries.FLUIDS.getKey(fluid);
    if (resourceLocation != null) {
      @Nonnull
      final ModelFile model =
          models()
              .getBuilder("block/" + resourceLocation.getPath())
              .texture(PARTICLE_TAG, stillTexture);
      getVariantBuilder(fluid.defaultFluidState().createLegacyBlock().getBlock())
          .partialState()
          .setModels(new ConfiguredModel(model));
    }
  }

  protected void simpleItemBlock(@Nonnull final Block block, @Nonnull final ModelFile modelFile) {
    simpleBlock(block, modelFile);
    simpleBlockItem(block, modelFile);
  }

  @Nullable
  protected String getRegistryName(@Nonnull final Block b) {
    @Nullable final ResourceLocation resourceLocation = ForgeRegistries.BLOCKS.getKey(b);
    if (resourceLocation != null) {
      return resourceLocation.toString();
    }
    return null;
  }

  protected void createBarrel(
      @Nonnull final Block block,
      @Nonnull final ResourceLocation texture,
      @Nonnull final ResourceLocation particle) {
    @Nonnull
    final ConfiguredModel model =
        new ConfiguredModel(
            models()
                .withExistingParent(getRegistryName(block), exnihiloLoc("block/barrel_base"))
                .texture("texture", texture)
                .texture(PARTICLE_TAG, particle));

    simpleItemBlock(block, model.model);
  }

  protected void createCake(@Nonnull final Block block) {
    @Nonnull final VariantBlockStateBuilder builder = getVariantBuilder(block);

    for (int i = 1; i < 7; i++) {
      VariantBlockStateBuilder.PartialBlockstate partialBlockstate = builder.partialState();
      @Nonnull
      final ConfiguredModel model =
          new ConfiguredModel(models().getExistingFile(exnihiloLoc("block/cake_slice" + i)));
      partialBlockstate.with(BITES, i).addModels(model);
    }
    VariantBlockStateBuilder.PartialBlockstate partialBlockstate = builder.partialState();
    @Nonnull
    final ConfiguredModel model =
        new ConfiguredModel(models().getExistingFile(exnihiloLoc("block/cake_uneaten")));
    partialBlockstate.with(BITES, 0).addModels(model);
  }

  protected void createCrucible(
      @Nonnull final Block block,
      @Nonnull final ResourceLocation texture,
      @Nonnull final ResourceLocation particle) {
    @Nonnull
    final ConfiguredModel model =
        new ConfiguredModel(
            models()
                .withExistingParent(getRegistryName(block), exnihiloLoc("block/crucible_base"))
                .texture(PARTICLE_TAG, particle)
                .texture("texture", texture));

    simpleItemBlock(block, model.model);
  }

  protected void createSieve(
      @Nonnull final Block block,
      @Nonnull final ResourceLocation texture,
      @Nonnull final ResourceLocation particle) {
    @Nonnull
    final ConfiguredModel model =
        new ConfiguredModel(
            models()
                .withExistingParent(getRegistryName(block), exnihiloLoc("block/sieve_base"))
                .texture("texture", texture)
                .texture(PARTICLE_TAG, particle));
    @Nonnull final MultiPartBlockStateBuilder builder = getMultipartBuilder(block);
    @Nonnull
    MultiPartBlockStateBuilder.PartBuilder partBuilder =
        builder.part().modelFile(model.model).addModel();
    partBuilder.end();

    for (@Nonnull final MeshType mesh : MeshType.values()) {
      if (mesh != MeshType.NONE) {
        partBuilder =
            builder
                .part()
                .modelFile(
                    new ModelFile.ExistingModelFile(
                        exnihiloLoc("block/" + mesh.getMeshName()), models().existingFileHelper))
                .addModel();
        partBuilder.condition(BlockSieve.MESH, mesh);
        partBuilder.end();
      }
    }
    simpleBlockItem(block, model.model);
  }
}
