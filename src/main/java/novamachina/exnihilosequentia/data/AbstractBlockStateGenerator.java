package novamachina.exnihilosequentia.data;

import static net.minecraft.world.level.block.CakeBlock.BITES;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.VariantBlockStateBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants.ModIds;

public abstract class AbstractBlockStateGenerator extends BlockStateProvider {

  @Nonnull protected static final String PARTICLE_TAG = "particle";

  protected AbstractBlockStateGenerator(
      @Nonnull final PackOutput output,
      @Nonnull final String modId,
      @Nonnull final ExistingFileHelper exFileHelper) {
    super(output, modId, exFileHelper);
  }

  @Nonnull
  protected ResourceLocation exnihiloLoc(@Nonnull final String string) {
    return new ResourceLocation(ModIds.EX_NIHILO_SEQUENTIA, string);
  }

  protected ResourceLocation vanillaLoc(@Nonnull final String string) {
    return new ResourceLocation(ModIds.MINECRAFT, string);
  }

  protected void basicBlock(@Nonnull final Block block) {
    simpleItemBlock(block, cubeAll(block));
  }

  protected void registerFluid(@Nonnull final Fluid fluid, ResourceLocation stillTexture) {
    @Nullable final ResourceLocation resourceLocation = BuiltInRegistries.FLUID.getKey(fluid);
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
    @Nullable final ResourceLocation resourceLocation = BuiltInRegistries.BLOCK.getKey(b);
    if (resourceLocation != null) {
      return resourceLocation.toString();
    }
    return null;
  }

  protected void createBarrel(Block block, ResourceLocation texture, ResourceLocation particle) {
    ConfiguredModel model =
        new ConfiguredModel(
            models()
                .withExistingParent(getRegistryName(block), exnihiloLoc("block/barrel_base"))
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
          new ConfiguredModel(models().getExistingFile(exnihiloLoc("block/cake_slice" + i)));
      partialBlockstate.with(BITES, i).addModels(model);
    }
    VariantBlockStateBuilder.PartialBlockstate partialBlockstate = builder.partialState();
    ConfiguredModel model =
        new ConfiguredModel(models().getExistingFile(exnihiloLoc("block/cake_uneaten")));
    partialBlockstate.with(BITES, 0).addModels(model);
  }

  protected void createCrucible(Block block, ResourceLocation texture, ResourceLocation particle) {
    ConfiguredModel model =
        new ConfiguredModel(
            models()
                .withExistingParent(getRegistryName(block), exnihiloLoc("block/crucible_base"))
                .texture(PARTICLE_TAG, particle)
                .texture("texture", texture)
                .renderType("cutout_mipped"));

    simpleItemBlock(block, model.model);
  }

  protected void createSieve(Block block, ResourceLocation texture, ResourceLocation particle) {
    ConfiguredModel model =
        new ConfiguredModel(
            models()
                .withExistingParent(getRegistryName(block), exnihiloLoc("block/sieve_base"))
                .texture("texture", texture)
                .texture(PARTICLE_TAG, particle)
                .renderType("cutout_mipped"));

    simpleItemBlock(block, model.model);
  }
}
